/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.LoginModel;

/**
 *
 * @author mka
 */
public class LoginController implements Initializable {
    
    
    LoginModel model=new LoginModel();
    boolean flageToggle=false;
    private Label label;
    @FXML
    private TextField userName_Email;
    @FXML
    private Button button_login;
    @FXML
    private Text register_text;
    @FXML
    private PasswordField userPsaaword;
    @FXML
    private ImageView show_hiddenPassword;
    @FXML
    private Label errorMessage;
    @FXML
    private Label emptypassword;
    @FXML
    private Label emptyUseName;
      
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
    }    
    
    
    
      private boolean isValidDta(){
        
        if(!(isValidPassword())|!(isValidateEmail())){
                  return false;
             }
             
         return true;
      }
    
    
    public boolean isValidPassword() {


        if (model.getUser_password().isEmpty()) {
           emptypassword.setText("password can not be empty");
            return false;
        } else if(model.getUser_password().length()<5) {
          emptypassword.setText(" week password ");

                return false;
        }
            
            return true;
        }


    

    
     private boolean isValidateEmail() {
         
         String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern email_pattern = Pattern.compile(emailRegex);
         
        if (model.getUser_name().isEmpty()) {
           emptyUseName.setText(" required ");
            return false;
        } 
        
        else if (!email_pattern.matcher(model.getUser_name()).matches()
                ) {
            errorMessage.setText("Please enter a valid email address");

            return false;
        } else {
            
            return true;
        }
    }

     
     public void getDatafromUser() {
        
     model.setUser_name( userName_Email.getText());
     model.setUser_password(userPsaaword.getText());
        
 
    
    
}

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
      getDatafromUser();
        if(isValidDta()){
           FXMLLoader fxmlLoader = new FXMLLoader();
           fxmlLoader.setLocation(getClass().getResource("/xo/PlayervsComputerEasyMode.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
           Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
        
        }
        else{
        
        }
    }

    @FXML
    private void register(MouseEvent event) {
          
        
    }
    @FXML
    private  void toggelPasswordIcon(){
     if(!flageToggle){
     Image image = new Image(getClass().getResourceAsStream("showPassword.png"));
     show_hiddenPassword.setImage(image);
     
         flageToggle = true;
     }
     else{
     
      Image image = new Image(getClass().getResourceAsStream("hiddenPassword.png"));
     show_hiddenPassword.setImage(image);
     userPsaaword.setFocusTraversable(true);
         flageToggle = false;
     }
    }
    
   @FXML
   private void hiddenNameErrorMeesage(){
   
    emptyUseName.setText(" ");
   
   
   }
    @FXML
   private void hiddenPasswordErrorMeesage(){
   
    emptypassword.setText(" ");
   
   
   }
    
    @FXML
    private void rememberMy(){
    //TODO
    }
    
    
    
}
