package model.filters;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.IModel;
import model.Model;
import utils.ImageUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A FilterTest where we
 * test the filter class and all of its methods and filterOptions.
 * FilterOptions options consist of
 * The Filter package allows to
 * <li> extract the red, blue, and green components separately of the given image </li>
 * <li> brighten the given image by value, intensity, or luma </li>
 * <li> darken the given image by value, intensity, or luma </li>
 */
public class FilterTest {

  IModel model;

  @Before
  public void init() {
    model = new Model();

  }

  //uses our actual model to save the image of parrot.ppm into a new file called parrot.ppm
  //  @Test
  //  public void saveImage() {
  //    Readable in = new StringReader("new-project proj1 472 314 " +
  //            "add-layer Layer1 " + "add-image-to-layer Layer1 res\\parrot.ppm 0 0 "
  //            + "save-image parrot.ppm ");
  //    Appendable log = new StringBuilder();
  //    IModel model = new Model();
  //    IView view = new View(model, new StringBuilder());
  //    IController controller = new Controller(model, view, in);
  //    controller.runProgram();
  //  }

  @Test
  public void saveImageRed() {
    model.newProject("proj1", 472, 314);
    model.addLayer("Layer1");
    try {
      model.addImageToLayer("Layer1",
              model.createImageFromBuff(ImageUtil.aPPMtoImage("res\\parrot.ppm")),
              0,
              0);
    } catch (FileNotFoundException e) {
      fail("The inputted file was invalid.");
    }
    model.setFilter("Layer1", new RedComponent());
    try {
      ImageUtil.saveImg(model.saveImage(), "res\\parrotRed.ppm");
    } catch (IOException e) {
      fail("The inputted file was invalid.");
    }
    assertEquals("proj1", model.getCurProject().getName());
  }

  @Test
  public void saveImageBlue() {
    model.newProject("proj1", 472, 314);
    model.addLayer("Layer1");
    try {
      model.addImageToLayer("Layer1",
              model.createImageFromBuff(ImageUtil.aPPMtoImage("res\\parrot.ppm")),
              0,
              0);
    } catch (FileNotFoundException e) {
      fail("The inputted file was invalid.");
    }
    model.setFilter("Layer1", new BlueComponent());
    try {
      ImageUtil.saveImg(model.saveImage(), "res\\parrotBlue.ppm");
    } catch (IOException e) {
      fail("The inputted file was invalid.");
    }
    assertEquals("proj1", model.getCurProject().getName());
  }

  @Test
  public void saveImageGreen() {
    model.newProject("proj1", 472, 314);
    model.addLayer("Layer1");
    try {
      model.addImageToLayer("Layer1",
              model.createImageFromBuff(ImageUtil.aPPMtoImage("res\\parrot.ppm")),
              0,
              0);
    } catch (FileNotFoundException e) {
      fail("The inputted file was invalid.");
    }
    model.setFilter("Layer1", new GreenComponent());
    try {
      ImageUtil.saveImg(model.saveImage(), "res\\parrotGreen.ppm");
    } catch (IOException e) {
      fail("The inputted file was invalid.");
    }
    assertEquals("proj1", model.getCurProject().getName());
  }

  @Test
  public void saveImageBrightenValue() {
    model.newProject("proj1", 472, 314);
    model.addLayer("Layer1");
    try {
      model.addImageToLayer("Layer1",
              model.createImageFromBuff(ImageUtil.aPPMtoImage("res\\parrot.ppm")),
              0,
              0);
    } catch (FileNotFoundException e) {
      fail("The inputted file was invalid.");
    }
    model.setFilter("Layer1", new BrightenValue());
    try {
      ImageUtil.saveImg(model.saveImage(), "res\\parrotBV.ppm");
    } catch (IOException e) {
      fail("The inputted file was invalid.");
    }
    assertEquals("proj1", model.getCurProject().getName());
  }

