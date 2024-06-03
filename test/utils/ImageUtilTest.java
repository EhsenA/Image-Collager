package utils;

import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.IModel;
import model.IPixel;
import model.IProject;
import model.Model;
import model.Project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A ImageUtilTest where we
 * test the ImageUtil class and all of its methods.
 * This class consists of different methods that allow the following
 * <li> convert an image to a ppm and return the image </li>
 * <li> save the given composed image </li>
 * <li> open a project </li>
 * <li> save a project and all of its contents </li>
 */
public class ImageUtilTest {
  private IProject proj1;
  private IModel model1;
  private IPixel[][] img;


  @Before
  public void init() {
    proj1 = new Project("Project1", 384, 425);
    model1 = new Model();
    img = new IPixel[231][134];
  }

  @Test
  public void readImgTestAndsaveImgTest() {
    try {
      //loading a project with a ppm image, jpg image, and png image already loaded onto the project
      this.proj1 = this.model1.openProject(ImageUtil.openProject(
              "ProjectWithPPMJPGPNGImages.collage"));
    } catch (FileNotFoundException e) {
      fail("Filename for project does not exist.");
    }

    try {
      ImageUtil.saveImg(proj1.saveImage(), "saveImgtest.ppm");
    } catch (IOException e) {
      fail("Invalid filename.");
    }

    try {
      img = this.model1.createImageFromBuff(ImageUtil.readImg(
              "saveImgtest.ppm")); //Project does not exist
    } catch (FileNotFoundException ignored) {
      fail("Filename for image does not exist.");
    }

    assertEquals(173, img[0][0].getR());
    assertEquals(179, img[0][0].getG());
    assertEquals(151, img[0][0].getB());
    assertEquals(255, img[0][0].getA());

    assertEquals(174, img[0][7].getR());
    assertEquals(180, img[0][7].getG());
    assertEquals(152, img[0][7].getB());
    assertEquals(255, img[0][7].getA());

    assertEquals(175, img[6][8].getR());
    assertEquals(181, img[6][8].getG());
    assertEquals(153, img[6][8].getB());
    assertEquals(255, img[6][8].getA());

    assertEquals(100, img[454][101].getR());
    assertEquals(91, img[454][101].getG());
    assertEquals(76, img[454][101].getB());
    assertEquals(255, img[454][101].getA());

    assertEquals(0, img[232][321].getR());
    assertEquals(42, img[232][321].getG());
    assertEquals(91, img[232][321].getB());
    assertEquals(255, img[232][321].getA());

    assertEquals(167, img[799][599].getR());
    assertEquals(106, img[799][599].getG());
    assertEquals(25, img[799][599].getB());
    assertEquals(255, img[799][599].getA());

    try {
      ImageUtil.saveImg(proj1.saveImage(), "saveImgtest.png");
    } catch (IOException e) {
      fail("Invalid filename.");
    }

    try {
      img = this.model1.createImageFromBuff(ImageUtil.readImg(
              "saveImgtest.png")); //Project does not exist
    } catch (FileNotFoundException ignored) {
      fail("Filename for image does not exist.");
    }

    assertEquals(173, img[0][0].getR());
    assertEquals(179, img[0][0].getG());
    assertEquals(151, img[0][0].getB());
    assertEquals(255, img[0][0].getA());

    assertEquals(174, img[0][7].getR());
    assertEquals(180, img[0][7].getG());
    assertEquals(152, img[0][7].getB());
    assertEquals(255, img[0][7].getA());

    assertEquals(175, img[6][8].getR());
    assertEquals(181, img[6][8].getG());
    assertEquals(153, img[6][8].getB());
    assertEquals(255, img[6][8].getA());

    assertEquals(100, img[454][101].getR());
    assertEquals(91, img[454][101].getG());
    assertEquals(76, img[454][101].getB());
    assertEquals(255, img[454][101].getA());

    assertEquals(0, img[232][321].getR());
    assertEquals(42, img[232][321].getG());
    assertEquals(91, img[232][321].getB());
    assertEquals(255, img[232][321].getA());

    assertEquals(167, img[799][599].getR());
    assertEquals(106, img[799][599].getG());
    assertEquals(25, img[799][599].getB());
    assertEquals(255, img[799][599].getA());

    try {
      ImageUtil.saveImg(proj1.saveImage(), "saveImgtest.jpg");
    } catch (IOException e) {
      fail("Invalid filename.");
    }

    try {
      img = this.model1.createImageFromBuff(ImageUtil.readImg(
              "saveImgtest.jpg")); //Project does not exist
    } catch (FileNotFoundException ignored) {
      fail("Filename for image does not exist.");
    }

    assertEquals(172, img[0][0].getR());
    assertEquals(178, img[0][0].getG());
    assertEquals(150, img[0][0].getB());
    assertEquals(255, img[0][0].getA());

    assertEquals(174, img[0][7].getR());
    assertEquals(180, img[0][7].getG());
    assertEquals(152, img[0][7].getB());
    assertEquals(255, img[0][7].getA());

    assertEquals(175, img[6][8].getR());
    assertEquals(181, img[6][8].getG());
    assertEquals(153, img[6][8].getB());
    assertEquals(255, img[6][8].getA());

    assertEquals(107, img[454][101].getR());
    assertEquals(85, img[454][101].getG());
    assertEquals(71, img[454][101].getB());
    assertEquals(255, img[454][101].getA());

    assertEquals(0, img[232][321].getR());
    assertEquals(45, img[232][321].getG());
    assertEquals(89, img[232][321].getB());
    assertEquals(255, img[232][321].getA());

    assertEquals(160, img[799][599].getR());
    assertEquals(102, img[799][599].getG());
    assertEquals(20, img[799][599].getB());
    assertEquals(255, img[799][599].getA());
  }

