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
    private ImageView show_hiddenPassword;
    @FXML
    private Label errorMessage;
    @FXML
    private Label emptypassword;
    @FXML
    private Button backButton;
    @FXML
    private Label emptyUseName;
    @FXML
    private CheckBox rememberCheckbox;
    //for save the user's status
    Preferences preference;
    Boolean rememberMeFlag;

   SceneController controller=new SceneController();

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
     //Image img = new Image(getClass().getResourceAsStream("/ressource/bbbbb.jpg"));
     //ImageView image=new ImageView(img);
     //backButton.setGraphic(new ImageView(img));
        
    }

    /*private boolean isValidPrefernce() {
        //for save the user's status
        if (!(userName_Email.getText().isEmpty() && userPsaaword.getText().isEmpty())) {

            return true;
        }

        return false;
    }*/

    private boolean isValidDta() {
        if (!(isValidPassword()) | !(isValidateEmail())) {
            return false;
        }

        return true;
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
    private void handleButtonAction(MouseEvent event) {
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        getDatafromUser();
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

    @FXML
    private void register(MouseEvent event) {
        try {
            
            controller.switchToRegisterScene(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void toggelPasswordIcon() {
        if (!flageToggle) {
            Image image = new Image(getClass().getResourceAsStream("showPassword.png"));
            show_hiddenPassword.setImage(image);

            flageToggle = true;
        } else {

            Image image = new Image(getClass().getResourceAsStream("hiddenPassword.png"));
            show_hiddenPassword.setImage(image);
            userPsaaword.setFocusTraversable(true);
            flageToggle = false;
        }
    }

    @FXML
    private void hiddenNameErrorMeesage() {

        emptyUseName.setText(" ");

    }

    @FXML
    private void hiddenPasswordErrorMeesage() {

        emptypassword.setText(" ");
    }
    
     @FXML
    private void back(ActionEvent event) {
        try {
            controller.switchToMainScene(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

}
