package xyz.yvtq8k3n.pokemon_tile_creator;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.util.prefs.Preferences;

public class FileReader {
    public static BufferedImage loadImage(){
        BufferedImage image = null;

        Preferences pref = Preferences.userRoot();
        String path = pref.get("DEFAULT_PATH", "");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(path));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "BMP, JPG, PNG & GIF Images", "bmp", "jpg", "png", "gif");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(new JFrame());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File f = fileChooser.getSelectedFile();
                image = ImageIO.read(f);

                fileChooser.setCurrentDirectory(f);
                // Save the selected path
                pref.put("DEFAULT_PATH", f.getAbsolutePath());

            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            System.out.println("You chose to open this file: " +
                    fileChooser.getSelectedFile().getName());
        }
        return image;
    }

    public static byte[] loadPalette(){
        byte[] palette = new byte[0];

        Preferences pref = Preferences.userRoot();
        String path = pref.get("DEFAULT_PATH", "");

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(path));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Palette Files", "pal");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(new JFrame());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File f = chooser.getSelectedFile();
                palette = Files.readAllBytes(f.toPath());

                chooser.setCurrentDirectory(f);
                // Save the selected path
                pref.put("DEFAULT_PATH", f.getAbsolutePath());

            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
        }
        return palette;
    }
}
