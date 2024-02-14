package com.ronyoung.assignment1;

// Allows the use of Scanner.
import java.util.Scanner;

public class Assignment1 {
    
    // Initial scanner to call in constructor.
    private final Scanner sc;
        
    // Constructor
    public Assignment1() {
        sc = new Scanner(System.in);
    }
    // Method that calls all other methods.
    public void perform() {
        
        // Declared variables to be used in do-while loop below.
        char choice;
        double result;
            
        /* 
        Loop to call methods based on user's menu choice and then
        print their results.
        */
        do {
            choice = menu();
            System.out.println("Choice = " + choice);
            switch (choice) {
                
                //If 'A' is selected, calls loanCalculator method.
                case 'A' -> {
                    result = loanCalculator();
                    System.out.println("Your monthly payment should be: $" + result);
                }
                
                //If 'B' is selected, calls valueOfSavingsCalculator method.
                case 'B' -> {
                    result = valueOfSavingsCalculator();
                    System.out.println("Your savings are: $" + (result * -1));
                }
                
                //If 'C' is selected, calls savingsGoalCalculator method
                case 'C' -> {
                    result = savingsGoalCalculator();
                    System.out.println("You should set aside $" + (result * -1) + " each month.");
                }
                /*
                If 'D' is selected, prints "Exiting" then loop is ended.
                */
                case 'D' ->
                    System.out.println("Exiting");
                default ->
                    // Shouldn't show up but if seen something is wrong.
                    System.out.println("I should never see this");
            }
        } while (choice != 'D');
        
        
    }
    
    // Just displays text for the menu.
    private void displayMenuText() {
        // Request for input.
        System.out.println("Please enter the letter for one of the following:");

        // Menu options.
        System.out.println("A. Loan Calculator");
        System.out.println("B. Future Value of Savings Calculator");
        System.out.println("C. Savings Goal Calculator");
        System.out.println("D. Exit");
    }
    
    // Resquests and handles menu options input from the user.
    private char menu() {
        
        char choice;

        // Loop to get desired letter input from user.
        do {
            displayMenuText();
            /* 
            Checks if the user input is an allowed vlaue, 
            if not informs them it's not and askes for input again. 
            */
            if (sc.hasNext("[a-dA-D]")) {
                choice = sc.next().toUpperCase().charAt(0);
            } else {
                System.out.println("Invalid choice, please select again");
                choice = 'z';
            }
            sc.nextLine();
        } while (choice == 'z');
        return choice;
    }
    
    // Method used to get user's rate.     
    private double userRate() {
        
        System.out.print("Annual Interest Rate: ");
        double rate = sc.nextDouble() / 12;
        
        return rate;
    }
    
    // Method to get users loan length in months.
    private int userPeriods() {
        
        
        System.out.print("Term in months: ");
        int numberOfPeriods = sc.nextInt();
        
        return numberOfPeriods;
    }
    
    // Method to get user's loan amount.
    private double userLoanValue() {
        
        
        System.out.print("Loan Amount: ");
        double presentValue = sc.nextDouble();
        
        return presentValue;
    }
    // Method to get user's current payment. 
    private double userPaymentAmount() {
        
        // Method to get user's current payment. 
        System.out.print("Monthly Payment to Savings: ");
        double payment = sc.nextDouble();
        
        return payment;
    }
    
    // Method to get user's desired savings goal.
    private double userSavingsGoal() { 
        
        // Get user's desired savings goal.
        System.out.print("Savings Goal: ");
        double savingsGoal = sc.nextDouble();
        
        return savingsGoal;
    }
    
    /* 
    Finds the monthly payment based on the user's inputted loan amount, rate,
    and term in months.
    */
    private double loanCalculator() {
        
        // Calls to methods to get user information.
        double rate = userRate();
        int numberOfPeriods = userPeriods();
        double presentValue = userLoanValue();
        
        //Equation to find monthly payment.
        var monthlyPayment = presentValue * rate / 
                (1 - Math.pow(1 + rate, -numberOfPeriods));
        
        // Returns the monthlyPayment rounded to two decimal points.
        return Math.round(monthlyPayment * 100.0) / 100.0;
    }
    
    /* 
    Finds the amount of money user will save with the rate, term in months,
    and monthly payment.
    */
    private double valueOfSavingsCalculator() {
        
        // Calls to methods to get user information.
        double rate = userRate();
        int numberOfPeriods = userPeriods();
        double payment = userPaymentAmount();
        
        
        //Equation to find future value of savings.
        var futureValue = payment * ((1 - Math.pow(1 + rate, numberOfPeriods))
                / rate);
                
        // Returns the futureValue rounded to two decimal points.
        return Math.round(futureValue * 100.0) / 100.0;
    }
    
    /* 
    Finds how much the user should set aside each month to meet a 
    particular savings goal.
    */
    private double savingsGoalCalculator() {
        
        // Calls to methods to get user information.
        double rate = userRate();
        int numberOfPeriods = userPeriods();
        double savingsGoal = userSavingsGoal();
        
        // Equation to find the amount saved per month.
        var savePerMonth = savingsGoal * 
                (rate / (1- Math.pow(1 + rate, numberOfPeriods)));
        
        // Returns the savePerMonth rounded to two decimal points.
        return Math.round(savePerMonth * 100.0) / 100.0;
        
    }
    
    // Main function method that calls other methods.
    public static void main(String[] args) {
        
    // Call the constructor using the perform method.
    new Assignment1().perform();

        
    }
         
}
