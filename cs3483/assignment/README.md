# CS3483 Assignment

In the assignment, you are required to use p5.js and ml5.js to develop an interface for viewing and interacting with an image. These actions are performed by using face detection and keyboard operations.

## Requirements

1. Initial Setup
Create a display window. Load and display an image in the window. An instant view of the camera should be displayed next to the image and has the same size as the image.

2. Face detection
A rectangle should be drawn on the camera view to indicate the position and size of the detected face. When you move your face in front of the camera, the rectangle should keep following your face. A corresponding position indicator should also be drawn on the image to follow the movement of your face.

3. Viewing the image
When the ‘v’ key is pressed and released, the interface should enter the view image mode. In this mode, a plain background with a color of your choice should be displayed. When your face is moving in the camera view, a rectangular area around the position indicator on the image should display the corresponding region from the image. When the position indicator moves away, the background should be restored.

4. Modifying the image
When the ‘m’ key is pressed and released, the interface should enter the modify image mode. In this mode, the original image should be displayed. When your face is moving in the camera view, a small circle should be drawn at the position indicator on the image. The circle should be filled with the pixel color at the location of the position indicator. When the position indicator moves away, the small circle should be retained. You may use random values for the diameter of the circle and the degree of opacity of the color in the circle.

5. Finding the faces
When the ‘f’ key is pressed and released, the interface should enter the find faces mode. In this mode, the original image should be displayed. When your face is moving across the camera view and the corresponding position indicator is close to a face on the image, the face on the image should be displayed at a slightly larger size at the same position. When the position indicator moves away, the original size of the face should be restored.

6. Exiting the view image/modify image/find faces mode
When the ‘e’ key is pressed and released, the interface should exit from the view image/modify image/find faces mode, and the original image should be re-displayed.

## Marking scheme
#### Program (40%)
You should submit your p5.js program and the associated image file.

#### Report (60%)
You should summarize your work in the form of a report which should include:
1. A description of the design of the different sections of your program.
2. Screen captures of the output.
3. Limitations and possible improvements of the program.