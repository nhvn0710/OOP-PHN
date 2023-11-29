package oop.dictionary.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * connect JDBC.
 */
public class ConnectJDBC {
    private String hostName = "localhost:3306";
    private String dbName = "dictionarydb";
    private String username = "root";
    private String password = "PhucDSK27122004";

    private String connectionURL = "jdbc:mysql://" + hostName + "/" + dbName;

    public Connection connect(){
        //Tạo đối tượng Connection
        Connection conn = null;
        
        try {
      //      DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            conn = DriverManager.getConnection(connectionURL, username, password);
            System.out.println("Kết nối thành công");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
