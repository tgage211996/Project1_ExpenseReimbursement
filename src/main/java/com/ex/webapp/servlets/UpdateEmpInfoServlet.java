package com.ex.webapp.servlets;

import com.ex.webapp.DOA.Employee;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class UpdateEmpInfoServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    /**
     * this servlet is used to handle a request to update an employee's information
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        LOGGER.info("we have entered the UpdateEMPInfoServlet");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("user");
        String fn = request.getParameter("fn");
        String ln = request.getParameter("ln");
        String pass = request.getParameter("pass");
        String addr = request.getParameter("addr");
        Employee emp = Employee.getInstance();
        emp.updateEmp(fn, ln, email, pass, addr);
        LOGGER.info("being redirected to index.jsp");
        response.sendRedirect("index.jsp");
        session.invalidate();
    }
}
