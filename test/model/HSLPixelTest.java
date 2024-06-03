package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A HSLPixelTest where we
 * test the HSLPixel class and all of its methods.
 * The HSLPixel class consists of a constructor and observers.
 * The HSLPixel class allows to
 * <li> get the Hue value </li>
 * <li> get the Saturation value </li>
 * <li> get the Lightness value </li>
 * <li> get the Alpha value </li>
 */
public class HSLPixelTest {

  IHSLPixel pix;
  IHSLPixel pix2;

  @Before
  public void init() {
    pix = new HSLPixel(35.74, .218, .923, 252);
    pix2 = new HSLPixel(274.152, .529, .426, 174);
  }

  @Test
  public void validConstruction() {
    try {
      pix = new HSLPixel(359.99, .25, .98, 1);
      pix = new HSLPixel(217.32, .82819, 1, 0);
      pix = new HSLPixel(0, .0, .0, 89);
      pix = new HSLPixel(280, 1, .2023, 255);
    } catch (IllegalArgumentException e) {
      fail("Valid Construction. Should not throw exception.");
    }
  }

  @Test
  public void invalidConstruction() {
    try {
      pix = new HSLPixel(360.0, .25, .47, 1);
      fail("Invalid Construction. Should throw exception.");
    } catch (IllegalArgumentException ignored) {

    }
    try {
      pix = new HSLPixel(-12.4, .25, .47, 1);
      fail("Invalid Construction. Should throw exception.");
    } catch (IllegalArgumentException ignored) {

    }
    try {
      pix = new HSLPixel(156.312, -.2, .98, 213);
      fail("Invalid Construction. Should throw exception.");
    } catch (IllegalArgumentException ignored) {

    }
    try {
      pix = new HSLPixel(256.312, 1.04, .98, 87);
      fail("Invalid Construction. Should throw exception.");
    } catch (IllegalArgumentException ignored) {

    }
    try {
      pix = new HSLPixel(56.312, .25, -.21, 165);
      fail("Invalid Construction. Should throw exception.");
    } catch (IllegalArgumentException ignored) {

    }
    try {
      pix = new HSLPixel(156.312, .8213, 1.98, 254);
      fail("Invalid Construction. Should throw exception.");
    } catch (IllegalArgumentException ignored) {

    }
    try {
      pix = new HSLPixel(156.312, .8213, .65, -123);
      fail("Invalid Construction. Should throw exception.");
    } catch (IllegalArgumentException ignored) {

    }
    try {
      pix = new HSLPixel(156.312, .8213, .8721, 289);
      fail("Invalid Construction. Should throw exception.");
    } catch (IllegalArgumentException ignored) {

    }
    try {
      pix = new HSLPixel(156.312, 1.8213, 1.98, 289);
      fail("Invalid Construction. Should throw exception.");
    } catch (IllegalArgumentException ignored) {

    }
  }

  @Test
  public void getH() {
    assertEquals(35.74, pix.getH(), .00001);
    assertEquals(274.152, pix2.getH(), .00001);

  }

  @Test
  public void getS() {
    assertEquals(.218, pix.getS(), .00001);
    assertEquals(.529, pix2.getS(), .00001);
  }

  @Test
  public void getL() {
    assertEquals(.923, pix.getL(), .00001);
    assertEquals(.426, pix2.getL(), .00001);
  }

  @Test
  public void getA() {
    assertEquals(252, pix.getA());
    assertEquals(174, pix2.getA());
  }
}