package utils;

import model.HSLPixel;
import model.IHSLPixel;
import model.IPixel;
import model.Pixel;

/**
 * This class contains utility methods to convert an RGB representation
 * to HSL and back and print those results. It also contains utility methods to convert
 * an RGB image representation to HSL and back.
 */
public class RepresentationConverter {

  /**
   * Converts an RGB representation in the range 0-1 into an HSL
   * representation. It does so where
   * <ul>
   * <li> 0 &lt;= H &lt; 360</li>
   * <li> 0 &lt;= S &lt;= 1</li>
   * <li> 0 &lt;= L &lt;= 1</li>
   * </ul>
   *
   * @param r red value of the RGB between 0 and 1
   * @param g green value of the RGB between 0 and 1
   * @param b blue value of the RGB between 0 and 1
   */
  public static IHSLPixel convertRGBtoHSL(double r, double g, double b, int a) {
    double componentMax = Math.max(r, Math.max(g, b));
    double componentMin = Math.min(r, Math.min(g, b));
    double delta = componentMax - componentMin;

    double lightness = (componentMax + componentMin) / 2;
    double hue;
    double saturation;
    if (delta == 0) {
      hue = 0;
      saturation = 0;
    } else {
      saturation = delta / (1 - Math.abs(2 * lightness - 1));
      hue = 0;
      if (componentMax == r) {
        hue = (g - b) / delta;
        while (hue < 0) {
          hue += 6; //hue must be positive to find the appropriate modulus
        }
        hue = hue % 6;
      } else if (componentMax == g) {
        hue = (b - r) / delta;
        hue += 2;
      } else if (componentMax == b) {
        hue = (r - g) / delta;
        hue += 4;
      }

      hue = hue * 60;
    }
    if (saturation > 1) {
      saturation = 1;
    }
    return new HSLPixel(hue, saturation, lightness, a);
  }


  /**
   * Converts an RGB Pixel image into an HSLPixel image.
   * @param img the Pixel[][] image to be converted.
   * @return an HSLPixel image which is a 2D array of HSLPixels.
   */
  public static IHSLPixel[][] convertRGBtoHSLImage(IPixel[][] img) {
    IHSLPixel[][] hslImage = new IHSLPixel[img.length][img[0].length];
    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[0].length; j++) {
        hslImage[i][j] = convertRGBtoHSL(img[i][j].getR() / 255.0, img[i][j].getG() / 255.0,
                img[i][j].getB() / 255.0, img[i][j].getA());
      }
    }
    return hslImage;
  }

  /**
   * Converts an HSL representation. It does so where
   * <ul>
   * <li> 0 &lt;= H &lt; 360</li>
   * <li> 0 &lt;= S &lt;= 1</li>
   * <li> 0 &lt;= L &lt;= 1</li>
   * </ul>
   * into an RGB representation where each component is in the range 0-1
   *
   * @param hue        hue of the HSL representation
   * @param saturation saturation of the HSL representation
   * @param lightness  lightness of the HSL representation
   */
  public static IPixel convertHSLtoRGB(double hue, double saturation, double lightness, int alpha) {
    int r = (int) Math.ceil(convertFn(hue, saturation, lightness, 0) * 255);
    int g = (int) Math.ceil(convertFn(hue, saturation, lightness, 8) * 255);
    int b = (int) Math.ceil(convertFn(hue, saturation, lightness, 4) * 255);
    return new Pixel(r, g, b, alpha);
  }

  /**
   * Converts an HSLPixel image into an RGB Pixel image.
   * @param img the HSLPixel[][] image to be converted.
   * @return a Pixel image which is a 2D array of Pixels.
   */
  public static IPixel[][] convertHSLtoRGBImage(IHSLPixel[][] img) {
    IPixel[][] image = new Pixel[img.length][img[0].length];
    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[0].length; j++) {
        image[i][j] = convertHSLtoRGB(img[i][j].getH(), img[i][j].getS(), img[i][j].getL(),
                img[i][j].getA());
      }
    }
    return image;
  }

  /*
   * Helper method that performs the translation from the HSL polygonal
   * model to the more familiar RGB model
   */
  private static double convertFn(double hue, double saturation, double lightness, int n) {
    double k = (n + (hue / 30)) % 12;
    double a = saturation * Math.min(lightness, 1 - lightness);

    return lightness - a * Math.max(-1, Math.min(k - 3, Math.min(9 - k, 1)));
  }
}
