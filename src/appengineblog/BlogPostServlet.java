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
import appengineblog.entity.BlogPost;

import java.util.Date;

public class BlogPostServlet extends HttpServlet{

	private static final Logger _logger = 
			Logger.getLogger(BlogPostServlet.class.getName());
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		try{
			if(title == null || title.isEmpty()) throw new Exception("Must enter title!");
			if(body == null || body.isEmpty()) throw new Exception("Must enter body!");
			
	        UserService userService = UserServiceFactory.getUserService();
	        User user = userService.getCurrentUser();
			Date date = new Date();
			
			BlogPostDAO.INSTANCE.addBlogPost(title, body, user, date);
			resp.sendRedirect("appengineblog.jsp");
			
		} catch (Exception e) { 
			
			e.printStackTrace();
			String logMsg = "Exception in processing request: " + e.getMessage(); 
			_logger.log(Level.INFO, logMsg); 
			throw new IOException(logMsg); 
		
		} 
	}	
}