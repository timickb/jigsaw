package me.timickb.jigsaw.domain;

import javafx.application.Platform;

import java.util.Date;
import java.util.Timer;

/**
 * Represents the Jigsaw game.
 */
public class Game {
    private Field field;
    private FigureSpawner figureSpawner;
    private Date startTime;
    private boolean goingOn;
    private int score;

    public Game() {
        field = new Field();
        figureSpawner = new FigureSpawner();
        configureSpawner();
    }

    private void configureSpawner() {

    }

    /**
     * Starts the game.
     */
    public void start() {
        if (goingOn) return;

        goingOn = true;
        score = 0;
        startTime = new Date();

    }

    /**
     * End the game.
     * @return The game result.
     */
    public GameResult end() {
        if (!goingOn) return null;
        goingOn = false;
        long millis = new Date().getTime() - startTime.getTime();
        return new GameResult(score, (int)(millis / 1000));
    }

    public boolean isGoingOn() {
        return goingOn;
    }
}
