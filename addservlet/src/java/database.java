import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class database {
    public static Connection connect()
    {
        Connection con=null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
        } catch(ClassNotFoundException | SQLException e){
            System.err.println(e);
        }
        return con;
    }
    
}
