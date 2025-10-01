package org.example.project3;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import static org.example.project3.constants.GameConstants.*;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(GameServlet.class);
    private final GameLogic gameLogic = new GameLogic();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        logger.info("GET-запрос от пользователя: {}", session.getId());

        if (session.getAttribute("playerName") == null) {
            logger.debug("Имя игрока не задано. Перенаправление на ввод.");
            request.getRequestDispatcher("game.jsp").forward(request, response);
            return;
        }

        if (session.getAttribute("gamesPlayed") == null) {
            session.setAttribute("gamesPlayed", 0);
            logger.debug("Инициализация счетчика игр.");
        }

        request.setAttribute("step", STEP_START);
        request.setAttribute("message", "Ви втрачаєте пам'ять. Прийняти виклик НЛО?");
        request.getRequestDispatcher("game.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        logger.info("POST-запрос от пользователя: {}", session.getId());

        String playerName = request.getParameter("playerName");
        if (playerName != null && !playerName.isBlank()) {
            logger.info("Игрок зарегистрирован: {}", playerName);
            session.setAttribute("playerName", playerName);
            session.setAttribute("gamesPlayed", 0);
            response.sendRedirect(request.getContextPath() + "/game");
            return;
        }

        String step = request.getParameter("step");
        String choice = request.getParameter("choice");

        if (CHOICE_RESTART.equals(choice)) {
            logger.info("Игрок перезапускает игру.");
            response.sendRedirect(request.getContextPath() + "/game");
            return;
        }

        if (step == null || choice == null) {
            logger.warn("Недопустимые параметры: step={}, choice={}", step, choice);
            response.sendRedirect(request.getContextPath() + "/game");
            return;
        }

        String result = gameLogic.getNextStep(step, choice);
        logger.info("Результат логики игры: {}", result);

        if (result.startsWith(VICTORY) || result.startsWith(DEFEAT)) {
            Integer games = (Integer) session.getAttribute("gamesPlayed");
            session.setAttribute("gamesPlayed", games + 1);
            logger.info("Игры сыграны: {}", games + 1);

            String[] parts = result.split(":", 2);
            request.setAttribute("step", parts[0]);
            request.setAttribute("message", parts.length > 1 ? parts[1] : "Невідомий результат.");
        } else {
            request.setAttribute("step", result);

            if (STEP_BRIDGE.equals(result)) {
                request.setAttribute("message", "Ви прийняли виклик. Піднятися на капітанський місток?");
            } else if (STEP_IDENTITY.equals(result)) {
                request.setAttribute("message", "Капітан питає, хто ви та звідки...");
            }
        }

        request.getRequestDispatcher("game.jsp").forward(request, response);
    }
}