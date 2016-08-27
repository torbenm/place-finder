package os.outtonight.placefinder.model.application.place.parser;

import os.outtonight.placefinder.model.application.place.Place;

import java.util.Collection;
import java.util.List;

public interface PlaceParser<INPUT_TYPE, OUTPUT_TYPE extends Place> {

    OUTPUT_TYPE parseOne(INPUT_TYPE input);
    List<OUTPUT_TYPE> parseMultiple(INPUT_TYPE input);

    default Class getOutputClass(){
        return Place.class;
    }

}
