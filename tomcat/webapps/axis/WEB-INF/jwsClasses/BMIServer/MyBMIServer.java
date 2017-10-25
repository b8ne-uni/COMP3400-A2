package BMIServer;

import BMIServer.BMIRange;

public class MyBMIServer {

    private BMIRange[] ranges;

    public MyBMIServer() {
        ranges = new BMIRange[99];
    }

    public String calcBMI (String weight, String height)
    {
        double h = Double.parseDouble(height);
        double w = Double.parseDouble(weight);
        double bmi = w / Math.pow(h, 2);

        for (BMIRange current : ranges) {

        }

        return String.format("%.2f", bmi) + " " + "UNDEFINED";
    }

    public String listRanges()
    {
        // Return UNDEFINED if empty
        if (ranges.length == 0) {
            return "UNDEFINED";
        }

        String output = "";
        for (BMIRange current : ranges) {
        }

        return output;
    }

    public String listWeights (String height)
    {
        double h = Double.parseDouble(height);

        // Get Normal Upper and Lower BMI's
        double upper = 0;
        double lower = 0;
        for (BMIRange current : ranges) {

        }

        // If no normal found, return UNDEFINED
        if (upper == 0 || lower == 0) {
            return "UNDEFINED";
        }

        // Return calculated weights
        return (lower * Math.pow(h, 2)) + " - " +  (upper * Math.pow(h, 2));
    }
}

