package BANK;
//imported package
import BANK.Interfaces.CheckPrinter;

//imported libraries
import java.util.ArrayList;
import java.util.List;

public class Customer implements CheckPrinter {
    // Attributes
    private String customerID;
    private double income;
    private boolean eligibilityStatus;
    private ArrayList<Loan> creditRecords; // Changed to ArrayList

    // Constructor
    public Customer(String customerID, double income, List<Loan> creditRecords, boolean eligibilityStatus) {
        this.customerID = customerID;
        this.income = income;
        this.creditRecords = new ArrayList<>(creditRecords); // Initialize ArrayList with provided list
        this.eligibilityStatus = checkEligibility(null); // Initially set eligibility status
    }

    // Getters and setters for attributes

    // Getter for customerID
    public String getCustomerID() {
        return customerID;
    }

    // Setter for customerID
    public void setCustomerID(String customerID) {
        // Validate the format of the customer ID
        if (customerID.matches("[A-Z]{3}\\d{3}")) {
            this.customerID = customerID;
        } else {
            System.out.println("Invalid format. Please enter a 3-letter capital code followed by 3 digits.");
        }
    }

    // Getter for income
    public double getIncome() {
        return income;
    }

    // Setter for income
    public void setIncome(double income) {
        this.income = income;
    }

    // Getter for eligibilityStatus
    public boolean isEligibilityStatus() {
        return eligibilityStatus;
    }

    // Setter for eligibilityStatus
    public void setEligibilityStatus(boolean eligibilityStatus) {
        this.eligibilityStatus = eligibilityStatus;
    }

    // Getter for creditRecords
    public ArrayList<Loan> getCreditRecords() { // Change return type to ArrayList
        return creditRecords;
    }
    
    // Setter for creditRecords
    public void setCreditRecords(ArrayList<Loan> creditRecords) { // Change parameter type to ArrayList
        this.creditRecords = creditRecords;
    }

    // Method to delete a record from the customer's credit records
    public void deleteRecord(String recordID) {
        // Iterate over the credit records to find the record with the given recordID
        for (int i = 0; i < creditRecords.size(); i++) {
            Loan loan = creditRecords.get(i);
            if (loan.getRecordID().equals(recordID)) {
                // Remove the record if found
                creditRecords.remove(i);
                System.out.println("Record with ID " + recordID + " deleted successfully.");
                return;
            }
        }
        // If the record with the given recordID is not found
        System.out.println("Record with ID " + recordID + " not found for this customer.");
    }

    // Method to add a new record to the customer's credit records
    public void addRecord(Loan newLoan) {
        creditRecords.add(newLoan);
        System.out.println("New record added successfully.");
    }
    
    
    // Implementing methods from CheckPrinter interface
    @Override
    public boolean checkEligibility(Customer customer) {
        double totalAmountLeftToPay = 0.0;
        for (Loan loan : creditRecords) {
            totalAmountLeftToPay += loan.getAmountLeftToPay();
        }
        return totalAmountLeftToPay <= 4 * income;
    }

    @Override
    public void printCustomerDetails(String customerID) {
        // If the provided customerID doesn't match, return without printing
        if (!this.customerID.equals(customerID)) {
            return;
        }
    
        // Print customer's eligibility status
        System.out.println("CustomerID: " + customerID);
        System.out.println("Eligible to arrange new loans - " + (eligibilityStatus ? "YES" : "NO"));
    
        // Print credit records
        System.out.printf("\t %-15s %-15s %-15s %-15s %-15s\n",
            "RecordID", "LoanType", "IntRate", "AmountLeft", "TimeLeft");
        for (Loan loan : creditRecords) {
            System.out.printf("\t %-15s %-15s %-15s %-15s %-15s\n",
                    loan.getRecordID(), loan.getLoanType(), loan.getInterestRate(),
                    loan.getAmountLeftToPay(), loan.getLoanTermLeft());
        }
    }
    

    // Method to print all records for all customers
    public static void printAllRecords(List<Customer> customers) {
        for (Customer customer : customers) {
            // Print customer's eligibility status
            System.out.println("CustomerID: " + customer.getCustomerID());
            System.out.println("Eligible to arrange new loans - " + (customer.isEligibilityStatus() ? "YES" : "NO"));
            // Print credit records
            for (Loan loan : customer.getCreditRecords()) {
                System.out.printf("\t %-15s %-15s %-15s %-15s %-15s %-15s\n",
                        "CustomerID", "RecordID", "LoanType", "IntRate", "AmountLeft", "TimeLeft");
                System.out.printf("\t %-15s %-15s %-15s %-15s %-15s %-15s\n",
                        customer.getCustomerID(), loan.getRecordID(), loan.getLoanType(),
                        loan.getInterestRate(), loan.getAmountLeftToPay(), loan.getLoanTermLeft());

            }
            // Add a line of equals signs for separation
            System.out.println("==========================================================");   
        }
    }  

}
