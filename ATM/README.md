# bankonit
Bank/ATM tool

The bank lets an admin manage customers and lets customers use
their checking and savings accounts. The customer information is saved
in customers.dat when the program exits.

Algorithm

Admin
- Set the username to admin and PIN to 0000.
- Display the admin menu and return the selection.
- Leave start empty because Bank handles admin actions.
- Return the admin name for the report.

CustomerList
- Extend ArrayList<Customer> to store customer objects.

Bank constructor
- Create the admin and customer list.
- Load saved customers, or sample customers on the first run.
- Start the bank menu.
- Save customers when the user exits.

Bank menu and start
- Display exit, admin login, and customer login.
- Read the selection.
- For admin login, check credentials and open the admin menu.
- For customer login, run loginCustomer.
- Repeat until exit is selected.

Admin menu
- Display the admin choices.
- Run a customer report, add a user, or apply interest.
- Repeat until the admin exits the menu.

Customer login
- Ask for a username and PIN.
- Check each customer for matching credentials.
- Start the matching customer's menu.
- Display an error if no customer matches.

Full customer report
- Go through the customer list.
- Print each customer's report.

Add user
- Ask for a username.
- Reject blank or duplicate usernames.
- Ask for a four-digit PIN.
- Create the customer and add them to the list.

Apply interest
- Go through the customer list.
- Call calcInterest on each savings account.

Sample customers
- Create Alice, Bob, and Cindy.
- Give Alice $1000 in checking and $1000 in savings.
- Set the savings interest rate to 5 percent.

Save customers
- Open an object output stream.
- Write the customer list to customers.dat.
- Close the stream.
- Display an error if saving fails.

Load customers
- Check whether customers.dat exists.
- If it does not exist, create sample customers.
- Otherwise, read the saved customer list.
- Stop with an error if the existing file cannot be loaded.

Extra feature
- Reject duplicate usernames, blank names, and invalid new PINs.

Run
make run

Sample logins
Admin: admin / 0000
Alice: Alice / 0000
Bob: Bob / 1111
Cindy: Cindy / 2222
