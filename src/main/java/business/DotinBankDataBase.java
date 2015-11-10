package business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class DotinBankDataBase {
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DATABASE_URL = "jdbc:mysql://localhost/DOTINBANK";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            /*creating DataBase*/
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection("jdbc:mysql://localhost", USERNAME, PASSWORD);
            System.out.println("Creating database...");
            statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE STUDENTS");
            System.out.println("Database created successfully...");

            /*creating Tables*/
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            statement.execute("CREATE TABLE CORPORATECUSTOMER " +
                    "(corporationName VARCHAR(255), " +
                    " financialCode VARCHAR(255) not null, " +
                    " registerDate DATE, " +
                    " customerNumber INTEGER not NULL, " +
                    " PRIMARY KEY ( customerNumber ))");
            System.out.println("corporateCustomer Table created successfully...");

            statement.execute("CREATE TABLE ACTUALCUSTOMER " +
                    "(firstName VARCHAR(255), " +
                    " lastName VARCHAR(255), " +
                    " fatherName VARCHAR(255), " +
                    " nationalCode VARCHAR(255), not null" +
                    " birthdayDate DATE, " +
                    " customerNumber INTEGER not NULL, " +
                    " PRIMARY KEY ( customerNumber ))");
            System.out.println("actualCustomer Table created successfully...");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
