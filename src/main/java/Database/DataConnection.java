package Database;

import java.sql.*;

/**
 * Created by DELL on 4/30/2017.
 */
public class DataConnection {

    private static String dbURL = "jdbc:oracle:thin:@localhost:1521:ORCL";
    private static String username = "sdproject";
    private static String password = "sd";

    private static Connection conn = null;

    public static Connection getConnection(){
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(dbURL, username, password);
            if(conn!=null) System.out.println("Connection successfully established.");
            else System.out.println("Could not establish connection");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("failed to close conn."+e.getMessage());
        }

    }
}
