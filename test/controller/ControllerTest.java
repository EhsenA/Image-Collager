package controller;

import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.StringReader;

import model.IModel;
import model.IPixel;
import model.IProject;
import model.Model;
import model.Pixel;
import model.Project;
import model.filters.BlueComponent;
import model.filters.BrightenBlend;
import model.filters.BrightenIntensity;
import model.filters.BrightenLuma;
import model.filters.BrightenValue;
import model.filters.DarkenBlend;
import model.filters.DarkenIntensity;
import model.filters.DarkenLuma;
import model.filters.DarkenValue;
import model.filters.Difference;
import model.filters.FilterOptions;
import model.filters.GreenComponent;
import model.filters.Normal;
import model.filters.RedComponent;
import view.IView;
import view.View;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A ControllerTest where we
 * test a Controller and all of its methods.
 * Controller has a model, view, and a readable, and it is the implementation
 * controller for the collage.
 * This tests for the allowance of the collage to be created and manipulated
 * by using methods declared in the model
 */
public class ControllerTest {

  /**
   * ModelConfirmInputsMock implements IModel. This creates a mock model for the IModel
   * which can then be used for testing.
   * This class implements Appendable.
   */
  class ModelConfirmInputsMock implements IModel {
    private final Appendable log;

    public ModelConfirmInputsMock(Appendable log) {
      this.log = log;
    }

    @Override
    public void newProject(String name, int height, int width) throws IllegalArgumentException {
      try {
        this.log.append("name: " + name + "\nheight: " + height + "\nwidth: " + width + "\n");
      } catch (IOException ignored) {
      }
    }

    @Override
    public void switchProject(IProject proj) {
      try {
        this.log.append("Current project parameters are\nname: " + proj.getName() + "\nheight: "
                + proj.getHeight() + "\nwidth: " + proj.getWidth() + "\n");
      } catch (IOException ignored) {
      }
    }

    @Override
    public void setFilter(String layerName, FilterOptions opt) throws IllegalArgumentException {
      try {
        this.log.append("Layer name: " + layerName + "\nFilter Option: " + opt.toString() + "\n");
      } catch (IOException ignored) {
      }
    }

    @Override
    public IPixel[][] saveImage() {
      try {
        this.log.append("Saving image\n");
      } catch (IOException ignored) {
      }
      Pixel[][] pixels = new Pixel[1][1];
      pixels[0][0] = new Pixel(255, 255, 255, 0);
      return pixels;
    }

    @Override
    public void addLayer(String layerName) throws IllegalArgumentException {
      try {
        this.log.append("Layer name: " + layerName + "\n");
      } catch (IOException ignored) {
      }
    }

    @Override
    public void addImageToLayer(String layerName, IPixel[][] image, int x, int y)
            throws IllegalArgumentException {
      try {
        this.log.append("Layer name: " + layerName + "\nImage width: " + image[0].length
                + "\nImage height: " + image.length
                + "\nImage[0][0] R: " + image[0][0].getR()
                + "\nImage[0][0] G: " + image[0][0].getG()
                + "\nImage[0][0] B: " + image[0][0].getB()
                + "\nImage[0][0] A: " + image[0][0].getA() + "\n");
      } catch (IOException ignored) {
      }
    }

    @Override
    public FilterOptions getFilterOptionsObject(String filter) throws IllegalArgumentException {
      switch (filter) {
        case "brighten-value":
          return new BrightenValue();
        case "brighten-intensity":
          return new BrightenIntensity();
        case "brighten-luma":
          return new BrightenLuma();
        case "darken-value":
          return new DarkenValue();
        case "darken-intensity":
          return new DarkenIntensity();
        case "darken-luma":
          return new DarkenLuma();
        case "red-component":
          return new RedComponent();
        case "green-component":
          return new GreenComponent();
        case "blue-component":
          return new BlueComponent();
        case "normal":
          return new Normal();
        case "difference":
          return new Difference();
        case "brighten-blend":
          return new BrightenBlend();
        case "darken-blend":
          return new DarkenBlend();
        default:
          throw new IllegalArgumentException("Invalid filter option");
      }
    }

    @Override
    public IProject openProject(StringBuilder builder) {
      return new Project("proj1", 800, 600);
    }

    @Override
    public IPixel[][] createImageFromBuff(BufferedImage buffered) {
      IPixel[][] pixels = new IPixel[1000][1000];
      pixels[0][0] = new Pixel(173,179,151,255);
      return pixels;
    }

    @Override
    public IProject getCurProject() {
      try {
        this.log.append("CurProject invoked\n");
      } catch (IOException ignored) {
      }
      return new Project("proj2", 100, 300);
    }
  }

