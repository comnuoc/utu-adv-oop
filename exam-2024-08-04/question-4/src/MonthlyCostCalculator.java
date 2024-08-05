import java.util.List;

public interface MonthlyCostCalculator {
    /*
     * Calculates the monthly energy cost for a household based on:
     *      - The number of common household appliances.
     *      - Their average usage patterns.
     *      - And the energy consumption rates during peak, normal, and off-peak times.
     *
     * @.pre: costsByMonth != null
     * @.post: TRUE
     */
    double calculate(CostsByMonth costsByMonth, List<ApplianceUsageTime> applianceUsageTimes, List<EnergyConsumptionRate> energyConsumptionRates);
}
