/**
 * This class represents a Board in a Score Four game (4x4x4 Connect Four).
 *
 * @author Joshua Payne,
 *         Student Number: 230152032
 * @version 1
 */
package model;

public class Board {

  private Spike[][] board;
  private final int BOARDSIZE = 4;

  /**
   * Constructs a new Board object that has a 4x4 array of empty Spikes.
   */
  public Board() {
    board = new Spike[BOARDSIZE][BOARDSIZE];
    for (int i = 0; i < BOARDSIZE; i++) {
      for (int j = 0; j < BOARDSIZE; j++) {
        board[i][j] = new Spike();
      }
    }
  }

  /**
   * Remove the top bead from a Spike on a specified row and column.
   *
   * @param row Specified row coordinate
   * @param col Specified column coordinate
   * @return True if a bead was removed, False if otherwise.
   */
  public boolean removeTopBeadFrom(int row, int col) {
    if (row >= 0 && row < BOARDSIZE && col >= 0 && col < BOARDSIZE) {
      if (!board[row][col].isEmpty()) {
        board[row][col].removeTopBead();
        return true;
      }
    }
    return false;
  }

  /**
   * Adds a bead to the specified spike if possible.
   *
   * @param row       The row of the spike.
   * @param col       The column of the spike.
   * @param beadColor The color of the bead to add.
   * @return True if the bead was added successfully, false otherwise.
   */
  public boolean addBead(int row, int col, BeadColour beadColor) {
    if (row >= 0 && row < BOARDSIZE && col >= 0 && col < BOARDSIZE) {
      if (!board[row][col].isFull()) {
        board[row][col].addBead(beadColor);
        return true;
      }
    }
    return false;
  }

  /**
   * Clears the board by resetting all spikes.
   */
  public void clearBoard() {
    for (Spike[] boardRow : board) {
      for (Spike spike : boardRow) {
        spike.clear();
      }
    }
  }

