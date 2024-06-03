package model;

/**
 * This interface is for a pixel which has a red, green, blue, and alpha value.
 */
public interface IPixel {

  /**
   * Computes the maximum value of the red, green, and blue pixel components
   * and returns it.
   * @return the maximum value of the rgb components of the pixel.
   */
  int maxRGB();

  /**
   * Computes the average value of the red, green, and blue pixel components
   * and returns it.
   * @return the average value of the rgb components of the pixel.
   */
  int intensityValue();

  /**
   * Computes the luma value of the red, green, and blue pixel components
   * by applying the weighted sum formula 0.2126r + 0.7152g + 0.0722b and returns it.
   * @return the luma value found from the formula using the rgb components of the pixel.
   */
  int lumaValue();

  /**
   * Brightens the pixel by a given value.
   * Adds the brightness value to each pixel component.
   * @param value the value to be added to each pixel component.
   * @return a Pixel which is the new Pixel with the updated and increased r, g, b, and a values.
   */
  IPixel brighten(int value);

  /**
   * Darkens the pixel by a given value.
   * Removes the brightness value from each pixel component.
   * @param value the value to be subtracted from each pixel component.
   * @return a Pixel which is the new Pixel with the updated and decreased r, g, b, and a values.
   */
  IPixel darken(int value);

  /**
   * Gets the maximum value for a component of a Pixel.
   * @return an int representing the maximum value for a component of a Pixel.
   */
  int getMaxValue();

  /**
   * Gets the red component for a Pixel.
   * @return an int representing the red component for a Pixel.
   */
  int getR();

  /**
   * Gets the green component for a Pixel.
   * @return an int representing the green component for a Pixel.
   */
  int getG();

  /**
   * Gets the blue component for a Pixel.
   * @return an int representing the blue component for a Pixel.
   */
  int getB();

  /**
   * Gets the alpha component for a Pixel.
   * @return an int representing the alpha component for a Pixel.
   */
  int getA();
}
