import java.util.LinkedList;

public class DataStore {
    static private int CALL_COUNT = 0;
    static private String ADMIN_USER = "admin";
    static private String ADMIN_PASSWORD = "bodymass";
    static private LinkedList<BMIRange> ranges = new LinkedList<>();

    public static int getCallCount() {
        return CALL_COUNT;
    }

    public static void setCallCount(int callCount) {
        CALL_COUNT = callCount;
    }

    public static String getAdminUser() {
        return ADMIN_USER;
    }

    public static void setAdminUser(String adminUser) {
        ADMIN_USER = adminUser;
    }

    public static String getAdminPassword() {
        return ADMIN_PASSWORD;
    }

    public static void setAdminPassword(String adminPassword) {
        ADMIN_PASSWORD = adminPassword;
    }

    public static LinkedList<BMIRange> getRanges() {
        return ranges;
    }

    public static void setRanges(LinkedList<BMIRange> ranges) {
        DataStore.ranges = ranges;
    }

    public static void pushRange(BMIRange range) {
        DataStore.ranges.push(range);
    }

    public static void deleteRange(BMIRange range) {
        DataStore.ranges.remove(range);
    }
}
