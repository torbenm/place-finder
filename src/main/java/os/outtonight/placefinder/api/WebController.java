package os.outtonight.placefinder.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import os.outtonight.placefinder.api.mapper.JSONMapper;
import os.outtonight.placefinder.database.DatabaseConnection;
import os.outtonight.placefinder.database.MongoDBConnection;
import os.outtonight.placefinder.model.application.ApplicationInfo;
import os.outtonight.placefinder.model.application.location.LatLng;

@RestController
public class WebController {

    private final JSONMapper jsonMapper = new JSONMapper();
    private final DatabaseConnection dbConnection = new MongoDBConnection();

    public WebController(){
        dbConnection.connect();
    }

    @RequestMapping("/info")
    public String version(){
        return jsonMapper.map(new ApplicationInfo());
    }

    @RequestMapping("/{lat},{lng}/place.json")
    public String placeByLatLng(@PathVariable float lat, @PathVariable float lng){
        LatLng latLng = new LatLng(lat, lng);
        return jsonMapper.map(dbConnection.getPlace(latLng));
    }

    @RequestMapping("/{lat},{lng}/places.json")
    public String placesByLatLng(@PathVariable float lat, @PathVariable float lng){
        LatLng latLng = new LatLng(lat, lng);
        return jsonMapper.map(dbConnection.getPlaces(latLng));
    }

    @RequestMapping("/{id}/place.json")
    public String placeById(@PathVariable String id){
        return jsonMapper.map(dbConnection.getPlace(id));
    }
}
