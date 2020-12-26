package com.ex.webapp.servlets;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserInfoServlet extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    /**
     * This servlet is used to to handle a request for user information from session cache
     * @param request
     * @param response
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        LOGGER.info("we have entered the userinfoservlet");
        HttpSession session = request.getSession();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF-8");

        JSONObject json = new JSONObject();
        json.put("fname", session.getAttribute("firstname"));
        json.put("lname", session.getAttribute("lastname"));
        json.put("email", session.getAttribute("user"));
        json.put("address", session.getAttribute("address"));
        json.put("dep_id", session.getAttribute("department"));
        json.put("super_Id", session.getAttribute("supervisor"));
        out.print(json.toJSONString());
        out.flush();

    }
}
