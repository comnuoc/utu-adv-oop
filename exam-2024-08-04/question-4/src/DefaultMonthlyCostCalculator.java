import java.util.List;

public class DefaultMonthlyCostCalculator implements MonthlyCostCalculator {
    @Override
    public double calculate(CostsByMonth costsByMonth, List<ApplianceUsageTime> applianceUsageTimes, List<EnergyConsumptionRate> energyConsumptionRates) {
        return applianceUsageTimes.stream()
                .map(applianceUsageTime -> calculateForEachType(costsByMonth, applianceUsageTime, energyConsumptionRates))
                .mapToDouble(f -> f)
                .sum();
    }

    private double calculateForEachType(CostsByMonth costsByMonth, ApplianceUsageTime applianceUsageTime, List<EnergyConsumptionRate> energyConsumptionRates) {
        EnergyConsumptionRate rate = findConsumptionRateByApplianceType(energyConsumptionRates, applianceUsageTime.applianceType());

        return applianceUsageTime.applianceCount()
                * (
                        applianceUsageTime.peakTimeHours() * costsByMonth.peakCostPerKwh()
                                + applianceUsageTime.normalTimeHours() * costsByMonth.normalCostPerKwh()
                                + applianceUsageTime.offPeakTimeHour() * costsByMonth.offPeakCostPerKwh()
                )
                * rate.kilowattHoursPerHour();
    }

    private EnergyConsumptionRate findConsumptionRateByApplianceType(List<EnergyConsumptionRate> energyConsumptionRates, ApplianceType applianceType) {
        return energyConsumptionRates.stream()
                .filter(energyConsumptionRate -> energyConsumptionRate.applianceType().equals(applianceType))
                .findFirst()
                .orElseThrow();
    }
}
