package os.outtonight.placefinder.model.application.place;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by torbenmeyer on 21/08/16.
 */
public class Address {

    private static final String FORMAT_DE = "$s $n, $z $c";
    private static final String EMPTY_STRING = "EMPTY";
    public static final Address EMPTY = new Address(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);

    private final String street;
    private final String housenumber;
    private final String zip;
    private final String city;
    private final String country;

    public Address(String street, String housenumber, String zip, String city, String country) {
        this.street = street;
        this.housenumber = housenumber;
        this.zip = zip;
        this.city = city;
        this.country = country;
    }


    public String getStreet() {
        return street;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString(){
        return toString(FORMAT_DE);
    }
    public String toString(String format){
        return format.replace("$s", getStreet()).replace("$n", getHousenumber())
                .replace("$z", getZip()).replace("$c", getCity()).replace("$C", getCountry());
    }

    @JsonIgnore
    public boolean isEmpty(){
        return EMPTY.equals(this);
    }





}
