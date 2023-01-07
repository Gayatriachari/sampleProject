package in.ineuron.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = { "/reg" }, 
		initParams = { 
				@WebInitParam(name = "url", value = "jdbc:mysql:///enterprice_java"), 
				@WebInitParam(name = "user", value = "root"), 
				@WebInitParam(name = "password", value = "Achari@2001")
		})
public class RegisterServlet extends HttpServlet {
	
	private Connection connection=null;
	
	private static final long serialVersionUID = 1L;

    static {
    	System.out.println("servlet Loading...");
    }
    public RegisterServlet(){
    	System.out.println("instantiation...");
    } 
    public void init() {
    	System.out.println("initialization..."); 
    	
    	ServletConfig config=getServletConfig();
    	
    	String url=config.getInitParameter("url");
    	String user=config.getInitParameter("user");
    	String password=config.getInitParameter("password"); 
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url, user,password);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 
    
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. Get the data from request object
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String city=request.getParameter("city");
		String country=request.getParameter("country");
		
		
		//2. Prepare a response and sent the data to database
		
		String sqlInsertQuery="insert into students(`name`,`email`,`city`,`country`) values(?,?,?,?)";
		PreparedStatement pstmt=null;
		int rowCount=0;
		
		try {
			if(connection!=null) {
			   pstmt=connection.prepareStatement(sqlInsertQuery);
			}
			if(pstmt!=null) {
				pstmt.setString(1, name);
				pstmt.setString(2, email);
				pstmt.setString(3, city);
				pstmt.setString(4, country);
				
				rowCount=pstmt.executeUpdate();
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//3.prepare a response and send it to the end user
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.println("<html><head><title>Output</title></head>");
		out.println("<body>");
		    if(rowCount==1) {
		    	out.println("<h1 style='color:green; text-align:center'>REGISTRATION SUCCESS</h1>");
		    }else {
		    	out.println("<h1 style='color:red;text-align:center'>REGISTRACTION FAILED</h1>");
		        out.println("<a style='text-align:center' href='./register.html'>register again</a>");
		    }
		
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

}
