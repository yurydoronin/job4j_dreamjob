<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>New user</title>
</head>
<body>
<h3>Addition a new user</h3>
<form action=${pageContext.servletContext.contextPath}/create method=post>
    User ID: <input type=text name=id style="font-size: medium"><br>
    Name: <input type=text name=name style="font-size: medium"><br>
    <p></p>
    <input type=submit value=Save style="background-color:mediumspringgreen; font-size: medium">
    <a href=Users.jsp>
        <input type=submit value="Back to Users" style="background-color:skyblue; font-size: medium">
    </a>
</form>
</body>
</html>
