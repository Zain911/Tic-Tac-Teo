package controller;

import static controller.PlayerNameDialogController.firstName;
import static controller.PlayerNameDialogController.secondName;
import helper.AccessFile;
import helper.CustomDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import model.GameSession;

public class PlayerVsPlayerController implements Initializable {

    GameSession gameSession = new GameSession();
    boolean isXSymbol = true;
    String symbol;
    public static boolean isRecord = false;
    private String player = "X";
    private boolean winner = false;
    private boolean display = false;
    private boolean firstPlayerWinner = false;
    private boolean secondPlayerWinner = false;

    private Preferences pref;

    @FXML
    private BorderPane borderPane;

    @FXML
    public Label scorePlayerOne;

    @FXML
    public Label scorePlayerTwo;
    @FXML
    public Label playerNameOne;
    @FXML
    public Label palyerNameTwo;

    String txtFirstNamePlayer, txtSecondNamePlayer;
    @FXML
    private ImageView backButton;
    @FXML
    private Text recordText;
    @FXML
    private ImageView recordImage;
    

    @FXML
    public void replayAgain() {
        GameBoardComponentController.gameEnded = false;
        System.out.println("bbb");
        isRecord = false;
        recordImage.setDisable(false);
        Image image = new Image(getClass().getResourceAsStream("/resource/oimage.png"));
         System.out.println(image.isError());
        recordImage.setImage(image);
        Parent buttonParent;
        try {
            buttonParent = FXMLLoader.load(getClass().getResource("/view/PlayerVsPlayerView.fxml"));
            //generate new scene
            Scene buttonScene = new Scene(buttonParent);

            //get stage information
            Stage window = (Stage) borderPane.getScene().getWindow();
            window.setTitle("Home");
            window.setScene(buttonScene);
            window.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/GameBoardComponent.fxml"));
            //fXMLLoader.setController(new GameBoardComponentController());
            borderPane.setCenter(fXMLLoader.load());
            GameBoardComponentController boardComponentController = fXMLLoader.getController();
            boardComponentController.setLabelScore(scorePlayerOne, scorePlayerTwo);
            // boardComponentController.setNamePlayer(txtFirstNamePlayer, txtSecondNamePlayer);

            boardComponentController.setPane(borderPane);

            boardComponentController.initPrefPlayer(scorePlayerOne, scorePlayerTwo);
            playerNameOne.setText(firstName);
            palyerNameTwo.setText(secondName);
        } catch (IOException ex) {
            Logger.getLogger(PlayerVsPlayerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BackingStoreException ex) {
            Logger.getLogger(PlayerVsPlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void onBackButtonClick(MouseEvent event) {

        SceneController controller = new SceneController();
        try {
            controller.switchToMainScene(event);
            GameBoardComponentController.scondPlayerScore = 0;
            GameBoardComponentController.firstPlayerScore = 0;
        } catch (IOException ex) {
            Logger.getLogger(PlayerVsPlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    @FXML
    private void onRecordGameClick(MouseEvent event) {
         isRecord = true;
        recordText.setText("Recording...");
       recordImage.setDisable(true);
       Image image = new Image(getClass().getResourceAsStream("/resource/recording.png"));
         
        recordImage.setImage(image);
    }

}
