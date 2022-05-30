package individualprojectpartb.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenericDetails {

    //methods that handle the conenction to the database
    private final String url = "jdbc:mysql://localhost:3306/individualprojb";
    private final String user = "root";
    private final String password = "DataPapa31@";
    
      protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(GenericDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    protected void closeConnections(ResultSet rs, Statement stmt, Connection conn) {
        try {
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GenericDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void closeConnections(PreparedStatement pstm, Connection conn) {
        try {
            pstm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GenericDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
