/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package css475.dropstudents.ecis;

/**
 *
 * @author joelm
 */
import java.sql.*;

public class MySqlConnection {

    private final String host;
    private final int port;

    /// Initialize for database called `dbName` in MySql server at host:port
    /// Exception thrown if com.mysql.jdbc.Driver fails to initialize
    /// get mysql driver at: https://dev.mysql.com/downloads/connector/j/
    public MySqlConnection(String host, int port) throws ClassNotFoundException {
        this.host = host;
        this.port = port;
        Class.forName("com.mysql.cj.jdbc.Driver"); // initialize Driver class
    }

    /// Use default port (3306)
    public MySqlConnection(String host) throws ClassNotFoundException {
        this(host, 3306);
    }

    public Database connect(String databaseName, String user, String password) throws SQLException {
        return new Database(databaseName, user, password);
    }

    public class Database implements AutoCloseable {

        private final String databaseName; 
        private Connection con;

        /// connect to a database on the server
        /// Throws IllegalStateException if already connected
        Database(String databaseName, String user, String password) throws SQLException {
            this.databaseName = databaseName;
            String connectString = String.format(
                    "jdbc:mysql://%s:%d/%s", 
                    MySqlConnection.this.host, 
                    MySqlConnection.this.port, 
                    this.databaseName
            );
            this.con = DriverManager.getConnection(connectString, user, password);
        }

        /// Check if connected to the database
        public boolean isConnected() {
            try {
                int timeout = 5; // maximum time (seconds) to validate connection status 
                return con != null && !con.isClosed() && con.isValid(timeout);
            } catch (SQLException e) { // this should only be thrown when timeout < 0 (which it is not)
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public void close() throws SQLException {
            if (this.isConnected()) {
                this.con.close();
                this.con = null;
            }
        }

        public ResultSet query(String sqlQuery) throws SQLException {
            Statement stmt = this.con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            return rs;
        }
        
        public int execute(String sqlQuery) throws SQLException {
            Statement stmt = this.con.createStatement();
            return stmt.executeUpdate(sqlQuery);
        }
    }
}
