package business;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegisterCorporateCustomer extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String corporationName = request.getParameter("corporationName");
        String financialCode = request.getParameter("financialCode");
        String registerDate = request.getParameter("registerDate");

        //generateCustomerNumber

        try {
            Class.forName(DotinBankDataBase.JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DotinBankDataBase.DATABASE_URL, DotinBankDataBase.USERNAME, DotinBankDataBase.PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement("insert into corporateCustomer values(?,?,?,?)");

            preparedStatement.setString(1, corporationName);
            preparedStatement.setString(2, financialCode);
            preparedStatement.setString(3, registerDate);
            //preparedStatement.setString(4, customerNumber);

            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() > 0)
                out.print("You are successfully registered...");

        } catch (Exception e2) {
            System.out.println(e2);
        }
        out.close();
    }
}