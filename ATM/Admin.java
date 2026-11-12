public class Admin extends User{
    private static final long serialVersionUID = 1L;

    public Admin(){
	setUserName("admin");
	setPIN("0000");

    }

    public String menu(){
	System.out.println("\nAdmin Menu");
	System.out.println("0) Exit this menu");
	System.out.println("1) Full customer report");
        System.out.println("2) Add user");
        System.out.println("3) Apply interest to savings accounts");
        System.out.print("\nAction: ");
        return CheckingAccount.input.nextLine();
    }

    public void start(){
    }

    public String getReport(){
        return "Admin: " + getUserName();
    }
}
