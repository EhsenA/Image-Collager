package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.IModel;
import model.filters.FilterOptions;
import utils.ImageUtil;
import view.IGUIView;

/**
 * This class represents the operations offered by the controller for
 * the GUI version of our image processor. In this version, the controller looks for
 * ActionEvents and delegates accordingly to the new gui view interface
 * instead of scanning through user inputted text.
 */
public class GUIController implements IGUIController, ActionListener {
  private IModel model;
  private IGUIView view;

  /**
   * The constructor for our graphical image processor controller. Takes in a model, and a
   * GUI view this time.
   * @param model model for processor
   * @param view GUI (graphical) image view
   */
  public GUIController(IModel model, IGUIView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("The model or view is null");
    }
    this.model = model;
    this.view = view;
    this.runProgram();
  }

  @Override
  public void runProgram() throws IllegalArgumentException {
    this.view.setListener(this);
    this.view.makeVisible();
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    String filename;
    String layerName;
    String imagePath;
    String projectName;
    int intXVal;
    int intYVal;
    try {
      switch (event.getActionCommand()) {
        case "Quit Button":
          System.exit(0);
          break;
        case "New Project Button":
          projectName = view.getString("What would you like to name your project?");
          intXVal = view.getValue("Please enter the height of the project");
          intYVal = view.getValue("Please enter the width of the project");
          try {
            this.model.newProject(projectName, intXVal, intYVal);
          } catch (IllegalArgumentException ignored) {
          }
          this.view.setImage(ImageUtil.createBuffImage(this.model.saveImage()));
          this.view.addLayerNametoList(this.model.getCurProject().getLayerNames());
          view.refresh();
          break;
        case "Load Project Button":
          filename = view.getFilePath();
          if (filename == null) {
            this.displayMessage("Loading operation cancelled");
            break;
          }
          try {
            this.model.switchProject(model.openProject(ImageUtil.openProject(filename)));
          } catch (IOException e) {
            this.displayMessage("File " + filename + " not found!");
          }
          this.view.setImage(ImageUtil.createBuffImage(this.model.saveImage()));
          this.view.addLayerNametoList(this.model.getCurProject().getLayerNames());
          this.displayMessage("The project was successfully loaded from " + filename + "\n");
          view.refresh();
          break;
        case "Save Project Button":
          this.displayMessage("Select the directory to which you would like to save to " +
                  "and enter the name of your project.");
          filename = view.getDirectory();
          try {
            ImageUtil.saveProj(filename, this.model.getCurProject());
          } catch (IOException e) {
            this.displayMessage("File " + filename + " not found!");
          }
          this.displayMessage("The project was successfully saved to " + filename + "\n");
          break;
        case "Add Layer Button":
          layerName = view.getString("Please enter the name of the layer");
          this.model.addLayer(layerName);
          this.view.addLayerNametoList(this.model.getCurProject().getLayerNames());
          view.refresh();
          break;
        case "Add Image to Layer Button":
          this.displayMessage("Select the directory to which you would " +
                  "like to obtain the image from.");
          imagePath = view.getFilePath();
          intXVal = view.getValue("Please enter the offset x-value for the images");
          intYVal = view.getValue("Please enter the offset y-value for the images");
          try {
            this.model.addImageToLayer(this.view.getLayerSelected(),
                    this.model.createImageFromBuff(ImageUtil.readImg(imagePath)),
                    intXVal, intYVal);
          } catch (FileNotFoundException | IllegalArgumentException e) {
            this.displayMessage("Either the provided layer name already exists " +
                    "or the image file is valid");
          }
          this.view.setImage(ImageUtil.createBuffImage(this.model.saveImage()));
          view.refresh();
          break;
        case "Save Image Button":
          this.displayMessage("Select the directory to which you would like to save to " +
                  "and enter the name of your file.");
          filename = view.getDirectory();
          try {
            ImageUtil.saveImg(this.model.saveImage(), filename);
          } catch (IOException | IllegalArgumentException e) {
            this.displayMessage("Either the provided layer name already exists " +
                    "or the inputted file path is valid");
          }
          this.displayMessage("The image was successfully saved to " + filename + "\n");
          break;
        case "Normal Button":
          this.applyFilter(this.model.getFilterOptionsObject("normal"),
                  " successfully created normal filter ");
          break;
        case "Red Component Button":
          this.applyFilter(this.model.getFilterOptionsObject("red-component"),
                  " successfully created red component filter ");
          break;
        case "Green Component Button":
          this.applyFilter(this.model.getFilterOptionsObject("green-component"),
                  " successfully created green component filter ");
          break;
        case "Blue Component Button":
          this.applyFilter(this.model.getFilterOptionsObject("blue-component"),
                  " successfully created blue component filter ");
          break;
        case "Brighten Value Button":
          this.applyFilter(this.model.getFilterOptionsObject("brighten-value"),
                  " successfully created brightened value filter ");
          break;
        case "Brighten Intensity Button":
          this.applyFilter(this.model.getFilterOptionsObject("brighten-intensity"),
                  " successfully created brightened intensity filter ");
          break;
        case "Brighten Luma Button":
          this.applyFilter(this.model.getFilterOptionsObject("brighten-luma"),
                  " successfully created brightened luma filter ");
          break;
        case "Darken Value Button":
          this.applyFilter(this.model.getFilterOptionsObject("darken-value"),
                  " successfully created darkened value filter ");
          break;
        case "Darken Intensity Button":
          this.applyFilter(this.model.getFilterOptionsObject("darken-intensity"),
                  " successfully created darkened intensity filter ");
          break;
        case "Darken Luma Button":
          this.applyFilter(this.model.getFilterOptionsObject("darken-luma"),
                  " successfully created darkened luma filter ");
          break;
        case "Difference Button":
          this.applyFilter(this.model.getFilterOptionsObject("difference"),
                  " successfully created difference filter ");
          break;
        case "Brighten Blend Button":
          this.applyFilter(this.model.getFilterOptionsObject("brighten-blend"),
                  " successfully created brighten blend filter ");
          break;
        case "Darken Blend Button":
          this.applyFilter(this.model.getFilterOptionsObject("darken-blend"),
                  " successfully created darken blend filter ");
          break;
        default:
          this.displayMessage("No button pushed\n");
          break;
      }
    }
    catch (NullPointerException n) {
      this.displayMessage("Incorrect button clicked");
    }
    catch (IllegalArgumentException f) {
      String error = f.getMessage();
      this.displayMessage(error);
    }
  }

  /**
   * Helper that retrieves the name of the image to be returned, then applies
   * the given command to the most recently edited image and returns as a new image with the second
   * given name. Also returns to the GUI view the given message to indicate the operation was
   * successful as a dialog p.
   */
  private void applyFilter(FilterOptions filter, String message) {
    String layerName = this.view.getLayerSelected();
    this.model.setFilter(layerName, filter);
    this.view.setImage(ImageUtil.createBuffImage(this.model.saveImage()));
    this.displayMessage(message);
    view.refresh();
  }

  /**
   * Creates a display message on the GUI using the view.
   * @param message the message to be displayed
   */
  private void displayMessage(String message) {
    try {
      view.renderMessage(message);
    } catch (IOException ignored) {
    }
  }

}
