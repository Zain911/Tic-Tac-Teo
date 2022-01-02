/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
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
    private TextField emailField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public RegisterController() {

    }

    public void setUserDatat() {
        model.setUsername(usernameField.getText().trim());
        System.out.println(model.getUsername());
        model.setEmail(emailField.getText());
        System.out.println(model.getEmail());
        model.setPassword(passwordField.getText());
        System.out.println(model.getPassword());
        model.setConfirmPassword(confirmField.getText());
        System.out.println(model.getConfirmPassword());
    }

    private boolean dataValidation() {
        if (!(passwordValidation()) | !(usernameValidation())
                | !(confirmValidation()) | !(emailValidation())) {
            return false;
        }
        return true;
    }

    public Boolean usernameValidation() {
        if (model.getUsername().length() < 6) {
            labelMessage.setText("too short username!");
            return false;

        } else if (model.getUsername().trim().length() > 14) {
            labelMessage.setText("too long username!");
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
            labelMessage.setText("");
            return false;
        } else if (model.getPassword().trim().length() < 8) {
            labelMessage.setText("Weak Password!");
            return false;
        } else if (model.getPassword().trim().length() > 14) {
            labelMessage.setText("too long password!");
            return true;
        } else {
            labelMessage.setText("Strong Password!");
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
        if (model.getUsername().isEmpty() || model.getEmail().isEmpty()
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

                labelMessage.setText("yesssssss");

            } else {
                labelMessage.setText("noooooooo");
            }
        }
    }
}
