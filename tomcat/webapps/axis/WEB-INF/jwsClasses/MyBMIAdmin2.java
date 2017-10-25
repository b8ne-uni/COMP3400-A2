import java.util.Iterator;
import java.util.LinkedList;

public class MyBMIAdmin {

    public static int CALL_COUNT = 0;
    private static String ADMIN_USER = "admin";
    private static String ADMIN_PASSWORD = "bodymass";

    public static LinkedList<BMIRange> ranges = new LinkedList<>();

    public boolean addRange (String user, String pwd, String lower, String upper, String name, boolean normal)
    {
        // Increment Call Count
        MyBMIAdmin.CALL_COUNT = MyBMIAdmin.CALL_COUNT++;

        // Authenticate
        if (!this.auth(user, pwd)) {
            return false;
        }

        if (MyBMIAdmin.ranges.size() == 0) {
            MyBMIAdmin.ranges.push(new BMIRange(name, lower, upper, normal));
        } else {
            int index = -1;
            // First iterate to search names
            for (BMIRange current : MyBMIAdmin.ranges) {
                if (current.getName().equals(name)) {
                    return false;
                }
            }

            // Next see if the new range overlaps
            for (BMIRange current : MyBMIAdmin.ranges) {
                if (!this.rangeOverlap(current.getLower(), current.getUpper(), lower, upper)) {
                    return false;
                }
            }

            // Finally, if new range is 'normal' search for existing
            if (normal) {
                for (BMIRange current : MyBMIAdmin.ranges) {
                    if (current.getNormal()) {
                        return false;
                    }
                }
            }

            // Safe to add new range
            MyBMIAdmin.ranges.addLast(new BMIRange(name, lower, upper, normal));
        }

        return true;
    }

    public boolean deleteRange (String user, String pwd, String name)
    {
        // Increment Call Count
        MyBMIAdmin.CALL_COUNT = MyBMIAdmin.CALL_COUNT++;

        // Authenticate
        if (!this.auth(user, pwd)) {
            return false;
        }

        // Iterate ranges and delete on the fly if found
        for (BMIRange current : MyBMIAdmin.ranges) {
            if (current.getName().equals(name)) {
                MyBMIAdmin.ranges.remove(current);
                return true;
            }
        }

        // Not found so return false
        return false;
    }

    public boolean setName (String user, String pwd, String oldName, String newName)
    {
        // Increment Call Count
        MyBMIAdmin.CALL_COUNT = MyBMIAdmin.CALL_COUNT++;

        // Authenticate
        if (!this.auth(user, pwd)) {
            return false;
        }

        // Iterate ranges and change name on the fly if found
        for (BMIRange current : MyBMIAdmin.ranges) {
            if (current.getName().equals(oldName)) {
                current.setName(newName);
                return true;
            }
        }

        // Not found so return false
        return false;
    }

    public int callCount(String user, String pwd) {
        // Authenticate
        if (!this.auth(user, pwd)) {
            return -1;
        }

        return MyBMIAdmin.CALL_COUNT++;
    }

    private boolean auth(String user, String pwd)
    {
        return user.equals(MyBMIAdmin.ADMIN_USER) && pwd.equals(MyBMIAdmin.ADMIN_PASSWORD);
    }

    private boolean rangeOverlap(String currentLower, String currentUpper, String newLower, String newUpper)
    {
        double cL = Double.parseDouble(currentLower);
        double cU = Double.parseDouble(currentUpper);
        double nL = Double.parseDouble(newLower);
        double nU = Double.parseDouble(newUpper);

        // Case 1, overlap on lower boundary
        if (nL <= cL && nU > cL && nU <= cU) {
            return false;
        }

        // Case 2, overlap on upper boundary
        if (nL >= cL && nL < cU && nU >= cU) {
            return false;
        }

        // Case 3, complete overlap
        if (nL <= cL && nU >= cU) {
            return false;
        }

        // Case 4, lies within
        if (nL >= cL && nU <= cU) {
            return false;
        }

        return true;
    }
}
