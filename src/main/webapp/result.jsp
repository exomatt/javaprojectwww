<%--
  Created by IntelliJ IDEA.
  User: exomat
  Date: 16.04.19
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<html>
<head>
    <title>Result of game </title>
</head>
<body>
<p>Your result!</p>
<p>Lives: ${sessionScope["lives"]}</p>
<p>Points: ${sessionScope["points"]}</p>
<form action="result" method="post">
    <input type="submit" name="continue" value="Continue game">
    <input type="submit" name="return" value="Return to leaderboard">
</form>
</body>
</html>
