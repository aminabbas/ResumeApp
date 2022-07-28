<%--
  Created by IntelliJ IDEA.
  User: Zeynəb
  Date: 27.03.2022
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../assets/css/users_page.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Login</title>
</head>
<body class="login_background">
<form action="login" method="POST">
    <div class="col-4 container login_fix_" >
<center><h1>Login:</h1></center>

    <div class="form-group">
        <label>Email Address:</label>
        <input type="email" class="form-control" placeholder="email@example.com" name="email">
    </div>

    <div class="form-group">
        <label>Password:</label>
        <input type="password" class="form-control" placeholder="Enter password" name="password">
    </div>
    <button type="submit" class="btn btn-primary" name="login">Login</button>
    </div>
</form>
</body>
</html>
