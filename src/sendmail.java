import java.io.*;
import java.net.*;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;

public class sendmail extends HttpServlet {
 
    protected void processRequest(HttpServletRequest request, 
                                  HttpServletResponse response)
                   throws IOException, ServletException {
 
        //final String err = "/filter.html";
        //final String succ = "/filter.html";
 
        String from = "comfshopping@gmail.com";
        String to = request.getParameter("email");
       // String subject = request.getParameter("subject");
        //String message = request.getParameter("message");
        String login = "comfshopping@gmail.com";
        String password = "comfortshopping123";
 
        try {
        Properties props = new Properties();
            props.setProperty("mail.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.starttls.enable", "true");
 
            Authenticator auth = new SMTPAuthenticator(login, password);
 
            Session session = Session.getInstance(props, auth);
 
            MimeMessage msg = new MimeMessage(session);
            msg.setText("You have been Successfully Registered at Comfortshopping." +
            		" Enjoy Shopping with us!");
            msg.setSubject("ComfortShopping Confirmation");
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            Transport.send(msg);
 
        }
        catch (AuthenticationFailedException ex) {
           request.setAttribute("ErrorMessage", "Authentication failed");
        	   
        	 
 
        } catch (AddressException ex) {
           request.setAttribute("ErrorMessage", "Wrong email address");
        	 
         
 
 
        } catch (MessagingException ex) {
            request.setAttribute("ErrorMessage", ex.getMessage());
 
        }
    }
    
 
    private class SMTPAuthenticator extends Authenticator {
 
        private PasswordAuthentication authentication;
 
        public SMTPAuthenticator(String login, String password) {
            authentication = new PasswordAuthentication(login, password);
        }
 
        protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
        }
    }
 
    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response)
                   throws ServletException, IOException {
        processRequest(request, response);
    }
 
    protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response)
                   throws ServletException, IOException {
        processRequest(request, response);
        String name = request.getParameter("name");
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		String email = request.getParameter("email");
		if(name.equals("")||username.equals("")||password.equals("")||email.equals("")){
			response.setContentType("text/html");
			   PrintWriter out = response.getWriter();
			   out.println("<script type=\"text/javascript\">");
			   out.println("alert('Please fill in required details!');");
			   out.println("location='signup.html';");
			   out.println("</script>");
		}
		else{
		  final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	      final String DB_URL="jdbc:mysql://localhost/comfortshopping";
          final String USER = "root";
	      final String PASS = "snu@123";	
		 try {
			Class.forName("com.mysql.jdbc.Driver");
		
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // Execute SQL query
        Statement stmt = conn.createStatement();
        String query;
        query = " insert into user (name, username,password, email)"
       	        + " values (?, ?, ?, ?)";

       	      // create the mysql insert preparedstatement
       	      PreparedStatement preparedStmt = conn.prepareStatement(query);
       	      preparedStmt.setString (1, name);
       	      preparedStmt.setString (2, username);
       	      preparedStmt.setString(3, password);
       	      preparedStmt.setString(4,email);
       	      preparedStmt.execute();
		 } catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    
			RequestDispatcher req = request.getRequestDispatcher("login.html");
			req.forward(request, response);
		}
    }
	}
 
	// TODO Auto-generated method stub

    

