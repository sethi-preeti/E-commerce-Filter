<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<style>
h1 {
	text-decoration:none;
	border:0;
	width : 200px;
	height : 113px;
	margin : 0;
	padding : 0;
	background : url("images/a.jpg") no-repeat 0 0;
}
h2{
font-family: "Courier New";
    font-weight: bold;
    float: right;
    top:100px;
     }

#nav {
	width: 100%;
	float: left;
	margin: 0 0 1em 0;
	padding: 0;
}

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
img {
    display: block;
    margin: 20px;
}
.brightness {
    background-color: white;
    display: inline-block;
    top: 50%;
    transform: translate(120%, 0%)
}
.brightness img:hover {
    opacity: .5;
}
div.desc {
    padding: 10px;
    font-family: "Courier New";
    font-weight: bold;
    text-align: center;
}
.logout{
float: right;}
input[type="submit"]{font-family: Tahoma;
color: white;
    background: #4682b4;}
</style>
<body>
<h1></h1>
<div id="nav">
<ul>
  <li><a href="http://localhost:8080/SE/gender.html">Home</a></li>
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
<% String user = request.getAttribute("user").toString(); %>
<form action="logout" method="post" >
<label> Hi, <%=user %></label>
<input type="submit" value="LOGOUT">
</form>
</div>
<div class="brightness">
<a target="_self" href="http://localhost:8080/SE/female.html">
<img src="images\female.jpg" alt="Female" width="280" height="500">
</a>
<div class="desc">Women</div>
</div>

<div class="brightness">
<a target="_self" href="http://localhost:8080/SE/male.html">
<img src="images\male.jpg" alt="Male" width="280" height="500">
</a><div class="desc">Men</div>

</div>
</body>
</html>