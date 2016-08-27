package os.outtonight.placefinder.model.application.location.parser;

import org.bson.Document;
import os.outtonight.placefinder.model.application.location.LatLng;

import java.util.List;


public class BSONLatLngParser implements LatLngParser<Document> {

    @Override
    public LatLng parseLatLng(Document input) {
        List coords = (List)input.get("coordinates");
        if(coords.size() == 2){
            //TODO: Order of Lat/Lng!
            return new LatLng(Float.parseFloat(coords.get(1).toString()),
                    Float.parseFloat(coords.get(0).toString()));
        }
        return LatLng.EMPTY;
    }
}
