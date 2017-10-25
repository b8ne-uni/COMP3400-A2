public class MyBMIServer {

    public String calcBMI (String weight, String height)
    {
        double h = Double.parseDouble(height);
        double w = Double.parseDouble(weight);
        double bmi = w / Math.pow(h, 2);

        for (BMIRange current : MyBMIAdmin.ranges) {
            if (Double.parseDouble(current.getLower()) <= bmi && Double.parseDouble(current.getUpper()) >= bmi) {
                return String.format("%.2f", bmi) + " " + current.getName().toUpperCase();
            }
        }

        return String.format("%.2f", bmi) + " " + "UNDEFINED";
    }

    public String listRanges()
    {
        // Return UNDEFINED if empty
        if (MyBMIAdmin.ranges.size() == 0) {
            return "UNDEFINED";
        }

        String output = "";
        for (BMIRange current : MyBMIAdmin.ranges) {
            output += current.getLower() + " - " + current.getUpper() + "\n";
        }

        return output;
    }

    public String listWeights (String height)
    {
        double h = Double.parseDouble(height);

        // Get Normal Upper and Lower BMI's
        double upper = 0;
        double lower = 0;
        for (BMIRange current : MyBMIAdmin.ranges) {
            if (current.getNormal()) {
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
        return (lower * Math.pow(h, 2)) + " - " +  (upper * Math.pow(h, 2));
    }
}
