
# Image Collager

This application will allow you to make a collage of images, each filtered in certain ways. This program now supports saving and reading images of different file formats like "png" and "jpg", along with "ppm". This program allows for the addition of a new project or the ability to load in a pre-existing project from the users computer to which we can add layers, images to every layer in the project, and set filters to these layers which will change the overall image. This program also allows for the image and project to be saved. This works in both the text and GUI user interfaces. All of the functionality from previous assignments works on both the text and GUI user interfaces.

## Example PPM image: 
parrot.ppm which is found in the res folder.

## Image of Class Diagram: 
CollageClassDiagram.png found in the zipped folder. The class diagram should show names of classes and interfaces, signature of methods and inheritance relationships.

## Design: 
The overall design of this assignment used MVC principles to create a loosely coupled Model, View, and Controller. The Model consisted of Model, Project, Layer, Pixel, and HSLPixel classes with their own respective interfaces describing their behavior. The View consisted of a View class and a GUIView with their own respective interfaces. The Controller consisted of a Controller and GUIController class with their own respective interface.

### Model: 

**Pixel** - The Pixel class has a red, green, blue, and alpha value to represent the RGB values of a pixel and its transparency. Each Pixel also has a min and max value to represent the upper and lower bound for the RGBA values. The IPixel Interface allows the inheriting classes to
 *  brighten(int value): brighten a pixel
 *  darken(int value): darken a pixel 
 *  maxRGB(): maximum value of the red, green, and blue pixel components  
 *  intensityValue(): average value of the red, green, and blue pixel components  
 *  lumaValue(): luma value of the red, green, and blue pixel components by applying the weighted sum formula 0.2126r + 0.7152g + 0.0722b  
 *  getR(), getG(), getB(), getA(), getMaxValue(): get the red, green, blue, alpha and maxValue for a pixel  

**HSLPixel** - The HSLPixel class has a Hue, Saturation, Lightness, and Alpha value to represent the HSL values of a HSLPixel and its transparency. The IHSLPixel Interface allows the inheriting classes to
* getH(), getS(), getL(), getA(): get the hue, saturation, lightness, and alpha values for an HSLPixel
**Layer** - The Layer class represents a Layer to be added to a project. This allows for the layers to be made with a name, the image on the layer, a list of filters applied to the layer, and the height and width of the project. The ILayer Interface allows the inheriting classes to
 * addImage(int x, int y, IPixel[][] img): add an image to the layer  
 * addFilter(FilterOptions options): add filter to the layer 
 * applyFilter(): apply filters to layer and returns an image
 * getName(), getImage(), getFilters(): get the name, image, and filters for a layer    

  
**Project** - The Project class represents a Project which has a given height and width and is comprised of multiple layers. This allows for the project to be made with a name, a list of layers, and the height and width of the project. The IProject interface allows the inheriting classes to
 * addLayer(String name): add a layer to a project 
 * addImageToLayer(String name, IPixel[][] image, int x, int y): add an image to the layer of the project 
 * setFilter(String name, FilterOptions options): set a filter to the given layer
 * saveImage(): Save a copy of the composed image  
 * getLayers(), getName(), getHeight(), getWidth(), getLayerNames(): get the list of layers for a project, name, height, width, and a list of the layer names

 **Model** - The Model class represents the overall model which consists of a current project  to be altered in the controller. This allows for the model to be made with a project. The IModel interface allows the inheriting classes to
 * newProject(String name, int height, int width): make a new project  
 * switchProject(IProject proj): load a project
 * setFilter(String layerName, FilterOptions opt): set a filter to a given layer in a project  
 * saveImage(): save the composed image of the current project  
 * addLayer(String layerName): add a layer to a project  
 * addImageToLayer(String name, IPixel[][] image, int x, int y): add an image to a layer in a project  
 * getFilterOptionsObject(String filter): Returns the appropriate FilterOptions object based on the entered string
 * openProject(StringBuilder builder): Parses a StringBuilder to create a Project
 * createBuffImage(IPixel[][] image): Converts a 2D array of pixels to a BufferedImage
 * getCurProject(): get the current project  


**Filters** - The IFilterOptions is an interface that describes the behavior for all inheriting classes (Filters). The IFilterOptions interface allows the inheriting classes to
 * apply(IPixel[][] image, IPixel[][] composed): alter the given image by changing its pixels to match the filter option and the composed image below if need be
 * toString(): return a String that describes the FilterOption  

