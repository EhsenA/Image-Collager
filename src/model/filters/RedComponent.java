package model.filters;

import model.IPixel;
import model.Pixel;

/**
 * This class allows for a red-component filter option.
 * RedComponent only uses the red portion of the RGB
 * amd sets the green portion and blue portion to 0.
 * It leaves the alpha value the same.
 */
public class RedComponent implements FilterOptions {

  // Using default Constructor

  @Override
  public IPixel[][] apply(IPixel[][] image, IPixel[][] composed) {
    IPixel[][] img = new IPixel[image.length][image[0].length];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        img[i][j] = new Pixel(image[i][j].getR(), 0, 0, image[i][j].getA());
      }
    }
    return img;
  }

  public String toString() {
    return "red-component";
  }
}