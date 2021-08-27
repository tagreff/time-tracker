<%--
  Created by IntelliJ IDEA.
  User: Mikhail
  Date: 8/21/2021
  Time: 11:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Green Team Time Tracker</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: slateblue">
        <div>
            <a href="" class="navbar-brand"> Green Team Time Tracker </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
        </ul>
    </nav>
</header>
<div class="row">
    <div class="container">
        <form action="${pageContext.request.contextPath}/sendReport" method="post">
            <button type="submit">Send report</button>
        </form>
    </div>
</div>
</form>
</body>
</html>
