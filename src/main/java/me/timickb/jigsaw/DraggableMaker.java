package me.timickb.jigsaw;

import javafx.scene.Node;

public class DraggableMaker {
    private double mousePositionX;
    private double mousePositionY;

    private double startX;
    private double startY;

    public void makeDraggable(Node node) {
        if (node == null) {
            return;
        }
        node.setOnMousePressed(event -> {
            startX = event.getSceneX() - node.getTranslateX();
            startY = event.getSceneY() - node.getTranslateY();
        });
        node.setOnMouseDragged(event -> {
           node.setTranslateX(event.getSceneX() - startX);
           node.setTranslateY(event.getSceneY() - startY);
        });
        node.setOnDragDropped(event -> {
            System.out.println("Dropped");
        });
        node.setOnDragDone(event -> {
            System.out.println("Done");
        });
    }
}
