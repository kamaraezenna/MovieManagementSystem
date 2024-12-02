<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
            background-color: #f2f2f2;
        }
        .error-container {
            background-color: #fff;
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            padding: 20px;
            display: inline-block;
            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #e74c3c;
        }
        p {
            color: #555;
        }
        a {
            text-decoration: none;
            color: #3498db;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Something Went Wrong</h1>
        <p>We encountered an error while processing your request.</p>
        <p>${errorMessage}</p>
        <p>
            <a href="login.jsp">Go back to Login</a> |
            <a href="register.jsp">Register</a>
        </p>
    </div>
</body>
</html>
