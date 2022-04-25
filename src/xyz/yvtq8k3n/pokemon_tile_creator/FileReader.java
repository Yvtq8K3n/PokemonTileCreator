package xyz.yvtq8k3n.pokemon_tile_creator;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;

public class FileReader {
    public static BufferedImage loadImage(){
        BufferedImage image = null;

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "BMP, JPG, PNG & GIF Images", "bmp", "jpg", "png", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(new JFrame());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File uploadedFile = chooser.getSelectedFile();
                image = ImageIO.read(uploadedFile);

            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
        }
        return image;
    }
}
