/**
 * This class represents the Console view.
 *
 * @author Swethin Panjwani
 * @version 1
 */
package view;

public class ConsoleView implements Viewable {
  /**
   * Displays the passed in message to th user, by printing to the console.
   */
  @Override
  public void displayMessage(String message) {
    System.out.println(message);
  }

}
