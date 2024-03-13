/**
 * This interface represents what a player in Score Four is capable of.
 *
 * @author Anupriya Shaju
 * @version 1
 */
package model.player;

import model.BeadColour;

public interface Playable {
  /**
   * @return The bead colour of the player.
   */
  public BeadColour getPlayerColour();
  /**
   * @return the specified name of the player.
   */
  public String getName();
}
