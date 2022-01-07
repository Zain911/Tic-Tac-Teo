/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.RegisterModel;

/**
 * FXML Controller class
 *
 * @author Mohamed Galal
 */
public class RegisterController implements Initializable {

    RegisterModel model = new RegisterModel();
    boolean isDataExist = false;
    @FXML
    private Button signupBtn;

    @FXML
    private Label labelMessage;


    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmField;
    @FXML
    private Label labelUsername;
    @FXML
    private Label labelPassword;
    @FXML
    private Label labelConfirm;
    @FXML
    private ImageView backButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public RegisterController() {

    }

    public void setUserDatat() {
        model.setUsername(usernameField.getText().trim());
        System.out.println(model.getUsername());
        
        model.setPassword(passwordField.getText());
        System.out.println(model.getPassword());
        model.setConfirmPassword(confirmField.getText());
        System.out.println(model.getConfirmPassword());
    }

    private boolean dataValidation() {
        if (!(passwordValidation()) | !(usernameValidation())
                | !(confirmValidation()) ) {
            return false;
        }
        return true;
    }

    public Boolean usernameValidation() {
         String regex = "^[A-Za-z]\\w{5,10}$";
         Pattern userNamePattern = Pattern.compile(regex);
        if (model.getUsername().isEmpty()) {
            labelMessage.setText("uerName is Reqired");
            return false;

        } else if (!userNamePattern.matcher(model.getUsername()).matches()) {
            labelMessage.setText("invalid user name \n  user name must start with character \n and contain 5-10");
            return false;
        } else {
            labelMessage.setText("");
            return true;
        }

    }

    public Boolean emailValidation() {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(model.getEmail());
        if (matcher.matches() == false) {
            labelMessage.setText("this email is not valid");
            return false;
        }
        return true;
    }

    public boolean passwordValidation() {
        if (model.getPassword().trim().length() == 0) {
            labelPassword.setText(" reqired");
            return false;
        } else if (model.getPassword().trim().length() < 5) {
            labelPassword.setText("Weak Password!");
            return false;
        } else if (model.getPassword().trim().length() > 10) {
            labelPassword.setText("too long password!");
            return true;
        } else {
            labelPassword.setText("Strong Password!");
            return false;
        }
    }

    public Boolean confirmValidation() {
        if (model.getConfirmPassword().trim().equals(model.getPassword().trim())) {
            labelMessage.setText("password is identical");
            return true;
        } else {
            labelMessage.setText("password is not identical");
            return false;
        }
    }

    private Boolean checkEmptyData() {
        if (model.getUsername().isEmpty() 
                || model.getPassword().isEmpty() || model.getConfirmPassword().isEmpty()) {
            labelMessage.setText("there is an Empty field");
            return false;
        }
        return true;
    }

    @FXML
    public void setOnPressed(ActionEvent event) {
        setUserDatat();
        if (checkEmptyData()) {

            if (dataValidation()) {

                labelMessage.setText("");

            } else {
                labelMessage.setText("invalid data");
            }
        }
    }

    @FXML
    private void backToLogin(MouseEvent event) {
         try {
            SceneController controller=new SceneController();
            controller.switchToLoginScene(event);
        } catch (IOException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onUserNmaeKeyPreasd(KeyEvent event) {
        
        if(event.getCode().equals(KeyCode.ENTER)){
                passwordField.requestFocus();
              
            }
    }

    @FXML
    private void onUserPasswordKeyPreasd(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
                confirmField.requestFocus();
              
            }
    }

  
   
}
