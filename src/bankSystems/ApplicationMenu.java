package bankSystems;

import commands.CommandHistory;

import java.util.Scanner;

public class ApplicationMenu {
    CommandHistory history = new CommandHistory();
    public ApplicationMenu() {
    }
    private Account acc;
    public void auth() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: {
                System.out.print("Enter your username: ");
                String username = new Scanner(System.in).next();
                System.out.print("Enter your password: ");
                String password = new Scanner(System.in).next();
                System.out.print("Enter your email: ");
                String email = new Scanner(System.in).next();
                System.out.print("Enter your phone number: ");
                String phone = new Scanner(System.in).next();
                System.out.print("Enter your address: ");
                String address = new Scanner(System.in).next();
                System.out.print("Enter your city: ");
                String city = new Scanner(System.in).next();
                System.out.print("Enter your state: ");
                String state = new Scanner(System.in).next();
                System.out.print("Enter your zip code: ");
                String zipCode = new Scanner(System.in).next();
                System.out.print("Enter your country: ");
                String country = new Scanner(System.in).next();
                this.acc = new Account(username, password);
                acc.createAccount(username, username, email, phone, address, city, state, zipCode, country);
                System.out.println("Account created successfully");
                this.acc = null;
                auth();
                break;
            }
            case 2: {
                System.out.println("Enter your username:");
                String username = new Scanner(System.in).next();
                System.out.println("Enter your password:");
                String password = new Scanner(System.in).next();
                this.acc = new Account(username, password);
                if (this.acc.login()) {
                    System.out.println("Authorization successful");
                    main();
                } else {
                    System.out.println("Authorization failed");
                    auth();
                }
                break;
            }
            case 3: {
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            }
            default:
                System.out.println("Invalid choice");
                auth();
                break;
        }
    }
    public void main() {
        System.out.println("Application Menu");
        System.out.println("1. Create a new bank");
        System.out.println("2. Deposits");
        System.out.println("3. Account information");
        System.out.println("4. Exit");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choice your option - ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Create a new bank");
                System.out.print("Enter bank name: ");
                String name = new Scanner(System.in).next();
                System.out.print("Enter bank address: ");
                String address = new Scanner(System.in).next();
                System.out.print("Enter bank city: ");
                String city = new Scanner(System.in).next();
                System.out.print("Enter bank state: ");
                String state = new Scanner(System.in).next();
                System.out.print("Enter bank zip code: ");
                String zipCode = new Scanner(System.in).next();
                System.out.print("Enter bank country: ");
                String country = new Scanner(System.in).next();
                System.out.print("Enter bank phone: ");
                String phone = new Scanner(System.in).next();
                System.out.print("Enter bank email: ");
                String email = new Scanner(System.in).next();
                System.out.print("Enter bank website: ");
                String website = new Scanner(System.in).next();
                Bank bank = new Bank();
                bank.createBank(name, address, city, state, zipCode, country, phone, email, website);
                main();
                break;
            case 2:
                System.out.println("1. Create a new deposit");
                System.out.println("2. Deposit information");
                System.out.println("3. Exit");
                int choice2 = scanner.nextInt();
                switch (choice2) {
                    case 1:
                        System.out.println("Create a new deposit");
                        System.out.print("Enter deposit amount: ");
                        String amount = new Scanner(System.in).next();
                        System.out.print("Enter deposit date: ");
                        String date = new Scanner(System.in).next();
                        System.out.print("Enter deposit time: ");
                        String time = new Scanner(System.in).next();
                        System.out.print("Enter deposit long term: ");
                        String longTerm = new Scanner(System.in).next();
                        System.out.print("Enter bank id: ");
                        String bankId = new Scanner(System.in).next();
                        Deposit deposit = new Deposit();
                        deposit.createDeposit(amount, date, time, longTerm, bankId);
                        main();
                        break;
                    case 2:
                        System.out.println("Deposits information");
                        ControlDeposit controlDeposit = new ControlDeposit();
                        controlDeposit.getAllDeposits();
                        break;
                    case 3:
                        System.out.println("Exit");
                        return;
                }
                break;
            case 3:
                System.out.println("Account information");
                System.out.println("Username: " + this.acc.getUsername());
                System.out.println("Balance: " + this.acc.getBalance());
                System.out.println("Deposits: " + this.acc.getDeposits());
                System.out.println("1. Add deposit");
                System.out.println("2. Remove deposit");
                System.out.println("3. Exit");
                int choice3 = scanner.nextInt();
                switch (choice3) {
                    case 1:
                        System.out.println("Add deposit");
                        System.out.print("Enter deposit id: ");
                        String depositId = new Scanner(System.in).next();
                        this.acc.addDeposit(depositId);
                        main();
                        break;
                    case 2:
                        System.out.println("Remove deposit");
                        System.out.print("Enter deposit id: ");
                        String depositId2 = new Scanner(System.in).next();
                        this.acc.removeDeposit(depositId2);
                        main();
                        break;
                    case 3:
                        System.out.println("Exit");
                        return;
                }
                break;
            case 4:
                System.out.println("Exit");
                System.exit(0);
                return;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }
}
