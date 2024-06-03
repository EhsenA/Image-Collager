package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A PixelTest where we
 * test the Pixel class and all of its methods.
 * Pixel has a red component, green component, blue component, and alpha values.
 * The Pixel class allows to
 * <li> brighten or darken a pixel </li>
 * <li> maximum value of the red, green, and blue pixel components </li>
 * <li> average value of the red, green, and blue pixel components </li>
 * <li> luma value of the red, green, and blue pixel components
 * by applying the weighted sum formula 0.2126r + 0.7152g + 0.0722b </li>
 * <li> get the red, green, blue, alpha and maxValue for a pixel </li>
 */
public class PixelTest {
  IPixel pix;
  IPixel pix2;
  IPixel pix3;
  IPixel redMax;
  IPixel greenMax;
  IPixel blueMax;
  IPixel white;
  IPixel black;

  @Before
  public void init() {
    int maxValue = 255;
    pix = new Pixel(100, 20, 0, 123);
    pix2 = new Pixel(100,150,200, 50);
    pix3 = new Pixel(20,150,200, 50);
    redMax = new Pixel(maxValue, 123, 26, 0);
    greenMax = new Pixel(123, maxValue, 26, maxValue);
    blueMax = new Pixel(26, 123, maxValue, 127);
    white = new Pixel(maxValue, maxValue, maxValue, maxValue);
    black = new Pixel(0, 0, 0, maxValue);
  }

  @Test
  public void testInvalidConstruction() {
    try {
      new Pixel(100, -20, 30, 50);
      fail("Value of green is less than minValue (0). Exception should be thrown.");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      new Pixel(323, 220, 30, 50);
      fail("Value of red is greater than maxValue (255). Exception should be thrown.");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      new Pixel(23, 220, -30, 50);
      fail("Value of blue is less than minValue (0). Exception should be thrown.");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      new Pixel(323, 220, 30, 550);
      fail("Value of alpha is greater than maxValue (255). Exception should be thrown.");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testValidConstruction() {
    try {
      new Pixel(100, 20, 0, 123);
      new Pixel(0,150,200, 0);
      new Pixel(20,0,200, 50);
      new Pixel(255, 123, 26, 0);
      new Pixel(123, 255, 26, 255);
      new Pixel(123, 26, 255, 255);
    } catch (IllegalArgumentException e) {
      fail("Reached a fail point. Should not throw an Exception.");
    }
  }

  @Test
  public void maxRGB() {
    assertEquals(100, pix.maxRGB());
    assertEquals(200, pix2.maxRGB());
    assertEquals(255, redMax.maxRGB());
    assertEquals(255, greenMax.maxRGB());
    assertEquals(255, blueMax.maxRGB());
    assertEquals(255, white.maxRGB());
    assertEquals(0, black.maxRGB());
  }

  @Test
  public void intensityValue() {
    assertEquals(40, pix.intensityValue());
    assertEquals(150, pix2.intensityValue());
    assertEquals(134, redMax.intensityValue());
    assertEquals(134, greenMax.intensityValue());
    assertEquals(134, blueMax.intensityValue());
    assertEquals(255, white.intensityValue());
    assertEquals(0, black.intensityValue());
  }

  @Test
  public void lumaValue() {
    assertEquals(36, pix.lumaValue());
    assertEquals(143, pix2.lumaValue());
    assertEquals(145, redMax.lumaValue());
    assertEquals(211, greenMax.lumaValue());
    assertEquals(112, blueMax.lumaValue());
    assertEquals(255, white.lumaValue());
    assertEquals(0, black.lumaValue());
  }

  @Test
  public void brighten() {
    assertEquals(100, pix.getR());
    assertEquals(20, pix.getG());
    assertEquals(0, pix.getB());
    assertEquals(123, pix.getA());
    IPixel tmp = pix.brighten(pix.maxRGB());
    assertEquals(200, tmp.getR());
    assertEquals(120, tmp.getG());
    assertEquals(100, tmp.getB());
    assertEquals(123, tmp.getA());

    assertEquals(20, pix3.getR());
    assertEquals(150, pix3.getG());
    assertEquals(200, pix3.getB());
    assertEquals(50, pix3.getA());
    tmp = pix3.brighten(pix3.maxRGB());
    assertEquals(220, tmp.getR());
    assertEquals(255, tmp.getG());
    assertEquals(255, tmp.getB());
    assertEquals(50, tmp.getA());

    assertEquals(255, redMax.getR());
    assertEquals(123, redMax.getG());
    assertEquals(26, redMax.getB());
    assertEquals(0, redMax.getA());
    tmp = redMax.brighten(3);
    assertEquals(255, tmp.getR());
    assertEquals(126, tmp.getG());
    assertEquals(29, tmp.getB());
    assertEquals(0, tmp.getA());
  }

  @Test
  public void darken() {
    assertEquals(100, pix.getR());
    assertEquals(20, pix.getG());
    assertEquals(0, pix.getB());
    assertEquals(123, pix.getA());
    IPixel tmp = pix.darken(pix.maxRGB());
    assertEquals(0, tmp.getR());
    assertEquals(0, tmp.getG());
    assertEquals(0, tmp.getB());
    assertEquals(123, tmp.getA());

    assertEquals(20, pix3.getR());
    assertEquals(150, pix3.getG());
    assertEquals(200, pix3.getB());
    assertEquals(50, pix3.getA());
    tmp = pix3.darken(pix3.maxRGB());
    assertEquals(0, tmp.getR());
    assertEquals(0, tmp.getG());
    assertEquals(0, tmp.getB());
    assertEquals(50, tmp.getA());

    assertEquals(255, redMax.getR());
    assertEquals(123, redMax.getG());
    assertEquals(26, redMax.getB());
    assertEquals(0, redMax.getA());
    tmp = redMax.darken(3);
    assertEquals(252, tmp.getR());
    assertEquals(120, tmp.getG());
    assertEquals(23, tmp.getB());
    assertEquals(0, tmp.getA());

    assertEquals(100, pix.getR());
    assertEquals(20, pix.getG());
    assertEquals(0, pix.getB());
    assertEquals(123, pix.getA());
    tmp = pix.darken(17);
    assertEquals(83, tmp.getR());
    assertEquals(3, tmp.getG());
    assertEquals(0, tmp.getB());
    assertEquals(123, tmp.getA());
  }

  @Test
  public void getMaxValue() {
    assertEquals(255, pix.getMaxValue());
    assertEquals(255, black.getMaxValue());
    assertEquals(255, white.getMaxValue());
  }

  @Test
  public void getR() {
    assertEquals(100, pix.getR());
    assertEquals(0, black.getR());
    assertEquals(255, white.getR());
  }

  @Test
  public void getG() {
    assertEquals(20, pix.getG());
    assertEquals(0, black.getG());
    assertEquals(255, white.getG());
  }

  @Test
  public void getB() {
    assertEquals(200, pix2.getB());
    assertEquals(0, black.getB());
    assertEquals(255, white.getB());
  }

  @Test
  public void getA() {
    assertEquals(123, pix.getA());
    assertEquals(0, redMax.getA());
    assertEquals(255, black.getA());
    assertEquals(255, white.getA());
  }
}