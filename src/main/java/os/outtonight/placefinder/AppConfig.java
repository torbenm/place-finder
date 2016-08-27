package os.outtonight.placefinder;


public class AppConfig {

    //TODO: move these to application.properties or a different file
    public static final String VERSION = "0.1.0";
    public static final String AUTHOR = "torben.meyer@mailbox.org";
    public static final String LAST_CHANGE = "2016-08-21";


    public static final String MONGO_DB = "poi-database";
    public static final String MONGO_HOST = "localhost";
    public static final int MONGO_PORT = 27017;

    public static final String QUERY_INDEX = "amenities";
    public static final boolean GEO_NEAR_SPHERICAL = true;
    public static final int GEO_NEAR_MAX_DISTANCE = 100;

}
