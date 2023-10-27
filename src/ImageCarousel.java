import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageCarousel extends JFrame {
    private final JPanel imagePanel;
    private int currentIndex = 0;

    private final static String PATH_TO_LOOP = "G:\\Result\\JPG";

    public ImageCarousel() {
        setTitle("Image Carousel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 1024);

        imagePanel = new JPanel();
        add(imagePanel);

        // Create a timer to change images every 3 seconds
        int delay = 500;
        Timer timer = new Timer(delay, e -> showNextImage());
        timer.start();
    }

    private void showNextImage() {
        File rootDirectory  = new File(PATH_TO_LOOP);
        List<File> allFiles = listAllFiles(rootDirectory);
        currentIndex = (currentIndex + 1) % allFiles.size();
        String imagePath = allFiles.get(currentIndex).getPath();

        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image scaledImage = imageIcon.getImage().getScaledInstance(
                imagePanel.getWidth(), imagePanel.getHeight(), Image.SCALE_SMOOTH
        );
        imageIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(imageIcon);

        imagePanel.removeAll();
        imagePanel.setBorder(BorderFactory.createTitledBorder(imagePath));
        imagePanel.add(imageLabel);
        imagePanel.revalidate();
        imagePanel.repaint();
    }


    public static List<File> listAllFiles(File directory) {
        List<File> fileList = new ArrayList<>();

        if (directory.isDirectory()) {
            File[] subFiles = directory.listFiles();
            if (subFiles != null) {
                for (File file : subFiles) {
                    if (file.isDirectory()) {
                        // Recursively list files in subdirectories
                        fileList.addAll(listAllFiles(file));
                    } else {
                        // Add the file to the list
                        fileList.add(file);
                    }
                }
            }
        } else if (directory.isFile()) {
            // If the provided file is not a directory, just add it to the list
            fileList.add(directory);
        }

        return fileList;
    }


}
