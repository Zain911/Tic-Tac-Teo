package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class OnlineSceneController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/GameBoardComponent.fxml"));
        fxmlLoader.setController(new GameBoardComponentController()); //Or just specify the Controller in the FXML file

        try {
          anchorPane.getChildren().add(fxmlLoader.load());
        } catch (IOException ex) {
            Logger.getLogger(OnlineSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
