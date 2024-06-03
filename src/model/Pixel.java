package model;

/**
 * The Pixel to be used to construct an image.
 * This class implements the interface IPixel.
 * This allows for the pixels to be made
 * with the red component, green component, blue component, and alpha values
 * The Pixel class allows to
 * <li> brighten or darken a pixel </li>
 * <li> maximum value of the red, green, and blue pixel components </li>
 * <li> average value of the red, green, and blue pixel components </li>
 * <li> luma value of the red, green, and blue pixel components
 * by applying the weighted sum formula 0.2126r + 0.7152g + 0.0722b </li>
 * <li> get the red, green, blue, alpha and maxValue for a pixel </li>
 */
public class Pixel implements IPixel {
  private int r;
  private int g;
  private int b;
  private int a;
  private final int maxValue;
  private final int minValue;

  /**
   * Constructor for a Pixel.
   * Creates a {@code Pixel} using values initialized in the constructor.
   * @param red the red component of the pixel.
   * @param green the green component of the pixel.
   * @param blue the blue component of the pixel.
   * @param alpha the alpha component of the pixel.
   * @throws IllegalArgumentException if the given red, green, blue, or alpha value
   *     is not between the minValue(0) and maxValue(255).
   */
  public Pixel(int red, int green, int blue, int alpha) throws IllegalArgumentException {
    maxValue = 255;
    minValue = 0;
    if (red > maxValue || green > maxValue || blue > maxValue || alpha > maxValue ||
            red < minValue || green < minValue || blue < minValue || alpha < minValue) {
      throw new IllegalArgumentException("r,g,b,a values must be between minValue(0)" +
              " and maxValue(255)");
    }
    r = red;
    g = green;
    b = blue;
    a = alpha;
  }

  @Override
  public int maxRGB() {
    return (int) (Math.max(r, Math.max(g, b))); //finds max of r, g, and b
  }

  @Override
  public int intensityValue() {
    return (int) ((r + g + b) / 3);
  }

  @Override
  public int lumaValue() {
    return (int) Math.ceil((.2126 * r) + (.7152 * g) + (.0722 * b));
  }

  @Override
  public Pixel brighten(int value) {
    Pixel temp;
    int red = Math.min((this.r + value), maxValue);
    int green = Math.min((this.g + value), maxValue);
    int blue = Math.min((this.b + value), maxValue);
    temp = new Pixel(red, green, blue, this.a);
    return temp;
  }

  @Override
  public Pixel darken(int value) {
    Pixel temp;
    int red = Math.max((this.r - value), minValue);
    int green = Math.max((this.g - value), minValue);
    int blue = Math.max((this.b - value), minValue);
    temp = new Pixel(red, green, blue, this.a);
    return temp;
  }

  @Override
  public int getMaxValue() {
    return maxValue;
  }

  @Override
  public int getR() {
    return r;
  }

  @Override
  public int getG() {
    return g;
  }

  @Override
  public int getB() {
    return b;
  }

  @Override
  public int getA() {
    return a;
  }
}
