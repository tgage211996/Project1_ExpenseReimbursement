package com.ex.webapp.servlets;


import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class UserHelloServlet extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    /**
     * this servlet is used to get an employee's name from session information and display it on the navbar
     * @param request
     * @param response
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        out.print(session.getAttribute("firstname"));
        LOGGER.info("The login user's first name is obtained");
        out.flush();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        doGet(req, resp);
    }
}
