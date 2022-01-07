/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import controller.PlayerVsPlayerController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.prefs.Preferences;
import model.PlayerMove;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.deploy.security.ruleset.RunRule;
import javax.json.JsonWriter;
import javax.json.stream.JsonGenerator;

public class AccessFile {

    private static File file;
    static String filePath = new File("").getAbsolutePath();
    static String pathLocalFile = filePath.concat("\\src\\savedGame\\");
    static String pathOnlineFile = filePath.concat("\\src\\savedOnlineGame\\");
    public FileOutputStream outStream;

    public AccessFile() {
    }

    public static File createFile(String listType) {

        Preferences preferencesLocalMode = Preferences.userNodeForPackage(AccessFile.class);

        Preferences preferencesOnlineMode = Preferences.userNodeForPackage(PlayerVsPlayerController.class);
        //if (listType.equals("local-mode")) 
            preferencesLocalMode.put(CurrentDateTime.getCurrentDateTime(), CurrentDateTime.getCurrentDateTime());
            File dir = new File("record/savedGame");
            dir.mkdirs();
            file = new File(dir, preferencesLocalMode.get(CurrentDateTime.getCurrentDateTime(), ""));

           // return file;
        
        /*if (listType.equals("local-mode")) {
            preferencesLocalMode.put(CurrentDateTime.getCurrentDateTime(), CurrentDateTime.getCurrentDateTime());
            File dir = new File("record/savedGame");
            dir.mkdirs();
            file = new File(dir, preferencesLocalMode.get(CurrentDateTime.getCurrentDateTime(), ""));

            return file;*/
       /* } else if (listType.equals("online-mode")) {
            File dir = new File("record/savedOnlineGame");
            dir.mkdirs();
            preferencesOnlineMode.put(CurrentDateTime.getCurrentDateTime(), CurrentDateTime.getCurrentDateTime());
            file = new File(dir, preferencesOnlineMode.get(CurrentDateTime.getCurrentDateTime(), ""));

            return file;
        }*/
        try {
            if (file.createNewFile()) {
                System.out.println("file created");
                System.out.println("vvv");
            }
        } catch (IOException ex) {
            Logger.getLogger(AccessFile.class.getName()).log(Level.SEVERE, null, ex);
        }

     
         System.out.println(filePath+"daoaa");
       
        return file;
    }

    public static void writeFile(ArrayList<PlayerMove> moves) {
        System.out.println("enterwrite");
        FileOutputStream writer = null;
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        //    String JSONObject = gson.toJson(moves);
        try {
            
            System.out.println("here WriteFile = "+file);
           // writer = new FileOutputStream(new File("F:\\tic tac\\Tic-Tac-Teo\\TicTacClient\\record\\doaa.json"));
           writer = new FileOutputStream(new File(createFile(filePath)+".json"));
            System.out.println(writer+"daa");
           com.google.gson.JsonArray Array = gson.toJsonTree(moves).getAsJsonArray();
            writer.write(Array.toString().getBytes());
            System.out.println(Array.getAsJsonArray().size()+"json");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AccessFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AccessFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(AccessFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static List<PlayerMove> readFile(String fileName) {
        JsonReader reader;

        List<PlayerMove> moves = new ArrayList<PlayerMove>();

        try {System.out.println("readFie Nameee____" +filePath+"\\record\\savedGame\\"+fileName );
            reader = Json.createReader(new FileReader(filePath+"\\record\\savedGame\\"+fileName));
            JsonStructure jsonst = reader.read();
            if (jsonst.getValueType() == JsonStructure.ValueType.ARRAY) {
                JsonArray array = (JsonArray) jsonst;
                for (int i = 0; i < array.size(); i++) {
                    JsonObject obj = (JsonObject) array.get(i);
                    PlayerMove move = new PlayerMove();
                    move.setX(obj.getInt("x"));
                    move.setY(obj.getInt("y"));
                    move.setIsX(obj.getBoolean("isX"));
                    move.setIsPlayerOneMove(obj.getBoolean("isPlayerOneMove"));
                    moves.add(move);

                }

                /* for(PlayerMove move : moves) {
                      System.out.println("x");
                    System.out.println(move);
                     System.out.println("x");
                }*/
            }
            System.out.println("size id" + moves.size());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AccessFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        return moves;

    }
}
