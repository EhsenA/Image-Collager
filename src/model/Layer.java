package model;

import model.filters.FilterOptions;
import model.filters.Normal;

/**
 * The Layer to be added to a project.
 * This class implements the interface ILayer.
 * This allows for the layers to be made
 * with a name, the image on the layer, a list of filters applied to the layer,
 * and the height and width of the project.
 * The Layer class allows to
 * <li> add an image to the layer </li>
 * <li> add and apply the filter to the layer </li>
 * <li> get the name, image, and filters for a layer </li>
 */
public class Layer implements ILayer {

  private final String name;
  private final IPixel[][] image;
  private FilterOptions filter;
  private final int height;
  private final int width;

  /**
   * Constructor for a Layer.
   * Creates a {@code Layer} using values initialized in the constructor.
   * @param name the name of the layer.
   * @param height the height of the layer.
   * @param width the width of the layer.
   * @throws IllegalArgumentException if the given height or width is not positive.
   */
  public Layer(String name, int height, int width) throws IllegalArgumentException {
    if (height <= 0 || width <= 0) {
      throw new IllegalArgumentException("Invalid value for height or width. " +
              "Must both be positive");
    }
    this.height = height;
    this.width = width;
    this.image = new Pixel[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        image[i][j] = new Pixel(255, 255, 255, 0);
      }
    }
    this.name = name;
    this.filter = new Normal();
  }

  @Override
  public void addImage(int x, int y, IPixel[][] img) {
    for (int i = 0; (i + y) < Math.min(img.length + y, height); i++) {
      for (int j = 0; (j + x) < Math.min(img[0].length + x, width); j++) {
        this.image[i + y][j + x] = img[i][j];
      }
    }
  }

  @Override
  public void addFilter(FilterOptions options) {
    this.filter = options;
  }

  @Override
  public IPixel[][] applyFilter(IPixel[][] composed) {
    Pixel[][] img = this.getImage();
    return filter.apply(img, composed);
  }

  @Override
  public Pixel[][] getImage() {
    Pixel[][] img = new Pixel[this.height][this.width];
    int r;
    int g;
    int b;
    int a;
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        r = image[i][j].getR();
        g = image[i][j].getG();
        b = image[i][j].getB();
        a = image[i][j].getA();
        img[i][j] = new Pixel(r, g, b, a);
      }
    }
    return img;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public FilterOptions getFilter() {
    return this.filter;
  }
}
