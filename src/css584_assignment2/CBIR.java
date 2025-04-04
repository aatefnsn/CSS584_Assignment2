/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ahmed_nada
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.*;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.lang.Object.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//public class readImage {
class readImage{
    int imageCount = 1; // counter to keep track of number of pictures
    int intensityBins[] = new int[25]; // intensity bin array for each picture
    int intensityMatrix[][] = new int[100][25]; // intensity bin matrix for al l00 pictures
    int colorCodeBins[] = new int[64]; // color code bin array for each picture
    int colorCodeMatrix[][] = new int[100][64]; // color code bin for all 100 pictures

    public readImage() {
        BufferedImage image = null;
        while (imageCount < 101) { // counter to get the whole 100 pictures in images folder
            try {
                image = ImageIO.read(new File("images/" + imageCount + ".jpg")); // read each image sequential
                //System.out.println("imageCount is " + imageCount);
                JFrame frame = new JFrame(); // create new frame
                frame.getContentPane().setLayout(new FlowLayout());
                frame.getContentPane().add(new JLabel(new ImageIcon(image)));
                frame.pack();
                int width = image.getWidth(); // get picture width to be used at getIntensity and getcolorCode functions
                int height = image.getHeight(); // get picture height to be used at getIntensity and getcolorCode functions
                //System.out.println("image width is " + width);
                //System.out.println("image height is " + height);
                //System.out.println("image width * height is " + height*width);
                getIntensity(image, height, width, imageCount); // call the getintensity function on each read image
                getColorCode(image, height, width, imageCount); // call the getColorcode function on each read image
                imageCount++; // increment the imageCount counter
            } catch (IOException e) {
                System.out.println("Error occurred when reading the file."); // printed if an exception is caught
            }
        }
        //System.out.println("imageCount is " + imageCount);
        System.out.println("Insity Matrix is \n");
        for (int o = 0; o < imageCount - 1; o++) { // for loop to print the intensity matrix
            //System.out.println(Arrays.toString(intensityMatrix[o]));
        }
        System.out.println("Color Code Matrix is \n");
        for (int o = 0; o < imageCount - 1; o++) { // for loop to print the color code matrix
            //System.out.println(Arrays.toString(colorCodeMatrix[o]));
        }

        writeIntensity(); // call the write intensity function to write the intensity matrix into text file
        writeColorCode(); // call the write color code function to write the color code matrix into text file
    }

    //intensity method 
    public void getIntensity(BufferedImage image, int height, int width, int imageCount) {
        //System.out.println("imageCount is " + imageCount);
        for (int x = 0; x < width; x = x + 1) { // nested for loop to go over each pixel in each image, going over width
            for (int y = 0; y < height; y = y + 1) { // going over height
                Color mycolor = new Color(image.getRGB(x, y)); // get RGB values in a new color object
                double I = (0.299 * mycolor.getRed()) + (0.587 * mycolor.getGreen()) + (0.114 * mycolor.getBlue()); // calculate the 
                //intensity of the pixel as per the equation in the assignment
                //System.out.println("Intensity is " + I + " for x=" + x + " and y=" + y); // print the intensity of each pixel
                if (I <= 10) { // if statements to create the intinsity bins, increement the bin value if the intensity falls into the bin intensity range
                    intensityBins[0]++;
                } else if (I <= 20) {
                    intensityBins[1]++;
                } else if (I <= 30) {
                    intensityBins[2]++;
                } else if (I <= 40) {
                    intensityBins[3]++;
                } else if (I <= 50) {
                    intensityBins[4]++;
                } else if (I <= 60) {
                    intensityBins[5]++;
                } else if (I <= 70) {
                    intensityBins[6]++;
                } else if (I <= 80) {
                    intensityBins[7]++;
                } else if (I <= 90) {
                    intensityBins[8]++;
                } else if (I <= 100) {
                    intensityBins[9]++;
                } else if (I <= 110) {
                    intensityBins[10]++;
                } else if (I <= 120) {
                    intensityBins[11]++;
                } else if (I <= 130) {
                    intensityBins[12]++;
                } else if (I <= 140) {
                    intensityBins[13]++;
                } else if (I <= 150) {
                    intensityBins[14]++;
                } else if (I <= 160) {
                    intensityBins[15]++;
                } else if (I <= 170) {
                    intensityBins[16]++;
                } else if (I <= 180) {
                    intensityBins[17]++;
                } else if (I <= 190) {
                    intensityBins[18]++;
                } else if (I <= 200) {
                    intensityBins[19]++;
                } else if (I <= 210) {
                    intensityBins[20]++;
                } else if (I <= 220) {
                    intensityBins[21]++;
                } else if (I <= 230) {
                    intensityBins[22]++;
                } else if (I <= 240) {
                    intensityBins[23]++;
                } else if (I <= 255) {
                    intensityBins[24]++;
                }
            }
        }
        //System.out.println("Insity Bin is \n" + Arrays.toString(intensityBins));
        for (int p = 0; p < 25; p++) { // for loop to copy the array into the intensity matrix
            intensityMatrix[imageCount - 1][p] = intensityBins[p]; // copy the intensity bin array for the current image to its row in the bigger intensity matrix
            intensityBins[p] = 0; // reset the intensity matrix before running sgain on the next image
        }
    }

