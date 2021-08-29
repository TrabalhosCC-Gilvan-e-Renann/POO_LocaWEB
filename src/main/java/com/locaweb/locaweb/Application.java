package com.locaweb.locaweb;

import com.locaweb.locaweb.Classes.LocaWEB;
import com.locaweb.locaweb.view.SplashController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        LocaWEB locaWeb = new LocaWEB();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/splash.fxml"));
        Parent root = loader.load();

        SplashController controller = loader.getController();
       controller.setLocaWeb(locaWeb);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}