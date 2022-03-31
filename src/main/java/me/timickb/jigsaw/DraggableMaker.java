package me.timickb.jigsaw;

import javafx.scene.Node;

public class DraggableMaker {
    private double mousePositionX;
    private double mousePositionY;

    public void makeDraggable(Node node) {
        if (node == null) {
            return;
        }
        node.setOnMousePressed(event -> {
            mousePositionX = event.getX();
            mousePositionY = event.getY();
        });
        node.setOnMouseDragged(event -> {
            node.setLayoutX(event.getSceneX() - mousePositionX);
            node.setLayoutX(event.getSceneY() - mousePositionY);
        });
    }
}
