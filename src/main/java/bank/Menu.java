package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import bank.exceptions.AmountException;

public class Menu {

  private Scanner scanner;

  private Customer authenticateUser() {
    System.out.println("Username: ");
    String username = scanner.next();

    System.out.println("Password: ");
    String password = scanner.next();

    Customer customer = null;

    try {
      customer = Authenticator.login(username, password);
    } catch (LoginException e) {
      System.out.println("Error: " + e.getMessage());
    }

    return customer;
  }

  private void showMenu(Customer customer, Account account) {
    int selection = 0;

    while(selection !=  4 && customer.isAuthenticated()) {
      System.out.println("=====================================");
      System.out.println("Select Option");
      System.out.println("1: Deposit");
      System.out.println("2: Withdraw");
      System.out.println("3: Check Balance");
      System.out.println("4: Exit");
      System.out.println("======================================");

      selection = scanner.nextInt();

      double amount = 0;

      switch(selection) {
        case 1:
          System.out.println("Deposit Amount: ");
          amount = scanner.nextDouble();
          try {
            account.deposit(amount);
          } catch (AmountException e) {
            System.out.println(e.getMessage());
          }
          break;
        case 2:
          System.out.println("Withdraw Amount: ");
          amount = scanner.nextDouble();
          try {
            account.withdraw(amount);
          } catch (AmountException e) {
            System.out.println(e.getMessage());
          }
          break;
        case 3:
          System.out.println("Balance: " + account.getBalance());
          break;
        case 4:
          Authenticator.logout(customer);
          System.out.println("Bye");
          break;
        default:
          System.out.println("Invalid Option");
          break;
      }
    }
  }

  public static void main(String[] args) {
    System.out.println("Welcome");

    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);

    Customer customer = menu.authenticateUser();

    if (customer != null) {
      Account account = DataSource.getAccount(customer.getAccountId());
      menu.showMenu(customer, account);
    }

    menu.scanner.close();
  }
}
