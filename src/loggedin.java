

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gargoylesoftware.htmlunit.javascript.host.fetch.Response;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;

/**
 * Servlet implementation class loggedin
 */
public class loggedin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loggedin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user=request.getParameter("user").toString();
		//String pass=request.getParameter("pass").toString();
		ServletContext context=getServletContext();  
		context.setAttribute("user",user);// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user=request.getParameter("user").toString();
		String pass=request.getParameter("pass").toString();
		HttpSession sess = request.getSession(true);
		 sess.setAttribute("user",user);
		 //HttpSession sess = request.getSession(true);
		 //sess.setAttribute("user",user);
		if(user.equals("")|| pass.equals("")){
			response.setContentType("text/html");
			   PrintWriter out = response.getWriter();
			   out.println("<script type=\"text/javascript\">");
			   out.println("alert('Please fill in required details!');");
			   out.println("location='login.html';");
			   out.println("</script>");
		}
		else{
		try {
		Class.forName("com.mysql.jdbc.Driver");
		final String USER = "root";
	      final String PASS = "snu@123";
	      final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	      final String DB_URL="jdbc:mysql://localhost/comfortshopping";
        // Open a connection
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        String query="Select * from user where username='" + user + "' and password='"+pass+"'";
        ResultSet rs = stmt.executeQuery(query);
        if ( rs.next()){
        	request.setAttribute("user",user);
			RequestDispatcher req = request.getRequestDispatcher("home.jsp");
			req.forward(request, response);
			
			}
		  else{
			  response.setContentType("text/html");
			   PrintWriter out = response.getWriter();
			   out.println("<script type=\"text/javascript\">");
			   out.println("alert('User or password incorrect');");
			   out.println("location='login.html';");
			   out.println("</script>");
			   
	 
	}
        
		} catch (SQLException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
	}}
