package org.example.project3;

import lombok.Data;
import java.io.Serializable;

@Data
public class GameState implements Serializable {
    private final String playerName;
    private int gamesPlayed;
    private boolean finished;
    private String message;

    public void incrementGames() {
        gamesPlayed++;
    }
}
