package com.gui.clock2.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ClockController implements Initializable {

    @FXML
    Label time_play;

    @FXML
    TextField time_zone;

    @FXML
    Button open;

    @FXML
    private Button close ;

    private boolean check_run_clock = false ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        open.setOnAction(event -> {
            int time_zone_value = Integer.parseInt(this.time_zone.getText());
            Stage newWindow = new Stage();
            newWindow.setTitle("Time in TimeZone: " + time_zone_value);
            try {
                URL url = getClass().getResource("/com/gui/clock2/view/Clock.fxml");
                FXMLLoader loader = new FXMLLoader(url) ;
                Parent root = loader.load();
                ClockController cc = loader.getController();
                cc.update_Time(time_zone_value);
                Scene scene = new Scene(root,400,250);
                newWindow.setScene(scene);
                newWindow.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        close.setOnAction(event -> {
            Stage window_present = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
            window_present.close();
        });
    }

    public void update_Time(int time_zone) {
        new Thread(() -> {
            while (true) {
                ZoneOffset zoneOffset = ZoneOffset.ofHours(time_zone);
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String formattedTime = timeFormatter.format(OffsetDateTime.now(zoneOffset));
                // Cập nhật giao diện người dùng trên luồng UI
                Platform.runLater(() -> {
                    time_play.setText(formattedTime);
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
