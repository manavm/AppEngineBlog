package appengineblog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Collections;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appengineblog.dao.BlogPostDAO;
import appengineblog.entity.BlogPost;


public class BlogPostsGetServlet extends HttpServlet{
	
	private static final Logger _logger = 
			Logger.getLogger(BlogPostsGetServlet.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws  IOException{
		
		System.out.println("Inside BlogPostsGetServlet");
	
		List<BlogPost> blogPostsList = BlogPostDAO.INSTANCE.getBlogPosts();
		ArrayList<BlogPost> blogPosts = new ArrayList<BlogPost>(blogPostsList);
		
		
		Collections.sort(blogPosts, new Comparator<BlogPost>() {
			  public int compare(BlogPost p1, BlogPost p2) {
			      return p1.getDate().compareTo(p2.getDate());
			  }
			});
		
//		System.out.println();
//		for(int i=0; i<blogPosts.size(); i++){
//			System.out.println(blogPosts.get(i).getTitle());
//		}
//		System.out.println();
		
		req.getSession().setAttribute("blogPosts", blogPosts);;
		resp.sendRedirect("appengineblog.jsp");
	}	
}