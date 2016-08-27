package os.outtonight.placefinder.database;


import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.types.ObjectId;
import os.outtonight.placefinder.AppConfig;
import os.outtonight.placefinder.model.application.location.LatLng;
import os.outtonight.placefinder.model.application.place.Place;
import os.outtonight.placefinder.model.application.place.parser.AmenityParser;
import os.outtonight.placefinder.model.application.place.parser.BSONAmenityParser;
import os.outtonight.placefinder.model.application.place.parser.BSONGeoNearParser;
import os.outtonight.placefinder.model.application.place.parser.GeoNearParser;

import java.util.List;

public class MongoDBConnection implements DatabaseConnection {


    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private GeoNearParser geoNearParser;
    private AmenityParser amenityParser;

    public MongoDBConnection(){
        amenityParser = new BSONAmenityParser();
        geoNearParser = new BSONGeoNearParser(amenityParser);
    }

    @Override
    public boolean connect() {
        mongoClient = new MongoClient(AppConfig.MONGO_HOST, AppConfig.MONGO_PORT);
        mongoDatabase = mongoClient.getDatabase(AppConfig.MONGO_DB);
        return mongoDatabase != null;
    }

    @Override
    public void disconnect() {
        mongoClient.close();
    }

    @Override
    public List<Place> getPlaces(LatLng location) {
        return geoNearParser.parseMultiple(queryPlacesByLocation(location));
    }

    @Override
    public Place getPlace(LatLng location) {
        return geoNearParser.parseOne(queryPlacesByLocation(location));
    }

    private Document queryPlacesByLocation(LatLng location){
        //TODO: Order of Lat/Lng!
        String command = String.format("{\n" +
                        "     geoNear: \"%s\",\n" +
                        "     near: { type: \"Point\", coordinates: [ %f, %f ] },\n" +
                        "     spherical: %b,\n" +
                        "     maxDistance: %d\n" +
                        "   }", AppConfig.QUERY_INDEX, location.getLng(), location.getLat(),
                AppConfig.GEO_NEAR_SPHERICAL, AppConfig.GEO_NEAR_MAX_DISTANCE);
        return mongoDatabase.runCommand(BsonDocument.parse(command));
    }

    @Override
    public Place getPlace(String id) {
        BasicDBObject queryObj = new BasicDBObject("_id", new ObjectId(id));
        return amenityParser.parseOne(mongoDatabase.getCollection(AppConfig.QUERY_INDEX).find(queryObj).first());
    }
}
