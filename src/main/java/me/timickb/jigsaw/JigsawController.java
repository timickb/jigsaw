package me.timickb.jigsaw;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.util.Pair;
import me.timickb.jigsaw.algorithm.Algorithm;
import me.timickb.jigsaw.domain.Field;
import me.timickb.jigsaw.domain.Figure;
import me.timickb.jigsaw.domain.Game;
import me.timickb.jigsaw.domain.GameResult;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Responsible for views rendering.
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
    @FXML
    private Label timeView;
    @FXML
    private Label pointCountView;

    private Game game;
    private Group figureView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timeline gameTimer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeView.setText("Время: %d сек.".formatted(game.getSeconds()));
            game.incTime();
        }));
        gameTimer.setCycleCount(Animation.INDEFINITE);

        exitButton.setOnMouseClicked(event -> javafx.application.Platform.exit());
        startButton.setOnMouseClicked(event -> handleStartButtonClick());

        game = new Game(gameTimer);

        figureView = new Group();
        new DraggableMaker().makeDraggable(figureView);
        figureView.setOnMouseReleased(e -> handlePlaceFigure());

        renderField();
        renderSpawnArea();
    }

    protected void handleStartButtonClick() {
        if (!game.isGoingOn()) {
            game.updateFigure();
            renderSpawnArea();
            game.start();
            startButton.setText("Завершить игру");
        } else {
            GameResult result = game.end();
            renderField();

            startButton.setText("Новая игра");
            timeView.setText("Время: 0 сек.");
            pointCountView.setText("Ходы: 0");
            figureView.getChildren().clear();

            showGameEndDialog(result);
        }
    }

    protected void handlePlaceFigure() {
        if (game.getCurrentFigure() == null) {
            return;
        }

        Pair<Long, Long> cellData = Algorithm.computeNearestCell(fieldView, figureView);
        if (cellData == null) {
            return;
        }

        int rowIndex = cellData.getKey().intValue();
        int columnIndex = cellData.getValue().intValue();

        if (game.placeFigure(rowIndex, columnIndex)) {
            renderField();
            game.updateFigure();
            renderSpawnArea();
            pointCountView.setText("Ходы: " + game.getScore());
        } else {
            figureView.setTranslateX(0);
            figureView.setTranslateY(0);
        }
    }

    // Перерисовывает область, в которой появляются фигуры.
    protected void renderSpawnArea() {
        int gap = (int) fieldView.getVgap();
        int areaWidth = Field.CELL_SIZE * 3 + gap * 2;
        int areaHeight = areaWidth;

        spawnerPane.setMinWidth(areaWidth);
        spawnerPane.setMinHeight(areaHeight);

        figureView.getChildren().clear();
        figureView.setTranslateX(0);
        figureView.setTranslateY(0);
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

    // Перерисовывает игровое поле
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

    /**
     * Shows a dialog with game results.
     * @param result Game result object
     */
    protected void showGameEndDialog(GameResult result) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Сообщение");
        alert.setHeaderText("Игра завершена");
        alert.setContentText("Количество ходов: %s; Прошло времени: %s секунд"
                .formatted(result.score(), result.seconds()));
        alert.showAndWait();
    }
}