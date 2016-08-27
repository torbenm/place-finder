package os.outtonight.placefinder.database;


import os.outtonight.placefinder.model.application.location.LatLng;
import os.outtonight.placefinder.model.application.place.Place;

import java.util.List;

public interface DatabaseConnection {

    boolean connect();
    void disconnect();

    List<Place> getPlaces(LatLng location);
    Place getPlace(LatLng location);
    Place getPlace(String id);
}