  @Test
  public void aPPMtoImageAndsaveImgPPMTest() {
    try {
      this.proj1 = this.model1.openProject(ImageUtil.openProject("ourtako.collage"));
    } catch (FileNotFoundException e) {
      fail("Filename for project does not exist.");
    }

    try {
      ImageUtil.saveImg(proj1.saveImage(), "saveImgtest.ppm");
    } catch (IOException e) {
      fail("Invalid filename.");
    }

    try {
      img = this.model1.createImageFromBuff(ImageUtil.aPPMtoImage(
              "saveImgtest.ppm")); //Project does not exist
    } catch (FileNotFoundException ignored) {
      fail("Filename for image does not exist.");
    }

    assertEquals(173, img[0][0].getR());
    assertEquals(179, img[0][0].getG());
    assertEquals(151, img[0][0].getB());
    assertEquals(255, img[0][0].getA());

    assertEquals(174, img[0][7].getR());
    assertEquals(180, img[0][7].getG());
    assertEquals(152, img[0][7].getB());
    assertEquals(255, img[0][7].getA());

    assertEquals(175, img[6][8].getR());
    assertEquals(181, img[6][8].getG());
    assertEquals(153, img[6][8].getB());
    assertEquals(255, img[6][8].getA());

    assertEquals(127, img[232][321].getR());
    assertEquals(104, img[232][321].getG());
    assertEquals(122, img[232][321].getB());
    assertEquals(255, img[232][321].getA());

    assertEquals(167, img[799][599].getR());
    assertEquals(106, img[799][599].getG());
    assertEquals(25, img[799][599].getB());
    assertEquals(255, img[799][599].getA());

  }

  @Test
  public void openProject() {
    try {
      proj1 = this.model1.openProject(ImageUtil.openProject("ourtako.collage"));
    } catch (FileNotFoundException e) {
      fail("Invalid filename.");
    }
    assertEquals("proj1", proj1.getName());
    assertEquals(800, proj1.getHeight());
    assertEquals(600, proj1.getWidth());

    try {
      proj1 = this.model1.openProject(ImageUtil.openProject(
              "ourtaco.collage")); //Project does not exist
      fail("Filename for project does not exist.");
    } catch (FileNotFoundException ignored) {
    }
  }

  @Test
  public void saveProj() {
    model1.newProject("diffproj", 400, 921);
    try {
      ImageUtil.saveProj("diffproj.collage", model1.getCurProject());
    } catch (IOException e) {
      fail("Invalid filename.");
    }

    try {
      model1.switchProject(this.model1.openProject(ImageUtil.openProject(
              "ourtako.collage")));
    } catch (FileNotFoundException e) {
      fail("Filename for project does not exist.");
    }

    proj1 = model1.getCurProject();
    assertEquals("proj1", proj1.getName());
    assertEquals(800, proj1.getHeight());
    assertEquals(600, proj1.getWidth());

    try {
      model1.switchProject(this.model1.openProject(ImageUtil.openProject(
              "diffproj.collage")));
    } catch (FileNotFoundException e) {
      fail("Filename for project does not exist.");
    }

    proj1 = model1.getCurProject();
    assertEquals("diffproj", proj1.getName());
    assertEquals(400, proj1.getHeight());
    assertEquals(921, proj1.getWidth());
  }

  @Test
  public void createBuffImage() {
    try {
      proj1 = this.model1.openProject(ImageUtil.openProject("ourtako.collage"));
    } catch (FileNotFoundException e) {
      fail("Filename for project does not exist.");
    }
    BufferedImage buff = ImageUtil.createBuffImage(proj1.saveImage());

    assertEquals(800, buff.getHeight());
    assertEquals(600, buff.getWidth());
    assertEquals(-5393513, buff.getRGB(0, 0));
    assertEquals(1, buff.getType());
    assertEquals(0, buff.getMinX());
    assertEquals(0, buff.getMinY());

    IPixel[][] img = model1.createImageFromBuff(buff);

    assertEquals(173, img[0][0].getR());
    assertEquals(179, img[0][0].getG());
    assertEquals(151, img[0][0].getB());
    assertEquals(255, img[0][0].getA());

    assertEquals(174, img[0][7].getR());
    assertEquals(180, img[0][7].getG());
    assertEquals(152, img[0][7].getB());
    assertEquals(255, img[0][7].getA());

    assertEquals(175, img[6][8].getR());
    assertEquals(181, img[6][8].getG());
    assertEquals(153, img[6][8].getB());
    assertEquals(255, img[6][8].getA());

    assertEquals(127, img[232][321].getR());
    assertEquals(104, img[232][321].getG());
    assertEquals(122, img[232][321].getB());
    assertEquals(255, img[232][321].getA());

    assertEquals(167, img[799][599].getR());
    assertEquals(106, img[799][599].getG());
    assertEquals(25, img[799][599].getB());
    assertEquals(255, img[799][599].getA());
  }
}