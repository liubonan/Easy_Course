<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*" import="server.*" import="model.*" import="utility.*" import="java.text.DecimalFormat" import="java.text.NumberFormat"%>
<% User u = (User)session.getAttribute("login");%>
<% Textbook tb = (Textbook)session.getAttribute("textbook");
DecimalFormat format = (DecimalFormat) NumberFormat.getInstance();
format.applyPattern("0.00");
WebHelper wh = new WebHelper();
List<TextbookComment> tc_list = (LinkedList<TextbookComment>)session.getAttribute("tcs");
List<Course> c_list = (LinkedList<Course>)session.getAttribute("cs");
List<User> u_list = (LinkedList<User>)session.getAttribute("us");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Textbook</title>
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
        <div class="grid_620">
        	<div class="info">
            	<h3><%=tb.getTbtitle() %></h3>
                <div class="blank10"></div>
            	<img src="<%=wh.getImage(tb.getTbISBN())%>"/>
                <ul>
                	<li><b>Author:</b>&nbsp;&nbsp;&nbsp;&nbsp;<a href="https://www.google.com/#q=<%=tb.getTbauthor() %>"><%=tb.getTbauthor() %></a></li>
                    <li><b>Publisher:</b>&nbsp;&nbsp;&nbsp;&nbsp;<a href="https://www.google.com/#q=<%=tb.getTbpublisher() %>"><%=tb.getTbpublisher() %></a></li>
                    <li><b>Price:</b>&nbsp;&nbsp;&nbsp;&nbsp;<span><%=format.format(tb.getTbprice()) %></span></li>
                    <li><b>ISBN:</b>&nbsp;&nbsp;&nbsp;&nbsp;<span><%=wh.ISBN1310(tb.getTbISBN()) %> / <%=tb.getTbISBN()%></span></li>
                </ul>
            </div>
            <div class="blank20"></div>
            <ol class="comments">
            	<li>
                	<h3>Comments</h3>
                </li>
           		<%if(tc_list.size() != 0) {%>
           		<%for(int i =0;i < tc_list.size();i++){ %>
           		<li>
                	<h3><a><%=tc_list.get(i).getBctitle() %></a></h3>
                    <p><%=tc_list.get(i).getBccomment() %>
                    	<span class="meta">
                        	<span><i>By</i> <a href="#"><%=tc_list.get(i).getUnickname() %></a></span>,
                            <span><%=tc_list.get(i).getBctime().toString() %></span>
                        </span>     
                    </p>
                </li>
                <%} %>
               
                <%}else{ %>
                <li>
                	<h3><a>There is no comments now.</a></h3>                    
                </li>
                <%} %>
            </ol>
            <div class="blank20"></div>
            <%if(u != null){ %>
            <form class="create_comm" action="/EasyCourse/AddTextbookComment" method="post">
            	<h3>Add My Comments</h3>
            	<fieldset>
                	<label for="title">Title</label>
                    <input type="text" id="title" class="create_comm_text" name="ctitle"/>
                    <p class="create_comm_help">Title is required.</p>
                </fieldset>
                <fieldset>
                	<label for="comment">Comment</label>
                    <textarea name="comment"></textarea>
                    <p class="create_comm_help">Comment &lt; 250 words.</p>
                </fieldset>
                <fieldset>
                	<input type="submit" value="Add Comment" class="green"/>
                </fieldset>
            </form>
            <%} %>
            <div class="blank30"></div>
        </div>
        <div class="grid_300">
        	<div>
            	<%if (c_list.size() != 0){ %>
            	<h3>This book is used by</h3>
                <ol class="img_grid">
                    <%for(int i=0;i < c_list.size();i++){ %>
                	<li>
                    	<!--  a href="#"><img  /></a-->
                        <p><a href="/EasyCourse/CourseServlet?cid=<%=c_list.get(i).getCid() %>"><%=c_list.get(i).getCname() %></a></p>
                    </li>
                    <%} %>
                </ol>
                <%}else{ %>
                <h3>No courses use this book now.</h3>
                <%} %>
                
            </div>
            <div class="blank30"></div>
            <div class="sell_book">
            	<a href="/EasyCourse/sellbook.jsp">Sell my used book!</a>
            </div>
            <div class="blank10"></div>
            <div>
                <%if (u_list.size() != 0){ %>
            	<h3>Buy a used book from</h3>
                <ol class="img_grid">
                	<%for(int i=0;i < u_list.size();i++){ %>
                	<li>
                    	<!--  a href="#"><img  /></a-->
                        <p><a href="/EasyCourse/SellInfoServlet?isbn=<%=tb.getTbISBN() %>&sell_userid=<%=u_list.get(i).getUserid() %>"><%=u_list.get(i).getUnickname() %></a></p>
                    </li>
                    <%} %>                    
                </ol>
                <%}else{ %>
                <h3>No users sell this book now.</h3>
                <%} %>
            </div>
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