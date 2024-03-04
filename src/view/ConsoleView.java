/**
 * This class represents the Console view.
 *
 * @author Joshua Payne,
 *         Student Number: 230152032
 * @version 1
 */
package view;


public class ConsoleView implements Viewable {
  /**
   * Displays the passed in message by printing to the console.
   */
  @Override
  public void displayMessage(String message) {
    System.out.println(message);
  }

}
