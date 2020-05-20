import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Register extends HttpServlet
{
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		PrintWriter out=response.getWriter();
                       String roll=request.getParameter("ROLL");
		String name=request.getParameter("NAME");
		String gender=request.getParameter("GENDER");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
		response.setContentType("text/html");
			PreparedStatement pst=con.prepareStatement("insert into students values(?,?,?,?)"); 
			pst.setString(1,roll);
			pst.setString(2,name);
			pst.setString(3,gender);
			pst.setInt(4,0);
			pst.executeUpdate();
			//response.sendRedirect("F:\\WT\\PROJECT\\home.html"); 	
			out.print("<html><head><style>{img {display: block;margin-left: auto;margin-right: auto;}}</style></head><body><img src=F:\\WT\\PROJECT\\home.jpg align= center alt='Save food' width=30%/></body></html>");
		}
		catch(Exception ex)
		{
			out.print(ex);
		}
	
}
}
