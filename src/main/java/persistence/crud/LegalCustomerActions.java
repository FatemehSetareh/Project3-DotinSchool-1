package persistence.crud;

import business.LegalCustomer;
import persistence.DotinBankDataBase;

import java.sql.*;
import java.util.ArrayList;


public class LegalCustomerActions {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private static String insertionSuccess;
    private static ArrayList<LegalCustomer> searchResultArray;
    private static ResultSetMetaData metaDataResult;
    private static String deletionSuccess;
    private static String updatingSuccess;


    public LegalCustomerActions() throws SQLException, ClassNotFoundException {
        Class.forName(DotinBankDataBase.JDBC_DRIVER);
        connection = DriverManager.getConnection(DotinBankDataBase.DATABASE_URL, DotinBankDataBase.USERNAME, DotinBankDataBase.PASSWORD);
        searchResultArray = new ArrayList<LegalCustomer>();
    }

    public void insertToDatabase(LegalCustomer legalCustomer) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO customer"
                    + "(corporationName, financialCode, registerDate) VALUES"
                    + "(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, legalCustomer.getCorporationName());
            preparedStatement.setString(2, legalCustomer.getFinancialCode());
            preparedStatement.setString(3, legalCustomer.getRegisterDate());
            System.out.println(preparedStatement);

            if (preparedStatement.executeUpdate() > 0) {
                int customerNumberAutoInc = -1;
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    customerNumberAutoInc = resultSet.getInt(1);
                }
                insertionSuccess = "Corporation Name: " + legalCustomer.getCorporationName() + "<br>" + " Customer Number: " + customerNumberAutoInc + "<br>" + " Registered Successfully.";
            } else
                insertionSuccess = "Corporation Name: " + legalCustomer.getCorporationName() + "<br>" + " Registration Failed.";
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
                deletionSuccess = "Account " + customerNumber + " successfully deleted";
            } else {
                deletionSuccess = "Account " + customerNumber + " could not deleted";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDatabase(String corporationName, String financialCode, String registerDate, Integer customerNumber) {
        String set = "";
        if (!corporationName.equals("")) {
            if (set.equals("")) {
                set += " SET " + "corporationName = '" + corporationName + "'";
            } else set += " , " + "corporationName = '" + corporationName + "'";
        }
        if (!financialCode.equals("")) {
            if (set.equals("")) {
                set += " SET " + "financialCode = '" + financialCode + "'";
            } else set += " , " + "financialCode = '" + financialCode + "'";
        }
        if (!registerDate.equals("")) {
            if (set.equals("")) {
                set += " SET " + "registerDate = '" + registerDate + "'";
            } else set += " , " + "registerDate = '" + registerDate + "'";
        }

        try {
            preparedStatement = connection.prepareStatement("UPDATE customer " + set + "WHERE customerNumber = " + customerNumber);
            if (preparedStatement.executeUpdate() > 0) {
                updatingSuccess = "Account " + customerNumber + " Updated Successfully.";
            } else
                updatingSuccess = "Account " + customerNumber + " Could Not Update.";
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkExistence(LegalCustomer legalCustomer) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS ROW_COUNT  FROM customer WHERE financialCode= '" + legalCustomer.getFinancialCode() + "'");
        System.out.println(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println(resultSet.getInt("ROW_COUNT"));
            if (resultSet.getInt("ROW_COUNT") == 0) {
                return true;
            } else {
                return false;
            }
        }
        System.out.println("Error...");
        return false;
    }

    public static ResultSetMetaData getMetaDataResult() {
        return metaDataResult;
    }

    public static ArrayList<LegalCustomer> getSearchResultArray() {
        return searchResultArray;
    }

    public static String getInsertionSuccess() {
        return insertionSuccess;
    }

    public static String getDeletionSuccess() {
        return deletionSuccess;
    }

    public static String getUpdatingSuccess() {
        return updatingSuccess;
    }

    public static void setInsertionSuccess(String insertionSuccess) {
        LegalCustomerActions.insertionSuccess = insertionSuccess;
    }
}
