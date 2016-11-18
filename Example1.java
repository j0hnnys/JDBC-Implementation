import java.sql.*;

/**
 * Created by Johnny on 11/5/16.
 */
public class Example1 {
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("No driver found for className");
            e.printStackTrace();
        }


        try {
            final String databaseName = "mysql-database";
            final String instanceConnectionName = "jdbc-implementation:us-central1:mysql-database";
            String jdbcUrl = String.format(
                    "jdbc:mysql://google/%s?cloudSqlInstance=%s&"
                            + "socketFactory=com.google.cloud.sql.mysql.SocketFactory",
                    databaseName,
                    instanceConnectionName);

            final String USER = "root'@'%";
            final String PASSWORD = "password";
            Connection conn = DriverManager.getConnection(jdbcUrl, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT First_Name FROM EMPLOYEES");
            while (rs.next()) {
                System.out.println(rs.getString("First_Name"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
