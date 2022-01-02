
package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PlayerDataModel;
//import org.apache.derby.jdbc.ClientDriver;

/**
 *
 * @author mka
 */
public class DataTransferObject {

    public static Connection con;
    static ArrayList<PlayerDataModel> arrayOfPlayer = new ArrayList<>();

    static ArrayList<PlayerDataModel> onLineArrayOfPlayer = new ArrayList<>();
    static ArrayList<PlayerDataModel> offLineArrayOfPlayer = new ArrayList<>();
    static ArrayList<PlayerDataModel> ArrayOfTopPlayer = new ArrayList<>();

    public static void getConnection() {

        try {
          //  DriverManager.registerDriver(new ClientDriver());

            con = DriverManager.getConnection("jdbc:derby://localhost:1527/RegisterForm", "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(DataTransferObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<PlayerDataModel> selectPlayer(PlayerDataModel model) {

        try {
            PreparedStatement pst = con.prepareStatement("select *from PLAYERDATA where playerId=?");
            pst.setInt(1, model.getPlayerId());

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                arrayOfPlayer.add(new PlayerDataModel(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getInt(6)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataTransferObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arrayOfPlayer;

    }

    public static ArrayList<PlayerDataModel> selectAllPlayers(PlayerDataModel model) {
        try {
            PreparedStatement pst = con.prepareStatement("select * from PLAYERDATA");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                arrayOfPlayer.add(new PlayerDataModel(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getInt(6)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataTransferObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arrayOfPlayer;
    }

    public static int addNewPlayer(PlayerDataModel model) {
        int result = 0;
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO PLAYERDATA VALUES (?, ? ,?, ?, ?,?)");

            pst.setInt(1, model.getPlayerId());
            pst.setString(2, model.getPlayerName());
            pst.setString(3, model.getPlayerEmail());

            pst.setString(4, model.getPlayerPassWord());
            pst.setInt(5, model.getPlayerScore());
            pst.setInt(6, model.getPlayerStatus());

            result = pst.executeUpdate();

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataTransferObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static ArrayList<PlayerDataModel> selectTopPlayers(PlayerDataModel model) {
        try {
            PreparedStatement pst = con.prepareStatement("select * from playerData where playerScore= (select Max(playerScore) from PLAYERDATA )");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ArrayOfTopPlayer.add(new PlayerDataModel(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getInt(6)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataTransferObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ArrayOfTopPlayer;

    }

    public static int deleteUser(PlayerDataModel model) {
        int result = 0;
        try {
            PreparedStatement pst = con.prepareStatement("Delete from playerData where playerId=?");
            pst.setInt(1, model.getPlayerId());

            result = pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DataTransferObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;

    }

    public static int updateUserStats(PlayerDataModel model) {
        int result = 0;
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE playerData set playerStatus=? where playerId=?");

            pst.setInt(1, model.getPlayerStatus());
            pst.setInt(2, model.getPlayerId());

            result = pst.executeUpdate();

            //stmt.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(DataTransferObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int updateUserScore(PlayerDataModel model) {
        int result = 0;
        try {
            PreparedStatement pst = con.prepareStatement("select playerScore from playerData  where playerId=?");
            pst.setInt(1, model.getPlayerStatus());
            ResultSet rs = pst.executeQuery();
            int totalScore = model.getPlayerScore() + rs.getInt(4);
            PreparedStatement pst2 = con.prepareStatement("UPDATE playerData set playerScore=? where playerId=?");

            pst.setInt(1, totalScore);
            pst.setInt(2, model.getPlayerId());

            result = pst.executeUpdate();

            //stmt.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(DataTransferObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }

    public static ArrayList<PlayerDataModel> selectOnlinePlyer() {
        try {
            PreparedStatement pst = con.prepareStatement("select from PLAYERDATA where playerStatus =1");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                onLineArrayOfPlayer.add(new PlayerDataModel(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getInt(6)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataTransferObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return onLineArrayOfPlayer;

    }

    public static ArrayList<PlayerDataModel> selectOfflinePlayer() {

        try {
            PreparedStatement pst = con.prepareStatement("select  from PLAYERDATA where playerStatus =0");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                offLineArrayOfPlayer.add(new PlayerDataModel(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getInt(6)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataTransferObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return offLineArrayOfPlayer;
    }

    public static int updataUser(PlayerDataModel model) {
        int result = 0;
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE playerData set playerName=?,playerEmail=?,playerPassword=?,playerStatus=?,playerScore=? where playerId=?");
            pst.setString(1, model.getPlayerName());
            pst.setString(2, model.getPlayerEmail());
            pst.setString(3, model.getPlayerPassWord());
            pst.setInt(4, model.getPlayerScore());
            pst.setInt(5, model.getPlayerStatus());
            pst.setInt(6, model.getPlayerId());

            result = pst.executeUpdate();

            //stmt.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(DataTransferObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
        public static PlayerDataModel selectPlayerForLogin(PlayerDataModel model) {
         PlayerDataModel playerFounded;
        try {
            PreparedStatement pst = con.prepareStatement("select *from PLAYERDATA where playerName=? oR playerEmail=?");
            pst.setString(1, model.getPlayerName());
             pst.setString(1, model.getPlayerEmail());

            ResultSet rs = pst.executeQuery();

           if(rs!=null){
          playerFounded=  new PlayerDataModel(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getInt(6));
          return  playerFounded;
           }

        } catch (SQLException ex) {
            Logger.getLogger(DataTransferObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }


    public static PlayerDataModel loginFunctionality(boolean isValid,PlayerDataModel player) {
        if(isValid){
           return selectPlayerForLogin(player);
            
        
        }
        return null;
       
        
    }

    public static int registerFunctionality(boolean isValid,PlayerDataModel player) {
        
        if(isValid){
        return addNewPlayer(player);
        
        }
       return  -1;
    }
}
