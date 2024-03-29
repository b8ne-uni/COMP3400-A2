/**
 * BMIRange.java
 *
 * POJO to represent a single BMI range instance
 * Author: Ben Sutter
 * ID: c3063467
 * Edited: 26/10/17
 */
public class BMIRange {
    private String name;
    private String lower;
    private String upper;
    private boolean normal;

    public BMIRange(String name, String lower, String upper, boolean normal) {
        this.name = name;
        this.lower = lower;
        this.upper = upper;
        this.normal = normal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLower() {
        return lower;
    }

    public String getUpper() {
        return upper;
    }

    public boolean getNormal() {
        return normal;
    }
}