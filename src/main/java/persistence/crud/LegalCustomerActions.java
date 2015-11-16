package persistence.crud;

import business.LegalCustomer;
import persistence.DotinBankDataBase;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class LegalCustomerActions {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private static String insertionSuccess;
    private static ArrayList<LegalCustomer> searchResultArray;
    private static ResultSetMetaData metaDataResult;


    public LegalCustomerActions() throws SQLException, ClassNotFoundException {
        Class.forName(DotinBankDataBase.JDBC_DRIVER);
        connection = DriverManager.getConnection(DotinBankDataBase.DATABASE_URL, DotinBankDataBase.USERNAME, DotinBankDataBase.PASSWORD);
        searchResultArray = new ArrayList();
    }

    public void insertToDatabase(LegalCustomer legalCustomer) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO customer"
                    + "(corporationName, financialCode, registerDate) VALUES"
                    + "(?,?,?)");
            preparedStatement.setString(1, legalCustomer.getCorporationName());
            preparedStatement.setString(2, legalCustomer.getFinancialCode());
            preparedStatement.setString(3, legalCustomer.getRegisterDate());
            System.out.println(preparedStatement);

            if (preparedStatement.executeUpdate() > 0) {
                insertionSuccess = legalCustomer.getCorporationName() + " Registered Successfully.";
            } else
                insertionSuccess = legalCustomer.getCorporationName() + " Registration Failed.";
        } catch (Exception e2) {
            e2.printStackTrace();
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
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LegalCustomer legalCustomer = new LegalCustomer(resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9));
                searchResultArray.add(legalCustomer);
            }
            metaDataResult = resultSet.getMetaData();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void deleteFromDatabase(Integer customerNumber) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE customerNumber = '" + customerNumber + "'");
            if (preparedStatement.executeUpdate() > 0) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDatabase(String corporationName, String financialCode, String registerDate, Integer customerNumber) {
        String set = "";
        if (corporationName != null) {
            set += (set.equals("")) ? " SET " : " AND " + "corporationName = '" + corporationName + "'";
        }
        if (financialCode != null) {
            set += (set.equals("")) ? " SET " : " AND " + "financialCode = '" + financialCode + "'";
        }
        if (registerDate != null) {
            set += (set.equals("")) ? " SET " : " AND " + "registerDate = '" + registerDate + "'";
        }
//        if (customerNumber != null) {
//            set += (set == "") ? " SET " : " AND " + "customerNumber = '" + customerNumber + "'";
//        }

        try {
            preparedStatement = connection.prepareStatement("UPDATE customer " + set + "WHERE customerNumber = '" + customerNumber + "'");
            if (preparedStatement.executeUpdate() > 0) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void exist(String financialCode) {
//        try {
//            preparedStatement = connection.prepareStatement("SELECT * FROM customer WHERE financialCode = '" + financialCode + "'");
//            if (preparedStatement.executeUpdate() > 0) {
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static ResultSetMetaData getMetaDataResult() {
        return metaDataResult;
    }

    public static ArrayList<LegalCustomer> getSearchResultArray() {
        return searchResultArray;
    }

    public static String getInsertionSuccess() {
        return insertionSuccess;
    }
}
