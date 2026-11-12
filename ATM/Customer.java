public class Customer extends User{
    private CheckingAccount checking;
    private SavingsAccount savings;

    public Customer(){
        this("Alice", "0000");
    }

    public Customer(String userName, String PIN){
        setUserName(userName);
        setPIN(PIN);
        checking = new CheckingAccount();
        savings = new SavingsAccount();
    }

    public static void main(String[] args){
        Customer customer = new Customer();

        if(customer.login()){
            System.out.println("Login Successful");
            customer.start();
        }else{
            System.out.println("Incorrect user name or PIN.");
        }
    }

    public String menu(){
        System.out.println("\nCustomer Menu");
        System.out.println("0) Exit");
        System.out.println("1) Manage Checking Account");
        System.out.println("2) Manage Savings Account");
        System.out.println("3) Change PIN");
        System.out.print("Action (0-3): ");
        return CheckingAccount.input.nextLine();
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
                    System.out.println("\nChecking Account");
                    checking.start();
                    break;
                case "2":
                    System.out.println("\nSavings Account");
                    savings.start();
                    break;
                case "3":
                    changePin();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void changePin(){
        System.out.print("Enter new PIN: ");
        setPIN(CheckingAccount.input.nextLine());
        System.out.println("PIN changed.");
    }

    public String getReport(){
        return "Customer: " + getUserName()
            + "\nChecking: " + checking.getBalanceString()
            + "\nSavings: " + savings.getBalanceString();
    }
    
    public CheckingAccount getChecking(){
	return checking;
    }

    public SavingsAccount getSavings(){
	return savings;
    }
}
