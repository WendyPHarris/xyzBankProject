package BANK.LoanTypes;

//imported packages
import BANK.Customer;
import BANK.Loan;

public class PersonalLoan extends Loan {
    // Additional properties specific to PersonalLoan
    private String borrowerName;
    private String loanPurpose;

    // Constructors
    // Default constructor
    public PersonalLoan() {
        super();
        this.borrowerName = "Unknown";
        this.loanPurpose = "Unknown";
    }

    // Parameterized constructor
    public PersonalLoan(String recordID, double interestRate, double amountLeftToPay, int loanTermLeft, String borrowerName, String loanPurpose, Customer customer) {
        super(customer, recordID, "Personal", interestRate, amountLeftToPay, loanTermLeft);
        this.borrowerName = borrowerName;
        this.loanPurpose = loanPurpose;
    }
    

    // Getter and setter methods for additional properties
    // Getter for borrowerName
    public String getBorrowerName() {
        return borrowerName;
    }

    // Setter for borrowerName
    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    // Getter for loanPurpose
    public String getLoanPurpose() {
        return loanPurpose;
    }

    // Setter for loanPurpose
    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }
}
