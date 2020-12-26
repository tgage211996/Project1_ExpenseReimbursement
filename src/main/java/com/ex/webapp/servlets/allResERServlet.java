package com.ex.webapp.servlets;

import com.ex.webapp.DOA.Reimbursement;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This servlet is used to get and return an employee's resolved reimbursements
 */
public class allResERServlet extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        LOGGER.info("we are now returning all resolved reimbursements");
        Reimbursement rbm = Reimbursement.getInstance();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        out.print(rbm.getAllResRem().toJSONString());
        out.flush();

    }
}
