/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

    @FXML
    private TextField emailField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmField;

    @FXML
    private Label labelPassword;

    @FXML
    private Label labelUsername;

    @FXML
    private Label labelConfirm;

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
        passwordField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (passwordField.getLength() == 0) {
                    labelPassword.setText("");
                } else if (passwordField.getText().length() < 8) {
                    labelPassword.setTextFill(Color.RED);
                    labelPassword.setText("Weak Password!");
                    confirmField.setDisable(true);
                } else if (passwordField.getText().length() > 14) {
                    labelPassword.setTextFill(Color.RED);
                    labelPassword.setText("too long password!");
                    confirmField.setDisable(true);
                } else {
                    labelPassword.setTextFill(Color.GREEN);
                    labelPassword.setText("Strong Password!");
                    confirmField.setDisable(false);
                }
            }
        });

        if (passwordField.getLength() == 0) {
            return false;
        } else if (passwordField.getLength() < 8) {
            return false;
        } else if (passwordField.getLength() > 14) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean confirmValidation() {
        confirmField.setOnKeyTyped((KeyEvent e) -> {
            if (confirmField.getText().equals(passwordField.getText())) {
                labelConfirm.setTextFill(Color.GREEN);
                labelConfirm.setText("password is identical");
                signupBtn.setDisable(false);
            } else {
                labelConfirm.setTextFill(Color.RED);
                labelConfirm.setText("password is not identical");
                signupBtn.setDisable(true);
            }
        });

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
    public void setOnPressed(ActionEvent event) {
        setUserDatat();
        if (checkEmptyData()) {

            if (dataValidation()) {

                labelConfirm.setText("yesssssss");

            } else {
                labelConfirm.setText("noooooooo");
            }
        }
    }
}
