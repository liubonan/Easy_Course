<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="server.*" import="model.*" import="utility.*" import="java.util.*"%>
<% User u = (User)session.getAttribute("login");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a new course</title>
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
	<div class="blank20"></div>
    <div class="blank20"></div>
    <div class="container_content">
        <div class="grid_940">
        <div class="formcontainer">
        	<h3 class="text_red">Add a new course</h3>
            <div class="blank20"></div>
        	<form class="general_form" action="/EasyCourse/AddCourseServlet" method="post">
            	<fieldset>
                	<label for="name">Name</label>
                    <input type="text" class="general_text" name="cname"/>
                    <p class="general_help"></p>
                </fieldset>
            	<fieldset>
                	<label for="staff">Taught By</label>
                    <select name="tsid">
                    <%
                    	StaffHelper sh = new StaffHelper();
                		List<Teachingstaff> ts_list = sh.get_staff_list();
                		for(int i=0;i < ts_list.size();i++){                			
                    %>
                    <option value="<%=ts_list.get(i).getTsid()%>">
                    <%=ts_list.get(i).getTsname() %>
                    </option>
                    <%} %>
                    </select>
                    <p class="general_help">Can't find teaching staff? <a href="/EasyCourse/addstaff.jsp">Add one!</a></p>
                </fieldset>
                <fieldset>
                	<label for="university">Offered By</label>
                    <select name="univid">
                    <%
                    	UnivHelper univhelp=new UnivHelper();
                		List<University> univlist = new ArrayList<University>();
                		univlist=univhelp.getUnivList();
                    	for(int i=0;i<univlist.size();i++){
                    %>
                    <option value="<%=univlist.get(i).getUnivid()%>">
                    <%
                    	out.write(univlist.get(i).getUnivname());
                    }
                    %>
                    </option>
                    </select>
                    <p class="general_help">Can't find university? <a href="/EasyCourse/adduniversity.jsp">Add one!</a></p>
                </fieldset>
                <fieldset>
                	<label for="semester">Semester</label>
                    <input type="text" class="general_text" name="csemester"/>
                    <p class="general_help">e.g 2012Spring</p>
                </fieldset>
                <fieldset>
                	<label for="cdescription">Description</label>
                    <textarea name=cdescription></textarea>
                    <p class="general_help"></p>
                </fieldset>
                <fieldset>
                	<input type="submit" class="blue" value="Save" />
                </fieldset>
            </form>
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