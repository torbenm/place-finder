package os.outtonight.placefinder.model.application.place.parser;


import org.bson.BsonDocument;
import org.bson.Document;
import os.outtonight.placefinder.model.application.place.Amenity;
import os.outtonight.placefinder.model.application.place.Place;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BSONGeoNearParser<OUTPUT_TYPE extends Place> extends GeoNearParser<Document, OUTPUT_TYPE> {

    public BSONGeoNearParser(PlaceParser<Document, OUTPUT_TYPE> placeParser) {
        super(placeParser);
    }

    @Override
    public OUTPUT_TYPE parseOne(Document input) {
        return parseInputDocument(input, 1).get(0);
    }

    @Override
    public List<OUTPUT_TYPE> parseMultiple(Document input) {
        return parseInputDocument(input, Integer.MAX_VALUE);
    }

    private List<OUTPUT_TYPE> parseInputDocument(Document input, int amount){
        List results = (List)input.get("results");
        List resultList = (List)results
                .stream()
                .filter(n -> results.indexOf(n) < amount)
                .map(n -> placeParser.parseOne((Document)((Document)n).get("obj")))
                .collect(Collectors.toList());
        return (List<OUTPUT_TYPE>) resultList;
    }
}
