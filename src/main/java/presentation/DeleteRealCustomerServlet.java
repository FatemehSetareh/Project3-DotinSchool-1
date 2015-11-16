package presentation;

import business.RealLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class DeleteRealCustomerServlet extends HttpServlet{

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //*** customerNumber require for deleting
        Integer customerNumber = Integer.valueOf(request.getParameter("customerNumber"));

        try {
            RealLogic realLogic = new RealLogic();
            realLogic.deleteLogic(customerNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
