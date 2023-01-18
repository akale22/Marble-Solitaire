package marblesolitaire.model;

/**
 * This class represents an implementation of the model of a variant of Marble Solitaire, which is
 * characterized by an octagon-shaped board. It contains all the functionality to allow gameplay.
 */
public class EuropeanSolitaireModel extends AbstractSolitaireModel {

  /**
   * A constructor for creating an EuropeanSolitaireModel with a board size of 3 and the empty
   * slot at the center.
   */
  public EuropeanSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * A constructor for creating an EuropeanSolitaireModel with a board size of 3 and the empty
   * slot at the specified slot.
   *
   * @param sRow  the row of the empty slot
   * @param sCol  the column of the empty slot
   * @throws IllegalArgumentException if the specified slot is not a part of the octagon
   */
  public EuropeanSolitaireModel(int sRow, int sCol) {
    super(3, sRow, sCol);
  }

  /**
   * A constructor for creating an EuropeanSolitaireModel with the specified board size and the
   * empty slot at the middle.
   *
   * @param armThickness the armThickness of the board
   * @throws IllegalArgumentException if the armThickness is not a positive odd integer greater
   *                                  than 3
   */
  public EuropeanSolitaireModel(int armThickness) {
    super(armThickness, (armThickness - 1) / 2 * 3, (armThickness - 1) / 2 * 3);
  }

  /**
   * A constructor for creating an EuropeanSolitaireModel with the specified board size and the
   * empty slot at the specified slot.
   *
   * @param armThickness the armThickness of the board
   * @param sRow         the row of the empty slot
   * @param sCol         the column of the empty slot
   * @throws IllegalArgumentException if the armThickness is not a positive odd integer greater
   *                                  than 3 or if the specified invalid slot is not a part of the
   *                                  octagon
   */
  public EuropeanSolitaireModel(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol);
  }

  /**
   * Determines if the slot is not a part of the octagon board.
   *
   * @param row the row of the slot
   * @param col the column of the slot
   * @return a boolean representing whether the slot (row and col) is invalid and not on the octagon
   */
  @Override
  protected boolean isInvalidSpot(int row, int col) {
    return
            // invalid in topLeft
            (row <= this.armThickness - 2 && (col <= this.armThickness - row - 2))
            // invalid in bottomLeft
            || (row >= this.armThickness * 2 - 1 && (col >= this.armThickness * 5 - row - 4))
            // invalid in topRight
            || (row <= this.armThickness - 2 && (col >= this.armThickness * 2 + row - 1))
            // invalid in bottomLeft
            || (row >= this.armThickness * 2 - 1 && (col <= row - 2 * this.armThickness + 1));
  }
}
