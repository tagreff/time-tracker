<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<table border="1">
    <caption>Отчет за ${requestScope.date} ${sessionScope.user.firstName} ${sessionScope.user.lastName}</caption>
    <tr>
        <th>Hours</th>
        <th>Minutes</th>
        <th>Description</th>

    </tr>
    <c:forEach var="task" items="${requestScope.tasks}">
        <tr>
            <form action="${pageContext.request.contextPath}/changeTask" method="post">
                <td><input type="text" name="hours" value="${task.hours}" ></td>
                <td><input type="text" name="minutes" value="${task.minutes}" ></td>
                <td><input type="text" name="description" value="${task.description}" ></td>
                <input type="hidden" name="id" value="${task.id}">
                <td><button type="submit">Change task</button></td>

            </form>
            <td><form action="${pageContext.request.contextPath}/deleteTask" method="post">
                <input type="hidden" name="taskId" value="${task.id}">
                <button type="submit">Delete task</button>
            </form></td>

        </tr>
    </c:forEach>


</table>

</body>
</html>
