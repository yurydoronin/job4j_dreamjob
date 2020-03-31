<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
    <link rel=stylesheet href=${pageContext.servletContext.contextPath}/css/table.css>
<%--    <style>--%>
<%--        table, th, td {--%>
<%--            border: 1px solid lightgray;--%>
<%--            border-collapse: collapse;--%>
<%--            background-color: #dfdfdf;--%>
<%--        }--%>

<%--        th, td {--%>
<%--            padding: 5px;--%>
<%--        }--%>
<%--    </style>--%>
</head>
<body>
<h2>Users</h2>
<p></p>
<a href=AddUser.jsp>
    <form action=${pageContext.servletContext.contextPath}/create method=post>
        <input type=submit value="New User" style="background-color:lightgreen; font-size: medium">
    </form>
</a>
<p></p>
<p></p>
<table class="styled">
    <tr>
        <th>ID</th>
        <th>User</th>
        <th>Edit</th>
    </tr>
    <c:forEach items="${users}" var="user">
    <tr>
        <td><c:out value="${user.id}"></c:out></td>
        <td><c:out value="${user.name}"></c:out></td>
        <td>
            <form action=<%=request.getContextPath()%>/edit method=post>
                <a href=EditUser.jsp>
                    <input type=hidden name=userId value=<c:out value="${user.id}"></c:out>/>
                    <input type=submit value=Edit style="background-color: limegreen; font-size: medium"/>
                </a>
            </form>
            <form action=<%=request.getContextPath()%>/delete method=post>
                <input type=hidden name=userId value=<c:out value="${user.id}"></c:out>/>
                <input type=submit value=Delete style="background-color: dodgerblue; font-size: medium"/>
            </form>
        </td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
