package os.outtonight.placefinder.model.application.place.parser;

import os.outtonight.placefinder.model.application.place.Amenity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


public interface AmenityParser<INPUT_TYPE> extends PlaceParser<INPUT_TYPE,Amenity> {

    @Override
    default Amenity parseOne(INPUT_TYPE input){
        return Amenity.EMPTY;
    }

    @Override
    default List<Amenity> parseMultiple(INPUT_TYPE type){
        return Collections.singletonList(Amenity.EMPTY);
    }

    @Override
    default Class getOutputClass(){
        return Amenity.class;
    }

}
