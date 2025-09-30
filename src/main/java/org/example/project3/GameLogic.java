package org.example.project3;

public class GameLogic {

    public String process(String step, String choice) {
        if ("start".equals(step)) {
            if ("accept".equals(choice)) {
                return "bridge";
            } else if ("decline".equals(choice)) {
                return "defeat:Ви відхилили виклик.";
            }
        }

        if ("bridge".equals(step)) {
            if ("go".equals(choice)) {
                return "identity";
            } else if ("refuse".equals(choice)) {
                return "defeat:Ви не пішли на переговори.";
            }
        }

        if ("identity".equals(step)) {
            if ("truth".equals(choice)) {
                return "victory:Вас повернули додому.";
            } else if ("lie".equals(choice)) {
                return "defeat:Ваша брехня була викрита.";
            }
        }

        return "defeat:Невідомий вибір.";
    }
}
