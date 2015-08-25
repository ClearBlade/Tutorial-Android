package tutorial.clearblade.com.clearbladetutorial;

/**
 * Created by sanketdeshpande on 8/20/15.
 */
public class PlatformConstants {

    public final static String SYSTEMKEY = "YOUR_SYSTEMKEY";
    public final static String SYSTEMSECRET = "YOUR_SYSTEMSECRET";
    public final static String PLATFORM_URL = "YOUR_PLATFORMURL";
    public final static String MESSAGING_URL = "YOUR_MESSAGINGURL";
    public final static String COLLECTIONID = "cee2a7dd0abef288e8f0fcf18663";
    public static String USER_EMAIL = "";

    public static String getSystemKey() {

        return SYSTEMKEY;
    }

    public static String getSystemSecret() {

        return SYSTEMSECRET;
    }

    public static String getPlatformUrl() {

        return PLATFORM_URL;
    }

    public static String getMessagingUrl() {

        return MESSAGING_URL;
    }

    public static String getUserEmail() {

        return USER_EMAIL;
    }

    public static String getCollectionID() {

        return COLLECTIONID;
    }


    public static void setUserEmail(String email) {

        USER_EMAIL = email;
    }
}
