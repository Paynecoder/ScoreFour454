/**
 * This class represents a Command Reader allowing input to be
 * read from the ConsoleView.
 *
 * @author Joshua Payne,
 *         Student Number: 230152032
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

  private boolean isValidMove(String input) {
    // Check if the input matches the pattern of a valid move (e.g., 'B2')
    // Regex!!!!
    return input.matches("^[A-Da-d][1-4]$");
  }

  private Move convertToMove(String input, GameController controller) {
    int row = input.toUpperCase().charAt(0) - 'A';
    int col = Character.getNumericValue(input.charAt(1)) - 1;
    return new Move(row, col, controller.getGame().getTurn().getPlayerColour());
  }

  public Move parseAddBeadCommand(String input, BeadColour beadColour) {
    String location = input.substring(input.indexOf("to") + 2, input.length() - 1);
    char rowChar = location.charAt(0);
    int row = Character.toUpperCase(rowChar) - 'A';
    int col = Integer.parseInt(location.substring(1)) - 1;
    return new Move(row, col, beadColour);
  }

  public int[] parseRemoveBeadCommand(String input) {
    String location = input.substring(14, input.length() - 1);
    int row = Character.toUpperCase(location.charAt(0)) - 'A';
    int col = Integer.parseInt(location.substring(1)) - 1;
    return new int[] { row, col };
  }

}