  @Test
  public void saveImageBrightenIntensity() {
    model.newProject("proj1", 472, 314);
    model.addLayer("Layer1");
    try {
      model.addImageToLayer("Layer1",
              model.createImageFromBuff(ImageUtil.aPPMtoImage("res\\parrot.ppm")),
              0,
              0);
    } catch (FileNotFoundException e) {
      fail("The inputted file was invalid.");
    }
    model.setFilter("Layer1", new BrightenIntensity());
    try {
      ImageUtil.saveImg(model.saveImage(), "res\\parrotBI.ppm");
    } catch (IOException e) {
      fail("The inputted file was invalid.");
    }
    assertEquals("proj1", model.getCurProject().getName());
  }

  @Test
  public void saveImageBrightenLuma() {
    model.newProject("proj1", 472, 314);
    model.addLayer("Layer1");
    try {
      model.addImageToLayer("Layer1",
              model.createImageFromBuff(ImageUtil.aPPMtoImage("res\\parrot.ppm")),
              0,
              0);
    } catch (FileNotFoundException e) {
      fail("The inputted file was invalid.");
    }
    model.setFilter("Layer1", new BrightenLuma());
    try {
      ImageUtil.saveImg(model.saveImage(), "res\\parrotBL.ppm");
    } catch (IOException e) {
      fail("The inputted file was invalid.");
    }
    assertEquals("proj1", model.getCurProject().getName());
  }

  @Test
  public void saveImageDarkenValue() {
    model.newProject("proj1", 472, 314);
    model.addLayer("Layer1");
    try {
      model.addImageToLayer("Layer1",
              model.createImageFromBuff(ImageUtil.aPPMtoImage("res\\parrot.ppm")),
              0,
              0);
    } catch (FileNotFoundException e) {
      fail("The inputted file was invalid.");
    }
    model.setFilter("Layer1", new DarkenValue());
    try {
      ImageUtil.saveImg(model.saveImage(), "res\\parrotDV.ppm");
    } catch (IOException e) {
      fail("The inputted file was invalid.");
    }
    assertEquals("proj1", model.getCurProject().getName());
  }

  @Test
  public void saveImageDarkenIntensity() {
    model.newProject("proj1", 472, 314);
    model.addLayer("Layer1");
    try {
      model.addImageToLayer("Layer1",
              model.createImageFromBuff(ImageUtil.aPPMtoImage("res\\parrot.ppm")),
              0,
              0);
    } catch (FileNotFoundException e) {
      fail("The inputted file was invalid.");
    }
    model.setFilter("Layer1", new DarkenIntensity());
    try {
      ImageUtil.saveImg(model.saveImage(), "res\\parrotDI.ppm");
    } catch (IOException e) {
      fail("The inputted file was invalid.");
    }
    assertEquals("proj1", model.getCurProject().getName());
  }

  @Test
  public void saveImageDarkenLuma() {
    model.newProject("proj1", 472, 314);
    model.addLayer("Layer1");
    try {
      model.addImageToLayer("Layer1",
              model.createImageFromBuff(ImageUtil.aPPMtoImage("res\\parrot.ppm")),
              0,
              0);
    } catch (FileNotFoundException e) {
      fail("The inputted file was invalid.");
    }
    model.setFilter("Layer1", new DarkenLuma());
    try {
      ImageUtil.saveImg(model.saveImage(), "res\\parrotDL.ppm");
    } catch (IOException e) {
      fail("The inputted file was invalid.");
    }
    assertEquals("proj1", model.getCurProject().getName());
  }

