<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Time tracker</title>
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
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/" method="post">
                <fieldset class="form-group">
                    <label>Login:
                        <input name="login" type="text" required="required">
                    </label>
                </fieldset>
                <fieldset class="form-group">
                    <label>Password:
                        <input name="password" type="text" required="required">
                    </label>
                </fieldset>
                <fieldset class="form-group">
                    <button type="submit">Login</button>
                    <c:if test="${param.error != null}">
                </fieldset>
                <div style="color: red">
                    <span>Login or password is not correct</span>
                </div>
                </c:if>
                <c:if test="${param.userCreated != null}">
                    <div style="color: darkgreen">
                        <span>User ${param.userCreated} successfully created!</span>
                    </div>
                </c:if>
                <c:if test="${param.report != null}">
                    <div style="color: darkgreen">
                        <span>Report sent</span>
                    </div>
                </c:if>
            </form>
        </div>
    </div>
</div>


</body>
</html>