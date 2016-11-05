import java.sql.*;

public class Example1 {

    public static void main (java.lang.String[] args) {
        try {
            // This is where we load the JDBC driver (step-1)
            Class.forName("sun.jdbc.odbc.JdbcObdcDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load the Driver class");
            return;
        }
    }
}
