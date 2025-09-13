package com.sji.rmiprotocoldemo.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        int height = 500;
        int width = 350;

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/calculator.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle("RMI Calculator");
        stage.setScene(scene);
        stage.show();
    }
}
