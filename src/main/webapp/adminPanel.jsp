<%--
  Created by IntelliJ IDEA.
  User: Przemek
  Date: 16.04.2019
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin Panel</title>
</head>
<body>
<form action="process" method="post">
    <table>
        <c:forEach var="word" items="${sessionScope.listOfWords}">
            <tr>
                <td>${word[0]}</td>
                <td>${word[1]}</td>
                <td><input type="checkbox" name="checked" value="${word[1]}"></td>
                <td><input type="radio" name="selected" value="${word[1]}"></td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" name="deleteButton" value="Delete Checked">
    <input type="submit" name="updateButton" value="Update Checked">
</form>
<form><input type="button"
             onclick="window.location.href = 'http://localhost:8080/javaproject_war_exploded/addNewWord';"
             value="Add New Word"></form>


</body>
</html>
