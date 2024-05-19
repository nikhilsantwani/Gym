package data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public signin() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");

        // Retrieve form parameters
        String name = request.getParameter("name");
        String password = request.getParameter("pass");
        String aadhar =	request.getParameter("aadhar");
        String mobile = request.getParameter("number");
        String duration = request.getParameter("duration");
        

        // JDBC Connection parameters
        String jdbcUrl = "jdbc:mysql://localhost:3306/gym";
        String User = "root";
        String pass= "1234";
//        
//        if (duration == null) {
//            out.println("Duration field cannot be null.");
//            return; // Stop further processing
//        }

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            Connection conn = DriverManager.getConnection(jdbcUrl,User,pass);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO signin (name,password, aadhar,mobile,duration) VALUES (?,?,?,?,?)");

            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, aadhar);
            ps.setString(4, mobile);
            ps.setString(5, duration);
            
            int status=ps.executeUpdate();
            System.out.print(status);
            ps.close();
            conn.close();
            
            out.println(name); 
            out.println(duration); 
        }
        catch(Exception ex) {
        	out.print(ex);
        	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
