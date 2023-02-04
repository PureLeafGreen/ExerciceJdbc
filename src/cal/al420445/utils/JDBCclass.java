package cal.al420445.utils;

import cal.al420445.model.Registration;
import org.h2.jdbc.JdbcSQLSyntaxErrorException;

import java.sql.*;

public class JDBCclass {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/registrationexercice";

    private static String QUERY = "SELECT id, first, last, age FROM Registration";

    //  Database credentials
    private static final String USER = "sa";
    private static final String PASS = "";

    private static Connection conn = null;
    private static Statement stmt = null;
    private static String sqlPs = "SELECT id, first, last, age FROM Registration" +
            " WHERE id = ?;";
    private static String insertRegistration = "INSERT INTO Registration(first, last, age) values(?,?,?);";


    static {
        // STEP 1: Register JDBC driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JDBCclass.createDatabase();
    }

    public static void createDatabase() {
        try {
            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 3: Execute a query
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();
            String sql =  "CREATE TABLE   REGISTRATION " +
                    "(id INTEGER auto_increment, " +
                    " first VARCHAR(255), " +
                    " last VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch(JdbcSQLSyntaxErrorException e) {
            // Database already exists
            handleException(e);
        } catch(SQLException se) {
            //Handle errors for JDBC
            handleException(se);
        } catch(Exception e) {
            //Handle errors for Class.forName
            handleException(e);
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                handleException(se);
            } //end finally try
        } //end try
        System.out.println("Goodbye!");
    }

    public static void dropTable() {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            String sql = "DROP TABLE REGISTRATION";
            stmt.executeUpdate(sql);
            System.out.println("Table deleted in given database...");
        } catch (SQLException e) {
            handleException(e);
        }
    }

    public static void insertRecords() {
        // Open a connection
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            // Execute a query
            System.out.println("Inserting records into the table...");
            String sql = "INSERT INTO Registration VALUES (100, 'Zara', 'Ali', 18)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Registration VALUES (101, 'Mahnaz', 'Fatma', 25)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Registration VALUES (102, 'Zaid', 'Khan', 30)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Registration VALUES(103, 'Sumit', 'Mittal', 28)";
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");
        } catch (SQLException e) {
            handleException(e);
        }
    }

    public static void selectRecords() {

        // Open a connection
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);
        ) {
            while(rs.next()){
                //Display values
                printResult(rs);
            }
            System.out.println();
        } catch (SQLException e) {
            handleException(e);
        }
    }

        public static void updateRecord() {
        // Open a connection
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            String sql = "UPDATE Registration " +
                    "SET age = 30 WHERE id in (100, 101)";
            stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery(QUERY);
            System.out.println("updated records");
            while(rs.next()){
                //Display values
                printResult(rs);
            }
            System.out.println();
            rs.close();
        } catch (SQLException e) {
            handleException(e);
        }
    }

    public static void deleteRecord() {
        // Open a connection
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            String sql = "DELETE FROM Registration " +
                    "WHERE id = 101";
            stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery(QUERY);
            System.out.println("Deleted record");
            while(rs.next()){
                //Display values
                printResult(rs);
            }
            System.out.println();
            rs.close();
        } catch (SQLException e) {
            handleException(e);
        }
    }

    public static void whereClause() {
        // Open a connection
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();) {

            // Select all records having ID equal or greater than 101
            System.out.println("Fetching records with condition...");
            String sql = "SELECT id, first, last, age FROM Registration" +
                    " WHERE id >= 101 ";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("records with id > 101");
            while(rs.next()){
                //Display values
                printResult(rs);
            }
            rs.close();
        } catch (SQLException e) {
            handleException(e);
        }
    }

    public static void prepareStatement() {
        // Open a connection
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement ps = conn.prepareStatement(sqlPs);) {

            // Select all records having ID equal or greater than 101
            System.out.println();
            System.out.println("Fetching records with prepared statement...");

            ps.setInt(1, 101);

            // NOTEZ le try à l'intérieur du try
            try (ResultSet rs = ps.executeQuery();) {
                System.out.println("records with id > 101");
                while (rs.next()) {
                    //Display values
                    printResult(rs);
                }
            }
        } catch (SQLException e) {
            handleException(e);
        }
    }

    private static void printResult(ResultSet rs) throws SQLException {
        System.out.print("ID: " + rs.getInt("id"));
        System.out.print(", Age: " + rs.getInt("age"));
        System.out.print(", First: " + rs.getString("first"));
        System.out.println(", Last: " + rs.getString("last"));
    }

    private static void handleException(Exception exception) {
        if (exception instanceof SQLException) {
            SQLException sqlException = (SQLException) exception;
            System.out.println("Error Code: " + sqlException.getErrorCode());
            System.out.println("SQL State: " + sqlException.getSQLState());
        }
        System.out.println("SQLException message: " + exception.getMessage());
        System.out.println("Stacktrace: ");
        exception.printStackTrace();
    }

    public static void save(Registration registration) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement ps = conn.prepareStatement(insertRegistration)) {

            // Select all records having ID equal or greater than 101
            System.out.println();
            System.out.println("inserting records with prepared statement...");


            ps.setString(1, registration.getFirstName());
            ps.setString(2, registration.getLastName());
            ps.setInt(3, registration.getAge());

            ps.executeUpdate();
        } catch (SQLException e) {
            handleException(e);
        }
    }

    public static Registration getRegistration(int id) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement ps = conn.prepareStatement(sqlPs);) {

            // Select all records having ID equal or greater than 101
            System.out.println();
            System.out.println("retrieving statement...");

            ps.setInt(1, id);

            // NOTEZ le try à l'intérieur du try
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return new Registration(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4));
                }
            }
        } catch (SQLException e) {
            handleException(e);
        }
        return null;
    }
}
