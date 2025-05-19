<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Management</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/product.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<div class="dashboard-container">
    <aside class="sidebar">
        <div class="logo">
            <h2><a href="${contextPath}/dashboard" style="text-decoration: none; color: white;">ZEnergy</a></h2>
        </div>
        <ul class="sidebar-nav">
            <li><a href="${contextPath}/admindashboard"><i class="fas fa-home mr-2"></i> Home</a></li>
            <li><a href="${contextPath}/userprofile"><i class="fas fa-user mr-2"></i> Profile</a></li>
            <li><a href="${contextPath}/products"><i class="fas fa-box mr-2"></i> Product</a></li>
            <li><a href="#"><i class="fas fa-sign-out-alt mr-2"></i> Logout</a></li>
        </ul>
    </aside>

    <main class="main-content">
        <div class="welcome-card">
            <div>
                <h2>Product Inventory Panel</h2>
                <p>Easily manage, update, and monitor your energy drink inventory.</p>
                
            </div>
        </div>

        <div class="table-section">
            <div class="table-header">
                <h3>All Products</h3>
                <input type="text" name="search" class="search" placeholder="Search by name or brand"></input>
    			<button type="submit" class="search-btn">Search</button>
                <a href="javascript:void(0);" class="add-btn" onclick="openAddForm()">
                    <i class="fas fa-plus"> </i> Add Product
                </a>
                
            </div>

            <table>
                <thead>
                    <tr>
                        <th>Image</th>
                        <th>Brand</th>
                        <th>Drink Name</th>
                        <th>Price</th>
                        <th>Flavor</th>
                        <th>Calorie</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="drink" items="${drinkList}">
                        <tr>
                            <td>
                                <c:if test="${not empty drink.imagePath}">
                                    <img src="${contextPath}/${drink.imagePath}" width="50" height="50" style="border-radius: 4px;">
                                </c:if>
                            </td>
                            <td>${drink.brandName}</td>
                            <td>${drink.drinkName}</td>
                            <td>${drink.price}</td>
                            <td>${drink.flavor}</td>
                            <td>${drink.calorie}</td>
                            <td>
                                <div class="action-container">
                                    <a href="${contextPath}/drinkUpdate?drinkId=${drink.drinkId}" class="action-btn update-btn">
                                        <i class="fas fa-edit"></i>
                                    </a>
                                    <form action="${contextPath}/products" method="post" onsubmit="return confirm('Are you sure you want to delete this drink?');">
                                        <input type="hidden" name="action" value="delete" />
                                        <input type="hidden" name="drinkId" value="${drink.drinkId}" />
                                        <button type="submit" class="action-btn delete-btn">
                                            <i class="fas fa-trash"></i>
                                        </button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
</div>

<!-- Add Drink -->
<div id="overlay" class="overlay">
    <div class="add-drink-modal">
        <h2>Add New Drink</h2>
        <form action="${contextPath}/addDrink" method="post" enctype="multipart/form-data">
            <div class="row">
                <div class="col1">
                    <input placeholder="Drink Name" type="text" name="drinkName" required>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <input placeholder="Flavor" type="text" name="flavor" required>
                </div>
                <div class="col1">
                    <input placeholder="Price" type="number" step="0.01" name="price" required>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <input placeholder="Calorie" type="number" name="calorie" required>
                </div>
                <div class="col1">
                    <input placeholder="Brand Name" type="text" name="brandName" required>
                </div>
            </div>
            <div class="row">
                <div class="col1">
                    <label class="btn-warning">
                        <i class="fa fa-upload"></i> Upload Image
                        <input type="file" name="image" required>
                    </label>
                </div>
            </div>
			
            <button type="submit" class="login-button">Add Drink</button>
            <button type="button" class="cancel-button" onclick="closeAddForm()">Cancel</button>
        </form>
    </div>
</div>

<!-- JS -->
<script>
    function openAddForm() {
        document.getElementById("overlay").classList.add("active");
    }

    function closeAddForm() {
        document.getElementById("overlay").classList.remove("active");
    }
</script>