  @Test
  public void saveImageDifference() {
    model.newProject("proj1", 472, 314);
    model.addLayer("Layer1");
    model.addLayer("Layer2");
    model.addLayer("Layer3");
    try {
      model.addImageToLayer("Layer1",
              model.createImageFromBuff(ImageUtil.aPPMtoImage("res\\parrot.ppm")),
              0,
              0);
    } catch (FileNotFoundException e) {
      fail("The inputted file was invalid.");
    }
    model.setFilter("Layer1", new DarkenLuma());
    try {
      ImageUtil.saveImg(model.saveImage(), "res\\parrotDL.ppm");
    } catch (IOException e) {
      fail("The inputted file was invalid.");
    }

    try {
      model.addImageToLayer("Layer2",
              model.createImageFromBuff(ImageUtil.aPPMtoImage("res\\parrot.ppm")),
              0,
              0);
    } catch (FileNotFoundException e) {
      fail("The inputted file was invalid.");
    }
    model.setFilter("Layer2", new BrightenIntensity());
    try {
      ImageUtil.saveImg(model.saveImage(), "res\\parrotDL.ppm");
    } catch (IOException e) {
      fail("The inputted file was invalid.");
    }

    try {
      model.addImageToLayer("Layer3",
              model.createImageFromBuff(ImageUtil.aPPMtoImage("res\\parrot.ppm")),
              0,
              0);
    } catch (FileNotFoundException e) {
      fail("The inputted file was invalid.");
    }
    model.setFilter("Layer3", new Difference());
    try {
      ImageUtil.saveImg(model.saveImage(), "res\\parrotDIFF.ppm");
    } catch (IOException e) {
      fail("The inputted file was invalid.");
    }
    assertEquals("proj1", model.getCurProject().getName());
  }

  @Test
  public void saveImageDarkenBlend() {
    model.newProject("proj1", 472, 314);
    model.addLayer("Layer1");
    model.addLayer("Layer2");
    model.addLayer("Layer3");
    try {
      model.addImageToLayer("Layer1",
              model.createImageFromBuff(ImageUtil.aPPMtoImage("res\\parrot.ppm")),
              0,
              0);
    } catch (FileNotFoundException e) {
      fail("The inputted file was invalid.");
    }
    model.setFilter("Layer1", new DarkenLuma());
    try {
      ImageUtil.saveImg(model.saveImage(), "res\\parrotDL.ppm");
    } catch (IOException e) {
      fail("The inputted file was invalid.");
    }

    try {
      model.addImageToLayer("Layer2",
              model.createImageFromBuff(ImageUtil.aPPMtoImage("res\\parrot.ppm")),
              0,
              0);
    } catch (FileNotFoundException e) {
      fail("The inputted file was invalid.");
    }
    model.setFilter("Layer2", new BrightenIntensity());
    try {
      ImageUtil.saveImg(model.saveImage(), "res\\parrotDL.ppm");
    } catch (IOException e) {
      fail("The inputted file was invalid.");
    }

    try {
      model.addImageToLayer("Layer3",
              model.createImageFromBuff(ImageUtil.aPPMtoImage("res\\parrot.ppm")),
              0,
              0);
    } catch (FileNotFoundException e) {
      fail("The inputted file was invalid.");
    }
    model.setFilter("Layer3", new DarkenBlend());
    try {
      ImageUtil.saveImg(model.saveImage(), "res\\parrotDB.ppm");
    } catch (IOException e) {
      fail("The inputted file was invalid.");
    }
    assertEquals("proj1", model.getCurProject().getName());
  }

