package me.timickb.jigsaw.algorithm;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import me.timickb.jigsaw.domain.Field;
import me.timickb.jigsaw.domain.GameResult;


/**
 * Different stuff
 */
public class Algorithm {
    /**
     * @param fieldView Game field view
     * @param figureView Game figure view
     * @return Integer pair: row and column of computed field cell.
     */
    public static Pair<Long, Long> computeNearestCell(GridPane fieldView, Group figureView) {
        Bounds fieldInScene = fieldView.localToScene(fieldView.getBoundsInLocal());
        Bounds figureInScene = figureView.localToScene(figureView.getBoundsInLocal());

        double fieldX = fieldInScene.getMinX() + fieldView.getPadding().getLeft();
        double fieldY = fieldInScene.getMinY() + fieldView.getPadding().getTop();

        double figureX = figureInScene.getMinX();
        double figureY = figureInScene.getMinY();

        long columnIndex = Math.round((figureX - fieldX) / (Field.CELL_SIZE + 5));
        long rowIndex = Math.round((figureY - fieldY) / (Field.CELL_SIZE + 5));

        return new Pair<>(columnIndex, rowIndex);
    }


}
