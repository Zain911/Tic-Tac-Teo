/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.ClientConnection1;
import connection.ClientConnection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.LoginModel;

/**
 *
 * @author mka
 */
public class LoginController implements Initializable {

    LoginModel model = new LoginModel();
    boolean flageToggle = false;
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
    private Label errorMessage;
    @FXML
    private Label emptypassword;
    @FXML
    private ImageView backButton;
    @FXML
    private Label emptyUseName;
    private CheckBox rememberCheckbox;
    //for save the user's status
    Preferences preference;
    Boolean rememberMeFlag;

   SceneController controller=new SceneController();
    @FXML
    private CheckBox rememberMe;

    private void rememberMy() {
        //for save the user's status
        preference = Preferences.userNodeForPackage(LoginController.class);
        rememberMeFlag = preference.getBoolean("rememberMe", Boolean.valueOf(""));
        if (rememberMeFlag) {
            if (!(preference.get("username", null).isEmpty() && preference.get("password", null).isEmpty())) {
                userName_Email.setText(preference.get("username", null));
                userPsaaword.setText(preference.get("password", null));
                rememberCheckbox.setSelected(rememberMeFlag);
            }
        }
    }

    public void putDataCheckbox(){
    //in connection DB part
        if (rememberCheckbox.isSelected() && !rememberMeFlag) {
            preference.put("username", userName_Email.getText());
            preference.put("password", userPsaaword.getText());
            preference.putBoolean("rememberMe", true);

        } else if (!rememberCheckbox.isSelected() && rememberMeFlag) {
            preference.put("username", "");
            preference.put("password", "");
            preference.putBoolean("rememberMe", false);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        
    }

    /*private boolean isValidPrefernce() {
        //for save the user's status
        if (!(userName_Email.getText().isEmpty() && userPsaaword.getText().isEmpty())) {

            return true;
        }

        return false;
    }*/

    private boolean isValidDta() {
        if (!(isValidPassword())|!(usernameValidation()) ) {
            return false;
        }

        return true;
    }

     public boolean passwordValidation() {
        if (model.getUser_password().trim().length() == 0) {
            
            emptypassword.setText("reqired");
            return false;
        } else if (model.getUser_password().trim().length() < 5) {
            emptypassword.setText("Weak Password!");
            return false;
        } else if (model.getUser_password().trim().length() > 10) {
            emptypassword.setText("too long password!");
            return true;
        } else {
            emptypassword.setText("Strong Password!");
            return false;
        }
    }
    public boolean isValidPassword() {

        if (model.getUser_password().isEmpty()) {
            emptypassword.setText("password can not be empty");
            return false;
        } else if (model.getUser_password().length() < 5) {
            emptypassword.setText(" week password ");

            return false;
        }

        return true;
    }
 public Boolean usernameValidation() {
         String regex = "^[A-Za-z]\\w{5,10}$";
         Pattern userNamePattern = Pattern.compile(regex);
       if (model.getUser_name().isEmpty()) {
            emptyUseName.setText("user name required");
            return false;

        } else if (!userNamePattern.matcher(model.getUser_name()).matches()) {
            emptyUseName.setText("invalid user name \n  user name must start with character \n and contain 5-10");
            return false;
        } else {
            emptyUseName.setText("");
            return true;
        }

    }
    private boolean isValidateEmail() {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern email_pattern = Pattern.compile(emailRegex);

        if (model.getUser_name().isEmpty()) {
            emptyUseName.setText(" required ");
            return false;
        } else if (!email_pattern.matcher(model.getUser_name()).matches()) {
            errorMessage.setText("Please enter a valid email address");

            return false;
        } else {

            return true;
        }
    }

    public void getDatafromUser() {
        model.setUser_name(userName_Email.getText());
        model.setUser_password(userPsaaword.getText());
    }

   

    @FXML
    private void login(ActionEvent event) throws IOException {
        getDatafromUser();
        if(isValidDta()){
        
        ClientConnection.createSocket("10.178.241.71", 5000);
        ClientConnection cliServer = new ClientConnection();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cliServer.sendLoginDataToServer(model);
                } catch (IOException ex) {
                    // doaa please handle send data exception
                }
            }
        }).start();
         }
    else{
            
}
    }
   

    @FXML
    private void register(MouseEvent event) {
        try {
            
            controller.switchToRegisterScene(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   

   

   
    
    

    @FXML
    private void backToMain(MouseEvent event) {
         try {
            controller.switchToMainScene(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void hiddenNameErrorMeesage(KeyEvent event) {
        
         emptyUseName.setText("");
    }

    @FXML
    private void hiddenPasswordErrorMeesage(KeyEvent event) {
        emptypassword.setText("");
        
    }

    @FXML
    private void onUserNmaeKeyPreasd(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
                userPsaaword.requestFocus();
              
            }
        
        
    }

    
    }
    
    
    
    

   

