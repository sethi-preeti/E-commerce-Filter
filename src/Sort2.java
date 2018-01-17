

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class sort
 */
public class Sort2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sort2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession(false);
		 String user = sess.getAttribute("user").toString();
		ArrayList<String> url = new ArrayList<String>(); 
		ArrayList<Integer> priceList = new ArrayList<Integer>(); 
		ArrayList<Integer> discList = new ArrayList<Integer>(); 
		ArrayList<String> webList = new ArrayList<String>(); 
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	      final String DB_URL="jdbc:mysql://localhost/comfortshopping";

	      //  Database credentials
	      final String USER = "root";
	      final String PASS = "snu@123";
	      try {
	    	  String filter="";
	  		if(request.getParameter("a")!=null){
	  		filter = request.getParameter("a");}
	  		
		         // Register JDBC driver
		         Class.forName("com.mysql.jdbc.Driver");

		         // Open a connection
		         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		         Statement stmt = conn.createStatement();
		         if(filter.equals("discount")){
		         String query="SELECT * from fclothes order by discount DESC";
		         ResultSet rs = stmt.executeQuery(query);
		         while (rs.next())
		         {
		           url.add(rs.getString("image_src"));
		           priceList.add(rs.getInt("price"));
		           discList.add(rs.getInt("discount"));
		           webList.add(rs.getString("rdr_link"));
		         }
		         rs.close();
		         }
		         else if(filter.equals("price")){
		         String query2="SELECT * from fclothes order by price";
		         ResultSet rs2 = stmt.executeQuery(query2);
		         while (rs2.next())
		         {
		           url.add(rs2.getString("image_src"));
		           priceList.add(rs2.getInt("price"));
		           discList.add(rs2.getInt("discount"));
		           webList.add(rs2.getString("rdr_link"));
		         }
		         rs2.close();
		         }
		            
	                conn.close();
		           
	                
	      }
	      
	      catch(Exception e){
	    	  
	      }
	    request.setAttribute("url",url);
		request.setAttribute("dis",discList);
		request.setAttribute("price",priceList);
		request.setAttribute("web",webList);
		request.setAttribute("user",user);
		RequestDispatcher req = request.getRequestDispatcher("Sort2.jsp");
		req.forward(request, response);
	}

}
