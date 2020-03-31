<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<h3>Edit a user</h3>
<form action=${pageContext.servletContext.contextPath}/edit method=post>
    User ID: <input type=text name=id style="font-size: medium"><br>
    Name: <input type=text name=name style="font-size: medium"><br>
    <p></p>
    <a href=Users.jsp>
        <input type=submit value=Save style="background-color: mediumspringgreen; font-size: medium"/>
    </a>
</form>
</body>
</html>