  /**
   * Checks if the board is full.
   *
   * @return True if the board is full, false otherwise.
   */
  public boolean isFull() {
    for (Spike[] boardRow : board) {
      for (Spike spike : boardRow) {
        if (!spike.isFull()) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Returns an ASCII representation of the form of a list. Eg. A1, B1, C1, D1,
   * A2....
   *
   * @return The String of the board in ASCII list format.
   */
  public String showBoard() {
    StringBuilder boardString = new StringBuilder();
    for (int i = 0; i < BOARDSIZE; i++) {
      for (int j = 0; j < BOARDSIZE; j++) {
        boardString.append((char) ('A' + i));
        boardString.append(j + 1);
        boardString.append(":");
        boardString.append(board[i][j] == null ? " " : board[i][j].toString());
        if (!(i == BOARDSIZE - 1 && j == BOARDSIZE - 1)) {
          boardString.append("\n");
        }
      }
    }
    return boardString.toString();
  }

  /**
   * Returns the board in an ascii format but in a more interactive design.
   *
   * @return The board drawn in ascii format.
   */
  public String drawBoard() {
    StringBuilder boardDrawing = new StringBuilder();
    final int MAX_HEIGHT = 4;
    boardDrawing.append("     1   2   3   4\n");
    for (int level = MAX_HEIGHT; level > 0; level--) {
      boardDrawing.append("Level ").append(level).append("\n");
      for (int row = 0; row < BOARDSIZE; row++) {
        boardDrawing.append((char) ('A' + row)).append("   ");
        for (int col = 0; col < BOARDSIZE; col++) {
          Spike spike = board[row][col];
          if (level <= spike.height()) {
            BeadColour beadColour = spike.beadColourAt(level - 1);
            char beadChar = (beadColour == BeadColour.WHITE) ? 'W' : 'B';
            boardDrawing.append("[").append(beadChar).append("]");
          } else {
            boardDrawing.append("[ ]");
          }
          if (col < BOARDSIZE - 1) {
            boardDrawing.append(" ");
          }
        }
        boardDrawing.append("\n");
      }
      if (level > 1) {
        boardDrawing.append("\n");
      }
    }
    return boardDrawing.toString();
  }

  /**
   * Checks if there's a winning condition on the board.
   * This method evaluates all possible win conditions including verticals,
   * horizontals, front to backs, layer diagonals, and skew diagonals.
   *
   * @return true if a win condition is found, false otherwise.
   */
  public boolean checkWin() {
    return checkVerticals() || checkHorizontals() || checkFrontToBacks() ||
        checkLayerDiagonals() || checkSkewDiagonals();
  }

  /**
   * Retrieves the color of the bead at the specified position.
   *
   * @param x The x-coordinate (row) of the bead.
   * @param y The y-coordinate (column) of the bead.
   * @param z The z-coordinate (level) of the bead.
   * @return The colour of the bead at the specified position, or null if no bead
   *         is present.
   */
  private BeadColour getBeadColour(int x, int y, int z) {
    Spike spike = board[x][y];
    if (z < spike.height()) {
      return spike.beadColourAt(z);
    }
    return null;
  }

  /**
   * Checks line of beads for a winning condition based on bead colors.
   *
   * @param a First bead color in the line.
   * @param b Second bead color in the line.
   * @param c Third bead color in the line.
   * @param d Fourth bead color in the line.
   * @return true if all beads are of the same color and not null, false
   *         otherwise.
   */
  private boolean checkLine(BeadColour a, BeadColour b, BeadColour c, BeadColour d) {
    if (a == null || b == null || c == null || d == null)
      return false;
    return a == b && b == c && c == d && a != null;
  }

  /**
   * Checks for any vertical winning conditions on the board.
   * Vertical lines occur when four beads of the same color are stacked
   * on top of each other.
   *
   * @return true if a vertical win is found, false otherwise.
   */
  private boolean checkVerticals() {
    for (int x = 0; x < BOARDSIZE; x++) {
      for (int y = 0; y < BOARDSIZE; y++) {
        Spike spike = board[x][y];
        if (spike.height() >= 4) {
          if (checkLine(spike.beadColourAt(0), spike.beadColourAt(1),
              spike.beadColourAt(2), spike.beadColourAt(3))) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Checks for any horizontal winning conditions across the board.
   * Horizontal lines occur when four beads of the same color align
   * horizontally on any layer.
   *
   * @return true if a horizontal win is found, false otherwise.
   */
  private boolean checkHorizontals() {
    for (int z = 0; z < 4; z++) {
      for (int x = 0; x < BOARDSIZE - 3; x++) {
        for (int y = 0; y < BOARDSIZE; y++) {
          if (checkLine(getBeadColour(x, y, z), getBeadColour(x + 1, y, z),
              getBeadColour(x + 2, y, z), getBeadColour(x + 3, y, z))) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Checks for any front-to-back winning conditions across the board.
   * These lines occur when four beads of the same color align straight
   * from the front of the board to the back, in any column.
   *
   * @return true if a front-to-back win is found, false otherwise.
   */
  private boolean checkFrontToBacks() {
    for (int z = 0; z < 4; z++) {
      for (int y = 0; y < BOARDSIZE - 3; y++) {
        for (int x = 0; x < BOARDSIZE; x++) {
          if (checkLine(getBeadColour(x, y, z), getBeadColour(x, y + 1, z),
              getBeadColour(x, y + 2, z), getBeadColour(x, y + 3, z))) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Checks for any diagonal winning lines within layers and through layers.
   * This includes diagonals within each single layer and diagonals that span
   * across layers in a straight line.
   *
   * @return true if a diagonal win is found, false otherwise.
   */
  private boolean checkLayerDiagonals() {
    // Layer diagonals within each layer
    for (int z = 0; z < 4; z++) {
      if (checkLine(getBeadColour(0, 0, z), getBeadColour(1, 1, z),
          getBeadColour(2, 2, z), getBeadColour(3, 3, z)) ||
          checkLine(getBeadColour(0, 3, z), getBeadColour(1, 2, z),
              getBeadColour(2, 1, z), getBeadColour(3, 0, z))) {
        return true;
      }
    }
    // Diagonals through layers in the same direction
    if (checkLine(getBeadColour(0, 0, 0), getBeadColour(1, 1, 1),
        getBeadColour(2, 2, 2), getBeadColour(3, 3, 3)) ||
        checkLine(getBeadColour(0, 3, 0), getBeadColour(1, 2, 1),
            getBeadColour(2, 1, 2), getBeadColour(3, 0, 3))
        ||
        checkLine(getBeadColour(3, 0, 0), getBeadColour(2, 1, 1),
            getBeadColour(1, 2, 2), getBeadColour(0, 3, 3))
        ||
        checkLine(getBeadColour(3, 3, 0), getBeadColour(2, 2, 1),
            getBeadColour(1, 1, 2), getBeadColour(0, 0, 3))) {
      return true;
    }
    return false;
  }

  /**
   * Checks for skew-diagonal lines conditions across the board.
   * Skew diagonals are the four possible longest diagonals that traverse
   * the cube from corner to corner, cutting across layers in skewed directions.
   *
   * @return true if a skew-diagonal win is found, false otherwise.
   */
  private boolean checkSkewDiagonals() {
    return checkLine(getBeadColour(0, 0, 0), getBeadColour(1, 1, 1),
        getBeadColour(2, 2, 2), getBeadColour(3, 3, 3)) ||
        checkLine(getBeadColour(0, 3, 0), getBeadColour(1, 2, 1),
            getBeadColour(2, 1, 2), getBeadColour(3, 0, 3))
        ||
        checkLine(getBeadColour(3, 0, 0), getBeadColour(2, 1, 1),
            getBeadColour(1, 2, 2), getBeadColour(0, 3, 3))
        ||
        checkLine(getBeadColour(3, 3, 0), getBeadColour(2, 2, 1),
            getBeadColour(1, 1, 2), getBeadColour(0, 0, 3));
  }
}