package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.input.MouseEvent;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToOnlineScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/OnlineScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToPlayerVsPlayerScene(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/view/PlayerVsPlayerView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToPlayersNamesScene(ActionEvent event) throws IOException{    
        root = FXMLLoader.load(getClass().getResource("/view/PlayerNameDialog.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void showDialogPlayersNames(ActionEvent event) throws IOException{    
        root = FXMLLoader.load(getClass().getResource("/view/PlayerNameDialog.fxml"));
        scene = new Scene(root, 350, 300);
        Stage stageDialog  = new Stage();
        
        stageDialog.initModality(Modality.APPLICATION_MODAL);
        stageDialog.initStyle(StageStyle.TRANSPARENT);
        stageDialog.setScene(scene);
        stageDialog.show();
    }
    
    public void switchToMainScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/view/MainScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneComputerScene(ActionEvent event) throws IOException {


        root = FXMLLoader.load(getClass().getResource("/view/PlayervsComputerEasyMode.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMinHeight(700);
        stage.setMinWidth(800);
        stage.show();

    }

    public void switchToLoginScene(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/view/loginFxml.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaxHeight(700);
        stage.setWidth(800);
        stage.setMinHeight(700);
        stage.setMinWidth(800);
        stage.show();

    }

    public void switchToRegisterScene(MouseEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/view/registerFxml.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        this.stage.setScene(scene);

        this.stage.show();

    }

    public void switchToChoosePlayerScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/PlayerVsPlayerOnlineMode.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToIpScene(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/view/Dialg.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene =new Scene(root);
        
        stage.setFullScreen(false);
        stage.setMaxHeight(600);
        stage.setMaxWidth(600);

    /*public void switchToChoosePlayerScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/PlayerVsPlayerOnlineMode.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }*/}
     public void switchToChooseLevelModeScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/HardModeFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
    }

}
