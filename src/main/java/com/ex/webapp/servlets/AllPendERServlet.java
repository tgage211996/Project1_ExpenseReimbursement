package com.ex.webapp.servlets;

import com.ex.webapp.DOA.Reimbursement;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This servlet is used to return all pending Reimbursements
 */
public class AllPendERServlet extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        LOGGER.info("we have entered AllPendERServlet");
        Reimbursement rbm = Reimbursement.getInstance();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        LOGGER.info("The next log should show what pending reimbursements where returned");
        LOGGER.info(rbm.getAllPenRem().toJSONString());
        out.print(rbm.getAllPenRem().toJSONString());
        out.flush();

    }
}
