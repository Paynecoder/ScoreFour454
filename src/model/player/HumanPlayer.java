/**
 * This class represents a Human player in the game of Score Four.
 *
 * @author Anupriya Shaju
 * @version 1
 */
package model.player;

import model.BeadColour;

public class HumanPlayer implements Playable {

  private BeadColour colour;
  private String name;

  /**
   * Returns a new Human Player objcet with a specified Bead Colour.
   *
   * @param colour
   */
  public HumanPlayer(BeadColour colour, String name) {
    this.colour = colour;
    this.name = name;
  }

  /**
   * @return The bead colour of the Human player.
   */
  @Override
  public BeadColour getPlayerColour() {
    return colour;
  }

  @Override
  public String getName() {
    return name;
  }

}
