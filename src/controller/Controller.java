package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import model.filters.FilterOptions;
import model.IModel;
import utils.ImageUtil;
import view.IView;

/**
 * The control for a collage.
 * This class implements the interface IController.
 * This allows for the mutation of elements defined in the model.
 * It also creates projects and alters them using methods declared in the model
 * and using the view to display the project and its layers and images.
 */
public class Controller implements IController {
  private final IModel model;
  private final IView view;
  private final Scanner in;

  /**
   * Constructor for a Controller.
   * Creates a {@code Controller} using values initialized in the constructor.
   * @param model the model which is of type Model and represents a project
   * @param view the view which is of type View and shows the menu and messages
   * @param in the input from the user in the terminal
   * @throws IllegalArgumentException when model, view, or in are null
   */
  public Controller(IModel model, IView view, Readable in) throws IllegalArgumentException {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("The model, view, or readable input is null");
    }
    this.model = model;
    this.view = view;
    this.in = new Scanner(in);
  }

  @Override
  public void runProgram() {
    boolean quit = false;

    while (in.hasNext()) {
      String filename;
      String option = in.next();
      switch (option) {
        case "quit":
          System.exit(0);
          break;
        case "new-project":
          try {
            this.model.newProject(in.next(), checkUserInputForHeightWidth(),
                    checkUserInputForHeightWidth());
          } catch (IllegalArgumentException ignored) {
          }
          break;
        case "load-project":
          filename = in.next();
          try {
            this.model.switchProject(model.openProject(ImageUtil.openProject(filename)));
          } catch (IOException e) {
            try {
              this.view.renderMessage("File " + filename + " not found!");
            } catch (IOException ignored) {
            }
          }
          break;
        case "save-project":
          filename = in.next();
          try {
            ImageUtil.saveProj(filename, this.model.getCurProject());
          } catch (IOException e) {
            try {
              this.view.renderMessage("File " + filename + " not found!");
            } catch (IOException ignored) {
            }
          }
          break;
        case "add-layer":
          this.model.addLayer(in.next());
          break;
        case "add-image-to-layer":
          try {
            this.model.addImageToLayer(in.next(),
                    this.model.createImageFromBuff(ImageUtil.readImg(in.next())),
                    checkUserInputForInt(), checkUserInputForInt());
          } catch (FileNotFoundException | IllegalArgumentException e) {
            try {
              this.view.renderMessage("Either the provided layer name already exists " +
                      "or the image file is valid");
            } catch (IOException ignored) {
            }
          }
          break;
        case "set-filter":
          String layerName = in.next();
          FilterOptions opt = getFilter();

          this.model.setFilter(layerName, opt);
          break;
        case "save-image":
          filename = in.next();
          try {
            ImageUtil.saveImg(this.model.saveImage(), filename);
          } catch (IOException | IllegalArgumentException e) {
            try {
              this.view.renderMessage("Either the provided layer name already exists " +
                      "or the inputted file path is valid");
            } catch (IOException ignored) {
            }
          }
          break;
        default:
          try {
            this.view.renderMessage("Please enter a valid command!");
          } catch (IOException ignored) {
          }
      }
    }
  }

  /**
   * Makes a new FilterOptions from a specific class given the name of the filter.
   * Gets the filter option that is applied to a layer.
   * @return a FilterOptions that represents the filterOption
   *     associated with the String name of the filter
   */
  private FilterOptions getFilter() {
    FilterOptions opt = this.model.getFilterOptionsObject("normal");
    boolean done = true;
    do {
      try {
        opt = this.model.getFilterOptionsObject(in.next());
      } catch (IllegalArgumentException e) {
        try {
          this.view.renderMessage("Please enter a valid FilterOption.");
        } catch (IOException ignored) {
        }
        done = false;
      }
    }
    while (!done && in.hasNext());

    return opt;
  }

  /**
   * Checks the user input to see if it is a positive integer.
   * If it is a positive int, return that value otherwise keep asking for user input.
   * @return a positive int that represents the next integer inputted.
   */
  private int checkUserInputForHeightWidth() {
    while (!in.hasNextInt()) {
      in.next();
    }
    int next = in.nextInt();
    if (next <= 0) {
      checkUserInputForHeightWidth();
    }
    return next;
  }

  /**
   * Checks the user input to see if it is an int.
   * If it is an int, return that value otherwise keep asking for user input.
   * @return an int that represents the next integer inputted.
   */
  private int checkUserInputForInt() {
    while (!in.hasNextInt()) {
      in.next();
    }
    return in.nextInt();
  }
}
