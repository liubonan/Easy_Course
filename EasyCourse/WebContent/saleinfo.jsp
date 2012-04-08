<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="server.*" import="model.*" import="java.text.DecimalFormat" import="java.text.NumberFormat"%>
<% User u = (User)session.getAttribute("login");    
SellInfo si = (SellInfo)session.getAttribute("sellinfo"); 
DecimalFormat format = (DecimalFormat) NumberFormat.getInstance();
format.applyPattern("0.00");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Sales Information</title>
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
        	<h3 class="text_red">Sales Information - <%=si.getTb().getTbtitle() %></h3>
            <div class="blank20"></div>
        	<form class="general_form" action="/EasyCourse/index.jsp">
            	<fieldset>
                	<label for="seller">Seller</label>
                    <p><%=si.getSeller() %></p>
                </fieldset>
            	<fieldset>
                	<label for="isbn">ISBN</label>
                    <p><%=si.getTb().getTbISBN() %></p>
                </fieldset>
                <fieldset>
                	<label for="condition">Condition</label>
                    <p><%=si.getCondition() %></p>
                </fieldset>
                 <fieldset>
                	<label for="price">Original Price</label>
                    <p>USD <%=format.format(si.getTb().getTbprice()) %></p>
                </fieldset>
                <fieldset>
                	<label for="price">Sell Price</label><%int perc =(int)(100 * (1 - (si.getSellprice()/si.getTb().getTbprice())));%>
                    <p>USD <%=format.format(si.getSellprice()) %> (<%=perc %>% Off)</p>
                </fieldset>
                <fieldset>
                	<label for="note">Note</label>
                    <p><%=si.getNote() %></p>
                </fieldset>
                <fieldset>
                	<a href="#"><input type="submit" class="blue" value="Back"/></a>
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