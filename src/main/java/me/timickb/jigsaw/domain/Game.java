package me.timickb.jigsaw.domain;


import me.timickb.jigsaw.exceptions.FigureSpawnerException;

import java.util.Date;

/**
 * Represents the Jigsaw game.
 */
public class Game {
    private Field field;
    private FigureSpawner figureSpawner;
    private Figure currentFigure;
    private Date startTime;
    private boolean goingOn;
    private int score;

    public Game() {
        field = new Field();
        figureSpawner = new FigureSpawnerCreator().create();
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

    public void updateFigure() {
        try {
            currentFigure = figureSpawner.getNext();
        } catch (FigureSpawnerException e) {
            e.printStackTrace();
        }
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

    public FigureSpawner getFigureSpawner() {
        return figureSpawner;
    }

    public Figure getCurrentFigure() {
        return currentFigure;
    }

    public Field getField() {
        return field;
    }
}
