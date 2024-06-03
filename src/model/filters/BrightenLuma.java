package model.filters;

import model.IPixel;

/**
 * This class allows for a brighten-luma filter option.
 * BrightenLuma adds the brightness luma value pixel by pixel
 * according to value from the corresponding pixel on the current layer.
 * The luma value to be added is found by
 * the weighted sum formula 0.2126r + 0.7152g + 0.0722b.
 */
public class BrightenLuma implements FilterOptions {

  // Using default Constructor

  @Override
  public IPixel[][] apply(IPixel[][] image, IPixel[][] composed) {
    IPixel[][] img = new IPixel[image.length][image[0].length];
    int value;
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        value = image[i][j].lumaValue();
        img[i][j] = image[i][j].brighten(value);
      }
    }
    return img;
  }

  public String toString() {
    return "brighten-luma";
  }
}
