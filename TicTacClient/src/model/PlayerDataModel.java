/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author mka
 */
public class PlayerDataModel {

    private int playerId;
    private String playerName;
    private String playerEmail;
    private String playerNameOrEmail;
    private String playerPassWord;
    private int PlayerScore;
    private int playerStatus;

    public PlayerDataModel() {
    }

    public PlayerDataModel(String playerNameOrEmail, String playerPassWord) {
        this.playerNameOrEmail = playerNameOrEmail;
        this.playerPassWord = playerPassWord;
    }

    public String getPlayerNameOrEmail() {
        return playerNameOrEmail;
    }

    public void setPlayerNameOrEmail(String playerNameOrEmail) {
        this.playerNameOrEmail = playerNameOrEmail;
    }

    public PlayerDataModel(String playerName, String playerEmail, String playerPassWord) {
        this.playerName = playerName;
        this.playerEmail = playerEmail;
        this.playerPassWord = playerPassWord;
    }

    public PlayerDataModel(int playerId, String playerName, String playerEmail, String playerPassWord, int PlayerScore, int playerStatus) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerEmail = playerEmail;
        this.playerPassWord = playerPassWord;
        this.PlayerScore = PlayerScore;
        this.playerStatus = playerStatus;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerPassWord() {
        return playerPassWord;
    }

    public void setPlayerPassWord(String playerPassWord) {
        this.playerPassWord = playerPassWord;
    }

    public int getPlayerScore() {
        return PlayerScore;
    }

    public void setPlayerScore(int PlayerScore) {
        this.PlayerScore = PlayerScore;
    }

    public int getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(int playerStatus) {
        this.playerStatus = playerStatus;
    }

    public String getPlayerEmail() {
        return playerEmail;
    }

    public void setPlayerEmail(String playerEmail) {
        this.playerEmail = playerEmail;
    }

}
