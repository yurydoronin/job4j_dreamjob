<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="ru.job4j.dream.model.Store" %>
<%@ page import="ru.job4j.dream.model.Post" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"
            integrity="sha384-vk5WoKIaW/vJyUAd9n/wmopsmNhiy+L2Z+SBxGYnUkunIxVxAv/UtMOhba/xskxh"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <style>
        table, th, td {
            border: 1px solid lightgray;
            border-collapse: collapse;
        }

        th, td {
            padding: 5px;
        }
    </style>
    <title>Работа мечты</title>
</head>
<body style="background-color:powderblue;">
<div class="container pt-3">
    <div class="row">
        <div class="card-header" style="background-color: beige">
            Вакансии
        </div>
        <div class="card-body">
            <table class="table table-dark">
                <thead>
                <h1 style="text-align:center;">Объявления</h1>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Created</th>
                </tr>
                </thead>
                <tbody>
                <% for (Post post : Store.instOf().findAll()) { %>
                <tr>
                    <td><%=post.getId()%>
                    </td>
                    <td><%=post.getName()%>
                    </td>
                    <td><%=post.getDescription()%>
                    </td>
                    <td><%=post.getCreated().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss"))%>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div> <%-- card-body --%>
    </div><%-- row --%>
</div><%-- container --%>
</body>
</html>