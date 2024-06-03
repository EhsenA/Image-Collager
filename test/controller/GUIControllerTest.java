package controller;

import org.junit.Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import model.IPixel;
import model.IProject;
import model.Model;
import model.IModel;
import model.Pixel;
import model.Project;
import model.filters.FilterOptions;
import view.GUIView;
import view.IGUIView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A GUIControllerTest where we
 * test a GUIController and all of its methods.
 * GUIController has a model and view and it is the implementation
 * controller for the collage.
 * This tests for the allowance of the collage to be created and manipulated
 * by using methods declared in the model
 */
public class GUIControllerTest {

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
      displayMessage("newProject invoked \n");
    }

    @Override
    public void switchProject(IProject proj) {
      displayMessage("switchProject invoked \n");
    }

    @Override
    public void setFilter(String layerName, FilterOptions opt) throws IllegalArgumentException {
      displayMessage("setFilter invoked \n");
    }

    @Override
    public IPixel[][] saveImage() {
      displayMessage("saveImage invoked \n");
      Pixel[][] pixels = new Pixel[1][1];
      pixels[0][0] = new Pixel(255, 255, 255, 0);
      return pixels;
    }

    @Override
    public void addLayer(String layerName) throws IllegalArgumentException {
      displayMessage("addLayer invoked \n");
    }

    @Override
    public void addImageToLayer(String layerName, IPixel[][] image, int x, int y)
            throws IllegalArgumentException {
      displayMessage("addImageToLayer invoked \n");
    }

    @Override
    public FilterOptions getFilterOptionsObject(String filter) throws IllegalArgumentException {
      return null;
    }

    @Override
    public IProject openProject(StringBuilder builder) {
      return null;
    }

    @Override
    public IPixel[][] createImageFromBuff(BufferedImage buffered) {
      return new IPixel[0][];
    }

    @Override
    public IProject getCurProject() {
      displayMessage("getCurProject invoked \n");
      return new Project("proj2", 100, 300);
    }

    private void displayMessage(String message) {
      try {
        this.log.append(message);
      } catch (IOException ignored) {
      }
    }
  }

  /**
   * ModelConfirmInputsMock implements IModel. This creates a mock model for the IModel
   * which can then be used for testing.
   * This class implements Appendable.
   */
  class ViewConfirmInputsMock implements IGUIView {
    private final Appendable log;

    public ViewConfirmInputsMock(Appendable log) {
      this.log = log;
    }

    @Override
    public void makeVisible() {
      return;
    }

    @Override
    public void setListener(ActionListener listener) {
      return;
    }

    @Override
    public void addLayerNametoList(List<String> layer) {
      displayMessage("addLayerNametoList invoked \n");
    }

    @Override
    public String getLayerSelected() {
      displayMessage("getLayerSelected invoked \n");
      return "";
    }

    @Override
    public void refresh() {
      displayMessage("refresh invoked \n");
    }

    @Override
    public String getString(String message) {
      displayMessage("getString invoked \n");
      return "";
    }

    @Override
    public String getFilePath() {
      displayMessage("getFilePath invoked \n");
      return "hello.hel";
    }

    @Override
    public String getDirectory() {
      displayMessage("getDirectory invoked \n");
      return "hello.hel";
    }

    @Override
    public Integer getValue(String message) {
      displayMessage("getValue invoked \n");
      return 0;
    }

    @Override
    public void setImage(BufferedImage buff) {
      displayMessage("setImage invoked \n");
    }

    @Override
    public void renderMessage(String message) throws IOException {
      //empty
    }

    @Override
    public void showOptions() throws IOException {
      //empty
    }

    @Override
    public void showFilterOptions() throws IOException {
      //empty
    }

    private void displayMessage(String message) {
      try {
        this.log.append(message);
      } catch (IOException ignored) {
      }
    }
  }

  @Test
  public void testValidConstructor() {
    try {
      IModel model = new Model();
      IGUIView view = new GUIView();
      IGUIController controller = new GUIController(model, view);
    } catch (IllegalArgumentException e) {
      fail("The model, view, or readable input is null. This exception should not be thrown.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    IGUIView view = new GUIView();
    IGUIController controller = new GUIController(null, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor2() {
    IModel model = new Model();
    IGUIController controller = new GUIController(model, null);
  }

  @Test
  public void newProject() {
    Appendable log = new StringBuilder();
    Appendable log2 = new StringBuilder();
    IModel model = new ModelConfirmInputsMock(log);
    IGUIView view = new ViewConfirmInputsMock(log2);
    IGUIController controller = new GUIController(model, view);
    ActionEvent action = new ActionEvent(new Object(), 1, "New Project Button");
    controller.actionPerformed(action);
    assertEquals("newProject invoked \n" +
            "saveImage invoked \n" +
            "getCurProject invoked \n", log.toString());

    assertEquals("getString invoked \n" +
            "getValue invoked \n" +
            "getValue invoked \n" +
            "setImage invoked \n" +
            "addLayerNametoList invoked \n" +
            "refresh invoked \n", log2.toString());
  }

  @Test
  public void loadProject() {
    Appendable log = new StringBuilder();
    Appendable log2 = new StringBuilder();
    IModel model = new ModelConfirmInputsMock(log);
    IGUIView view = new ViewConfirmInputsMock(log2);
    IGUIController controller = new GUIController(model, view);
    ActionEvent action = new ActionEvent(new Object(), 1, "Load Project Button");
    controller.actionPerformed(action);

    assertEquals("switchProject invoked \n" +
            "saveImage invoked \n" +
            "getCurProject invoked \n", log.toString());

    assertEquals("getFilePath invoked \n" +
            "setImage invoked \n" +
            "addLayerNametoList invoked \n" +
            "refresh invoked \n", log2.toString());
  }

  @Test
  public void saveProject() {
    Appendable log = new StringBuilder();
    Appendable log2 = new StringBuilder();
    IModel model = new ModelConfirmInputsMock(log);
    IGUIView view = new ViewConfirmInputsMock(log2);
    IGUIController controller = new GUIController(model, view);
    ActionEvent action = new ActionEvent(new Object(), 1, "Save Project Button");
    controller.actionPerformed(action);
    assertEquals("getCurProject invoked \n", log.toString());

    assertEquals("getDirectory invoked \n", log2.toString());
  }

  @Test
  public void addLayer() {
    Appendable log = new StringBuilder();
    Appendable log2 = new StringBuilder();
    IModel model = new ModelConfirmInputsMock(log);
    IGUIView view = new ViewConfirmInputsMock(log2);
    IGUIController controller = new GUIController(model, view);
    ActionEvent action = new ActionEvent(new Object(), 1, "Add Layer Button");
    controller.actionPerformed(action);
    assertEquals("addLayer invoked \n" +
            "getCurProject invoked \n", log.toString());

    assertEquals("getString invoked \n" +
            "addLayerNametoList invoked \n" +
            "refresh invoked \n", log2.toString());
  }

  @Test
  public void addImagetoLayer() {
    Appendable log = new StringBuilder();
    Appendable log2 = new StringBuilder();
    IModel model = new ModelConfirmInputsMock(log);
    IGUIView view = new ViewConfirmInputsMock(log2);
    IGUIController controller = new GUIController(model, view);
    ActionEvent action = new ActionEvent(new Object(), 1, "Add Image to Layer Button");
    controller.actionPerformed(action);
    assertEquals("addImageToLayer invoked \n" +
            "saveImage invoked \n", log.toString());

    assertEquals("getFilePath invoked \n" +
            "getValue invoked \n" +
            "getValue invoked \n" +
            "getLayerSelected invoked \n" +
            "setImage invoked \n" +
            "refresh invoked \n", log2.toString());
  }

  @Test
  public void saveImage() {
    Appendable log = new StringBuilder();
    Appendable log2 = new StringBuilder();
    IModel model = new ModelConfirmInputsMock(log);
    IGUIView view = new ViewConfirmInputsMock(log2);
    IGUIController controller = new GUIController(model, view);
    ActionEvent action = new ActionEvent(new Object(), 1, "Save Image Button");
    controller.actionPerformed(action);
    assertEquals("saveImage invoked \n", log.toString());

    assertEquals("getDirectory invoked \n", log2.toString());
  }

  @Test
  public void normalFilter() {
    Appendable log = new StringBuilder();
    Appendable log2 = new StringBuilder();
    IModel model = new ModelConfirmInputsMock(log);
    IGUIView view = new ViewConfirmInputsMock(log2);
    IGUIController controller = new GUIController(model, view);
    ActionEvent action = new ActionEvent(new Object(), 1, "Normal Button");
    controller.actionPerformed(action);
    assertEquals("setFilter invoked \n" + "saveImage invoked \n", log.toString());

    assertEquals("getLayerSelected invoked \n" +
            "setImage invoked \n" +
            "refresh invoked \n", log2.toString());
  }

  @Test
  public void redComponentFilter() {
    Appendable log = new StringBuilder();
    Appendable log2 = new StringBuilder();
    IModel model = new ModelConfirmInputsMock(log);
    IGUIView view = new ViewConfirmInputsMock(log2);
    IGUIController controller = new GUIController(model, view);
    ActionEvent action = new ActionEvent(new Object(), 1, "Red Component Button");
    controller.actionPerformed(action);
    assertEquals("setFilter invoked \n" + "saveImage invoked \n", log.toString());

    assertEquals("getLayerSelected invoked \n" +
            "setImage invoked \n" +
            "refresh invoked \n", log2.toString());
  }

  @Test
  public void greenComponentFilter() {
    Appendable log = new StringBuilder();
    Appendable log2 = new StringBuilder();
    IModel model = new ModelConfirmInputsMock(log);
    IGUIView view = new ViewConfirmInputsMock(log2);
    IGUIController controller = new GUIController(model, view);
    ActionEvent action = new ActionEvent(new Object(), 1, "Green Component Button");
    controller.actionPerformed(action);
    assertEquals("setFilter invoked \n" + "saveImage invoked \n", log.toString());

    assertEquals("getLayerSelected invoked \n" +
            "setImage invoked \n" +
            "refresh invoked \n", log2.toString());
  }

  @Test
  public void blueComponentFilter() {
    Appendable log = new StringBuilder();
    Appendable log2 = new StringBuilder();
    IModel model = new ModelConfirmInputsMock(log);
    IGUIView view = new ViewConfirmInputsMock(log2);
    IGUIController controller = new GUIController(model, view);
    ActionEvent action = new ActionEvent(new Object(), 1, "Blue Component Button");
    controller.actionPerformed(action);
    assertEquals("setFilter invoked \n" + "saveImage invoked \n", log.toString());

    assertEquals("getLayerSelected invoked \n" +
            "setImage invoked \n" +
            "refresh invoked \n", log2.toString());
  }

  @Test
  public void brightenValueFilter() {
    Appendable log = new StringBuilder();
    Appendable log2 = new StringBuilder();
    IModel model = new ModelConfirmInputsMock(log);
    IGUIView view = new ViewConfirmInputsMock(log2);
    IGUIController controller = new GUIController(model, view);
    ActionEvent action = new ActionEvent(new Object(), 1, "Brighten Value Button");
    controller.actionPerformed(action);
    assertEquals("setFilter invoked \n" + "saveImage invoked \n", log.toString());

    assertEquals("getLayerSelected invoked \n" +
            "setImage invoked \n" +
            "refresh invoked \n", log2.toString());
  }

  @Test
  public void brightenIntensityFilter() {
    Appendable log = new StringBuilder();
    Appendable log2 = new StringBuilder();
    IModel model = new ModelConfirmInputsMock(log);
    IGUIView view = new ViewConfirmInputsMock(log2);
    IGUIController controller = new GUIController(model, view);
    ActionEvent action = new ActionEvent(new Object(), 1, "Brighten Intensity Button");
    controller.actionPerformed(action);
    assertEquals("setFilter invoked \n" + "saveImage invoked \n", log.toString());

    assertEquals("getLayerSelected invoked \n" +
            "setImage invoked \n" +
            "refresh invoked \n", log2.toString());
  }

  @Test
  public void brightenLumaFilter() {
    Appendable log = new StringBuilder();
    Appendable log2 = new StringBuilder();
    IModel model = new ModelConfirmInputsMock(log);
    IGUIView view = new ViewConfirmInputsMock(log2);
    IGUIController controller = new GUIController(model, view);
    ActionEvent action = new ActionEvent(new Object(), 1, "Brighten Luma Button");
    controller.actionPerformed(action);
    assertEquals("setFilter invoked \n" + "saveImage invoked \n", log.toString());

    assertEquals("getLayerSelected invoked \n" +
            "setImage invoked \n" +
            "refresh invoked \n", log2.toString());
  }

  @Test
  public void darkenValueFilter() {
    Appendable log = new StringBuilder();
    Appendable log2 = new StringBuilder();
    IModel model = new ModelConfirmInputsMock(log);
    IGUIView view = new ViewConfirmInputsMock(log2);
    IGUIController controller = new GUIController(model, view);
    ActionEvent action = new ActionEvent(new Object(), 1, "Darken Value Button");
    controller.actionPerformed(action);
    assertEquals("setFilter invoked \n" + "saveImage invoked \n", log.toString());

    assertEquals("getLayerSelected invoked \n" +
            "setImage invoked \n" +
            "refresh invoked \n", log2.toString());
  }

  @Test
  public void darkenIntensityFilter() {
    Appendable log = new StringBuilder();
    Appendable log2 = new StringBuilder();
    IModel model = new ModelConfirmInputsMock(log);
    IGUIView view = new ViewConfirmInputsMock(log2);
    IGUIController controller = new GUIController(model, view);
    ActionEvent action = new ActionEvent(new Object(), 1, "Darken Intensity Button");
    controller.actionPerformed(action);
    assertEquals("setFilter invoked \n" + "saveImage invoked \n", log.toString());

    assertEquals("getLayerSelected invoked \n" +
            "setImage invoked \n" +
            "refresh invoked \n", log2.toString());
  }

  @Test
  public void darkenLumaFilter() {
    Appendable log = new StringBuilder();
    Appendable log2 = new StringBuilder();
    IModel model = new ModelConfirmInputsMock(log);
    IGUIView view = new ViewConfirmInputsMock(log2);
    IGUIController controller = new GUIController(model, view);
    ActionEvent action = new ActionEvent(new Object(), 1, "Darken Luma Button");
    controller.actionPerformed(action);
    assertEquals("setFilter invoked \n" + "saveImage invoked \n", log.toString());

    assertEquals("getLayerSelected invoked \n" +
            "setImage invoked \n" +
            "refresh invoked \n", log2.toString());
  }

  @Test
  public void differenceFilter() {
    Appendable log = new StringBuilder();
    Appendable log2 = new StringBuilder();
    IModel model = new ModelConfirmInputsMock(log);
    IGUIView view = new ViewConfirmInputsMock(log2);
    IGUIController controller = new GUIController(model, view);
    ActionEvent action = new ActionEvent(new Object(), 1, "Difference Button");
    controller.actionPerformed(action);
    assertEquals("setFilter invoked \n" + "saveImage invoked \n", log.toString());

    assertEquals("getLayerSelected invoked \n" +
            "setImage invoked \n" +
            "refresh invoked \n", log2.toString());
  }

  @Test
  public void brightenBlendFilter() {
    Appendable log = new StringBuilder();
    Appendable log2 = new StringBuilder();
    IModel model = new ModelConfirmInputsMock(log);
    IGUIView view = new ViewConfirmInputsMock(log2);
    IGUIController controller = new GUIController(model, view);
    ActionEvent action = new ActionEvent(new Object(), 1, "Brighten Blend Button");
    controller.actionPerformed(action);
    assertEquals("setFilter invoked \n" + "saveImage invoked \n", log.toString());

    assertEquals("getLayerSelected invoked \n" +
            "setImage invoked \n" +
            "refresh invoked \n", log2.toString());
  }

  @Test
  public void darkenBlendFilter() {
    Appendable log = new StringBuilder();
    Appendable log2 = new StringBuilder();
    IModel model = new ModelConfirmInputsMock(log);
    IGUIView view = new ViewConfirmInputsMock(log2);
    IGUIController controller = new GUIController(model, view);
    ActionEvent action = new ActionEvent(new Object(), 1, "Darken Blend Button");
    controller.actionPerformed(action);
    assertEquals("setFilter invoked \n" + "saveImage invoked \n", log.toString());

    assertEquals("getLayerSelected invoked \n" +
            "setImage invoked \n" +
            "refresh invoked \n", log2.toString());
  }
}