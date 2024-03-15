# UNBC CPSC101 Score Four Project 2024

## Compiling and Running

From the scorefour454 directory:

To Compile

`javac src/*.java`

You can then run the game using:

`java bin/Main.class`

## Team Members

The team for this project consisted of:

- Myself, Joshua Payne
- Gursevak Billing
- Anupriya Shaju
- Sukirat Singh Dhillon
- Wilbert Suteja
- Swethin Panjwani

## Code Organization

This project follows the Model-View-Controller (MVC) design paradigm, which separates the application into three interconnected components.

- **Model**: The Model package corresponds to all the data-related logic that the user works with. This can represent either the data that is being transferred between the View and Controller components or any other business-related data. In our project, the Model classes can be found in the `src/model/` directory.

- **View**: The View package is used for all the UI logic of the application. For our project, the View classes are located in the `src/view/` directory.

- **Controller**: The controller package act as an intermediary between Model and View components to process all the game logic and incoming command requests, manipulate data using the Model component, and interact with the View to render the final output. In our project, the Controller classes are located in the `src/controller/`.

