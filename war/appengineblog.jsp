<%@ page import="appengineblog.entity.*" %>
<%@	page import="java.util.ArrayList"	%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
 
 
<html> 
	<head> 
		<meta http-equiv="content-type" content="text/html; charset=UTF-8"> 
		<title>App Engine Blog</title> 
	</head> 
	<body> 
		<h1>Welcome to the Blog!</h1><hr/> 
		<% 
		ArrayList<BlogPost> blogPosts = new ArrayList<BlogPost>();
		blogPosts = (ArrayList<BlogPost>) session.getAttribute("blogPosts");
		
		if (blogPosts != null){ 
			int numPosts = (blogPosts.size()<5) ? blogPosts.size():5;
			for(int i=0; i < numPosts; i++){
				BlogPost post = blogPosts.get(blogPosts.size()-1-i);
		%> 
			<table> 
				<tr>
					<td><%="User: " + post.getUser() + " on " + post.getDate().getDate() + " at " + post.getDate().getTime()%></td>
				</tr>		
				<tr> 
					<td style="font-weight:bold;">Title:</td> 
					<td><%=post.getTitle() %></td> 
				</tr> 
				<tr> 
					<td style="font-weight:bold;">Student Name:</td> 
					<td><%=post.getPost() %></td> 
				</tr> 
				
			</table><hr/>	 
		<% 
			}
		} else { 
		%> 
			<h3>There are no blog posts at the moment</h3> 
		<% 
		} 
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if(user == null){
        %>	
			<h3>You must <a href=<% userService.createLoginURL(request.getRequestURI()); %>> login</a> to make post</h3>
        
        <%} else{ %>
        
        	<h3>Click <a href="/makepost.html">here</a> to make post</h3>
        	
        <% 
        }
		%> 
	</body> 
</html>