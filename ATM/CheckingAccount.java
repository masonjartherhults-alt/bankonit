import java.util.Scanner;
import java.io.Serializable;

public class CheckingAccount implements HasMenu, Serializable{
    static final Scanner input = new Scanner(System.in);
    private double balance;

    public CheckingAccount(){
        balance = 0;
    }

    public CheckingAccount(double balance){
        this.balance = balance;
    }

    public static void main(String[] args){
        CheckingAccount account = new CheckingAccount();
        account.start();
    }

    public String menu(){
        System.out.println("\nAccount Menu");
        System.out.println("0) Quit");
        System.out.println("1) Check balance");
        System.out.println("2) Make a deposit");
        System.out.println("3) Make a withdrawal");
        System.out.print("Please enter 0-3: ");
        return input.nextLine();
    }

    public void start(){
        boolean running = true;

        while(running){
            String choice = menu();

            switch(choice){
                case "0":
                    running = false;
                    break;
                case "1":
                    checkBalance();
                    break;
                case "2":
                    makeDeposit();
                    break;
                case "3":
                    makeWithdrawal();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getBalanceString(){
        return String.format("$%.2f", balance);
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public void checkBalance(){
        System.out.println("Current balance: " + getBalanceString());
    }

    private double getDouble(){
        try{
            double amount = Double.parseDouble(input.nextLine());
            return Double.isFinite(amount) ? amount : 0;
        }catch(NumberFormatException e){
            return 0;
        }
    }

    public void makeDeposit(){
        System.out.print("How much to deposit? ");
        double amount = getDouble();

        if(amount > 0){
            balance += amount;
            System.out.println("New balance: " + getBalanceString());
        }else{
            System.out.println("Invalid amount.");
        }
    }

    public void makeWithdrawal(){
        System.out.print("How much to withdraw? ");
        double amount = getDouble();

        if(amount <= 0){
            System.out.println("Invalid amount.");
        }else if(amount > balance){
            System.out.println("Not enough money.");
        }else{
            balance -= amount;
            System.out.println("New balance: " + getBalanceString());
        }
    }
}
