package os.outtonight.placefinder.model.application.place;

import os.outtonight.placefinder.model.application.location.LatLng;

/**
 * Created by torbenmeyer on 21/08/16.
 */
public interface Place {

    String UNDEFINED = "UNDEFINED";

    default LatLng getLocation(){
        return new LatLng(0, 0);
    }

    default String getName(){
        return UNDEFINED;
    }

    default String getID(){
        return UNDEFINED;
    }

    default String getType(){
        return UNDEFINED;
    }

    default Address getAddress(){
        return new Address(UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED);
    }

    static Class implementationByIndex(String index){
        switch(index.toUpperCase()){
            case "AMENITIES":
                return Amenity.class;
        }
        return null;
    }

}
