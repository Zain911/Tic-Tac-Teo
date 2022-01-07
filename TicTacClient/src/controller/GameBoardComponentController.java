package controller;

import static controller.PlayerVsPlayerController.isRecord;
import helper.AccessFile;
import helper.CustomDialog;
import static helper.DrawLine.colorBackgroundWinnerButtons;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import model.GameSession;
import model.PlayerMove;

public class GameBoardComponentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Region region;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setupImageXO();
       
        // initPrefPlayer(scorePlayerOne,scorePlayerTwo);

    }

    GameSession gameSession = new GameSession();
    boolean isXSymbol = true;
    String symbol;
     ArrayList<PlayerMove>listOfMove=new ArrayList<>();
    private String player = "X";
    private boolean winner = false;
    public  static boolean gameEnded=false;
    private boolean display = false;
    private boolean firstPlayerWinner = false;
    private boolean secondPlayerWinner = false;
    protected static int firstPlayerScore = 0;
    protected static int secondPlayerScore = 0;
    
    Line line = new Line();
    BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/resource/x_symbol.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    Background background = new Background(backgroundImage);
    private Pane pane;
    Parent buttonParent;
    Stage stage;
    private Preferences pref;

    @FXML
    private Label scorePlayerOne;

    @FXML
    private Label scorePlayerTwo;

    Image imgo, imgx;
    ImageView image;
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
        Button buttonPressed = (Button) event.getSource();
        if (gameEnded==false&&buttonPressed.getText().equalsIgnoreCase("")) {
            gameSession.addMove(returnMove((Button) event.getSource()));
            ((Button) event.getSource()).setText(returnSymbol(buttonPressed));
            ((Button) event.getSource()).setStyle("-fx-text-fill:transparent;");
              
            try {
                checkState();
            } catch (BackingStoreException ex) {
                Logger.getLogger(GameBoardComponentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
  int count=0;
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
        listOfMove.add(move);
     System.out.println("size="+""+listOfMove.size());
        System.out.println(listOfMove.get(count) );
       // System.out.println(listOfMove.get(count).getX()+""+listOfMove.get(count).getY());
        count++;
        return move;
    }

    private String returnSymbol(Button buttonPressed) {
        String symbol;
        if (isXSymbol == true) {
            symbol = "X";
            buttonPressed.setGraphic(new ImageView(imgx));
        } else {
            symbol = "O";
            buttonPressed.setGraphic(new ImageView(imgo));

        }
        isXSymbol = !isXSymbol;
        return symbol;
    }

    public void drawLine(Button b1, Button b2) {
        Bounds bound1 = b1.localToScene(b1.getBoundsInLocal());
        Bounds bound2 = b2.localToScene(b2.getBoundsInLocal());
        double x1, y1, x2, y2;
        x1 = (bound1.getMinX() + bound1.getMaxX()) / 2;
        y1 = (bound1.getMinY() + bound1.getMaxY()) / 2;
        x2 = (bound2.getMinX() + bound2.getMaxX()) / 2;
        y2 = (bound2.getMinY() + bound2.getMaxY()) / 2;
        pane.getChildren().add(new Line(x1, y1, x2, y2));

    }

    private void checkRows() {
        if (btn00.getText().equals(btn01.getText())
                && btn01.getText().equals(btn02.getText())
                && !btn00.getText().equals("")) {
            colorBackgroundWinnerButtons(btn00, btn01, btn02);
            drawLine(btn00, btn02);

            if (btn00.getText().equals("X")) {
                firstPlayerWinner = true;
            } else {
                secondPlayerWinner = true;
            }
            winner = true;
        } else if (btn10.getText().equals(btn11.getText())
                && btn11.getText().equals(btn12.getText())
                && !btn10.getText().equals("")) {
            colorBackgroundWinnerButtons(btn10, btn11, btn12);
            drawLine(btn10, btn12);
            if (btn10.getText().equals("X")) {
                firstPlayerWinner = true;
            } else {
                secondPlayerWinner = true;
            }
            winner = true;
        } else if (btn20.getText().equals(btn21.getText())
                && btn21.getText().equals(btn22.getText())
                && !btn22.getText().equals("")) {
            colorBackgroundWinnerButtons(btn20, btn21, btn22);
            drawLine(btn20, btn22);
            if (btn22.getText().equals("X")) {
                System.out.println("x is winning");

                firstPlayerWinner = true;
            } else {
                System.out.println("o is winning");
                secondPlayerWinner = true;
            }
            winner = true;
        }
    }
    private void checkColumns() {
        if (btn00.getText().equals(btn10.getText())
                && btn10.getText().equals(btn20.getText())
                && !btn00.getText().equals("")) {
            colorBackgroundWinnerButtons(btn00, btn10, btn20);
            drawLine(btn00, btn20);
            if (btn00.getText().equals("X")) {
                System.out.println("x is winning");
                firstPlayerWinner = true;
            } else {
                System.out.println("o is winning");
                secondPlayerWinner = true;
            }
            winner = true;
        } else if (btn01.getText().equals(btn11.getText())
                && btn11.getText().equals(btn21.getText())
                && !btn01.getText().equals("")) {
            colorBackgroundWinnerButtons(btn01, btn11, btn21);
            drawLine(btn01, btn21);
            if (btn01.getText().equals("X")) {
                firstPlayerWinner = true;
            } else {
                secondPlayerWinner = true;
            }
            winner = true;
        } else if (btn02.getText().equals(btn12.getText())
                && btn12.getText().equals(btn22.getText())
                && !btn02.getText().equals("")) {
            colorBackgroundWinnerButtons(btn02, btn12, btn22);
            
            drawLine(btn02, btn22);

            if (btn02.getText().equals("X")) {
                firstPlayerWinner = true;
            } else {
                secondPlayerWinner = true;
            }
            winner = true;
        }
    }

    private void checkDiagonal() {
        if (btn00.getText().equals(btn11.getText())
                && btn11.getText().equals(btn22.getText())
                && !btn00.getText().equals("")) {
            colorBackgroundWinnerButtons(btn00, btn11, btn22);
            drawLine(btn00, btn22);
            if (btn00.getText().equals("X")) {
                firstPlayerWinner = true;
            } else {
                System.out.println("o is winning");
                secondPlayerWinner = true;
            }
            winner = true;
        } else if (btn02.getText().equals(btn11.getText())
                && btn11.getText().equals(btn20.getText())
                && !btn02.getText().equals("")) {
            colorBackgroundWinnerButtons(btn02, btn11, btn20);
            drawLine(btn02, btn20);
            if (btn02.getText().equals("X")) {
                System.out.println("x is winning");
                firstPlayerWinner = true;
            } else {
                System.out.println("o is winning");
                secondPlayerWinner = true;
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

    private void checkState() throws BackingStoreException {

        checkRows();
        checkColumns();
        checkDiagonal();
        if (firstPlayerWinner) {
            System.out.println("X is win");
            firstPlayerScore += 5;
            scorePlayerOne.setText(String.valueOf(firstPlayerScore));
             gameEnded=true;
             System.out.println(isRecord);
            if (isRecord) {
                System.out.println(" doaa write");
                System.out.println( PlayerVsPlayerController.isRecord +"  record");
                System.out.println("arrsize"+listOfMove.size());
                AccessFile.writeFile(listOfMove);
                
            }

        } else if (secondPlayerWinner) {
            System.out.println("O is win");
            secondPlayerScore += 5;
            scorePlayerTwo.setText(String.valueOf(secondPlayerScore));
                       gameEnded=true;

             if (isRecord) {
                 System.out.println(" doaa write");
                  System.out.println("arrsize"+listOfMove.size());
                System.out.println( isRecord +"  record");
                AccessFile.writeFile(listOfMove);
            }
           

             
        } else {
            if ((isFullGrid())) {
                System.out.println("It's a Draw");
                              gameEnded=true;
                               if (PlayerVsPlayerController.isRecord) {
                 System.out.println("arrsize"+listOfMove.size());
                AccessFile.writeFile(listOfMove);
                System.out.println(" doaa write");
                System.out.println( PlayerVsPlayerController.isRecord +"  record");
            }

               
            }
           

        }
         //isRecord = false;
       // PlayerVsPlayerController.recordImage.setDisable(false);
        Image image = new Image(getClass().getResourceAsStream("/resource/oimage.png"));

      // PlayerVsPlayerController.recordImage.setImage(image);
      
    }

    /*public void replayAgain(String winner) throws BackingStoreException {

        boolean result = CustomDialog.askPlayAgain(winner, "play again");

        if (result) {
            boolean check = CustomDialog.askPlayAgain("Do you want record game",
                    "Record");
            if (check) {
                MainSceneController.isRecord = false;

                AccessFile.createFile("local-mode");
                 AccessFile.writeFile();

               // AccessFile.writeFile(pref.get("fristPlayer", "") + ".");
                //AccessFile.writeFile(pref.get("secondPlayer", "") + ".");
            }

            //get scene
            try {
                buttonParent = FXMLLoader.load(getClass().getResource("/view/PlayerVsPlayerView.fxml"));
                //generate new scene
                Scene buttonScene = new Scene(buttonParent);

                //get stage information
                Stage window = (Stage) btn00.getScene().getWindow();
                window.setTitle("Home");

                window.setScene(buttonScene);
                window.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
          

            firstPlayerScore=secondPlayerScore=0;
            try {
                buttonParent = FXMLLoader.load(getClass().getResource("/view/MainScene.fxml"));
                //generate new scene
                Scene buttonScene = new Scene(buttonParent);

                //get stage information
                Stage window = (Stage) btn00.getScene().getWindow();
                window.setTitle("Home");

                window.setScene(buttonScene);
                window.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }*/

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    private void setupImageXO() {
        imgo = new Image(getClass().getResourceAsStream("/resource/oimage.png"));

        imgx = new Image(getClass().getResourceAsStream("/resource/ximage.png"));

    }

    public void initPrefPlayer(Label scorePlayerOne, Label scorePlayerTwo) throws BackingStoreException {

        if (firstPlayerScore != 0 || secondPlayerScore != 0) {
            scorePlayerOne.setText(String.valueOf(firstPlayerScore));
            scorePlayerTwo.setText(String.valueOf(secondPlayerScore));

        }
    }

    void setLabelScore(Label scorePlayerOne, Label scorePlayerTwo) {
        this.scorePlayerOne = scorePlayerOne;
        this.scorePlayerTwo = scorePlayerTwo;
    }

 /*   void setNamePlayer(String txtFirstNamePlayer, String txtSecondNamePlayer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
*/
   
}
