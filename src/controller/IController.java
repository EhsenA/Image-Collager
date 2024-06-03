package controller;

/**
 * The Controller to be made for a collage.
 * This allows for the controller to be made
 * with a project.
 * The IController interface allows for the mutation of elements defined in the model.
 * It also creates projects and alters them using methods declared in the model
 * and using the view to display the project and its layers and images.
 * <li> It runs the program to create projects and alter them. </li>
 */
public interface IController {

  /**
   * Creates projects and alters them using methods declared in the model
   * and using the view to display the project and its layers and images.
   * Allows for the mutation of elements defined in the model.
   */
  void runProgram();
}
