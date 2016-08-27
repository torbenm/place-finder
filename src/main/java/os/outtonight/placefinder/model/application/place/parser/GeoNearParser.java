package os.outtonight.placefinder.model.application.place.parser;

import os.outtonight.placefinder.model.application.place.Place;

import java.util.Collection;

public abstract class GeoNearParser<INPUT_TYPE, OUTPUT_TYPE extends Place> implements PlaceParser<INPUT_TYPE, OUTPUT_TYPE> {

    protected PlaceParser<INPUT_TYPE, OUTPUT_TYPE> placeParser;

    public GeoNearParser(PlaceParser<INPUT_TYPE, OUTPUT_TYPE> placeParser) {
        this.placeParser = placeParser;
    }

    @Override
    public Class getOutputClass(){
        return placeParser.getOutputClass();
    }
}
