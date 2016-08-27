package os.outtonight.placefinder.model.application.place.parser;

import os.outtonight.placefinder.model.application.place.Address;

/**
 * Created by torbenmeyer on 24/08/16.
 */
public interface AddressParser<INPUT_TYPE> {
    default Address parseAddress(INPUT_TYPE input){
        return Address.EMPTY;
    }
}
