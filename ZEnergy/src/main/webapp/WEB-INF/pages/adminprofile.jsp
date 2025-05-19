<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Profile</title>

<style>
header {
    background: #455a64;
    padding: 2vh 4vw;
    display: flex;
    justify-content: space-between;
    align-items: center;
    color: #ffffff;
}
header h1 {
    font-size: 3vh;
    font-weight: 600;
    text-align: center;
}
nav ul {
    list-style: none;
    display: flex;
    gap: 3vw;
    align-items: center;
}
nav a {
    color: #ffffff;
    text-decoration: none;
    font-size: 2.2vh;
    transition: color 0.3s ease;
    font-weight: 400;
}
nav a:hover {
    color: #b0bec5;
}
.profile-icon, .logout-icon {
    width: 5vh;
    height: 5vh;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
}
.profile-icon {
    background-color: #607d8b;
}
.logout-icon:hover {
    background-color: #455a64;
}
.logout-image {
    width: 100%;
    height: 100%;
    object-fit: contain;
}

body {
    margin: 0;
    padding: 0;
    font-family: 'Arial', sans-serif;
    background-color: #b0bec5;
    
}

.about-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
    padding: 50px;
    gap: 50px;
}

.about-text {
    flex: 1 1 400px;
    max-width: 600px;
}

.about-text h1 {
    font-size: 36px;
    color: #000;
    margin-bottom: 20px;
}

.about-text p {
    font-size: 18px;
    color: #555;
    line-height: 1.6;
    margin-bottom: 20px;
}

.about-image {
    flex: 1 1 400px;
    max-width: 500px;
}

.about-image img {
    width: 100%;
    height: auto;
    border-radius: 10px;
}

footer {
    background: #455a64;
    color: #ffffff;
    padding: 1vh 3vw;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    gap: 2vh;
}
footer > div {
    flex: 1 1 180px;
    min-width: 180px;
}
footer h3 {
    margin-bottom: 1vh;
    font-size: 2.3vh;
    color: white;
}
footer p, footer a {
    font-size: 2vh;
    color: #cfd8dc;
    text-decoration: none;
    line-height: 1.6;
}
footer a:hover {
    color: #ffffff;
    text-decoration: underline;
}
.footer-bottom {
    flex: 1 1 100%;
    text-align: center;
    font-size: 1.8vh;
    color: #b0bec5;
    margin-top: 1vh;
}
.elements {
    display: flex;
    justify-content: space-between;
}

.profile-container {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: calc(100vh - 120px); /* 100% viewport height minus header/footer */
    padding: 20px;
}

.profile-card {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 6px 12px rgba(0,0,0,0.2);
            width: 400px;
            text-align: center;
            margin: 10px;
            
        }
        .profile-card img {
            width: 120px;
            height: 120px;
            border-radius: 50%;
            object-fit: cover;
            margin-bottom: 20px;
            border: 4px solid #455a64;
        }
        .profile-card h2 {
            color: #455a64;
            margin: 15px 0 5px;
        }
        .profile-card p.role {
            color: #607d8b;
            margin: 5px 0 20px;
            font-size: 16px;
        
        }
        .profile-info {
            text-align: left;
            margin-top: 20px;
        }
        .profile-info div {
            margin-bottom: 12px;
            font-size: 15px;
        }
        .profile-info strong {
            color: #455a64;
            width: 100px;
            display: inline-block;
        }
        .edit-button {
            margin-top: 25px;
            display: inline-block;
            background-color: #455a64;
            color: white;
            padding: 12px 24px;
            border-radius: 6px;
            text-decoration: none;
            font-size: 16px;
        }
        .edit-button:hover {
            background-color: #37474f;
        }
</style>
</head>

<body>

<header>
    <h1>ZEnergy</h1>
    <nav>
      <ul>
        <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/contact">Contact Us</a></li>
        <li><a href="${pageContext.request.contextPath}/aboutus">About Us</a></li>
        <li>
          <div class="profile-icon">
            <a href="${pageContext.request.contextPath}/profile"><span style="color:white; font-size: 2.5vh;"></span></a>
          </div>
        </li>
        <li class="logout">
          <div class="logout-icon">
            <a href="${pageContext.request.contextPath}/logout">
              <img src="logging-out-2355227_1280.webp" alt="Logout" class="logout-image">
            </a>
          </div>
        </li>
      </ul>
    </nav>
</header>

<div class="profile-container">
    <div class="profile-card">
        <img src="https://via.placeholder.com/120" alt="User Photo">
        <h2>John Doe</h2>
        <p class="role">Administrator</p>

        <div class="profile-info">
            <div><strong>Email:</strong> john.doe@example.com</div>
            <div><strong>Username:</strong> johndoe</div>
            <div><strong>Phone:</strong> +1 234 567 890</div>
            <div><strong>Address:</strong> 1234 Main Street, Cityville</div>
            <div><strong>Birthday:</strong> January 1, 1990</div>
            <div><strong>Joined:</strong> March 15, 2022</div>
            <div><strong>Status:</strong> Active</div>
        </div>

        <a href="edit-profile.html" class="edit-button">Edit Profile</a>
    </div>
</div>

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

</body>
</html>
