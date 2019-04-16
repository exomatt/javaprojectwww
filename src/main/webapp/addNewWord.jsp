<%--
  Created by IntelliJ IDEA.
  User: Przemek
  Date: 16.04.2019
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Panel</title>
</head>
<body>

<form action="addNewWord" method="post">
    Language: <input type="text" name="language" value="">
    Word:<input type="text" name="word" value="">
    <input type="submit" value="Add Word">
</form>

</body>
</html>
