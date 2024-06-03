package model.filters;

import model.Pixel;
import model.IPixel;

/**
 * This class allows for a green-component filter option.
 * GreenComponent only uses the green portion of the RGB
 * amd sets the red portion and blue portion to 0.
 * It leaves the alpha value the same.
 */
public class GreenComponent implements FilterOptions {

  // Using default Constructor

  @Override
  public IPixel[][] apply(IPixel[][] image, IPixel[][] composed) {
    IPixel[][] img = new IPixel[image.length][image[0].length];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        img[i][j] = new Pixel(0, image[i][j].getG(), 0, image[i][j].getA());
      }
    }
    return img;
  }

  public String toString() {
    return "green-component";
  }
}