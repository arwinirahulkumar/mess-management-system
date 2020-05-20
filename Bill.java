import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Bill extends HttpServlet
{
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		int flag=0;
		PrintWriter out=response.getWriter();
		String roll=request.getParameter("ROLL");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select sid,token from students");
			while(rs.next())
			{
			     if(rs.getString(1).equals(roll))
			     {
				out.print("<body bgcolor=green>");
				out.print("<center>");
				out.print("Hostel Management System");
				out.print("<br>");
				out.print("<br>");
				out.print("<hr>");
				out.print("<br>");
				out.print("your bill is:"+rs.getInt(2)*20);
				out.print("</center>");
				out.print("</body>");
				flag=1;break;	
			     }		
			}
			if(flag==0)
			{
                                               
				out.print("<body bgcolor=green>");
				out.print("<form action=Register.html>");
				out.print("<center>");
				out.print("Hostel Management System");
				out.print("<br>");
				out.print("<br>");
				out.print("<hr>");
				out.print("<br>");
				out.print("Do Register or Enter correct Roll No :");
				out.print("<input type=submit value=REGISTER>");
				out.print("</center>");
				out.print("</form>");
				out.print("</body>");
				out.print("  ");
			}
			
		}
		catch(Exception ex)
		{
			out.print(ex);
		}
	} 
}
