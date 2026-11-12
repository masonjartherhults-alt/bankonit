import java.io.*;

public class Bank implements HasMenu{
    private Admin admin;
    private CustomerList customers;

    public static void main(String[] args){
        new Bank();
    }

    public Bank(){
        admin = new Admin();
        customers = new CustomerList();

        loadCustomers();
        start();
        saveCustomers();
    }

    public String menu(){
        System.out.println("\nBank Menu");
        System.out.println("0) Exit system");
        System.out.println("1) Login as admin");
        System.out.println("2) Login as customer");
        System.out.print("\nAction: ");
        return CheckingAccount.input.nextLine();
    }

    public void start(){
        boolean keepGoing = true;

        while(keepGoing){
            String choice = menu();

            switch(choice){
                case "0":
                    keepGoing = false;
                    break;
                case "1":
                    System.out.println("\nAdmin login");

                    if(admin.login()){
                        startAdmin();
                    }else{
                        System.out.println("Incorrect username or PIN.");
                    }
                    break;
                case "2":
                    loginCustomer();
                    break;
                default:
                    System.out.println("Choose 0, 1, or 2.");
            }
        }
    }

    public void startAdmin(){
        boolean keepGoing = true;

        while(keepGoing){
            String choice = admin.menu();

            switch(choice){
                case "0":
                    keepGoing = false;
                    break;
                case "1":
                    fullCustomerReport();
                    break;
                case "2":
                    addUser();
                    break;
                case "3":
                    applyInterest();
                    break;
                default:
                    System.out.println("Choose 0, 1, 2, or 3.");
            }
        }
    }

    public void loginCustomer(){
        System.out.println("\nCustomer login");
        System.out.print("User name: ");
        String userName = CheckingAccount.input.nextLine();

        System.out.print("PIN: ");
        String pin = CheckingAccount.input.nextLine();

        Customer currentCustomer = null;

        for(Customer customer : customers){
            if(customer.login(userName, pin)){
                currentCustomer = customer;
                break;
            }
        }

        if(currentCustomer == null){
            System.out.println("Incorrect username or PIN.");
        }else{
            currentCustomer.start();
        }
    }

    public void fullCustomerReport(){
        System.out.println("\nFull customer report");

        for(Customer customer : customers){
            System.out.println(customer.getReport());
        }
    }

    public void addUser(){
        System.out.println("\nAdd User");
        System.out.print("Name: ");
        String userName = CheckingAccount.input.nextLine().trim();

        if(userName.isEmpty()){
            System.out.println("Name cannot be blank.");
            return;
        }

        for(Customer customer : customers){
            if(customer.getUserName().equalsIgnoreCase(userName)){
                System.out.println("That username already exists.");
                return;
            }
        }

        System.out.print("PIN: ");
        String pin = CheckingAccount.input.nextLine();

        if(!pin.matches("[0-9]{4}")){
            System.out.println("PIN must contain four numbers.");
            return;
        }

        Customer customer = new Customer(userName, pin);
        customer.getSavings().setInterestRate(0.05);
        customers.add(customer);

        System.out.println("User added.");
    }

    public void applyInterest(){
        System.out.println("\nApply interest");

        for(Customer customer : customers){
            customer.getSavings().calcInterest();
        }
    }

    public void loadSampleCustomers(){
        customers.clear();

        Customer alice = new Customer("Alice", "0000");
        alice.getChecking().setBalance(1000);
        alice.getSavings().setBalance(1000);
        alice.getSavings().setInterestRate(0.05);

        Customer bob = new Customer("Bob", "1111");
        bob.getSavings().setInterestRate(0.05);

        Customer cindy = new Customer("Cindy", "2222");
        cindy.getSavings().setInterestRate(0.05);

        customers.add(alice);
        customers.add(bob);
        customers.add(cindy);
    }

    public void saveCustomers(){
        try(ObjectOutputStream output =
                new ObjectOutputStream(
                    new FileOutputStream("customers.dat"))){

            output.writeObject(customers);
            System.out.println("Customer data saved.");

        }catch(IOException error){
            System.out.println("Could not save customer data.");
            System.out.println(error.getMessage());
        }
    }

    public void loadCustomers(){
        File file = new File("customers.dat");

        if(!file.exists()){
            loadSampleCustomers();
            return;
        }

        try(ObjectInputStream input =
                new ObjectInputStream(
                    new FileInputStream(file))){

            customers = (CustomerList)input.readObject();

        }catch(IOException | ClassNotFoundException |
                ClassCastException error){

            throw new IllegalStateException(
                "Could not load customers.dat. Existing data was not replaced.",
                error
            );
        }
    }
}
