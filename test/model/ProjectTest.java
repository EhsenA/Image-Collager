package model;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

import model.filters.BlueComponent;
import model.filters.FilterOptions;
import model.filters.RedComponent;
import utils.ImageUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * A ProjectTest where we
 * test the Project class and all of its methods.
 * Project has a name, a list of layers, and the height and width of the project.
 * This tests for the allowance of the game to be played, including
 * <li> add a layer to a project </li>
 * <li> add an image to a layer in a project </li>
 * <li> set a filter to the layer </li>
 * <li> save a copy of the composed image </li>
 * <li> get the list of layers for a project, name, height, and width </li>
 */
public class ProjectTest {
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
  IModel model;

  @Before
  public void init() {
    maxValue = 255;
    height = 800;
    width = 600;
    pix = new Pixel(100, 20, 0, 123);
    pix2 = new Pixel(100,150,200, 50);
    pix3 = new Pixel(20,150,200, 50);
    redMax = new Pixel(maxValue, 123, 26, 0);
    greenMax = new Pixel(123, maxValue, 26, maxValue);
    blueMax = new Pixel(26, 123, maxValue, 127);
    white = new Pixel(maxValue, maxValue, maxValue, maxValue);
    black = new Pixel(0, 0, 0, maxValue);
    layer1 = new Layer("layer1", height, width);
    layer2 = new Layer("layer2", 600, 400);
    layer3 = new Layer("randomVals", 389, 635);
    layer4 = new Layer("mini", 2, 4);
    model = new Model();
    try {
      image1 = model.createImageFromBuff(ImageUtil.aPPMtoImage("tako.ppm"));
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
  }

  @Test
  public void testInvalidConstruction() {
    try {
      new Project("proj1", -234, 467);
      fail("Should throw IllegalArgumentException because height or width is not positive.");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      new Project("proj1", 0, 574);
      fail("Should throw IllegalArgumentException because height or width is not positive.");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      new Project("proj1", 234, -467);
      fail("Should throw IllegalArgumentException because height or width is not positive.");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      new Project("proj1", 7895, 0);
      fail("Should throw IllegalArgumentException because height or width is not positive.");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testValidConstruction() {
    try {
      new Project("proj1", height, width);
      new Project("proj2", 7548, 9742);
    } catch (IllegalArgumentException e) {
      fail("Reached a fail point. Should not throw an Exception.");
    }
  }

  @Test
  public void addLayer() {
    project1.addLayer("Layer1");
    project1.addImageToLayer("Layer1", image1, 0, 0);
    List<ILayer> layerList = project1.getLayers();
    IPixel[][] img = project1.getLayers().get(0).getImage();
    assertEquals(1, layerList.size());
    assertEquals("Layer1", layerList.get(0).getName());

    assertEquals(173, img[0][0].getR());
    assertEquals(179, img[0][0].getG());
    assertEquals(151, img[0][0].getB());
    assertEquals(255, img[0][0].getA());
  }


  @Test
  public void addImageToLayer() {
    project1.addLayer("Layer1");
    project1.addImageToLayer("Layer1", image2, 0, 0);

    try {
      project1.addImageToLayer("Layer2", image2, 0, 0); //Layer does not exist
      fail("Should throw IllegalArgumentException because layer does not exist.");
    } catch (IllegalArgumentException ignored) {
    }


    IPixel[][] img = project1.getLayers().get(0).getImage();
    assertEquals(100, img[0][0].getR());
    assertEquals(20, img[0][0].getG());
    assertEquals(0, img[0][0].getB());
    assertEquals(123, img[0][0].getA());

    project1.addImageToLayer("Layer1", image1, 1, 2);
    img = project1.getLayers().get(0).getImage();
    assertEquals(100, img[0][0].getR());
    assertEquals(20, img[0][0].getG());
    assertEquals(0, img[0][0].getB());
    assertEquals(123, img[0][0].getA());

    assertEquals(255, img[1][2].getR());
    assertEquals(255, img[1][2].getG());
    assertEquals(255, img[1][2].getB());
    assertEquals(255, img[1][2].getA());

    project1.addImageToLayer("Layer1", image2, 0, 2);
    img = project1.getLayers().get(0).getImage();
    assertEquals(0, img[1][3].getR());
    assertEquals(0, img[1][3].getG());
    assertEquals(0, img[1][3].getB());
    assertEquals(255, img[1][3].getA());

    project1.addLayer("Layer2");
    project1.addImageToLayer("Layer2", image1, 0, 0);
    img = project1.getLayers().get(1).getImage();
    assertEquals(173, img[0][0].getR());
    assertEquals(179, img[0][0].getG());
    assertEquals(151, img[0][0].getB());
    assertEquals(255, img[0][0].getA());

    assertEquals(174, img[0][7].getR());
    assertEquals(180, img[0][7].getG());
    assertEquals(152, img[0][7].getB());
    assertEquals(255, img[0][2].getA());
  }

  @Test
  public void setFilter() {
    try {
      project1.setFilter("Layer1", new RedComponent()); //Layer does not exist
      fail("Should throw IllegalArgumentException because layer does not exist.");
    } catch (IllegalArgumentException ignored) {
    }

    project1.addLayer("Layer1");
    project1.addImageToLayer("Layer1", image2, 0, 0);
    project1.setFilter("Layer1", new BlueComponent());

    try {
      project1.setFilter("Layer3", new RedComponent()); //Layer does not exist
      fail("Should throw IllegalArgumentException because layer does not exist.");
    } catch (IllegalArgumentException ignored) {
    }

    ILayer layer1 = project1.getLayers().get(0);
    FilterOptions layer1Filter = layer1.getFilter();
    assertEquals("blue-component", layer1Filter.toString());
  }

  @Test
  public void saveImage() {
    project1.addLayer("Layer1");
    project1.addImageToLayer("Layer1", image2, 0, 0);

    IPixel[][] copy = project1.saveImage();
    IPixel[][] img = project1.getLayers().get(0).getImage();

    assertNotEquals(copy, project1.saveImage());

    assertEquals(img[0][0].getR(), copy[0][0].getR());
    assertEquals(img[0][0].getG(), copy[0][0].getG());
    assertEquals(img[0][0].getB(), copy[0][0].getB());
    assertEquals(img[0][0].getA(), copy[0][0].getA());

    assertEquals(img[0][1].getR(), copy[0][1].getR());
    assertEquals(img[0][2].getG(), copy[0][2].getG());

    //a = 0 -- so the image is transparent which means we should see the background which is white.
    assertEquals(255, copy[0][3].getB());

    assertEquals(img[1][0].getA(), copy[1][0].getA());
    assertEquals(img[1][1].getR(), copy[1][1].getR());
    assertEquals(img[1][2].getG(), copy[1][2].getG());
    assertEquals(img[1][3].getB(), copy[1][3].getB());

    project1.setFilter("Layer1", new RedComponent());
    copy = project1.saveImage();
    img = project1.getLayers().get(0).applyFilter(new Pixel[0][0]);

    assertNotEquals(copy, project1.saveImage());

    assertEquals(img[0][0].getR(), copy[0][0].getR());
    assertEquals(img[0][0].getG(), copy[0][0].getG());
    assertEquals(img[0][0].getB(), copy[0][0].getB());
    assertEquals(img[0][0].getA(), copy[0][0].getA());

    assertEquals(img[0][1].getR(), copy[0][1].getR());
    assertEquals(img[0][2].getG(), copy[0][2].getG());

    //a = 0 -- so the image is transparent which means we should see the background which is white.
    assertEquals(255, copy[0][3].getB());

    assertEquals(img[1][0].getA(), copy[1][0].getA());
    assertEquals(img[1][1].getR(), copy[1][1].getR());
    assertEquals(img[1][2].getG(), copy[1][2].getG());
    assertEquals(img[1][3].getB(), copy[1][3].getB());

    project1.addLayer("Layer2");
    project1.addImageToLayer("Layer2", image2, 1, 2);
    copy = project1.saveImage();

    assertNotEquals(copy, project1.saveImage());

    assertEquals(100, copy[0][0].getR());
    assertEquals(0, copy[0][0].getG());
    assertEquals(0, copy[0][0].getB());
    assertEquals(123, copy[0][0].getA());

    assertEquals(100, copy[0][1].getR());
    assertEquals(0, copy[0][2].getG());

    //a = 0 -- so the image is transparent which means we should see the background which is white.
    assertEquals(255, copy[0][3].getB());

    assertEquals(255, copy[1][0].getA());
    assertEquals(26, copy[1][1].getR());
    assertEquals(0, copy[1][2].getG());
    assertEquals(0, copy[1][3].getB());

  }

  @Test
  public void getLayers() {
    project1.addLayer("Layer1");
    project1.addImageToLayer("Layer1", image2, 0, 0);
    project1.setFilter("Layer1", new BlueComponent());

    ILayer layer1 = project1.getLayers().get(0);
    assertEquals("Layer1",layer1.getName());

    project1.addLayer("SecondLayer");
    project1.addImageToLayer("SecondLayer", image2, 0, 0);

    layer1 = project1.getLayers().get(0);
    assertEquals("Layer1",layer1.getName());
    ILayer layer2 = project1.getLayers().get(1);
    assertEquals("SecondLayer",layer2.getName());
  }

  @Test
  public void getName() {
    assertEquals("proj1", project1.getName());
    assertEquals("proj2", project2.getName());
  }

  @Test
  public void getHeight() {
    assertEquals(800, project1.getHeight());
    assertEquals(7548, project2.getHeight());
  }

  @Test
  public void getWidth() {
    assertEquals(600, project1.getWidth());
    assertEquals(9742, project2.getWidth());
  }
}