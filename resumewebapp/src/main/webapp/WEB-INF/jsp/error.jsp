<%--
  Created by IntelliJ IDEA.
  User: Zeynəb
  Date: 25.03.2022
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Why Are You Here?</title>
</head>
<body>
<%
    String msg = request.getParameter("msg");
%>
<%= msg%>
</body>
</html>
