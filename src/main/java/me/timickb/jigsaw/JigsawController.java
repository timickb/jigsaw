package me.timickb.jigsaw;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import me.timickb.jigsaw.domain.Field;
import me.timickb.jigsaw.domain.Figure;
import me.timickb.jigsaw.domain.Game;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exitButton.setOnMouseClicked(event -> {
            javafx.application.Platform.exit();
        });

        startButton.setOnMouseClicked(event -> {
            spawnFigure();
            if (game.isGoingOn()) {
                // start game
            } else {
                // end game
            }
        });

        game = new Game();
        figureView = new Group();
        new DraggableMaker().makeDraggable(figureView);

        figureView.setOnMouseReleased(e -> handlePlaceFigure());

        renderField();
        renderSpawnArea();
    }

    protected void handlePlaceFigure() {
        if (game.getCurrentFigure() == null) {
            return;
        }
        // Берем левую верхнюю координату figureView.
        // Находим ближайшую к ней координату левого-верхнего угла
        // какой-нибудь клетки поля.
        // Эту координату передаем в game.placeFigure
        // Радуемся
        Pair<Integer, Integer> cellData = computeCoords();

        if (cellData == null) {
            return;
        }

        if (game.placeFigure(cellData.getValue(), cellData.getKey())) {
            renderField();
            spawnFigure();
        }
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
                    cell.setFill(Color.BLUEVIOLET);
                }
                figureView.getChildren().add(cell);
            }
        }
        figureView.setTranslateX(gap * 2);
        figureView.setViewOrder(0);
        fieldView.setViewOrder(1);
        spawnerPane.getChildren().add(figureView);
    }

    // Перерисовывает поле 9x9
    protected void renderField() {
        for (int i = 0; i < Field.SIZE; i++) {
            for (int j = 0; j < Field.SIZE; ++j) {
                fieldView.getChildren().add(renderFieldCell(i, j));
            }
        }
    }

    // Рисует конкретную ячейку поля
    protected Rectangle renderFieldCell(int row, int col) {
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

    // Считает координаты ближайшей подходящей ячейки
    protected Pair<Integer, Integer> computeCoords() {
        Bounds fieldInScene = fieldView.localToScene(fieldView.getBoundsInLocal());
        Bounds figureInScene = figureView.localToScene(figureView.getBoundsInLocal());

        double fieldX = fieldInScene.getMinX() + fieldView.getPadding().getLeft();
        double fieldY = fieldInScene.getMinY() + fieldView.getPadding().getTop();

        double figureX = figureInScene.getMinX();
        double figureY = figureInScene.getMinY();

        int columnIndex = (int)(figureX - fieldX) / (Field.CELL_SIZE + 5);
        int rowIndex = (int)(figureY - fieldY) / (Field.CELL_SIZE + 5);

        System.out.println("Row: %s. Column: %s".formatted(rowIndex, columnIndex));

        System.out.println(fieldX + " " + fieldY);
        System.out.println(figureX + " " + figureY);
        System.out.println();

        return new Pair<>(rowIndex, columnIndex);
    }
}