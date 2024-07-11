/**
 * Simulates a day and its corresponding rate.
 * <p>
 *      This class simulates the days of the month which
 *     also stores and edits the respective rate for each day.
 * </p>
 * @author Albrecht Gabriel Abad
 * @since July 2024
 * @version 1.0
 */

public class DateModifier {
    private int day; // The day value
    private float pricePercent; // Rate of this day

    /**
     * Constructor for DateModifier.
     * @param day The value of the day assigned.
     * @param pricePercent The rate assigned for this day.
     */
    public DateModifier(int day, float pricePercent){
        this.day = day;
        this.pricePercent = pricePercent;
    }

    /**
     * Setter for the rate of this day.
     * @param pricePercent The new percentage rate of this day.
     */
    public void setPricePercent(float pricePercent) {
        this.pricePercent = pricePercent;
    }

    /**
     * Getter for the current day rate.
     * @return the rate of this day.
     */
    public float getPricePercent() {
        return pricePercent;
    }

    /**
     * Getter for the day value.
     * @return the an integer day value.
     */
    public int getDay() {
        return day;
    }
}
