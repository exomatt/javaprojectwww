<%--
  Created by IntelliJ IDEA.
  User: Kuba
  Date: 16.04.2019
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<html>
<head>
    <title>Hangman</title>
</head>
<body>
    <h2>${sessionScope.wordMask}</h2>
    <p>Lifes: ${sessionScope.lives}</p>
    <p>Points: ${sessionScope.points}</p>
    <form action="game" method="post">
        <input type="text" name="letter">
        <input type="submit" value="Check">
    </form>

    <c:if test="${sessionScope['login'] eq 'admin'}">
        <form action="admin" method="post">
            <input type="submit" name="button" value="Go to admin panel">
        </form>
    </c:if>
</body>
</html>
