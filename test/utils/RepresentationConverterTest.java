package utils;

import org.junit.Before;
import org.junit.Test;

import model.HSLPixel;
import model.IHSLPixel;
import model.IPixel;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * A RepresentationConverterTest where we
 * test the RepresentationConverter class and all of its methods.
 * This class tests the utility methods to convert an RGB representation
 * to HSL and back and print those results. It also tests the utility methods to convert
 * an RGB image representation to HSL and back.
 */
public class RepresentationConverterTest {
  IPixel pix;
  IPixel pix2;
  IPixel pix3;
  IPixel redMax;
  IPixel greenMax;
  IPixel blueMax;
  IPixel white;
  IPixel black;
  IHSLPixel hslpix;
  IHSLPixel hslpix2;
  IHSLPixel hslpix3;
  IHSLPixel hslredMax;
  IHSLPixel hslgreenMax;
  IHSLPixel hslblueMax;
  IHSLPixel hslwhite;
  IHSLPixel hslblack;
  IPixel[][] image2;
  IHSLPixel[][] hslimage2;

  @Before
  public void init() {
    int maxValue = 255;
    pix = new Pixel(100, 20, 0, 123);
    pix2 = new Pixel(100, 150, 200, 50);
    pix3 = new Pixel(20, 150, 200, 50);
    redMax = new Pixel(maxValue, 123, 26, 0);
    greenMax = new Pixel(123, maxValue, 26, maxValue);
    blueMax = new Pixel(26, 123, maxValue, 127);
    white = new Pixel(maxValue, maxValue, maxValue, maxValue);
    black = new Pixel(0, 0, 0, maxValue);

    hslpix = new HSLPixel(12, 1.0, 0.196, 123);
    hslpix2 = new HSLPixel(210, 0.48, 0.588, 50);
    hslpix3 = new HSLPixel(196.67, 0.82, 0.43, 50);
    hslredMax = new HSLPixel(25.41, 1.0, 0.55, 0);
    hslgreenMax = new HSLPixel(94.58, 1.0, 0.55, 255);
    hslblueMax = new HSLPixel(214.58, 1.0, 0.55, 127);
    hslwhite = new HSLPixel(0.0, 0.0, 1.0, 255);
    hslblack = new HSLPixel(0.0, 0.0, 0.0, 255);

    image2 = new Pixel[2][4];
    image2[0][0] = pix;
    image2[0][1] = pix2;
    image2[0][2] = pix3;
    image2[0][3] = redMax;
    image2[1][0] = greenMax;
    image2[1][1] = blueMax;
    image2[1][2] = white;
    image2[1][3] = black;

    hslimage2 = new HSLPixel[2][4];
    hslimage2[0][0] = hslpix;
    hslimage2[0][1] = hslpix2;
    hslimage2[0][2] = hslpix3;
    hslimage2[0][3] = hslredMax;
    hslimage2[1][0] = hslgreenMax;
    hslimage2[1][1] = hslblueMax;
    hslimage2[1][2] = hslwhite;
    hslimage2[1][3] = hslblack;
  }

  @Test
  public void convertRGBtoHSL() {
    hslpix2 = RepresentationConverter.convertRGBtoHSL(pix.getR() / 255.0,
            pix.getG() / 255.0,
            pix.getB() / 255.0,
            pix.getA());

    assertEquals(hslpix.getH(), hslpix2.getH(), 0.1);
    assertEquals(hslpix.getS(), hslpix2.getS(), 0.1);
    assertEquals(hslpix.getL(), hslpix2.getL(), 0.1);
    assertEquals(hslpix.getA(), hslpix2.getA());

    hslpix3 = RepresentationConverter.convertRGBtoHSL(redMax.getR() / 255.0,
            redMax.getG() / 255.0,
            redMax.getB() / 255.0,
            redMax.getA());

    assertEquals(hslredMax.getH(), hslpix3.getH(), 0.1);
    assertEquals(hslredMax.getS(), hslpix3.getS(), 0.1);
    assertEquals(hslredMax.getL(), hslpix3.getL(), 0.1);
    assertEquals(hslredMax.getA(), hslpix3.getA());
  }

  @Test
  public void convertRGBtoHSLImage() {
    hslimage2 = RepresentationConverter.convertRGBtoHSLImage((Pixel[][]) image2);

    assertEquals(12.0, hslimage2[0][0].getH(), 0.1);
    assertEquals(1.0, hslimage2[0][0].getS(), 0.01);
    assertEquals(0.196, hslimage2[0][0].getL(), 0.01);
    assertEquals(123, hslimage2[0][0].getA());

    assertEquals(0.0, hslimage2[1][2].getH(), 0.1);
    assertEquals(0.0, hslimage2[1][2].getS(), 0.01);
    assertEquals(1.0, hslimage2[1][2].getL(), 0.01);
    assertEquals(255, hslimage2[1][2].getA());
  }

  @Test
  public void convertHSLtoRGB() {
    pix2 = RepresentationConverter.convertHSLtoRGB(12, 1.0, 0.196, 123);

    assertEquals(pix.getR(), pix2.getR());
    assertEquals(pix.getG(), pix2.getG());
    assertEquals(pix.getB(), pix2.getB());
    assertEquals(pix.getA(), pix2.getA());

    pix3 = RepresentationConverter.convertHSLtoRGB(25.41, 1.0, 0.55, 0);

    assertEquals(redMax.getR(), pix3.getR());
    assertEquals(redMax.getG(), pix3.getG());
    assertEquals(redMax.getB(), pix3.getB());
    assertEquals(redMax.getA(), pix3.getA());
  }

  @Test
  public void convertHSLtoRGBImage() {
    image2 = RepresentationConverter.convertHSLtoRGBImage(hslimage2);

    assertEquals(100, image2[0][0].getR());
    assertEquals(20, image2[0][0].getG());
    assertEquals(0, image2[0][0].getB());
    assertEquals(123, image2[0][0].getA());

    assertEquals(255, image2[1][2].getR());
    assertEquals(255, image2[1][2].getG());
    assertEquals(255, image2[1][2].getB());
    assertEquals(255, image2[1][2].getA());
  }
}