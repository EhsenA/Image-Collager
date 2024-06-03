
# Image Collager

This application will allow you to make a collage of images, each filtered in certain ways. This application features a graphical user interface. This will allow a user to interactively load and save images, projects, create layers, and apply filters for each layer.

## GUI Options
There are 20 total buttons when you first run the GUI. Each button has its own unique functionality that alters the image on the canvas.

### Buttons
The buttons shown on the GUI are:
* Quit
* New Project
* Load Project
* Save Project
* Add Layer
* Add Image to Layer
* Save Image
* Normal
* Red Component
* Green Component
* Blue Component
* Brighten Value
* Brighten Intensity
* Brighten Luma
* Darken Value
* Darken Intensity
* Darken Luma
* Difference
* Brighten Blend
* Darken Blend

## Functionality
As expressed above, each button has its own functionality. To use each button follow the directions below:

### Quit
* Simply press the Quit button and it will exit you from the application. 
* It will not save any progress and all progress made that is not saved will be lost.

### New Project
* Pressing the New Project button prompts the user to input a name, height, and width for the project.
* A name for the project must be entered in the first pop up prompt.
* The user should then press "OK".
* A positive integer value must be entered for the height of the project in the second pop up prompt. 
* The user should then press "OK".
* A positive integer value must be entered for the width of the project in the third pop up prompt.
* The user should then press "OK".
* This will open up a blank canvas with a new project with the specified height and width.

### Load Project
* Pressing the Load Project button prompts the user to select a project to open.
* The user can navigate to any directory on their computer to select the project from.
* The project selected must be of type collage.
* The user should then press "Open".
* After selecting the project, the Layers will populate with the layers of the project selected.

### Save Project
* Before pressing the Save Project button, the user must make sure there is a project to save.
* Pressing the Save Project button prompts the user to select a directory to save the project in.
* The user can navigate to any directory on their computer to save the project to.
* The user can also name the project by typing the name of their project in the Save As section of the pop up.
* The project must be saved as type collage (add ".collage" to the end of the name of the project).
* The user should then press "Save".

### Add Layer
* Before pressing the Add Layer button, the user must make sure there is a project to add a layer to.
* Pressing the Add Layer button prompts the user to enter the name of the layer to be added to the project.
* A name for the layer must be entered in the first pop up prompt.
* The user should then press "OK".
* After adding the layer, the Layers will populate with the new layer added to the project selected.
* The Layers section will now show all the layers of the project including the new layer added.

### Add Image to Layer
* Before pressing the Add Image to Layer button, the user must select the layer they would like to add an image to.
* To select the layer, the user simply needs to click on the layer name in Layers.
* Pressing the Add Image to Layer button prompts the user to select the image file from a directory on their computer.
* The user can navigate to any directory on their computer to select the image file from.
* The image selected should be of type ppm (ends in .ppm).
* The user should then press "Open".
* The user should enter the offset value in the horizontal direction with the left of the canvas being the origin in the pop up prompt.
* The user should then press "OK".
* The user should enter the offset value in the vertical direction with the top of the canvas being the origin in the next pop up prompt.
* The user should then press "OK".
* The image should then be added to the layer selected at the offset value entered.

### Save Image
* Before pressing the Add Image to Layer button, the user must make sure there is an image to save.
* Pressing the Save Image button prompts the user to select a directory to save the image in.
* The user can navigate to any directory on their computer to save the image to.
* The user can also name the image by typing the name of their image in the Save As section of the pop up.
* The image must be saved as type ppm, png, or jpg (add ".ppm", ".png", or ".jpg" to the end of the name of the image).
* The user should then press "Save".

### Normal
* Before pressing the Normal button, there must be an image to set a filter to and the user must select the layer to add the filter to.
* Pressing the Normal button returns the image back to its original self which means the filter added to it is reset.

### Red Component
* Before pressing the Red Component button, there must be an image to set a filter to and the user must select the layer to add the filter to.
* Pressing the Red Component button sets the image to a red-component of the actual image.
* This filter only uses the red portion of the RGB amd sets the green portion and blue portion to 0.

