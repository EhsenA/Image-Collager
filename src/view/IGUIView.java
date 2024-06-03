package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;


/**
 * This interface represents a graphical view implementation for the image processor as opposed to
 * a simple text implementation from before.
 */
public interface IGUIView extends IView {

  /**
   * Make the view visible. This is usually called
   * after the view is constructed
   */
  void makeVisible();

  /**
   * Allows the view to take in a listener object and forward any events it receives to the given
   * listener.
   * @param listener the given listener.
   */
  void setListener(ActionListener listener);

  /**
   * Adds a list of strings, the names of the layers to a given JList so it can be shown on the GUI.
   * @param layers the names of the layers as strings.
   */
  void addLayerNametoList(List<String> layers);

  /**
   * Gets the selected layer name from the list of layers on the GUI.
   * @return a String which is the name of the layer
   */
  String getLayerSelected();

  /**
   * Signal the view to redraw and refresh itself.
   */
  void refresh();

  /**
   * Creates a prompt for the user to specify a specified String amount for a certain prompt, such
   * as the name of a new Image.
   * @param message The message asking the user what they are entering the prompt for.
   * @return the String that the user specifies
   */
  String getString(String message);

  /**
   * Gets the name of the file path.
   * @return file path as a string
   */
  String getFilePath();

  /**
   * Gets the path to the selected directory.
   * @return file path as a string
   */
  String getDirectory();

  /**
   * Creates a prompt for the user to specify the amount for a certain prompt, such as the amount
   * by which they would like to brighten the image.
   * @param message The message asking the user what they are entering the prompt for.
   * @return the value by which user would like to change a value
   */
  Integer getValue(String message);

  /**
   * Takes in the given image and displays it on the image panel.
   * @param buff the given buffered image.
   */
  void setImage(BufferedImage buff);

}