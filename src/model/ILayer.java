package model;

import model.filters.FilterOptions;

/**
 * The Layer to be added to a project.
 * This allows for the layers to be made
 * with a name, the image on the layer, a list of filters applied to the layer,
 * and the height and width of the project.
 * The ILayer interface allows to
 * <li> add an image to the layer </li>
 * <li> add and apply a filter to the layer </li>
 * <li> get the name, image, and list of filters for a layer </li>
 */
public interface ILayer {

  /**
   * Adds a given image to a layer based on an offset value given as x-coordinate and y-coordinate.
   * @param x the x-coordinate of the offset.
   * @param y the y-coordinate of the offset.
   * @param img the given image to be added to the layer.
   */
  void addImage(int x, int y, IPixel[][] img);

  /**
   * Adds a filter to the list of filters.
   * Helps to keep track of what filters are used on a layer.
   * @param options the filter used on the layer.
   */
  void addFilter(FilterOptions options);

  /**
   * Applies filter added to a layer to the layer.
   * @param composed the composed image.
   * @return an image of type Pixel[][]
   *     which is the image added to the layer with the filters of the layer added
   */
  IPixel[][] applyFilter(IPixel[][] composed);

  /**
   * Gets a copy of the image of a layer without the filters added.
   * @return an image of type Pixel[][]
   *     which is a copy of the image added to a layer without the filters added.
   */
  IPixel[][] getImage();

  /**
   * Gets the name of the layer.
   * @return a String representing the name of the layer.
   */
  String getName();

  /**
   * Gets a copy of the filter added to the layer.
   * @return a {@code List<FilterOptions>} representing the list of filters.
   */
  FilterOptions getFilter();
}
