package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.filters.FilterOptions;

import model.filters.BlueComponent;
import model.filters.BrightenBlend;
import model.filters.BrightenIntensity;
import model.filters.BrightenLuma;
import model.filters.BrightenValue;
import model.filters.DarkenBlend;
import model.filters.DarkenIntensity;
import model.filters.DarkenLuma;
import model.filters.DarkenValue;
import model.filters.Difference;
import model.filters.GreenComponent;
import model.filters.Normal;
import model.filters.RedComponent;

/**
 * The Model to be made.
 * This allows for the model to be made
 * with a project.
 * The Model class allows to
 * <li> make a new project </li>
 * <li> load a project </li>
 * <li> save a project </li>
 * <li> set a filter to a given layer in a project </li>
 * <li> save the composed image of the current project </li>
 * <li> add a layer to a project </li>
 * <li> add an image to a layer in a project </li>
 * <li> get the current project </li>
 */
public class Model implements IModel {
  private IProject curProject;

  // Using default Constructor

  @Override
  public void newProject(String name, int height, int width) throws IllegalArgumentException {
    this.curProject = new Project(name, height, width);
  }

  @Override
  public void switchProject(IProject proj) {
    this.curProject = proj;
  }

  @Override
  public void setFilter(String layerName, FilterOptions opt) throws IllegalArgumentException {
    this.curProject.setFilter(layerName, opt);
  }

  @Override
  public IPixel[][] saveImage() {
    return this.curProject.saveImage();
  }

  @Override
  public void addLayer(String layerName) throws IllegalArgumentException {
    this.curProject.addLayer(layerName);
  }

  @Override
  public void addImageToLayer(String name, IPixel[][] image, int x, int y)
          throws IllegalArgumentException {
    this.curProject.addImageToLayer(name, image, x, y);
  }

  @Override
  public FilterOptions getFilterOptionsObject(String filter) throws IllegalArgumentException {
    switch (filter) {
      case "brighten-value":
        return new BrightenValue();
      case "brighten-intensity":
        return new BrightenIntensity();
      case "brighten-luma":
        return new BrightenLuma();
      case "darken-value":
        return new DarkenValue();
      case "darken-intensity":
        return new DarkenIntensity();
      case "darken-luma":
        return new DarkenLuma();
      case "red-component":
        return new RedComponent();
      case "green-component":
        return new GreenComponent();
      case "blue-component":
        return new BlueComponent();
      case "normal":
        return new Normal();
      case "difference":
        return new Difference();
      case "brighten-blend":
        return new BrightenBlend();
      case "darken-blend":
        return new DarkenBlend();
      default:
        throw new IllegalArgumentException("Invalid filter option.");
    }
  }

  @Override
  public IProject openProject(StringBuilder builder) {
    Scanner sc;

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();

    int width = sc.nextInt();
    int height = sc.nextInt();
    Project proj = new Project(token, height, width);
    int maxValue = sc.nextInt();

    Pixel[][] image = new Pixel[height][width];
    while (sc.hasNext()) {
      String layerName = sc.next();
      FilterOptions opt = new Normal();
      while (!sc.hasNextInt()) {
        switch (sc.next()) {
          case "normal":
            opt = new Normal();
            break;
          case "red-component":
            opt = new RedComponent();
            break;
          case "green-component":
            opt = new GreenComponent();
            break;
          case "blue-component":
            opt = new BlueComponent();
            break;
          case "brighten-value":
            opt = new BrightenValue();
            break;
          case "brighten-intensity":
            opt = new BrightenIntensity();
            break;
          case "brighten-luma":
            opt = new BrightenLuma();
            break;
          case "darken-value":
            opt = new DarkenValue();
            break;
          case "darken-intensity":
            opt = new DarkenIntensity();
            break;
          case "darken-luma":
            opt = new DarkenLuma();
            break;
          case "difference":
            opt = new Difference();
            break;
          default:
            //default case not needed because it should never save a filter is not a FilterOptions
        }
      }
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          Pixel temp;
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          int a = sc.nextInt();
          temp = new Pixel(r, g, b, a);
          image[i][j] = temp;
        }
      }
      proj.addLayer(layerName);
      proj.addImageToLayer(layerName, image, 0, 0);
      proj.setFilter(layerName, opt);
    }

    return proj;
  }

  @Override
  public IPixel[][] createImageFromBuff(BufferedImage buffered) {
    Pixel[][] img = new Pixel[buffered.getHeight()][buffered.getWidth()];

    //Iterating so x moves to the right and y moves down
    for (int x = 0; x < buffered.getWidth(); x++) {
      for (int y = 0; y < buffered.getHeight(); y++) {
        int argb = buffered.getRGB(x, y);
        // Taking the 4 values and creating a single number
        int a = (argb & 0xff000000) >>> 24;
        int r = (argb & 0xff0000) >>> 16;
        int g = (argb & 0xff00) >>> 8;
        int b = argb & 0xff;
        img[y][x] = new Pixel(r, g, b, a);
      }
    }
    return img;
  }

  @Override
  public Project getCurProject() {
    String name = this.curProject.getName();
    int height = this.curProject.getHeight();
    int width = this.curProject.getWidth();
    int counter = 0;

    List<ILayer> layers = this.curProject.getLayers();
    List<FilterOptions> totalFilters = new ArrayList<>();
    for (ILayer l : layers) {
      totalFilters.add(l.getFilter());
    }

    Project copy = new Project(name, height, width);
    for (ILayer l : layers) {
      String layerName = l.getName();
      copy.addLayer(layerName);
      copy.addImageToLayer(layerName, l.getImage(), 0, 0);
      FilterOptions layerFilter = totalFilters.get(counter);
      copy.setFilter(layerName, layerFilter);
      counter++;
    }

    return copy;
  }
}
