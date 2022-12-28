package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

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

  }

  public static void main(String[] args) {
    System.out.println("Welcome");

    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);

    Customer customer = menu.authenticateUser();

    if (customer != nul) {
      Account account = DataSource.getAccount(customer.getAccountId());
      menu.showMenu(customer, account);
    }

    menu.scanner.close();
  }
}
