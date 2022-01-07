/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.AccessFile;
import helper.CustomDialog;

import static helper.DrawLine.colorBackgroundWinnerButtons;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.json.JSONException;

/**
 * FXML Controller class
 *
 * @author mka
 */
public class PlayervsComputerEasyModeController implements Initializable {

    @FXML
    private Button btn00;
    @FXML
    private Button btn01;
    @FXML
    private Button btn02;
    @FXML
    private Button btn10;
    @FXML
    private Button btn11;
    @FXML
    private Button btn12;
    @FXML
    private Button btn20;
    @FXML
    private Button btn21;
    @FXML
    private Button btn22;
    @FXML
    private Text playerName;
    @FXML
    private Text playerScore;
    @FXML
    private Text pcScore;
    @FXML
    private Text score;
    Image imgo, imgx;
    ImageView image;
    @FXML
    private Button playAginButton;
    Button[] boardButtons = new Button[3 * 3];
    Button[] cornersButtons = new Button[4];

    boolean winer;

    //  boolean winer = false;
    boolean isGameEnded;
    boolean isPlayerTurn = true;
    boolean isPcTurn = false;
    int XOCounter = 0;

    Color xForeground = Color.YELLOW;
    Color oForeground = Color.YELLOW;
    Random random = new Random();
    int randomNumber;
    String mode;
    @FXML
    private ImageView backButton;
    @FXML
    private GridPane buttonsGrid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setButtondInArray();
        imgo = new Image(getClass().getResourceAsStream("/resource/oimage.png"));

        imgx = new Image(getClass().getResourceAsStream("/resource/ximage.png"));

