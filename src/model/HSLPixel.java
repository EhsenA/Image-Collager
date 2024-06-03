package model;

/**
 * The HSLPixel to be used to construct an image.
 * This allows for the pixels to be made
 * with the hue component, saturation component, lightness component, and alpha values
 * The HSLPixel class allows to
 * <li> represent and construct an HSL pixel with proper HSL values </li>
 * <li> get the hue, saturation, lightness, and alpha an HSLPixel </li>
 */
public class HSLPixel implements IHSLPixel {
  private double h;
  private double s;
  private double l;
  private int a;

  /**
   * Constructor for an HSLPixel.
   * Creates a {@code HSLPixel} using values initialized in the constructor.
   * @param hue the red component of the pixel.
   * @param saturation the green component of the pixel.
   * @param lightness the blue component of the pixel.
   * @throws IllegalArgumentException if the given hue, saturation, or lightness value
   *     is not between the minValue(0) and maxValue(360).
   */
  public HSLPixel(double hue, double saturation, double lightness, int alpha)
          throws IllegalArgumentException {
    int maxValue = 360;
    int minValue = 0;
    if (hue >= maxValue || saturation > 1 || lightness > 1 || alpha > 255 || hue < minValue
            || saturation < minValue || lightness < minValue || alpha < 0) {
      throw new IllegalArgumentException("h,s,l values must be between minValue(0)" +
              " and maxValue(360)");
    }
    h = hue;
    s = saturation;
    l = lightness;
    a = alpha;
  }

  public double getH() {
    return h;
  }

  public double getS() {
    return s;
  }

  public double getL() {
    return l;
  }

  public int getA() {
    return a;
  }

}
