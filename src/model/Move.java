/**
 * Represents a Move in its most basic form. Holding a row and column coordinate.
 *
 * @author Joshua Payne,
 *         Student Number: 230152032
 * @version 1
 */
package model;

public class Move {
  private int row;
  private int col;
  private BeadColour beadColour;

  public Move(int row, int col, BeadColour colour) {
    this.row = row;
    this.col = col;
    beadColour = colour;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public BeadColour getColour() {
    return beadColour;
  }

}