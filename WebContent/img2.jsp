<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.ArrayList" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shop Shop!</title>
<style>
body  {background-color: #f0f0f0;}
.logout{
float: right;}
#nav {
	width: 100%;
	float: right;
	margin: 0 0 1em 0;
	padding: 0;
}
.filter {
	width:550px;
	border-width: 2px 2px 0 0;
	border-radius:1px;
	height: 100px;
    position: absolute;
  top: 35%;
  left: 35%;}
  
  #nav ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #4682b4;
}
#nav li {
    font-family: "Courier New";
    font-weight: bold;
    float: left;
}

li a{
    display: inline-block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}
h1 {
	text-decoration:none;
	border:0;
	width : 200px;
	height : 113px;
	margin : 0;
	padding : 0;
	background : url("images/a.jpg") no-repeat 0 0;
}
 
h1 a {
	display : block;
	height : 113px;
	text-indent : -9999px; 
}
#nav .dropbtn {
    display: inline-block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover, .dropdown:hover .dropbtn {
    background-color: #008080;
}

#nav li.dropdown {
    display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    text-align: left;
}

.dropdown-content a:hover {background-color: #f1f1f1}

.dropdown:hover .dropdown-content {
    display: block;
}
div.gallery {
    margin: 5px;
    border: 1px solid #ccc;
    float: left;
    width: 200px;
    position: relative;
    top: 100px;
}
.text {
  color: white;
  font-size: 20px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
}
.overlay {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  height: 100%;
  width: 100%;
  opacity: 0;
  transition: .5s ease;
  background-color: #008CBA;
}
.gallery:hover .overlay {
  opacity: 1;
}

div.gallery:hover {
    border: 1px solid #777;
}

div.gallery img {
    width: 200px;
    height: 250px;
}

div.desc {
    padding: 15px;
    text-align: center;
}

 #cat{position: absolute;
  top: 28%;
  left: 42%;
  }
.logout{
float: right;}
input[type="submit"]{font-family: Tahoma;
color: white;
    background: #4682b4;}
</style>
</head>

<body>
<h1></h1>
<div id="nav">
<ul>
  <li><a href="http://localhost:8080/SE/home.jsp">Home</a></li>
  <li class="dropdown">
    <a href="#" class="dropbtn">About Us</a>
    <div id="nav1" class="dropdown-content">
      <a>Comfort Shopping <br/> is a small company <br/> based in Noida</a>
    </div>
  </li>
  <li class="dropdown">
    <a href="#" class="dropbtn">Contact</a>
    <div id="nav1" class="dropdown-content">
      <a>Email: <br/> support@comfortshopping.com <br/> Phone: <br/> 12345678</a>
    </div>
  </li>
  <li><a href="http://localhost:8080/SE/login.html">Login</a></li>
</ul>
</div>
<div class="logout">
<% HttpSession sess = request.getSession(false); %>
<% String user = sess.getAttribute("user").toString(); %>
<form action="logout" method="post" >
<label> Hi, <%=user %></label>
<input type="submit" value="LOGOUT">
</form>
</div>
<div id = "cat">
<form action = "fimage" method = "post" >
<label> Choose your category: </label>
<select name="category" class = "class">
<option value = "tops-tees-shirts">Tops</option>
<option value = "dresses-jumpsuits">Dresses</option>
</select>
<input type = "submit" value = "GO"></input>
</form>
</div>
<div class ="filter">
<form action="Sort2" method="post">
<input type="radio" name="a" value="price" >Price: Low to High
<input type="radio" value="discount" name="a" >Discount on the Price
<input type = "submit" value="SORT">
</form>
</div>

<%ArrayList url = (ArrayList)request.getAttribute("url"); 
ArrayList discList = (ArrayList)request.getAttribute("dis"); 
ArrayList priceList = (ArrayList)request.getAttribute("price");
ArrayList webList = (ArrayList)request.getAttribute("web");%>
<% for(int i=0;i<discList.size();i++){ 
   String path = url.get(i).toString(); 
   String discount = discList.get(i).toString();
   String price = priceList.get(i).toString();
   String web = webList.get(i).toString();%>
<div class="gallery">
<a  href="<%=web%>" target="_blank">
  <img src="<%=path %>" alt="Loading" width="300" height="200"></a>
 <div class="desc">Price: Rs.<%=price%> <br>Discount: <%=discount%> %</div>
</div>
 <%} %>
</body>
</html>