package os.outtonight.placefinder.model.application.location.parser;

import os.outtonight.placefinder.model.application.location.LatLng;

public interface LatLngParser<INPUT_TYPE> {

    default LatLng parseLatLng(INPUT_TYPE input){
        return LatLng.EMPTY;
    }
}
