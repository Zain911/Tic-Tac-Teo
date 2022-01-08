package controller;

import connection.ClientConnection;
import helper.ConstantAttributes;
import java.io.IOException;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToPlayerVsPlayerScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/PlayerVsPlayerView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(askToExit(false));
    }

    public void switchToPlayersNamesScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/PlayerNameDialog.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(askToExit(false));
    }

    public void switchToMainScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/MainScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setMaxWidth(1087);
        stage.setMaxHeight(649);
        stage.setMaximized(false);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(askToExit(false));
    }

    public void switchToMainScene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/MainScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setMaxWidth(1087);
        stage.setMaxHeight(649);

        stage.setMaximized(false);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(askToExit(false));
    }

    public void switchToSceneComputerScene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/PlayervsComputerEasyMode.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(askToExit(false));
    }

    public void switchToLoginScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/loginFxml.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
        stage.setOnCloseRequest(askToExit(false));
    }

    public void switchToLoginScene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/loginFxml.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
        stage.setOnCloseRequest(askToExit(false));
    }

    public void switchToRegisterScene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/registerFxml.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(askToExit(false));
    }

    public void switchToChoosePlayerScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/PlayerVsPlayerOnlineMode.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(askToExit(true));
    }

    public void switchToIpScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/ipDialg.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setFullScreen(false);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(askToExit(false));
    }

    public void switchToChooseLevelModeScene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/HardModeFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.centerOnScreen();
        stage.setScene(scene);

        stage.show();
        stage.setOnCloseRequest(askToExit(false));
    }

    public void switchToChooseLevelModeScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/HardModeFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(askToExit(false));
    }

    public void switchToRecordListScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/RecordList.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(askToExit(false));
    }

    public void switchToRecordListScene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/RecordList.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(askToExit(false));
    }

    public void switchToShowRecordScene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/showRecordedeGame.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(askToExit(false));
    }

    private EventHandler<WindowEvent> askToExit(boolean isFromOnline) {
        EventHandler<WindowEvent> confirmCloseEventHandler;
        return confirmCloseEventHandler = event -> {
            Alert closeConfirmation = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to Exit?"
            );

            Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(ButtonType.OK);
            exitButton.setText("Exit");

            closeConfirmation.setHeaderText("Confirm Exit");
            closeConfirmation.initModality(Modality.APPLICATION_MODAL);
            closeConfirmation.initOwner(stage);

            Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
            if (!ButtonType.OK.equals(closeResponse.get())) {
                event.consume();
            } else {
                if (isFromOnline) {
                    ClientConnection clientServer = new ClientConnection();

                    Platform.exit();
                    ConstantAttributes.currentPlayer.setRequestStatus("logOut");
                    ConstantAttributes.currentPlayer.setStatus(0);
                    try {
                        clientServer.sendChangeStatues(ConstantAttributes.currentPlayer);
                        ClientConnection.closeSocket();
                    } catch (IOException | ClassNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }

        };
    }

}
