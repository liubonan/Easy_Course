<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="server.*" import="utility.UnivHelper" import="model.*" import="java.util.*"%>
<% User u = (User)session.getAttribute("login");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a new staff</title>
<link href="style/style.css" type="text/css" rel="stylesheet" />
<link href="style/generalform.css" type="text/css" rel="stylesheet" />
<link href="style/validationEngine.jquery.css" rel="stylesheet" type="text/css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js" type="text/javascript"></script>
<script src="js/jquery.validationEngine.js" type="text/javascript"></script>
<script src="js/jquery.validationEngine-en.js" type="text/javascript"></script>

<script type="text/javascript">	
	$(document).ready(function() {
		$("#addstaffForm").validationEngine();
		
		$(".working_info").hide();
		
		$("#working_info_click").click(function(){
			$(".working_info").toggle();
		});
	});
	

</script>	
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
        	<h3 class="text_red">Add a new teaching staff</h3>
            <div class="blank20"></div>
        	<form class="general_form" action="/EasyCourse/AddStaffServlet" method="get" id="addstaffForm">
            	<fieldset>
                	<label for="name">Name</label>
                    <input type="text" class="general_text validate[required] text-input" name="name" id="name"/>
                    <p class="general_help"></p>
                </fieldset>
            	<fieldset>
                	<label for="title">Title</label>
                    <input type="text" class="general_text" name="title" />
                    <p class="general_help"></p>
                </fieldset>
				<fieldset>
					<p><a id="working_info_click">More Information</a></p>
				</fieldset>
				<fieldset class="working_info">
					<label for="workinginfo"></label>
					<input type="radio" name="workinginfo" value="workinginfo_enabled" >&nbsp;Add Working Information
					<input type="radio" name="workinginfo" value="workinginfo_unenabled" checked="checked">&nbsp;NOT Add Working Information
				</fieldset>
                <fieldset class="working_info">
                	<label for="university">Works In</label>
                    <select name="univ_list">
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
                <fieldset  class="working_info">
                	<label for="sdate">Start time</label>
                    <input type="text" class="general_text" name="sdate" placeholder="DD-MM-YYYY"/>
                	<p class="general_help">Start time is required</p>
                </fieldset>
                <fieldset  class="working_info">
                	<label for="edate">End time</label>
                    <input type="text" class="general_text" name="edate" placeholder="DD-MM-YYYY"/>
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