  @Test
  public void testValidConstructor() {
    try {
      Readable in = new StringReader("3 4");
      IModel model = new Model();
      IView view = new View();
      IController controller = new Controller(model, view, in);
    } catch (IllegalArgumentException e) {
      fail("The model, view, or readable input is null. This exception should not be thrown.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    Readable in = new StringReader("3 4");
    IView view = new View();
    IController controller = new Controller(null, view, in);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor2() {
    Readable in = new StringReader("3 4");
    IModel model = new Model();
    IController controller = new Controller(model, null, in);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor3() {
    IModel model = new Model();
    IView view = new View();
    IController controller = new Controller(model, view, null);
  }


  @Test
  public void newProject() {
    Readable in = new StringReader("new-project proj1 800 600 ");
    Appendable log = new StringBuilder();
    IModel mock = new ModelConfirmInputsMock(log);
    IView view = new View(new StringBuilder());
    IController controller = new Controller(mock, view, in);
    try {
      controller.runProgram();
    } catch (IllegalStateException ignored) {
    }
    assertEquals("name: proj1\n" +
            "height: 800\n" +
            "width: 600\n", log.toString());
  }

  @Test
  public void newProjectWithMultipleCalls() {
    Readable in = new StringReader("newproject proj1 300 600 "
            + "new-project proj1 800 600 ");
    Appendable log = new StringBuilder();
    IModel mock = new ModelConfirmInputsMock(log);
    IView view = new View(new StringBuilder());
    IController controller = new Controller(mock, view, in);
    try {
      controller.runProgram();
    } catch (IllegalStateException ignored) {
    }
    assertEquals("name: proj1\n" +
            "height: 800\n" +
            "width: 600\n", log.toString());
  }

  @Test
  public void switchProject() {
    Readable in = new StringReader("new-project proj2 100 300 " +
            "load-project proj1.collage");
    Appendable log = new StringBuilder();
    IModel mock = new ModelConfirmInputsMock(log);
    IView view = new View(new StringBuilder());
    IController controller = new Controller(mock, view, in);
    try {
      controller.runProgram();
    } catch (IllegalStateException ignored) {
    }
    String[] lines = log.toString().split("\n", -1);
    assertEquals("Current project parameters are", lines[lines.length - 5]);
    assertEquals("name: proj1", lines[lines.length - 4]);
    assertEquals("height: 800", lines[lines.length - 3]);
    assertEquals("width: 600", lines[lines.length - 2]);
  }

  @Test
  public void switchProjectWithMultipleCalls() {
    Readable in = new StringReader("new-project proj2 proj2 100 " +
            "new-project proj2 proj2 300 " +
            "load-project proj1.collage");
    Appendable log = new StringBuilder();
    IModel mock = new ModelConfirmInputsMock(log);
    IView view = new View(new StringBuilder());
    IController controller = new Controller(mock, view, in);
    try {
      controller.runProgram();
    } catch (IllegalStateException ignored) {
    }
    String[] lines = log.toString().split("\n", -1);
    assertEquals("Current project parameters are", lines[lines.length - 5]);
    assertEquals("name: proj1", lines[lines.length - 4]);
    assertEquals("height: 800", lines[lines.length - 3]);
    assertEquals("width: 600", lines[lines.length - 2]);
  }

  @Test
  public void saveProject() {
    Readable in = new StringReader("new-project proj2 100 300 " +
            "save-project proj2.collage ");
    Appendable log = new StringBuilder();
    IModel mock = new ModelConfirmInputsMock(log);
    IView view = new View(new StringBuilder());
    IController controller = new Controller(mock, view, in);
    try {
      controller.runProgram();
    } catch (IllegalStateException ignored) {
    }
    String[] lines = log.toString().split("\n", -1);
    assertEquals("CurProject invoked", lines[lines.length - 2]);
  }

  //uses our actual model to save the project of tako into a new file called ourtako.collage
  @Test
  public void saveProject2() {
    Readable in = new StringReader("new-project proj1 800 600 " +
            "add-layer Layer1 " + "add-image-to-layer Layer1 tako.ppm 0 0 "
            + "save-project ourtako.collage ");
    Appendable log = new StringBuilder();
    IModel model = new Model();
    IView view = new View(new StringBuilder());
    IController controller = new Controller(model, view, in);
    try {
      controller.runProgram();
    } catch (IllegalStateException ignored) {
    }
    assertEquals("proj1", model.getCurProject().getName());
  }

  @Test
  public void setFilter() {
    Readable in = new StringReader("new-project proj2 100 300 " +
            "add-layer Layer1 " + "set-filter Layer1 red-component ");
    Appendable log = new StringBuilder();
    IModel mock = new ModelConfirmInputsMock(log);
    IView view = new View(new StringBuilder());
    IController controller = new Controller(mock, view, in);
    try {
      controller.runProgram();
    } catch (IllegalStateException ignored) {
    }
    String[] lines = log.toString().split("\n", -1);
    assertEquals("Layer name: Layer1", lines[lines.length - 3]);
    assertEquals("Filter Option: red-component", lines[lines.length - 2]);
  }

  @Test
  public void setFilterWithMultipleCalls() {
    Readable in = new StringReader("new-project proj2 100 300 " +
            "add-layer Layer1 " + "set-filter Layer1 redcomponent "
            + "set-filter Layer1 blue-component");
    Appendable log = new StringBuilder();
    IModel mock = new ModelConfirmInputsMock(log);
    IView view = new View(new StringBuilder());
    IController controller = new Controller(mock, view, in);
    try {
      controller.runProgram();
    } catch (IllegalStateException ignored) {
    }
    String[] lines = log.toString().split("\n", -1);
    assertEquals("Layer name: Layer1", lines[lines.length - 3]);
    assertEquals("Filter Option: blue-component", lines[lines.length - 2]);
  }

  @Test
  public void saveImage() {
    Readable in = new StringReader("new-project proj1 100 300 " +
            "add-layer Layer1 " + "add-image-to-layer Layer1 tako.ppm 0 0 "
            + "set-filter Layer1 red-component " + "save-image proj1.ppm ");
    Appendable log = new StringBuilder();
    IModel mock = new ModelConfirmInputsMock(log);
    IView view = new View(new StringBuilder());
    IController controller = new Controller(mock, view, in);
    try {
      controller.runProgram();
    } catch (IllegalStateException ignored) {
    }
    String[] lines = log.toString().split("\n", -1);
    assertEquals("Saving image", lines[lines.length - 2]);
  }

  //uses our actual model to save the image of tako into a new file called ourtako.ppm
  @Test
  public void saveImage2() {
    Readable in = new StringReader("new-project proj1 800 600 " +
            "add-layer Layer1 " + "add-image-to-layer Layer1 tako.ppm 0 0 "
            + "save-image ourtako.ppm ");
    Appendable log = new StringBuilder();
    IModel model = new Model();
    IView view = new View(new StringBuilder());
    IController controller = new Controller(model, view, in);
    try {
      controller.runProgram();
    } catch (IllegalStateException ignored) {
    }
    assertEquals("proj1", model.getCurProject().getName());
  }

  @Test
  public void addLayer() {
    Readable in = new StringReader("new-project proj2 100 300 " +
            "add-layer Layer1 ");
    Appendable log = new StringBuilder();
    IModel mock = new ModelConfirmInputsMock(log);
    IView view = new View(new StringBuilder());
    IController controller = new Controller(mock, view, in);
    try {
      controller.runProgram();
    } catch (IllegalStateException ignored) {
    }
    String[] lines = log.toString().split("\n", -1);
    assertEquals("Layer name: Layer1", lines[lines.length - 2]);
  }

  @Test
  public void addImageToLayer() {
    Readable in = new StringReader("new-project proj2 100 300 " +
            "add-layer Layer1 " + "add-image-to-layer Layer1 tako.ppm 0 0 ");
    Appendable log = new StringBuilder();
    IModel mock = new ModelConfirmInputsMock(log);
    IView view = new View(new StringBuilder());
    IController controller = new Controller(mock, view, in);
    try {
      controller.runProgram();
    } catch (IllegalStateException ignored) {
    }
    String[] lines = log.toString().split("\n", -1);
    assertEquals("name: proj2", lines[lines.length - 12]);
    assertEquals("height: 100", lines[lines.length - 11]);
    assertEquals("width: 300", lines[lines.length - 10]);
    assertEquals("Layer name: Layer1", lines[lines.length - 9]);
    assertEquals("Layer name: Layer1", lines[lines.length - 8]);
    assertEquals("Image width: 1000", lines[lines.length - 7]);
    assertEquals("Image height: 1000", lines[lines.length - 6]);
    assertEquals("Image[0][0] R: 173", lines[lines.length - 5]);
    assertEquals("Image[0][0] G: 179", lines[lines.length - 4]);
    assertEquals("Image[0][0] B: 151", lines[lines.length - 3]);
    assertEquals("Image[0][0] A: 255", lines[lines.length - 2]);
  }
}