<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Текстовий квест</title>
</head>
<body>

<c:if test="${empty sessionScope.playerName}">
    <h2>Введіть ім'я:</h2>
    <form action="game" method="post">
        <input type="text" name="playerName" required/>
        <button type="submit">Почати гру</button>
    </form>
</c:if>

<c:if test="${not empty sessionScope.playerName}">
    <h2>Вітаю, ${sessionScope.playerName}!</h2>
    <p>Кількість ігор: ${sessionScope.gamesPlayed}</p>

    <c:if test="${not empty message}">
        <p><b>${message}</b></p>
    </c:if>

    <c:if test="${not empty step}">
        <form action="game" method="post">
            <input type="hidden" name="step" value="${step}"/>

            <c:if test="${step eq 'start'}">
                <button name="choice" value="accept">Прийняти виклик</button>
                <button name="choice" value="decline">Відхилити виклик</button>
            </c:if>

            <c:if test="${step eq 'bridge'}">
                <button name="choice" value="go">Піднятися на місток</button>
                <button name="choice" value="refuse">Відмовитись</button>
            </c:if>

            <c:if test="${step eq 'identity'}">
                <button name="choice" value="truth">Розповісти правду</button>
                <button name="choice" value="lie">Збрехати</button>
            </c:if>

            <c:if test="${step eq 'victory' or step eq 'defeat'}">
                <button name="choice" value="restart">Почати знову</button>
            </c:if>
        </form>
    </c:if>
</c:if>

</body>
</html>