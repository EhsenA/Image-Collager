package view;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.ILayer;
import model.IModel;
import model.IPixel;
import model.IProject;
import model.Layer;
import model.Model;
import model.Pixel;
import model.Project;
import model.filters.RedComponent;
import utils.ImageUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A ViewTest where we
 * test the View class and all of its methods.
 * View has a model and appendable.
 * The View class allows for the collage to be viewed, including
 * <li> viewing the menu options </li>
 * <li> viewing the filteroptions </li>
 * <li> viewing an inputted message </li>
 */
public class ViewTest {
  int maxValue;
  int height;
  int width;
  IPixel pix;
  IPixel pix2;
  IPixel pix3;
  IPixel redMax;
  IPixel greenMax;
  IPixel blueMax;
  IPixel white;
  IPixel black;
  ILayer layer1;
  ILayer layer2;
  ILayer layer3;
  ILayer layer4;
  IPixel[][] image1;
  IPixel[][] image2;
  IProject project1;
  IProject project2;
  IModel model1;
  IView view1;
  private Appendable out;

  @Before
  public void init() {
    maxValue = 255;
    height = 800;
    width = 600;
    pix = new Pixel(100, 20, 0, 123);
    pix2 = new Pixel(100, 150, 200, 50);
    pix3 = new Pixel(20, 150, 200, 50);
    redMax = new Pixel(maxValue, 123, 26, 0);
    greenMax = new Pixel(123, maxValue, 26, maxValue);
    blueMax = new Pixel(26, 123, maxValue, 127);
    white = new Pixel(maxValue, maxValue, maxValue, maxValue);
    black = new Pixel(0, 0, 0, maxValue);
    layer1 = new Layer("layer1", height, width);
    layer2 = new Layer("layer2", 600, 400);
    layer3 = new Layer("randomVals", 389, 635);
    layer4 = new Layer("mini", 2, 4);
    model1 = new Model();
    try {
      image1 = model1.createImageFromBuff(ImageUtil.aPPMtoImage("tako.ppm"));
    } catch (FileNotFoundException e) {
      fail("Invalid file path.");
    }
    image2 = new Pixel[2][4];
    image2[0][0] = pix;
    image2[0][1] = pix2;
    image2[0][2] = pix3;
    image2[0][3] = redMax;
    image2[1][0] = greenMax;
    image2[1][1] = blueMax;
    image2[1][2] = white;
    image2[1][3] = black;

    project1 = new Project("proj1", height, width);
    project2 = new Project("proj2", 7548, 9742);

    this.out = new StringBuilder();
    this.view1 = new View(out);
  }

  @Test
  public void renderMessage() {
    try {
      this.view1.renderMessage("\nInput a valid Image file.\n");
      assertEquals("\nInput a valid Image file.\n", this.out.toString());
      model1.newProject("proj1", height, width);
      this.model1.addLayer("Layer1");
      model1.addImageToLayer("Layer1", image1, 0, 0);
      model1.setFilter("Layer1", new RedComponent());
      model1.addLayer("SecondLayer");
      this.view1.renderMessage("The name of the project is: "
              + this.model1.getCurProject().getName());
      assertEquals("\nInput a valid Image file.\nThe name of the project is: proj1",
              this.out.toString());
    } catch (IOException ignored) {
    }
  }

  @Test
  public void showOptions() {
    try {
      this.view1.showOptions();
      assertEquals("Menu: \n"
                      + "new-project project-name canvas-height canvas-width \n"
                      + "load-project path-to-project-file \n"
                      + "save-project path-to-project-file \n"
                      + "add-layer layer-name \n"
                      + "add-image-to-layer layer-name image-name x-pos y-pos \n"
                      + "set-filter layer-name filter-option \n"
                      + "save-image file-name \n"
                      + "quit \n"
                      + "Here are the currently supported filter options: \n"
                      + "red-component: when applied, only uses the red portion of the RGB. "
                      + "Similar for green-component and blue-component\n"
                      + "brighten-value when applied, adds the brightness value pixel by pixel "
                      + "according to value from the corresponding pixel on the current layer. "
                      + "Similar for "
                      + "brighten-intensity and brighten-luma. Only affects the current layer.\n"
                      + "darken-value: when applied, "
                      + "removes the brightness value pixel by pixel according "
                      + "to value from the corresponding pixel on the current layer. Similar for "
                      + "darken-intensity and darken-luma. Only affects the current layer. \n",
              this.out.toString());
    } catch (IOException ignored) {
    }
  }

  @Test
  public void showFilterOptions() {
    try {
      this.view1.showFilterOptions();
      assertEquals("Here are the currently supported filter options: \n"
                      + "red-component: when applied, only uses the red portion of the RGB. "
                      + "Similar for green-component and blue-component\n"
                      + "brighten-value when applied, adds the brightness value pixel by pixel "
                      + "according to value from the corresponding pixel on the current layer. "
                      + "Similar for "
                      + "brighten-intensity and brighten-luma. Only affects the current layer.\n"
                      + "darken-value: when applied, "
                      + "removes the brightness value pixel by pixel according "
                      + "to value from the corresponding pixel on the current layer. Similar for "
                      + "darken-intensity and darken-luma. Only affects the current layer. \n",
              this.out.toString());
    } catch (IOException ignored) {
    }
  }
}