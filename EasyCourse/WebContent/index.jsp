<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="server.*" import="model.*" import="utility.*" import="java.util.*"%>
<% User u = (User)session.getAttribute("login");
IndexHelper ih=new IndexHelper();
List<Course> cl = ih.getCourses();
List<Textbook> bl = ih.getTextbook();
WebHelper wh = new WebHelper();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Easy Course</title>
<link href="style/style.css" type="text/css" rel="stylesheet" />
<link href="style/listform.css" type="text/css" rel="stylesheet" />
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
            <li><a href="/EasyCourse/BrowseBookServlet">Textbook</a></li>
            <li><a href="/EasyCourse/BrowseUnivServlet">University</a></li>
        </ul>
    </container_nav>
    <div class="clear"></div>
    <div class="container_content">
    <div class="blank10"></div>
        <div class="grid_620 min_600">
            <div class="formcontainer" id="index_list">
            	<h3>Recommended Courses</h3>
            	<%if(cl.size() != 0){ %>
                <form class="list_form">
                <%for(int i=0;i<cl.size() && i<5;i++){ %>
            	<fieldset>
                	<label for="name"><%=cl.get(i).getCname() %></label>
                	<p><%=cl.get(i).getCdescription() %></p>
                	<p class="link"><a href="/EasyCourse/CourseServlet?cid=<%=cl.get(i).getCid() %>">more&gt;&gt;</a></p>
                </fieldset>
                <%} %>                
            	</form>
            	<%}else{ %>
            	There is no courses now.
            	<%} %>
            </div>
            <div class="blank20"></div>
            <div class="img_grid_left" id="index_grid">
            	<h3>Hottest Textbooks</h3>
            	<div class="blank10"></div>
            	<%if(bl.size() != 0){ %>

                <ol class="img_grid">
                <%for(int i=0;i<6;i++){ %>
                	<li>
                		<div class="img_grid_container">
                			<a href="/EasyCourse/TextbookServlet?isbn=<%=bl.get(i).getTbISBN()%>"><img src="<%=wh.getImage(bl.get(i).getTbISBN())%>"/></a>
                		</div>
                		<div class="blank5"></div>
                        <p><a href="/EasyCourse/TextbookServlet?isbn=<%=bl.get(i).getTbISBN()%>"><%=bl.get(i).getTbtitle() %></a></p>
                    </li>
                <%} %>                  
                </ol>
				<div class="blank10"></div>
				<%if (bl.size()>=6){ %>
				<ol class="img_grid">
				<%for(int i=0;i<2 && (i+6)<bl.size();i++){ %>
                	<li>
                		<div class="img_grid_container">
                			<a href="/EasyCourse/TextbookServlet?isbn=<%=bl.get(i+6).getTbISBN()%>"><img src="<%=wh.getImage(bl.get(i+6).getTbISBN())%>"/></a>
                		</div>
                		<div class="blank5"></div>
                        <p><a href="/EasyCourse/TextbookServlet?isbn=<%=bl.get(i+6).getTbISBN()%>"><%=bl.get(i+6).getTbtitle() %></a></p>
                    </li>
                <%} %>   	
				</ol>
				<%} %>
                <%}else{ %>
                There is no textbook now.
                <%} %>
            </div>
            <div class="blank30"></div>
        </div>
        <div class="grid_300">
        	<%if(u != null) {%>
        	<%List<List<Course>> l = ih.getMyCourses(u.getUserid()); %>
        	<div class="formcontainer" id="index_user_list">
            	<h3>My Courses</h3>
                <form class="list_form">
                <fieldset class="list_title">
                	<p><b class="grey">Plan...</b></p>
                </fieldset>
                <%if(l.get(0).size() != 0){ %>
                <%for (int i =0;i < l.get(0).size();i++){ %>
            	<fieldset>
                	<p><a href="/EasyCourse/CourseServlet?cid=<%=l.get(0).get(i).getCid()%>"><%=l.get(0).get(i).getCname() %></a></p>
                </fieldset>
                <%} %>
                <%}else{ %>
                <fieldset>You have no course in plan.</fieldset>
                <%} %>
                <fieldset class="list_title">
                	<p><b class="grey">Is Taking...</b></p>
                </fieldset>
                <%if(l.get(1).size() != 0){ %>
            	<%for (int i =0;i < l.get(1).size();i++){ %>
            	<fieldset>
                	<p><a href="/EasyCourse/CourseServlet?cid=<%=l.get(1).get(i).getCid()%>"><%=l.get(1).get(i).getCname() %></a></p>
                </fieldset>
                <%} %>
                <%}else{ %>
                <fieldset>You are not taking any courses now.</fieldset>
                <%} %>
                <fieldset class="list_title">
                	<p><b class="grey">Completed...</b></p>
                </fieldset>
                <%if(l.get(2).size() != 0){ %>
            	<%for (int i =0;i < l.get(2).size();i++){ %>
            	<fieldset>
                	<p><a href="/EasyCourse/CourseServlet?cid=<%=l.get(2).get(i).getCid()%>"><%=l.get(2).get(i).getCname() %></a></p>
                </fieldset>
                <%} %>
                <%}else{ %>
                <fieldset>You have not completed any courses.</fieldset>
                <%} %>
            	</form>
            </div>
            <%} else {%>
            <div class="formcontainer" id="index_user_list">
            	<img src="images/introduction.png"/>
            </div>
            
            <%} %>
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