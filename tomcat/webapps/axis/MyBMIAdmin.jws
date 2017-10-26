/**
 * MyBMIAdmin.jws
 *
 * Web service consumed by MyBMIAdmin interface
 * Author: Ben Sutter
 * ID: c3063467
 * Edited: 26/10/17
 */
public class MyBMIAdmin {
    /**
     * Add a BMI range
     * @param user
     * @param pwd
     * @param lower
     * @param upper
     * @param name
     * @param normal
     * @return
     */
    public boolean addRange (String user, String pwd, String lower, String upper, String name, boolean normal)
    {
        // Increment Call Count
        DataStore.setCallCount(DataStore.getCallCount() + 1);

        // Authenticate
        if (!this.auth(user, pwd)) {
            return false;
        }

        if (DataStore.getRanges().size() == 0) {
            if (upper.equals("*")) {
                DataStore.pushRange(new BMIRange(name, formatDouble(lower),  upper, normal));
            } else {
                DataStore.pushRange(new BMIRange(name, formatDouble(lower), formatDouble(upper), normal));
            }
        } else {
            // First iterate to search names
            for (BMIRange current : DataStore.getRanges()) {
                if (current.getName().equals(name)) {
                    return false;
                }
            }

            // Next see if the new range overlaps
            // Based on the specs only 1 range can have * upper, so check that first
            if (upper.equals("*")) {
                double highestUpper = 0;
                // Iterate all ranges
                for (BMIRange current : DataStore.getRanges()) {
                    // Return false if * already exists
                    if (current.getUpper().equals("*")) {
                        return false;
                    }

                    // Update our highest Lower value
                    double cU = Double.parseDouble(current.getUpper());
                    if (cU > highestUpper) {
                        highestUpper = cU;
                    }
                }

                // If we are here, a upper of * doesnt exist, so check that the new lower is larger than the highest Upper
                if (highestUpper > Double.parseDouble(lower)) {
                    return false;
                } else {
                    // Safe to add new range
                    DataStore.pushRange(new BMIRange(name, formatDouble(lower), upper, normal));
                    return true;
                }
            }

            // This must be a standard range, so check all other overlap cases
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
            DataStore.pushRange(new BMIRange(name, formatDouble(lower), formatDouble(upper), normal));
        }

        return true;
    }

    /**
     * Delete a BMI range if it exists
     * @param user
     * @param pwd
     * @param name
     * @return
     */
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

    /**
     * Rename a BMI range if it exists
     * @param user
     * @param pwd
     * @param oldName
     * @param newName
     * @return
     */
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

    /**
     * Get the Web Service call count
     * @param user
     * @param pwd
     * @return
     */
    public int callCount(String user, String pwd) {
        // Authenticate
        if (!this.auth(user, pwd)) {
            return -1;
        }

        DataStore.setCallCount(DataStore.getCallCount() + 1);
        return DataStore.getCallCount();
    }

    /**
     * Authenticate Admin requests
     * @param user
     * @param pwd
     * @return
     */
    private boolean auth(String user, String pwd)
    {
        return user.equals(DataStore.getAdminUser()) && pwd.equals(DataStore.getAdminPassword());
    }

    /**
     * Determine of a new range will overlap an existing one
     * @param currentLower
     * @param currentUpper
     * @param newLower
     * @param newUpper
     * @return
     */
    private boolean rangeOverlap(String currentLower, String currentUpper, String newLower, String newUpper)
    {
        // Parse these now as we need them in the first check
        double cL = Double.parseDouble(currentLower);
        double nU = Double.parseDouble(newUpper);

        // Exception case 1
        if (currentUpper.equals("*") && nU <= cL) {
            return true;
        }

        // Exception case 2, current range has * upper and new range overlaps it
        if (currentUpper.equals("*") && nU > cL) {
            return false;
        }

        // Now parse the other params
        double cU = Double.parseDouble(currentUpper);
        double nL = Double.parseDouble(newLower);

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

    /**
     * Format all values to 2 decimal places
     * @param value
     * @return
     */
    private String formatDouble(String value) {
        return String.format("%.2f", Double.parseDouble(value));
    }
}
