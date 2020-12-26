package com.ex.webapp.servlets;

import com.ex.webapp.DOA.Reimbursement;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class ViewPenReimServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    /**
     * This servlet is used to handle the request for all pending Reimbursements for an employee
     * @param request
     * @param response
     * @throws IOException
     */

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        LOGGER.info("we have entered the viewPendReimServlet");
        HttpSession session = request.getSession();
        Reimbursement rbm = Reimbursement.getInstance();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();


        String email = (String) session.getAttribute("user");

        out.print(rbm.getPenReimbursement(email).toJSONString());
        out.flush();

    }
}
