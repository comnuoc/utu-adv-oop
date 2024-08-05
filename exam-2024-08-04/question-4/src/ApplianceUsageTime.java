import java.util.Objects;

/*
 * @.classInvariant:
 *      applianceType() != null
 *      && applianceCount() >= 0
 *      && peakTimeHours() >= 0
 *      && normalTimeHours() >= 0
 *      && offPeakTimeHour() >= 0
 */
public record ApplianceUsageTime(ApplianceType applianceType, int applianceCount, double peakTimeHours, double normalTimeHours, double offPeakTimeHour) {
    public ApplianceUsageTime {
        Objects.requireNonNull(applianceType);

        if (applianceCount < 0) {
            throw new NegativeNumberException("Number of appliances should be greater than or equal zero");
        }

        if (peakTimeHours < 0) {
            throw new NegativeNumberException("Peak time hours should be greater than or equal zero");
        }

        if (normalTimeHours < 0) {
            throw new NegativeNumberException("Normal time hours should be greater than or equal zero");
        }

        if (offPeakTimeHour < 0) {
            throw new NegativeNumberException("Off peak time hours should be greater than or equal zero");
        }
    }
}
