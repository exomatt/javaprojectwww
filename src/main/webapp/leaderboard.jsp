<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Kuba
  Date: 16.04.2019
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Leaderboard</title>
</head>
<body>
<h2>Leaderboard</h2>
<ol>
    <c:forEach var="person" items="${applicationScope.leaderboardList}">
        <li>Name:${person.get(0)} Points:${person.get(1)}</li>
    </c:forEach>
</ol>
<form action="index.jsp" method="post">
    <input type="submit" name="button" value="Go to home">
</form>
</body>
</html>
