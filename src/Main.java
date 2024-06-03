import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.Controller;
import controller.GUIController;
import controller.IController;
import model.Model;
import model.IModel;
import view.GUIView;
import view.IGUIView;
import view.View;
import view.IView;

/**
 * A class defining the main method of this application.
 */
public class Main {

  /**
   * Main method of the application.
   * @param args represents the arguments passed into main.
   */
  public static void main(String[] args) {
    IModel model = new Model();

    // GUI
    if (args.length == 0) {
      IGUIView guiView = new GUIView();
      IController controller = new GUIController(model, guiView);
    }
    // Command Line Text
    else if (args[0].equals("-text")) {
      IView view = new View();
      Readable rd = new InputStreamReader(System.in);
      IController controller = new Controller(model, view, rd);
      controller.runProgram();
    }

    // Script file
    else if (args[0].equals("-file")) {
      IView view = new View();
      Readable rd = null;
      try {
        rd = new FileReader(args[1]);
      } catch (IOException ignored) {
      }
      IController controller = new Controller(model, view, rd);
      controller.runProgram();
    }
  }
}
