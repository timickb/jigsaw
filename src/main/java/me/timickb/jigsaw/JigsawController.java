package me.timickb.jigsaw;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import me.timickb.jigsaw.domain.Field;

import java.net.URL;
import java.util.ResourceBundle;

public class JigsawController implements Initializable {
    @FXML
    private Label title;
    @FXML
    private GridPane fieldView;

    private DraggableMaker draggableMaker;
    private Field field;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        field = new Field();
        renderField();
    }

    public void renderField() {
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
}