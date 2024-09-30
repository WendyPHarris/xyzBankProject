package BANK;

//imported libraries
import java.util.List;
import java.util.ArrayList;

public abstract class Loan {
    // Store all loan records
    private static List<Loan> allRecords = new ArrayList<>();

    // Attributes
    private Customer customer; // Store the associated Customer object
    private String recordID;
    private String loanType;
    private double interestRate;
    private double amountLeftToPay;
    private int loanTermLeft;

    // Constructors
    // Default constructor
    public Loan() {
        this.customer = null;
        this.recordID = "000000";
        this.loanType = "Other";
        this.interestRate = 0.0;
        this.amountLeftToPay = 0.0;
        this.loanTermLeft = 0;
        allRecords.add(this);
    }

    // Parameterized constructor
    public Loan(Customer customer, String recordID, String loanType, double interestRate, double amountLeftToPay, int loanTermLeft) {
        this.customer = customer;
        this.recordID = recordID;
        this.loanType = loanType;
        this.interestRate = interestRate;
        this.amountLeftToPay = amountLeftToPay;
        this.loanTermLeft = loanTermLeft;
        allRecords.add(this);
    }

    // Getters and setters
    // Getter for customerID
    public String getCustomerID() {
        return (customer != null) ? customer.getCustomerID() : "N/A";
    }

    // Getter for recordID
    public String getRecordID() {
        return recordID;
    }

    // Setter for recordID
    public void setRecordID(String recordID) {
        // Ensure that the recordID is exactly six digits and unique
        if (recordID.matches("\\d{6}") && isUniqueRecordID(recordID)) {
            this.recordID = recordID;
        } else {
            System.out.println("Invalid Record ID. It should be a six-digit unique number.");
        }
    }

    // Check if the given recordID is unique
    private boolean isUniqueRecordID(String recordID) {
        for (Loan loan : allRecords) {
            if (loan.getRecordID().equals(recordID)) {
                return false; // Not unique
            }
        }
        return true; // Unique
    }

    // Getter for loanType
    public String getLoanType() {
        return loanType;
    }

    // Setter for loanType
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    // Getter for interestRate
    public double getInterestRate() {
        return interestRate;
    }

    // Setter for interestRate
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    // Getter for amountLeftToPay
    public double getAmountLeftToPay() {
        return amountLeftToPay;
    }

    // Setter for amountLeftToPay
    public void setAmountLeftToPay(double amountLeftToPay) {
        this.amountLeftToPay = amountLeftToPay;
    }

    // Getter for loanTermLeft
    public int getLoanTermLeft() {
        return loanTermLeft;
    }

    // Setter for loanTermLeft
    public void setLoanTermLeft(int loanTermLeft) {
        this.loanTermLeft = loanTermLeft;
    }
}
