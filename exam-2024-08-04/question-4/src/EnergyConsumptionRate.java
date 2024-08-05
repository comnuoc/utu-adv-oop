import java.util.Objects;

/*
 * @.classInvariant:
 *      applianceType() != null
 *      && kilowattHoursPerHour() >= 0
 */
public record EnergyConsumptionRate(ApplianceType applianceType, double kilowattHoursPerHour) {
    /*
     * @.pre: true
     * @.post: Object is constructed
     *      throws NullPointerException if applianceType is null
     *      throws NegativeNumberException if kilowattHoursPerHour < 0
     */
    public EnergyConsumptionRate {
        Objects.requireNonNull(applianceType, "Appliance type should not be null");

        if (kilowattHoursPerHour < 0) {
            throw new NegativeNumberException("Consumption rate should be greater than or equal zero");
        }
    }
}