/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.CustomDialog;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author mka
 */
public class PlayervsComputerEasyModeController implements Initializable {

    @FXML
    private Button button00;
    @FXML
    private Button button01;
    @FXML
    private Button button02;
    @FXML
    private Button button10;
    @FXML
    private Button button11;
    @FXML
    private Button button12;
    @FXML
    private Button button20;
    @FXML
    private Button button21;
    @FXML
    private Button button22;
    @FXML
    private Text playerName;
    @FXML
    private Text time;
    @FXML
    private Text playerScore;
    @FXML
    private Text pcScore;
    @FXML
    private Text score;
    @FXML
    private Button playAginButton;
    @FXML
    private Button exitButton;
    Button[] boardButtons = new Button[3 * 3];
    Button[] cornersButtons = new Button[4];
    // Label currentPlayerSymbol = new Label();

   
    boolean winer;

  //  boolean winer = false;
    boolean isGameEnded;
    boolean isPlayerTurn = true;
    boolean isPcTurn = false;
    int XOCounter = 0;

    Color xForeground = Color.BLUE;
    Color oForeground = Color.RED;
    Random random = new Random();
    int randomNumber;
    String mode;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setButtondInArray();
        
        //to make computer start play
        //button11.setText("O");
        //XOCounter++;
    }

    @FXML
    private void startPlay(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (isGameEnded == false && clickedButton.getText().equals("")) {

            XOCounter++;
            System.out.println("count for test : " + XOCounter);

            isPlayerTurn = true;
            clickedButton.setTextFill(xForeground);
            clickedButton.setText("X");
            checkIfGameEnds();
            if (isGameEnded == false) {

                XOCounter++;
                isPlayerTurn = false;
                if (mode.equals("easy")) {
                    randomChoseBtn();
                } else {
                    hardMode();
                }
                System.out.println("mm/" + mode);
               checkIfGameEnds();
            }

        }
    }

    

                    
                   
                 
                  

    private void hardMode() {
        if (XOCounter == 2) {
            if (boardButtons[4].getText().isEmpty()) {
                boardButtons[4].setText("O");
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
        cornersButtons[0] = button00;
        cornersButtons[1] = button02;
        cornersButtons[2] = button20;
        cornersButtons[3] = button22;
        return cornersButtons;
    }

    private void setMarkToCornerButton() {
        int randomCorner;
        for (;;) {
            randomCorner = random.nextInt(4);
            if (cornerButtonsInit()[randomCorner].getText().isEmpty()) {
                cornerButtonsInit()[randomCorner].setTextFill(oForeground);
                cornerButtonsInit()[randomCorner].setText("O");
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
                System.out.println("randomm");
                break;
            }
        }
    }

    private void checkFirstRow() {
        System.out.println("first row");
        if (boardButtons[0].getText().isEmpty()) {
            boardButtons[0].setText("O");
        } else if (boardButtons[1].getText().isEmpty()) {
            boardButtons[1].setText("O");
        } else if (boardButtons[2].getText().isEmpty()) {
            boardButtons[2].setText("O");
        }
    }

    private void checkSecoundRow() {
        System.out.println("secound row");
        if (boardButtons[3].getText().isEmpty()) {
            boardButtons[3].setText("O");
        } else if (boardButtons[4].getText().isEmpty()) {
            boardButtons[4].setText("O");
        } else if (boardButtons[5].getText().isEmpty()) {
            boardButtons[5].setText("O");
        }
    }

    private void checkThirdRow() {
        System.out.println("third row");
        if (boardButtons[6].getText().isEmpty()) {
            boardButtons[6].setText("O");
        } else if (boardButtons[7].getText().isEmpty()) {
            boardButtons[7].setText("O");
        } else if (boardButtons[8].getText().isEmpty()) {
            boardButtons[8].setText("O");
        }
    }

    private void checkFirstColumn() {
        System.out.println("first coulmn");
        if (boardButtons[0].getText().isEmpty()) {
            boardButtons[0].setText("O");
        } else if (boardButtons[3].getText().isEmpty()) {
            boardButtons[3].setText("O");
        } else if (boardButtons[6].getText().isEmpty()) {
            boardButtons[6].setText("O");
        }
    }

    private void checkSecoundCoulmn() {
        System.out.println("secound coulmn");
        if (boardButtons[1].getText().isEmpty()) {
            boardButtons[1].setText("O");
        } else if (boardButtons[4].getText().isEmpty()) {
            boardButtons[4].setText("O");
        } else if (boardButtons[7].getText().isEmpty()) {
            boardButtons[7].setText("O");
        }
    }

    private void checkThirdColumn() {
        System.out.println("third coulmn");
        if (boardButtons[2].getText().isEmpty()) {
            boardButtons[2].setText("O");
        } else if (boardButtons[5].getText().isEmpty()) {
            boardButtons[5].setText("O");
        } else if (boardButtons[8].getText().isEmpty()) {
            boardButtons[8].setText("O");
        }
    }

    private void checkFirstDigonal() {
        System.out.println("first diagonal");
        if (boardButtons[0].getText().isEmpty()) {
            boardButtons[0].setText("O");
        } else if (boardButtons[4].getText().isEmpty()) {
            boardButtons[4].setText("O");
        } else if (boardButtons[8].getText().isEmpty()) {
            boardButtons[8].setText("O");
        }
    }

    private void checkSecoundDiagonal() {
        System.out.println("secound diagonal");
        if (boardButtons[2].getText().isEmpty()) {
            boardButtons[2].setText("O");
        } else if (boardButtons[4].getText().isEmpty()) {
            boardButtons[4].setText("O");
        } else if (boardButtons[6].getText().isEmpty()) {
            boardButtons[6].setText("O");
        }
    }

    ////////////////////////////////
    public void checkHardOrEasy(String modeFromUser) {
      mode= modeFromUser;
    }

    private void checkIfGameEnds() {
        if (button00.getText().equals(button01.getText()) && button00.getText().equals(button02.getText()) && !button00.getText().equals("")) {
            isGameEnded = true;
            winer=true;
            colorBackgroundWinnerButtons(button00, button01, button02);
            if (button00.getText().equals("X")) {
                showWinnerGif();
               
            } else {
                showLoserGif();
            //winer=true;
           // colorBackgroundWinnerButtons(button00, button01, button01);
            }
            winer = true;
            //colorBackgroundWinnerButtons(button00, button01, button01);

        }

        if (button10.getText().equals(button11.getText()) && button10.getText().equals(button12.getText()) && !button10.getText().equals("")) {
            isGameEnded = true;
            winer = true;
            colorBackgroundWinnerButtons(button10, button11, button12);
            if (button10.getText().equals("X")) {
                showWinnerGif();
            } else {
                showLoserGif();
            }
            System.out.println("1");

        }

        if (button20.getText().equals(button21.getText()) && button20.getText().equals(button22.getText()) && !button20.getText().equals("")) {
            isGameEnded = true;
            winer = true;
            colorBackgroundWinnerButtons(button20, button21, button22);
            if (button20.getText().equals("X")) {
                showWinnerGif();
            } else {
                showLoserGif();
            }
            System.out.println("2");

        }

        if (button00.getText().equals(button10.getText()) && button00.getText().equals(button20.getText()) && !button00.getText().equals("")) {
            isGameEnded = true;
            winer = true;
            colorBackgroundWinnerButtons(button00, button10, button20);
            if (button00.getText().equals("X")) {
                showWinnerGif();
            } else {
                showLoserGif();
            }
            System.out.println("3");
        }

        if (button01.getText().equals(button11.getText()) && button01.getText().equals(button21.getText()) && !button01.getText().equals("")) {
            isGameEnded = true;
            winer = true;
            colorBackgroundWinnerButtons(button01, button11, button21);
            if (button01.getText().equals("X")) {
                showWinnerGif();
            } else {
                showLoserGif();
            }
            System.out.println("4");
        }

        if (button02.getText().equals(button12.getText()) && button02.getText().equals(button22.getText()) && !button02.getText().equals("")) {
            isGameEnded = true;
            winer = true;
            colorBackgroundWinnerButtons(button02, button12, button22);
            if (button02.getText().equals("X")) {
                showWinnerGif();
            } else {
                showLoserGif();
            }
            System.out.println("5");
        }

        if (button00.getText().equals(button11.getText()) && button00.getText().equals(button22.getText()) && !button00.getText().equals("")) {
            isGameEnded = true;
            winer = true;
            colorBackgroundWinnerButtons(button00, button11, button22);
            if (button00.getText().equals("X")) {
                showWinnerGif();
            } else {
                showLoserGif();
            }
            System.out.println("6");
        }

        if (button02.getText().equals(button11.getText()) && button02.getText().equals(button20.getText()) && !button02.getText().equals("")) {
            isGameEnded = true;
            winer = true;
            colorBackgroundWinnerButtons(button02, button11, button20);
            if (button02.getText().equals("X")) {
                showWinnerGif();
            } else {
                showLoserGif();
            }
            System.out.println("7");
        }

        if (XOCounter >= 9) {
            System.out.println("vvvv");
            isGameEnded = true;
             playAginButton.setVisible(true);
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

    private void colorBackgroundWinnerButtons(Button b1, Button b2, Button b3) {
        b1.setStyle("-fx-background-color: yellow;");
        b2.setStyle("-fx-background-color: yellow;");
        b3.setStyle("-fx-background-color: yellow;");
    }

    private void setButtondInArray() {
        boardButtons[0] = button00;
        boardButtons[1] = button01;
        boardButtons[2] = button02;
        boardButtons[3] = button10;
        boardButtons[4] = button11;
        boardButtons[5] = button12;
        boardButtons[6] = button20;
        boardButtons[7] = button21;
        boardButtons[8] = button22;

    }

    @FXML
    private void playAgin() {
         playAginButton.setDisable(true);
        XOCounter = 0;
        isGameEnded = false;
        XOCounter = 0;
         winer=false;
        
        for (Button boardButton : boardButtons) {
            boardButton.setText("");
            boardButton.setStyle("-fx-background-color:");
        }
        
        //to make computer start play
        //button11.setText("O");
        //XOCounter++;

    }

    @FXML
    private void exit() {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.getButtonTypes().setAll(yes,
                no);
        dialog.setTitle("");

        dialog.setHeaderText("Do you want to Exit?");
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.get() == yes) {

            System.exit(0);

        } else if (result.get() == no) {
            dialog.close();

        }

    }

    private void showWinnerGif() {
        CustomDialog.creatWinDialog();
    }

    private void showLoserGif() {
        CustomDialog.creatLoseDialog();
    }

    
     
     
     
     
      @FXML
    private void back(ActionEvent event) {
        SceneController controller=new  SceneController();
        try {
         controller.switchToChooseLevelModeScene(event);
            //controller.switchToMainScene(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}



