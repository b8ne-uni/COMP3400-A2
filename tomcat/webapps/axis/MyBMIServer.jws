/**
 * MyBMIServer.jws
 *
 * Web service consumed by MyBMIClient interface
 * Author: Ben Sutter
 * ID: c3063467
 * Edited: 26/10/17
 */
public class MyBMIServer {

    /**
     * Calculate a BMI based of weight and height
     * @param weight
     * @param height
     * @return
     */
    public String calcBMI (String weight, String height)
    {
        // Increment Call Count
        DataStore.setCallCount(DataStore.getCallCount() + 1);

        double h = Double.parseDouble(height);
        double w = Double.parseDouble(weight);
        double bmi = w / Math.pow((h/100), 2);

        for (BMIRange current : DataStore.getRanges()) {
            // Handle if upper is *
            if (current.getUpper().equals("*") && Double.parseDouble(current.getLower()) <= bmi) {
                return String.format("%.2f", bmi) + " " + current.getName().toUpperCase();
            }
            // Handle other cases
            if (Double.parseDouble(current.getLower()) <= bmi && Double.parseDouble(current.getUpper()) >= bmi) {
                return String.format("%.2f", bmi) + " " + current.getName().toUpperCase();
            }
        }

        return String.format("%.2f", bmi) + " " + "UNDEFINED";
    }

    /**
     * List all stored BMI ranges
     * @return
     */
    public String listRanges()
    {
        // Increment Call Count
        DataStore.setCallCount(DataStore.getCallCount() + 1);

        // Return UNDEFINED if empty
        if (DataStore.getRanges().size() == 0) {
            return "UNDEFINED";
        }

        String output = "";
        for (BMIRange current : DataStore.getRanges()) {
            output += current.getLower() + " - " + current.getUpper() + " " + current.getName().toUpperCase() + "\n";
        }

        return output;
    }

    /**
     * List the 'Normal' lower and upper BMI limits for a given height
     * @param height
     * @return
     */
    public String listWeights (String height)
    {
        // Increment Call Count
        DataStore.setCallCount(DataStore.getCallCount() + 1);

        double h = Double.parseDouble(height);

        // Get Normal Upper and Lower BMI's
        double upper = 0;
        double lower = 0;
        for (BMIRange current : DataStore.getRanges()) {
            if (current.getNormal()) {
                if (current.getUpper().equals("*")) {
                    return "UNDEFINED";
                }
                upper = Double.parseDouble(current.getUpper());
                lower = Double.parseDouble(current.getLower());
                break;
            }
        }

        // If no normal found, return UNDEFINED
        if (upper == 0 || lower == 0) {
            return "UNDEFINED";
        }

        // Return calculated weights
        return String.format("%.2f", (lower * Math.pow((h/100), 2))) + " - " +  String.format("%.2f", (upper * Math.pow((h/100), 2)));
    }
}
