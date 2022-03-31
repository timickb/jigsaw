package me.timickb.jigsaw;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import me.timickb.jigsaw.domain.Field;
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

    private Game game;

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
        renderField();
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
        cell.setFill(Paint.valueOf("lightgray"));
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