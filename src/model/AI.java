/**
 * This class represents the AI subsystem utilized by the AI Player
 * and Human Player to recommend moves.
 *
 * @author Gursevak Billing
 * @version 1
 */
package model;

import java.util.Random;


public class AI {

  private Random rng;
  private Game game;

  /**
   * Constructs a new AI object.
   * @param game the current game to base the moves off of.
   */
  public AI(Game game) {
    rng = new Random();
    this.game = game;
  }

  /**
   * @return A move for the AI to play when in interactive mode.
   */
  public Move getMove() {
    int row, col;
    do {
      row = rng.nextInt(4);
      col = rng.nextInt(4);
    } while (game.isSpikeFullAt(row, col));
    return new Move(row, col, game.getTurn().getPlayerColour());
  }

  /**
   * @return Returns a recommended move for the player with black beads.
   * In a human readable format like (A1).
   */
  public String recommendBlackMove() {
    int row, col;
    char rowChar = 'A';
    do {
      row = rng.nextInt(4);
      col = rng.nextInt(4);
      switch (row) {
        case 0:
          rowChar = 'A';
          break;
        case 1:
          rowChar = 'B';
          break;
        case 2:
          rowChar = 'C';
          break;
        case 3:
          rowChar = 'D';
          break;
      }
    } while (game.isSpikeFullAt(row, col));
    return "" + rowChar + (col + 1) + ".";
  }

    /**
   * @return Returns a recommended move for the player with white beads.
   * In a human readable format like (A1).
   */
  public String recommendWhiteMove() {
    int row, col;
    char rowChar = 'A';
    do {
      row = rng.nextInt(4);
      col = rng.nextInt(4);
      switch (row) {
        case 0:
          rowChar = 'A';
          break;
        case 1:
          rowChar = 'B';
          break;
        case 2:
          rowChar = 'C';
          break;
        case 3:
          rowChar = 'D';
          break;
      }
    } while (game.isSpikeFullAt(row, col));
    return "" + rowChar + (col + 1) + ".";
  }

}
