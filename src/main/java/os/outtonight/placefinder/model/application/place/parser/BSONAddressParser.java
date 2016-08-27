package os.outtonight.placefinder.model.application.place.parser;

import org.bson.Document;
import os.outtonight.placefinder.model.application.place.Address;


public class BSONAddressParser implements AddressParser<Document> {
    @Override
    public Address parseAddress(Document input) {
        String country = input.getString("country");
        String street = input.getString("street");
        String housenumber = input.getString("housenumber");
        String postcode = input.getString("postcode");
        String city = input.getString("city");
        return new Address(street, housenumber, postcode, city, country);
    }
}
