package com.ex.webapp.Util;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class ConnectionUtil {
    protected String username;
    protected String password;
    protected String url;
    public abstract Connection newConnection() throws SQLException;

}

