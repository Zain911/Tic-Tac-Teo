/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import com.sun.javafx.scene.control.skin.CustomColorDialog;
import controller.SceneController;
import helper.CustomDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;

import javafx.scene.Parent;
import javafx.scene.control.Dialog;
import javafx.stage.StageStyle;



public class MainSceneController implements Initializable {
    SceneController controller;

    @FXML
    private void handleVsAiBtn(ActionEvent event){
        controller=new SceneController();
        try {
            controller.switchToChooseLevelModeScene(event);
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void handleVsPlayerBtn(ActionEvent event){
        try {
            controller.switchToPlayersNamesScene(event);
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

     @FXML
    private void handleVsPlayerOnlineBtn(ActionEvent event){
        
        try {
             controller=new SceneController();
            
            controller.switchToIpScene(event);
            
  
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
   /* @FXML
=======
    /*public void switchToPlayerVsPlayer() {
        try {
            controller.switchToPlayerVsPlayerScene(eventBtn);
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    
    
   /* @FXML
    private void handleVsPlayerOnlineBtn(ActionEvent event){
        controller = new SceneController();
        try {
            controller.switchToLoginScene(event);

           


        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
         CustomDialog.showIpDialog(event);
        
    }*/
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new SceneController();
    }    

    private void showDialog(ActionEvent event) {
          try {

            Dialog dialog = new Dialog();
            Parent root = FXMLLoader.load(CustomDialog.class.getResource("/view/PlayerNameDialog.fxml"));
            dialog.getDialogPane().setContent(root);
            dialog.initStyle(StageStyle.TRANSPARENT);
            
            dialog.show();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
