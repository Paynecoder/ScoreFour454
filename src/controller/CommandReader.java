/**
 * This class represents a Command Reader allowing input to be
 * read from the ConsoleView.
 *
 * @author Sukirat Dhillon
 * @version 1
 */
package controller;

import java.util.Scanner;

import model.BeadColour;
import model.Move;

public class CommandReader {
  private Scanner scanner;

  /**
   * Returns a new CommandReader object with a Scanner with console
   * input stream.
   */
  public CommandReader() {
    scanner = new Scanner(System.in);
  }

  /**
   * Reads the command input by the user and sends to the controller
   * to be handled and potentially preform an action.
   *
   * @param controller The game controller to send the message to.
   */
  public void readCommand(GameController controller) {
    System.out.print("Enter Command: ");
    String input = scanner.nextLine();
    controller.handleConsoleInput(input);
  }

    /**
   * Prompts the user to enter a colour and returns the corresponding BeadColour.
   * @return The chosen BeadColour.
   */
  public BeadColour getInteractiveColour() {
    System.out.println("Enter Colour: 'B' or 'W': ");
    String input;
    do {
      input = scanner.nextLine();
      if (!input.equalsIgnoreCase("B") && !input.equalsIgnoreCase("W")) {
        System.out.println("Invalid Colour.");
      }
    } while (!input.equalsIgnoreCase("B") && !input.equalsIgnoreCase("W"));
    return input.equalsIgnoreCase("W") ? BeadColour.WHITE : BeadColour.BLACK;
  }

    /**
   * Prompts the user to enter a move and returns the corresponding Move.
   *
   * @param controller The game controller to send the move to.
   * @return The chosen Move.
   */
  public Move readInteractiveMoveCommand(GameController controller) {
    String input;
    do {
      System.out.println("Enter Move (e.g., 'B2' (A-D, 1-4)) or 'quit.': ");
      input = scanner.nextLine();
      if (input.startsWith("quit.")) {
        controller.handleConsoleInput(input);
      }
      if (!isValidMove(input)) {
        System.out.println("Invalid move. Please enter a valid move.");
      }
    } while (!isValidMove(input));
    return convertToMove(input, controller);
  }

    /**
   * Checks if the given input is a valid move.
   *
   * @param input The input to check.
   * @return True if the input is a valid move, false otherwise.
   * @author Joshua Payne
   */
  private boolean isValidMove(String input) {
    // Check if the input matches the pattern of a valid move (e.g., 'B2')
    //Regex!
    return input.matches("^[A-Da-d][1-4]$");
  }

    /**
   * Converts the given input into a Move.
   *
   * @param input The input to convert.
   * @param controller The game controller to get the current player's colour from.
   * @return The Move corresponding to the input.
   */
  private Move convertToMove(String input, GameController controller) {
    int row = input.toUpperCase().charAt(0) - 'A';
    int col = Character.getNumericValue(input.charAt(1)) - 1;
    return new Move(row, col, controller.getGame().getTurn().getPlayerColour());
  }

    /**
   * Parses a command to add a bead to the game board in testing mode.
   *
   * @param input The command input string.
   * @param beadColour The colour of the bead to be added.
   * @return A Move object representing the add bead command.
   */
  public Move parseAddBeadCommand(String input, BeadColour beadColour) {
    String location = input.substring(input.indexOf("to") + 2, input.length() - 1);
    char rowChar = location.charAt(0);
    int row = Character.toUpperCase(rowChar) - 'A';
    int col = Integer.parseInt(location.substring(1)) - 1;
    return new Move(row, col, beadColour);
  }

    /**
   * Parses a command to remove a bead from the game board in testing mode.
   *
   * @param input The command input string.
   * @return An array of two integers representing the row and column of the bead to be removed.
   */
  public int[] parseRemoveBeadCommand(String input) {
    String location = input.substring(14, input.length() - 1);
    int row = Character.toUpperCase(location.charAt(0)) - 'A';
    int col = Integer.parseInt(location.substring(1)) - 1;
    return new int[] { row, col };
  }

}