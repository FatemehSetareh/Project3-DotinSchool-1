package persistence.crud;

import business.RealCustomer;
import persistence.DotinBankDataBase;

import java.sql.*;
import java.util.ArrayList;


public class RealCustomerCrud {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private static String insertionSuccess;
    private static ArrayList<RealCustomer> searchResultArray;
    private static ResultSetMetaData metaDataResult;
    private static String deletionSuccess;
    private static String updatingSuccess;


    public RealCustomerCrud() throws SQLException, ClassNotFoundException {
        Class.forName(DotinBankDataBase.JDBC_DRIVER);
        connection = DriverManager.getConnection(DotinBankDataBase.DATABASE_URL, DotinBankDataBase.USERNAME, DotinBankDataBase.PASSWORD);
        searchResultArray = new ArrayList<RealCustomer>();
    }

    public void insertToDatabase(RealCustomer realCustomer) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO customer"
                    + "(firstName, lastName, fatherName, nationalCode, birthDate, customerType) VALUES"
                    + "(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, realCustomer.getFirstName());
            preparedStatement.setString(2, realCustomer.getLastName());
            preparedStatement.setString(3, realCustomer.getFatherName());
            preparedStatement.setString(4, realCustomer.getNationalCode());
            preparedStatement.setString(5, realCustomer.getBirthDate());
            preparedStatement.setString(6, realCustomer.getCustomerType());
            System.out.println(preparedStatement);

            if (preparedStatement.executeUpdate() > 0) {
                int customerNumberAutoInc = -1;
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    customerNumberAutoInc = resultSet.getInt(1);
                }
                insertionSuccess = "Customer Name: " + realCustomer.getFirstName() + " " + realCustomer.getLastName() + "<br>" + " Customer Number: " + customerNumberAutoInc + "<br>" + " Registered Successfully.";
            } else
                insertionSuccess = "Customer Name: " + realCustomer.getFirstName() + " " + realCustomer.getLastName() + " Registration Failed.";
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchDatabase(String firstName, String lastName, String nationalCode, Integer customerNumber) {
        String where = "";
        ArrayList<String> values = new ArrayList<String>();
        if (firstName != null) {
            if (where.equals("")) {
                where += " WHERE " + "firstName = ?";
            } else {
                where += " AND " + "firstName = ?";
            }
            values.add(firstName);
        }
        if (lastName != null) {
            if (where.equals("")) {
                where += " WHERE " + "lastName = ?";
            } else {
                where += " AND " + "lastName = ?";
            }
            values.add(lastName);
        }
        if (nationalCode != null) {
            if (where.equals("")) {
                where += " WHERE " + "nationalCode = ?";
            } else {
                where += " AND " + "nationalCode = ?";
            }
            values.add(nationalCode);
        }
        if (customerNumber != null) {
            if (where.equals("")) {
                where += " WHERE " + "customerNumber = ?";
            } else {
                where += " AND " + "customerNumber = ?";
            }
            values.add(customerNumber.toString());
        }
        if (where.equals("")) {
            where += " WHERE " + "customerType = '" + 1 + "'";
        } else {
            where += " AND " + "customerType = '" + 1 + "'";
        }

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM customer " + where);
            //**To prevent injection SQL attack
            for (int i = 0; i < values.size(); i++) {
                preparedStatement.setString(i + 1, values.get(i));
            }
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                RealCustomer realCustomer = new RealCustomer(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getString(7));
                searchResultArray.add(realCustomer);
            }
            metaDataResult = resultSet.getMetaData();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void deleteFromDatabase(Integer customerNumber) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE customerNumber = " + customerNumber);
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
                updatingSuccess = "Account " + customerNumber + " Updated Successfully.";
            } else
                updatingSuccess = "Account " + customerNumber + " Could Not Update.";
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkExistence(RealCustomer realCustomer) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS ROW_COUNT  FROM customer WHERE nationalCode= '" + realCustomer.getNationalCode() + "' AND customerType='1' ");
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

    public static void setInsertionSuccess(String insertionSuccess) {
        RealCustomerCrud.insertionSuccess = insertionSuccess;
    }
}