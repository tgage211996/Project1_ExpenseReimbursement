package com.ex.webapp.DOA;

import com.ex.webapp.Util.ConnectionUtil;
import com.ex.webapp.Util.PostgresConnectionUtil;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Manager {

        private static com.ex.webapp.DOA.Manager instance;

        Manager(){}

        public static com.ex.webapp.DOA.Manager getInstance(){
            if(instance == null)
                instance = new com.ex.webapp.DOA.Manager();
            return instance;
        }


    /**
     * This method is used to check the login in information against the database of manager records
     * @param email This is the login email of the supposed manager
     * @param pass This is the manager login password
     * @throws IOException
     */
        public boolean checkManager(String email, String pass) throws IOException {
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

                PreparedStatement ps = c.prepareStatement("select * from manager where email=? and password=?");
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
     * This method is used to get information about the manager logging in
     * @param email The logging Manager's email
     * @param pass  The logging Manager's password
     * @return
     * @throws IOException
     */
    public JSONObject getManagerData(String email, String pass) throws IOException {
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

            PreparedStatement ps = c.prepareStatement("select * from manager where email=? and password=?");
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                obj.put("id", rs.getInt(1));
                obj.put("firstName", rs.getString(2));
                obj.put("lastName", rs.getString(3));
            }
        }catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return obj;
    }

    }
