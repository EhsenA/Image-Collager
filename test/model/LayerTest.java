package model;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import model.filters.BlueComponent;
import model.filters.FilterOptions;
import model.filters.GreenComponent;
import model.filters.RedComponent;
import utils.ImageUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * A LayerTest where we
 * test the Layer class and all of its methods.
 * Layer has a name, the image on the layer, a list of filters applied to the layer,
 * and the height and width of the project.
 * The Layer class allows to
 * <li> add an image to the layer </li>
 * <li> add and apply the filter to the layer </li>
 * <li> get the name, image, and filters for a layer </li>
 */
public class LayerTest {
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
  }

  @Test
  public void testInvalidConstruction() {
    try {
      new Layer("layer1", -234, 467);
      fail("Should throw IllegalArgumentException because height or width is not positive.");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      new Layer("layer1", 0, 574);
      fail("Should throw IllegalArgumentException because height or width is not positive.");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      new Layer("layer1", 234, -467);
      fail("Should throw IllegalArgumentException because height or width is not positive.");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      new Layer("layer1", 7895, 0);
      fail("Should throw IllegalArgumentException because height or width is not positive.");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testValidConstruction() {
    try {
      new Layer("layer1", height, width);
      new Layer("layer2", 600, 400);
      new Layer("randomVals", 389, 635);
      new Layer("mini", 2, 4);
    } catch (IllegalArgumentException e) {
      fail("Reached a fail point. Should not throw an Exception.");
    }
  }

  @Test
  public void addImage() {
    layer4.addImage(0, 0, image2);
    assertEquals(100, layer4.getImage()[0][0].getR());
    assertEquals(20, layer4.getImage()[0][0].getG());
    assertEquals(0, layer4.getImage()[0][0].getB());
    assertEquals(123, layer4.getImage()[0][0].getA());

    layer4.addImage(1, 2, image1);
    assertEquals(100, layer4.getImage()[0][0].getR());
    assertEquals(20, layer4.getImage()[0][0].getG());
    assertEquals(0, layer4.getImage()[0][0].getB());
    assertEquals(123, layer4.getImage()[0][0].getA());

    assertEquals(255, layer4.getImage()[1][2].getR());
    assertEquals(255, layer4.getImage()[1][2].getG());
    assertEquals(255, layer4.getImage()[1][2].getB());
    assertEquals(255, layer4.getImage()[1][2].getA());

    layer4.addImage(0, 2, image2);
    assertEquals(0, layer4.getImage()[1][3].getR());
    assertEquals(0, layer4.getImage()[1][3].getG());
    assertEquals(0, layer4.getImage()[1][3].getB());
    assertEquals(255, layer4.getImage()[1][3].getA());

    layer1.addImage(0, 0, image1);
    assertEquals(173, layer1.getImage()[0][0].getR());
    assertEquals(179, layer1.getImage()[0][0].getG());
    assertEquals(151, layer1.getImage()[0][0].getB());
    assertEquals(255, layer1.getImage()[0][0].getA());

    assertEquals(174, layer1.getImage()[0][7].getR());
    assertEquals(180, layer1.getImage()[0][7].getG());
    assertEquals(152, layer1.getImage()[0][7].getB());
    assertEquals(255, layer1.getImage()[0][2].getA());
  }

  @Test
  public void addAndapplyFilter() {
    layer4.addImage(0, 0, image2);
    layer4.addFilter(new RedComponent());
    IPixel[][] pixVal = layer4.applyFilter(new Pixel[2][2]);
    assertEquals(100, pixVal[0][0].getR());
    assertEquals(0, pixVal[0][0].getG());
    assertEquals(0, pixVal[0][0].getB());
    assertEquals(123, pixVal[0][0].getA());

    layer4.addFilter(new GreenComponent());
    pixVal = layer4.applyFilter(new Pixel[2][2]);
    assertEquals(0, pixVal[0][0].getR());
    assertEquals(20, pixVal[0][0].getG());
    assertEquals(0, pixVal[0][0].getB());
    assertEquals(123, pixVal[0][0].getA());

    init();
    layer4.addImage(0, 0, image2);
    layer4.addFilter(new GreenComponent());
    pixVal = layer4.applyFilter(new Pixel[2][2]);
    assertEquals(0, pixVal[0][0].getR());
    assertEquals(20, pixVal[0][0].getG());
    assertEquals(0, pixVal[0][0].getB());
    assertEquals(123, pixVal[0][0].getA());

    init();
    layer4.addImage(0, 0, image2);
    layer4.addFilter(new BlueComponent());
    pixVal = layer4.applyFilter(new Pixel[2][2]);
    assertEquals(0, pixVal[0][0].getR());
    assertEquals(0, pixVal[0][0].getG());
    assertEquals(0, pixVal[0][0].getB());
    assertEquals(123, pixVal[0][0].getA());

    assertEquals(0, pixVal[0][2].getR());
    assertEquals(0, pixVal[0][2].getG());
    assertEquals(200, pixVal[0][2].getB());
    assertEquals(50, pixVal[0][2].getA());

    assertEquals(0, pixVal[1][2].getR());
    assertEquals(0, pixVal[1][2].getG());
    assertEquals(255, pixVal[1][2].getB());
    assertEquals(255, pixVal[1][2].getA());

    assertEquals(0, pixVal[1][3].getR());
    assertEquals(0, pixVal[1][3].getG());
    assertEquals(0, pixVal[1][3].getB());
    assertEquals(255, pixVal[1][3].getA());
  }

  @Test
  public void getImage() {
    layer4.addImage(0, 0, image2);
    IPixel[][] copy = layer4.getImage();
    assertNotEquals(copy, layer4.getImage());

    assertEquals(image2[0][0].getR(), copy[0][0].getR());
    assertEquals(image2[0][0].getG(), copy[0][0].getG());
    assertEquals(image2[0][0].getB(), copy[0][0].getB());
    assertEquals(image2[0][0].getA(), copy[0][0].getA());

    assertEquals(image2[0][1].getR(), copy[0][1].getR());
    assertEquals(image2[0][2].getG(), copy[0][2].getG());
    assertEquals(image2[0][3].getB(), copy[0][3].getB());
    assertEquals(image2[1][0].getA(), copy[1][0].getA());
    assertEquals(image2[1][1].getR(), copy[1][1].getR());
    assertEquals(image2[1][2].getG(), copy[1][2].getG());
    assertEquals(image2[1][3].getB(), copy[1][3].getB());
  }

  @Test
  public void getName() {
    assertEquals("layer1", layer1.getName());
    assertEquals("layer2", layer2.getName());
    assertEquals("randomVals", layer3.getName());
    assertEquals("mini", layer4.getName());
  }

  @Test
  public void getFilters() {
    layer4.addImage(0, 0, image2);
    layer4.addFilter(new RedComponent());
    FilterOptions filter = layer4.getFilter();
    layer4.addFilter(new BlueComponent());
    IPixel[][] image = layer4.applyFilter(new Pixel[0][0]);
    assertEquals(0, image[0][0].getR());
    assertEquals(0, image[0][0].getG());
    assertEquals(0, image[0][0].getB());
    assertEquals(123, image[0][0].getA());

    layer4.addFilter(new BlueComponent());
    image = layer4.applyFilter(new Pixel[2][2]);
    assertEquals(0, image[0][0].getR());
    assertEquals(0, image[0][0].getG());
    assertEquals(0, image[0][0].getB());
    assertEquals(123, image[0][0].getA());
  }
}