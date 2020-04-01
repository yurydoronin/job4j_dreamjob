<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<h3>Edit a user</h3>
<form action=<%=request.getContextPath()%>/edit method=post>
    User ID: <input type=text name="id"/><br>
    Name: <input type=text name="name"/><br><br>
    <input type=submit value=Save style="background-color:mediumspringgreen"/>
</form>
</body>
</html>
