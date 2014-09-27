package appengineblog;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import appengineblog.dao.BlogPostDAO;

import java.util.Date;

public class MakePostServlet extends HttpServlet{

	private static final Logger _logger = 
			Logger.getLogger(MakePostServlet.class.getName());
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		_logger.info("recieved post with title: "+title+" and body: "+ body);
		try{
			if(title == null || title.isEmpty()) throw new Exception("Must enter title!");
			if(body == null || body.isEmpty()) throw new Exception("Must enter body!");
			
			System.out.println();
			System.out.println(title);
			System.out.println();
			
	        UserService userService = UserServiceFactory.getUserService();
	        User user = userService.getCurrentUser();
			Date date = new Date();
			
			BlogPostDAO.INSTANCE.addBlogPost(title, body, user, date);
			resp.sendRedirect("appengineblog");
			
		} catch (Exception e) { 
			
			e.printStackTrace();
			String logMsg = "Exception in processing request: " + e.getMessage(); 
			_logger.log(Level.INFO, logMsg); 
			throw new IOException(logMsg); 
		
		} 
	}	
}