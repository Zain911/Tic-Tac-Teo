/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.ClientConnection;
import helper.ConstantAttributes;
import helper.CustomDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

import javafx.scene.input.KeyEvent;

import model.Player;
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
            labelUsername.setText("invalid user name \nuser name must start with character \nand contain 5-10 charcter");

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
        System.out.println(model.getConfirmPassword() + "pass" + model.getPassword());
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
            SceneController controller = new SceneController();
            controller.switchToLoginScene(event);
        } catch (IOException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onUserNmaeKeyPreasd(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            passwordField.requestFocus();
        }
    }

    @FXML
    private void onUserPasswordKeyPreasd(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            confirmField.requestFocus();
        }
    }

    @FXML
    private void OnSignUpPressed(ActionEvent event) {
        setUserDatat();
        if (checkEmptyData()) {

            if (dataValidation()) {
                labelConfirm.setText("");
                sendPlayerToServer(event);
            } else {
                labelConfirm.setText("invalid data");
            }
        }
    }

    private void sendPlayerToServer(ActionEvent event) {
        ClientConnection cliServer = new ClientConnection();
        SceneController controller = new SceneController();

        new Thread(() -> {
            try {
                cliServer.sendRegisterDataToServer(getUserData());

                Object serverResponse = cliServer.reciveRegisterDataFromServer();
                if (serverResponse instanceof Player) {
                    Platform.runLater(() -> {
                        System.out.println("register done");
                        try {
                            ConstantAttributes.currentPlayer = (Player) serverResponse;
                            controller.switchToChoosePlayerScene(event);
                        } catch (IOException ex) {
                            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                } else if (serverResponse instanceof String) {
                    if (serverResponse.equals("Error")) {
                        System.out.println("error");
                        Platform.runLater(() -> {
                            CustomDialog.showLoginFailedDialog("error to register. please try again");
                        });
                    } else if (serverResponse.equals("User is Already Exist")) {
                        System.out.println("User is Already Exist");
                        Platform.runLater(() -> {
                            CustomDialog.showLoginFailedDialog("User is Already Exist. please try another again");
                        });
                    }

                }

            } catch (IOException ex) {
                // doaa please handle send data exception
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    private Player getUserData() {
        return new Player(usernameField.getText(), passwordField.getText(), 1, 0, 0, 0);
    }

}
