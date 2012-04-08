<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="server.*" import="model.*"%>
<% User u = (User)session.getAttribute("login");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
<link href="style/style.css" type="text/css" rel="stylesheet" />
<link href="style/generalform.css" type="text/css" rel="stylesheet" />
</head>

<body>
<div class="header">
	<div class="container_12">
    	<div class="header_logo">
        	<a href="/EasyCourse/index.jsp"><img src="images/logo.png" /></a>
        </div>
        <%if(u != null) {%>
        <ul class="header_nav">
            <li><a href="/EasyCourse/LogoutServlet">Log out</a></li>
            <li><a href="/EasyCourse/updateinfo.jsp"><%=u.getUnickname() %></a></li>
        </ul>
        <%} else { %>
        <div class="no_logged_in">
            <form class="header_login" action="/EasyCourse/LoginServlet" method="post">
            	<fieldset class="login_email">
                    <input type="text" placeholder="Email" name="Email"/>
                </fieldset>
                <fieldset class="login_pass">
                    <input type="password" placeholder="Password" name="Password"/>
                </fieldset>
                <fieldset class="login_submit">
                    <input type="submit"  class="blue" value="Log In" />
                </fieldset>
            </form>
            <div class="no_logged_in_link">
                <a href="/EasyCourse/signup.jsp">Join Us Now!</a>
            </div>
        </div>
        <%} %>
        
    </div>
	
</div>
<div class="header_border_bottom"></div>

<div class="container_12">
	<container_nav role="navigation">
    	<ul>
        	<li><a href="/EasyCourse/BrowseCourseServlet">Course</a></li>
            <li><a href="/EasyCourse/BrowseStaffServlet">Teaching Staff</a></li>
            <li><a href="/EasyCourse/BrowseBookServlet">Textbook</a></li>
            <li><a href="/EasyCourse/BrowseUnivServlet">University</a></li>
        </ul>
    </container_nav>
    <div class="clear"></div>
    <div class="container_content">
        <div class="grid_940">
        	<div class="formcontainer">
            	<h3 class="text_red">Error!</h3>
                <p><span><%=(String)session.getAttribute("ErrorMessage") %></span></p>
                <br />
                <p><a href="/EasyCourse/index.jsp">Go back!</a></p>
            </div>
        </div>
    </div>
    
</div>

<div class="blank30"></div>
<div class="container_12">
    <footer role="contentinfo">
        <nav role="navigation">
            <ul>
            	<li>Contact Us:</li>
                <li><a href="http://www.liubonan.info/">Bonan Liu</a></li>
                <li><a href="http://www.muzigao.com">Muzi Gao</a></li>
            </ul>
        </nav>
        <p>Copyright &copy; 2012 All rights reserved.</p>
    </footer>
</div>
</body>
</html>