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
import java.util.prefs.Preferences;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.LoginPlayer;
import model.Player;

/**
 *
 * @author mka
 */
public class LoginController implements Initializable {

    LoginPlayer model = new LoginPlayer();
    boolean flageToggle = false;
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

    @FXML
    private CheckBox rememberMe;
    SceneController controller = new SceneController();

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

    public void putDataCheckbox() {
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
        preference = Preferences.userNodeForPackage(LoginController.class);
        if (preference != null) {
            if (preference.get("username", null) != null && !preference.get("password", null).isEmpty()) {
                userName_Email.setText(preference.get("username", null));
                userPsaaword.setText(preference.get("password", null));
            }
        }
    }
    
    private boolean isValidDta() {
        if (!(isValidPassword()) | !(usernameValidation())) {
            return false;
        }

        return true;
    }

    public boolean passwordValidation() {
        if (model.getUserPassword().trim().length() == 0) {

            emptypassword.setText("reqired");
            return false;
        } else if (model.getUserPassword().trim().length() < 5) {
            emptypassword.setText("Weak Password!");
            return false;
        } else if (model.getUserPassword().trim().length() > 10) {
            emptypassword.setText("too long password!");
            return true;
        } else {
            emptypassword.setText("Strong Password!");
            return true;
        }
    }

    public boolean isValidPassword() {
        if (model.getUserPassword().isEmpty()) {
            emptypassword.setText("password can't be empty");
            return false;
        } else if (model.getUserPassword().length() < 5) {
            emptypassword.setText(" week password ");

            return false;
        }

        return true;
    }

    public Boolean usernameValidation() {
        String regex = "^[A-Za-z]\\w{5,10}$";
        Pattern userNamePattern = Pattern.compile(regex);
        if (model.getUsername().isEmpty()) {
            emptyUseName.setText("user name required");
            return false;

        } else if (!userNamePattern.matcher(model.getUsername()).matches()) {
            emptyUseName.setText("invalid user name \nuser name must start with character \nand contain 5-10 charcter");
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

        if (model.getUsername().isEmpty()) {
            emptyUseName.setText(" required ");
            return false;
        } else if (!email_pattern.matcher(model.getUsername()).matches()) {
            errorMessage.setText("Please enter a valid email address");

            return false;
        } else {

            return true;
        }
    }

    public void getDatafromUser() {
        model.setUsername(userName_Email.getText());
        model.setUserPassword(userPsaaword.getText());
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        ClientConnection cliServer = new ClientConnection();

        new Thread(() -> {
            try {
                LoginPlayer login = new LoginPlayer(userName_Email.getText(), userPsaaword.getText());
                cliServer.sendLoginDataToServer(login);
                Object serverResponse = cliServer.reciveLoginDataFromServer();

                if (serverResponse instanceof Player) {

                    Platform.runLater(() -> {
                        try {
                            ConstantAttributes.currentPlayer = (Player) serverResponse;
                            controller.switchToChoosePlayerScene(event);
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                } else if (serverResponse instanceof String) {

                    if (serverResponse.equals("PasswordNotCorrect")) {
                        System.out.println("refuse");
                        Platform.runLater(() -> {
                            CustomDialog.showLoginFailedDialog("please check your password!");
                        });
                    } else {
                        Platform.runLater(() -> {
                            CustomDialog.showLoginFailedDialog("please Sign in to Play Online!");
                        });
                        System.out.println("please sign in");
                    }

                }

            } catch (IOException ex) {
                //TODO doaa please handle send data exception
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    @FXML
    private void register(MouseEvent event) {
        try {
            System.out.println("re");
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
        if (event.getCode().equals(KeyCode.ENTER)) {
            userPsaaword.requestFocus();
        }

    }

}
