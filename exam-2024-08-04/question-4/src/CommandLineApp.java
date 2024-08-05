public class CommandLineApp {
    private final MonthlyCostCalculator monthlyCostCalculator;

    /*
     * @.pre: monthlyCostCalculator != null
     * @.post: object is constructed.
     */
    public CommandLineApp(MonthlyCostCalculator monthlyCostCalculator) {
        this.monthlyCostCalculator = monthlyCostCalculator;
    }

    /*
     * Ask for information from the user, then calculate and display monthly cost.
     *
     * @.pre: true
     * @.post: print the monthly cost.
     */
    public void run() {
        System.out.println("To do");
    }
}
