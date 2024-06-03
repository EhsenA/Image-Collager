package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.filters.FilterOptions;

/**
 * The Project to be made.
 * This class implements the interface IProject.
 * This allows for the project to be made
 * with a name, a list of layers,
 * and the height and width of the project.
 * The Project class allows to
 * <li> add a layer to a project </li>
 * <li> add an image to a layer in a project </li>
 * <li> set a filter to the layer </li>
 * <li> save a copy of the composed image </li>
 * <li> get the list of layers for a project, name, height, and width </li>
 */
public class Project implements IProject {
  private final Map<String, ILayer> layersMap;
  private final List<ILayer> layers;

  private final String name;
  private final int height;
  private final int width;

  /**
   * Constructor for a Project.
   * Creates a {@code Project} using values initialized in the constructor.
   * @param name the name of the project.
   * @param height the height of the project.
   * @param width the width of the project.
   * @throws IllegalArgumentException if the given height or width is not positive.
   */
  public Project(String name, int height, int width) throws IllegalArgumentException {
    if (height <= 0 || width <= 0) {
      throw new IllegalArgumentException("Invalid value for height or width. " +
              "Must both be positive");
    }
    this.name = name;
    this.height = height;
    this.width = width;
    this.layersMap = new HashMap<String, ILayer>();
    this.layers = new ArrayList<>();
  }

  @Override
  public void addLayer(String name) throws IllegalArgumentException {
    if (layersMap.containsKey(name)) {
      throw new IllegalArgumentException("The provided layer name already exists.");
    }
    Layer tmp = new Layer(name, height, width);
    layersMap.put(name, tmp);
    layers.add(tmp);
  }

  @Override
  public void addImageToLayer(String name, IPixel[][] image, int x, int y)
          throws IllegalArgumentException {
    if (!layersMap.containsKey(name)) {
      throw new IllegalArgumentException("The provided layer name does not exist");
    }
    layersMap.get(name).addImage(x, y, image);
  }

  @Override
  public void setFilter(String name, FilterOptions options) throws IllegalArgumentException {
    if (!layersMap.containsKey(name)) {
      throw new IllegalArgumentException("The provided layer name does not exist");
    }
    layersMap.get(name).addFilter(options);
  }

  @Override
  public IPixel[][] saveImage() {
    Pixel[][] composed = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        composed[i][j] = new Pixel(255, 255, 255, 0); // sets background
      }
    }
    int fR;
    int fG;
    int fB;
    int a;
    int r;
    int g;
    int b;
    double fA;
    double com;
    for (ILayer l : layers) {
      IPixel[][] img = l.applyFilter(composed);
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          a = img[i][j].getA();
          r = img[i][j].getR();
          g = img[i][j].getG();
          b = img[i][j].getB();
          com = composed[i][j].getA() / 255.0 * (1 - (a / 255.0));
          fA = a / 255.0 + com;
          if (a == 255) {
            composed[i][j] = new Pixel(r, g, b, a);
          } else if (a > 0 && a < 255) {
            fR = (int) Math.ceil(((a / 255.0 * r + composed[i][j].getR() * com) * (1.0 / fA)));
            fG = (int) Math.ceil(((a / 255.0 * g + composed[i][j].getG() * com) * (1.0 / fA)));
            fB = (int) Math.ceil(((a / 255.0 * b + composed[i][j].getB() * com) * (1.0 / fA)));
            fA *= 255;
            composed[i][j] = new Pixel(fR, fG, fB, (int) (Math.ceil(fA)));
          }
        }
      }
    }
    return composed;
  }

  @Override
  public List<ILayer> getLayers() {
    return new ArrayList<ILayer>(layers);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public List<String> getLayerNames() {
    List<String> layerNames = new ArrayList<>();
    for (ILayer l : layers) {
      layerNames.add(l.getName());
    }
    return layerNames;
  }
}
