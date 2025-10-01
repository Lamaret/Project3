<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Результат</title></head>
<body>
<h2>${sessionScope.state.playerName}, гра завершена!</h2>
<p>${sessionScope.state.message}</p>
<p>Всього ігор зіграно: ${sessionScope.state.gamesPlayed}</p>

<a href="index.jsp">Почати спочатку</a>
</body>
</html>
