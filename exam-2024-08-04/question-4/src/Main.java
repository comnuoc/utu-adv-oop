public class Main {
    public static void main(String[] args) {
        MonthlyCostCalculator calculator = new DefaultMonthlyCostCalculator();
        CommandLineApp app = new CommandLineApp(calculator);
        app.run();
    }
}