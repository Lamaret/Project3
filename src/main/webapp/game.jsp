<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Quest</title></head>
<body>
<h2>Крок: ${step}</h2>

<form action="game" method="post">
    <input type="hidden" name="step" value="${step}">

    <% if ("start".equals(step)) { %>
    <button name="choice" value="accept">Прийняти виклик</button>
    <button name="choice" value="decline">Відхилити виклик</button>
    <% } else if ("bridge".equals(step)) { %>
    <button name="choice" value="go">Піднятися на місток</button>
    <button name="choice" value="refuse">Відмовитись</button>
    <% } else if ("identity".equals(step)) { %>
    <button name="choice" value="truth">Розповісти правду</button>
    <button name="choice" value="lie">Збрехати</button>
    <% } %>
</form>
</body>
</html>
