/**
 * Represents a Move in its most basic form. Holding a row and column coordinate.
 *
 * @author Sukirat Dhillon
 * @author Willbert Suteja
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

  @Override
  public String toString() {
    char rowLetter = (char) ('A' + row);
    int colNumber = col + 1;
    return "" + rowLetter + colNumber;
}

}
