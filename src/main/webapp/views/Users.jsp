<%@ page import="ru.job4j.servlets.crud.model.ValidateService" %>
<%@ page import="ru.job4j.servlets.crud.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h3>Users</h3>
<table style="border: 1px solid black" cellpadding=5 cellspacing=0 border=1>
    <tr>
        <th>id</th>
        <th>user</th>
    </tr>
    <% for (User user : ValidateService.getInstance().findAll()) { %>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><form action=<%=request.getContextPath()%>/edit method=post>
            <input type=hidden name=userId value=<%=user.getId()%>/>
            <input type=submit value=Edit style="background-color:limegreen"/>
        </form>
        </td>
        <td><form action=<%=request.getContextPath()%>/edit method=post>
            <input type=hidden name=userId value=<%=user.getId()%>/>
            <input type=submit value=Delete style="background-color:crimson"/>
        </form>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>
