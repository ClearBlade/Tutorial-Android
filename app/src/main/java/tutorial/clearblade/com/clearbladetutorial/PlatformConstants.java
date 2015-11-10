package tutorial.clearblade.com.clearbladetutorial;

/**
 * Created by sanketdeshpande on 8/20/15.
 */
public class PlatformConstants {

    public final static String SYSTEMKEY = "d2f7d2dc0ab8cfbfa49cf0feb50b";
    public final static String SYSTEMSECRET = "D2F7D2DC0AD0E6AEB89AB0E6FAB501";
    public final static String PLATFORM_URL = "https://rtp.clearblade.com";
    public final static String MESSAGING_URL = "tcp://rtp.clearblade.com:1883";
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
