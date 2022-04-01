package me.timickb.jigsaw;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import me.timickb.jigsaw.domain.Field;
import me.timickb.jigsaw.domain.Figure;
import me.timickb.jigsaw.domain.Game;
import me.timickb.jigsaw.exceptions.FigureSpawnerException;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * App controller.
 */
public class JigsawController implements Initializable {
    @FXML
    private GridPane fieldView;
    @FXML
    private Button exitButton;
    @FXML
    private Button startButton;
    @FXML
    private Pane spawnerPane;

    private Game game;
    private Group figureView;
    private DraggableMaker draggableMaker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exitButton.setOnMouseClicked(event -> {
            javafx.application.Platform.exit();
        });

        startButton.setOnMouseClicked(event -> {
            if (game.isGoingOn()) {
                startGame();
            } else {
                endGame();
            }
        });
        game = new Game();
        figureView = new Group();
        figureView.setViewOrder(1000);
        draggableMaker = new DraggableMaker();
        draggableMaker.makeDraggable(figureView);

        renderField();
        renderSpawnArea();
        spawnFigure();
    }

    protected void spawnFigure() {
        game.updateFigure();
        renderSpawnArea();
    }

    // Перерисовывает область, в которой появляются фигуры.
    protected void renderSpawnArea() {
        int gap = (int) fieldView.getVgap();
        int areaWidth = Field.CELL_SIZE * 3 + gap * 2;
        int areaHeight = areaWidth;

        spawnerPane.setMinWidth(areaWidth);
        spawnerPane.setMinHeight(areaHeight);

        figureView.getChildren().clear();
        spawnerPane.getChildren().clear();

        for (int i = 0; i < Figure.MAX_SIZE; ++i) {
            for (int j = 0; j < Figure.MAX_SIZE; ++j) {
                Rectangle cell = new Rectangle(Field.CELL_SIZE, Field.CELL_SIZE,
                        Color.TRANSPARENT);
                cell.setTranslateX((Field.CELL_SIZE + gap) * i);
                cell.setTranslateY((Field.CELL_SIZE + gap) * j);
                if (game.getCurrentFigure() != null
                        && game.getCurrentFigure().getCell(i, j)) {
                    cell.setFill(Color.BLACK);
                }
                figureView.getChildren().add(cell);
            }
        }
        figureView.setTranslateX(gap * 2);
        figureView.setViewOrder(0);
        fieldView.setViewOrder(1);
        spawnerPane.getChildren().add(figureView);
    }

    protected void renderField() {
        for (int i = 0; i < Field.SIZE; i++) {
            for (int j = 0; j < Field.SIZE; ++j) {
                fieldView.getChildren().add(getCellRectangle(i, j));
            }
        }
    }

    protected Rectangle getCellRectangle(int row, int col) {
        Rectangle cell = new Rectangle();
        cell.setWidth(Field.CELL_SIZE);
        cell.setHeight(Field.CELL_SIZE);
        if (!game.getField().getCell(row, col)) {
            cell.setFill(Color.LIGHTGRAY);
        } else {
            cell.setFill(Color.BLACK);
        }
        GridPane.setConstraints(cell, row, col);

        return cell;
    }

    protected Rectangle getSpawnerCellRectangle(int row, int col) {
        Rectangle cell = new Rectangle();
        cell.setWidth(Field.CELL_SIZE);
        cell.setHeight(Field.CELL_SIZE);
        cell.setFill(Paint.valueOf("white"));
        GridPane.setConstraints(cell, row, col);

        return cell;
    }

    protected void startGame() {
        game.start();
        switchStartButton();
    }

    protected void endGame() {
        game.end();
        switchStartButton();
    }

    protected void switchStartButton() {
        if(game.isGoingOn()) {
            startButton.setText("Завершить игру");
        } else {
            startButton.setText("Новая игра");
        }
    }
}