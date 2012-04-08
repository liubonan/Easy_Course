<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="server.*" import="model.*" import="utility.*" import="java.util.*"%>
<% User u = (User)session.getAttribute("login");
Course c = (Course)session.getAttribute("course");
List<Teachingstaff> ts_list = (LinkedList<Teachingstaff>)session.getAttribute("tss");
List<University> univ_list = (LinkedList<University>)session.getAttribute("univs");
Integer take_flag = (Integer)session.getAttribute("take");
List<Textbook> tb_list = (LinkedList<Textbook>)session.getAttribute("tbs");
WebHelper wh = new WebHelper();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course</title>
<link href="style/style.css" type="text/css" rel="stylesheet" />
<link href="style/singlepage.css" type="text/css" rel="stylesheet" />
<link href="style/generalform.css" type="text/css" rel="stylesheet"/>
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
        	<li class="active"><a href="/EasyCourse/BrowseCourseServlet">Course</a></li>
            <li><a href="/EasyCourse/BrowseStaffServlet">Teaching Staff</a></li>
            <li><a href="/EasyCourse/BrowseBookServlet">Textbook</a></li>
            <li><a href="/EasyCourse/BrowseUnivServlet">University</a></li>
        </ul>
    </container_nav>
    <div class="clear"></div>
    <div class="container_content">
    <div class="blank30"></div>
        <div class="grid_620">
        	<div class="info">
            	<h3><%=c.getCname() %></h3>
                <div class="blank10"></div>
            	<img />
                <ul>
                	<li><b>Taught By:</b>&nbsp;&nbsp;&nbsp;&nbsp;<%if(ts_list.size() != 0){ for(int i =0;i<ts_list.size();i++){%><a href="/EasyCourse/StaffServlet?tsid=<%=ts_list.get(i).getTsid()%>"><p><%=ts_list.get(i).getTsname()%> | <%=ts_list.get(i).getTstitle()%></p></a> <%}}else{ %>No teacher taught this course.<%} %></li>
                    <li><b>Offered By:</b>&nbsp;&nbsp;&nbsp;&nbsp;<%if(univ_list.size() != 0){ for(int i =0;i<univ_list.size();i++){%><a href="<%= univ_list.get(i).getUnivwebsite()%>"><p><%=univ_list.get(i).getUnivname()%> (<%= univ_list.get(i).getUnivwebsite()%>) </p></a> <%}}else{ %>No university open this course.<%} %></li>            
                </ul>
            </div>
            <div class="blank20"></div>
            <%if(u != null){%>
            <div class="mark_course">
            	<h3>Like it <%if(take_flag == 0) {%>- You do not concern this course now.<%} %></h3>
                <form class="mark_status" action="/EasyCourse/UpdateTakingServlet" method="post">
                	<fieldset>
                    	<ul>
                        	<li><label><input type="radio" name="status" value="Plan" <%if(take_flag == 1) {%>checked="checked"<%} %>/> Plan</label></li>
                            <li><label><input type="radio" name="status" value="Is taking" <%if(take_flag == 2) {%>checked="checked"<%} %>/> Is taking</label></li>
                            <li><label><input type="radio" name="status" value="Completed" <%if(take_flag == 3) {%>checked="checked"<%} %>/> Completed</label></li>
                        </ul>
                    </fieldset>
                    <fieldset>
                    	<input type="submit" class="blue" value="Update" />
                    </fieldset>
                </form>
            </div>
            <%} %>
            <div class="blank20"></div>
            <ol class="comments">
            	<li>
                	<h3>Description</h3>
                </li>
           		<li>
                    <p><%=c.getCdescription() %></p>
                </li>
            </ol>
            <div class="blank20"></div>
            <div class="img_grid_left">
            	<h3>Textbook required</h3>
                <ol class="img_grid">
                    <%if(tb_list.size()!=0){ %>
                    <%for(int i=0;i < tb_list.size();i++){ %>
                    <li>
                    	<div class="img_grid_container">
                    		<a href="/EasyCourse/TextbookServlet?isbn=<%=tb_list.get(i).getTbISBN()%>"><img src="<%=wh.getImage(tb_list.get(i).getTbISBN()) %>" /></a>
                    	</div>
                    	<div class="blank5"></div>
                        <p><a href="/EasyCourse/TextbookServlet?isbn=<%=tb_list.get(i).getTbISBN()%>"><%=tb_list.get(i).getTbtitle() %></a></p>
                    </li>
                    <%} %>                    
                    <%}else{ %>
                    <p><a>No textbooks required.</a></p>
                    <%} %>
                </ol>
            </div>
            <div class="blank20"></div>
            <div class="img_grid_left">
            	<h3>Add a textbook for this course</h3>
	        	<form class="addbook" action="/EasyCourse/CourseRequireBookServlet" method="get">
	        	<fieldset>
	        		<select name="book_list">
	        		<%
	        			BookHelper bookhelp=new BookHelper();
	        			List<Textbook> booklist=new ArrayList<Textbook>();
            			//List<String> existedlist=new ArrayList<String>();
	        			booklist=bookhelp.getBookList(c.getCid());
	        			//existedlist=bookhelp.getExistedBookList(c.getCid());
	        			for(int i=0; i<booklist.size();i++){
	        		%>
	        		<option value="<%=booklist.get(i).getTbISBN()%>">
	        		<%
	        			out.write(booklist.get(i).getTbtitle());
	        		%>
	        		</option>		
	        		<% }%>
	        		</select>
	        		<p class="help">Please select textbook to be assigned.</p>
	        	</fieldset>
	        	<fieldset>
	        		<input type="submit" class="blue" value="Add"/>
	        	</fieldset>
	        	</form>
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