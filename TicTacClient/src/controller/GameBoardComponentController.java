package controller;

import helper.DrawLine;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Line;
import model.GameSession;
import model.PlayerMove;

public class GameBoardComponentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Region region;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    GameSession gameSession = new GameSession();
    boolean isXSymbol = true;
    String symbol;

    private String player = "X";
    private boolean winner = false;
    private boolean display = false;
    private boolean firstPlayerWinner = false;
    private boolean secondPlayerWinner = false;
    private int firstPlayerScore = 0;
    private int secondPlayerScore = 0;
    Line line = new Line();
    BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/resource/x_symbol.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    Background background = new Background(backgroundImage);
    @FXML
    private Pane paneWalid;

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
    private GridPane buttonsGrid;

    @FXML
    private void buttonOnePressed(ActionEvent event) {
        ((Button) event.getSource()).setDisable(true);
        gameSession.addMove(returnMove ((Button) event.getSource()));
        
        checkState();
    }

    private PlayerMove returnMove(Button btn) {
        PlayerMove move = new PlayerMove();
        if (btn == btn00) {
            move = new PlayerMove(0, 0, isXSymbol);
        } else if (btn == btn01) {
            move = new PlayerMove(0, 1, isXSymbol);
        } else if (btn == btn02) {
            move = new PlayerMove(0, 2, isXSymbol);
        } else if (btn == btn10) {
            move = new PlayerMove(1, 0, isXSymbol);
        } else if (btn == btn11) {
            move = new PlayerMove(1, 1, isXSymbol);
        } else if (btn == btn12) {
            move = new PlayerMove(1, 2, isXSymbol);
        } else if (btn == btn20) {
            move = new PlayerMove(2, 0, isXSymbol);
        } else if (btn == btn21) {
            move = new PlayerMove(2, 1, isXSymbol);
        } else if (btn == btn22) {
            move = new PlayerMove(2, 2, isXSymbol);
        }
        return move;
    }

    private String returnSymbol() {
        String symbol;
        if (isXSymbol == true) {
            symbol = "X";
        } else {
            symbol = "O";
        }
        isXSymbol = !isXSymbol;
        return symbol;
    }

    private void checkRows() {
        if (btn00.getText().equals(btn01.getText())
                && btn01.getText().equals(btn02.getText())
                && !btn00.getText().equals("")) {
            //drawLine(btn00, btn02);
            line = DrawLine.drawLine(btn00, btn02);
            paneWalid.getChildren().add(line);
            
                if (btn00.getText().equals("X")) {
                    firstPlayerWinner = true;
                    firstPlayerScore += 10;
                } else {
                    secondPlayerWinner = true;
                    secondPlayerScore += 10;
                }
            winner = true;
        } else if (btn10.getText().equals(btn11.getText())
                && btn11.getText().equals(btn12.getText())
                && !btn10.getText().equals("")) {
            line = DrawLine.drawLine(btn10, btn12);
            paneWalid.getChildren().add(line);
            if (btn10.getText().equals("X")) {
                firstPlayerWinner = true;
                firstPlayerScore += 10;
            } else {
                secondPlayerWinner = true;
                secondPlayerScore += 10;
            }
            winner = true;
        } else if (btn20.getText().equals(btn21.getText())
                && btn21.getText().equals(btn22.getText())
                && !btn22.getText().equals("")) {
            line = DrawLine.drawLine(btn20, btn22);
            paneWalid.getChildren().add(line);
            if (btn22.getText().equals("X")) {
                System.out.println("x is winning");

                firstPlayerWinner = true;
                firstPlayerScore += 10;
            } else {
                System.out.println("o is winning");
                secondPlayerWinner = true;
                secondPlayerScore += 10;
            }
            winner = true;
        }
    }

    private void checkColumns() {
        if (btn00.getText().equals(btn10.getText())
                && btn10.getText().equals(btn20.getText())
                && !btn00.getText().equals("")) {
            line = DrawLine.drawLine(btn00, btn20);
            paneWalid.getChildren().add(line);
            if (btn00.getText().equals("X")) {
                System.out.println("x is winning");
                firstPlayerWinner = true;
                firstPlayerScore += 10;
            } else {
                System.out.println("o is winning");
                secondPlayerWinner = true;
                secondPlayerScore += 10;
            }
            winner = true;
        } else if (btn01.getText().equals(btn11.getText())
                && btn11.getText().equals(btn21.getText())
                && !btn01.getText().equals("")) {
            line = DrawLine.drawLine(btn01, btn21);
            paneWalid.getChildren().add(line);
            if (btn01.getText().equals("X")) {
                firstPlayerWinner = true;
                firstPlayerScore += 10;
            } else {
                secondPlayerWinner = true;
                secondPlayerScore += 10;
            }
            winner = true;
        } else if (btn02.getText().equals(btn12.getText())
                && btn12.getText().equals(btn22.getText())
                && !btn02.getText().equals("")) {
            line = DrawLine.drawLine(btn02, btn22);
            paneWalid.getChildren().add(line);
            if (btn02.getText().equals("X")) {
                firstPlayerWinner = true;
                firstPlayerScore += 10;
            } else {
                secondPlayerWinner = true;
                secondPlayerScore += 10;
            }
            winner = true;
        }
    }

    private void checkDiagonal() {
        if (btn00.getText().equals(btn11.getText())
                && btn11.getText().equals(btn22.getText())
                && !btn00.getText().equals("")) {
            line = DrawLine.drawLine(btn00, btn22);
            paneWalid.getChildren().add(line);
            if (btn00.getText().equals("X")) {
                firstPlayerWinner = true;
                firstPlayerScore += 10;
            } else {
                System.out.println("o is winning");
                secondPlayerWinner = true;
                secondPlayerScore += 10;
            }
            winner = true;
        } else if (btn02.getText().equals(btn11.getText())
                && btn11.getText().equals(btn20.getText())
                && !btn02.getText().equals("")) {
            line = DrawLine.drawLine(btn02, btn20);
            paneWalid.getChildren().add(line);
            if (btn02.getText().equals("X")) {
                System.out.println("x is winning");
                firstPlayerWinner = true;
                firstPlayerScore += 10;
            } else {
                System.out.println("o is winning");
                secondPlayerWinner = true;
                secondPlayerScore += 10;
            }
            winner = true;
        }
    }

    private boolean isFullGrid() {
        if (!btn00.getText().equals("")
                && !btn01.getText().equals("")
                && !btn02.getText().equals("")
                && !btn10.getText().equals("")
                && !btn11.getText().equals("")
                && !btn12.getText().equals("")
                && !btn20.getText().equals("")
                && !btn21.getText().equals("")
                && !btn22.getText().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private void checkState() {
        
        //todo nested Check
        checkRows();
        checkColumns();
        checkDiagonal();
        if (firstPlayerWinner) {
            System.out.println("X is win");
        } else if (secondPlayerWinner) {
            System.out.println("O is win");
        } else {
            if ((isFullGrid())) {
                System.out.println("It's a Draw");
            }
        }
    }

}
