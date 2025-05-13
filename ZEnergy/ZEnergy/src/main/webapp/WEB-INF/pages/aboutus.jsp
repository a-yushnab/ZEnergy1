<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>About Us</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/aboutus.css"/>

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

<div class="about-container">
    <div class="about-text">
        <h1>About Me</h1>
        <p>
            Hi, I am Ayushnab Poudel, the founder of ZEnergy. I created ZEnergy because I believe that life should be lived at full throttle  with focus, passion, and purpose. Like many of you, I was tired of energy drinks loaded with sugar and chemicals that left me feeling worse, not better.
        </p>
        <p>
            Thats why I set out to create something different  an energy drink made with quality ingredients that power your body and mind, without the crash. ZEnergy is built for dreamers, doers, and those who refuse to settle.
        </p>
        <p>
            My mission is simple: to energize greatness, one sip at a time. Whether you're chasing a goal, hitting the gym, or just taking on your everyday hustle, ZEnergy is here to fuel your fire.
        </p>
        <p>
            Thank you for being part of this journey. Let's make every moment unstoppable.
        </p>
        <button style="background-color:#d4c29a; border:none; padding:10px 20px; font-size:16px; font-weight:bold; cursor:pointer; border-radius:5px;">Learn More</button>
    </div>

    <div class="about-image">
        <img src="${pageContext.request.contextPath}/resources/images/me.jpeg" alt="Image">
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
