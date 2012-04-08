<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="server.*" import="model.*" import="java.text.DecimalFormat" import="java.text.NumberFormat"%>
<% User u = (User)session.getAttribute("login");
Textbook tb = (Textbook)session.getAttribute("textbook");
DecimalFormat format = (DecimalFormat) NumberFormat.getInstance();
format.applyPattern("0.00");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sell Book</title>
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
        	<h3 class="text_red">Sell a used book - <%=tb.getTbtitle() %></h3>
            <div class="blank20"></div>
            
        	<form class="general_form" action="/EasyCourse/SellBookServlet" method="post">
        		<%if(u != null){ %>
            	<fieldset>
                	<label for="isbn" >ISBN</label>
                    <p><span><%=tb.getTbISBN() %></span></p>
                    <p class="general_help"></p>
                </fieldset>
                <fieldset>
                	<label for="condition">Condition</label>
                    <ul>
                    	<li><input type="radio" name="condition" value="Like New"/> Like New</li>
                        <li><input type="radio" name="condition" value="Very Good"/> Very Good</li>
                        <li><input type="radio" name="condition" value="Good"/> Good</li>
                        <li><input type="radio" name="condition" value="Acceptable" checked="checked"/> Acceptable</li>
                    </ul>
                </fieldset>
                <fieldset>
                	<label for="price">Price</label>
                    <input type="text" class="general_text price" name="sell_price"/> (Original Price: <%=format.format(tb.getTbprice()) %>)
                    <p class="general_help"></p>
                </fieldset>
                <fieldset>
                	<label for="note">Note</label>
                    <textarea name="note"></textarea>
                    <p class="general_help"></p>
                </fieldset>
                <fieldset>
                	<input type="submit" class="blue" value="Submit" />
                </fieldset>
                <%} else { %>
            	<fieldset>
                	<label for="isbn">Please login first!</label>                    
                </fieldset>
            	<%} %>
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
