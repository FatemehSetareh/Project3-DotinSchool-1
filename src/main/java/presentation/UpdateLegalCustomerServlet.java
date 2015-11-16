package presentation;

import business.LegalLogic;

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
public class UpdateLegalCustomerServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        String corporationName = request.getParameter("corporationName");
        String financialCode = request.getParameter("financialCode");
        String registerDate = request.getParameter("registerDate");
        Integer customerNumber = Integer.valueOf(request.getParameter("customerNumber"));


        try {
            LegalLogic legalLogic = new LegalLogic();
            legalLogic.updateLogic(corporationName, financialCode, registerDate, customerNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
