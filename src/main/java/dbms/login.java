package dbms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/log")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("username");
		String pass=request.getParameter("pass");
		String url="jdbc:mysql://localhost:3306/gym";
		String user="root";
		String password="1234";
		try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection
            Connection con = DriverManager.getConnection(url, user, password);
            // Prepare SQL query
            String query = "SELECT * FROM signin WHERE name = ?";
            PreparedStatement s = con.prepareStatement(query);
            s.setString(1, name);
            // Execute the query
            ResultSet rs = s.executeQuery();
            // Check if the name is present in the database
            if (rs.next()) {
                // User is valid, forward to index.html
                response.sendRedirect("index.html");
            } else {
                // User is not valid
                out.println("<h1>You are not a valid user</h1>");
            }
			rs.close();
			s.close();
			con.close();
			out.close();
		}
		catch(Exception ex) {
			out.println(ex.getMessage());
		}
}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
