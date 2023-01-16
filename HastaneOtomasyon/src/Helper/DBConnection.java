package Helper;
import java.sql.*;

public class DBConnection {
      Connection c = null;
      
      public DBConnection() {}
      
      public Connection conDb() {
    	  try {
			this.c = DriverManager.getConnection("jdbc:mysql://localhost:3308/deneme","root","baran");
			return c;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	  return c;
      }
}

