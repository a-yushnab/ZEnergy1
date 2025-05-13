<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/admindashboard.css"/>
</head>
<body>
<div class="dashboard-container">
    <aside class="sidebar">
        <div class="logo">
            <h2><a href="#" style="text-decoration: none; color: white;">ZEnergy</a></h2>
        </div>
        <ul class="sidebar-nav">
            <li><a href="#"><i class="fas fa-home mr-2"></i> Home</a></li>
            <li><a href="${pageContext.request.contextPath}/userprofile"><i class="fas fa-user mr-2"></i> Profile</a></li>
            <li><a href="${pageContext.request.contextPath}/products"><i class="fas fa-box mr-2"></i> Product</a></li>
            <li><a href="${pageContext.request.contextPath}/logout">"<i class="fas fa-sign-out-alt mr-2"></i> Logout</a></li>
        </ul>
    </aside>

    <main class="main-content">
        <div class="welcome-card">
            <div>
                <h2>Welcome Back, Admin!</h2>
                <p>Here's what's happening with your users today.</p>
            </div>
        </div>

        <div class="stats-cards">
            <div class="stat-card all-users">
                <div class="stat-icon">
                    <i class="fas fa-users"></i>
                </div>
                <div class="stat-info">
                    <h3>${userList.size()}</h3>
                    <p>Total Users</p>
                </div>
            </div>
            
            <div class="stat-card female-users">
                <div class="stat-icon">
                    <i class="fas fa-venus"></i>
                </div>
                <div class="stat-info">
                    <h3>
                        <c:set var="femaleCount" value="0" />
                        <c:forEach var="user" items="${userList}">
                            <c:if test="${user.gender == 'Female'}">
                                <c:set var="femaleCount" value="${femaleCount + 1}" />
                            </c:if>
                        </c:forEach>
                        ${femaleCount}
                    </h3>
                    <p>Female Users</p>
                </div>
            </div>
            
            <div class="stat-card male-users">
                <div class="stat-icon">
                    <i class="fas fa-mars"></i>
                </div>
                <div class="stat-info">
                    <h3>
                        <c:set var="maleCount" value="0" />
                        <c:forEach var="user" items="${userList}">
                            <c:if test="${user.gender == 'Male'}">
                                <c:set var="maleCount" value="${maleCount + 1}" />
                            </c:if>
                        </c:forEach>
                        ${maleCount}
                    </h3>
                    <p>Male Users</p>
                </div>
            </div>
        </div>

        <div class="table-section">
            <h3>
                All Users
            </h3>
            <table>
                <thead>
                    <tr>
                        <th>Image</th>
                        <th>Username</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Gender</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${userList}">
                        <tr>
                            <td>
                                <img src="${contextPath}/resources/images/user/${user.imageUrl}" width="50" height="50">
                            </td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.userName}</td>
                            <td>${user.email}</td>
                            <td>${user.number}</td>
                            <td>${user.gender}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
</div>
</body>
</html>