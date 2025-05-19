<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ZEnergy</title>
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/home.css"/>
</head>
<body>
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
            <a href="${pageContext.request.contextPath}/logout">
              <img src="${pageContext.request.contextPath}/resources/images/logging-out-2355227_1280.jpeg" alt="Logout" class="logout-image">
            </a>
          </div>
        </li>
      </ul>
    </nav>
  </header>

    <main class="hero">
        <div class="image-container">
            <img src="${pageContext.request.contextPath}/resources/images/5635795c3a1d6060f0e3d58d355e24cc.jpg" alt="Woman exercising with kettlebell">
        </div>
        <div class="text-container">
            <h1><span class="light">Your</span> <span class="bold">Partner</span> <span class="light">in an</span> <span class="bold">Active Lifestyle</span></h1>
            <h2 class="subheading">Clinically Proven to Function</h2>
            <p>
              At ZEnergy, we make it easy to manage and serve a wide selection of top-performance beverages, including popular brands like Prime, Red Bull, Monster, and Celsius.
              Whether your customers are looking for hydration, energy, or recovery, our system helps you keep track of inventory, monitor sales trends, and ensure timely restocking.
              From sports drinks and energy boosters to zero-sugar and natural options, ZEnergy allows you to offer a curated drink experience tailored to your audience needs.
              With real-time insights and simple controls, managing your beverage lineup has never been more efficient.
          </p>
          
        </div>
        <section class="drink-section">
		    <c:forEach var="drink" items="${drinkList}">
		        <div class="drink-card">
		            <img src="${pageContext.request.contextPath}/${drink.imagePath}" alt="${drink.brandName} Image" />
		            <div class="overlay">
		                <h2>${drink.brandName}</h2>
		                <p>${drink.drinkName}</p>
		            </div>
		        </div>
		    </c:forEach>
		</section>

    </main>
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
