package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import model.ILayer;
import model.IPixel;
import model.IProject;

/**
 * The ImageUtils class with all methods dealing with reading and writing to any file.
 * This class contains utility methods to convert an image to a ppm and
 * return the image as a BufferedImage, save the given composed image,
 * open a project, and save a project and all of its contents.
 */
public class ImageUtil {

  /**
   * Reads the file to produce a composed BufferedImage.
   * @param fileName the filename to read from.
   * @return the BufferedImage produced from the file.
   * @throws FileNotFoundException if the file is not found.
   */
  public static BufferedImage readImg(String fileName) throws FileNotFoundException {
    BufferedImage buffered;

    if (fileName.split("\\.", 0)[1].equals("ppm")) {
      return ImageUtil.aPPMtoImage(fileName);
    }
    else { // if file not ppm, ImageIO.read them
      try {
        buffered = ImageIO.read(new File(fileName));
      } catch (IOException e) {
        throw new FileNotFoundException("Error occurred during reading\n");
      }

      return buffered;
    }
  }

  /**
   * Converts a ppm file to an image of type BufferedImage.
   *
   * @param filename the filename of the ppm image.
   * @return an image of type BufferedImage.
   * @throws FileNotFoundException if the file to the image was not found.
   */
  public static BufferedImage aPPMtoImage(String filename) throws FileNotFoundException {
    Scanner sc;
    BufferedImage buffered;

    sc = new Scanner(new FileInputStream(filename));

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    buffered = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    IPixel[][] image = new IPixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        IPixel temp;
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        int argb = 0;//a << 24;
        argb = r << 16;
        argb |= g << 8;
        argb |= b;
        buffered.setRGB(j, i, argb);
      }
    }
    return buffered;
  }

  /**
   * Saves the composed image to a file.
   *
   * @param img      the composed image.
   * @param fileName the filename to save the image to.
   * @throws IOException if the file cannot be created.
   */
  public static void saveImg(IPixel[][] img, String fileName) throws IOException {
    int maxValue = img[0][0].getMaxValue();
    String extension = fileName.split("\\.", 0)[1];
    if (fileName.split("\\.", 0)[1].equals("ppm")) {
      FileWriter writer = new FileWriter(fileName);
      writer.write("P3\n");
      writer.write(img[0].length + " " + img.length + "\n");
      writer.write(maxValue + "\n");
      for (int i = 0; i < img.length; i++) {
        for (int j = 0; j < img[0].length; j++) {
          writer.write((int) (img[i][j].getR()
                  * ((double) (img[i][j].getA()) / maxValue)) + " ");
          writer.write((int) (img[i][j].getG()
                  * ((double) (img[i][j].getA()) / maxValue)) + " ");
          writer.write((int) (img[i][j].getB()
                  * ((double) (img[i][j].getA()) / maxValue)) + " ");
        }
      }
      writer.close();
    }
    else { // if file not ppm, convert pixels to bufferedImage and ImageIO.write them
      BufferedImage buffered = ImageUtil.createBuffImage(img);
      ImageIO.write(buffered, extension, new File(fileName));
    }
  }

  /**
   * Opens a project with all of its data and contents given the filename.
   *
   * @param filename the filename at which the project is located.
   * @return a StringBuilder with all of its given contents.
   * @throws FileNotFoundException if the given file at the filename is not found.
   */
  public static StringBuilder openProject(String filename) throws FileNotFoundException {
    Scanner sc;

    sc = new Scanner(new FileInputStream(filename));

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    return builder;
  }

  /**
   * Saves the given project at the given filename.
   *
   * @param fileName the filename at which the project should be saved.
   * @param proj the project to be saved.
   * @throws IOException if the file cannot be saved.
   */
  public static void saveProj(String fileName, IProject proj) throws IOException {
    int maxValue = 0;
    if (proj.getLayers().size() > 0) {
      maxValue = proj.getLayers().get(0).getImage()[0][0].getMaxValue();
    }
    int height = proj.getHeight();
    int width = proj.getWidth();
    FileWriter writer =
            new FileWriter(fileName);
    writer.write(proj.getName() + "\n");
    writer.write(width + " " + height + "\n");
    writer.write(maxValue + "\n");
    for (ILayer l : proj.getLayers()) {
      writer.write(l.getName() + " ");
      writer.write(l.getFilter().toString() + "\n");
      IPixel[][] image = l.getImage();
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          writer.write(image[i][j].getR() + " ");
          writer.write(image[i][j].getG() + " ");
          writer.write(image[i][j].getB() + " ");
          writer.write(image[i][j].getA() + " ");
        }
      }
      writer.write("\n");
    }
    writer.close();
  }

  /**
   * Transforms the given image into a BufferedImage.
   * This will be used to convert the composed image in the project into
   * a BufferedImage to be rendered on the GUI.
   * @param image the image to be created into a BufferedImage.
   * @return a BufferedImage with the rgb values of the given image.
   */
  public static BufferedImage createBuffImage(IPixel[][] image) {
    BufferedImage buffered =
            new BufferedImage(image[0].length, image.length, BufferedImage.TYPE_INT_RGB);

    //Iterating so x moves to the right and y moves down
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        int r = image[i][j].getR();
        int g = image[i][j].getG();
        int b = image[i][j].getB();
        int a = image[i][j].getA();

        // Taking the 4 values and creating a single number
        int argb = 0;//a << 24;
        argb = r << 16;
        argb |= g << 8;
        argb |= b;
        buffered.setRGB(j, i, argb);
      }
    }
    return buffered;
  }
}

