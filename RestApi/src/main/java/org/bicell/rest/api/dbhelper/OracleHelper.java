package org.bicell.rest.api.dbhelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class OracleHelper {

    private final String username="bicell";

    private final String password="bicell";

    private final String db_url="jdbc:oracle:thin:@34.172.153.64:1521/XEPDB1";


    public OracleHelper() {
        TimeZone timeZone=TimeZone.getTimeZone("Asia/Kolkata");
        TimeZone.setDefault(timeZone);
    }

    public Connection getConnection() throws Exception{
        Connection connection=null;
        connection=DriverManager.getConnection(db_url, username, password);
        return connection;
    }
}
