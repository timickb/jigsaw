package me.timickb.jigsaw.domain;

public class Field {
    public static final int SIZE = 9;
    public static final int CELL_SIZE = 40;

    private boolean[][] cells;

    public Field() {
        cells = new boolean[SIZE][SIZE];
    }

    public boolean getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCell(int row, int col, boolean value) {
        cells[row][col] = value;
    }

    public boolean place(Figure figure, int topLeftRow, int topLeftCol) {

        return true;
    }
}