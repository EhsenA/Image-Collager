package model;

/**
 * Interface for the state of the model.
 * This interface allows observation of the model's current project,
 * but not the mutation of these elements.
 * <li> save the composed image of the current project </li>
 * <li> get the current project </li>
 */
public interface IModelState {

  /**
   * Saves a copy of the composed image with all filters.
   * The result is an image after applying all filters on the image.
   * @return an image of type Pixel[][]
   *     which is the composed image of the project with all filters added
   */
  IPixel[][] saveImage();

  /**
   * Gets a copy of the Project.
   * Gets the name, height, width, layers, and filteroptions for each layer
   * and adds to a new Project to create the copy.
   * @return a Project representing current project.
   */
  IProject getCurProject();
}
