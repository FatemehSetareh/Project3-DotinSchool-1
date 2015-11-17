package presentation;

import business.RealLogic;
import persistence.crud.RealCustomerActions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


public class DeleteRealCustomerServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Integer customerNumber = Integer.valueOf(request.getParameter("customerNumber"));
        System.out.println(customerNumber);

        try {
            RealLogic realLogic = new RealLogic();
            realLogic.deleteLogic(customerNumber);
            out.print(RealCustomerActions.getDeletionSuccess());

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
