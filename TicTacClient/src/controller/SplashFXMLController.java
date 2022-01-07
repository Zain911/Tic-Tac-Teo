/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PCM
 */
public class SplashFXMLController implements Initializable {

    @FXML
    private ImageView headerImg;
    @FXML
    private ImageView imgBack;
    @FXML
    private ImageView ticImg;
    @FXML
    private ProgressBar prog;

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            // TODO
            try {
                Image imageTop = new Image("/resource/tictactoe2.png") {};
                Image imageBack = new Image("/resource/back2.jpg") {};
                Image imageTic = new Image("/resource/ticBack.png") {};
                
                headerImg.setImage(imageTop);
                headerImg.setCache(true);
                
                imgBack.setImage(imageBack);
                imgBack.setCache(true);
                
                ticImg.setImage(imageTic);
                ticImg.setCache(true);
                
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            sleepScreen();
                
    }
    
    
    private void goToMainScreen() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/MainScene.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            } catch (IOException ex) {
                Logger.getLogger(SplashFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    private void closeSplashScreen() {
        Stage stage = (Stage) prog.getScene().getWindow();
        stage.close();
    }

    private void sleepScreen() {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
            try {
               Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
               return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                goToMainScreen();
                closeSplashScreen();
            }
        });
        
        new Thread(sleeper).start();
    }
    
    
}
