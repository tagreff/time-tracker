<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create user</title>
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
        <h3>New user</h3>
        <form method="post">
            <div class="mb-3">
                <label for="login">Login</label><br>
                <input name="login" id="login" type="text" class="form-control" required title="2 to 30 characters" />
            </div>
            <div class="mb-3">
                <label for="firstName">First Name</label><br>
                <input name="firstName" id="firstName" type="text" class="form-control" required title="2 to 30 characters"/>
            <div class="mb-3">
            </div>
                <label for="lastName">Last name</label><br>
                <input name="lastName" id="lastName" type="text" class="form-control" required title="2 to 30 characters"/>
            <div class="mb-3">
            </div>
                <label for="password">Password</label><br>
                <input name="password" id="password" type="text" class="form-control" required title="2 to 30 characters"/>
            </div>
            <button type="submit" class="btn btn-success" value="Save">Save</button>
            <c:if test="${param.cantCreate != null}">
                <div style="color: red">
                    <span>Can't create user!</span>
                </div>
            </c:if>
        </form>
    </div>
</body>
</html>