  @Test
  public void saveImageBrightenBlend() {
    model.newProject("proj1", 472, 314);
    model.addLayer("Layer1");
    model.addLayer("Layer2");
    model.addLayer("Layer3");
    try {
      model.addImageToLayer("Layer1",
              model.createImageFromBuff(ImageUtil.aPPMtoImage("res\\parrot.ppm")),
              0,
              0);
    } catch (FileNotFoundException e) {
      fail("The inputted file was invalid.");
    }
    model.setFilter("Layer1", new DarkenLuma());
    try {
      ImageUtil.saveImg(model.saveImage(), "res\\parrotDL.ppm");
    } catch (IOException e) {
      fail("The inputted file was invalid.");
    }

    try {
      model.addImageToLayer("Layer2",
              model.createImageFromBuff(ImageUtil.aPPMtoImage("res\\parrot.ppm")),
              0,
              0);
    } catch (FileNotFoundException e) {
      fail("The inputted file was invalid.");
    }
    model.setFilter("Layer2", new BrightenIntensity());
    try {
      ImageUtil.saveImg(model.saveImage(), "res\\parrotDL.ppm");
    } catch (IOException e) {
      fail("The inputted file was invalid.");
    }

    try {
      model.addImageToLayer("Layer3",
              model.createImageFromBuff(ImageUtil.aPPMtoImage("res\\parrot.ppm")),
              0,
              0);
    } catch (FileNotFoundException e) {
      fail("The inputted file was invalid.");
    }
    model.setFilter("Layer3", new BrightenBlend());
    try {
      ImageUtil.saveImg(model.saveImage(), "res\\parrotBB.ppm");
    } catch (IOException e) {
      fail("The inputted file was invalid.");
    }
    assertEquals("proj1", model.getCurProject().getName());
  }

  @Test
  public void saveImagePPMRed() {
    model.newProject("proj1", 472, 314);
    model.addLayer("Layer1");
    try {
      model.addImageToLayer("Layer1",
              model.createImageFromBuff(ImageUtil.aPPMtoImage("res\\parrot.ppm")),
              0,
              0);
    } catch (FileNotFoundException e) {
      fail("The inputted file was invalid.");
    }
    model.setFilter("Layer1", new RedComponent());
    try {
      ImageUtil.saveImg(model.saveImage(), "res\\parrotPPMRed.ppm");
    } catch (IOException e) {
      fail("The inputted file was invalid.");
    }
    assertEquals("proj1", model.getCurProject().getName());
  }

  @Test
  public void saveImagePNGRed() {
    model.newProject("proj1", 472, 314);
    model.addLayer("Layer1");
    try {
      model.addImageToLayer("Layer1",
              model.createImageFromBuff(ImageUtil.aPPMtoImage("res\\parrot.ppm")),
              0,
              0);
    } catch (FileNotFoundException e) {
      fail("The inputted file was invalid.");
    }
    model.setFilter("Layer1", new RedComponent());
    try {
      ImageUtil.saveImg(model.saveImage(), "res\\parrotPNGRed.png");
    } catch (IOException e) {
      fail("The inputted file was invalid.");
    }
    assertEquals("proj1", model.getCurProject().getName());
  }

  @Test
  public void saveImageJPGRed() {
    model.newProject("proj1", 472, 314);
    model.addLayer("Layer1");
    try {
      model.addImageToLayer("Layer1",
              model.createImageFromBuff(ImageUtil.aPPMtoImage("res\\parrot.ppm")),
              0,
              0);
    } catch (FileNotFoundException e) {
      fail("The inputted file was invalid.");
    }
    model.setFilter("Layer1", new RedComponent());
    try {
      ImageUtil.saveImg(model.saveImage(), "res\\parrotJPGRed.jpg");
    } catch (IOException e) {
      fail("The inputted file was invalid.");
    }
    assertEquals("proj1", model.getCurProject().getName());
  }

