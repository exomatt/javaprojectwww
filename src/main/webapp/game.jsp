<%--
  Created by IntelliJ IDEA.
  User: Kuba
  Date: 16.04.2019
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hangman</title>
</head>
<body>
    <h2>${sessionScope.wordMask}</h2>
    <form action="/game" method="post">
        <input type="text" name="letter">
        <input type="submit" value="Check">
    </form>
</body>
</html>
