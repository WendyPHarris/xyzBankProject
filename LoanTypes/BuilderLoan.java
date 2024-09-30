package BANK.LoanTypes;

//imported packages
import BANK.Customer;
import BANK.Loan;

public class BuilderLoan extends Loan {
    // Additional properties specific to BuilderLoan
    private double overpaymentPercentage;

    // Constructors
    // Default constructor
    public BuilderLoan() {
        super(); // Call to superclass constructor
        this.overpaymentPercentage = 0.0;
    }

    // Parameterized constructor
    public BuilderLoan(String recordID, double interestRate, double amountLeftToPay, int loanTermLeft, double overpaymentPercentage, Customer customer) {
        super(customer, recordID, "Builder", interestRate, amountLeftToPay, loanTermLeft);
        if (overpaymentPercentage >= 0 && overpaymentPercentage <= 2) {
            this.overpaymentPercentage = overpaymentPercentage;
        } else {
            throw new IllegalArgumentException("Overpayment percentage must be between 0 and 2.");
        }
    }
    
    // Constructor chaining
    public BuilderLoan(Customer customer, String recordID, double interestRate, double amountLeftToPay, int loanTermLeft, double overpaymentPercentage) {
        this(recordID, interestRate, amountLeftToPay, loanTermLeft, overpaymentPercentage, customer);
    }

    // Getter and setter methods for additional properties
    // Getter for overpaymentPercentage
    public double getOverpaymentPercentage() {
        return overpaymentPercentage;
    }

    // Setter for overpaymentPercentage
    public void setOverpaymentPercentage(double overpaymentPercentage) {
        if (overpaymentPercentage >= 0 && overpaymentPercentage <= 2) {
            this.overpaymentPercentage = overpaymentPercentage;
        } else {
            throw new IllegalArgumentException("Overpayment percentage must be between 0 and 2.");
        }
    }
}
