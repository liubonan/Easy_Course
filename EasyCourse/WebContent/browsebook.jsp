<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="server.*" import="model.*" import="utility.*" import="java.util.*"%>
<% User u = (User)session.getAttribute("login");
List<Textbook> tb_list = (List<Textbook>)session.getAttribute("tbs");
WebHelper wh =new WebHelper();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Browse Book</title>
<link href="style/style.css" type="text/css" rel="stylesheet" />
<link href="style/singlepage.css" type="text/css" rel="stylesheet" />
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
            <li class="active"><a href="/EasyCourse/BrowseBookServlet">Textbook</a></li>
            <li><a href="/EasyCourse/BrowseUnivServlet">University</a></li>
        </ul>
    </container_nav>
    <div class="clear"></div>
    <div class="container_content">
    <div class="blank30"></div>
        <div class="grid_620 min_600">
            <div class="img_grid_left">
            	<%if(tb_list.size() != 0){ %>
            	<h3>All textbooks</h3>
                <div class="blank10"></div>
                
                <%int size_y=(int)tb_list.size()/6; %>
                <%int remainder=tb_list.size()%6; %>
                <%for (int j=0;j<size_y;j++){ %>
                <ol class="img_grid">
                	<%for(int i = 0;i < 6;i++){ %>
                	<li>
                    	<div class="img_grid_container">
                    		<a href="/EasyCourse/TextbookServlet?isbn=<%=tb_list.get(j*6+i).getTbISBN()%>"><img src="<%=wh.getImage(tb_list.get(j*6+i).getTbISBN())%>"/></a>
                    	</div>
                    	<div class="blank5"></div>
                    	<p><a href="/EasyCourse/TextbookServlet?isbn=<%=tb_list.get(j*6+i).getTbISBN()%>"><%=tb_list.get(j*6+i).getTbtitle() %></a></p>
                    </li>
                	<%} %>
                </ol>  
                <div class="blank10"></div>
                <%} %>
                <%if(remainder!=0){ %>  
                <ol class="img_grid">
                	<%for(int i = 0;i < remainder;i++){ %>
                	<li>
                    	<div class="img_grid_container">
                    		<a href="/EasyCourse/TextbookServlet?isbn=<%=tb_list.get(size_y*6+i).getTbISBN()%>"><img src="<%=wh.getImage(tb_list.get(size_y*6+i).getTbISBN())%>"/></a>
                    	</div>
                    	<div class="blank5"></div>
                    	<p><a href="/EasyCourse/TextbookServlet?isbn=<%=tb_list.get(size_y*6+i).getTbISBN()%>"><%=tb_list.get(size_y*6+i).getTbtitle() %></a></p>
                    </li>
                	<%} %>
                </ol>
                <%} %>        
                <%}else{ %>
                <h3>There is no textbooks now.</h3>
                <%} %>
            </div>
            <div class="blank30"></div>
        </div>
        <div class="grid_300">
        	<div class="blank30"></div>
        	<div class="blank20"></div>
        	<p class="link_blue"><a href="/EasyCourse/addbook.jsp">+&nbsp;add new book</a></p>
        </div>
        <div class="blank30"></div>
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