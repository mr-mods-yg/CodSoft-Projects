package com.yashgarg.codsoft;
import java.util.Scanner;

public class ATM_Interface {
    private BankAccount user;
    private int currentBalance;

    // deposits the balance to the user's bank account
    void deposit(int amount){
        if(amount<0) {
            System.out.println("ERROR : You cannot deposit negative balance");
            return; // can't deposit negative balance
        }
        // updates the balance
        currentBalance = user.updateBalance(currentBalance+amount); // user's account balance
        System.out.printf("DEPOSIT : + %d INR\n",amount);

    }
    // withdraws the amount from the user's bank account
    void withdraw(int amount){
        if(amount<0) {
            System.out.println("ERROR : You cannot withdraw negative balance");
            return; // can't withdraw negative balance
        }
        if(amount>currentBalance){
            System.out.println("BALANCE EXCEEDED!");
        }
        else{
            // updates the balance
            currentBalance = user.updateBalance(currentBalance-amount); // user's account balance
            System.out.printf("WITHDRAW : - %d INR\n",amount);
        }
    }
    // checks the bank's account balance of the user
    void checkBalance(){
        System.out.println("CURRENT BALANCE : " + currentBalance + " INR/-");
    }
    // shows all the commands of ATM Interface
    void showMenu(){
        System.out.println("----------------------------");
        System.out.printf("| %-25s|\n","WELCOME TO THE XYZ BANK");
        System.out.printf("| %-25s|\n","1. Deposit");
        System.out.printf("| %-25s|\n","2. Withdraw");
        System.out.printf("| %-25s|\n","3. Check Balance");
        System.out.printf("| %-25s|\n","4. Exit");
        System.out.println("----------------------------");
    }
    public static void main(String[] args){
        boolean wantToClose = false; // will check if user want to close the atm interface or not
        ATM_Interface bank = new ATM_Interface();
        bank.user = new BankAccount();
        bank.currentBalance = bank.user.getBalance();
        bank.showMenu(); // shows the available commands of the bank interface
        while (!wantToClose){
            System.out.print("CHOOSE THE OPTION : ");
            Scanner sc = new Scanner(System.in);
            int x = sc.nextInt();
            int amount; // temporary variable to store the user's input amount
            switch (x) {
                case 1 -> {
                    System.out.print("Enter Amount to be deposited :");
                    amount = sc.nextInt();
                    bank.deposit(amount);
                }
                case 2 -> {
                    System.out.print("Enter Amount to be withdrawn :");
                    amount = sc.nextInt();
                    bank.withdraw(amount);
                }
                case 3 -> bank.checkBalance();
                case 4 -> {
                    wantToClose = true; // user want to close the atm interface
                    System.out.println("---------------------------------");
                    System.out.printf("| %-30s |\n", " THANKS FOR VISITING XYZ BANK");
                    System.out.println("---------------------------------");
                }
            }
            System.out.println(); // print empty line
        }
    }

}
class BankAccount{
    private int balance;
    public int updateBalance(int balance) {
        // updates the balance and returns the new balance
        this.balance = balance;
        return balance;
    }
    public int getBalance() {
        // gets the balance and returns it
        return balance;
    }
}
