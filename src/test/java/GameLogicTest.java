import org.example.project3.GameLogic;
import org.junit.jupiter.api.Test;

import static org.example.project3.constants.GameConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameLogicTest {
    private final GameLogic gameLogic = new GameLogic();

    @Test
    void testStartAccept() {
        String result = gameLogic.getNextStep(STEP_START, CHOICE_ACCEPT);
        assertEquals(STEP_BRIDGE, result);
    }

    @Test
    void testStartDecline() {
        String result = gameLogic.getNextStep(STEP_START, CHOICE_DECLINE);
        assertEquals(DEFEAT + MSG_DECLINE, result);
    }

    @Test
    void testBridgeGo() {
        String result = gameLogic.getNextStep(STEP_BRIDGE, CHOICE_GO);
        assertEquals(STEP_IDENTITY, result);
    }

    @Test
    void testBridgeRefuse() {
        String result = gameLogic.getNextStep(STEP_BRIDGE, CHOICE_REFUSE);
        assertEquals(DEFEAT + MSG_REFUSE, result);
    }

    @Test
    void testIdentityTruth() {
        String result = gameLogic.getNextStep(STEP_IDENTITY, CHOICE_TRUTH);
        assertEquals(VICTORY + MSG_TRUTH, result);
    }

    @Test
    void testIdentityLie() {
        String result = gameLogic.getNextStep(STEP_IDENTITY, CHOICE_LIE);
        assertEquals(DEFEAT + MSG_LIE, result);
    }

    @Test
    void testUnknownChoice() {
        String result = gameLogic.getNextStep(STEP_UNKNOWN,CHOICE_UNKNOWN);
        assertEquals(DEFEAT + MSG_UNKNOWN, result);
    }
}
