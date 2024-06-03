package model.filters;

import model.IPixel;

/**
 * This class allows for a darken-intensity filter option.
 * DarkenIntensity removes the brightness intensity value pixel by pixel
 * according to value from the corresponding pixel on the current layer.
 * The intensity value to be subtracted is found by
 * the average of the three components for each pixel.
 */
public class DarkenIntensity implements FilterOptions {

  // Using default Constructor

  @Override
  public IPixel[][] apply(IPixel[][] image, IPixel[][] composed) {
    IPixel[][] img = new IPixel[image.length][image[0].length];
    int value;
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        value = image[i][j].intensityValue();
        img[i][j] = image[i][j].darken(value);
      }
    }
    return img;
  }


  public String toString() {
    return "darken-intensity";
  }
}