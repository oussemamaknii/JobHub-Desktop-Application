package Utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connexion {

  private String url = "jdbc:mysql://127.0.0.1:3306/jobapplication";
  private String username = "root";
  private String password = "";

  private Connection connection;
  private static Connexion instance;

  private Connexion() {
    try {
      connection = DriverManager.getConnection(url, username, password);
      System.out.println("connection etablie");
    } catch (SQLException e) {
      Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, e);
    }
  }

  public static Connexion getInstance() {
    if (instance == null) {
      instance = new Connexion();
    }
    return instance;
  }

  public Connection getConnection() {
    return connection;
  }
}
