<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            padding: 20px;
        }
        nav ul {
            list-style: none;
            padding: 0;
        }
        nav ul li {
            display: inline;
            margin-right: 20px;
        }
        nav ul li a {
            color: #007bff;
            text-decoration: none;
        }
        h2 {
            background-color: #dc3545;
            color: #fff;
            text-align: center;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 30px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #dee2e6;
        }
        th {
            background-color: #f8f9fa;
        }
        input[type="text"], input[type="submit"], input[type="reset"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ced4da;
            border-radius: 5px;
            transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
        }
        input[type="submit"], input[type="reset"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }
        input[type="submit"]:hover, input[type="reset"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<% 
        String uname = (String)session.getAttribute("user");
        if (uname != null) {
        %>
       <nav>
        <ul>
            <li><a href="index.jsp"><button style="background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;">Home</button></a></li>
        </ul>
    </nav>
    <h2>Book Registration</h2>
    <form action="register" method="post">
        <table>
            <tr>
                <td>Book Name</td>
                <td><input type="text" name="bookName"></td>
            </tr>
            <tr>
                <td>Author</td>
                <td><input type="text" name="bookAuthor"></td>
            </tr>
            <tr>
                <td>Book Edition</td>
                <td><input type="text" name="bookEdition"></td>
            </tr>
            <tr>
                <td>Book Price</td>
                <td><input type="text" name="bookPrice"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Register"></td>
                <td><input type="reset" value="Cancel"></td>
            </tr>
        </table>
    </form>
       
        <% } else { %>
        <p>You need to login to use this feature.</p>
        <% } %>

    </body>
</html>
