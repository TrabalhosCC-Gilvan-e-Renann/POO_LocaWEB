package com.locaweb.locaweb.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.locaweb.locaweb.Classes.LocaWEB;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class SplashController implements Initializable {

    private LocaWEB locaWeb;

    @FXML
    private ImageView imageView;

    @FXML
    private AnchorPane apane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image("/documentary.png");
        imageView.setImage(image);
        imageView.setCache(true);

        splash();
    }
    private void splash() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1350);
                } catch (Exception e) {
                    System.out.println(e);
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login-view.fxml"));
                            Parent root = loader.load();
                            Stage stage = (Stage)apane.getScene().getWindow();

                            LoginController controller = loader.getController();
                            controller.setLocaWeb(locaWeb);

                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.centerOnScreen();
                            stage.show();
                        } catch (IOException ex) {

                        }
                    }
                });
            }
        }.start();
    }
    public void setLocaWeb(LocaWEB locaWeb) {
        this.locaWeb = locaWeb;
    }
}