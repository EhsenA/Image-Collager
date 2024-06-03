package controller;

import java.awt.event.ActionEvent;

/**
 *  The GUIController to be made for a collage.
 *  This allows for the controller to be made
 *  with a project.
 *  The IGUIController interface extends the IController interface which allows
 *  for the Collager program to be run. This interface adds the functionality of
 *  receiving and responding to action events. This allows mutation of the model.
 *  <li> It runs the program to create projects and alter them. </li>
 *
 */
public interface IGUIController extends IController {

  /**
   * Mutates the model and view accordingly when given a user input. Interfaces Controller with
   * model and view.
   * @param event a given ActionEvent which represents a user input that indicates a component
   *              defined action has occurred.
   */
  void actionPerformed(ActionEvent event);
}