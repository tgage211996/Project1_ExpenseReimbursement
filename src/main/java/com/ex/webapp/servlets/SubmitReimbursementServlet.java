package com.ex.webapp.servlets;

import com.ex.webapp.DOA.Reimbursement;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * this servlet is used to handle a request to submit a new reimbursement entry to the data
 */
public class SubmitReimbursementServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("we have entered the submitReimbursement Servlet");
        Reimbursement rmb = Reimbursement.getInstance();
        HttpSession session = request.getSession();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        double amount = Double.parseDouble(request.getParameter("amount"));
        String date = request.getParameter("date");

        rmb.submitReimbursement(amount, (String) session.getAttribute("user"),date);

        out.println("<script type=\"text/javascript\">");
        out.println("alert('Reimbursement Form Completed');");
        out.println("</script>");
        LOGGER.info("now being redirected to employee.jsp");
        RequestDispatcher rs = request.getRequestDispatcher("Employee.jsp");
        rs.include(request, response);


    }
}
