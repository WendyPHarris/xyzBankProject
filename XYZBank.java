package BANK;

// Imported packages
import BANK.LoanTypes.AutoLoan;
import BANK.LoanTypes.BuilderLoan;
import BANK.LoanTypes.MortgageLoan;
import BANK.LoanTypes.OtherLoan;
import BANK.LoanTypes.PersonalLoan;

//imported necessary libraries
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class XYZBank {
    public static void main(String[] args) {

        // Initialize scanner
        Scanner scanner = new Scanner(System.in);

        //choose between manual or auto input
        System.out.println("Do you wish to see manual creation and testing or do you wish to do self user input for customer records?: (Manual/Input/QUIT)");
        String option = scanner.nextLine();

        option = option.toLowerCase();
        if (option.equals("input")){

            // User prompt
            System.out.print("Enter the maximum number of customers (or type 'QUIT' to stop): ");

            // Read the input for the maximum number of customers
            String maxCustomersInput = scanner.next();

            // Check if the user wants to quit
            if (maxCustomersInput.equalsIgnoreCase("QUIT")) {
                System.out.println("Exiting customer registration.");
                scanner.close();
                return; // Quit the registration process
            }
            
            // Continue with input validation for numerical value
            int maxCustomers;
            while (true) {
                try {
                    maxCustomers = Integer.parseInt(maxCustomersInput);
                    if (maxCustomers > 0) {
                        break; // If true, breaks out of loop and input is accepted
                    } else {
                        System.out.println("Invalid. Please enter a positive number");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numerical value or 'QUIT' to stop.");
                }
                // Read the next input for the maximum number of customers
                maxCustomersInput = scanner.next();
            }

            // ArrayList to store customers
            ArrayList<Customer> customers = new ArrayList<>();

            // Set to store existing record IDs
            Set<String> existingRecordIDs = new HashSet<>();

            // Register new customers
            for (int i = 0; i < maxCustomers; i++) {
                System.out.println("\nCustomer " + (i + 1) + ":");
                String customerID;
                while (true) {
                    System.out.print("Enter Customer ID (3 capital letters followed by 3 digits) OR type 'QUIT' to stop): ");
                    customerID = scanner.next();

                    if (customerID.equalsIgnoreCase("QUIT")) {
                        System.out.println("Exiting customer registration.");
                        break; // Exit the registration process
                    }

                    // Validate the format of the customer ID
                    if (!customerID.matches("[A-Z]{3}\\d{3}")) {
                        System.out.println("Invalid format. Please enter a 3-letter capital code followed by 3 digits.");
                    } else {
                        break; // Exit the loop if the format is valid
                    }
                }

                if (customerID.equalsIgnoreCase("QUIT")) {
                    continue; // Skip to the next iteration of the loop if "QUIT" was entered
                }

                System.out.print("Enter Annual Income: ");
                double income = scanner.nextDouble();

                // Create a new customer
                Customer customer = new Customer(customerID, income, new ArrayList<>(), false);
                customers.add(customer);

                System.out.println("Customer registered successfully.");
            }

            // Allow customers to input loan records
            int numLoans = 0;
            int maxrecords = 0;
            int numRegisteredRecords = 0; // Variable to track the total number of registered records

            for (Customer customer : customers) {
                System.out.println("\nCustomer ID: " + customer.getCustomerID());
            
                while (true) {
                    try {
                        System.out.print("Enter the number of loans to input for this customer: ");
                        numLoans = scanner.nextInt();
                        maxrecords += numLoans;
            
                        for (int i = 0; i < numLoans; i++) {
                            System.out.println("\nLoan " + (i + 1) + ":");
                            String recordID;
                            while (true) {
                                System.out.print("Enter Record ID (6 digits or QUIT to move on): ");
                                recordID = scanner.next();
            
                                if (recordID.equalsIgnoreCase("QUIT")) {
                                    System.out.println("Exiting loan registration for this customer.");
                                    break; // Exit the loop for entering loan details
                                }
            
                                // Check if the record ID already exists
                                if (existingRecordIDs.contains(recordID) || recordID.length() != 6 || !recordID.matches("\\d+")) {
                                    System.out.println("Invalid record ID. Please enter a unique 6-digit numeric ID.");
                                } else {
                                    existingRecordIDs.add(recordID);
                                    break; // Exit the loop if the record ID is valid
                                }
                            }
            
                            if (recordID.equalsIgnoreCase("QUIT")) {
                                break; // Exit the loop for entering loan details
                            }
                            // Additional loan details
                            System.out.print("Enter Loan Type (Auto/Builder/Mortgage/Personal/Other): ");
                            String loanType;
                            while (true) {
                                loanType = scanner.next().toLowerCase(); // Convert to lowercase for easier comparison
                                if (loanType.equals("auto") || loanType.equals("builder") || loanType.equals("mortgage") || loanType.equals("personal") || loanType.equals("other")) {
                                    break; // Exit loop if a valid loan type is entered
                                } else {
                                    System.out.println("Invalid loan type. Please enter one of the following: Auto, Builder, Mortgage, Personal, Other");
                                }
                            }
            
                            System.out.print("Enter Interest Rate: ");
                            double interestRate;
                            while (true) {
                                try {
                                    interestRate = scanner.nextDouble();
                                    break; // Exit loop if a valid double is entered
                                } catch (java.util.InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid numeric value for the interest rate.");
                                    scanner.next(); // Consume invalid input
                                }
                            }
            
                            System.out.print("Enter Amount Left to Pay (in thousands of pounds): ");
                            double amountLeftToPay;
                            while (true) {
                                try {
                                    amountLeftToPay = scanner.nextDouble();
                                    break; // Exit loop if a valid double is entered
                                } catch (java.util.InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid numeric value for the amount left to pay.");
                                    scanner.next(); // Consume invalid input
                                }
                            }
            
                            System.out.print("Enter Loan Term Left (in years): ");
                            int loanTermLeft;
                            while (true) {
                                try {
                                    loanTermLeft = scanner.nextInt();
                                    break; // Exit loop if a valid int is entered
                                } catch (java.util.InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid numeric value for the loan term left.");
                                    scanner.next(); // Consume invalid input
                                }
                            }
            
                            Loan loan = null;

                            //additional loan type options

                            switch (loanType.toLowerCase()) {
                                case "auto":
                                    System.out.print("Enter Car Model: ");
                                    String carModel = scanner.next();
                                    System.out.print("Enter Year Manufactured: ");
                                    int yearManufactured;
                                    while (true) {
                                        try {
                                            yearManufactured = scanner.nextInt();
                                            if (yearManufactured <= 0) {
                                                System.out.println("Invalid input. Please enter a positive integer for the year manufactured.");
                                            } else {
                                                break;
                                            }
                                        } catch (java.util.InputMismatchException e) {
                                            System.out.println("Invalid input. Please enter a valid numeric value for the year manufactured.");
                                            scanner.next(); // Consume invalid input
                                        }
                                    }
                                    loan = new AutoLoan(recordID, interestRate, amountLeftToPay, loanTermLeft, carModel, yearManufactured, customer);
                                    break;
                                case "builder":
                                    System.out.print("Enter Overpayment Percentage: (must be between 0 and 2 to be eligible)");
                                    double overpaymentPercentageBuilder;
                                    while (true) {
                                        try {
                                            overpaymentPercentageBuilder = scanner.nextDouble();
                                            if (overpaymentPercentageBuilder < 0) {
                                                System.out.println("Invalid input. Please enter a non-negative value for the overpayment percentage.");
                                            } else {
                                                break;
                                            }
                                        } catch (java.util.InputMismatchException e) {
                                            System.out.println("Invalid input. Please enter a valid numeric value for the overpayment percentage.");
                                            scanner.next(); // Consume invalid input
                                        }
                                    }
                                    loan = new BuilderLoan(recordID, interestRate, amountLeftToPay, loanTermLeft, overpaymentPercentageBuilder, customer);
                                    break;
                                case "mortgage":
                                    System.out.print("Enter Overpayment Percentage: ");
                                    double overpaymentPercentageMortgage;
                                    while (true) {
                                        try {
                                            overpaymentPercentageMortgage = scanner.nextDouble();
                                            if (overpaymentPercentageMortgage < 0) {
                                                System.out.println("Invalid input. Please enter a non-negative value for the overpayment percentage.");
                                            } else {
                                                break;
                                            }
                                        } catch (java.util.InputMismatchException e) {
                                            System.out.println("Invalid input. Please enter a valid numeric value for the overpayment percentage.");
                                            scanner.next(); // Consume invalid input
                                        }
                                    }
                                    loan = new MortgageLoan(recordID, interestRate, amountLeftToPay, loanTermLeft, overpaymentPercentageMortgage, customer);
                                    break;
                                case "personal":
                                    System.out.print("Enter Borrower Name: ");
                                    String borrowerName = scanner.nextLine(); // Use nextLine() to allow spaces in the name
                                    System.out.print("Enter Loan Purpose: ");
                                    String loanPurpose = scanner.nextLine(); // Use nextLine() to allow spaces in the purpose
                                    loan = new PersonalLoan(recordID, interestRate, amountLeftToPay, loanTermLeft, borrowerName, loanPurpose, customer);
                                    break;
                                case "other":
                                    System.out.print("Enter Purpose: ");
                                    String purpose = scanner.nextLine(); // Use nextLine() to allow spaces in the purpose
                                    loan = new OtherLoan(recordID, interestRate, amountLeftToPay, loanTermLeft, purpose, customer);
                                    break;
                                default:
                                    System.out.println("Invalid loan type.");
                                
                            }
                            
            
                            if (loan != null) {
                                customer.getCreditRecords().add(loan);
                                numRegisteredRecords++; // Increment the counter for registered records
                                System.out.println("Loan added successfully.");
                            }
                        }
                        break; // Exit the loop if input is successful

                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid input. Please enter valid data.");
                        scanner.next(); // Consume invalid input
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                // Check if the user chose to quit during loan registration
                if (numLoans == 0 || numLoans != maxrecords) {
                    break; // Move on printing
                }
            }

            // Example usage
            if (numRegisteredRecords != 0){//only displays if records have been registered

                System.out.println("Type the customerID you wish to view (e.g., 'AAA123') OR 'ALL' to view all records: ");
                String input = scanner.next();

                // Print information about all customers or a specific customer's details

                // Print maximum number of records
                System.out.println("\n Maximum number of Records: " + maxrecords);
                // Print registered records
                System.out.println("Number of Registered Records: " + numRegisteredRecords);

                if (input.equalsIgnoreCase("ALL")) {
                    System.out.println("\nAll Loan Records:");
                    Customer.printAllRecords(customers);
                    System.out.println(); // Add a newline for better readability
                } else {
                    // Print details for a specific customer
                    System.out.println("\nCustomer Details for ID: " + input);
                    for (Customer customer : customers) {
                        if (customer.getCustomerID().equals(input)) {
                            customer.printCustomerDetails(input); // Print customer details for the specified customerID
                            break;
                        }
                    }   
                }

            } 
        //manual testing
        } else if (option.equals("manual")){
        // Create some sample customers
        Customer customer1 = new Customer("ABC123", 50000, new ArrayList<>(), false);
        Customer customer2 = new Customer("DEF456", 60000, new ArrayList<>(), false);

        // Create loans and associate them with customers
        Loan loan1 = new AutoLoan("000001", 5.0, 20000, 5, "Toyota", 2022, customer1);
        Loan loan2 = new MortgageLoan("000002", 3.5, 300000, 20, 0.0, customer1);
        Loan loan3 = new PersonalLoan("000003", 8.0, 10000, 3, "John Doe", "Home Renovation", customer2);

        // Add loans to the credit records of customers
        customer1.getCreditRecords().add(loan1);
        customer1.getCreditRecords().add(loan2);
        customer2.getCreditRecords().add(loan3);

        // Create a list of customers
        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);

        //new line fore readability
        System.out.println("\n");

        // Now you can print all records associated with each customer
        Customer.printAllRecords(customers);

        // Test updating a loan
        loan1.setAmountLeftToPay(15000);
        loan1.setLoanTermLeft(4);
        loan2.setInterestRate(3.0);

        //new line fore readability
        System.out.println("\n");

        // Print updated loans
        System.out.println("\nUpdated Loans after updating 000001(amountleft and timeleft) and 000002 (int rate): ");
        Customer.printAllRecords(customers);

        // Test deleting a loan
        customer2.getCreditRecords().remove(loan3);

        //new line fore readability
        System.out.println("\n");

        // Print remaining loans
        System.out.println("\nRemaining Loans after 000003 deletion:");
        Customer.printAllRecords(customers);

        // Create a new loan for customer 2
        Loan loan4 = new AutoLoan("000004", 4.5, 25000, 6, "Honda", 2023, customer2);

        // Add the new loan to the credit records of customer 2
        customer2.getCreditRecords().add(loan4);

        //new line fore readability
        System.out.println("\n");

        // Print customer records after adding the new loan
        System.out.println("\nCustomer Records after adding a new loan for customer 2:");
        Customer.printAllRecords(customers);


        } else if(option.equals("quit")){
            System.out.println("quiting...");
        }else {
            System.out.println("Invalid option. please choose either manual, input or QUIT");
        }
        // Close the scanner
        scanner.close(); 
    }
}
