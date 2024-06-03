package model.filters;

import model.IPixel;
import model.Pixel;

/**
 * This class allows for a blue-component filter option.
 * BlueComponent only uses the blue portion of the RGB
 * amd sets the red portion and green portion to 0.
 * It leaves the alpha value the same.
 */
public class BlueComponent implements FilterOptions {

  // Using default Constructor

  @Override
  public IPixel[][] apply(IPixel[][] image, IPixel[][] composed) {
    IPixel[][] img = new IPixel[image.length][image[0].length];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        img[i][j] = new Pixel(0, 0, image[i][j].getB(), image[i][j].getA());
      }
    }
    return img;
  }

  public String toString() {
    return "blue-component";
  }
}