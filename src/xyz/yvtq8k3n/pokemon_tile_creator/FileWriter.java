package xyz.yvtq8k3n.pokemon_tile_creator;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.prefs.Preferences;

public class FileWriter {
    public static void writeTileset(BufferedImage image, byte[] palette) {
        Preferences pref = Preferences.userRoot();
        String path = pref.get("DEFAULT_PATH", "");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(path));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Tileset", "bmp", "jpg", "png","pal");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(new JFrame());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File f = fileChooser.getSelectedFile();
                ImageIO.write(image, "bmp", new File(f.getAbsolutePath() + ".bmp"));
                Files.write(new File(f.getAbsolutePath() + ".pal").toPath(), palette);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Failed to save Tileset!");
            }
        } else {
            System.out.println("No file chosen!");
        }
    }
}
