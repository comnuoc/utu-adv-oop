import java.util.Objects;

/*
 * @.classInvariant:
 *      month() != null
 */
public record CostsByMonth(Month month, double peakCostPerKwh, double normalCostPerKwh, double offPeakCostPerKwh) {
    /*
     * @.pre: true
     * @.post: Object is constructed
     *      throws NullPointerException if month is null
     */
    public CostsByMonth {
        Objects.requireNonNull(month, "Month should not be null");
    }
}


