/**
 * Represents a Move in its most basic form. Holding a row/column coordinate and a Colour.
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

  /**
   * Constructs a new move object, with a specified row, col and colour.
   * @param row row coordinate
   * @param col column coordinate
   * @param colour Colour of bead to be placed.
   */
  public Move(int row, int col, BeadColour colour) {
    this.row = row;
    this.col = col;
    beadColour = colour;
  }

  /**
   * @return the row coordinate of the move
   */
  public int getRow() {
    return row;
  }

  /**
   * @return the column coordinate of the move.
   */
  public int getCol() {
    return col;
  }

  /**
   * @return the bead colour of the move
   */
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
