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
            //out.print(RealCustomerActions.getDeletionSuccess());
            out.print("<html>" +
                    "<head>" +
                    "    <style>" +
                    "        body {" +
                    "            background: lightpink center;" +
                    "            padding: 25pt;" +
                    "        }" +
                    "" +
                    "        h1 {" +
                    "            font-size: 250%;" +
                    "            color: darkblue;" +
                    "            text-align: center;" +
                    "            margin-top: 60pt;" +
                    "        }" +
                    "" +
                    "        h3 {" +
                    "            font-size: 15pt;" +
                    "            color: black;" +
                    "            text-align: center;" +
                    "            margin-top: 30pt;" +
                    "        }" +
                    "" +
                    "        p {" +
                    "            font-size: 11pt;" +
                    "            text-align: left;" +
                    "            margin-top: 60pt;" +
                    "        }" +
                    "" +
                    "        input {" +
                    "            font-weight: bold;" +
                    "            border: 3px solid #998297;" +
                    "            padding: 5pt;" +
                    "            height: 25pt;" +
                    "            border-radius: 7px;" +
                    "        }" +
                    "" +
                    "    </style>" +
                    "</head>" +
                    "" +
                    "<body>" +
                    "<h1>Dotin Internet Bank</h1>" +
                    "" +
                    "<h3>" + RealCustomerActions.getDeletionSuccess() + "</h3>" +
                    "</body>" +
                    "</html>");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
