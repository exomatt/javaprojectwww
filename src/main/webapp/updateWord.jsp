<%--
  Created by IntelliJ IDEA.
  User: Przemek
  Date: 16.04.2019
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin Panel</title>
</head>
<body>

<c:if test="${sessionScope.wordToUpdate != null}">
    <form action="updateWord" method="post">
        Language: <input type="text" name="language" value="${sessionScope.wordToUpdate[0]}">
        Word:<input type="text" name="word" value="${sessionScope.wordToUpdate[1]}">

        <input type="submit" value="updateWord">
    </form>
</c:if>

<c:if test="${sessionScope.wordToUpdate == null}">
    No word has been selected :/
</c:if>



</body>
</html>
