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
   * @param controller The game controller to send the message to.
   */
  public void readCommand(GameController controller) {
    System.out.print("Enter Command: ");
    String input = scanner.nextLine();
    controller.handleConsoleInput(input);
  }
}
