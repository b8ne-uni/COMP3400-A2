package BMIServer;

import java.util.LinkedList;

public class MyBMIAdmin {
    public boolean addRange (String user, String pwd, String lower, String upper, String name, boolean normal)
    {
        // Increment Call Count
        DataStore.setCallCount(DataStore.getCallCount() + 1);

        // Authenticate
        if (!this.auth(user, pwd)) {
            return false;
        }

        if (DataStore.getRanges().size() == 0) {

            DataStore.pushRange(new BMIRange(name, lower, upper, normal));
        } else {
            int index = -1;
            // First iterate to search names
            for (BMIRange current : DataStore.getRanges()) {
                if (current.getName().equals(name)) {
                    return false;
                }
            }

            // Next see if the new range overlaps
            for (BMIRange current : DataStore.getRanges()) {
                if (!this.rangeOverlap(current.getLower(), current.getUpper(), lower, upper)) {
                    return false;
                }
            }

            // Finally, if new range is 'normal' search for existing
            if (normal) {
                for (BMIRange current : DataStore.getRanges()) {
                    if (current.getNormal()) {
                        return false;
                    }
                }
            }

            // Safe to add new range
            DataStore.pushRange(new BMIRange(name, lower, upper, normal));
        }

        return true;
    }

    public boolean deleteRange (String user, String pwd, String name)
    {
        // Increment Call Count
        DataStore.setCallCount(DataStore.getCallCount() + 1);

        // Authenticate
        if (!this.auth(user, pwd)) {
            return false;
        }

        // Iterate ranges and delete on the fly if found
        for (BMIRange current : DataStore.getRanges()) {
            if (current.getName().equals(name)) {
                DataStore.deleteRange(current);
                return true;
            }
        }

        // Not found so return false
        return false;
    }

    public boolean setName (String user, String pwd, String oldName, String newName)
    {
        // Increment Call Count
        DataStore.setCallCount(DataStore.getCallCount() + 1);

        // Authenticate
        if (!this.auth(user, pwd)) {
            return false;
        }

        // Iterate ranges and change name on the fly if found
        for (BMIRange current : DataStore.getRanges()) {
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

        DataStore.setCallCount(DataStore.getCallCount() + 1);
        return DataStore.getCallCount();
    }

    private boolean auth(String user, String pwd)
    {
        return user.equals(DataStore.getAdminUser()) && pwd.equals(DataStore.getAdminPassword());
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
