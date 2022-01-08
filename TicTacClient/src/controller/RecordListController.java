/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.PlayerMove;

/**
 * FXML Controller class
 *
 * @author mka
 */
public class RecordListController implements Initializable {

    List<String> results = new ArrayList<String>();
     List<PlayerMove>movesList;
    @FXML
    private ListView <String>listView;
    static String filePath = new File("").getAbsolutePath();
  //  static String pathLocalFile = filePath.concat("\\src\\savedGame\\");
    static String pathLocalFile =filePath.concat("\\record\\savedGame");
    static String pathOnlineFile = filePath.concat("\\src\\savedOnlineGame\\");
    public static String selectedItemOfRecordList;
    @FXML
    private ImageView backButton;
    @FXML
    private ScrollBar scrollBar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        getList();
        System.out.println(results.get(0));
        listView.getItems().addAll(results);
       // scrollBar=getListViewScrollBar(listView);
        
    }   
    private  void getList(){
      File directoryPath = new File(pathLocalFile);
      File filesList[] = directoryPath.listFiles();
      for(File file : filesList) {
           results.add(file.getName());
      }
   }

    @FXML
    private void openRecord(MouseEvent event) {
        try {
          selectedItemOfRecordList=  listView.getSelectionModel().getSelectedItem();
            SceneController controller=new SceneController();
            controller.switchToShowRecordScene(event);
        } catch (IOException ex) {
            Logger.getLogger(RecordListController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void onBackButtonClick(MouseEvent event) {
        SceneController controller=new SceneController();
         try {
            controller.switchToMainScene(event);
        } catch (IOException ex) {
            Logger.getLogger(HardModeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
