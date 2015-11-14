package persistence.crud;

import business.RealCustomer;
import persistence.DotinBankDataBase;
import presentation.RegisterRealCustomerServlet;

import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class RealCustomerActions {
    private Connection connection;
    private PrintWriter out;
    private PreparedStatement preparedStatement;
    private RegisterRealCustomerServlet registerRealCustomerServlet;


    public RealCustomerActions() throws SQLException, ClassNotFoundException {
        Class.forName(DotinBankDataBase.JDBC_DRIVER);
        connection = DriverManager.getConnection(DotinBankDataBase.DATABASE_URL, DotinBankDataBase.USERNAME, DotinBankDataBase.PASSWORD);
        registerRealCustomerServlet = new RegisterRealCustomerServlet();
    }

    public void insertToDatabase(RealCustomer realCustomer) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO customer"
                    + "(firstName, lastName, fatherName, nationalCode, birthDate) VALUES"
                    + "(?,?,?,?,?)");
            preparedStatement.setString(1, realCustomer.getFirstName());
            preparedStatement.setString(2, realCustomer.getLastName());
            preparedStatement.setString(3, realCustomer.getFatherName());
            preparedStatement.setString(4, realCustomer.getNationalCode());
            preparedStatement.setString(5, realCustomer.getBirthDate());
            System.out.println(preparedStatement);

            if (preparedStatement.executeUpdate() > 0) {
                //registerRealCustomerServlet.printSuccessReport();
                System.out.println("success");
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("!!!!!!!insertion error!!!!!");
        } finally {
            out.close();
        }
    }

    public void searchDatabase(String firstName, String lastName, String nationalCode, Integer customerNumber) {
        String where = "";
        if (firstName != null) {
            where += (where == "") ? " WHERE " : " AND "
                    + "firstName = " + firstName;
        }
        if (lastName != null) {
            where += (where == "") ? " WHERE " : " AND "
                    + "lastName = " + lastName;
        }
        if (nationalCode != null) {
            where += (where == "") ? " WHERE " : " AND "
                    + "nationalCode = " + nationalCode;
        }
        if (customerNumber != null) {
            where += (where == "") ? " WHERE " : " AND "
                    + "customerNumber = " + customerNumber.toString();
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer " + where);
            out.print("<html><body>");
            out.print("<caption>Result:</caption>");
            out.print("<table width=50% border=1>");
            out.print("<caption>Result:</caption>");

            ResultSet resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaDataResult = resultSet.getMetaData();
            out.print("<tr>");
            for (int i = 1; i <= metaDataResult.getColumnCount(); i++) {
                out.print("<th>" + metaDataResult.getColumnName(i) + "</th>");
            }
            out.print("</tr>");

            while (resultSet.next()) {
                out.print("<tr><td>" + resultSet.getString(1)
                        + "</td><td>" + resultSet.getString(2)
                        + "</td ><td > " + resultSet.getString(3)
                        + " </td ><td > " + resultSet.getString(4)
                        + " </td ><td > " + resultSet.getDate(5)
                        + " </td ><td > " + resultSet.getInt(6)
                        + " </td ></tr > ");
            }
            out.print("</table>");
            out.print("</body></html>");
        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            out.close();
        }
    }

    public void deleteFromDatabase(Integer customerNumber) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE customer WHERE customerNumber = ?");
            preparedStatement.setInt(1, customerNumber);
            if (preparedStatement.executeUpdate() > 0) {
                out.print("successfully deleted...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDatabase(String firstName, String lastName, String nationalCode, Integer customerNumber) {
        String where = "";
        if (firstName != null) {
            where += (where == "") ? " WHERE " : " AND "
                    + "firstName = " + firstName;
        }
        if (lastName != null) {
            where += (where == "") ? " WHERE " : " AND "
                    + "lastName = " + lastName;
        }
        if (nationalCode != null) {
            where += (where == "") ? " WHERE " : " AND "
                    + "nationalCode = " + nationalCode;
        }
        if (customerNumber != null) {
            where += (where == "") ? " WHERE " : " AND "
                    + "customerNumber = " + customerNumber.toString();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customer " + where);
            if (preparedStatement.executeUpdate() > 0) {
                out.print("successfully updated...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
