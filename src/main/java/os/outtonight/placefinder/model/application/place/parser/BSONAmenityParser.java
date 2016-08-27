package os.outtonight.placefinder.model.application.place.parser;


import org.bson.BsonDocument;
import org.bson.Document;
import os.outtonight.placefinder.model.application.location.LatLng;
import os.outtonight.placefinder.model.application.location.parser.BSONLatLngParser;
import os.outtonight.placefinder.model.application.location.parser.LatLngParser;
import os.outtonight.placefinder.model.application.place.Address;
import os.outtonight.placefinder.model.application.place.Amenity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.List;

public class BSONAmenityParser implements AmenityParser<Document> {

    private LatLngParser latLngParser = new BSONLatLngParser();
    private AddressParser addressParser = new BSONAddressParser();

    @Override
    public Amenity parseOne(Document input) {
        LatLng location = latLngParser.parseLatLng(input.get("location"));
        Address address = addressParser.parseAddress(input.get("address"));
        String name = input.getString("name");
        String amenityType = input.getString("amenity");
        String id = input.getObjectId("_id").toString();
        return new Amenity(location, name, id, address, amenityType);
    }

    @Override
    public List<Amenity> parseMultiple(Document document) {
        throw new NotImplementedException();
    }
}
