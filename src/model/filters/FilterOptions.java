package model.filters;

import model.IPixel;

/**
 * This interface is for all the filter options that can be applied to a layer.
 */
public interface FilterOptions {

  /**
   * Creates a new image with the filter applied to the given image.
   * @param image current image of type Pixel[][]
   * @return a new image of type Pixel[][]
   */
  IPixel[][] apply(IPixel[][] image, IPixel[][] composed);

  /**
   * Overrides toString to return the name of the filter option according to the menu in the view.
   * @return a String representing the name of the filter option
   */
  String toString();
}
