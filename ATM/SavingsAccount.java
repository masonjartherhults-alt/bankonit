public class SavingsAccount extends CheckingAccount{
    private double interestRate;

    public SavingsAccount(){
        super();
        interestRate = 0.02;
    }

    public static void main(String[] args){
        SavingsAccount account = new SavingsAccount();
        account.start();
    }

    public void calcInterest(){
        double interest = getBalance() * interestRate;
        setBalance(getBalance() + interest);
    }

    public void setInterestRate(double interestRate){
        this.interestRate = interestRate;
    }

    public double getInterestRate(){
        return interestRate;
    }
}
