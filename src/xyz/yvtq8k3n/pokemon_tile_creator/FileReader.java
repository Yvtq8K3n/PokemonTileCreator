package xyz.yvtq8k3n.pokemon_tile_creator;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.prefs.Preferences;

public class FileReader {
    public static BufferedImage loadImage(){
        BufferedImage image = null;

        Preferences pref = Preferences.userRoot();
        String path = pref.get("DEFAULT_PATH", "");

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(path));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "BMP, JPG, PNG & GIF Images", "bmp", "jpg", "png", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(new JFrame());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File f = chooser.getSelectedFile();
                image = ImageIO.read(f);

                chooser.setCurrentDirectory(f);
                // Save the selected path
                pref.put("DEFAULT_PATH", f.getAbsolutePath());

            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
        }
        return image;
    }

    public static BufferedImage loadPalette(){
        return null;
    }
}
