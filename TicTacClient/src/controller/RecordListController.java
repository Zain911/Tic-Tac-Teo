/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author mka
 */
public class RecordListController implements Initializable {

    List<String> results = new ArrayList<String>();

    @FXML
    private ListView <String>listView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //listView=new  ListView<>();
        getList();
        //list.setItems((ObservableList) results);
        System.out.println(results.get(0));
        listView.getItems().addAll(results);
    }    
    private  void getList(){
     //Creating a File object for directory
      File directoryPath = new File("F:\\iti data");
      //List of all files and directories
      File filesList[] = directoryPath.listFiles();
      System.out.println("List of files and directories in the specified directory:");
      for(File file : filesList) {
           results.add(file.getName());
          
          
          
         System.out.println("File name: "+file.getName());
         System.out.println("File path: "+file.getAbsolutePath());
         System.out.println("Size :"+file.getTotalSpace());
         System.out.println(" ");
      }
   }
}
