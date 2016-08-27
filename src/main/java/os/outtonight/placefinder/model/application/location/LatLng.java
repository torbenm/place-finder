package os.outtonight.placefinder.model.application.location;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class LatLng {

    private final float lat;
    private final float lng;

    private static final float UNDEFINED = Float.MAX_VALUE;
    public static final LatLng EMPTY = new LatLng(UNDEFINED, UNDEFINED);

    public LatLng(float lat, float lng){
        this.lat = lat;
        this.lng = lng;
    }

    public float getLat() {
        return lat;
    }
    public float getLng() {
        return lng;
    }

    @JsonIgnore
    public boolean isEmpty(){
        return EMPTY.equals(this);
    }

    @Override
    public String toString(){
        return String.format("%f, %f", getLat(), getLng());
    }

}
