<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${sessionScope.user.lastName}</title>
</head>
<body>
<table border="1">
    <caption>Add task</caption>
    <tr>
        <th>Field</th>
        <th>Value</th>
    </tr>
    <form action="${pageContext.request.contextPath}/mainPage" method="post">
    <tr>
        <td>Hours</td><td><input name="hours" type="number" min="0" max="24"  required></td>
    </tr>
        <tr>
            <td>minutes</td><td><input name="minutes" type="number" min="0" max="60" required></td>
        </tr>
        <tr>
            <td>description</td><td><input name="description" type="text"  required></td>
        </tr>
        <tr>
            <td></td><td><button type="submit" >Send task</button></td>
        </tr>
        <input name="id" type="hidden" value="${sessionScope.user.id}">
        <c:if test="${param.error != null}">
            <div style="color: red">
                <span>Hours or minutes is not correct</span>
            </div>
        </c:if>
    </form>

</table>


    <br>

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
                <td><input type="number" min="0" max="24" name="hours" value="${task.hours}" ></td>
                <td><input type="number" min="0" max="60" name="minutes" value="${task.minutes}" ></td>
                <td><input type="text" name="description" value="${task.description}" ></td>
                <input type="hidden" name="id" value="${task.id}">
                <td><button type="submit">Change task</button></td>

            </form>
            <form action="${pageContext.request.contextPath}/deleteTask" method="post">
                <input type="hidden" name="taskId" value="${task.id}">
                <td>
                    <button type="submit">Delete task</button>
                </td>
            </form>

        </tr>
    </c:forEach>


</table>

</body>
</html>
