package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JFileChooser;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * This class opens the main window, that has different elements illustrated in
 * it. It also doubles up as all the listeners for simplicity. Such a design is
 * not recommended in general.
 */

public class GUIView extends JFrame implements IGUIView {
  private final JButton quit;
  private final JButton newProj;
  private final JButton loadProj;
  private final JButton saveProj;
  private final JButton addL;
  private final JButton addImgToL;
  private final JButton saveImg;
  private final JButton normal;
  private final JButton red;
  private final JButton blue;
  private final JButton green;
  private final JButton brightenVal;
  private final JButton brightenInt;
  private final JButton brightenLuma;
  private final JButton darkenVal;
  private final JButton darkenInt;
  private final JButton darkenLuma;
  private final JButton difference;
  private final JButton brightenBlend;
  private final JButton darkenBlend;
  private final JPanel layerPanel;
  private final ImageIcon imageIcon;
  private final JScrollPane imageScrollPane;
  private JList<String> listOfStrings;

  /**
   * This class represents a graphical view implementation for the image processor as opposed to
   * a simple text implementation from before. It constructs the GUI and all of its elements
   * regarding the buttons, the image, the layer selection list, the scroll pane, and more.
   */
  public GUIView() {
    super();
    setTitle("Collager");
    setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());

    JPanel optionPanel = new JPanel();
    optionPanel.setLayout(new GridLayout(15, 3));
    optionPanel.setPreferredSize(new Dimension(300, 700));
    this.add(optionPanel, BorderLayout.WEST);

    JPanel imagePanel = new JPanel();
    JLabel imageLabel = new JLabel();
    imagePanel.add(imageLabel);
    //imagePanel.setSize(new Dimension(1000, 1000)); -- this doesn't do anything
    imageScrollPane = new JScrollPane(imagePanel);
    imageScrollPane.setPreferredSize(new Dimension(500, 500));
    //imageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    //imageScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    //imagePanel.add(imageScrollPane);
    this.add(imageScrollPane, BorderLayout.NORTH);
    imageIcon = new ImageIcon();
    imageLabel.setIcon(imageIcon);

    //Selection lists
    layerPanel = new JPanel();
    layerPanel.setBorder(BorderFactory.createTitledBorder("Layers"));
    layerPanel.setPreferredSize(new Dimension(100, 500));
    this.add(layerPanel);

    //buttons
    quit = new JButton("Quit");
    quit.setActionCommand("Quit Button");
    optionPanel.add(quit);

    newProj = new JButton("New Project");
    newProj.setActionCommand("New Project Button");
    optionPanel.add(newProj);

    loadProj = new JButton("Load Project");
    loadProj.setActionCommand("Load Project Button");
    optionPanel.add(loadProj);

    saveProj = new JButton("Save Project");
    saveProj.setActionCommand("Save Project Button");
    optionPanel.add(saveProj);

    addL = new JButton("Add Layer");
    addL.setActionCommand("Add Layer Button");
    optionPanel.add(addL);

    addImgToL = new JButton("Add Image to Layer");
    addImgToL.setActionCommand("Add Image to Layer Button");
    optionPanel.add(addImgToL);

    saveImg = new JButton("Save Image");
    saveImg.setActionCommand("Save Image Button");
    optionPanel.add(saveImg);

    normal = new JButton("Normal");
    normal.setActionCommand("Normal Button");
    optionPanel.add(normal);

    red = new JButton("Red Component");
    red.setActionCommand("Red Component Button");
    optionPanel.add(red);

    green = new JButton("Green Component");
    green.setActionCommand("Green Component Button");
    optionPanel.add(green);

    blue = new JButton("Blue Component");
    blue.setActionCommand("Blue Component Button");
    optionPanel.add(blue);

    brightenVal = new JButton("Brighten Value");
    brightenVal.setActionCommand("Brighten Value Button");
    optionPanel.add(brightenVal);

    brightenInt = new JButton("Brighten Intensity");
    brightenInt.setActionCommand("Brighten Intensity Button");
    optionPanel.add(brightenInt);

