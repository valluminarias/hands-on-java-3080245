package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {
  public static Connection connect() {
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;

    try {
       connection = DriverManager.getConnection(db_file);

       System.out.println("Connected");
    } catch(SQLException e) {
      e.printStackTrace();
    }

    return connection;
  }

  public static Customer getCustomer(String username) {
    String sql = "select * from Customers where username = ?";
    Customer customer = null;

    try {
      Connection connection = connect();
      PreparedStatement statement = connection.prepareStatement(sql);

      statement.setString(1, username);
      try(ResultSet result = statement.executeQuery()) {
        customer = new Customer(
          result.getInt("id"),
          result.getString("name"),
          result.getString("username"),
          result.getString("password"),
          result.getInt("account_id")
        );
      }
    } catch(SQLException e) {
      e.printStackTrace();
    }

    return customer;
  }

  public static Account getAccount(int account_id) {
    String sql = "select * from Accounts where id = ?";
    Account account = null;

    try {
      Connection connection = connect();
      PreparedStatement statement = connection.prepareStatement(sql);

      statement.setInt(1, account_id);
      try(ResultSet result = statement.executeQuery()) {
        account = new Account(
          result.getInt("id"),
          result.getString("type"),
          result.getDouble("balance")
        );
      }
    } catch(SQLException e) {
      e.printStackTrace();
    }

    return account;
  }

  public static void updateAccountBalance(int accountId, double balance) {
    String sql = "update accounts set balance = ? where id = ?";

    try {
      Connection connection = connect();

      PreparedStatement statement = connection.prepareStatement(sql);

      statement.setDouble(1, balance);
      statement.setInt(2, accountId);

      statement.executeUpdate();
    } catch(SQLException e) {
      e.printStackTrace();
    }
  }
}