    //color code method to calculate the color code representation of each image
    public void getColorCode(BufferedImage image, int height, int width, int imageCount) {
        for (int x = 0; x < width; x = x + 1) { // nested loop to go over each pixel of the image
            for (int y = 0; y < height; y = y + 1) {
                Color mycolor = new Color(image.getRGB(x, y)); // color that has the RGB values of each pixel

                int redbits = mycolor.getRed(); // get integer represntation of red colour
                //System.out.println("Red bits is "+redbits);
                String redbin = Integer.toBinaryString(redbits); // transform the red integer reprenstation into string
                redbin = (("00000000" + redbin).substring(redbin.length())).substring(0, 2); // transfor the string to the full byte represntation then get the first 2 bits substring
                //System.out.println("First 2 Red bits in binary is "+redbin);

                int greenbits = mycolor.getGreen(); // get integer represntation of green colour
                //System.out.println("Green bits is "+greenbits);
                String greenbin = Integer.toBinaryString(greenbits); // transform the green integer reprenstation into string
                greenbin = (("00000000" + greenbin).substring(greenbin.length())).substring(0, 2); // transfor the string to the full byte represntation then get the first 2 bits substring
                //System.out.println("First 2 Green bits in binary is "+greenbin);

                int bluebits = mycolor.getBlue(); //same as with red and green
                //System.out.println("Blue bits is "+bluebits);
                String bluebin = Integer.toBinaryString(bluebits);
                bluebin = (("00000000" + bluebin).substring(bluebin.length())).substring(0, 2);
                //System.out.println("First 2 Blue bits in binary is "+bluebin);

                String pixelcolorcode = redbin + greenbin + bluebin; // append the strings togehter
                //to form the pixel color code represntation first 2 bits of red, followed by first 2 bits of green followed by first 2 bits of blue
                //System.out.println("Pixel Color Code "+pixelcolorcode);

                int decimalValue = Integer.parseInt(pixelcolorcode, 2); //get the decimal value of the pixel color code
                //System.out.println("Pixel Color Code in decimal is "+decimalValue);
                colorCodeBins[decimalValue]++; // to get the color code bins, increment the bin representating the integer value of the color code
            }
        }
        //System.out.println("Color Code Bin is \n" + Arrays.toString(colorCodeBins));
        for (int p = 0; p < 64; p++) { // for loop to copy the color code bin array into the color code matrix
            colorCodeMatrix[imageCount - 1][p] = colorCodeBins[p];
            colorCodeBins[p] = 0;
        }
    }

