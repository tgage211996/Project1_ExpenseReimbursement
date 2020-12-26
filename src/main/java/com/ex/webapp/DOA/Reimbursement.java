package com.ex.webapp.DOA;

import com.ex.webapp.Util.ConnectionUtil;
import com.ex.webapp.Util.PostgresConnectionUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Properties;

public class Reimbursement {
    private static Reimbursement instance;

    Reimbursement(){}

    public static Reimbursement getInstance(){
        if(instance == null)
            instance = new Reimbursement();
        return instance;
    }


    /**
     * This method is for creating a reimbursment record in the database which will belong to the employee submitting
     * the reimbursement
     * @param amount The amount of money the employee is wishing to be reimbursed.
     * @param empEmail The employee's email
     * @param date  The date the reimbursement was submitted
     */
    public void submitReimbursement(double amount, String empEmail, String date){
        ConnectionUtil util = null;
        Properties dbProps = null;

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("db.properties");
            dbProps = new Properties();
            dbProps.load(input);
            util = new PostgresConnectionUtil(dbProps);

            Connection c = util.newConnection();

            CallableStatement stmt = c.prepareCall("{call submitReimbursement(?, ?, ?, ?)}");
            stmt.setBigDecimal(1, BigDecimal.valueOf(amount));
            stmt.setString(2,"pending");
            stmt.setString(3,empEmail);
            stmt.setString(4,date);
            stmt.execute();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * This method is for a employee to see all their pending reimibursements
     * @param email The employee's email
     * @return Returns a JSONArray
     * @throws IOException
     */
    public JSONArray getPenReimbursement(String email) throws IOException {
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

            PreparedStatement ps = c.prepareStatement("select * from reimbursement where status=? and created_by=?");
            ps.setString(1, "pending");
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("id", rs.getInt(1));
                obj.put("amount", rs.getString(2));
                obj.put("status", rs.getString(3));
                obj.put("date",rs.getString(5));
                obj.put("reviewedby", rs.getInt(6));
                array.add(obj);
            }
        }catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return array;
    }


    /**
     * This method is to return an employee's resolved reimbursements
     * @param email This is the employee's email
     * @return returns a JSONArray
     * @throws IOException
     */
    public JSONArray getResReimbursement(String email) throws IOException {
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

            PreparedStatement ps = c.prepareStatement("select * from reimbursement where status!=? and created_by=?");
            ps.setString(1, "pending");
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("id", rs.getInt(1));
                obj.put("amount", rs.getString(2));
                obj.put("status", rs.getString(3));
                obj.put("date",rs.getString(5));
                obj.put("reviewedby", rs.getInt(6));
                array.add(obj);
            }
        }catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return array;
    }


    /**
     * This method is used to get any existing pending reimbursements in the database
     * @return
     * @throws IOException
     */
    public JSONArray getAllPenRem() throws IOException {
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

            PreparedStatement ps = c.prepareStatement("select * from reimbursement where status=?");
            ps.setString(1, "pending");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("id", rs.getInt(1));
                obj.put("amount", rs.getString(2));
                obj.put("status", rs.getString(3));
                obj.put("date",rs.getString(5));
                array.add(obj);
            }
        }catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return array;
    }

    /**
     * This method is used to get any existing resolved reimbursements in the database
     * @return
     * @throws IOException
     */
    public JSONArray getAllResRem() throws IOException {
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

            PreparedStatement ps = c.prepareStatement("select * from reimbursement where status!=?");
            ps.setString(1, "pending");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("id", rs.getInt(1));
                obj.put("amount", rs.getString(2));
                obj.put("status", rs.getString(3));
                obj.put("date",rs.getString(5));
                obj.put("reviewedby", rs.getInt(6));
                array.add(obj);
            }
        }catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return array;
    }

    /**
     * This method returns all reimbursements for a single employee
     * @param email This is the desired employee email that is given by the manager
     * @return
     * @throws IOException
     */
    public JSONArray getEmpReims(String email) throws IOException {
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

            PreparedStatement ps = c.prepareStatement("select * from reimbursement where created_by=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("id", rs.getInt(1));
                obj.put("amount", rs.getString(2));
                obj.put("status", rs.getString(3));
                obj.put("date",rs.getString(5));
                obj.put("reviewedby", rs.getInt(6));
                array.add(obj);
            }
        }catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return array;
    }

    /**
     * This method is used to change the status of a pending reimbursement request to approved or denied
     * @param status The particular status we are wanting to change our reimbursement status to
     * @param id The id of the reibursement request being updated
     * @param mang_Id The id of the manager reviewing the reimbursement request
     * @throws IOException
     */
    public void approveDenyReimbursement(String status, int id, int mang_Id) throws IOException {
        ConnectionUtil util = null;
        Properties dbProps = null;

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("db.properties");
            dbProps = new Properties();
            dbProps.load(input);
            util = new PostgresConnectionUtil(dbProps);

            Connection c = util.newConnection();

            PreparedStatement ps = c.prepareStatement("UPDATE public.reimbursement SET status=?, reviewed_by=? WHERE reimburse_id=?;");
            ps.setString(1, status);
            ps.setInt(2,mang_Id);
            ps.setInt(3, id);
            ps.executeUpdate();

        }catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
    }
}
