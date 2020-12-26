package com.ex.webapp.servlets;

import com.ex.webapp.DOA.Reimbursement;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class approveDenyReimbursementServlet extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        LOGGER.info("we are now approving or denying reimbursement");
        Reimbursement rbm = Reimbursement.getInstance();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String status = request.getParameter("status");
        String id = request.getParameter("id");
        int mang_Id = (int) session.getAttribute("Mang_Id");
        rbm.approveDenyReimbursement(status, Integer.parseInt(id), mang_Id);
        response.sendRedirect("Manager.jsp");
    }
}
