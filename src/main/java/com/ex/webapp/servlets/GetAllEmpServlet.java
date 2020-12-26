package com.ex.webapp.servlets;

import com.ex.webapp.DOA.Employee;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetAllEmpServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    /**
     * This servlet is used to handle a request for all Employee data
     * @param request The request parameters for getting all Employee data
     * @param response The response data including all employee data
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        LOGGER.info("entered the getallempservlet");
        Employee emp = Employee.getInstance();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        out.print(emp.getAllEmp().toJSONString());
        out.flush();

    }
}
