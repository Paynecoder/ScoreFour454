/**
 * This class represents an AI Player as an opponent for the Score Four Game.
 *
 * @author Anupriya Shaju
 * @version 1
 */
package model.player;
import model.BeadColour;

public class AIPlayer implements Playable {

  private BeadColour colour;
  private String name;

  /**
   * Returns a new AIPlayer object with a specified colour
   * @param colour
   */
  public AIPlayer(BeadColour colour, String name) {
    this.colour = colour;
    this.name = name;
  }

  /**
   * @return The Bead colour of the AI player.
   */
  @Override
  public BeadColour getPlayerColour() {
    return colour;
  }

  @Override
  public String getName() {
    return this.name;
  }

}
