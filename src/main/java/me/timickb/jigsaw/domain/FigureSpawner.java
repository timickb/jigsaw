package me.timickb.jigsaw.domain;

import me.timickb.jigsaw.exceptions.FigureSpawnerException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FigureSpawner {
    private Random random;
    private List<Figure> figures;

    public FigureSpawner() {
        random = new Random(System.currentTimeMillis());
        figures = new ArrayList<>();
    }

    public void addFigure(Figure figure) {
        figures.add(figure);
    }

    public void removeFigure(Figure figure) {
        figures.remove(figure);
    }

    /**
     * Returns next figure for player with random value.
     * @return figure
     * @throws FigureSpawnerException
     */
    public Figure getNext() throws FigureSpawnerException {
        if (figures.isEmpty()) {
            throw new FigureSpawnerException();
        }
        int nextId = random.nextInt(figures.size());
        return figures.get(nextId);
    }

    /**
     * Returns a figure by its id.
     * @param id Identifier (type) of figure
     * @return figure
     */
    public Figure getById(int id) {
        return figures.get(id);
    }
}
