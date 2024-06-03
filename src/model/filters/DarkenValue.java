package model.filters;

import model.IPixel;

/**
 * This class allows for a darken-value filter option.
 * DarkenValue removes the brightness value pixel by pixel
 * according to value from the corresponding pixel on the current layer.
 * The darken value to be subtracted is found by
 * the maximum value of the three components for each pixel.
 */
public class DarkenValue implements FilterOptions {

  // Using default Constructor

  @Override
  public IPixel[][] apply(IPixel[][] image, IPixel[][] composed) {
    IPixel[][] img = new IPixel[image.length][image[0].length];
    int value;
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        value = image[i][j].maxRGB();
        img[i][j] = image[i][j].darken(value);
      }
    }
    return img;
  }

  public String toString() {
    return "darken-value";
  }
}