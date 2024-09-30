package BANK.LoanTypes;

//imported packages
import BANK.Customer;
import BANK.Loan;

public class OtherLoan extends Loan {
    // Additional properties specific to OtherLoan
    private String purpose;

    // Constructors
    // Default constructor
    public OtherLoan() {
        super(); // Call to superclass constructor
        this.purpose = "Unknown";
    }

    // Parameterized constructor
    public OtherLoan(String recordID, double interestRate, double amountLeftToPay, int loanTermLeft, String purpose, Customer customer) {
        super(customer, recordID, "Other", interestRate, amountLeftToPay, loanTermLeft);
        this.purpose = purpose;
    }
    

    // Getter and setter methods for additional property
    // Getter for purpose
    public String getPurpose() {
        return purpose;
    }

    // Setter for purpose
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
