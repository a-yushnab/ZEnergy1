<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>User Profile</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/admindashboard.css"/>
  <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/profile.css"/>
</head>

<body>
  <div class="dashboard-container">
    <aside class="sidebar">
      <div class="logo">
        <h2><a href="#" style="text-decoration: none; color: white;">ZEnergy</a></h2>
      </div>
      <ul class="sidebar-nav">
            <li><a href="${pageContext.request.contextPath}/admindashboard"><i class="fas fa-home mr-2"></i> Home</a></li>
            <li><a href="${pageContext.request.contextPath}/userprofile"><i class="fas fa-user mr-2"></i> Profile</a></li>
            <li><a href="${pageContext.request.contextPath}/products"><i class="fas fa-box mr-2"></i> Product</a></li>
           	<li><a href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt mr-2"></i> Logout</a></li>
        </ul>
    </aside>

    <main class="main-content">
      <!-- Profile card -->
      <div class="profile-card" id="profileCard">
        <div class="profile-header">
          <div class="profile-image-container">
            <img src="${contextPath}/resources/images/user/${user.imageUrl}" alt="User Photo">
          </div>
          <div class="profile-name">
            <h2>${user.firstName} ${user.lastName}</h2>
          </div>
        </div>

        <div class="profile-info">
          <div class="profile-item">
            <strong>First Name:</strong>
            <span>${user.firstName}</span>
          </div>
          <div class="profile-item">
            <strong>Last Name:</strong>
            <span>${user.lastName}</span>
          </div>
          <div class="profile-item">
            <strong>Email:</strong>
            <span>${user.email}</span>
          </div>
          <div class="profile-item">
            <strong>Gender:</strong>
            <span>${user.gender}</span>
          </div>
          <div class="profile-item">
            <strong>DOB:</strong>
            <span>${user.dob}</span>
          </div>
          <div class="profile-item">
            <strong>Number:</strong>
            <span>${user.number}</span>
          </div>
        </div>

        <div class="profile-actions">
          <button class="button" onclick="toggleEditProfile()">Edit Profile</button>
        </div>
      </div>

      <!-- Edit profile form -->
      <div class="edit-profile-form" id="editProfileForm">
        <h2>Edit Profile</h2>

        <form action="${contextPath}/userprofile" method="post" enctype="multipart/form-data">
          <!-- Image preview and upload -->
          <div class="profile-image-container" style="text-align: center; margin-bottom: 20px;">
            <input type="file" name="newImage" id="imageInput" class="file-input" accept="image/*" onchange="previewImage(event)">
            <label for="imageInput">
              <img src="${contextPath}/resources/images/user/${user.imageUrl}" 
                   alt="Current Image" 
                   id="imagePreview" />
              <div style="font-size: 12px; margin-top: 5px; color: #666;">Click to change photo</div>
            </label>
            <input type="hidden" name="imageUrl" value="${user.imageUrl}" />
          </div>

          <div class="form-group">
            <label for="firstName">First Name</label>
            <input type="text" id="firstName" class="form-control" name="firstName" value="${user.firstName}">
          </div>
          
          <div class="form-group">
            <label for="lastName">Last Name</label>
            <input type="text" id="lastName" class="form-control" name="lastName" value="${user.lastName}">
          </div>
          
          <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" class="form-control" name="email" value="${user.email}">
          </div>
          
          <div class="form-group">
            <label for="gender">Gender</label>
            <select id="gender" name="gender" class="form-control">
              <option value="Male" ${user.gender == 'Male' ? 'selected' : ''}>Male</option>
              <option value="Female" ${user.gender == 'Female' ? 'selected' : ''}>Female</option>
            </select>
          </div>
          
          <div class="form-group">
            <label for="dob">Date of Birth</label>
            <input type="date" id="dob" class="form-control" name="dob" value="${user.dob}">
          </div>
          
          <div class="form-group">
            <label for="number">Phone Number</label>
            <input type="text" id="number" class="form-control" name="number" value="${user.number}">
          </div>

          <div class="profile-actions">
            <button type="submit" class="button">Save Changes</button>
            <button type="button" class="button" onclick="toggleEditProfile()" style="background-color: #757575; margin-left: 10px;">Cancel</button>
          </div>
        </form>
      </div>
    </main>
  </div>

  <script>
    function toggleEditProfile() {
      const profileCard = document.getElementById("profileCard");
      const editProfileForm = document.getElementById("editProfileForm");

      if (profileCard.style.display === "none") {
        profileCard.style.display = "block";
        editProfileForm.style.display = "none";
      } else {
        profileCard.style.display = "none";
        editProfileForm.style.display = "block";
      }
    }

    function previewImage(event) {
      const reader = new FileReader();
      reader.onload = function () {
        const output = document.getElementById('imagePreview');
        output.src = reader.result;
      }
      reader.readAsDataURL(event.target.files[0]);
    }
  </script>
</body>
</html>