<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BOOKMANDU</title>
    <style>
       body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-image: url('logo.jpg'); /* Replace 'your_image.jpg' with the path to your image */
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            opacity: 0.9; /* Adjust opacity as needed */
        }

        header {
            background-color: rgba(0, 0, 0, 0.5); /* Semi-transparent black background */
            color: white;
            padding: 15px;
            text-align: center;
        }

        nav {
   
    color: white;
    padding: 10px 0;
}

nav ul {
    list-style: none;
}

nav ul li {
    display: inline;
    margin-right: 50px;
}

nav ul li a {
    color: white;
    text-decoration: none;
    text-align:left;
    padding: 10px 20px;
    border-radius: 5px;
    border: 1px solid black;
    

}
li a:hover {
    background-color: #004080; /* Change background color on hover */
}

nav ul li.logged-in {
    position: absolute; /* Set position to absolute */
   background-color:blue;
    transform: translateY(-50%); /* Center vertically */
    right: 20px; /* Adjust as needed */
}

        .content {
            background-color: rgba(255, 255, 255, 0.7); /* Semi-transparent white background */
            padding: 20px;
            margin: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
        }

        footer {
            background-color: rgba(0, 0, 0, 0.5); /* Semi-transparent black background */
            color: white;
            padding: 15px;
            text-align: center;
        }
        .logo{
        width:160px;
        }
        
           .option ul {
            list-style: none;
            padding: 0;
            display: flex;
            justify-content: center; /* Align items horizontally */
        }

        .option ul li {
            margin: 0 10px; /* Add spacing between list items */
        }

        .option ul li a {
            color: white;
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 5px;
            border: 1px solid #004080; /* Dark blue border */
            background-color: #004080; /* Dark blue background */
            transition: background-color 0.3s ease; /* Smooth transition */
        }

        .option ul li a:hover {
            background-color: #003366; /* Darker blue background on hover */
        }
        
        
    </style>
</head>
<body>

    <header>
              
    </header>

    <nav>
        <ul>
  <img src="first.png" class="logo" alt="logo">
      <li><a href="login.html">login</a><li>
        <li><a href="uregister.html">signup</a><li>
        <li><a href="logout">logout</a></li>
  
   <h1>BOOKMANDU</h1>
      
      
            <li class="logged-in"> <% 
        String uname = (String)session.getAttribute("user");
        if (uname != null) {
        %>
        <p>Logged in as: <%= uname %></p>
        <% } else { %>
        <p>You are not logged in.</p>
        <% } %></li>
        </ul>
    </nav>

    

    <div id="welcome">
        <h1>Welcome to Bookmandu</h1>
        <p>Your Trusted Source For Genuine Books</p>
    </div>


    <section class="about" id="about">
        <h2>About Bookmandu</h2>
        <p>Bookmandu is a user-friendly website designed for book lovers. It allows users to create a list of books where they can register new books in their list, edit their book list and view the list. Users can access detailed information about each book. Basically Bookmandu is a system created to store the records of books along  with their prices so thet ti may be easy for the people to access the data about the book.</p>
<img src="hero.jpg" height="300px" width="370px">
<img src="coffee.jpg" height="300px" width="370px">
<img src="library.jpg" height="300px" width="370px">
<img src="first.jpg" height="300px" width="370px">
</section>
<section class="option" id="option"><ul>         
		   <li><a href = "register.jsp">Register a book</a>
            <li><a href="bookList">Show list</a></li>
            <li><a href="edit.html">Edit details</a></li>
            <li><a href="delete.html">Delete book</a></li></ul>
</section>

    <section class="contact" id="contact">
        <h2>Contact Us</h2>
        <p>Have questions or feedback? We'd love to hear from you! Reach out to our team at <a href="mailto:info@bookmandu.com">info@bookmandu.com</a>.</p>
        <p>Follow us on social media for the latest updates: <br><a href="https://www.facebook.com/prashanna.chand">Facebook</a> | <a href="https://www.instagram.com/prashanna_chand">Instagram</a>
 </p>
      
    </section>

    <footer>
        <p>&copy; 2024 Bookmandu. All rights reserved.</p>
    </footer>

</body>
</html>
