<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Drink</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Helvetica Neue', sans-serif;
            background: linear-gradient(to bottom, rgb(211, 211, 211), rgb(69, 90, 100));
        }

        .login-box {
            max-width: 1000px;
            margin: 50px auto;
            background: white;
            display: flex;
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
        }

        .image-side {
            width: 50%;
            background-color: #fff;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 40px;
            box-sizing: border-box;
        }

        .image-side img {
            max-width: 100%;
            max-height: 400px;
            border-radius: 10px;
        }

        form {
            flex: 1;
            padding: 40px;
            box-sizing: border-box;
        }

        h2 {
            font-size: 28px;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .row {
            display: flex;
            gap: 20px;
            margin-bottom: 20px;
        }

        .col, .col1 {
            flex: 1;
        }

        input[type="text"],
        input[type="number"],
        input[type="file"] {
            width: 100%;
            padding: 12px 15px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 10px;
            transition: border-color 0.3s;
        }

        input[type="text"]:focus,
        input[type="number"]:focus {
            border-color: #333;
            outline: none;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 8px;
        }

        .login-button {
            display: block;
            width: 100%;
            background-color: #000;
            color: white;
            font-size: 16px;
            padding: 15px;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            margin-top: 30px;
            transition: background-color 0.3s;
        }

        .login-button:hover {
            background-color: #333;
        }

        h3 {
            margin-top: 30px;
            font-weight: normal;
            font-size: 14px;
            text-align: center;
        }

        h3 a {
            color: #000;
            text-decoration: underline;
        }

        p {
            font-size: 14px;
        }

        @media screen and (max-width: 768px) {
            .login-box {
                flex-direction: column;
            }

            .image-side {
                width: 100%;
            }
        }
    </style>
</head>
<body>
    <div class="login-box">

        <!-- Image section -->
        <div class="image-side">
            <c:if test="${drink != null && drink.imagePath != null}">
                <img src="${pageContext.request.contextPath}/${drink.imagePath}" alt="Current Drink Image">
            </c:if>
          
        </div>

        <!-- Form section -->
        <form action="${pageContext.request.contextPath}/drinkUpdate" method="post" enctype="multipart/form-data">
            <h2>Update DRINK</h2>

            <% 
                String error = (String) request.getAttribute("error");
                if (error != null) { 
            %>
                <p style="color: red;"><%= error %></p>
            <% } %>

            <% 
                String success = (String) request.getAttribute("success");
                if (success != null) { 
            %>
                <p style="color: green;"><%= success %></p>
            <% } %>

            <input type="hidden" name="drinkId" value="${drink.drinkId}" />
            <input type="hidden" name="username" value="${drink != null ? drink.username : ''}">

            <div class="row">
                <div class="col">
                    <input placeholder="Brand Name" type="text" name="brandName" required value="${drink != null ? drink.brandName : ''}">
                </div>
                <div class="col1">
                    <input placeholder="Drink Name" type="text" name="drinkName" required value="${drink != null ? drink.drinkName : ''}">
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <input placeholder="Price" type="number" name="price" step="0.01" required value="${drink != null ? drink.price : ''}">
                </div>
                <div class="col1">
                    <input placeholder="Flavor" type="text" name="flavor" required value="${drink != null ? drink.flavor : ''}">
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <input placeholder="Calories" type="number" name="calorie" required value="${drink != null ? drink.calorie : ''}">
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <label>Upload New Image (optional):</label>
                    <input type="file" name="image" accept="image/*">
                </div>
            </div>

            <button style="margin-top: 50px" type="submit" class="login-button">Submit</button>
            <h3>Want to go back to the product list? <a href="${pageContext.request.contextPath}/products">Back to Drinks</a></h3>
        </form>
    </div>
</body>
</html>
