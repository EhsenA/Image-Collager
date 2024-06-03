package model.filters;

import model.IPixel;
import model.Pixel;

/**
 * This class allows for a difference filter option.
 * Difference inverts the colors of an image on the current layer.
 * This blending filter takes two pixels RGB components and subtracts them component-wise.
 * This filter option uses both an image on the layer
 * and the composed image of the layers beneath the layer chosen.
 * It then finds the absolute value of the subtraction of the red, green, and blue components
 * to create a new Pixel with those given rgb values, while the alpha value stays untouched.
 */
public class Difference implements FilterOptions {
  @Override
  public IPixel[][] apply(IPixel[][] image, IPixel[][] composed) {
    IPixel[][] img = new IPixel[image.length][image[0].length];
    int red;
    int green;
    int blue;
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        red = Math.abs(image[i][j].getR() - composed[i][j].getR());
        green = Math.abs(image[i][j].getG() - composed[i][j].getG());
        blue = Math.abs(image[i][j].getB() - composed[i][j].getB());
        img[i][j] = new Pixel(red, green, blue, image[i][j].getA());
      }
    }
    return img;
  }

  public String toString() {
    return "difference";
  }
}
