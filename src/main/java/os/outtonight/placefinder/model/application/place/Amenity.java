package os.outtonight.placefinder.model.application.place;


import os.outtonight.placefinder.model.application.location.LatLng;

public class Amenity implements Place{

    public static String TYPE = "Amenity";

    private final LatLng location;
    private final String name;
    private final String id;
    private final Address address;
    private final String amenityType;

    private static final String UNDEFINED = "UNDEFINED";
    public static final Amenity EMPTY = new Amenity(LatLng.EMPTY, UNDEFINED, UNDEFINED, Address.EMPTY, UNDEFINED);

    public Amenity(LatLng location, String name, String id, Address address, String amenityType) {
        this.location = location;
        this.name = name;
        this.id = id;
        this.address = address;
        this.amenityType = amenityType;
    }

    @Override
    public LatLng getLocation() {
        return location;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    public String getAmenityType(){
        return amenityType;
    }

    @Override
    public String toString(){
        return String.format("ID: %s, Name: %s, Address: %s, Location: %s",
                getID(), getName(), getAddress(), getLocation());
    }
}
