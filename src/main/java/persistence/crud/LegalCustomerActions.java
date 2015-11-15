package persistence.crud;

import business.LegalCustomer;
import persistence.DotinBankDataBase;
import presentation.RegisterLegalCustomerServlet;

import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class LegalCustomerActions {
    private Connection connection;
    private PrintWriter out;
    private PreparedStatement preparedStatement;
    RegisterLegalCustomerServlet registerLegalCustomerServlet;


    public LegalCustomerActions() throws SQLException, ClassNotFoundException {
        Class.forName(DotinBankDataBase.JDBC_DRIVER);
        connection = DriverManager.getConnection(DotinBankDataBase.DATABASE_URL, DotinBankDataBase.USERNAME, DotinBankDataBase.PASSWORD);
    }

    public void insertToDatabase(LegalCustomer legalCustomer) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO customer"
                    + "(corporationName, financialCode, registerDate) VALUES"
                    + "(?,?,?)");
            preparedStatement.setString(1, legalCustomer.getCorporationName());
            preparedStatement.setString(2, legalCustomer.getFinancialCode());
            preparedStatement.setString(3, legalCustomer.getRegisterDate());

            if (preparedStatement.executeUpdate() > 0) {
                System.out.println("success! legal");
                //registerLegalCustomerServlet.printSuccessReport();
            }
        } catch (Exception e2) {
            //e2.printStackTrace();
            System.out.println("insertion error : legal");
        } finally {
            out.close();
        }
    }

    public void searchDatabase(String corporationName, String financialCode, Integer customerNumber) {
        String where = "";
        if (corporationName != null) {
            if (where.equals("")) {
                where += " WHERE " + "corporationName = '" + corporationName + "'";
            } else where += " AND " + "corporationName = '" + corporationName + "'";
        }
        if (financialCode != null) {
            if (where.equals("")) {
                where += " WHERE " + "financialCode = '" + financialCode + "'";
            } else where += " AND " + "financialCode = '" + financialCode + "'";
        }
        if (customerNumber != null) {
            if (where.equals("")) {
                where += " WHERE " + "customerNumber = '" + customerNumber + "'";
            } else where += " AND " + "customerNumber = '" + customerNumber + "'";
        }

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM customer " + where);

//            out.print("<table width=50% border=1>");
//            out.print("<caption>Result:</caption>");


            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1)
                        + " " + resultSet.getString(2)
                        + " " + resultSet.getString(3)
                        + " " + resultSet.getString(4)
                        + " " + resultSet.getString(5)
                        + " " + resultSet.getInt(6)
                        + " " + resultSet.getString(7)
                        + " " + resultSet.getString(8)
                        + " " + resultSet.getString(9));
            }
//            ResultSetMetaData metaDataResult = resultSet.getMetaData();
//            out.print("<tr>");
//            for (int i = 1; i <= metaDataResult.getColumnCount(); i++) {
//                out.print("<th>" + metaDataResult.getColumnName(i) + "</th>");
//            }
//            out.print("</tr>");
//
//            while (resultSet.next()) {
//                out.print("<tr><td>" + resultSet.getString(1)
//                        + "</td><td>" + resultSet.getString(2)
//                        + "</td ><td > " + resultSet.getDate(3)
//                        + " </td ><td > " + resultSet.getInt(4)
//                        + " </td ></tr > ");
//            }
//
//            out.print("</table>");

        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            out.close();
        }
    }

    public void deleteFromDatabase(Integer customerNumber) {
        try {
            preparedStatement = connection.prepareStatement("DELETE customer WHERE customerNumber = ?");
            preparedStatement.setInt(1, customerNumber);
            if (preparedStatement.executeUpdate() > 0) {
                out.print("successfully deleted ...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDatabase(String corporationName, String financialCode, Integer customerNumber) {
        String where = "";
        if (corporationName != null) {
            where += (where == "") ? " WHERE " : " AND "
                    + "corporationName = " + corporationName;
        }
        if (financialCode != null) {
            where += (where.equals("")) ? " WHERE " : " AND "
                    + "financialCode = " + financialCode;
        }
        if (customerNumber != null) {
            where += (where == "") ? " WHERE " : " AND "
                    + "customerNumber = " + customerNumber.toString();
        }

        try {
            preparedStatement = connection.prepareStatement("UPDATE customer " + where);
            if (preparedStatement.executeUpdate() > 0) {
                out.print("successfully updated ...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void exist(String financialCode) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM customer WHERE financialCode= '" + financialCode + "'");
            if (preparedStatement.executeUpdate() > 0) {
                //***send reply to legalLogic that this financial code registered before!
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
