<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>New user</title>
</head>
<body>
<h3>Addition a new user</h3>
<form action=<%=request.getContextPath()%>/create method=post>
    User ID: <input type=text name="id"/><br>
    Name: <input type=text name="name"/><br><br>
    <input type=submit value=Add style="background-color:mediumspringgreen"/>
</form>
</body>
</html>
