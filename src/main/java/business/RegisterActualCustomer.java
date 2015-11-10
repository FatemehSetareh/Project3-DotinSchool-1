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

public class RegisterActualCustomer extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String fatherName = request.getParameter("fatherName");
        String nationalCode = request.getParameter("nationalCode");
        String birthdayDate = request.getParameter("birthdayDate");

        //generateCustomerNumber

        try {
            Class.forName(DotinBankDataBase.JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DotinBankDataBase.DATABASE_URL, DotinBankDataBase.USERNAME, DotinBankDataBase.PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement("insert into actualCustomer values(?,?,?,?,?,?)");

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, fatherName);
            preparedStatement.setString(4, nationalCode);
            preparedStatement.setString(5, birthdayDate);
           // preparedStatement.setString(6, customerNumber);

            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() > 0)
                out.print("You are successfully registered...");

        } catch (Exception e2) {
            System.out.println(e2);
        }
        out.close();
    }

}  