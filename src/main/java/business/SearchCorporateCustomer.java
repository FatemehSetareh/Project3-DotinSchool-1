package business;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class SearchCorporateCustomer extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String corporationName = request.getParameter("corporationName");
        String financialCode = request.getParameter("financialCode");
        Integer customerNumber = Integer.valueOf(request.getParameter("customerNumber"));

        String where = "";
        if (corporationName != null) {
            where += (where == "") ? " WHERE " : " AND "
                    + "corporationName = " + corporationName;
        }
        if (financialCode != null) {
            where += (where == "") ? " WHERE " : " AND "
                    + "financialCode = " + financialCode;
        }
        if (customerNumber != null) {
            where += (where == "") ? " WHERE " : " AND "
                    + "customerNumber = " + customerNumber.toString();
        }
        String query = "SELECT * "
                + "  FROM CORPORATECUSTOMER"
                + where;

        try {
            Class.forName(DotinBankDataBase.JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DotinBankDataBase.DATABASE_URL, DotinBankDataBase.USERNAME, DotinBankDataBase.PASSWORD);

            System.out.println("This is my query : " + query);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setString(1, corporationName);

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
                        + "</td ><td > " + resultSet.getDate(3)
                        + " </td ><td > " + resultSet.getInt(4)
                        + " </td ></tr > ");
            }

            out.print("</table>");

        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            out.close();
        }
    }
}

