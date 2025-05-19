<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/login.css">
</head>
<body>
    <div class="container">
        <div class="login-box">
               <% String error = (String) request.getAttribute("error");
       if (error != null) { %>
       <p style="color: red;"><%= error %></p>
    <% } %>
       <% String success = (String) request.getAttribute("success");
       if (success != null) { %>
       <p style="color: green;"><%= success %></p>
    <% } %>
        
            <h2 style="font-family:	'Poppins', sans-serif">LOGIN</h2>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="row">
                    <div class="col">
                        
                        <input placeholder="Username" type="text" id="username" name="username" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        
                        <input placeholder="Password" type="password" id="password" name="password" required>
                    </div>
                </div>
                <button type="submit" class="login-button">Login</button>
                <h3>Don't have an account? <a href="${pageContext.request.contextPath}/register">Register</a></h3>
            </form>
        </div>
    </div>
</body>
</html>
