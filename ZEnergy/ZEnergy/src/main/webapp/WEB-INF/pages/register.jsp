<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/signup.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    <div class="login-box">
        <h2>REGISTRATION FORM</h2>
        
        <% String error = (String) request.getAttribute("error");
       if (error != null) { %>
       <p style="color: red;"><%= error %></p>
    <% } %>
       <% String success = (String) request.getAttribute("success");
       if (success != null) { %>
       <p style="color: green;"><%= success %></p>
    <% } %>

        <form action="${pageContext.request.contextPath}/register" method="post" enctype="multipart/form-data">
            <div class="row">
                <div class="col">
                    <input placeholder="First Name" type="text" id="firstName" name="firstName" required value="${firstName}">
                </div>
                <div class="col1">
                    <input placeholder="Last Name" type="text" id="lastName" name="lastName" required value="${lastName}">
                </div>
            </div>
            <div class="row">
                <div class="col">	
                    <input placeholder="Username" type="text" id="userName" name="userName" required value="${userName}">
                </div>
                <div class="col1">
                    <input placeholder="Date of Birth" type="date" id="dob" name="dob" required value="${dob}">
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <input placeholder="Gender" type="text" id="gender" name="gender" required value="${gender}">
                </div>
                <div class="col1">
                    <input placeholder="Email" type="email" id="email" name="email" required value="${email}">
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <input placeholder="Phone Number" type="text" id="number" name="number" required value="${number}">
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <input placeholder="Password" type="password" id="password" name="password" required value="${password}">
                </div>
                <div class="col1">
                    <input placeholder="Retype Password" type="password" id="retypePassword" name="retypePassword" required value="${retypePassword}">
                </div>
            </div>
            <div class="row">
                <div class="col1">
  						<button type = "button" class = "btn-warning">
  						
  							<i style="margin-right: 8px" class = "fa fa-upload"></i>Upload Image
  							<input type="file" id="image" name="image">
     					</button>
                </div>
                
            </div>
            <button style="margin-top: 50px" type="submit" class="login-button">Submit</button>
            <h3>Already have an account? <a href="${pageContext.request.contextPath}/login">Login</a></h3>
            
        </form>
    </div>
</body>
</html>
