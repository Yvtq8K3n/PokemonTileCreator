package xyz.yvtq8k3n.pokemon_tile_creator;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.prefs.Preferences;

public class FileReader {

    public static File loadImage(){
        File file = null;

        //Switch to Operative System L&F
        LookAndFeel originalLaf = UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        Preferences pref = Preferences.userRoot();
        String path = pref.get("DEFAULT_PATH", "");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(path));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "BMP, JPG, PNG & GIF Images", "bmp", "jpg", "png");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(new JFrame());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                file = fileChooser.getSelectedFile();
                fileChooser.setCurrentDirectory(file);
                // Save the selected path
                pref.put("DEFAULT_PATH", file.getAbsolutePath());

            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Failed to load Image!");
            }
            System.out.println("You chose to open this file: " +
                    fileChooser.getSelectedFile().getName());
        }

        //Flick the L&F back to the default
        try {
            UIManager.setLookAndFeel(originalLaf);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        return file;
    }

    public static byte[] loadPalette(){
        //Switch to Operative System L&F
        LookAndFeel originalLaf = UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

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
                JOptionPane.showMessageDialog(null, "Failed to load Palette!");
            }
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
        }

        //Flick the L&F back to the default
        try {
            UIManager.setLookAndFeel(originalLaf);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        return palette;
    }
}
