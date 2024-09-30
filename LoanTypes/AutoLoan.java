package BANK.LoanTypes;

//imported packages
import BANK.Customer;
import BANK.Loan;

public class AutoLoan extends Loan {
    // Additional properties specific to AutoLoan
    private String carModel;
    private int yearManufactured;

    // Constructors
    // Default constructor
    public AutoLoan() {
        super(); // Call to superclass constructor
        this.carModel = "Unknown";
        this.yearManufactured = 0;
    }

    // Parameterized constructor
    public AutoLoan(String recordID, double interestRate, double amountLeftToPay, int loanTermLeft, String carModel, int yearManufactured, Customer customer) {
        super(customer, recordID, "Auto", interestRate, amountLeftToPay, loanTermLeft);
        this.carModel = carModel;
        this.yearManufactured = yearManufactured;
    }
    
    
    // Getter and setter methods for additional properties
    // Getter for carModel
    public String getCarModel() {
        return carModel;
    }

    // Setter for carModel
    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    // Getter for yearManufactured
    public int getYearManufactured() {
        return yearManufactured;
    }

    // Setter for yearManufactured
    public void setYearManufactured(int yearManufactured) {
        this.yearManufactured = yearManufactured;
    }
}
