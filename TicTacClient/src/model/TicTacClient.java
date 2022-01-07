
package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TicTacClient extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {

      // Parent root = FXMLLoader.load(getClass().getResource("/view/registerFxml.fxml"));

       
       //TODO use sokash screen 
       Parent root = FXMLLoader.load(getClass().getResource("/view/SplashFXML.fxml"));
      // Parent root = FXMLLoader.load(getClass().getResource("/view/MainScene.fxml")); 
       
       Scene scene = new Scene(root);
        
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
