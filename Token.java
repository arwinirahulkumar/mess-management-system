import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Token extends HttpServlet
{	
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		int token=0,flag=0;
		PrintWriter out=response.getWriter();
		String roll=request.getParameter("ROLL");
		try
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");			Statement st=con.createStatement();
			ResultSet rs1=st.executeQuery("select roll from students");
			while(rs1.next())
			{
			        if(rs1.getString(1).equals(roll))
			        {
					flag=1;
					break;
			        }	
			}
			if(flag==1){
			PreparedStatement pst1=con.prepareStatement("select token from student where sid=?");
			pst1.setString(1,roll);
			ResultSet rs=pst1.executeQuery();
			while(rs.next())
			{
				token=rs.getInt(1)+1;
		PreparedStatement pst2=con.prepareStatement("UPDATE student SET token =? WHERE sid=?");
				pst2.setInt(1,token);
				pst2.setString(2,roll);
				pst2.executeUpdate();
				out.print("<body bgcolor=green>");
				out.print("<center>");
				out.print("<h1>MESS MANAGEMENT SYSTEM</h1>");
				out.print("<br>");
				out.print("<br>");
				out.print("<hr>");
				out.print("<br>");
				out.print("</center>");
				out.print("<center>");
				out.print("Token generated for :"+roll);
				out.print("</center>");
				out.print("</body>");	
			}
			}
			else
			{
				out.print("<body bgcolor=green>");
				out.print("<form action=Register.html>");
				out.print("<center>");
				out.print("<h1>MESS MANAGEMENT SYSTEM</h1>");
				out.print("<br>");
				out.print("<br>");
				out.print("<hr>");
				out.print("<br>");
				out.print("</center>");
				out.print("<center>");
				out.print("Do Register To Have Token:");
				out.print("<input type=submit value=REGISTER>");
				out.print("</center>");
				out.print("</form>");
				out.print("</body>");
			}			
		}
		catch(Exception ex)
		{
			
		}
	}
}
