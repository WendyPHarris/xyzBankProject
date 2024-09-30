package BANK.Interfaces;

import BANK.Customer;

public interface CheckPrinter {
    // Method to check a customer's eligibility
    abstract boolean checkEligibility(Customer customer);

    // Method to print customer's details
    abstract void printCustomerDetails(String CustomerID);
}

