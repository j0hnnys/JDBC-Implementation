//STEP 1. Import required packages

import java.sql.*;

public class Example1 {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/mysql";
    //  static final String DB_URL = "jdbc:mysql://localhost/?user=root&password=rootpassword";
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "password";
//    static final String PASS = "<52x35pg(m#O";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "DROP TABLE IF EXISTS authors, authorISBN, titles, publishers;\n" +
                    "\n" +
                    "CREATE TABLE authors (\n" +
                    "  authorID INTEGER NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,\n" +
                    "  firstName CHAR(20) NOT NULL,\n" +
                    "  lastName CHAR(20) NOT NULL\n" +
                    "  ) ENGINE=INNODB;\n" +
                    "\n" +
                    "\n" +
                    "CREATE TABLE publishers (\n" +
                    "  publisherID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                    "  publisherName CHAR(100) NOT NULL\n" +
                    "  ) ENGINE=INNODB;\n" +
                    "\n" +
                    "\n" +
                    "CREATE TABLE titles (\n" +
                    "  isbn VARCHAR(13) NOT NULL PRIMARY KEY,\n" +
                    "  editionNumber INTEGER NOT NULL,\n" +
                    "  Year CHAR(4) NOT NULL,\n" +
                    "  publisherID INTEGER NOT NULL,\n" +
                    "  price FLOAT(8,2) NOT NULL,\n" +
                    "  title VARCHAR(500) NOT NULL,\n" +
                    "  INDEX (publisherID),\n" +
                    "  FOREIGN KEY (publisherID)\n" +
                    "    REFERENCES publishers(publisherID)\n" +
                    "    ON UPDATE CASCADE\n" +
                    "    ON DELETE CASCADE\n" +
                    "  ) ENGINE=INNODB;\n" +
                    "\n" +
                    "\n" +
                    "CREATE TABLE authorISBN (\n" +
                    "  authorID INTEGER NOT NULL UNIQUE,\n" +
                    "  isbn VARCHAR(13) NOT NULL ,\n" +
                    "  INDEX (authorID, isbn),\n" +
                    "  FOREIGN KEY (authorID)\n" +
                    "    REFERENCES authors(authorID)\n" +
                    "    ON UPDATE CASCADE\n" +
                    "    ON DELETE CASCADE,\n" +
                    "  FOREIGN KEY (isbn)\n" +
                    "    REFERENCES titles(isbn)\n" +
                    "    ON UPDATE CASCADE\n" +
                    "    ON DELETE CASCADE\n" +
                    "  ) ENGINE=INNODB;\n";
            //STEP 6: Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end FirstExample