    //This method writes the contents of the colorCode matrix to a file named colorCodes.txt.
    public void writeColorCode() {
        System.out.println("Color Code Matrix Columns length " + colorCodeMatrix[0].length);
        System.out.println("Color Code Matrix Rows length " + colorCodeMatrix.length);
        StringBuilder builder = new StringBuilder(); // string builder to be used to write the string representing the color code of one image into a txt file
        for (int i = 0; i < colorCodeMatrix.length; i++)//for each row
        {
            for (int j = 0; j < colorCodeMatrix[0].length; j++)//for each column
            {
                builder.append(colorCodeMatrix[i][j] + "");//append to the output string
                if (j < colorCodeMatrix.length - 1)//if this is not the last row element
                {
                    builder.append(",");//then add comma
                }
            }
            builder.append("\n");//append new line at the end of the row
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("colorCodes.txt")); // create and open a new file writer in a file named colorCodes.txt
            writer.write(builder.toString());//save the string representation of the board
            writer.close(); // close the buffered writer
            System.out.println("colorCodes.txt is closed and ready for parsing");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //This method writes the contents of the intensity matrix to a file called intensity.txt
    public void writeIntensity() {
        System.out.println("Intensity Matrix Columns length " + intensityMatrix[0].length);
        System.out.println("Intensity Matrix Rows length " + intensityMatrix.length);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < intensityMatrix.length; i++)//for each row
        {
            for (int j = 0; j < intensityMatrix[0].length; j++)//for each column
            {
                builder.append(intensityMatrix[i][j] + "");//append to the output string
                if (j < intensityMatrix.length - 1)//if this is not the last row element
                {
                    builder.append(",");//then add comma
                }
            }
            builder.append("\n");//append new line at the end of the row
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("intensity.txt")); // create and open a new file writer in a file named intensity.txt
            writer.write(builder.toString());//save the string representation of the board
            writer.close(); // close the buffered writer
            System.out.println("intensity.txt is closed and ready for parsing");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class CBIR extends JFrame {

    private JLabel photographLabel = new JLabel();  // new jlabel  
    private JButton[] button; //creates an array of JButtons for images
    private int[] buttonOrder = new int[101]; //creates an array to keep up with the image order
    private int[] selectedImages = new int[99];
    private double[] imageSize = new double[100]; //keeps up with the image sizes
    private GridLayout gridLayout1;
    private GridLayout gridLayout2;
    private GridLayout gridLayout3;
    private GridLayout gridLayout4;
    private JPanel panelBottom1;
    private JPanel panelBottom2;
    private JPanel panelTop;
    private JPanel buttonPanel;
    private int[][] intensityMatrix = new int[100][25]; // intensity matrix that will be used to copy the data from text file
    private int[][] colorCodeMatrix = new int[100][64]; // color code matrix that will be used to copy the data from text file
    private double[][] colorCodeIntensityMatrix = new double[100][89];
    //private Map<Double, LinkedList<Integer>> map;
    int picNo = 0; // variable to hold the pic/image index
    int imageCount = 1; //keeps up with the number of images displayed since the first page.
    int selectedImagesIndex;
    int pageNo = 1; // variable to hold the page number
    int iteration = -1;
    JButton previousPage;
    JButton nextPage;
    JCheckBox checkbox; // relevance feedback check box
    JCheckBox iconCheckBox; // check box for each image in the first page

    public static void main(String args[]) {
        new readImage();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CBIR app = new CBIR();
                app.setVisible(true);
            }
        });
    }

    public CBIR() {
        //The following lines set up the interface including the layout of the buttons and JPanels.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Content-Based Image Retrieval system: Please Select an Image and the preferred method (Color Code Method or Intensity Method)");
        panelBottom1 = new JPanel();
        panelBottom2 = new JPanel();
        panelTop = new JPanel();
        buttonPanel = new JPanel();
        gridLayout1 = new GridLayout(4, 5, 5, 5);
        gridLayout2 = new GridLayout(2, 1, 5, 5);
        gridLayout3 = new GridLayout(1, 2, 5, 5);
        gridLayout4 = new GridLayout(2, 3, 5, 5);
        setLayout(gridLayout2);
        panelBottom1.setLayout(gridLayout1);
        panelBottom2.setLayout(gridLayout1);
        panelTop.setLayout(gridLayout3);
        add(panelTop);
        add(panelBottom1);
        photographLabel.setVerticalTextPosition(JLabel.BOTTOM);
        photographLabel.setHorizontalTextPosition(JLabel.CENTER);
        photographLabel.setHorizontalAlignment(JLabel.CENTER);
        photographLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        buttonPanel.setLayout(gridLayout4);
        panelTop.add(photographLabel);

        panelTop.add(buttonPanel);
        checkbox = new JCheckBox("Relevance Feedback"); // Check Box to enable the simplified relevance feedback
        previousPage = new JButton("Previous Page"); // previous 20 images
        nextPage = new JButton("Next Page"); // next 20 images
        JButton intensity = new JButton("Intensity"); // intensity button to select intensity method
        JButton colorCode = new JButton("Color Code"); // color code button to select color code method
        JButton colorCodeIntensity = new JButton("Color Code and Intensity");
        JButton refresh = new JButton("Refresh"); // refresh button to revert all images to their original positions without having to restart the program
        buttonPanel.add(previousPage);
        buttonPanel.add(nextPage);
        buttonPanel.add(intensity);
        buttonPanel.add(colorCode);
        buttonPanel.add(colorCodeIntensity);
        buttonPanel.add(refresh);
        buttonPanel.add(checkbox); // add the relevance feedback checkbox

        nextPage.addActionListener(new nextPageHandler()); // setting action for each button using ActionListener
        previousPage.addActionListener(new previousPageHandler());
        intensity.addActionListener(new intensityHandler());
        colorCode.addActionListener(new colorCodeHandler());
        colorCodeIntensity.addActionListener(new colorCodeIntensityHandler()); // action listener for the colorCodeIntensity
        refresh.addActionListener(new refreshHandler());
        checkbox.addActionListener(new checkboxHandler());
        //iconCheckBox.addActionListener(new IconCheckBoxHandler());
        setSize(1100, 750);
        // this centers the frame on the screen
        setLocationRelativeTo(null);

        button = new JButton[101];
        /*This for loop goes through the images in the database and stores them as icons and adds
         * the images to JButtons and then to the JButton array
         */
        for (int i = 1; i < 101; i++) {
            ImageIcon icon;
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("images/" + i + ".jpg")); // read the images from the images folder
                int width = image.getWidth(); // get the width as it will be used in calculating the distance
                int height = image.getHeight(); // get the height of the image as it will be used in calculating the distance
                imageSize[i - 1] = width * height; // add the image size width x height in the image size array
            } catch (IOException e) {
                System.out.println("Error occurred when reading the file.");
            }
            icon = new ImageIcon(getClass().getResource("images/" + i + ".jpg")); // add a new icon for each image
            if (icon != null) {
                button[i] = new JButton(icon); // add the image icon in the button array
                button[i].addActionListener(new IconButtonHandler(i, icon));
                buttonOrder[i] = i; // add the index of each button in the button order array that will be used later on for image preview
            }
        }

        readIntensityFile(); // after getting the images and creating the icons, read the intensity.txt file from the readImage class
        readColorCodeFile(); // read the colorcode.txt resulting from the read image class
        displayFirstPage(); // display first page method which shows the first 20 image icons
    }

    /*This method opens the intensity text file containing the intensity matrix with the histogram bin values for each image.
     * The contents of the matrix are processed and stored in a two dimensional array called intensityMatrix.
     */
    public void readIntensityFile() { // read the intesity.txt file function
        Scanner read;
        String line = "";
        int lineNumber = 0;
        try {
            read = new Scanner(new File("intensity.txt")); // a new scanner object to read the intensity.txt
            while (read.hasNextLine()) { // while loop the scanner object has another line do the following 
                line = read.nextLine(); // read the following line
                String[] cols = line.split(","); // create a string array filled with the intensity bins after removing the comma separator 
                //System.out.println(Arrays.toString(cols));
                for (int j = 0; j < 25; j++) {
                    intensityMatrix[lineNumber][j] = Integer.parseInt(cols[j]); // parse the string value as integer and then add them in intensity matrix 
                }
                lineNumber++;
            }

            //Print the intensity Matrix
            System.out.println("Reconstructed Intensity Matrix");
            for (int o = 0; o < 100; o++) {
                System.out.println(Arrays.toString(intensityMatrix[o])); // print the intensity matrix
            }
        } catch (FileNotFoundException EE) { // catch statement if the intensity.txt file was not created previously by running the readImage class functions
            System.out.println("The file intensity.txt does not exist");
        }
    }

    /*This method opens the color code text file containing the color code matrix with the histogram bin values for each image.
     * The contents of the matrix are processed and stored in a two dimensional array called colorCodeMatrix.
     */
    private void readColorCodeFile() { // for colorcode.txt we are doing the same as reading and parsing the intensity.txt
        //StringTokenizer token;
        Scanner read;
        //Double colorCodeBin;
        int lineNumber = 0;
        String line = "";
        try {
            read = new Scanner(new File("colorCodes.txt"));
            while (read.hasNextLine()) {
                line = read.nextLine();
                String[] cols = line.split(",");
                //System.out.println(Arrays.toString(cols));
                for (int j = 0; j < 64; j++) {
                    colorCodeMatrix[lineNumber][j] = Integer.parseInt(cols[j]);
                }
                lineNumber++;
            }

            //Print the color code Matrix
            System.out.println("Reconstructed colorCode Matrix");
            for (int o = 0; o < 100; o++) {
                System.out.println(Arrays.toString(colorCodeMatrix[o]));
            }

        } catch (FileNotFoundException EE) {
            System.out.println("The file colorCodes.txt does not exist");
        }

    }

    /*This method displays the first twenty images in the panelBottom.  The for loop starts at number one and gets the image
     * number stored in the buttonOrder array and assigns the value to imageButNo.  The button associated with the image is 
     * then added to panelBottom1.  The for loop continues this process until twenty images are displayed in the panelBottom1
     */
    private void displayFirstPage() {
        imageCount = 1;
        pageNo = 1;
        previousPage.setEnabled(false); // in the firstp age, previous page should be disabled
        nextPage.setEnabled(true); // next page is enabled
        int imageButNo = 0;
        panelBottom1.removeAll(); // refresh the bottom panel
        for (int i = 1; i < 21; i++) {
            imageButNo = buttonOrder[i]; // get the image button number from the buttonOrder array
            panelBottom1.add(button[imageButNo]); // add the button of imageButton number to the bottom panel
            JLabel label1 = new JLabel(imageButNo + ".jpg"); // add a label for the button
            label1.setForeground(Color.BLACK); // set the color to balck
            label1.setFont(new Font("SansSerif", Font.BOLD, 28)); // set the font type and font size
            panelBottom1.add(label1); // add the label to the bottom panel for each new button
            imageCount++; // increment the image count
        }
        if (iteration == -1) {
            checkbox.setEnabled(false); // add a condition to only enable the relevence 
            //feeback checkbox if the color code intesity method has been selected, default number of iteration is -1 so the check box is deisabled
        } else {
            checkbox.setEnabled(true); // if the number of iterations is 0 or more this means that color code intensity method is being used so the check box can be enabled
        }
        panelBottom1.revalidate(); // revalidate and repaint the bottom panel
        panelBottom1.repaint();
    }

    private void displayFirstPageCB() {
        imageCount = 1;
        pageNo = 1;
        previousPage.setEnabled(false);
        nextPage.setEnabled(true);
        int imageButNo = 0;
        panelBottom1.removeAll(); // refresh the bottom panel
        for (int i = 1; i < 21; i++) {
            imageButNo = buttonOrder[i]; // get the image button number from the buttonOrder array
            iconCheckBox = new JCheckBox();
            iconCheckBox.addActionListener(new IconCheckBoxHandler(i, iconCheckBox));            
//            int height = button[i].getIcon().getIconHeight();
//            int width = button[i].getIcon().getIconWidth();
//            
//            ImageIcon icon = new ImageIcon(getClass().getResource("images/" + imageButNo + ".jpg"));
//            Image img = icon.getImage() ;  
//            Image newimg = img.getScaledInstance( 120, 120,  java.awt.Image.SCALE_SMOOTH ) ;  
//            icon = new ImageIcon( newimg );
//            button[imageButNo] = new JButton(icon);                        
            panelBottom1.add(button[imageButNo]); // add the button of imageButton number to the bottom panel
            JLabel label1 = new JLabel(imageButNo + ".jpg"); // add a label for the button
            label1.setForeground(Color.BLACK); // set the color to balck
            label1.setFont(new Font("SansSerif", Font.BOLD, 28)); // set the font type and font size
            panelBottom1.add(label1); // add the label to the bottom panel for each new button
            panelBottom1.add(iconCheckBox);
            
            //ImageIcon imageIcon = new ImageIcon(new ImageIcon("images/" + imageButNo + ".jpg").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
            //label1.setIcon(imageIcon);
            
            if (buttonOrder[i] == picNo) { // disable the check box for the query image since it will automatically be added in the images matrix, no need to select as relevant
                iconCheckBox.setEnabled(false);
            }
            for (int j = 0; j < selectedImages.length; j++) {
                if (buttonOrder[i] == selectedImages[j]) {
                    System.out.println("found a checked image");
                    iconCheckBox.setSelected(true);
                }
            }
            imageCount++; // increment the image count
        }
        panelBottom1.revalidate(); // revalidate and repaint the bottom panel
        panelBottom1.repaint();
    }
    
    public void flushSelectedImages(){ // function to reset/initialize the selected images array
        for (int y = 0; y < selectedImages.length; y++) {
                    selectedImages[y] = 0;
                }
                selectedImagesIndex = 0;
    }
    
    public void resetColorCodeIntensity(){ // function to reset the color code intensity method attributes
        iteration = -1; // if the query image has changed then set the iteration to -1
        flushSelectedImages(); // reset the selected images array
        checkbox.setSelected(false);
        checkbox.setEnabled(false);
        displayFirstPage();
    }
    
    private class checkboxHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (checkbox.isSelected()) {
                //System.out.println("Selected");
                if (pageNo == 1) {
                    displayFirstPageCB();
                }
                flushSelectedImages();

            } else {
                //System.out.println("Unselected");
                if (pageNo == 1) {
                    displayFirstPage();
                }
                flushSelectedImages();
            }
        }
    }

    /*This class implements an ActionListener for each iconButton.  When an icon button is clicked, the image on the 
     * the button is added to the photographLabel and the picNo is set to the image number selected and being displayed.
     */
    private class IconButtonHandler implements ActionListener {

        int pNo = 0;
        ImageIcon iconUsed;

        IconButtonHandler(int i, ImageIcon j) {
            pNo = i; // variable to store the image number when clicked
            iconUsed = j;  //sets the icon to the one used in the button
        }

        public void actionPerformed(ActionEvent e) {
            photographLabel.setIcon(iconUsed);
            picNo = pNo; // set the picNo variable to the current icon image
            resetColorCodeIntensity(); // if another image icon has been selected, this is a new query image so reset all color code intensity
        }
    }

    private class IconCheckBoxHandler implements ActionListener {

        //int index;
        int pNo = 0;
        JCheckBox iconCheckBoxUsed;

        IconCheckBoxHandler(int i, JCheckBox j) {
            pNo = buttonOrder[i]; // variable to store the image number when clicked
            iconCheckBoxUsed = j;  //sets the icon to the one used in the button
            //iconCheckBoxUsed.setSelected(true);
        }

        public void actionPerformed(ActionEvent e) {
            if (iconCheckBoxUsed.isSelected()) {
                System.out.println("Image " + pNo + " has been selected");
                selectedImages[selectedImagesIndex] = pNo; // adding the selected image to the array of selected images
                selectedImagesIndex++; // increment the index
                System.out.println("Selected Images array so far is: \n");
                for (int y = 0; y < selectedImages.length; y++) {
                    System.out.print(selectedImages[y] + ", ");
                }
                System.out.println("\n");
            } else {
                System.out.println("Image " + pNo + " has been unselected");
                for (int y = 0; y < selectedImages.length; y++) {
                    if (selectedImages[y] == pNo) { // look for the unselected image and and replace it with 0
                        selectedImages[y] = 0;
                    }
                }
                for (int y = 0; y < selectedImages.length - 1; y++) {
                    if (selectedImages[y] == 0 && selectedImages[y + 1] != 0) { // re-arrange the selected images array to remove the 0 values if any images have been deleted
                        selectedImages[y] = selectedImages[y + 1];
                        selectedImages[y + 1] = 0;
                    }
                }
                selectedImagesIndex--; // if image has been removed, then decrement the index

                System.out.println("Selected Images array so far is: \n");
                for (int y = 0; y < selectedImages.length - 1; y++) {
                    System.out.print(selectedImages[y] + ", ");
                }
            }
        }
    }

    /*This class implements an ActionListener for the nextPageButton.  The last image number to be displayed is set to the 
     * current image count plus 20.  If the endImage number equals 101, then the next page button does not display any new 
     * images because there are only 100 images to be displayed.  The first picture on the next page is the image located in 
     * the buttonOrder array at the imageCount
     */
    private class nextPageHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (pageNo < 5) {
                pageNo++;
            }
            System.out.println("current page number is " + pageNo);
            if (pageNo < 5) {
                nextPage.setEnabled(true);
                previousPage.setEnabled(true);
            } else if (pageNo == 5) {
                nextPage.setEnabled(false);
                previousPage.setEnabled(true);
            }
            int imageButNo = 0;
            int endImage = imageCount + 20; // next page handler should show the images starting the image count till the image count + 20
            if (endImage <= 101) { // if statement to make sure endimage does not exceed the number of images
                panelBottom1.removeAll(); // refresh bottom panel 
                for (int i = imageCount; i < endImage; i++) { //for loop from the current image count to the endImage counter
                    imageButNo = buttonOrder[i]; // get the image index from the buttonOrder array
                    panelBottom1.add(button[imageButNo]);
                    JLabel label1 = new JLabel(imageButNo + ".jpg");
                    label1.setForeground(Color.BLACK);
                    label1.setFont(new Font("SansSerif", Font.BOLD, 28));
                    panelBottom1.add(label1);
                    imageCount++;
                }
                panelBottom1.revalidate();
                panelBottom1.repaint();
            }
        }
    }

    /*This class implements an ActionListener for the previousPageButton.  The last image number to be displayed is set to the 
     * current image count minus 40.  If the endImage number is less than 1, then the previous page button does not display any new 
     * images because the starting image is 1.  The first picture on the next page is the image located in 
     * the buttonOrder array at the imageCount
     */
    private class previousPageHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (pageNo > 1) {
                pageNo--;
            }
            System.out.println("current page number is " + pageNo);
            if (pageNo > 1) {
                previousPage.setEnabled(true);
                nextPage.setEnabled(true);
            } else if (pageNo == 1 && checkbox.isSelected()) { // if first page and checkbox is selected, then display the firstPAgeCB function
                displayFirstPageCB();
                return;
                //previousPage.setEnabled(false);
                //nextPage.setEnabled(true);
            } else if (pageNo == 1) {
                previousPage.setEnabled(false); // if first page, disable the rpevious button
                nextPage.setEnabled(true);
            }
            int imageButNo = 0;
            int startImage = imageCount - 40; // the start image of the previous page is the current - 40 
            int endImage = imageCount - 20; // the end image of the previouis page is the current count - 20
            if (startImage >= 1) {
                panelBottom1.removeAll();
                /*The for loop goes through the buttonOrder array starting with the startImage value
             * and retrieves the image at that place and then adds the button to the panelBottom1.
                 */
                for (int i = startImage; i < endImage; i++) { // same for loop as in the nextPageHandler
                    imageButNo = buttonOrder[i];
                    panelBottom1.add(button[imageButNo]);
                    JLabel label1 = new JLabel(imageButNo + ".jpg");
                    label1.setForeground(Color.BLACK);
                    label1.setFont(new Font("SansSerif", Font.BOLD, 28));
                    panelBottom1.add(label1);
                    imageCount--; // decrementing the imageCount so if nextPAge is called again, it will display the 20 next images
                }
                panelBottom1.revalidate();
                panelBottom1.repaint();
            }
        }
    }

    private class refreshHandler implements ActionListener { // refreshes the images back to their original position

        public void actionPerformed(ActionEvent e) {
            //int imageButNo = 0;
            imageCount = 1; // set the image count to 1 
            //pageNo = 1;
            panelBottom1.removeAll(); // refresh the bottom panel
            for (int i = 1; i < 101; i++) {
                buttonOrder[i] = i; // refresh the image positions to the original position in the button order                   
            }
            resetColorCodeIntensity();      
        }
    }

    public double[] distance(int pic, double[] weight) { // function to calculate the weighted distance
        double[] distance = new double[100];
        //int pic = (picNo - 1); 
        for (int row = 0; row < 100; row++) {
            for (int column = 0; column < 89; column++) {
                double dist = ((colorCodeIntensityMatrix[pic][column] / imageSize[pic]) - (colorCodeIntensityMatrix[row][column] / imageSize[row])); // calculating distance as in the assignmnet document

                if (dist < 0) {
                    dist = dist * -1; // getting the modulus of the distance if -ve value then get the absolute +ve
                }
                dist = weight[column] * dist; // multiply the distance by the weight of that feature/column
                distance[row] = distance[row] + dist; // adding the distances together
            }
            //System.out.println("distance of row " + row + " is " + distance[row]);
        }
        return distance;
    }

    public double[] getAverage(double[][] doubleArray) {
        int rows = doubleArray.length;
        int columns = doubleArray[0].length;
        double[] average = new double[columns];
        for (int p = 0; p < columns; p++) {
            double sum = 0;
            for (int q = 0; q < rows; q++) {
                sum += doubleArray[q][p];
            }
            //System.out.println("Average is " + sum/Double.valueOf(rows));
            average[p] = sum / Double.valueOf(rows);
        }
        return average;
    }

    public double[] getWeight(double[][] doubleArray) {
        int columns = doubleArray[0].length;
        double[] weight = new double[columns];
        if (iteration == 0) { // first iteration
            int rows = doubleArray.length;
            for (int i = 0; i < columns; i++) {
                double columnsDouble = columns;
                weight[i] = 1.0 / columnsDouble; // wight is equivalent to 1 / the number of features
            }
        } else { // for subsequent iterations
            double sum = 0; // the double array is actually the selected images representation of features
            double[] average = new double[columns];
            average = getAverage(doubleArray);
            double[] std = new double[columns];
            std = getStandardDeviation(doubleArray);
            for (int i = 0; i < columns; i++) {
                if (std[i] != 0.0) {
                    weight[i] = 1.0 / std[i]; // wight is 1 / the standard deviation
                } else {
                    weight[i] = 0;
                }
            }
            for (int i = 0; i < columns; i++) {
                sum += weight[i]; // get sum
            }
            for (int i = 0; i < columns; i++) {
                weight[i] = weight[i] / sum; // normalize the weight by dividing over the sum of we
            }
        }
        return weight;
    }

    public void test(double[][] doubleArray) {
        //iteration =1;
        System.out.println("Inside Test");
        double[] average = new double[doubleArray[0].length];
        double[] weight = new double[doubleArray[0].length];
        double[] stddev = new double[doubleArray[0].length];
        average = getAverage(doubleArray);
        stddev = getStandardDeviation(doubleArray);
        weight = getWeight(doubleArray);

        System.out.println(Arrays.toString(average));
        System.out.println(Arrays.toString(stddev));
        System.out.println(Arrays.toString(weight));
    }

    public double[] getStandardDeviation(double[][] doubleArray) {
        int rows = doubleArray.length;
        System.out.println("rows in STD function is " + rows);
        int columns = doubleArray[0].length;
        double[] average = new double[columns];
        average = getAverage(doubleArray);
        double[] std = new double[columns];
        for (int p = 0; p < columns; p++) {
            double stdd = 0;
            for (int q = 0; q < rows; q++) {
                stdd += Math.pow((doubleArray[q][p] - average[p]), 2); // calculate the standard deviation
            }
            //System.out.println("std is " + Math.sqrt(stdd / (rows - 1)));
            std[p] = Math.sqrt(stdd / (rows - 1));
            System.out.println("std is " + std[p]);
        }
        return std;
    }

    private class colorCodeIntensityHandler implements ActionListener { // exactly the same as the previous intensityHandler except that the number of columns is 64 instead of 25

        public void actionPerformed(ActionEvent e) {
            if (picNo == 0) { // if no query images have been selected, display an error message to select a query image first
                JFrame f;
                f = new JFrame();
                JOptionPane.showMessageDialog(f, "Select an image first");
                return; 
            }
            if (iteration > -1 && selectedImages[0]==0){ // if intensity color code method has been started for a query image, 
                JFrame f;
                f = new JFrame();
                JOptionPane.showMessageDialog(f, "Select at least one relevent images before continuing");
                return; 
            }
            //double[][] test2 = {{-0.261116484, -0.789228329, 0.747241624, 0.226994973, 0.226994973, -0.261116484, -0.172440375}, {-1.30558242, 1.465709755, 0.977162123, -1.286304844, -1.286304844, 1.479660076, 0.977162123}, {0.783349452, -0.338240713, -0.862201873, -0.075664991, -0.075664991, -0.609271796, 0.517321124}};
            //test(test2);
            if (iteration == -1) { // the very first iteration
                double[] average = new double[89];
                double[] std = new double[89];
                double[][] intensityMatrixNew = new double[100][25];
                double[][] colorCodeMatrixNew = new double[100][64];
                for (int p = 0; p < 100; p++) {
                    for (int q = 0; q < 25; q++) {
                        intensityMatrixNew[p][q] = intensityMatrix[p][q] / imageSize[p]; // normalize the intensity matrix
                    }
                }

                for (int p = 0; p < 100; p++) {
                    for (int q = 0; q < 64; q++) {
                        colorCodeMatrixNew[p][q] = colorCodeMatrix[p][q] / imageSize[p]; // normalize the color code matrix
                    }
                }

                System.out.println("New Normalized colorCode Matrix");
                for (int o = 0; o < 100; o++) {
                    //System.out.println(Arrays.toString(colorCodeMatrixNew[o]));
                }

                System.out.println("\n");

                System.out.println("New Normalized Intensity Matrix");
                for (int o = 0; o < 100; o++) {
                    //System.out.println(Arrays.toString(intensityMatrixNew[o]));
                }

                for (int p = 0; p < 100; p++) { // concatenate the 2 matrixes together intensity matrix first then color code
                    int i = 25;
                    for (int q = 0; q < 89; q++) {
                        if (q <= 24) {
                            colorCodeIntensityMatrix[p][q] = intensityMatrixNew[p][q]; // copy contents of intensity matrix
                        } else {
                            colorCodeIntensityMatrix[p][q] = colorCodeMatrixNew[p][q - i]; // copy contents of color code matrix
                        }
                    }
                }

                System.out.println("\n");
                System.out.println("New combined Matrix");
                for (int o = 0; o < 100; o++) {
                    System.out.println(Arrays.toString(colorCodeIntensityMatrix[o]));
                }

                average = getAverage(colorCodeIntensityMatrix);
                System.out.println("\n");
                System.out.println("Average Array");
                System.out.println(Arrays.toString(average));

                std = getStandardDeviation(colorCodeIntensityMatrix);
                System.out.println("\n");
                System.out.println("Standard Deviation Array");
                System.out.println(Arrays.toString(std));

                for (int p = 0; p < 89; p++) {
                    for (int q = 0; q < 100; q++) {
                        if (std[p] == 0.0) {
                            colorCodeIntensityMatrix[q][p] = 0;
                        } else {
                            colorCodeIntensityMatrix[q][p] = (colorCodeIntensityMatrix[q][p] - average[p]) / std[p]; // normalize array using Gaussian normalization
                        }
                    }
                }

                System.out.println("Gaussian Normalized Color Code and Intensity Matrix");
                for (int o = 0; o < 100; o++) {
                    System.out.println(Arrays.toString(colorCodeIntensityMatrix[o]));
                }

                iteration++;
                //System.out.println("Iteration now is: " + iteration);
            }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//            System.out.println("\n");
//            System.out.println("Color Code Intensity method Weighted Distance Matrix of picture " + picNo);
            int pic = (picNo - 1);
            double[] distance = new double[100];
            double[] weight = new double[89];
            if (iteration == 0) { // first iteration using equal weights and use the color code intensity matrix itself 
                weight = getWeight(colorCodeIntensityMatrix);
            } else {
                System.out.println("Now " + selectedImagesIndex + " images are selected");
                int size = selectedImagesIndex;
                size++; // increment size to account for the query image being in the selected images matrix
                System.out.println("selected pictures array size is " + size);
                double[][] selected = new double[size][89]; // create a new matrix for the selected images
                for (int j = 0; j < size; j++) {
                    if (j == 0) {
                        for (int i = 0; i < 89; i++) { // first row in selected images will be the query image
                            selected[j][i] = colorCodeIntensityMatrix[picNo - 1][i]; // copying query image features values
                        }
                    } else {
                        for (int i = 0; i < 89; i++) {
                            selected[j][i] = colorCodeIntensityMatrix[selectedImages[j - 1] - 1][i]; // copying selected images features value
                        }
                    }
                }

                System.out.println("Selected Matrix");
                for (int o = 0; o < size; o++) {
                    System.out.println(Arrays.toString(selected[o]));
                }

                weight = getWeight(selected); // get weight based on the selected images matrix 
            }

            distance = distance(pic, weight); // calculate the weighted distance
            for (int o = 0; o < 100; o++) {
                System.out.print((distance[o]) + " ,");
            }

            System.out.println("\n");
            buttonOrder[1] = picNo; // set the selected picture to be the first in the button order, 
            //display first starts at buttonOder of 1 so the selected image will always be displayed first
            for (int u = 2; u < 101; u++) { // for loop to go over all the remaining images
                double smallest = 50000000; // setting the smallest distance variable to a random big number
                int smallestIndex = -1; // setting the index of the smallest to -1
                for (int k = 0; k < 100; k++) { // for loop to go over the ditance array and find the smallest distance to another image and then get the index of that next similar image
                    if (distance[k] < smallest && distance[k] != 0) { // f distance is smaller and if it is not 0 (same picture or a picture that has been already listed as similar)
                        smallest = distance[k]; // update smallest value
                        smallestIndex = k; // update the index
                    }
                }
                distance[smallestIndex] = 0; // once found the smallest distance, set it now to 0 so we do not go over it once again
                ++smallestIndex; // increment the smallest index by 1 to match the button Order
                //System.out.println("Smallest Index is " + smallestIndex);
                buttonOrder[u] = smallestIndex; // update button order
            }
            for (int u = 1; u < 101; u++) {
                System.out.print(buttonOrder[u] + ", "); // printing the button 
                //Order which will be the sequnce of images from the highest similar to the least similar according to intensity method
            }
            imageCount = 1; // before re-displaying we need to make sure to revert the imageCount to 1 to start displaying images from the start
            if (!checkbox.isSelected()) {
                displayFirstPage(); // call the display first images to show the first 20 images, clicking next will display the next 20 and so on
            } else {
                displayFirstPageCB();
            }
            iteration++;
        }
    }

    /*This class implements an ActionListener when the user selects the intensityHandler button.  The image number that the
     * user would like to find similar images for is stored in the variable pic.  pic takes the image number associated with
     * the image selected and subtracts one to account for the fact that the intensityMatrix starts with zero and not one.
     * The size of the image is retrieved from the imageSize array.  The selected image's intensity bin values are 
     * compared to all the other image's intensity bin values and a score is determined for how well the images compare.
     * The images are then arranged from most similar to the least.
     */
    private class intensityHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (picNo == 0) {
                JFrame f;
                //OptionPaneExample(){  
                f = new JFrame();
                JOptionPane.showMessageDialog(f, "Select an image first");
                return;
                //}  
            }
            iteration = -1;
            for (int u = 0; u < selectedImages.length; u++) {
                selectedImages[u] = 0;
            }
            selectedImagesIndex = 0;
            if (checkbox.isSelected()) {
                checkbox.setSelected(false);
                checkbox.setEnabled(false);
            }
            double[] distance = new double[100]; // distance array to hold the distance value of each image to the other image in that index
            //map = new HashMap<Double, LinkedList<Integer>>();
            int pic = (picNo - 1); // the position of the pic in the intensity matrix starts at 0 while pictures start at so decrementing 1 at the beginning to adjust
            //System.out.println("Real Pic number is " + picNo + ".jpg but its place in the intensity array is " + pic);
            //System.out.println("Image size is " + imageSize[pic]);
            for (int row = 0; row < 100; row++) {
                //System.out.println("Row "+ row + " Image size is "+imageSize[row]);
                for (int column = 0; column < 25; column++) {
                    //double dist= (intensityMatrix[row][column]/imageSize[row]) - (intensityMatrix[pic][column]/imageSize[pic]);
                    double dist = (intensityMatrix[pic][column] / imageSize[pic]) - (intensityMatrix[row][column] / imageSize[row]); // calculating distance as in the assignmnet document

                    if (dist < 0) {
                        dist = dist * -1; // getting the modulus of the distance if -ve value then get the absolute +ve
                    }
                    //distance[row] += dist;
                    distance[row] = distance[row] + dist; // adding the distances together
                }
                //System.out.println("distance of row " + row + " is " + distance[row]);
            }

            //Print the Distance Array
            System.out.println("Intensity method Distance Matrix of picture " + picNo);
            for (int o = 0; o < 100; o++) {
                System.out.print((distance[o]) + " ,");
            }

            System.out.println("\n");
            buttonOrder[1] = picNo; // set the selected picture to be the first in the button order, 
            //display first starts at buttonOder of 1 so the selected image will always be displayed first
            for (int u = 2; u < 101; u++) { // for loop to go over all the remaining images
                double smallest = 50000000; // setting the smallest distance variable to a random big number
                int smallestIndex = -1; // setting the index of the smallest to -1
                for (int k = 0; k < 100; k++) { // for loop to go over the ditance array and find the smallest distance to another image and then get the index of that next similar image
                    if (distance[k] < smallest && distance[k] != 0) { // f distance is smaller and if it is not 0 (same picture or a picture that has been already listed as similar)
                        smallest = distance[k]; // update smallest value
                        smallestIndex = k; // update the index
                    }
                }
                distance[smallestIndex] = 0; // once found the smallest distance, set it now to 0 so we do not go over it once again
                ++smallestIndex; // increment the smallest index by 1 to match the button Order
                //System.out.println("Smallest Index is " + smallestIndex);
                buttonOrder[u] = smallestIndex; // update button order
            }
            for (int u = 1; u < 101; u++) {
                System.out.print(buttonOrder[u] + ", "); // printing the button 
                //Order which will be the sequnce of images from the highest similar to the least similar according to intensity method
            }
            imageCount = 1; // before re-displaying we need to make sure to revert the imageCount to 1 to start displaying images from the start
            if (!checkbox.isSelected()) {
                displayFirstPage(); // call the display first images to show the first 20 images, clicking next will display the next 20 and so on
            } else {
                displayFirstPageCB();
            }
        }
    }

    /*This class implements an ActionListener when the user selects the colorCode button.  The image number that the
     * user would like to find similar images for is stored in the variable pic.  pic takes the image number associated with
     * the image selected and subtracts one to account for the fact that the intensityMatrix starts with zero and not one. 
     * The size of the image is retrieved from the imageSize array.  The selected image's intensity bin values are 
     * compared to all the other image's intensity bin values and a score is determined for how well the images compare.
     * The images are then arranged from most similar to the least.
     */
    private class colorCodeHandler implements ActionListener { // exactly the same as the previous intensityHandler except that the number of columns is 64 instead of 25

        public void actionPerformed(ActionEvent e) {
            if (picNo == 0) {
                JFrame f;
                //OptionPaneExample(){  
                f = new JFrame();
                JOptionPane.showMessageDialog(f, "Select an image first");
                return;
                //}  
            }
            iteration = -1;
            for (int u = 0; u < selectedImages.length; u++) {
                selectedImages[u] = 0;
            }
            selectedImagesIndex = 0;
            if (checkbox.isSelected()) {
                checkbox.setSelected(false);
                checkbox.setEnabled(false);
            }
            double[] distance = new double[101];
            //map = new HashMap<Double, LinkedList<Integer>>();
            int pic = (picNo - 1);
            System.out.println("Image size is " + imageSize[pic]);
            for (int row = 0; row < 100; row++) {
                //System.out.println("Row "+ row + " Image size is "+imageSize[row]);
                for (int column = 0; column < 64; column++) {
                    //double dist= (intensityMatrix[row][column]/imageSize[row]) - (intensityMatrix[pic][column]/imageSize[pic]);
                    double dist = (colorCodeMatrix[pic][column] / imageSize[pic]) - (colorCodeMatrix[row][column] / imageSize[row]);

                    if (dist < 0) {
                        dist = dist * -1;
                    }

                    distance[row] = distance[row] + dist;
                }
                //System.out.println("distance of row " + row + " is " + distance[row]);
            }

            System.out.println("Color Code method Distance Matrix of picture " + picNo);
            for (int o = 0; o < 100; o++) {
                System.out.print((distance[o]) + " ,");
            }

            System.out.println("\n");
            buttonOrder[1] = picNo;
            for (int u = 2; u < 101; u++) {
                double smallest = 50000000;
                int smallestIndex = -1;
                for (int k = 0; k < 100; k++) {
                    if (distance[k] < smallest && distance[k] != 0) {
                        smallest = distance[k];
                        smallestIndex = k;
                    }
                }
                distance[smallestIndex] = 0;
                ++smallestIndex;
                //System.out.println("Smallest Index is " + smallestIndex);
                buttonOrder[u] = smallestIndex;
            }
            for (int u = 1; u < 101; u++) {
                System.out.print(buttonOrder[u] + ", ");
            }
            imageCount = 1;
            if (!checkbox.isSelected()) {
                displayFirstPage(); // call the display first images to show the first 20 images, clicking next will display the next 20 and so on
            } else {
                displayFirstPageCB();
            }
        }
    }
}
