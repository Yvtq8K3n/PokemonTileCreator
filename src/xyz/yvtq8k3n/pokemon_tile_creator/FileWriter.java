package xyz.yvtq8k3n.pokemon_tile_creator;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class FileWriter {
    public static void exportTileset(BufferedImage image){
        Preferences pref = Preferences.userRoot();
        String path = pref.get("DEFAULT_PATH", "");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(path));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "BMP, JPG, PNG & GIF Images", "bmp", "jpg", "png", "gif");
        fileChooser.setFileFilter(filter);
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                File f = fileChooser.getSelectedFile();
                ImageIO.write(image, "bmp", new File(f.getAbsolutePath()+".bmp"));

                // Save the selected path
                fileChooser.setCurrentDirectory(f);
                pref.put("DEFAULT_PATH", f.getAbsolutePath());
            } catch (IOException ex) {
                System.out.println("Failed to save image!");
            }
        } else {
            System.out.println("No file choosen!");
        }
    }
}
