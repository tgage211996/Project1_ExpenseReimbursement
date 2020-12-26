package com.ex.webapp.servlets;

import com.ex.webapp.DOA.Employee;
import com.ex.webapp.DOA.Reimbursement;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetAllEmpReims extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    /**
     * This method is used to get all existing Reimbursements
     * @param request The request for all reimbursements
     * @param response The requested reimbursement data
     * @throws IOException
     * @throws ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        LOGGER.info("we have now entered the servlet for getting all of an employee's reimbursement records");
        String email = request.getParameter("email");
        Employee emp = Employee.getInstance();
        Reimbursement reim = Reimbursement.getInstance();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        if(emp.checkEmployee2(email)) {
            LOGGER.info("getting the employee's reimbursements");
            out.print(reim.getEmpReims(email).toJSONString());
            out.flush();
        }else{
            LOGGER.info("no employee with the entered email exists");
            JSONArray empty = new JSONArray();
            out.print(empty.toJSONString());
            out.flush();
        }
    }
}
