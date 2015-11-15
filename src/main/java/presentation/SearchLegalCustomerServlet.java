package presentation;

import business.LegalLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class SearchLegalCustomerServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String corporationName;
        String financialCode;
        Integer customerNumber;

        if (!request.getParameter("corporationName").equals("")) {
            corporationName = request.getParameter("corporationName");
        } else corporationName = null;

        if (!request.getParameter("financialCode").equals("")) {
            financialCode = request.getParameter("financialCode");
        } else financialCode = null;

        if (!request.getParameter("customerNumber").equals("")) {
            customerNumber = Integer.valueOf(request.getParameter("customerNumber"));
        } else customerNumber = null;

        System.out.println(corporationName + "\n" + financialCode + "\n" + customerNumber);
        try {
            LegalLogic legalLogic = new LegalLogic();
            legalLogic.searchLogic(corporationName, financialCode, customerNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}