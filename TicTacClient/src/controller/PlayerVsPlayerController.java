package controller;

import static controller.PlayerNameDialogController.firstName;
import static controller.PlayerNameDialogController.secondName;
import helper.AccessFile;
import helper.CustomDialog;
import helper.DrawLine;
import static helper.DrawLine.colorBackgroundWinnerButtons;
import static helper.DrawLine.drawLine;
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
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.lang.model.element.Element;
import model.GameSession;
import model.PlayerMove;

public class PlayerVsPlayerController implements Initializable {

    GameSession gameSession = new GameSession();
    boolean isXSymbol = true;
    String symbol;

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
    private void buttonBackPressed(ActionEvent event) {
        SceneController controller = new SceneController();
        try {
            controller.switchToMainScene(event);
            GameBoardComponentController.firstPlayerScore = 0;
            GameBoardComponentController.secondPlayerScore = 0;
        } catch (IOException ex) {
            Logger.getLogger(PlayerVsPlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void replayAgain(String winner) throws BackingStoreException {

        boolean result = CustomDialog.askPlayAgain(winner, "play again");

        if (result) {
            boolean check = CustomDialog.askPlayAgain("Do you want record game",
                    "Record");
            if (check) {
                MainSceneController.isRecord = false;

                AccessFile.createFile("local-mode");
                // AccessFile.writeFile();

                AccessFile.writeFile(pref.get("fristPlayer", "") + ".");
                AccessFile.writeFile(pref.get("secondPlayer", "") + ".");
            }

            //get scene
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
        } else {
            pref.clear();
            //TODO navigate to main to main menu ya 5elan portsaid
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/GameBoardComponent.fxml"));
            fXMLLoader.setController(new GameBoardComponentController());
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

   

}
