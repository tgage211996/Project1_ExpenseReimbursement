package com.ex.webapp.servlets;

import com.ex.webapp.DOA.Employee;
import com.ex.webapp.DOA.Manager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    Employee emp = Employee.getInstance();
    Manager mngr = Manager.getInstance();
    static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    /**
     * This servlet is used to login a user with a email and password and distinguish if it is a manager or employee
     * @param request The request parameters email and password
     * @param response The response of the servlet
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email").toLowerCase();
        String pass = request.getParameter("pass");

        if(emp.checkEmployee(email, pass))
        {
            LOGGER.info("We  have verified that the logger is an employee");
            JSONObject obj = emp.getEmployeeData(email,pass);
            //creating session
            HttpSession session = request.getSession();
            session.setAttribute("user", email);
            session.setAttribute("firstname", obj.get("firstName") );
            session.setAttribute("lastname", obj.get("lastName"));
            session.setAttribute("address", obj.get("address"));
            session.setAttribute("department", obj.get("departmentId"));
            session.setAttribute("supervisor", obj.get("supervisorId"));
            response.sendRedirect("Employee.jsp");
        }
        else if(mngr.checkManager(email, pass))
        {
            LOGGER.info("We have verified that the logger in a manager");
            JSONObject obj = mngr.getManagerData(email,pass);
            //creating session
            HttpSession session = request.getSession();
            session.setAttribute("Mang_Id", obj.get("id"));
            session.setAttribute("user", email);
            session.setAttribute("firstname", obj.get("firstName"));
            session.setAttribute("lastname", obj.get("lastName"));
            response.sendRedirect("Manager.jsp");
        }else {
            LOGGER.info("There were no records on file that matched");
            out.println("<script type=\"text/javascript\">");
            out.println("alert('User or password incorrect, please try again');");
            out.println("</script>");
            RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
            rs.include(request,response);
        }
    }
}
