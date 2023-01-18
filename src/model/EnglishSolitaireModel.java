package model;

/**
 * This class represents an implementation of the model of a variant of Marble Solitaire, which is
 * characterized by the '+' shaped board. It contains all the functionality to allow gameplay.
 */
public class EnglishSolitaireModel extends AbstractSolitaireModel {

  /**
   * A constructor for creating an EnglishSolitaireModel with a board size of 3 and the empty
   * slot at the center.
   */
  public EnglishSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * A constructor for creating an EnglishSolitaireModel with a board size of 3 and the empty
   * slot at the specified slot.
   *
   * @param sRow  the row of the empty slot
   * @param sCol  the column of the empty slot
   * @throws IllegalArgumentException if the specified slot is a part of the plus
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(3, sRow, sCol);
  }

  /**
   * A constructor for creating an EnglishSolitaireModel with the specified board size and the
   * empty slot at the middle.
   *
   * @param armThickness the armThickness of the board
   * @throws IllegalArgumentException if the armThickness is not a positive odd integer greater
   *                                  than 3
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    super(armThickness, (armThickness - 1) / 2 * 3, (armThickness - 1) / 2 * 3);
  }

  /**
   * A constructor for creating an EnglishSolitaireModel with the specified board size and the
   * empty slot at the specified slot.
   *
   * @param armThickness the armThickness of the board
   * @param sRow         the row of the empty slot
   * @param sCol         the column of the empty slot
   * @throws IllegalArgumentException if the armThickness is not a positive odd integer greater
   *                                  than 3 or if the specified invalid slot is not a part of the
   *                                  plus
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    super(armThickness, sRow, sCol);
  }

  /**
   * Determines if a spot is invalid on an EnglishSolitaireModel. Determines if a slot is in one
   * of the corners.
   *
   * @param row the row of the slot
   * @param col the column of the slot
   * @return a boolean determining whether the slot is invalid
   */
  @Override
  protected boolean isInvalidSpot(int row, int col) {
    // the slot is in one of the corners which is not a part of the '+' playing board
    return (((row <= this.armThickness - 2) || (row >= this.armThickness * 2 - 1))
            && ((col <= this.armThickness - 2) || (col >= this.armThickness * 2 - 1)));
  }
}