### Green Component
* Before pressing the Green Component button, there must be an image to set a filter to and the user must select the layer to add the filter to.
* Pressing the Green Component button sets the image to a red-component of the actual image.
* This filter only uses the green portion of the RGB amd sets the red portion and blue portion to 0.

### Blue Component
* Before pressing the Blue Component button, there must be an image to set a filter to and the user must select the layer to add the filter to.
* Pressing the Blue Component button sets the image to a red-component of the actual image.
* This filter only uses the blue portion of the RGB amd sets the red portion and green portion to 0.

### Brighten Value
* Before pressing the Brighten Value button, there must be an image to set a filter to and the user must select the layer to add the filter to.
* Pressing the Brighten Value button brightens the actual image by a given value.
* This filter adds the brightness value pixel by pixel according to the value from the corresponding pixel on the current layer.

### Brighten Intensity
* Before pressing the Brighten Intensity button, there must be an image to set a filter to and the user must select the layer to add the filter to.
* Pressing the Brighten Intensity button brightens the actual image by an intensity value.
* This filter adds the brightness intensity value pixel by pixel according to value from the corresponding pixel on the current layer.

### Brighten Luma
* Before pressing the Brighten Luma button, there must be an image to set a filter to and the user must select the layer to add the filter to.
* Pressing the Brighten Luma button brightens the actual image by a given luma value.
* This filter adds the brightness luma value pixel by pixel according to value from the corresponding pixel on the current layer.

### Darken Value
* Before pressing the Darken Value button, there must be an image to set a filter to and the user must select the layer to add the filter to.
* Pressing the Darken Value button darkens the actual image by a given value.
* This filter removes the brightness value pixel by pixel according to value from the corresponding pixel on the current layer.

### Darken Intensity
* Before pressing the Brighten Luma button, there must be an image to set a filter to and the user must select the layer to add the filter to.
* Pressing the Brighten Luma button darkens the actual image by a given intensity value.
* This filter removes the brightness intensity value pixel by pixel according to value from the corresponding pixel on the current layer

### Darken Luma
* Before pressing the Brighten Luma button, there must be an image to set a filter to and the user must select the layer to add the filter to.
* Pressing the Brighten Luma button darkens the actual image by a given luma value.
* This filter removes the brightness luma value pixel by pixel according to value from the corresponding pixel on the current layer.

### Difference
* Before pressing the Difference button, there must be an image to set a filter to and the user must select the layer to add the filter to.
* Pressing the Difference button inverts the colors of an image on the current layer.
* This filter option uses both an image on the layer and the composed image of the layers beneath the layer chosen.
* It then finds the absolute value of the subtraction of the red, green, and blue components to create a new Pixel with those given rgb values, while the alpha value stays untouched.

### Brighten Blend
* Before pressing the Brighten Blend button, there must be an image to set a filter to and the user must select the layer to add the filter to.
* Pressing the Brighten Blend button converts the given image on the layer and the composed image of the layers below the layer of the given image into HSLPixels, applies a formula, and returns a new Pixel with rgb values.
* This filter option uses both an image on the layer and the composed image of the layers beneath the layer chosen.

### Darken Blend
* Before pressing the Darken Blend button, there must be an image to set a filter to and the user must select the layer to add the filter to.
* Pressing the Darken Blend button converts the given image on the layer and the composed image of the layers below the layer of the given image into HSLPixels, applies a formula, and returns a new Pixel with rgb values.
* This filter option uses both an image on the layer and the composed image of the layers beneath the layer chosen.

## Layer List
There is a list of layers of the current project the user is working on. This list of layers is the name of the layers the user inputted when creating new layers or after loading in a project. These layers are in the order of the top most layer being at the bottom of the project. Therefore, the last layer in the list of Layers is the top most layer of the project. A user can select the layer they want to edit by simply clicking on the layer name in Layers.
