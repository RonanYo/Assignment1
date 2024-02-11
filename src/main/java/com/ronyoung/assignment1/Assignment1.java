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
        
        // Get user's rate.     
        System.out.print("What is your rate? ");
        double rate = sc.nextDouble() / 12;
        
        // Get users loan length in months.
        System.out.print("How many months? ");
        int numberOfPeriods = sc.nextInt();
        
        // Get user's loan amount.
        System.out.print("How much is your loan? ");
        double presentValue = sc.nextDouble();
        
        // Print out user's gathered information
        System.out.println("Annual interest rate: " + rate * 12);
        System.out.println("Term in months: " + numberOfPeriods);
        System.out.println("Loan amount: " + presentValue);
        
        /* 
        Loop to call methods based on user's menu choice and then
        print their results.
        */
        do {
            choice = menu();
            System.out.println("Choice = " + choice);
            switch (choice) {
                /* 
                If 'A' is selected, calls loanCalculator method and finds result
                using user's gathered information.
                */
                case 'A' -> {
                    result = loanCalculator(presentValue, rate, numberOfPeriods);
                    System.out.println("Your monthly payment should be: $" + result);
                }
                /*
                If 'B' is selected, calls valueOfSavingsCalculator method and 
                finds result using user's gathered information.
                */
                case 'B' -> {
                    result = valueOfSavingsCalculator(rate, numberOfPeriods);
                    System.out.println("Your savings are: $" + (result * -1));
                }
                /*
                If 'C' is selected, calls savingsGoalCalculator method and finds
                result using user's gathered information.
                */
                case 'C' -> {
                    result = savingsGoalCalculator(rate, numberOfPeriods);
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
        System.out.println("B. Future Value of Sabings Calculator");
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
    
    /* 
    Finds the monthly payment based on the user's inputted loan amount, rate,
    and term in months.
    */
    private double loanCalculator(double presentValue, double rate, 
            int numberOfPeriods) {
        
        //Equation to find monthly payment.
        var monthlyPayment = presentValue * rate / 
                (1 - Math.pow(1 + rate, -numberOfPeriods));
        
        return monthlyPayment;
    }
    
    /* 
    Finds the amount of money user will save with the rate, term in months,
    and monthly payment.
    */
    private double valueOfSavingsCalculator(double rate,
            double numberOfPeriods) {
        
        System.out.print("What is your monthly payment? ");
        double monthlyPayment = sc.nextDouble();
        
        //Equation to find future value of savings.
        var futureValue = monthlyPayment * (1 - Math.pow(1 + 
                rate, numberOfPeriods) / 
                rate);
        
        return futureValue;
    }
    
    /* 
    Finds how much the user should set aside each month to meet a 
    particular savings goal.
    */
    private double savingsGoalCalculator(double rate, double numberOfPeriods) {
        
        System.out.print("What is your savings goal? ");
        double savingsGoal = sc.nextDouble();
        
        // Equation to find the amount saved per month.
        var savePerMonth = savingsGoal * 
                (rate / (1- Math.pow(1 + rate, numberOfPeriods)));
        
        return savePerMonth;
        
    }
    
    // Main function method that calls other methods.
    public static void main(String[] args) {
        
    // Call the constructor using the perform method.
    new Assignment1().perform();

        
    }
         
}
