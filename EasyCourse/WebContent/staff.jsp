<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="server.*" import="model.*" import="utility.*" import="java.util.*"%>
<%User u = (User)session.getAttribute("login");%>
<%Teachingstaff ts = (Teachingstaff)session.getAttribute("staff"); 
University tsuniv = (University)session.getAttribute("tsuniv");
List<TeachingstaffComment> tsc_list = (LinkedList<TeachingstaffComment>)session.getAttribute("tscs");
List<Course> sc_list = (LinkedList<Course>)session.getAttribute("scs");
String startdate = (String)session.getAttribute("startdate");
String enddate = (String)session.getAttribute("enddate");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teaching Staff</title>
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
            <li class="active"><a href="/EasyCourse/BrowseStaffServlet">Teaching Staff</a></li>
            <li><a href="/EasyCourse/BrowseBookServlet">Textbook</a></li>
            <li><a href="/EasyCourse/BrowseUnivServlet">University</a></li>
        </ul>
    </container_nav>
    <div class="clear"></div>
    <div class="container_content">
    <div class="blank30"></div>
        <div class="grid_620">
        	<div class="info">
            	<h3><%=ts.getTsname() %></h3>
                <div class="blank10"></div>
            	<img src="images/staff.jpg"/>
                <ul>
                	<li><b>Title:</b>&nbsp;&nbsp;&nbsp;&nbsp;<span><%=ts.getTstitle() %></span></li>
                    <li><b>University:</b>&nbsp;&nbsp;&nbsp;&nbsp;<span><span><%=tsuniv.getUnivname() %></span> (<a href="<%=tsuniv.getUnivwebsite() %>"><%=tsuniv.getUnivwebsite() %></a>)</span></li>
                    <li><b>Start Time:</b>&nbsp;&nbsp;&nbsp;&nbsp;</b><span><%=startdate %></span></li>
                    <li><b>End Time:</b>&nbsp;&nbsp;&nbsp;&nbsp;</b><span><%=enddate %></span></li>
                </ul>
            </div>
            <div class="blank20"></div>
            <ol class="comments">
            	<li>
                	<h3>Comments</h3>
                </li>
           		<%if(tsc_list.size() != 0) {%>
           		<%for(int i =0;i < tsc_list.size();i++){ %>
           		<li>
                	<h3><a><%=tsc_list.get(i).getSctitle() %></a></h3>
                    <p><%=tsc_list.get(i).getSccomment() %>
                    	<span class="meta">
                        	<span><i>By</i> <a href="#"><%=tsc_list.get(i).getUnickname() %></a></span>,
                            <span><%=tsc_list.get(i).getSctime().toString() %></span>
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
            <form class="create_comm" action="/EasyCourse/AddStaffComment" method="post">
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
            	<%if (sc_list.size() != 0){ %>
            	<h3>Course</h3>
                <ol class="img_grid">
                    <%for(int i=0;i < sc_list.size();i++){ %>
                	<li>
                    	<!--  a href="#"><img  /></a-->
                        <p><a href="/EasyCourse/CourseServlet?cid=<%=sc_list.get(i).getCid()%>"><%=sc_list.get(i).getCname() %>-<%=sc_list.get(i).getSemester() %></a></p>
                    </li>
                    <%} %>
                </ol>
                <%}else{ %>
                <h3>No courses taught by him/her.</h3>
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