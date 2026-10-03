import java.util.Scanner;

public class ElectricityBillGenerator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Customer details
        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Customer ID: ");
        int customerId = sc.nextInt();

        System.out.print("Enter Units Consumed: ");
        double units = sc.nextDouble();

        double bill = 0;

        // Validate units
        if (units < 0) {
            System.out.println("Invalid units entered.");
            sc.close();
            return;
        }

        // Electricity bill calculation
        if (units <= 100) {

            bill = units * 1.50;

        } else if (units <= 200) {

            bill = (100 * 1.50)
                    + ((units - 100) * 2.50);

        } else if (units <= 300) {

            bill = (100 * 1.50)
                    + (100 * 2.50)
                    + ((units - 200) * 4.00);

        } else {

            bill = (100 * 1.50)
                    + (100 * 2.50)
                    + (100 * 4.00)
                    + ((units - 300) * 6.00);
        }

        // Display bill
        System.out.println("\n==============================");
        System.out.println("    ELECTRICITY BILL");
        System.out.println("==============================");

        System.out.println("Customer Name : " + name);
        System.out.println("Customer ID   : " + customerId);
        System.out.println("Units Consumed: " + units);

        System.out.println("------------------------------");

        System.out.printf("Total Bill    : Rs. %.2f%n", bill);

        System.out.println("==============================");
        System.out.println("     Thank You!");
        System.out.println("==============================");

        sc.close();
    }
}
