import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ImageCarousel extends JFrame {
    private final JPanel imagePanel;
    private int currentIndex = 0;

    private final static String PATH_TO_LOOP = "";

    public ImageCarousel() {
        setTitle("Image Carousel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        imagePanel = new JPanel();
        add(imagePanel);

        // Create a timer to change images every 3 seconds
        int delay = 3000;
        Timer timer = new Timer(delay, e -> showNextImage());
        timer.start();
    }

    private void showNextImage() {
        File[] imagePaths = getlist();
        currentIndex = (currentIndex + 1) % imagePaths.length;
        String imagePath = imagePaths[currentIndex].getPath();

        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image scaledImage = imageIcon.getImage().getScaledInstance(
                imagePanel.getWidth(), imagePanel.getHeight(), Image.SCALE_SMOOTH
        );
        imageIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(imageIcon);

        imagePanel.removeAll();
        imagePanel.add(imageLabel);
        imagePanel.revalidate();
        imagePanel.repaint();
    }


    private File[] getlist() {
        File directory = new File(PATH_TO_LOOP);
        if (directory.exists() && directory.isDirectory()) {
            return directory.listFiles();
        } else {
            System.err.println("The specified directory does not exist or is not a directory.");
        }
        return null;
    }

}
