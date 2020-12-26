package com.ex.webapp.DOA;


import com.ex.webapp.Util.ConnectionUtil;
import com.ex.webapp.Util.PostgresConnectionUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class Employee {

    private static Employee instance;

    Employee(){}

    public static Employee getInstance(){
        if(instance == null)
            instance = new Employee();
        return instance;
    }


    /**
     * This method checks to see if the login email and password exists within the database and returns true if the
     * email and pasword exists or false if the email and password does not exist
     * @param email The user login email
     * @param pass The user login password
     * @return
     * @throws IOException
     */
    public boolean checkEmployee(String email, String pass) throws IOException {
        boolean st = false;
        ConnectionUtil util = null;
        Properties dbProps = null;

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("db.properties");
            dbProps = new Properties();
            dbProps.load(input);
            util = new PostgresConnectionUtil(dbProps);

            Connection c = util.newConnection();

            PreparedStatement ps = c.prepareStatement("select * from employee where email=? and password=?");
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            st = rs.next();
        }catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return st;
    }

    /**
     * This method is used to check the data base if a employee with a certain email exists
     * @param email This is the user's email
     * @return
     * @throws IOException
     */
    public boolean checkEmployee2(String email) throws IOException {
        boolean st = false;
        ConnectionUtil util = null;
        Properties dbProps = null;

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("db.properties");
            dbProps = new Properties();
            dbProps.load(input);
            util = new PostgresConnectionUtil(dbProps);

            Connection c = util.newConnection();

            PreparedStatement ps = c.prepareStatement("select * from employee where email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            st = rs.next();
        }catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return st;
    }


    /**
     * This method gets all the information of a particular employee based on their email and password on record
     * @param email This is the user's email
     * @param pass This is the user's password
     * @return
     * @throws IOException
     */
    public JSONObject getEmployeeData(String email, String pass) throws IOException {
        JSONObject obj = new JSONObject();
        ConnectionUtil util = null;
        Properties dbProps = null;

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("db.properties");
            dbProps = new Properties();
            dbProps.load(input);
            util = new PostgresConnectionUtil(dbProps);

            Connection c = util.newConnection();

            PreparedStatement ps = c.prepareStatement("select * from employee where email=? and password=?");
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                obj.put("id", rs.getInt(1));
                obj.put("firstName", rs.getString(2));
                obj.put("lastName", rs.getString(3));
                obj.put("address", rs.getString(6));
                obj.put("departmentId",rs.getString(7));
                obj.put("supervisorId", rs.getInt(8));
            }
        }catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return obj;
    }


    /**
     * This method is to return all records of the employees within the database
     * @return
     * @throws IOException
     */
    public JSONArray getAllEmp() throws IOException {
        JSONArray array = new JSONArray();
        ConnectionUtil util = null;
        Properties dbProps = null;

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("db.properties");
            dbProps = new Properties();
            dbProps.load(input);
            util = new PostgresConnectionUtil(dbProps);

            Connection c = util.newConnection();

            PreparedStatement ps = c.prepareStatement("select * from employee");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("id", rs.getInt(1));
                obj.put("firstName", rs.getString(2));
                obj.put("lastName", rs.getString(3));
                obj.put("email",rs.getString(4));
                obj.put("address", rs.getString(6));
                obj.put("departmentId",rs.getString(7));
                obj.put("supervisorId", rs.getInt(8));
                array.add(obj);
            }
        }catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return array;
    }

    /**
     * This method is used to update account information of an employee
     * @param fn The first name of an employee being changed to.
     * @param ln The last name of an employee being changed to.
     * @param email The email of an employee being changed to
     * @param pass The password of an employee being changed to
     * @param address The street address of an employee being changed to.
     * @throws IOException
     */
    public void updateEmp(String fn, String ln, String email, String pass, String address) throws IOException {
        ConnectionUtil util = null;
        Properties dbProps = null;

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("db.properties");
            dbProps = new Properties();
            dbProps.load(input);
            util = new PostgresConnectionUtil(dbProps);

            Connection c = util.newConnection();

            PreparedStatement ps = c.prepareStatement("UPDATE public.employee SET first_name=?, last_name=?, password=?, address=? WHERE email=?;");
            ps.setString(1, fn);
            ps.setString(2, ln);
            ps.setString(3, pass);
            ps.setString(4, address);
            ps.setString(5, email);
            ps.executeUpdate();

        }catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
    }
}
