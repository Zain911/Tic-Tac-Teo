/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.CustomDialog;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private Button startButton;
    @FXML
    private Button exitButton;
    Button[] boardButtons = new Button[3 * 3];
    // Label currentPlayerSymbol = new Label();
   
    boolean winer=false;
    boolean isGameEnded;
    boolean isPlayerTurn = true;
    boolean isPcTurn = false;
    int XOCounter = 0;
    
    Color xForeground = Color.BLUE;
    Color oForeground = Color.RED;
    Random random = new Random();
    int randomNumber;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setButtondInArray();
    }

    @FXML
    private void startPlay(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (isGameEnded == false && clickedButton.getText().equals("")) {
            
            
            System.out.println(XOCounter);
            XOCounter++;
            isPlayerTurn = true;
            clickedButton.setTextFill(xForeground);
            clickedButton.setText("X");
            checkIfGameEnds();
            if (isGameEnded == false) {

                XOCounter++;
                isPlayerTurn = false;
                
                for (;;) {
                    randomNumber = random.nextInt(9);
<<<<<<< HEAD
                    if (button00.getText().equals(button02.getText())) {
                        boardButtons[1].setTextFill(oForeground);
                        boardButtons[1].setText("O");
=======
                    if (boardButtons[randomNumber].getText().equals("")) {
                        boardButtons[randomNumber].setTextFill(oForeground);
                        boardButtons[randomNumber].setText("O");
                        
>>>>>>> aedd2ce844ece07a97a553c52a12686a482f1e6d
                        break;
                    }else if (button02.getText().equals(button22.getText())) {
                        boardButtons[5].setTextFill(oForeground);
                        boardButtons[5].setText("O");
                        break;
                    }else if (button10.getText().equals(button12.getText())) {
                        boardButtons[4].setTextFill(oForeground);
                        boardButtons[4].setText("O");
                        break;
                    }else if (button20.getText().equals(button22.getText())) {
                        boardButtons[7].setTextFill(oForeground);
                        boardButtons[7].setText("O");
                        break;
                    }else if (button00.getText().equals(button20.getText())) {
                        boardButtons[3].setTextFill(oForeground);
                        boardButtons[3].setText("O");
                        break;
                    }else if (button01.getText().equals(button21.getText())) {
                        boardButtons[4].setTextFill(oForeground);
                        boardButtons[4].setText("O");
                        break;
                    }else
                        easyMode();
                }
                checkIfGameEnds();

            }

        }

    }
    public void easyMode(){
        for (;;) {
                    randomNumber = random.nextInt(9);
                    if (boardButtons[randomNumber].getText().equals("")) {
                        boardButtons[randomNumber].setTextFill(oForeground);
                        boardButtons[randomNumber].setText("O");
                        break;
                    }
                }
    }

    private void checkIfGameEnds() {
        if (button00.getText().equals(button01.getText()) && button00.getText().equals(button02.getText()) && !button00.getText().equals("")) {
            isGameEnded = true;
            colorBackgroundWinnerButtons(button00, button01, button02);
            if(button00.getText().equals("X"))
                showWinnerGif();
            else
                showLoserGif();
            winer=true;
            colorBackgroundWinnerButtons(button00, button01, button01);
            System.out.println("0");
        }

         if (button10.getText().equals(button11.getText()) && button10.getText().equals(button12.getText()) && !button10.getText().equals("")) {
            isGameEnded = true;
            winer=true;
            colorBackgroundWinnerButtons(button10, button11, button12);
            if(button10.getText().equals("X"))
                showWinnerGif();
            else
                showLoserGif();
            System.out.println("1");

        }

        if (button20.getText().equals(button21.getText()) && button20.getText().equals(button22.getText()) && !button20.getText().equals("")) {
            isGameEnded = true;
            winer=true;
            colorBackgroundWinnerButtons(button20, button21, button22);
            if(button20.getText().equals("X"))
                showWinnerGif();
            else
                showLoserGif();
            System.out.println("2");

        }

        if (button00.getText().equals(button10.getText()) && button00.getText().equals(button20.getText()) && !button00.getText().equals("")) {
            isGameEnded = true;
            winer=true;
            colorBackgroundWinnerButtons(button00, button10, button20);
            if(button00.getText().equals("X"))
                showWinnerGif();
            else
                showLoserGif();
            System.out.println("3");
        }

        if (button01.getText().equals(button11.getText()) && button01.getText().equals(button21.getText()) && !button01.getText().equals("")) {
            isGameEnded = true;
            winer=true;
            colorBackgroundWinnerButtons(button01, button11, button21);
            if(button01.getText().equals("X"))
                showWinnerGif();
            else
                showLoserGif();
            System.out.println("4");
        }

        if (button02.getText().equals(button12.getText()) && button02.getText().equals(button22.getText()) && !button02.getText().equals("")) {
            isGameEnded = true;
            winer=true;
            colorBackgroundWinnerButtons(button02, button12, button22);
            if(button02.getText().equals("X"))
                showWinnerGif();
            else
                showLoserGif();
            System.out.println("5");
        }

        if (button00.getText().equals(button11.getText()) && button00.getText().equals(button22.getText()) && !button00.getText().equals("")) {
            isGameEnded = true;
            winer=true;
            colorBackgroundWinnerButtons(button00, button11, button22);
            if(button00.getText().equals("X"))
                showWinnerGif();
            else
                showLoserGif();
            System.out.println("6");
        }

        if (button02.getText().equals(button11.getText()) && button02.getText().equals(button20.getText()) && !button02.getText().equals("")) {
            isGameEnded = true;
            winer=true;
            colorBackgroundWinnerButtons(button02, button11, button20);
            if(button02.getText().equals("X"))
                showWinnerGif();
            else
                showLoserGif();
            System.out.println("7");
        }

        if (XOCounter >= 9) {
            System.out.println("vvvv");
            isGameEnded = true;
            isPlayerTurn = true;
            XOCounter = 0;
        }
////////// found bug
        if (isGameEnded == true) {
            if(winer){
                if (isPlayerTurn) {
                playerScore.setText(Integer.valueOf(playerScore.getText()) + 1 + "");
                } 
                else  {
                pcScore.setText(Integer.valueOf(pcScore.getText()) + 1 + "");
               }
               
               
            } 
            else{
            
            }
            XOCounter = 0;
            
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

        isGameEnded = false;
        for (Button boardButton : boardButtons) {
            boardButton.setText("");
            boardButton.setStyle("-fx-background-color:");
        }

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
    
    
}

