package persistence.crud;

import business.RealCustomer;
import persistence.DotinBankDataBase;

import java.sql.*;
import java.util.ArrayList;


public class RealCustomerActions {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private static String insertionSuccess;
    private static ArrayList<RealCustomer> searchResultArray;
    private static ResultSetMetaData metaDataResult;
    private static String deletionSuccess;
    private static String updatingSuccess;

    public RealCustomerActions() throws SQLException, ClassNotFoundException {
        Class.forName(DotinBankDataBase.JDBC_DRIVER);
        connection = DriverManager.getConnection(DotinBankDataBase.DATABASE_URL, DotinBankDataBase.USERNAME, DotinBankDataBase.PASSWORD);
        searchResultArray = new ArrayList();
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
                System.out.println("Success");
                insertionSuccess = realCustomer.getFirstName() + " " + realCustomer.getLastName() + " Registered Successfully.";
            } else
                insertionSuccess = realCustomer.getFirstName() + " " + realCustomer.getLastName() + " Registration Failed.";
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchDatabase(String firstName, String lastName, String nationalCode, Integer customerNumber) {
        String where = "";
        if (firstName != null) {
            if (where.equals("")) {
                where += " WHERE " + "firstName = '" + firstName + "'";
            } else where += " AND " + "firstName = '" + firstName + "'";
        }
        if (lastName != null) {
            if (where.equals("")) {
                where += " WHERE " + "lastName = '" + lastName + "'";
            } else where += " AND " + "lastName = '" + lastName + "'";
        }
        if (nationalCode != null) {
            if (where.equals("")) {
                where += " WHERE " + "nationalCode = '" + nationalCode + "'";
            } else where += " AND " + "nationalCode = '" + nationalCode + "'";
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
                RealCustomer realCustomer = new RealCustomer(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6));
                searchResultArray.add(realCustomer);
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

    public void updateDatabase(String firstName, String lastName, String fatherName, String nationalCode, String birthDate, Integer customerNumber) {
        String set = "";
        if (!firstName.equals("")) {
            if (set.equals("")) {
                set += " SET " + "firstName = '" + firstName + "'";
            } else set += " , " + "firstName = '" + firstName + "'";
        }
        if (!lastName.equals("")) {
            if (set.equals("")) {
                set += " SET " + "lastName = '" + lastName + "'";
            } else set += " , " + "lastName = '" + lastName + "'";
        }
        if (!fatherName.equals("")) {
            if (set.equals("")) {
                set += " SET " + "fatherName = '" + fatherName + "'";
            } else set += " , " + "fatherName = '" + fatherName + "'";
        }
        if (!nationalCode.equals("")) {
            if (set.equals("")) {
                set += " SET " + "nationalCode = '" + nationalCode + "'";
            } else set += " , " + "nationalCode = '" + nationalCode + "'";
        }
        if (!birthDate.equals("")) {
            if (set.equals("")) {
                set += " SET " + "birthDate = '" + birthDate + "'";
            } else set += " , " + "birthDate = '" + birthDate + "'";
        }

        try {
            preparedStatement = connection.prepareStatement("UPDATE customer " + set + " WHERE customerNumber = " + customerNumber);
            System.out.println(preparedStatement);
            if (preparedStatement.executeUpdate() > 0) {
                updatingSuccess = " Updated Successfully.";
            } else
                updatingSuccess = " Updating Not Successful.";
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void exist(String nationalCode) {
//        try {
//            preparedStatement = connection.prepareStatement("SELECT EXISTS (SELECT customerNumber FROM customer WHERE nationalCode= '" + nationalCode + "')");
//            if (preparedStatement.executeQuery() != null) {
//                //***send reply to realLogic that this national code registered before!
//                exists = true;
//            }else{
//                exists = false;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static String getInsertionSuccess() {
        return insertionSuccess;
    }

    public static ArrayList<RealCustomer> getSearchResultArray() {
        return searchResultArray;
    }

    public static ResultSetMetaData getMetaDataResult() {
        return metaDataResult;
    }

    public static String getDeletionSuccess() {
        return deletionSuccess;
    }

    public static String getUpdatingSuccess() {
        return updatingSuccess;
    }
}