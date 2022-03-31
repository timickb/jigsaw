package me.timickb.jigsaw.domain;

import me.timickb.jigsaw.domain.enums.FigureReflection;
import me.timickb.jigsaw.domain.enums.FigureRotation;
import me.timickb.jigsaw.exceptions.CellSettingException;

import java.math.MathContext;

public class Figure {
    public static final int MAX_SIZE = 3;

    private boolean[][] cells;

    public Figure() {
        cells = new boolean[MAX_SIZE][MAX_SIZE];
    }

    public void setCells(boolean[][] cells) {
        for (int i = 0; i < MAX_SIZE; ++i) {
            for (int j = 0; j < MAX_SIZE; ++j) {
                this.cells[i][j] = cells[i][j];
            }
        }
    }

    public boolean[][] getCells() {
        return cells;
    }

    public void setCell(int row, int col, boolean active) {
        cells[row][col] = active;
    }

    public boolean getCell(int row, int col) {
        return cells[row][col];
    }

    public void rotate(FigureRotation rotation, int times) {
        for (int round = 0; round < times; ++round) {
            boolean[][] newCells = new boolean[MAX_SIZE][MAX_SIZE];

            if (rotation == FigureRotation.CLOCKWISE) {
                for (int i = 0; i < MAX_SIZE; ++i) {
                    for (int j = 0; j < MAX_SIZE; ++j) {
                        newCells[i][j] = cells[MAX_SIZE - j - 1][i];
                    }
                }
            }
            if (rotation == FigureRotation.ANTICLOCKWISE) {
                for (int i = 0; i < MAX_SIZE; ++i) {
                    for (int j = 0; j < MAX_SIZE; ++j) {
                        newCells[i][j] = cells[j][MAX_SIZE - i - 1];
                    }
                }
            }

            this.cells = newCells;
        }
    }

    public void reflect(FigureReflection reflection) {
        boolean[][] newCells = new boolean[MAX_SIZE][MAX_SIZE];

        if (reflection == FigureReflection.HORIZONTAL) {
            for (int i = 0; i < MAX_SIZE; ++i) {
                for (int j = 0; j < MAX_SIZE; ++j) {
                    newCells[i][j] = cells[i][MAX_SIZE - j - 1];
                }
            }
        }
        if (reflection == FigureReflection.VERTICAL) {
            for (int i = 0; i < MAX_SIZE; ++i) {
                for (int j = 0; j < MAX_SIZE; ++j) {
                    newCells[i][j] = cells[MAX_SIZE - i - 1][j];
                }
            }
        }

        this.cells = newCells;
    }
}
