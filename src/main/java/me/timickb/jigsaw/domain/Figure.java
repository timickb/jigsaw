package me.timickb.jigsaw.domain;

public class Figure {
    public static final int MAX_SIZE = 3;
    private boolean[][] cells;

    public Figure() {
        cells = new boolean[MAX_SIZE][MAX_SIZE];
    }

    public void setCell(int row, int col, boolean active) {
        cells[row][col] = active;
    }

    public boolean getCell(int row, int col) {
        return cells[row][col];
    }
}
