package view;

import javax.swing.JFrame;

public class GameFrame extends JFrame implements Viewable {

  /**
   * Creates a new GameFrame object allowing the user to play the
   * game in a GUI environment.
   */
  public GameFrame() {
    super("ScoreFour454");
    setSize(1280, 720);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    //hacky way to pop screen on top of all other windows when opened
    setAlwaysOnTop(true);
    setVisible(true);
    setAlwaysOnTop(false);
    setLocationRelativeTo(null);
  }

  /**
   * Displays the passed in message by printing to the console.
   */
  @Override
  public void displayMessage(String message) {
  System.out.println(message);
  }

}
