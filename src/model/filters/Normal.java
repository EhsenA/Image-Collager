package model.filters;

import model.IPixel;

/**
 * This class allows for a normal filter option.
 * Normal does nothing to the image.
 */
public class Normal implements FilterOptions {

  // Using default Constructor

  @Override
  public IPixel[][] apply(IPixel[][] image, IPixel[][] composed) {
    return image;
  }

  public String toString() {
    return "normal";
  }
}
