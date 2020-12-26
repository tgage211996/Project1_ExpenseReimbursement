package com.ex.webapp.servlets;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This servet is used to logout a user
 */
public class LogOutServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if(session!=null){
            LOGGER.info("invalidated session");
            session.invalidate();
        }
        LOGGER.info("redirecting to index.jsp");
        resp.sendRedirect("index.jsp");
    }

}
