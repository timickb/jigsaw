package me.timickb.jigsaw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class JigsawApplication extends Application {
    public static final int WINDOW_WIDTH = 950;
    public static final int WINDOW_HEIGHT = 550;

    public static final String STYLE_RESOURCE = "style.css";
    public static final String MARKUP_RESOURCE = "views/main-view.fxml";
    public static final String ICON_RESOURCE = "icon.png";
    public static final String APP_TITLE = "Игра \"Пазл\"";

    @Override
    public void start(Stage stage) throws IOException {
        String styleSheet = getClass().getResource(STYLE_RESOURCE).toExternalForm();

        FXMLLoader mainViewLoader = new FXMLLoader(getClass().getResource(MARKUP_RESOURCE));
        Scene mainScene = new Scene(mainViewLoader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
        mainScene.getStylesheets().add(styleSheet);

        stage.setScene(mainScene);
        stage.setResizable(false);
        stage.setTitle(APP_TITLE);
        stage.getIcons().add(new Image(getClass().getResourceAsStream(ICON_RESOURCE)));

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}