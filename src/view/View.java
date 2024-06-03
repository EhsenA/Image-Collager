package view;

import java.io.IOException;

/**
 * The view for a Collage. This class implements the interface IView.
 * This allows for the collage to be viewed, including
 * <li> viewing the menu options </li>
 * <li> viewing the filteroptions </li>
 * <li> viewing an inputted message </li>
 */
public class View implements IView {
  private Appendable out;

  /**
   * Constructor for a View.
   * Creates a {@code View} using values initialized in the constructor.
   * @throws IllegalArgumentException when model is null.
   */
  public View() throws IllegalArgumentException {
    this.out = new StringBuilder();
  }

  /**
   * Constructor for a View.
   * Creates a {@code View} using values initialized in the constructor.
   * @param out the input from the user in the terminal
   * @throws IllegalArgumentException when model or out is null.
   */
  public View(Appendable out) throws IllegalArgumentException {
    if (out == null) {
      throw new IllegalArgumentException("The provided appendable was null");
    }
    this.out = out;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.out.append(message);
  }

  @Override
  public void showOptions() throws IOException {
    out.append("Menu: \n");
    out.append("new-project project-name canvas-height canvas-width \n");
    out.append("load-project path-to-project-file \n");
    out.append("save-project path-to-project-file \n");
    out.append("add-layer layer-name \n");
    out.append("add-image-to-layer layer-name image-name x-pos y-pos \n");
    out.append("set-filter layer-name filter-option \n");
    out.append("save-image file-name \n");
    out.append("quit \n");
    showFilterOptions();
  }

  @Override
  public void showFilterOptions() throws IOException {
    out.append("Here are the currently supported filter options: \n");
    out.append("red-component: when applied, only uses the red portion of the RGB. " +
            "Similar for green-component and blue-component\n");
    out.append("brighten-value when applied, adds the brightness value pixel by pixel " +
            "according to value from the corresponding pixel on the current layer. Similar for " +
            "brighten-intensity and brighten-luma. Only affects the current layer.\n");
    out.append("darken-value: when applied, " +
            "removes the brightness value pixel by pixel according " +
            "to value from the corresponding pixel on the current layer. Similar for " +
            "darken-intensity and darken-luma. Only affects the current layer. \n");
  }
}
