package os.outtonight.placefinder.model.application;

import os.outtonight.placefinder.AppConfig;

public class ApplicationInfo {

    public String version;
    public String author;
    public String lastChange;

    public ApplicationInfo(){

        version = AppConfig.VERSION;
        author =  AppConfig.AUTHOR;
        lastChange = AppConfig.LAST_CHANGE;

    }
}
