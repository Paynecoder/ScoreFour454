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

  public AI(Game game) {
    rng = new Random();
    this.game = game;
  }

  public Move getMove() {
    int row, col;
    do {
      row = rng.nextInt(4);
      col = rng.nextInt(4);
    } while (game.isSpikeFullAt(row, col));
    return new Move(row, col, game.getTurn().getPlayerColour());
  }

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
