<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Drink</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/signup.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    <div class="login-box">
        <h2>ADD NEW DRINK</h2>

        <% String error = (String) request.getAttribute("error");
           if (error != null) { %>
           <p style="color: red;"><%= error %></p>
        <% } %>
        <% String success = (String) request.getAttribute("success");
           if (success != null) { %>
           <p style="color: green;"><%= success %></p>
        <% } %>

        <form action="${pageContext.request.contextPath}/addDrink" method="post" enctype="multipart/form-data">
            <div class="row">
                <div class="col1">
                    <input placeholder="Drink Name" type="text" id="drinkName" name="drinkName" required value="${drinkName}">
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <input placeholder="Flavor" type="text" id="flavor" name="flavor" required value="${flavor}">
                </div>
                <div class="col1">
                    <input placeholder="Price" type="number" step="0.01" id="price" name="price" required value="${price}">
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <input placeholder="Calorie" type="number" id="calorie" name="calorie" required value="${calorie}">
                </div>
                <div class="col1">
                    <input placeholder="Brand Name" type="text" id="brandName" name="brandName" required value="${brandName}">
                </div>
            </div>

            <!-- Image upload section -->
            <div class="row">
                <div class="col1">
                    <button type="button" class="btn-warning">
                        <i style="margin-right: 8px" class="fa fa-upload"></i>Upload Image
                        <input type="file" id="image" name="image" required>
                    </button>
                </div>
            </div>

            <button style="margin-top: 30px" type="submit" class="login-button">Add Drink</button>
        </form>
    </div>
</body>
</html>

