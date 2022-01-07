/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author PCM
 */
public class LoserGifXmlController implements Initializable {

    @FXML
    private ImageView imgLoser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image imageLose = new Image("/resource/loser.gif");
                
        imgLoser.setImage(imageLose);
        imgLoser.setCache(true);
        sleepScreen();
        
    }    
    
        private void sleepScreen() {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
            try {
               Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
               return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                imgLoser.getScene().getWindow().hide();
            }
        });
        
        new Thread(sleeper).start();
    }
    
}
