package me.timickb.jigsaw.domain;

import me.timickb.jigsaw.domain.enums.FigureReflection;
import me.timickb.jigsaw.domain.enums.FigureRotation;

/**
 * FigureSpawner creation manager.
 */
public class FigureSpawnerCreator {
    /** Generates a set of figures and adds
     * it to new spawner.
     * @return Generated spawner.
     */
    public FigureSpawner create() {
        FigureSpawner spawner = new FigureSpawner();

        createFirstType(spawner);
        createSecondType(spawner);
        createThirdType(spawner);
        createFourthType(spawner);
        createFifthType(spawner);
        createSixthType(spawner);
        createSeventhType(spawner);
        createEighthType(spawner);

        return spawner;
    }

    // Буква "Г"
    private void createFirstType(FigureSpawner spawner) {
        boolean[][] scheme1 = {
                {false, true, true},
                {false, true, false},
                {false, true, false}
        };
        Figure type1r0r0 = new Figure();
        Figure type1r0r1 = new Figure();
        Figure type1r0r2 = new Figure();
        Figure type1r0r3 = new Figure();
        Figure type1r1r0 = new Figure();
        Figure type1r1r1 = new Figure();
        Figure type1r1r2 = new Figure();
        Figure type1r1r3 = new Figure();

        type1r0r0.setCells(scheme1);
        type1r0r1.setCells(scheme1);
        type1r0r2.setCells(scheme1);
        type1r0r3.setCells(scheme1);
        type1r1r0.setCells(scheme1);
        type1r1r1.setCells(scheme1);
        type1r1r2.setCells(scheme1);
        type1r1r3.setCells(scheme1);

        type1r0r1.rotate(FigureRotation.ANTICLOCKWISE, 1);
        type1r0r2.rotate(FigureRotation.ANTICLOCKWISE, 2);
        type1r0r3.rotate(FigureRotation.ANTICLOCKWISE, 3);

        type1r1r0.reflect(FigureReflection.HORIZONTAL);
        type1r1r1.reflect(FigureReflection.HORIZONTAL);
        type1r1r1.rotate(FigureRotation.CLOCKWISE, 1);
        type1r1r2.reflect(FigureReflection.HORIZONTAL);
        type1r1r2.rotate(FigureRotation.CLOCKWISE, 2);
        type1r1r3.reflect(FigureReflection.HORIZONTAL);
        type1r1r3.rotate(FigureRotation.CLOCKWISE, 3);

        spawner.addFigure(type1r0r0);
        spawner.addFigure(type1r0r1);
        spawner.addFigure(type1r0r2);
        spawner.addFigure(type1r0r3);
        spawner.addFigure(type1r1r0);
        spawner.addFigure(type1r1r1);
        spawner.addFigure(type1r1r2);
        spawner.addFigure(type1r1r3);
    }

    // Половина свастики
    private void createSecondType(FigureSpawner spawner) {
        boolean[][] scheme = {
                {false, true, false},
                {false, true, true},
                {false, false, true}
        };

        Figure r0r0 = new Figure();
        Figure r0r1 = new Figure();
        Figure r1r0 = new Figure();
        Figure r1r1 = new Figure();

        r0r0.setCells(scheme);
        r1r1.setCells(scheme);
        r0r1.setCells(scheme);
        r1r0.setCells(scheme);

        r0r1.rotate(FigureRotation.CLOCKWISE, 1);
        r1r0.reflect(FigureReflection.HORIZONTAL);
        r1r1.reflect(FigureReflection.HORIZONTAL);
        r1r1.rotate(FigureRotation.ANTICLOCKWISE, 1);

        spawner.addFigure(r0r0);
        spawner.addFigure(r0r1);
        spawner.addFigure(r1r0);
        spawner.addFigure(r1r1);
    }

