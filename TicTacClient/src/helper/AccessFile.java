/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import controller.PlayerVsPlayerController;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import model.PlayerMove;

public class AccessFile {

    private static File file;
    static String filePath = new File("").getAbsolutePath();
    static String pathLocalFile = filePath.concat("\\src\\savedGame\\");
    static String pathOnlineFile = filePath.concat("\\src\\savedOnlineGame\\");

    public AccessFile() {
    }

    public static void createFile(String listType) {

        Preferences preferencesLocalMode = Preferences.userNodeForPackage(AccessFile.class);

        Preferences preferencesOnlineMode = Preferences.userNodeForPackage(PlayerVsPlayerController.class);

        if (listType.equals("local-mode")) {
            preferencesLocalMode.put(CurrentDateTime.getCurrentDateTime(), CurrentDateTime.getCurrentDateTime());
            File dir = new File("record/savedGame");
            dir.mkdirs();
            file = new File(dir, preferencesLocalMode.get(CurrentDateTime.getCurrentDateTime(), ""));

        } else if (listType.equals("online-mode")) {
            File dir = new File("record/savedOnlineGame");
            dir.mkdirs();
            preferencesOnlineMode.put(CurrentDateTime.getCurrentDateTime(), CurrentDateTime.getCurrentDateTime());
            file = new File(dir, preferencesOnlineMode.get(CurrentDateTime.getCurrentDateTime(), ""));

        }
        try {

            if (file.createNewFile()) {
                System.out.println("file created");
            }

        } catch (IOException ex) {
            Logger.getLogger(AccessFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void writeFile(PlayerMove s[]) {
        try {
            System.out.println("file Created an writ into it");

            if (file.exists()) {
                for (int i = 0; i < s.length; i++) {

                    FileWriter writer = new FileWriter(file, true);
                    writer.write(s[i].getX() + s[i].getY());
                    if (s[i].isIsX()) {
                        writer.write("X");
                    } else {
                        writer.write("O");
                    }

                    writer.flush();
                    writer.close();
                    System.out.println("write in file : " + i);
                }
            }
        } catch (IOException ex) {
            System.out.println("error during write file");
            Logger.getLogger(AccessFile.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void writeFile(String s) {
        try {
            System.out.println("file Created an writ into it");

            if (file.exists()) {

                FileWriter writer = new FileWriter(file, true);
                writer.write(s);

                writer.flush();
                writer.close();
                System.out.println("write in file : in string  " );
            }
        } catch (IOException ex) {
            System.out.println("error during write file string");
            Logger.getLogger(AccessFile.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static String readFileAsString(String fileName) {
        String data = "";
        try {

            data = new String(Files.readAllBytes(Paths.get(fileName)));

        } catch (IOException ex) {

            Logger.getLogger(AccessFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
}
