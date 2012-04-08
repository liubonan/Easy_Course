<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="server.*" import="model.*" import="utility.*" import="java.util.*" %>
<%User u = (User)session.getAttribute("login");%>
<%University univ = (University)session.getAttribute("univ"); 
List<Course> c_list = (LinkedList<Course>)session.getAttribute("ucs");
List<User> u_list = (LinkedList<User>)session.getAttribute("uus");
List<Teachingstaff> ts_list=(LinkedList<Teachingstaff>)session.getAttribute("uts");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>University</title>
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
            <li><a href="/EasyCourse/BrowseBookServlet">Textbook</a></li>
            <li class="active"><a href="/EasyCourse/BrowseUnivServlet">University</a></li>
        </ul>
    </container_nav>
    <div class="clear"></div>
    <div class="container_content">
    <div class="blank30"></div>
        <div class="grid_620">
        	<div class="info">
            	<h3><%=univ.getUnivname() %></h3>
                <div class="blank10"></div>
            	<img src="images/univ.jpg" />
                <ul>
                	<li><b>Website:</b>&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=univ.getUnivwebsite()%>"><%=univ.getUnivwebsite()%></a></li>
                </ul>
            </div>
            <div class="blank20"></div>
            <div class="img_grid_left">
            	<%if(c_list.size() != 0){ %>
            	<h3>Courses opened here</h3>
                <ol class="img_grid">
                	<%for(int i=0;i<c_list.size();i++){ %>
                	<li>
                        <p><a href="/EasyCourse/CourseServlet?cid=<%=c_list.get(i).getCid() %>"><%=c_list.get(i).getCname() %></a></p>
                    </li>
                    <%} %>
                </ol>
                <%}else{ %>
                <h3>No Courses opened here.</h3>
                <%} %>
            </div>
            <div class="blank20"></div>
            <div class="img_grid_left">
            	<%if(u_list.size() != 0){ %>
            	<h3>Student enrolled here</h3>
                <ol class="img_grid">
                	<%for(int i=0;i<u_list.size();i++){ %>
                	<li>
                        <p><a href="#"><%=u_list.get(i).getUnickname() %></a></p>
                    </li>
                    <%} %>
                </ol>
                <%}else{ %>
                <h3>No Users enrolled here.</h3>
                <%} %>
            </div>
            <div class="blank20"></div>
            <div class="img_grid_left">
            	<%if(ts_list.size() != 0){ %>
            	<h3>Faculty here</h3>
                <ol class="img_grid">
                	<%for(int i=0;i<ts_list.size();i++){ %>
                	<li>
                		<div class="img_grid_container">
                			<a href="/EasyCourse/StaffServlet?tsid=<%=ts_list.get(i).getTsid() %>"><img src="images/staff.jpg"  /></a>
                		</div>
                    	<div class="blank5"></div>
                        <p><a href="/EasyCourse/StaffServlet?tsid=<%=ts_list.get(i).getTsid() %>"><%=ts_list.get(i).getTsname() %></a></p>
                    </li>
                    <%} %>
                </ol>
                <%}else{ %>
                <h3>No Teaching Staff works here.</h3>
                <%} %>
            </div>
            <div class="blank30"></div>
        </div>
        <div class="grid_300">
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