        //to make computer start play
        //button11.setText("O");
        //XOCounter++;
    }

    @FXML
    private void buttonOnePressed(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (isGameEnded == false && clickedButton.getText().equals("")) {

            XOCounter++;
            System.out.println("count for test : " + XOCounter);

            isPlayerTurn = true;
           clickedButton.setTextFill(xForeground);
            clickedButton.setText("X");
            clickedButton.setGraphic(new ImageView(imgx));
            clickedButton.setStyle("-fx-text-fill:transparent;");

            checkIfGameEnds();
            if (isGameEnded == false) {
                XOCounter++;
                isPlayerTurn = false;
                if (mode.equals("easy")) {

                    randomChoseBtn();
                } else {
                    hardMode();
                }
                checkIfGameEnds();
            }
            checkIfGameEnds();

        }
    }

    private void hardMode() {
        if (XOCounter == 2) {
            if (boardButtons[4].getText().isEmpty()) {
                boardButtons[4].setText("O");
                boardButtons[4].setGraphic(new ImageView(imgo));
                boardButtons[4].setStyle("-fx-text-fill:transparent;");
                System.out.println("center x");
            } else {
                setMarkToCornerButton();
                System.out.println("corner x");
            }
        } else {
            setMarkToOtherButtons();
            System.out.println("other btn");
        }
    }

    private Button[] cornerButtonsInit() {
        cornersButtons[0] = btn00;
        cornersButtons[1] = btn02;
        cornersButtons[2] = btn20;
        cornersButtons[3] = btn22;
        return cornersButtons;
    }

    private void setMarkToCornerButton() {
        int randomCorner;
        for (;;) {
            randomCorner = random.nextInt(4);
            if (cornerButtonsInit()[randomCorner].getText().isEmpty()) {
               cornerButtonsInit()[randomCorner].setTextFill(oForeground);
                cornerButtonsInit()[randomCorner].setText("O");
                cornerButtonsInit()[randomCorner].setStyle("-fx-text-fill:transparent;");

                break;
            }
        }
    }

    private void setMarkToOtherButtons() {

        // check first row
        if ((boardButtons[0].getText().equals(boardButtons[1].getText())
                && !boardButtons[1].getText().isEmpty() && boardButtons[2].getText().isEmpty())
                || (boardButtons[1].getText().equals(boardButtons[2].getText())
                && !boardButtons[2].getText().isEmpty() && boardButtons[0].getText().isEmpty())
                || (boardButtons[0].getText().equals(boardButtons[2].getText())
                && !boardButtons[2].getText().isEmpty() && boardButtons[1].getText().isEmpty())) {

            checkFirstRow();

            //check secound row
        } else if ((boardButtons[3].getText().equals(boardButtons[4].getText())
                && !boardButtons[4].getText().isEmpty() && boardButtons[5].getText().isEmpty())
                || (boardButtons[4].getText().equals(boardButtons[5].getText())
                && !boardButtons[5].getText().isEmpty() && boardButtons[3].getText().isEmpty())
                || (boardButtons[3].getText().equals(boardButtons[5].getText())
                && !boardButtons[5].getText().isEmpty() && boardButtons[4].getText().isEmpty())) {

            checkSecoundRow();

            // check third row
        } else if ((boardButtons[6].getText().equals(boardButtons[7].getText())
                && !boardButtons[7].getText().isEmpty() && boardButtons[8].getText().isEmpty())
                || (boardButtons[7].getText().equals(boardButtons[8].getText())
                && !boardButtons[8].getText().isEmpty() && boardButtons[6].getText().isEmpty())
                || (boardButtons[6].getText().equals(boardButtons[8].getText())
                && !boardButtons[8].getText().isEmpty() && boardButtons[7].getText().isEmpty())) {

            checkThirdRow();

            //check first column
        } else if ((boardButtons[0].getText().equals(boardButtons[3].getText())
                && !boardButtons[3].getText().isEmpty() && boardButtons[6].getText().isEmpty())
                || (boardButtons[3].getText().equals(boardButtons[6].getText())
                && !boardButtons[6].getText().isEmpty() && boardButtons[0].getText().isEmpty())
                || (boardButtons[0].getText().equals(boardButtons[6].getText())
                && !boardButtons[6].getText().isEmpty() && boardButtons[3].getText().isEmpty())) {

            checkFirstColumn();

            // check secound column
        } else if ((boardButtons[1].getText().equals(boardButtons[4].getText())
                && !boardButtons[4].getText().isEmpty() && boardButtons[7].getText().isEmpty())
                || (boardButtons[4].getText().equals(boardButtons[7].getText())
                && !boardButtons[7].getText().isEmpty() && boardButtons[1].getText().isEmpty())
                || (boardButtons[1].getText().equals(boardButtons[7].getText())
                && !boardButtons[7].getText().isEmpty() && boardButtons[4].getText().isEmpty())) {

            checkSecoundCoulmn();

            // check third column
        } else if ((boardButtons[2].getText().equals(boardButtons[5].getText())
                && !boardButtons[5].getText().isEmpty() && boardButtons[8].getText().isEmpty())
                || (boardButtons[5].getText().equals(boardButtons[8].getText())
                && !boardButtons[8].getText().isEmpty() && boardButtons[2].getText().isEmpty())
                || (boardButtons[2].getText().equals(boardButtons[8].getText())
                && !boardButtons[8].getText().isEmpty() && boardButtons[5].getText().isEmpty())) {

            checkThirdColumn();

            // check first diagonal
        } else if ((boardButtons[0].getText().equals(boardButtons[4].getText())
                && !boardButtons[4].getText().isEmpty() && boardButtons[8].getText().isEmpty())
                || (boardButtons[4].getText().equals(boardButtons[8].getText())
                && !boardButtons[8].getText().isEmpty() && boardButtons[0].getText().isEmpty())
                || (boardButtons[8].getText().equals(boardButtons[0].getText()))
                && !boardButtons[0].getText().isEmpty() && boardButtons[4].getText().isEmpty()) {

            checkFirstDigonal();

            // check secound diagonal
        } else if ((boardButtons[2].getText().equals(boardButtons[4].getText())
                && !boardButtons[4].getText().isEmpty() && boardButtons[6].getText().isEmpty())
                || (boardButtons[4].getText().equals(boardButtons[6].getText())
                && !boardButtons[6].getText().isEmpty() && boardButtons[2].getText().isEmpty())
                || (boardButtons[2].getText().equals(boardButtons[6].getText())
                && !boardButtons[6].getText().isEmpty() && boardButtons[4].getText().isEmpty())) {

            checkSecoundDiagonal();

        } else {
            randomChoseBtn();
        }
    }

    private void randomChoseBtn() {
        for (;;) {

            randomNumber = random.nextInt(9);
            if (boardButtons[randomNumber].getText().equals("")) {
                boardButtons[randomNumber].setTextFill(oForeground);
                boardButtons[randomNumber].setText("O");
                boardButtons[randomNumber].setStyle("-fx-text-fill:transparent;");

                boardButtons[randomNumber].setGraphic(new ImageView(imgo));

                System.out.println("randomm");
                break;
            }
        }
    }

    private void checkFirstRow() {
        System.out.println("first row");
        if (boardButtons[0].getText().isEmpty()) {
            boardButtons[0].setText("O");
            boardButtons[0].setGraphic(new ImageView(imgo));
            boardButtons[0].setStyle("-fx-text-fill:transparent;");

        } else if (boardButtons[1].getText().isEmpty()) {
            boardButtons[1].setText("O");
            boardButtons[1].setGraphic(new ImageView(imgo));
            boardButtons[1].setStyle("-fx-text-fill:transparent;");

        } else if (boardButtons[2].getText().isEmpty()) {
            boardButtons[2].setText("O");
            boardButtons[2].setGraphic(new ImageView(imgo));
            boardButtons[2].setStyle("-fx-text-fill:transparent;");

        }
    }

    private void checkSecoundRow() {
        System.out.println("secound row");
        if (boardButtons[3].getText().isEmpty()) {
            boardButtons[3].setText("O");
            boardButtons[3].setGraphic(new ImageView(imgo));

        } else if (boardButtons[4].getText().isEmpty()) {
            boardButtons[4].setText("O");
            boardButtons[4].setGraphic(new ImageView(imgo));

        } else if (boardButtons[5].getText().isEmpty()) {
            boardButtons[5].setText("O");
            boardButtons[5].setGraphic(new ImageView(imgo));

        }
    }

    private void checkThirdRow() {
        System.out.println("third row");
        if (boardButtons[6].getText().isEmpty()) {
            boardButtons[6].setText("O");
            boardButtons[6].setGraphic(new ImageView(imgo));

        } else if (boardButtons[7].getText().isEmpty()) {
            boardButtons[7].setText("O");
            boardButtons[7].setGraphic(new ImageView(imgo));

        } else if (boardButtons[8].getText().isEmpty()) {
            boardButtons[8].setText("O");
            boardButtons[8].setGraphic(new ImageView(imgo));

        }
    }

    private void checkFirstColumn() {
        System.out.println("first coulmn");
        if (boardButtons[0].getText().isEmpty()) {
            boardButtons[0].setText("O");
            boardButtons[0].setGraphic(new ImageView(imgo));

        } else if (boardButtons[3].getText().isEmpty()) {
            boardButtons[3].setText("O");
            boardButtons[3].setGraphic(new ImageView(imgo));

        } else if (boardButtons[6].getText().isEmpty()) {
            boardButtons[6].setText("O");
            boardButtons[6].setGraphic(new ImageView(imgo));

        }
    }

    private void checkSecoundCoulmn() {
        System.out.println("secound coulmn");
        if (boardButtons[1].getText().isEmpty()) {
            boardButtons[1].setText("O");
            boardButtons[1].setGraphic(new ImageView(imgo));

        } else if (boardButtons[4].getText().isEmpty()) {
            boardButtons[4].setText("O");
            boardButtons[4].setGraphic(new ImageView(imgo));
            boardButtons[4].setStyle("-fx-text-fill:transparent;");

        } else if (boardButtons[7].getText().isEmpty()) {
            boardButtons[7].setText("O");
            boardButtons[7].setGraphic(new ImageView(imgo));

        }
    }

    private void checkThirdColumn() {
        System.out.println("third coulmn");
        if (boardButtons[2].getText().isEmpty()) {
            boardButtons[2].setText("O");

            boardButtons[2].setGraphic(new ImageView(imgo));

        } else if (boardButtons[5].getText().isEmpty()) {
            boardButtons[5].setText("O");
            boardButtons[5].setGraphic(new ImageView(imgo));

        } else if (boardButtons[8].getText().isEmpty()) {
            boardButtons[8].setText("O");
            boardButtons[8].setGraphic(new ImageView(imgo));

        }
    }

    private void checkFirstDigonal() {
        System.out.println("first diagonal");
        if (boardButtons[0].getText().isEmpty()) {
            boardButtons[0].setText("O");
            boardButtons[0].setGraphic(new ImageView(imgo));

        } else if (boardButtons[4].getText().isEmpty()) {
            boardButtons[4].setText("O");
            boardButtons[4].setGraphic(new ImageView(imgo));

        } else if (boardButtons[8].getText().isEmpty()) {
            boardButtons[8].setText("O");
            boardButtons[8].setGraphic(new ImageView(imgo));

        }
    }

    private void checkSecoundDiagonal() {
        System.out.println("secound diagonal");
        if (boardButtons[2].getText().isEmpty()) {
            boardButtons[2].setText("O");
            boardButtons[2].setGraphic(new ImageView(imgo));

        } else if (boardButtons[4].getText().isEmpty()) {
            boardButtons[4].setText("O");
            boardButtons[4].setGraphic(new ImageView(imgo));

        } else if (boardButtons[6].getText().isEmpty()) {
            boardButtons[6].setText("O");
            boardButtons[6].setGraphic(new ImageView(imgo));

        }
    }

    ////////////////////////////////
    public void checkHardOrEasy(String modeFromUser) {
        mode = modeFromUser;
    }

    private void checkIfGameEnds() {
        if (btn00.getText().equals(btn01.getText()) && btn00.getText().equals(btn02.getText()) && !btn00.getText().equals("")) {
            isGameEnded = true;
            winer = true;
           
            colorBackgroundWinnerButtons(btn00, btn01, btn02);
            if (btn00.getText().equals("X")) {
                showWinnerGif();

            } else {
                showLoserGif();
        //winer=true;
                // colorBackgroundWinnerButtons(button00, button01, button01);
            }
            winer = true;

        }

        if (btn10.getText().equals(btn11.getText()) && btn10.getText().equals(btn12.getText()) && !btn10.getText().equals("")) {
            isGameEnded = true;
            winer = true;
            colorBackgroundWinnerButtons(btn10, btn11, btn12);
           
            if (btn10.getText().equals("X")) {
                showWinnerGif();
            } else {
                showLoserGif();
            }
            System.out.println("1");

        }

        if (btn20.getText().equals(btn21.getText()) && btn20.getText().equals(btn22.getText()) && !btn20.getText().equals("")) {
            isGameEnded = true;
            winer = true;
            colorBackgroundWinnerButtons(btn20, btn21, btn22);
             
            if (btn20.getText().equals("X")) {
                showWinnerGif();
            } else {
                showLoserGif();
            }
            System.out.println("2");

        }

        if (btn00.getText().equals(btn10.getText()) && btn00.getText().equals(btn20.getText()) && !btn00.getText().equals("")) {
            isGameEnded = true;
            winer = true;
            colorBackgroundWinnerButtons(btn00, btn10, btn20);
            if (btn00.getText().equals("X")) {
                showWinnerGif();
            } else {
                showLoserGif();
            }
            System.out.println("3");
        }

        if (btn01.getText().equals(btn11.getText()) && btn01.getText().equals(btn21.getText()) && !btn01.getText().equals("")) {
            isGameEnded = true;
            winer = true;
            colorBackgroundWinnerButtons(btn01, btn11, btn21);
            if (btn01.getText().equals("X")) {
                showWinnerGif();
            } else {
                showLoserGif();
            }
            System.out.println("4");
        }

        if (btn02.getText().equals(btn12.getText()) && btn02.getText().equals(btn22.getText()) && !btn02.getText().equals("")) {
            isGameEnded = true;
            winer = true;
            colorBackgroundWinnerButtons(btn02, btn12, btn22);
            if (btn02.getText().equals("X")) {
                showWinnerGif();
            } else {
                showLoserGif();
            }
            System.out.println("5");
        }

        if (btn00.getText().equals(btn11.getText()) && btn00.getText().equals(btn22.getText()) && !btn00.getText().equals("")) {
            isGameEnded = true;
            winer = true;
            colorBackgroundWinnerButtons(btn00, btn11, btn22);
      
            if (btn00.getText().equals("X")) {
                showWinnerGif();
            } else {
                showLoserGif();
            }
            System.out.println("6");
        }

        if (btn02.getText().equals(btn11.getText()) && btn02.getText().equals(btn20.getText()) && !btn02.getText().equals("")) {
            isGameEnded = true;
            winer = true;
            colorBackgroundWinnerButtons(btn02, btn11, btn20);
            if (btn02.getText().equals("X")) {
                showWinnerGif();
            } else {
                showLoserGif();
            }
            System.out.println("7");
        }

        if (XOCounter >= 9) {
            isGameEnded = true;
            playAginButton.setVisible(true);
            isPlayerTurn = true;
            // isPlayerTurn = true;
            XOCounter = 0;
        }

        if (isGameEnded == true) {
            if (winer) {
                if (isPlayerTurn) {
                    playerScore.setText(Integer.valueOf(playerScore.getText()) + 1 + "");
                } else {
                    pcScore.setText(Integer.valueOf(pcScore.getText()) + 1 + "");
                }

            } else {

            }
            XOCounter = 0;
            playAginButton.setDisable(false);
        }

    }
    
    private void setButtondInArray() {
        boardButtons[0] = btn00;
        boardButtons[1] = btn01;
        boardButtons[2] = btn02;
        boardButtons[3] = btn10;
        boardButtons[4] = btn11;
        boardButtons[5] = btn12;
        boardButtons[6] = btn20;
        boardButtons[7] = btn21;
        boardButtons[8] = btn22;

    }

    @FXML
    private void playAgin() {
        playAginButton.setDisable(true);
        XOCounter = 0;
        isGameEnded = false;
        XOCounter = 0;
        winer = false;


        for (Button boardButton : boardButtons) {
            boardButton.setText("");
            boardButton.setStyle("-fx-background-color:");
                        boardButton.setGraphic(new ImageView());

        }

        //to make computer start play
        //button11.setText("O");
        //XOCounter++;
    }

   

    private void showWinnerGif() {
        CustomDialog.creatWinDialog();
    }

    private void showLoserGif() {
        CustomDialog.creatLoseDialog();
    }

  
    

    @FXML
    private void onBackButtonClick(MouseEvent event) {
        System.out.println("   back");
         SceneController controller = new SceneController();
        try {
            controller.switchToChooseLevelModeScene(event);
            
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
