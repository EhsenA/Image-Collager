package model;

import java.awt.image.BufferedImage;

import model.filters.FilterOptions;

/**
 * The Model to be made.
 * This allows for the model to be made
 * with a project.
 * The IModel interface allows to
 * <li> make a new project </li>
 * <li> load a project </li>
 * <li> save a project </li>
 * <li> set a filter to a given layer in a project </li>
 * <li> save the composed image of the current project </li>
 * <li> add a layer to a project </li>
 * <li> add an image to a layer in a project </li>
 * <li> save a copy of the composed image to the given filepath </li>
 * <li> get the current project </li>
 */
public interface IModel extends IModelState {

  /**
   * Creates a new project with the given name and given dimensions.
   * Every project has a white background layer by default.
   * @param name the name of the project.
   * @param height the height of the project.
   * @param width the width of the project.
   * @throws IllegalArgumentException if the given height or width is not positive.
   */
  void newProject(String name, int height, int width) throws IllegalArgumentException;

  /**
   * Switches the current project to the given inputted project.
   */
  void switchProject(IProject proj);

  /**
   * Sets the filter of the given layer where filter-option is one of {@code FilterOptions}..
   * @param layerName the name of the layer in a project.
   * @param opt the filter option to be added to a layer in a project.
   * @throws IllegalArgumentException if the name of the layer does not exist in the project.
   */
  void setFilter(String layerName, FilterOptions opt) throws IllegalArgumentException;

  /**
   * Saves a copy of the composed image with all filters.
   * The result is an image after applying all filters on the image.
   * @return an image of type Pixel[][]
   *     which is the composed image of the project with all filters added
   */
  IPixel[][] saveImage();

  /**
   * Adds a new layer with the given name to the top of the whole project.
   * This layer always has a fully transparent white image and the Normal filter on by default.
   * Any attempt at creating another layer with the same name reports an error to the user,
   * but continues the program.
   * @param layerName the name of the layer to be added to the project.
   * @throws IllegalArgumentException if the name of the layer already exists in the project.
   */
  void addLayer(String layerName) throws IllegalArgumentException;

  /**
   * Adds an image to a layer in the current project.
   * @param name the name of the layer.
   * @param image the image to be added to the layer.
   * @param x the x-component of the offset.
   * @param y the y-coordinate of the offset.
   * @throws IllegalArgumentException if the name of the layer does not exist in the project.
   */
  void addImageToLayer(String name, IPixel[][] image, int x, int y)
          throws IllegalArgumentException;

  /**
   * Obtains the corresponding FilterOptions to the inputted string filter.
   * @param filter the filter option as a string.
   * @return the corresponding FilterOptions object.
   * @throws IllegalArgumentException if the filter does not exist.
   */
  FilterOptions getFilterOptionsObject(String filter) throws IllegalArgumentException;

  /**
   * Opens a project with all of its data and contents given the StringBuilder.
   * @param builder the given StringBuilder with all of the projects data and contents.
   * @return a Project with all of its given contents.
   */
  IProject openProject(StringBuilder builder);

  /**
   * Transforms the given BufferedImage into a 2D array of Pixels.
   * This will be used to convert the BufferedImage rendered on the GUI
   * to the composed image in the project into.
   * @param buffered the bufferedImage to be created into an image
   * @return a 2D array of Pixels with the rgb values of the buffered image.
   */
  IPixel[][] createImageFromBuff(BufferedImage buffered);
}
