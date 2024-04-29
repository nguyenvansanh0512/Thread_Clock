package com.gui.clock2.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage window) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/gui/clock2/view/Clock.fxml"));
            Scene scene = new Scene(root,400,250);
            window.setScene(scene);
            window.setTitle("Clock");
            window.centerOnScreen();
            window.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
