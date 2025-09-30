package org.example.project3;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    private final GameLogic gameLogic = new GameLogic();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("playerName") == null) {
            request.getRequestDispatcher("game.jsp").forward(request, response);
            return;
        }
        if (session.getAttribute("gamesPlayed") == null) {
            session.setAttribute("gamesPlayed", 0);
        }

        request.setAttribute("step", "start");
        request.setAttribute("message", "Ви втрачаєте пам'ять. Прийняти виклик НЛО?");
        request.getRequestDispatcher("game.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");

        String playerName = request.getParameter("playerName");
        if (playerName != null && !playerName.isBlank()) {
            session.setAttribute("playerName", playerName);
            session.setAttribute("gamesPlayed", 0);
            response.sendRedirect(request.getContextPath() + "/game");
            return;
        }

        String step = request.getParameter("step");
        String choice = request.getParameter("choice");

        if ("restart".equals(choice)) {
            response.sendRedirect(request.getContextPath() + "/game");
            return;
        }
        if (step == null || choice == null) {
            response.sendRedirect(request.getContextPath() + "/game");
            return;
        }

        String result = gameLogic.process(step, choice);

        if (result.startsWith("victory") || result.startsWith("defeat")) {
            Integer games = (Integer) session.getAttribute("gamesPlayed");
            session.setAttribute("gamesPlayed", games + 1);

            String[] parts = result.split(":", 2);
            request.setAttribute("step", parts[0]);
            request.setAttribute("message", parts[1]);
        } else {
            request.setAttribute("step", result);

            if ("bridge".equals(result)) {
                request.setAttribute("message", "Ви прийняли виклик. Піднятися на капітанський місток?");
            } else if ("identity".equals(result)) {
                request.setAttribute("message", "Капітан питає, хто ви та звідки...");
            }
        }

        request.getRequestDispatcher("game.jsp").forward(request, response);
    }
}