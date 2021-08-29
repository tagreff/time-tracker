<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add task — ${sessionScope.user.login}</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: slateblue">
            <div>
                <a href="" class="navbar-brand"> Green Team Time Tracker </a>
            </div>
        </nav>
    </header>
    <div class="row">
        <div class="container">
            <table class="table table-bordered">
                <form action="${pageContext.request.contextPath}/mainPage" method="post">
                    <tr>
                        <td>Hours</td>
                        <td><input name="hours" type="number" min="0" max="23" class="form-control" required></td>
                    </tr>
                    <tr>
                        <td>Minutes</td>
                        <td><input name="minutes" type="number" min="0" max="59" class="form-control" required></td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td><input name="description" type="text" class="form-control" required></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <button type="submit" class="btn btn-success">Send task</button>
                        </td>
                    </tr>
                    <input name="id" type="hidden" value="${sessionScope.user.id}">
                    <c:if test="${param.error != null}">
                        <div style="color: red">
                            <span>Hours or minutes is not correct</span>
                        </div>
                    </c:if>
                </form>

            </table>
        </div>
    </div>

    <br>

    <div class="row">
        <div class="container">
            <table class="table table-bordered">
                <caption>Report
                    for ${requestScope.date} ${sessionScope.user.firstName} ${sessionScope.user.lastName}</caption>
                <tr>
                    <th>Hours</th>
                    <th>Minutes</th>
                    <th>Description</th>

                </tr>
                <c:forEach var="task" items="${requestScope.tasks}">
                    <tr>
                        <form action="${pageContext.request.contextPath}/changeTask" method="post">
                            <td><input type="number" min="0" max="23" name="hours" class="form-control" value="${task.hours}"></td>
                            <td><input type="number" min="0" max="59" name="minutes" class="form-control" value="${task.minutes}"></td>
                            <td><input type="text" name="description" class="form-control" value="${task.description}"></td>
                            <input type="hidden" name="id" value="${task.id}">
                            <td>
                                <button type="submit" class="btn btn-warning">Change task</button>
                            </td>

                        </form>
                        <form action="${pageContext.request.contextPath}/deleteTask" method="post">
                            <input type="hidden" name="taskId" value="${task.id}">
                            <td>
                                <button type="submit" class="btn btn-danger">Delete task</button>
                            </td>
                        </form>

                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</body>
</html>
