package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SceneController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public void switchToOnlineScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/view/OnlineScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    public void switchToPlayerVsPlayerScene(ActionEvent event) throws IOException{    
        root = FXMLLoader.load(getClass().getResource("/view/PlayerVsPlayerView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToMainScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/view/MainScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToSceneComputerScene(ActionEvent event) throws IOException{
  
        root = FXMLLoader.load(getClass().getResource("/view/PlayervsComputerEasyMode.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
           
    public void switchToLoginScene(ActionEvent event) throws IOException{
  
        root = FXMLLoader.load(getClass().getResource("/view/loginFxml.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
         //((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    public void switchToChoosePlayerScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/view/PlayerVsPlayerOnlineMode.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
 
    
}