  //  @Test
  //  public void saveImageBrightenValueRed() {
  //    model.newProject("proj1", 472, 314);
  //    model.addLayer("Layer1");
  //    model.addLayer("Layer2");
  //    try {
  //      model.addImageToLayer("Layer1", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //      model.addImageToLayer("Layer2", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //    } catch (FileNotFoundException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    model.setFilter("Layer1", new BrightenValue());
  //    model.setFilter("Layer2", new RedComponent());
  //    try {
  //      ImageUtil.saveImg(model.saveImage(), "res\\parrotBVR.ppm");
  //    } catch (IOException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    assertEquals("proj1", model.getCurProject().getName());
  //  }
  //
  //  @Test
  //  public void saveImageBrightenValueGreen() {
  //    model.newProject("proj1", 472, 314);
  //    model.addLayer("Layer1");
  //    model.addLayer("Layer2");
  //    try {
  //      model.addImageToLayer("Layer1", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //      model.addImageToLayer("Layer2", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //    } catch (FileNotFoundException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    model.setFilter("Layer1", new BrightenValue());
  //    model.setFilter("Layer2", new GreenComponent());
  //    try {
  //      ImageUtil.saveImg(model.saveImage(), "res\\parrotBVG.ppm");
  //    } catch (IOException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    assertEquals("proj1", model.getCurProject().getName());
  //  }
  //
  //  @Test
  //  public void saveImageBrightenValueBlue() {
  //    model.newProject("proj1", 472, 314);
  //    model.addLayer("Layer1");
  //    model.addLayer("Layer2");
  //    try {
  //      model.addImageToLayer("Layer1", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //      model.addImageToLayer("Layer2", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //    } catch (FileNotFoundException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    model.setFilter("Layer1", new BrightenValue());
  //    model.setFilter("Layer2", new BlueComponent());
  //    try {
  //      ImageUtil.saveImg(model.saveImage(), "res\\parrotBVB.ppm");
  //    } catch (IOException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    assertEquals("proj1", model.getCurProject().getName());
  //  }
  //
  //  @Test
  //  public void saveImageBrightenIntensityRed() {
  //    model.newProject("proj1", 472, 314);
  //    model.addLayer("Layer1");
  //    model.addLayer("Layer2");
  //    try {
  //      model.addImageToLayer("Layer1", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //      model.addImageToLayer("Layer2", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //    } catch (FileNotFoundException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    model.setFilter("Layer1", new BrightenIntensity());
  //    model.setFilter("Layer2", new RedComponent());
  //    try {
  //      ImageUtil.saveImg(model.saveImage(), "res\\parrotBIR.ppm");
  //    } catch (IOException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    assertEquals("proj1", model.getCurProject().getName());
  //  }
  //
  //  @Test
  //  public void saveImageBrightenIntensityGreen() {
  //    model.newProject("proj1", 472, 314);
  //    model.addLayer("Layer1");
  //    model.addLayer("Layer2");
  //    try {
  //      model.addImageToLayer("Layer1", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //      model.addImageToLayer("Layer2", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //    } catch (FileNotFoundException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    model.setFilter("Layer1", new BrightenIntensity());
  //    model.setFilter("Layer2", new GreenComponent());
  //    try {
  //      ImageUtil.saveImg(model.saveImage(), "res\\parrotBIG.ppm");
  //    } catch (IOException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    assertEquals("proj1", model.getCurProject().getName());
  //  }
  //
  //  @Test
  //  public void saveImageBrightenIntensityBlue() {
  //    model.newProject("proj1", 472, 314);
  //    model.addLayer("Layer1");
  //    model.addLayer("Layer2");
  //    try {
  //      model.addImageToLayer("Layer1", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //      model.addImageToLayer("Layer2", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //    } catch (FileNotFoundException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    model.setFilter("Layer1", new BrightenIntensity());
  //    model.setFilter("Layer2", new BlueComponent());
  //    try {
  //      ImageUtil.saveImg(model.saveImage(), "res\\parrotBIB.ppm");
  //    } catch (IOException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    assertEquals("proj1", model.getCurProject().getName());
  //  }
  //
  //  @Test
  //  public void saveImageBrightenLumaRed() {
  //    model.newProject("proj1", 472, 314);
  //    model.addLayer("Layer1");
  //    model.addLayer("Layer2");
  //    try {
  //      model.addImageToLayer("Layer1", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //      model.addImageToLayer("Layer2", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //    } catch (FileNotFoundException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    model.setFilter("Layer1", new BrightenLuma());
  //    model.setFilter("Layer2", new RedComponent());
  //    try {
  //      ImageUtil.saveImg(model.saveImage(), "res\\parrotBLR.ppm");
  //    } catch (IOException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    assertEquals("proj1", model.getCurProject().getName());
  //  }
  //
  //  @Test
  //  public void saveImageBrightenLumaGreen() {
  //    model.newProject("proj1", 472, 314);
  //    model.addLayer("Layer1");
  //    model.addLayer("Layer2");
  //    try {
  //      model.addImageToLayer("Layer1", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //      model.addImageToLayer("Layer2", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //    } catch (FileNotFoundException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    model.setFilter("Layer1", new BrightenLuma());
  //    model.setFilter("Layer2", new GreenComponent());
  //    try {
  //      ImageUtil.saveImg(model.saveImage(), "res\\parrotBLG.ppm");
  //    } catch (IOException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    assertEquals("proj1", model.getCurProject().getName());
  //  }
  //
  //  @Test
  //  public void saveImageBrightenLumaBlue() {
  //    model.newProject("proj1", 472, 314);
  //    model.addLayer("Layer1");
  //    model.addLayer("Layer2");
  //    try {
  //      model.addImageToLayer("Layer1", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //      model.addImageToLayer("Layer2", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //    } catch (FileNotFoundException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    model.setFilter("Layer1", new BrightenLuma());
  //    model.setFilter("Layer2", new BlueComponent());
  //    try {
  //      ImageUtil.saveImg(model.saveImage(), "res\\parrotBLB.ppm");
  //    } catch (IOException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    assertEquals("proj1", model.getCurProject().getName());
  //  }
  //
  //  @Test
  //  public void saveImageDarkenValueRed() {
  //    model.newProject("proj1", 472, 314);
  //    model.addLayer("Layer1");
  //    model.addLayer("Layer2");
  //    try {
  //      model.addImageToLayer("Layer1", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //      model.addImageToLayer("Layer2", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //    } catch (FileNotFoundException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    model.setFilter("Layer1", new DarkenValue());
  //    model.setFilter("Layer2", new RedComponent());
  //    try {
  //      ImageUtil.saveImg(model.saveImage(), "res\\parrotDVR.ppm");
  //    } catch (IOException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    assertEquals("proj1", model.getCurProject().getName());
  //  }
  //
  //  @Test
  //  public void saveImageDarkenValueGreen() {
  //    model.newProject("proj1", 472, 314);
  //    model.addLayer("Layer1");
  //    model.addLayer("Layer2");
  //    try {
  //      model.addImageToLayer("Layer1", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //      model.addImageToLayer("Layer2", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //    } catch (FileNotFoundException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    model.setFilter("Layer1", new DarkenValue());
  //    model.setFilter("Layer2", new GreenComponent());
  //    try {
  //      ImageUtil.saveImg(model.saveImage(), "res\\parrotDVG.ppm");
  //    } catch (IOException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    assertEquals("proj1", model.getCurProject().getName());
  //  }
  //
  //  @Test
  //  public void saveImageDarkenValueBlue() {
  //    model.newProject("proj1", 472, 314);
  //    model.addLayer("Layer1");
  //    model.addLayer("Layer2");
  //    try {
  //      model.addImageToLayer("Layer1", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //      model.addImageToLayer("Layer2", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //    } catch (FileNotFoundException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    model.setFilter("Layer1", new DarkenValue());
  //    model.setFilter("Layer2", new BlueComponent());
  //    try {
  //      ImageUtil.saveImg(model.saveImage(), "res\\parrotDVB.ppm");
  //    } catch (IOException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    assertEquals("proj1", model.getCurProject().getName());
  //  }
  //
  //  @Test
  //  public void saveImageDarkenIntensityRed() {
  //    model.newProject("proj1", 472, 314);
  //    model.addLayer("Layer1");
  //    model.addLayer("Layer2");
  //    try {
  //      model.addImageToLayer("Layer1", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //      model.addImageToLayer("Layer2", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //    } catch (FileNotFoundException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    model.setFilter("Layer1", new DarkenIntensity());
  //    model.setFilter("Layer2", new RedComponent());
  //    try {
  //      ImageUtil.saveImg(model.saveImage(), "res\\parrotDIR.ppm");
  //    } catch (IOException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    assertEquals("proj1", model.getCurProject().getName());
  //  }
  //
  //  @Test
  //  public void saveImageDarkenIntensityGreen() {
  //    model.newProject("proj1", 472, 314);
  //    model.addLayer("Layer1");
  //    model.addLayer("Layer2");
  //    try {
  //      model.addImageToLayer("Layer1", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //      model.addImageToLayer("Layer2", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //    } catch (FileNotFoundException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    model.setFilter("Layer1", new DarkenIntensity());
  //    model.setFilter("Layer2", new GreenComponent());
  //    try {
  //      ImageUtil.saveImg(model.saveImage(), "res\\parrotDIG.ppm");
  //    } catch (IOException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    assertEquals("proj1", model.getCurProject().getName());
  //  }
  //
  //  @Test
  //  public void saveImageDarkenIntensityBlue() {
  //    model.newProject("proj1", 472, 314);
  //    model.addLayer("Layer1");
  //    model.addLayer("Layer2");
  //    try {
  //      model.addImageToLayer("Layer1", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //      model.addImageToLayer("Layer2", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //    } catch (FileNotFoundException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    model.setFilter("Layer1", new DarkenIntensity());
  //    model.setFilter("Layer2", new BlueComponent());
  //    try {
  //      ImageUtil.saveImg(model.saveImage(), "res\\parrotDIB.ppm");
  //    } catch (IOException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    assertEquals("proj1", model.getCurProject().getName());
  //  }
  //
  //  @Test
  //  public void saveImageDarkenLumaRed() {
  //    model.newProject("proj1", 472, 314);
  //    model.addLayer("Layer1");
  //    model.addLayer("Layer2");
  //    try {
  //      model.addImageToLayer("Layer1", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //      model.addImageToLayer("Layer2", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //    } catch (FileNotFoundException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    model.setFilter("Layer1", new DarkenLuma());
  //    model.setFilter("Layer2", new RedComponent());
  //    try {
  //      ImageUtil.saveImg(model.saveImage(), "res\\parrotD:R.ppm");
  //    } catch (IOException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    assertEquals("proj1", model.getCurProject().getName());
  //  }
  //
  //  @Test
  //  public void saveImageDarkenLumaGreen() {
  //    model.newProject("proj1", 472, 314);
  //    model.addLayer("Layer1");
  //    model.addLayer("Layer2");
  //    try {
  //      model.addImageToLayer("Layer1", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //      model.addImageToLayer("Layer2", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //    } catch (FileNotFoundException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    model.setFilter("Layer1", new DarkenLuma());
  //    model.setFilter("Layer2", new GreenComponent());
  //    try {
  //      ImageUtil.saveImg(model.saveImage(), "res\\parrotDLG.ppm");
  //    } catch (IOException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    assertEquals("proj1", model.getCurProject().getName());
  //  }
  //
  //  @Test
  //  public void saveImageDarkenLumaBlue() {
  //    model.newProject("proj1", 472, 314);
  //    model.addLayer("Layer1");
  //    model.addLayer("Layer2");
  //    try {
  //      model.addImageToLayer("Layer1", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //      model.addImageToLayer("Layer2", ImageUtil.aPPMtoImage("res\\parrot.ppm"), 0, 0);
  //    } catch (FileNotFoundException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    model.setFilter("Layer1", new DarkenLuma());
  //    model.setFilter("Layer2", new BlueComponent());
  //    try {
  //      ImageUtil.saveImg(model.saveImage(), "res\\parrotDLB.ppm");
  //    } catch (IOException e) {
  //      fail("The inputted file was invalid.");
  //    }
  //    assertEquals("proj1", model.getCurProject().getName());
  //  }
}

