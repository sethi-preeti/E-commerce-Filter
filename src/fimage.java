

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


public class fimage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected ArrayList processRequest1(HttpServletRequest request, 
            HttpServletResponse response) throws IOException{
		String category = request.getParameter("category" );
		String url = "https://www.jabong.com/women/clothing/"+category+"/";
		String url2 ="";
		if(category.equals("tops-tees-shirts")){
			url2 = "http://www.shopclues.com/womens-western-wear-tops.html";
		}
		else url2 = "http://www.shopclues.com/womens-western-wear-dresses.html";
		   Document document = Jsoup.connect(url).get();
		   Document document2 = Jsoup.connect(url2).get();
		 
			 ArrayList<String> imageList=new ArrayList<String>();
			 Elements img = document.select("img");
			 
			
			 ArrayList<String> imageList2=new ArrayList<String>();
				//ArrayList<String> imageList3=new ArrayList<String>();
				 Elements img2 = document2.select("img");
				 //Elements rdr = document2.select(".row a");
				 
				 try{
				 for (Element el : img2) {
	            	 String a = el.attr("data-img");
	            	 if(!a.isEmpty())
		            	 imageList2.add(a);
		            } 
				
				 }catch(Exception e){}
				
				 
	       
		 for(int i=0;i<imageList2.size();i=i+1){
		     	imageList.add(imageList2.get(i));}
		 for (Element el : img) {
				String a = el.attr("data-img-config");
	          	 String[] params = a.split(",");
	          	 String baseLength = params[params.length-1];
	               try{
	          	 String value = baseLength.substring(13);
	               String source = value.substring(0,value.lastIndexOf("\""));
	               String src = source+".jpg";
	              imageList.add(src);
	            }catch(StringIndexOutOfBoundsException e){
	              	 System.out.println("Error");
	               }
	              }
		 
			return imageList;
	}
	protected ArrayList processRequest2(HttpServletRequest request, 
            HttpServletResponse response) throws IOException{
		String category = request.getParameter("category" );
		String url = "https://www.jabong.com/women/clothing/"+category+"/";
		String url2 ="";
		if(category.equals("tops-tees-shirts")){
			url2 = "http://www.shopclues.com/womens-western-wear-tops.html";
		}
		else url2 = "http://www.shopclues.com/womens-western-wear-dresses.html";
	    Document document = Jsoup.connect(url).get();
	    Document document2 = Jsoup.connect(url2).get();
	   String discount=document.select(".discount").text();
		 String[] discountList=discount.split(" ");
		 String discount2 = document2.select(".prd_discount").text();
	     String[] discountList2 = discount2.split(" ");
	     ArrayList<Integer> dis = new ArrayList<Integer>();
		 
		 for(int i=0;i<discountList2.length;i=i+2){
				dis.add(Integer.parseInt(discountList2[i].substring(0, 2)));
			 }
			 for(int i=0;i<discountList.length;i++){
				 String act_dis=discountList[i].substring(2, 4);
				 dis.add(Integer.parseInt(act_dis));
			 }
				 
				 String sortType = request.getParameter("a" );
		
		    String price=document.select(".standard-price").text();
			 String[] priceList=price.split(" ");
			
			return dis;
	}
	protected ArrayList processRequest3(HttpServletRequest request, 
            HttpServletResponse response) throws IOException{
		String category = request.getParameter("category" );
		String url = "https://www.jabong.com/women/clothing/"+category+"/";
		String url2 ="";
		if(category.equals("tops-tees-shirts")){
			url2 = "http://www.shopclues.com/womens-western-wear-tops.html";
		}
		else url2 = "http://www.shopclues.com/womens-western-wear-dresses.html";
		   Document document = Jsoup.connect(url).get();
		   Document document2 = Jsoup.connect(url2).get();
			 ArrayList<Integer> pList=new ArrayList<Integer>();
			 String price=document.select(".standard-price").text();
			 String[] priceList=price.split(" ");
			 String price2 = document2.select(".p_price").text();
		     String[] priceList2 = price2.split(" ");
			 
			 for(int i=0; i<priceList2.length; i=i+1) {
			    	pList.add(Integer.parseInt(priceList2[i].substring(3)));
			          }
			 for(int i=1;i<priceList.length;i=i+2){
			 pList.add(Integer.parseInt(priceList[i]));
		 }
			 //ArrayList<String> pfinal = new ArrayList<String>();
			 /*String sortType = request.getParameter("a");
			 if(sortType.equals("price"))
				Collections.sort(pList);*/
			 
			return pList;
	}
	protected ArrayList processRequest4(HttpServletRequest request, 
            HttpServletResponse response) throws IOException{
		String category = request.getParameter("category" );
		String url = "https://www.jabong.com/women/clothing/"+category+"/";
		String url2 ="";
		if(category.equals("tops-tees-shirts")){
			url2 = "http://www.shopclues.com/womens-western-wear-tops.html";
		}
		else url2 = "http://www.shopclues.com/womens-western-wear-dresses.html";
		Document document = Jsoup.connect(url).get();
		   Document document2 = Jsoup.connect(url2).get();
		ArrayList<String> img_srcs = new ArrayList<String>();
		ArrayList<String> imageList4=new ArrayList<String>();
		Elements rdr = document.select("a");
		 Elements rdr2 = document2.select(".row a");
	  

	    for (Element el : rdr2) {
            String a = el.attr("href");
         	 if(!a.isEmpty())
         	 imageList4.add(a);}
	    for(int i=1;i<imageList4.size();i=i+2){
	     	img_srcs.add(imageList4.get(i));}
	    //System.out.println(rdr.size());
	    for (Element el : rdr) {
                 String a = el.attr("data-original-href");
	            	 if(!a.isEmpty())                             
	            	 {
	            		 String fnlsrc=url+a.substring(1);    
	            		 img_srcs.add(fnlsrc);
	            	}
	             }
	return img_srcs;
	}
    public fimage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest1(request, response);
		processRequest2(request, response);
		processRequest3(request, response);
		processRequest4(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sess = request.getSession(false);
		 String user = sess.getAttribute("user").toString();
		String category = request.getParameter("category" );
		ArrayList<String> il = new ArrayList<String>();
		il = processRequest1(request, response);
		request.setAttribute("url",il);
		ArrayList<Integer> dis = new ArrayList<Integer>();
		dis = processRequest2(request, response);
		request.setAttribute("dis",dis);
		ArrayList<Integer> price = new ArrayList<Integer>();
		price = processRequest3(request, response);
		request.setAttribute("price",price);
		ArrayList<String> web = new ArrayList<String>();
		web = processRequest4(request, response);
		request.setAttribute("web",web);
		request.setAttribute("user",user);
		RequestDispatcher req = request.getRequestDispatcher("img2.jsp");
		req.forward(request, response);
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	      final String DB_URL="jdbc:mysql://localhost/comfortshopping";

	      //  Database credentials
	      final String USER = "root";
	      final String PASS = "snu@123";

	      // Set response content type
	      response.setContentType("text/html");
	      //PrintWriter out = response.getWriter();
	      //String title = "Database Result";
	      try {
	         // Register JDBC driver
	         Class.forName("com.mysql.jdbc.Driver");

	         // Open a connection
	         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

	         // Execute SQL query
	         Statement stmt = conn.createStatement();
	         String del_query="truncate table fclothes";
	         stmt.execute(del_query);
	         String query;
	         for(int i=0;i<dis.size()-1;i++){
	         query = " insert into fclothes (image_src, price, discount,rdr_link)"
	        	        + " values (?, ?, ?, ?)";

	        	      // create the mysql insert preparedstatement
	        	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	        	      preparedStmt.setString (1, il.get(i));
	        	      preparedStmt.setInt(2, price.get(i));
	        	      preparedStmt.setInt(3, dis.get(i));
	        	      preparedStmt.setString(4,web.get(i));
	        	      preparedStmt.execute();
	         }

	        	      // execute the preparedstatement
	         stmt.close();
	         conn.close();
	      } catch(SQLException se) {
	         //Handle errors for JDBC
	         se.printStackTrace();
	      } catch(Exception e) {
	         //Handle errors for Class.forName
	         e.printStackTrace();
	      } 
				
		
	

		
	}

}
