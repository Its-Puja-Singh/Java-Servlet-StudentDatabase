import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.sql.*;

public class studentservlet extends HttpServlet
{
    String n, a, c;
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()) 
        {
            n=request.getParameter("txtname");
            a=request.getParameter("txtaddress");
            c=request.getParameter("txtcourse");
            try
            {
                Class forName = Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
                PreparedStatement ps = conn.prepareStatement("Insert into student values(?,?,?)");
                ps.setString(1, n);
                ps.setString(2, a);
                ps.setString(3, c);
                int i = ps.executeUpdate();
                out.print("Hey Man!...Your details has been successfully Saved");
            }
            catch(SQLException | ClassNotFoundException e)
            {
               out.println(e);
            }
        } 
    }
}
