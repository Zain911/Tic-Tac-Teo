package controller;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

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

    public void switchToPlayersNamesScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/PlayerNameDialog.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

   

    public void switchToMainScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/MainScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setMaxWidth(1087);
       stage.setMaxHeight(649);
        stage.setMaximized(false);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToMainScene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/MainScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

       stage.setMaxWidth(1087);
       stage.setMaxHeight(649);
     
        stage.setMaximized(false);
        //stage.setFullScreen(false);
        stage.setScene(scene);
        stage.show();
    }
    


    public void switchToSceneComputerScene(MouseEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/view/PlayervsComputerEasyMode.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        
        stage.show();

    }

    public void switchToLoginScene(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/view/loginFxml.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
       
        stage.show();

    }
    public void switchToLoginScene(MouseEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/view/loginFxml.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        
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
        
        
        stage.setOnCloseRequest((WindowEvent e) -> {
            try {
                Platform.exit();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    public void switchToIpScene(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/view/ipDialg.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setFullScreen(false);
       
        stage.setScene(scene);
        stage.show();
    }

    
        /*public void switchToChoosePlayerScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/PlayerVsPlayerOnlineMode.fxml"));
>>>>>>> 143493edc3b0771d4ecaf9e86702d6844111fbea
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.centerOnScreen();

        stage.setScene(scene);

        stage.show();

    }
*/
  public void switchToChooseLevelModeScene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/HardModeFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.centerOnScreen();
         stage.setScene(scene);

        stage.show();

    }
    

    public void switchToChooseLevelModeScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/HardModeFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToRecordListScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/RecordList.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
public void switchToRecordListScene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/RecordList.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToShowRecordScene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/showRecordedeGame.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    
}
