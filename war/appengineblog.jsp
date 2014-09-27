<%@ page import="appengineblog.entity.*" %>
<%@	page import="java.util.ArrayList"	%>
 
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
				BlogPost post = (BlogPost) blogPosts.get(i);
		%> 
			<table> 
				<tr>
					<td><%="User: " + post.getUser().getEmail() + " on " + post.getDate().getDate() + " at " + post.getDate().getTime()%></td>
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
		%> 
	</body> 
</html>