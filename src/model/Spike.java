/**
 * This class represents a Spike in a Score Four game (4x4x4 Connect Four).
 *
 * @author Joshua Payne
 * @author Gursevak Billing
 * @version 1
 */
package model;

import java.util.ArrayList;

public class Spike {
  private ArrayList<BeadColour> beads;

    /**
   * Constructs a new Spike instance with no beads on it.
   */
  public Spike() {
    beads = new ArrayList<>(4);
  }

  /**
   * Removes the top bead from the spike if there is one.
   */
  public void removeTopBead() {
    if (!isEmpty()) {
      beads.remove(height() - 1);
    }
  }

    /**
   * Clears all beads from the peg.
   */
  public void clear() {
    beads.clear();
  }

    /**
   * Adds a bead to the peg.
   * @param b The color of the bead to add.
   */
  public void addBead(BeadColour b) {
    if (!isFull()) {
      beads.add(b);
    }
  }

    /**
   * @return The height of the peg.
   */
  public int height() {
    return beads.size();
  }

    /**
   * Counts the number of black beads on the peg.
   * @return The number of black beads.
   */
  public int numberOfBlack() {
    int count = 0;
    for (BeadColour bead : beads) {
      if (bead == BeadColour.BLACK)
        count++;
    }
    return count;
  }

    /**
   * Counts the number of white beads on the peg.
   * @return The number of white beads.
   */
  public int numberOfWhite() {
    int count = 0;
    for (BeadColour bead : beads) {
      if (bead == BeadColour.WHITE)
        count++;
    }
    return count;
  }

    /**
   * Returns the color of the bead at the specified index.
   * @param i The index of the bead.
   * @return The color of the bead at the specified index.
   */
  public BeadColour beadColourAt(int i) {
    if (i < 0 || i >= height())
      return null;
    return beads.get(i);
  }

    /**
   * Checks if the peg is full.
   * @return True if the peg is full, false otherwise.
   */
  public boolean isFull() {
    return height() == 4;
  }

    /**
   * Checks if the peg is empty.
   * @return True if the peg is empty, false otherwise.
   */
  public boolean isEmpty() {
    return height() == 0;
  }

    /**
   * Checks if this peg is equal to another object.
   * @param another The object to compare with.
   * @return True if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object another) {
    if (!(another instanceof Spike))
      return false;
    Spike s = (Spike) another;
    if (this.height() != s.height())
      return false;
    for (int i = 0; i < this.height(); i++) {
      if (this.beads.get(i) != s.beads.get(i))
        return false;
    }
    return true;
  }

    /**
   * @return A string representation of the peg.
   */
  @Override
  public String toString() {
    String out = "";
    for (BeadColour bead : beads) {
      out += (bead == BeadColour.WHITE) ? "W" : "B";
    }
    return out;
  }
}
