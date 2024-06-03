package model;

import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.List;

import model.filters.FilterOptions;
import model.filters.GreenComponent;
import model.filters.RedComponent;
import utils.ImageUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * A ModelTest where we
 * test the Model class and all of its methods.
 * Model has a project.
 * The Model class allows to
 * <li> make a new project </li>
 * <li> load a project </li>
 * <li> save a project </li>
 * <li> set a filter to a given layer in a project </li>
 * <li> add a layer to a project </li>
 * <li> add an image to a layer in a project </li>
 * <li> save a copy of the composed image to the given filepath </li>
 * <li> get the current project </li>
 */
public class ModelTest {
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
  }

  @Test
  public void testValidConstruction() {
    try {
      new Model();
    } catch (IllegalArgumentException e) {
      fail("Reached a fail point. Should not throw an Exception.");
    }
  }

  @Test
  public void newProject() {
    model1.newProject("testnew", 60, 50);
    assertEquals("testnew", model1.getCurProject().getName());
    assertEquals(60, model1.getCurProject().getHeight());
    assertEquals(50, model1.getCurProject().getWidth());

    model1.newProject("diffproj", 400, 921);
    try {
      model1.addLayer("layer1");
      model1.addLayer("secondlayer");
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("The provided layer name already exists");
    }
    assertEquals("diffproj", model1.getCurProject().getName());
    assertEquals(400, model1.getCurProject().getHeight());
    assertEquals(921, model1.getCurProject().getWidth());
    assertEquals(2, model1.getCurProject().getLayers().size());

    try {
      model1.newProject("badProj", -60, 50);
      fail("Invalid height. Should throw an exception.");
    } catch (IllegalArgumentException ignored) {
    }

    try {
      model1.newProject("badProj2", 60, -50);
      fail("Invalid width. Should throw an exception.");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void switchProject() {
    model1.newProject("diffproj", 400, 921);
    try {
      model1.addLayer("layer1");
      model1.addLayer("secondlayer");
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("The provided layer name already exists");
    }
    IProject diff = model1.getCurProject();
    assertEquals("diffproj", model1.getCurProject().getName());
    assertEquals(400, model1.getCurProject().getHeight());
    assertEquals(921, model1.getCurProject().getWidth());
    assertEquals(2, model1.getCurProject().getLayers().size());

    model1.switchProject(project1);

    assertEquals("proj1", model1.getCurProject().getName());
    assertEquals(800, model1.getCurProject().getHeight());
    assertEquals(600, model1.getCurProject().getWidth());
    assertEquals(0, model1.getCurProject().getLayers().size());

    model1.switchProject(diff);
    assertEquals("diffproj", model1.getCurProject().getName());
    assertEquals(400, model1.getCurProject().getHeight());
    assertEquals(921, model1.getCurProject().getWidth());
    assertEquals(2, model1.getCurProject().getLayers().size());
  }

  @Test
  public void setFilter() {
    model1.newProject("proj1", height, width);
    try {
      model1.setFilter("Layer1", new RedComponent()); //Layer does not exist
      fail("Should throw IllegalArgumentException because layer does not exist.");
    } catch (IllegalArgumentException ignored) {
    }

    try {
      model1.addLayer("Layer1"); //sets first filter for Layer1 to normal
      model1.addImageToLayer("Layer1", image1, 0, 0);
      model1.setFilter("Layer1", new RedComponent());
      model1.setFilter("Layer3", new RedComponent()); //Layer does not exist
      fail("Should throw IllegalArgumentException because layer does not exist.");
    } catch (IllegalArgumentException ignored) {
    }

    IProject proj = model1.getCurProject();
    ILayer layer1 = proj.getLayers().get(0);
    FilterOptions layer1Filter = layer1.getFilter();
    assertEquals("red-component", layer1Filter.toString());
  }

  @Test
  public void saveImage() {
    model1.newProject("proj1", height, width);
    try {
      model1.addLayer("Layer1");
      model1.addImageToLayer("Layer1", image2, 0, 0);
    } catch (IllegalArgumentException e) {
      fail("There is an error with the provided layer name");
    }

    IPixel[][] copy = model1.saveImage();
    IPixel[][] img = model1.getCurProject().getLayers().get(0).getImage();

    assertNotEquals(copy, model1.saveImage());

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

    try {
      model1.setFilter("Layer1", new RedComponent());
    } catch (IllegalArgumentException e) {
      fail("There is an error with the provided layer name");
    }
    copy = model1.saveImage();
    img = model1.getCurProject().getLayers().get(0).applyFilter(new Pixel[0][0]);

    assertNotEquals(copy, model1.saveImage());

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

    try {
      model1.addLayer("Layer2");
      model1.addImageToLayer("Layer2", image2, 1, 2);
    } catch (IllegalArgumentException e) {
      fail("There is an error with the provided layer name");
    }
    copy = model1.saveImage();

    assertNotEquals(copy, model1.saveImage());

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
  public void addLayer() {
    model1.newProject("proj1", height, width);
    try {
      model1.addLayer("Layer1");
      model1.addImageToLayer("Layer1", image1, 0, 0);
      model1.addLayer("Layer1");
      fail("There is an error with the provided layer name");
    } catch (IllegalArgumentException ignored) {
    }

    IProject proj = model1.getCurProject();
    List<ILayer> layerList = proj.getLayers();
    IPixel[][] img = proj.getLayers().get(0).getImage();
    assertEquals(1, layerList.size());
    assertEquals("Layer1", layerList.get(0).getName());

    assertEquals(173, img[0][0].getR());
    assertEquals(179, img[0][0].getG());
    assertEquals(151, img[0][0].getB());
    assertEquals(255, img[0][0].getA());

    try {
      model1.addLayer("SecondLayer");
      model1.addImageToLayer("SecondLayer", image1, 2, 2);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("There is an error with the provided layer name");
    }
    proj = model1.getCurProject();
    layerList = proj.getLayers();
    assertEquals(2, layerList.size());
    img = proj.getLayers().get(1).getImage();
    assertEquals(255, img[0][0].getR());
    assertEquals(255, img[0][0].getG());
    assertEquals(255, img[0][0].getB());
    assertEquals(0, img[0][0].getA());
    assertEquals(173, img[2][2].getR());
    assertEquals(179, img[2][2].getG());
    assertEquals(151, img[2][2].getB());
    assertEquals(255, img[2][2].getA());
  }

  @Test
  public void addImageToLayer() {
    model1.newProject("proj1", height, width);
    try {
      model1.addLayer("Layer1");
      model1.addImageToLayer("Layer1", image2, 0, 0);
      model1.addImageToLayer("Layer3", image2, 1, 0);
      fail("There is an error with the provided layer name");
    } catch (IllegalArgumentException ignored) {
    }

    IProject proj = model1.getCurProject();
    IPixel[][] img = proj.getLayers().get(0).getImage();
    assertEquals(100, img[0][0].getR());
    assertEquals(20, img[0][0].getG());
    assertEquals(0, img[0][0].getB());
    assertEquals(123, img[0][0].getA());

    try {
      model1.addImageToLayer("Layer1", image1, 1, 2);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("There is an error with the provided layer name");
    }
    proj = model1.getCurProject();
    img = proj.getLayers().get(0).getImage();
    assertEquals(100, img[0][0].getR());
    assertEquals(20, img[0][0].getG());
    assertEquals(0, img[0][0].getB());
    assertEquals(123, img[0][0].getA());

    assertEquals(255, img[1][2].getR());
    assertEquals(255, img[1][2].getG());
    assertEquals(255, img[1][2].getB());
    assertEquals(255, img[1][2].getA());

    try {
      model1.addImageToLayer("Layer1", image2, 0, 2);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("There is an error with the provided layer name");
    }
    proj = model1.getCurProject();
    img = proj.getLayers().get(0).getImage();
    assertEquals(0, img[1][3].getR());
    assertEquals(0, img[1][3].getG());
    assertEquals(0, img[1][3].getB());
    assertEquals(255, img[1][3].getA());

    try {
      model1.addLayer("Layer2");
      model1.addImageToLayer("Layer2", image1, 0, 0);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("There is an error with the provided layer name");
    }
    proj = model1.getCurProject();
    img = proj.getLayers().get(1).getImage();
    assertEquals(173, img[0][0].getR());
    assertEquals(179, img[0][0].getG());
    assertEquals(151, img[0][0].getB());
    assertEquals(255, img[0][0].getA());

    assertEquals(174, img[0][7].getR());
    assertEquals(180, img[0][7].getG());
    assertEquals(152, img[0][7].getB());
    assertEquals(255, img[0][7].getA());
  }

  @Test
  public void getFilterOptionsObject() {
    assertEquals("brighten-value", model1.getFilterOptionsObject("brighten-value").toString());
    assertEquals("brighten-intensity",
            model1.getFilterOptionsObject("brighten-intensity").toString());
    assertEquals("brighten-luma", model1.getFilterOptionsObject("brighten-luma").toString());
    assertEquals("darken-value", model1.getFilterOptionsObject("darken-value").toString());
    assertEquals("darken-intensity", model1.getFilterOptionsObject("darken-intensity").toString());
    assertEquals("darken-luma", model1.getFilterOptionsObject("darken-luma").toString());
    assertEquals("red-component", model1.getFilterOptionsObject("red-component").toString());
    assertEquals("green-component", model1.getFilterOptionsObject("green-component").toString());
    assertEquals("blue-component", model1.getFilterOptionsObject("blue-component").toString());
    assertEquals("normal", model1.getFilterOptionsObject("normal").toString());
    assertEquals("difference", model1.getFilterOptionsObject("difference").toString());
    assertEquals("brighten-blend", model1.getFilterOptionsObject("brighten-blend").toString());
    assertEquals("darken-blend", model1.getFilterOptionsObject("darken-blend").toString());
    try {
      model1.getFilterOptionsObject("not-normal");
      fail("Invalid filter option.");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void openProject() {
    try {
      project1 = this.model1.openProject(ImageUtil.openProject("ourtako.collage"));
    } catch (FileNotFoundException e) {
      fail("Invalid filename.");
    }
    assertEquals("proj1", project1.getName());
    assertEquals(800, project1.getHeight());
    assertEquals(600, project1.getWidth());

    try {
      project1 = this.model1.openProject(ImageUtil.openProject(
              "ourtaco.collage")); //Project does not exist
      fail("Filename for project does not exist.");
    } catch (FileNotFoundException ignored) {
    }
  }

  @Test
  public void createImageFromBuff() {
    try {
      project1 = this.model1.openProject(ImageUtil.openProject("ourtako.collage"));
    } catch (FileNotFoundException e) {
      fail("Filename for project does not exist.");
    }
    BufferedImage buff = ImageUtil.createBuffImage(project1.saveImage());

    IPixel[][] img = model1.createImageFromBuff(buff);

    assertEquals(173, img[0][0].getR());
    assertEquals(179, img[0][0].getG());
    assertEquals(151, img[0][0].getB());
    assertEquals(255, img[0][0].getA());

    assertEquals(174, img[0][7].getR());
    assertEquals(180, img[0][7].getG());
    assertEquals(152, img[0][7].getB());
    assertEquals(255, img[0][7].getA());

    assertEquals(175, img[6][8].getR());
    assertEquals(181, img[6][8].getG());
    assertEquals(153, img[6][8].getB());
    assertEquals(255, img[6][8].getA());

    assertEquals(127, img[232][321].getR());
    assertEquals(104, img[232][321].getG());
    assertEquals(122, img[232][321].getB());
    assertEquals(255, img[232][321].getA());

    assertEquals(167, img[799][599].getR());
    assertEquals(106, img[799][599].getG());
    assertEquals(25, img[799][599].getB());
    assertEquals(255, img[799][599].getA());
  }

  @Test
  public void getCurProject() {
    model1.newProject("proj1", height, width);
    try {
      model1.addLayer("Layer1");
      model1.addImageToLayer("Layer1", image1, 0, 0);
      model1.setFilter("Layer1", new GreenComponent());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("There is an error with the provided layer name");
    }
    IProject copy1 = model1.getCurProject();
    IProject copy2 = model1.getCurProject();
    assertNotEquals(copy1, copy2);
    assertEquals("proj1", copy1.getName());
    assertEquals(800, copy1.getHeight());
    assertEquals(600, copy1.getWidth());
  }
}