The inheriting classes are RedComponent, GreenComponent, BlueComponent, BrightenValue, BrightenIntensity, BrightenLuma, DarkenValue, DarkenIntensity, DarkenLuma. Their apply methods perform the following: 
 * RedComponent(): Only uses the Red component of the given image
 * GreenComponent(): Only uses the Green component of the given image
 * BlueComponent(): Only uses the Blue component of the given image  
 * BrightenValue(): Brightens the pixels of the given image by max RGB Value   
 * BrightenIntensity(): Brightens the pixels of the given image by Intensity Value  
 * BrightenLuma(): Brightens the pixels of the given image by Luma Value 
 * DarkenValue(): Darkens the pixels of the given image by max RGB Value  
 * DarkenIntensity(): Darkens the pixels of the given image by Intensity Value 
 * DarkenLuma(): Darkens the pixels of the given image by Luma Value 
 * Difference(): Inverts the color of the pixels on the composite image below
 * BrightenBlend(): Brightens the pixels of the given image by multiplying the inverse of lightness with the composite image below
 * DarkenBlend(): Darkens the pixels of the given image by multipltying the lightness with the composite image below

### View:
**View** - The View class represents a View to be added to a project. This allows for the view to be made with an IModelState model and an Appendable out. The IView Interface allows the inheriting classes to
 * renderMessage(String message): viewing the menu options  
 * showOptions(): viewing the filteroptions 
 * showFilterOptions(): viewing an inputted message 

**GUIView** - The GUIView class represents a graphical View to be added to a project. The IGUIView Interface allows the inheriting classes to
 * makeVisible(String message): Makes the view visible 
 * setListener(ActionListener listener): Allows the view to take in a listener object and forward events it receives to the listener 
 * addLayerNametoList(List<String> layers): Adds a list of strings that represent layer names so that they can be displayed on the GUI 
 * getLayerSelected(): Returns the name of the currently selected layer
 * refresh(): Signal the view to redraw and refresh itself
 * getString(String message): Creates a prompt to retrieve a String from the user with the given message
 * getValue(String message): Creates a prompt to retrieve an Integer from the user with the given message
 * setImage(BufferedImage buff): Takes the given image and displays it on the image panel

### Controller:
**Controller** - The Controller class represents a Controller to be added to a project. This allows for the mutation of elements defined in the model. It also allows for the controller to be made with an IModel model, IView view, and a Scanner in. The IController Interface allows the inheriting classes to
 * runProgram(): It runs the program to create projects and alter them
 
**GUIController** - The GUIController class represents a GUI Controller to be added to a project. This allows for the mutation of elements defined in the model. It also allows for the controller to be made with an IModel model, and an IGUIView view. The IController Interface allows the inheriting classes to
* actionPerformed(ActionEvent event): Mutates the Model and View accordingly when given a user input. Interfaces Controller with Model and View


### ImageUtil:
**ImageUtil** - The ImageUtil class contains all utilities methods. THese methods deal with reading and writing to any type of file. This class contains the following utility methods
 * aPPMtoImage(String filename): convert an image to a ppm and returns a buffered image
 * saveImg(IPixel[][] img, String fileName): save the given composed image
 * openProject(String filename): open a project
 * saveProj(String fileName, IProject proj): save a project and all of its contents
 * readImg(String fileName): Reads an image file with a supported extenstion and returns a BufferedImage
 * createBuffImage(IPixel[][] image): Converts a 2D array of pixels to a BufferedImage
  
 **RepresentationConverter** - This class contains utility methods to convert an RGB representation to HSL and back and print those results. It also contains utility methods to convert an RGB image representation to HSL and back. This class contains the following utility methods 
 * convertRGBtoHSL(double r, double g, double b, int a): Returns an HSLPixel when given an r, g, b, and a value.
 * convertRGBtoHSLImage(IPixel[][] img): Returns a 2D array of HSLPixels when given a 2d array of Pixels
 * convertHSLtoRGB(double hue, double saturation, double lightness, int alpha): Returns a Pixel when given an H, S, L, and A value.
 * convertHSLtoRGBImage(IHSLPixel[][] img): Returns a 2D array of Pixels when given a 2d array of HSLPixels
### Main: 
**Main** - The Main class contains the main method of the application and runs the colllager. 

**main(String[] args)**: Main method of application which suupports different arguments
#### Arguments
* -file: Followed by a script file. Uses the text based controller and view to execute the script
* -text: Runs the program using the text based controller and view
* if no arguments are selected, the collager is run using the GUI based controller and view

## Script of Commands: 
**scriptFile.txt** found in the zipped folder. Please run the main method in the Main class and type/paste the script found in scriptFile.txt to create a new image (ppm) and project (collage).

## Citation for Source of parrot.ppm: 
The citation with the proper credentials for the ppm image we used: 
https://unsplash.com/photos/ZiLOWCARpVw
  
## Requirements/dependencies list: 
In order to compile your code, the user will need to run:
* Java 11 or higher JRE
* JUnit 4 for running the tests

## Look at the USEME.md file to run the code
* The USEME.md file can be found in the Assignment 6 directory which is also the location of the README.md.