    // "Уголок"
    private void createThirdType(FigureSpawner spawner) {
        boolean[][] scheme = {
                {false, false, true},
                {false, false, true},
                {true, true, true}
        };

        Figure r0r0 = new Figure();
        Figure r1r0 = new Figure();
        Figure r1r1 = new Figure();
        Figure r0r1 = new Figure();

        r0r0.setCells(scheme);
        r0r1.setCells(scheme);
        r1r0.setCells(scheme);
        r1r1.setCells(scheme);

        r1r0.reflect(FigureReflection.HORIZONTAL);
        r1r1.reflect(FigureReflection.HORIZONTAL);
        r1r1.rotate(FigureRotation.CLOCKWISE, 1);
        r0r1.reflect(FigureReflection.VERTICAL);

        spawner.addFigure(r0r0);
        spawner.addFigure(r0r1);
        spawner.addFigure(r1r0);
        spawner.addFigure(r1r1);
    }

    // То, что обычно строят в майнкрафте
    private void createFourthType(FigureSpawner spawner) {
        boolean[][] scheme = {
                {false, true, false},
                {false, true, false},
                {true, true, true}
        };
        Figure normal = new Figure();
        Figure reflected = new Figure();
        Figure rotated = new Figure();
        Figure reflectedRotated = new Figure();

        normal.setCells(scheme);
        reflectedRotated.setCells(scheme);
        reflected.setCells(scheme);
        rotated.setCells(scheme);

        reflected.reflect(FigureReflection.VERTICAL);
        rotated.rotate(FigureRotation.CLOCKWISE, 1);
        reflectedRotated.reflect(FigureReflection.VERTICAL);
        reflectedRotated.rotate(FigureRotation.CLOCKWISE, 1);

        spawner.addFigure(normal);
        spawner.addFigure(rotated);
        spawner.addFigure(reflected);
        spawner.addFigure(reflectedRotated);
    }

    // Прямые штуки из трех блоков
    private void createFifthType(FigureSpawner spawner) {
        boolean[][] scheme = {
                {false, false, false},
                {true, true, true},
                {false, false, false}
        };

        Figure normal = new Figure();
        Figure rotated = new Figure();

        normal.setCells(scheme);
        rotated.setCells(scheme);

        rotated.rotate(FigureRotation.CLOCKWISE, 1);

        spawner.addFigure(normal);
        spawner.addFigure(rotated);
    }

    // Один квадратик
    private void createSixthType(FigureSpawner spawner) {
        Figure figure = new Figure();
        figure.setCell(1, 1, true);
        spawner.addFigure(figure);
    }

    // "Уголок", но поменьше
    private void createSeventhType(FigureSpawner spawner) {
        boolean[][] scheme = {
                {false, true, true},
                {false, true, false},
                {false, false, false}
        };

        Figure normal = new Figure();
        Figure rotated1 = new Figure();
        Figure rotated2 = new Figure();
        Figure rotated3 = new Figure();

        normal.setCells(scheme);
        rotated1.setCells(scheme);
        rotated2.setCells(scheme);
        rotated3.setCells(scheme);

        rotated1.rotate(FigureRotation.CLOCKWISE, 1);
        rotated2.rotate(FigureRotation.CLOCKWISE, 2);
        rotated3.rotate(FigureRotation.CLOCKWISE, 3);

        spawner.addFigure(normal);
        spawner.addFigure(rotated1);
        spawner.addFigure(rotated2);
        spawner.addFigure(rotated3);
    }

    // То, что обычно строят в майнкрафте, но короче
    private void createEighthType(FigureSpawner spawner) {
        boolean[][] scheme = {
                {false, false, false},
                {false, true, false},
                {true, true, true}
        };

        Figure normal = new Figure();
        Figure rotated1 = new Figure();
        Figure rotated2 = new Figure();
        Figure rotated3 = new Figure();

        normal.setCells(scheme);
        rotated1.setCells(scheme);
        rotated2.setCells(scheme);
        rotated3.setCells(scheme);

        rotated1.rotate(FigureRotation.CLOCKWISE, 1);
        rotated2.rotate(FigureRotation.CLOCKWISE, 2);
        rotated3.rotate(FigureRotation.CLOCKWISE, 3);

        spawner.addFigure(normal);
        spawner.addFigure(rotated1);
        spawner.addFigure(rotated2);
        spawner.addFigure(rotated3);
    }

}
