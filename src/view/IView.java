package view;

import java.io.IOException;

/**
 * The View to be made for a Collage.
 * This allows for the view to be made
 * with a project.
 * The IView interface allows to be viewed, including
 * <li> viewing the menu options </li>
 * <li> viewing the filteroptions </li>
 * <li> viewing an inputted message </li>
 */
public interface IView {

  /**
   * Renders a given message to the data output in the implementation.
   * @param message the message to be printed.
   */
  void renderMessage(String message) throws IOException;

  /**
   * Prints the menu options for the user to see and use.
   * It contains all options regarding creating and altering projects, layers, and images.
   * It also contains the filterOptions which are found by {@code showFilterOptions}.
   */
  void showOptions() throws IOException;

  /**
   * Prints the menu options for the filterOptions for the user to see and use.
   * It contains all possible filter-options string name
   * which will be the inputted filter by the user.
   */
  void showFilterOptions() throws IOException;

}
