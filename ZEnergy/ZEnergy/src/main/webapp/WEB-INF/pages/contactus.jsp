<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%@ page import="jakarta.servlet.http.HttpServletRequest"%>

<%
// Initialize necessary objects and variables
HttpSession userSession = request.getSession(false);
String currentUser = (String) (userSession != null ? userSession.getAttribute("username") : null);
// need to add data in attribute to select it in JSP code using JSTL core tag
pageContext.setAttribute("currentUser", currentUser);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/contactus.css"/>

<title>Contact Us</title>
</head>
<header>
    <h1>ZEnergy</h1>
    <nav>
      <ul>
        <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/contactus">Contact Us</a></li>
        <li><a href="${pageContext.request.contextPath}/aboutus">About Us</a></li>
        <li>
          <div class="profile-icon">
            <a href="${pageContext.request.contextPath}/userprofile">
            <img src="${pageContext.request.contextPath}/resources/images/businessman-avatar-illustration-cartoon-user-portrait-user-profile-icon_118339-5507.jpg.jpeg" alt="Logout" class="logout-image">
            </a>
          </div>
        </li>
       <li class="logout">
  <div class="logout-icon">
    <a href="#" onclick="return confirmLogout();">
      <img src="${pageContext.request.contextPath}/resources/images/logging-out-2355227_1280.jpeg" alt="Logout" class="logout-image">
    </a>
    <form id="logoutForm" action="${pageContext.request.contextPath}/login" method="post" style="display:none;"></form>
  </div>
</li>

        </li>
      </ul>
    </nav>
  </header>
<body>

<div class="container">
    <div class="contact-info">
        <h1>Contact Us</h1>
        <p>Our mailing address is:<br>Tokha-6<br>Kathmandu, Nepal<br>Phone: +977 9841258662</p>
       
    </div>

    <div class="contact-form">
        <p>Great vision without great people is irrelevant.<br>Let's work together.</p>
        <form action="#" method="POST">
            <input type="text" name="name" placeholder="Enter your Name" required>
            <input type="email" name="email" placeholder="Enter a valid email address" required>
            <textarea name="message" placeholder="Enter your message" required></textarea>
            <button type="submit">Submit</button>
        </form>
    </div>
</div>

</body>
<footer>
    <div class="elements">
      <div>
        <h3>Visit Us</h3>
        <p>Tokha-6<br>Kathmandu, Nepal</p>
      </div>
      <div>
        <h3>Contact</h3>
        <p>Email: zenergy@gmail.com</p>
        <p>Phone: +977 9841258662</p>
      </div>
      <div>
        <h3>Follow Us</h3>
        <p><a href="#">Instagram</a></p>
        <p><a href="#">Facebook</a></p>
        <p><a href="#">Twitter</a></p>
      </div>
      <div>
        <h3>Quick Links</h3>
        <p><a href="#">Terms Of User</a></p>
        <p><a href="#">FAQ</a></p>
        <p><a href="#">Privacy Policy</a></p>
      </div>
      <div>
        <h3>Company</h3>
        <p><a href="#">Maps</a></p>
        <p><a href="#">Careers</a></p>
        <p><a href="#">About Us</a></p>
      </div>
    </div>
    <div class="footer-bottom">
      &copy; 2025 ZEnergy. All rights reserved.
    </div>
  </footer>
  <script>
  function confirmLogout() {
	    if (confirm("Are you sure you want to logout?")) {
	      document.getElementById("logoutForm").submit();
	    }
	    return false; // prevent default <a> navigation
	  }
</script>
</html>