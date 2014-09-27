package appengineblog;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appengineblog.dao.BlogPostDAO;
import appengineblog.entity.BlogPost;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class BlogPostsGetServlet extends HttpServlet{
	
	private static final Logger _logger = 
			Logger.getLogger(BlogPostsGetServlet.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws  IOException{
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		List<BlogPost> blogPostsList = BlogPostDAO.INSTANCE.getBlogPosts();
		ArrayList<BlogPost> blogPosts = new ArrayList<BlogPost>(blogPostsList);
		req.getSession().setAttribute("blogPosts", blogPosts);
	}	
}