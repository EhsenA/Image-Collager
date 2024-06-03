package model;

/**
 * This interface is for an HSLPixel which has a Hue, Saturation, Lightness, and Alpha Value.
 * This interface solely consists of observers to get H, S, L, and A values.
 */
public interface IHSLPixel {

  /**
   * Gets the Hue component for an HSLPixel.
   * @return the Hue component of an HSLPixel.
   */
  double getH();

  /**
   * Gets the Saturation component for an HSLPixel.
   * @return the Saturation component of an HSLPixel.
   */
  double getS();

  /**
   * Gets the Lightness component for an HSLPixel.
   * @return the Lightness component of an HSLPixel.
   */
  double getL();

  /**
   * Gets the Alpha component for an HSLPixel.
   * @return the Alpha component of an HSLPixel.
   */
  int getA();
}