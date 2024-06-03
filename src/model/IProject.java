package model;

import java.util.List;

import model.filters.FilterOptions;

/**
 * The Project to be made.
 * This allows for the project to be made
 * with a name, a list of layers,
 * and the height and width of the project.
 * The IProject interface allows to
 * <li> add a layer to a project </li>
 * <li> add an image to a layer in a project </li>
 * <li> set a filter to the layer </li>
 * <li> save a copy of the composed image </li>
 * <li> get the list of layers for a project, name, height, and width </li>
 */
public interface IProject {

  /**
   * Adds a layer to a project.
   * @param name the name of the layer.
   * @throws IllegalArgumentException if the name of the layer already exists in the project.
   */
  void addLayer(String name) throws IllegalArgumentException;

  /**
   * Adds an image to a layer in a project.
   * @param name the name of the layer.
   * @param image the image to be added to the layer.
   * @param x the x-component of the offset.
   * @param y the y-coordinate of the offset.
   * @throws IllegalArgumentException if the name of the layer does not exist in the project.
   */
  void addImageToLayer(String name, IPixel[][] image, int x, int y)
          throws IllegalArgumentException;

  /**
   * Sets all filters for a layer to the layer.
   * @param name the name of the layer for the filter to be added to.
   * @param options the filter to be added to the layer.
   * @throws IllegalArgumentException if the name of the layer does not exist in the project.
   */
  void setFilter(String name, FilterOptions options) throws IllegalArgumentException;

  /**
   * Saves a copy of the composed image with all filters.
   * The result is an image after applying all filters on the image.
   * @return an image of type Pixel[][]
   *     which is the composed image of the project with all filters added
   */
  IPixel[][] saveImage();

  /**
   * Gets a copy of the list of layers added to the project.
   * @return a {@code List<Layer>} representing the list of layers.
   */
  List<ILayer> getLayers();

  /**
   * Gets the name of the project.
   * @return a String representing the name of the project.
   */
  String getName();

  /**
   * Gets the height of the project.
   * @return an int representing the height of the project.
   */
  int getHeight();

  /**
   * Gets the width of the project.
   * @return an int representing the width of the project.
   */
  int getWidth();

  List<String> getLayerNames();

}
