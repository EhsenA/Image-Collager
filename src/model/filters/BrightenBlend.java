package model.filters;

import model.HSLPixel;
import model.IHSLPixel;
import model.IPixel;
import utils.RepresentationConverter;

/**
 * This class allows for a brighten-blend filter option.
 * BrightenBlend converts the given image on the layer
 * and the composed image of the layers below the layer of the given image
 * into HSLPixels.
 * The formula of (H, S, 1-((1-L)*(1-dL))) is applied
 * to get the proper H, S, and L values for the filter option
 * These values are then added to an HSLPixel
 * and then converted back into rgb values to be returned.
 * This filter option uses both an image on the layer
 * and the composed image of the layers beneath the layer chosen.
 */
public class BrightenBlend implements FilterOptions {
  @Override
  public IPixel[][] apply(IPixel[][] image, IPixel[][] composed) {
    IHSLPixel[][] hslImage = RepresentationConverter.convertRGBtoHSLImage(image);
    IHSLPixel[][] hslComp = RepresentationConverter.convertRGBtoHSLImage(composed);

    HSLPixel[][] fin = new HSLPixel[image.length][image[0].length];

    double lightness;
    for (int i = 0; i < fin.length; i++) {
      for (int j = 0; j < fin[0].length; j++) {
        lightness = 1 - (1 - hslImage[i][j].getL()) * (1 - hslComp[i][j].getL());
        fin[i][j] = new HSLPixel(hslImage[i][j].getH(), hslImage[i][j].getS(), lightness,
                hslImage[i][j].getA());
      }
    }
    return RepresentationConverter.convertHSLtoRGBImage(fin);
  }

  public String toString() {
    return "brighten-blend";
  }
}
