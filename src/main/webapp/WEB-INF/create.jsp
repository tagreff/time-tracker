<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create user</title>
</head>
<body>
    <h2>New user</h2>
    <form method="post">
        <label for="login">Login</label><br>
        <input name="login" id="login" type="text" pattern=".{2,30}" required title="2 to 30 characters" /><br><br>
        <label for="firstName">First Name</label><br>
        <input name="firstName" id="firstName" type="text" pattern=".{2,30}" required title="2 to 30 characters"/><br><br>
        <label for="lastName">Last name</label><br>
        <input name="lastName" id="lastName" type="text" pattern=".{2,30}" required title="2 to 30 characters"/><br><br>
        <label for="password">Password</label><br>
        <input name="password" id="password" type="text" pattern=".{2,30}" required title="2 to 30 characters"/><br><br>

        <input type="submit" value="Save" />
        <c:if test="${param.cantCreate != null}">
            <div style="color: red">
                <span>Can't create user!</span>
            </div>
        </c:if>
    </form>
</body>
</html>
