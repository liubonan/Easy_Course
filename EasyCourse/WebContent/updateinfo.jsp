<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="server.*" import="utility.UnivHelper" import="model.*" import="java.util.*"%>
<% User u = (User)session.getAttribute("login");%>
<% UnivHelper univhelp=new UnivHelper(); %>
<% String univname=null; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Information</title>
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
        	<h3 class="text_red">Update Information</h3>
            <div class="blank20"></div>
        	<form class="general_form" action="/EasyCourse/UpdateUserInfoServlet" method="post">
            	<fieldset>
                	<label for="email">Email</label>
                    <p><%=u.getUemail() %></p>
                    <p class="general_help">Email cannot be changed.</p>
                </fieldset>
                <fieldset>
                	<label for="username">Nickname</label>
                    <input type="text" class="general_text" value="<%=u.getUnickname() %>" name="nickname" />
                    <p class="general_help"></p>
                </fieldset>
                <fieldset>
                	<label for="password">Password</label>
                    <p><a href="/EasyCourse/updatepass.jsp">Change Password</a></p>
                    <p class="general_help"></p>
                </fieldset>
                <fieldset>
                	<label for="university">University</label>
                    <select name="univ_list">
                    <option>
                    <%
                    	univname=univhelp.getUnivname(u.getUnivid());
                		out.write(univname);
                    %>
                    </option>
                    <option value="not_enrolled">
                    	---- Choose University Later ----
                    </option>
                    <%
                		List<University> univlist = new ArrayList<University>();
                		univlist=univhelp.getUnivList();
                    	for(int i=0;i<univlist.size();i++){
                    		if(!univlist.get(i).getUnivid().equals(u.getUnivid())){
                    %>
                    <option value="<%=univlist.get(i).getUnivid()%>">
                    <%
                    	out.write(univlist.get(i).getUnivname());
                    }}
                    %>
                    </option>
                    </select>
                    
                </fieldset>
                <fieldset>
                	<input type="submit" class="blue" value="Save" />
                </fieldset>
            </form>
        </div>
        <div class="formcontainer">
        	<h3 class="text_red">Deactivate this account</h3>
            <div class="blank20"></div>
        	<form class="" action="/EasyCourse/DeleteUserServlet" method="post">
                <fieldset>
                	<p>* If you deactive your account, you can never use it.</p>
                	<div class="blank10"></div>
                	<input type="submit" class="blue" value="Deactive Account" />
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
