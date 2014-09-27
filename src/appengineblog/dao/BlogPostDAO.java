package appengineblog.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import appengineblog.entity.BlogPost;
import appengineblog.services.PMF;

import com.google.appengine.api.users.User;


public enum BlogPostDAO {
	INSTANCE;
	public List<BlogPost> getBlogPosts(){
		List<BlogPost> blogPosts;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(BlogPost.class);
		try{
			blogPosts = (List<BlogPost>) query.execute();
		}
		finally{
			pm.close();
		}
		return blogPosts;
	}
	
	public void addBlogPost(String title, String body, User user, Date date){
		
		BlogPost newPost = new BlogPost(title, body, user, date);
		synchronized(this){
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try{
				pm.makePersistent(newPost);
			}
			finally{
				pm.close();
			}
		}

	}
}