    brightenLuma = new JButton("Brighten Luma");
    brightenLuma.setActionCommand("Brighten Luma Button");
    optionPanel.add(brightenLuma);

    darkenVal = new JButton("Darken Value");
    darkenVal.setActionCommand("Darken Value Button");
    optionPanel.add(darkenVal);

    darkenInt = new JButton("Darken Intensity");
    darkenInt.setActionCommand("Darken Intensity Button");
    optionPanel.add(darkenInt);

    darkenLuma = new JButton("Darken Luma");
    darkenLuma.setActionCommand("Darken Luma Button");
    optionPanel.add(darkenLuma);

    difference = new JButton("Difference");
    difference.setActionCommand("Difference Button");
    optionPanel.add(difference);

    brightenBlend = new JButton("Brighten Blend");
    brightenBlend.setActionCommand("Brighten Blend Button");
    optionPanel.add(brightenBlend);

    darkenBlend = new JButton("Darken Blend");
    darkenBlend.setActionCommand("Darken Blend Button");
    optionPanel.add(darkenBlend);

    this.pack();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setListener(ActionListener listener) {
    quit.addActionListener(listener);
    newProj.addActionListener(listener);
    loadProj.addActionListener(listener);
    saveProj.addActionListener(listener);
    addL.addActionListener(listener);
    addImgToL.addActionListener(listener);
    saveImg.addActionListener(listener);
    normal.addActionListener(listener);
    red.addActionListener(listener);
    green.addActionListener(listener);
    blue.addActionListener(listener);
    brightenVal.addActionListener(listener);
    brightenInt.addActionListener(listener);
    brightenLuma.addActionListener(listener);
    darkenVal.addActionListener(listener);
    darkenInt.addActionListener(listener);
    darkenLuma.addActionListener(listener);
    difference.addActionListener(listener);
    brightenBlend.addActionListener(listener);
    darkenBlend.addActionListener(listener);
  }

  @Override
  public void addLayerNametoList(List<String> layers) {
    DefaultListModel<String> dataForListOfStrings = new DefaultListModel<>();
    for (String l : layers) {
      dataForListOfStrings.addElement(l);
    }
    listOfStrings = new JList<>(dataForListOfStrings);
    listOfStrings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    layerPanel.removeAll();
    layerPanel.add(listOfStrings);
    this.refresh();
  }

  @Override
  public String getLayerSelected() {
    return this.listOfStrings.getSelectedValue();
  }

  @Override
  public void refresh() {
    imageScrollPane.revalidate();
    imageScrollPane.repaint();
    this.revalidate();
    this.repaint();
  }

  @Override
  public String getString(String message) {
    String str = JOptionPane.showInputDialog(null, message,
            "Input", JOptionPane.QUESTION_MESSAGE);
    if (str == null) {
      this.renderMessage("No input, operation cancelled");
      return null;
    }
    return str;
  }

  @Override
  public String getFilePath() {
    JFileChooser chooser = new JFileChooser();
    chooser.showOpenDialog(new JDialog());
    File file = chooser.getSelectedFile();
    String fullPath = file.getAbsolutePath();
    return fullPath;
  }

  @Override
  public String getDirectory() {
    JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    chooser.showSaveDialog(new JDialog());
    File file = chooser.getSelectedFile();
    String fullPath = file.getAbsolutePath();
    return fullPath;
  }

  @Override
  public Integer getValue(String message) {
    String asString = JOptionPane.showInputDialog(null, message,
            "Input", JOptionPane.QUESTION_MESSAGE);
    if (asString == null) {
      this.renderMessage("No input, operation cancelled");
      return null;
    }
    Integer intVal = Integer.parseInt(asString);
    return intVal;
  }

  @Override
  public void setImage(BufferedImage buff) {
    imageIcon.setImage(buff);
    System.out.println(buff.getHeight());
    System.out.println(buff.getWidth());
    this.refresh();
  }

  @Override
  public void renderMessage(String message) {
    JOptionPane.showMessageDialog(null, message);
  }

  @Override
  public void showOptions() throws IOException {
    showFilterOptions();
  }

  @Override
  public void showFilterOptions() throws IOException {
    return;
  }
}
