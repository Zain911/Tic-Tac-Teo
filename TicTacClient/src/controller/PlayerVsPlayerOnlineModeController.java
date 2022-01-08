/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.ClientConnection;
import helper.ConstantAttributes;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Player;

/**
 * FXML Controller class
 *
 * @author PCM
 */
public class PlayerVsPlayerOnlineModeController implements Initializable {

    @FXML
    private ListView<Player> listView;
    @FXML
    private Group groupAvailable;
    @FXML
    private Button btnSendInvitaion;
    @FXML
    private Text txtPlayerOneScore;
    @FXML
    private Text txtPlayeroneName;
    @FXML
    private ImageView imgVs;
    @FXML
    private Text txtPlayerTwoScore;
    @FXML
    private Text txtPlayerTwoName;
    @FXML
    private Group groupNotAvailable;
    @FXML
    private Button btnOk;

    @FXML
    private ImageView imgIconTwo;

    @FXML
    private ImageView imgIconOne;

    private ObservableList<Player> playerObserverlist = FXCollections.observableArrayList();
    ;
    
    @FXML
    private ImageView backButton;

    private ClientConnection cliServer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image imgV = new Image("/resource/vsicon.png");
        Image imgP1 = new Image("/resource/ponlineone.png");
        Image imgP2 = new Image("/resource/ptwoonline.png");

        imgVs.setImage(imgV);
        imgIconOne.setImage(imgP1);
        imgIconTwo.setImage(imgP2);

        new Thread(() -> {
            ArrayList<Player> players = getAllOnlinePlayers();
            playerObserverlist.addAll(players);
            listView.setItems(playerObserverlist);
            listView.setCellFactory((ListView<Player> param) -> new CustomListCell());
        }).start();

        txtPlayeroneName.setText(ConstantAttributes.currentPlayer.getUsername());
        txtPlayerOneScore.setText(String.valueOf(ConstantAttributes.currentPlayer.getScore()));

    }

    private ArrayList<Player> getAllOnlinePlayers() {
        ArrayList<Player> players = new ArrayList<>();
        cliServer = new ClientConnection();

        try {
            cliServer.sendRequestOnlineUsers(ConstantAttributes.currentPlayer.getUsername());
            players = (ArrayList<Player>) cliServer.reciveOnlineUsers();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(controller.PlayerVsPlayerOnlineModeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return players;
    }

    @FXML
    private void sendInvitation(ActionEvent event) {

        // send invitation from current player to another player by id of players (name)
        //System.out.println("id for current sender player" + ConstantAttributes.currentPlayer.getUsername());
        //System.out.println("id for reciver player" + txtPlayerTwoName.getText());
    }

    @FXML
    private void getItemFromListView(MouseEvent event) {
        listView.setOnMouseClicked((MouseEvent event1) -> {
            Player player = (Player) listView.getSelectionModel().getSelectedItem();
            txtPlayerTwoName.setText(player.getUsername());
            txtPlayerTwoScore.setText(String.valueOf(player.getScore()));

            if (player.getStatus() == 1) {
                groupAvailable.setVisible(true);
                groupNotAvailable.setVisible(false);
            } else if (player.getStatus() == 2) {
                groupNotAvailable.setVisible(true);
                groupAvailable.setVisible(false);
            }
        });
    }

    private void onOk(ActionEvent event) {
        groupNotAvailable.setVisible(false);
        txtPlayerTwoName.setText("");
        txtPlayerTwoScore.setText("0");
    }

    @FXML
    private void onBackButtonClick(MouseEvent event) {
        try {
            SceneController controller = new SceneController();
            ConstantAttributes.currentPlayer.setRequestStatus("logOut");
            ConstantAttributes.currentPlayer.setStatus(0);
            cliServer.sendChangeStatues(ConstantAttributes.currentPlayer);
            ClientConnection.closeSocket();
            controller.switchToMainScene(event);
            playerObserverlist.clear();
            listView.setItems(null);
            playerObserverlist = null;
            listView = null;

        } catch (IOException ex) {
            Logger.getLogger(PlayerVsPlayerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PlayerVsPlayerOnlineModeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

class CustomListCell extends ListCell<Player> {

    private HBox content;
    private Text name;
    private Text score;
    private Text state;

    public CustomListCell() {
        super();
        name = new Text();
        score = new Text();
        state = new Text();
        VBox vBox = new VBox(name, score, state);
        content = new HBox(vBox);
        content.setSpacing(10);
    }

    @Override
    protected void updateItem(Player item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) { // <== test for null item and empty parameter
            name.setText(item.getUsername());
            score.setText("score : " + String.valueOf(item.getScore()));
            if (item.getStatus() == 2) {
                state.setText("playing...");
            } else if (item.getStatus() == 1) {
                state.setText("ready to play");
            }
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }

}
