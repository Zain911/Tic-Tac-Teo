 

package controller;


import static controller.RecordListController.selectedItemOfRecordList;
import helper.AccessFile;
import static helper.DrawLine.colorBackgroundWinnerButtons;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.PlayerMove;

/**
 * FXML Controller class
 *
 * @author mka
 */
public class ShowRecordedeGameController implements Initializable {

    @FXML
    private Text playerName;
    @FXML
    private Text playerScore;
    @FXML
    private Text pcScore;
    @FXML
    private Text score;
    @FXML
    private Button button22;
    @FXML
    private Button button01;
    @FXML
    private Button button12;
    @FXML
    private Button button11;
    @FXML
    private Button button10;
    @FXML
    private Button button02;
    @FXML
    private Button button21;
    @FXML
    private Button button20;
    @FXML
    private Button button00;
    @FXML
    private ImageView backButton;
    List<PlayerMove> playerMoves;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        showGameInTread();
    }

   

    private void showGamen() {
        System.out.println("in shoe");
        System.out.println(" selecet item is ====>>" +selectedItemOfRecordList);
        if (selectedItemOfRecordList != null && !selectedItemOfRecordList.isEmpty()) {
            playerMoves = AccessFile.readFile(selectedItemOfRecordList);
        
        System.out.println(playerMoves.size());

        for (int i = 0; i < playerMoves.size(); i++) {
            try {
                System.out.println("dd");
                int x = playerMoves.get(i).getX();
                int y = playerMoves.get(i).getY();
                boolean isX = playerMoves.get(i).isIsX();
                Platform.runLater(() -> {
                    switch (x) {

                        case 0:
                            if (y == 0) {
                                System.out.println("bb");
                                aetXoOnButton(button00, isX);
                                break;
                            } else if (y == 1) {
                                aetXoOnButton(button01, isX);
                                break;
                            } else {
                                aetXoOnButton(button02, isX);
                                break;
                            }

                        case 1:
                            if (y == 0) {
                                aetXoOnButton(button10, isX);
                                break;
                            } else if (y == 1) {
                                aetXoOnButton(button11, isX);
                                break;
                            } else {
                                aetXoOnButton(button12, isX);
                                break;
                            }
                        case 2:
                            if (y == 0) {
                                aetXoOnButton(button20, isX);
                                break;
                            } else if (y == 1) {
                                aetXoOnButton(button21, isX);
                                break;
                            } else {
                                aetXoOnButton(button22, isX);
                                break;
                            }

                    }

                });
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                Logger.getLogger(ShowRecordedeGameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        checkIfGameEnds();
        }
    }
        

    private void aetXoOnButton(Button button, boolean isX) {

        if (isX) {
            System.out.println("x");

            button.setText("x");
        } else {
            button.setText("o");

        }

    }

    private void showGameInTread() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                showGamen();
            }
        }).start();

    }

    private void checkIfGameEnds() {
        if (button00.getText().equals(button01.getText()) && button00.getText().equals(button02.getText()) && !button00.getText().equals("")) {

            colorBackgroundWinnerButtons(button00, button01, button02);
            if (button00.getText().equals("X")) {
                playerScore.setText(Integer.valueOf(playerScore.getText()) + 1 + "");

                // showWinnerGif();
            } else {
                pcScore.setText(Integer.valueOf(pcScore.getText()) + 1 + "");

                // showLoserGif();
            }

        }

        if (button10.getText().equals(button11.getText()) && button10.getText().equals(button12.getText()) && !button10.getText().equals("")) {

            colorBackgroundWinnerButtons(button10, button11, button12);
            if (button10.getText().equals("X")) {
                playerScore.setText(Integer.valueOf(playerScore.getText()) + 1 + "");

                //showWinnerGif();
            } else {
                pcScore.setText(Integer.valueOf(pcScore.getText()) + 1 + "");

                // showLoserGif();
            }

        }

        if (button20.getText().equals(button21.getText()) && button20.getText().equals(button22.getText()) && !button20.getText().equals("")) {

            colorBackgroundWinnerButtons(button20, button21, button22);
            if (button20.getText().equals("X")) {
                playerScore.setText(Integer.valueOf(playerScore.getText()) + 1 + "");

                //showWinnerGif();
            } else {
                pcScore.setText(Integer.valueOf(pcScore.getText()) + 1 + "");

                //showLoserGif();
            }
            System.out.println("2");

        }

        if (button00.getText().equals(button10.getText()) && button00.getText().equals(button20.getText()) && !button00.getText().equals("")) {

            colorBackgroundWinnerButtons(button00, button10, button20);
            if (button00.getText().equals("X")) {
                playerScore.setText(Integer.valueOf(playerScore.getText()) + 1 + "");

                //showWinnerGif();
            } else {
                pcScore.setText(Integer.valueOf(pcScore.getText()) + 1 + "");

                //showLoserGif();
            }
            System.out.println("3");
        }

        if (button01.getText().equals(button11.getText()) && button01.getText().equals(button21.getText()) && !button01.getText().equals("")) {

            colorBackgroundWinnerButtons(button01, button11, button21);
            if (button01.getText().equals("X")) {
                playerScore.setText(Integer.valueOf(playerScore.getText()) + 1 + "");

                //showWinnerGif();
            } else {
                pcScore.setText(Integer.valueOf(pcScore.getText()) + 1 + "");

                // showLoserGif();
            }
            System.out.println("4");
        }

        if (button02.getText().equals(button12.getText()) && button02.getText().equals(button22.getText()) && !button02.getText().equals("")) {

            colorBackgroundWinnerButtons(button02, button12, button22);
            if (button02.getText().equals("X")) {
                playerScore.setText(Integer.valueOf(playerScore.getText()) + 1 + "");

                // showWinnerGif();
            } else {
                pcScore.setText(Integer.valueOf(pcScore.getText()) + 1 + "");

                // showLoserGif();
            }
            System.out.println("5");
        }

        if (button00.getText().equals(button11.getText()) && button00.getText().equals(button22.getText()) && !button00.getText().equals("")) {

            colorBackgroundWinnerButtons(button00, button11, button22);
            if (button00.getText().equals("X")) {
                playerScore.setText(Integer.valueOf(playerScore.getText()) + 1 + "");

                //showWinnerGif();
            } else {
                pcScore.setText(Integer.valueOf(pcScore.getText()) + 1 + "");

                //showLoserGif();
            }
            System.out.println("6");
        }

        if (button02.getText().equals(button11.getText()) && button02.getText().equals(button20.getText()) && !button02.getText().equals("")) {

            colorBackgroundWinnerButtons(button02, button11, button20);
            if (button02.getText().equals("X")) {
                playerScore.setText(Integer.valueOf(playerScore.getText()) + 1 + "");

                //showWinnerGif();
            } else {
                pcScore.setText(Integer.valueOf(pcScore.getText()) + 1 + "");

                // showLoserGif();
            }
            System.out.println("7");
        }

    }

    @FXML
    private void startPlay(ActionEvent event) {
    }

    @FXML
    private void onBackButtonClick(MouseEvent event) {
        
        try {
            SceneController controller = new SceneController();
            controller.switchToRecordListScene(event);
        } catch (IOException ex) {
            Logger.getLogger(ShowRecordedeGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    

}
