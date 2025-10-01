package org.example.project3;

import static org.example.project3.constants.GameConstants.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.example.project3.constants.GameConstants.*;

public class GameLogic {

    private static final Logger logger = LoggerFactory.getLogger(GameLogic.class);

    public String getNextStep(String currentStep, String choice) {
        logger.info("Обработка шага: {}, выбор игрока: {}", currentStep, choice);

        if (STEP_START.equals(currentStep)) {
            if (CHOICE_ACCEPT.equals(choice)) {
                logger.debug("Игрок принял вызов.");
                return STEP_BRIDGE;
            } else if (CHOICE_DECLINE.equals(choice)) {
                logger.warn("Игрок отклонил вызов.");
                return DEFEAT + ":" + MSG_DECLINE;
            }
        }

        if (STEP_BRIDGE.equals(currentStep)) {
            if (CHOICE_GO.equals(choice)) {
                logger.debug("Игрок идет на мостик.");
                return STEP_IDENTITY;
            } else if (CHOICE_REFUSE.equals(choice)) {
                logger.warn("Игрок отказался идти на мостик.");
                return DEFEAT + ":" + MSG_REFUSE;
            }
        }

        if (STEP_IDENTITY.equals(currentStep)) {
            if (CHOICE_TRUTH.equals(choice)) {
                logger.info("Игрок сказал правду.");
                return VICTORY + ":" + MSG_TRUTH;
            } else if (CHOICE_LIE.equals(choice)) {
                logger.warn("Игрок солгал.");
                return DEFEAT + ":" + MSG_LIE;
            }
        }

        logger.error("Неизвестный шаг или выбор: step={}, choice={}", currentStep, choice);
        return DEFEAT + ":" + MSG_UNKNOWN;
    }
}
