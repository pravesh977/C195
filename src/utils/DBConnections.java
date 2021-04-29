package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/** A seperate class that handles database connections using jdbc*/
public class DBConnections {
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com:3306/";
    private static final String dbName = "WJ05Zvi";


    private static final String jdbcURL = protocol + vendorName + ipAddress + dbName + "?connectionTimeZone=SERVER";

//    private static final String MYSQLJDBCDriver = "../../External Libraries/mysql-connector-java-8.0.23/";
    private static final String MYSQLJDBCDriver = "com.mysql.cj.jdbc.Driver";

    private static final String username = "U05Zvi";
    private static final String password = "53688651099";

    private static Connection conn = null;

    /** Starts a connection with the database with username, password and database url.*/
    public static Connection startConnection(){
        try {
            Class.forName(MYSQLJDBCDriver);
            conn = DriverManager.getConnection(jdbcURL, username, password);
            //System.out.println("Connection was successful Hurray!!!");
        }
        catch (ClassNotFoundException e){
            //System.out.println(e.getMessage());
            e.printStackTrace();
        }

        catch (SQLException e){
            //System.out.println(e);
            e.printStackTrace();
        }
        //System.out.println(conn);
        return conn;
    }

    /** Gets a connection whenever SQL query is made.*/
    public static Connection getConnection() {
        return conn;
    }

    /** Used to end connection after terminating the program.*/
    public static void closeConnection() {
        try {
            conn.close();
            //System.out.println("Connection Closed!");
        }
        catch(SQLException e) {
            //System.out.println(e.getMessage());
        }
    }
}
