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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.scene.input.KeyEvent;

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
    @FXML
    private ImageView xoImage;

    

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
       
        if (!(passwordValidation()) || !(usernameValidation())
                || !(confirmValidation())) {
            System.out.println(passwordValidation());
            System.out.println(usernameValidation());
            System.out.println(confirmValidation());
            return false;
        }
        return true;
    }
    
    public Boolean usernameValidation() {
         String regex = "^[A-Za-z]\\w{5,10}$";
         Pattern userNamePattern = Pattern.compile(regex);
        if (model.getUsername().isEmpty()) {
        labelUsername.setText("uerName is Reqired");
            return false;

        } else if (!userNamePattern.matcher(model.getUsername()).matches()) {
            labelUsername.setText("invalid user name \n  user name must start with character \n and contain 5-10");

        }
        if (usernameField.getLength() < 6) {
            labelUsername.setText("too short username!");
            return false;

        } else if (usernameField.getLength() > 14) {
            labelUsername.setText("too long username!");
            return false;
        } else {
            labelUsername.setText("");
            return true;
        }

    
    }
    public boolean passwordValidation() {
        


        if (passwordField.getLength() == 0) {
            return false;
        } else if (passwordField.getLength() < 5) {
            return false;
        } else if (passwordField.getLength() > 10) {
            return false;
        } else {
            return true;
        }
    }
    
    public Boolean confirmValidation() {
      
           System.out.println("nnnn");
           System.out.println(model.getConfirmPassword()+"pass"+model.getPassword());
        if (model.getConfirmPassword().equals(model.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean checkEmptyData() {

        if (model.getUsername().isEmpty() || model.getPassword().isEmpty()
                || model.getConfirmPassword().isEmpty()) {
            labelConfirm.setText("there is an Empty field");
            return false;
        }
        return true;
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

    @FXML
    private void OnSignUpPressed(ActionEvent event) {
        
        
         setUserDatat();
        if (checkEmptyData()) {

            if (dataValidation()) {

                labelConfirm.setText("");

            } else {

                labelConfirm.setText("invalid data");

            } 
        }
    }

  
   
}
