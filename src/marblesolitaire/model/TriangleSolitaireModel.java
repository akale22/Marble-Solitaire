package marblesolitaire.model;

/**
 * This class represents an implementation of the model of a variant of Marble Solitaire, which is
 * characterized by a triangle-shaped board. It contains all the functionality to allow gameplay.
 */
public class TriangleSolitaireModel extends AbstractSolitaireModel {

  /**
   * A constructor for creating an TriangleSolitaireModel with a board size of 5 and the empty
   * slot at the top.
   */
  public TriangleSolitaireModel() {
    super(5, 0, 0);
  }

  /**
   * A constructor for creating an TriangleSolitaireModel with a board size of 5 and the empty
   * slot at the specified slot.
   *
   * @param sRow  the row of the empty slot
   * @param sCol  the column of the empty slot
   * @throws IllegalArgumentException if the specified slot is not a part of the triangle
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(5, sRow, sCol);
  }

  /**
   * A constructor for creating an TriangleSolitaireModel with the specified board size and the
   * empty slot at the top.
   *
   * @param dimensions the dimensions of the board
   * @throws IllegalArgumentException if the armThickness is not a positive integer
   */
  public TriangleSolitaireModel(int dimensions) throws IllegalArgumentException {
    super(dimensions, 0, 0);
  }

  /**
   * A constructor for creating an TriangleSolitaireModel with the specified board size and the
   * empty slot at the specified slot.
   *
   * @param dimensions   the dimensions of the board
   * @param sRow         the row of the empty slot
   * @param sCol         the column of the empty slot
   * @throws IllegalArgumentException if the armThickness is not a positive integer or if the
   *                                  invalid spot is not a part of the triangle
   */
  public TriangleSolitaireModel(int dimensions, int sRow, int sCol)
          throws IllegalArgumentException {
    super(dimensions, sRow, sCol);
  }

  /**
   * Checking to ensure that the armThickness for a triangle solitaire model is a positive integer.
   *
   * @param armThickness the desired number of marbles in the last row of the triangle model.
   * @throws IllegalArgumentException if the armThickness is not a positive integer
   */
  @Override
  protected void checkArmThickness(int armThickness) throws IllegalArgumentException {
    if (armThickness <= 0) {
      throw new IllegalArgumentException("Arm thickness of a triangle solitaire model must be "
              + "greater than 0!");
    }
  }

  /**
   * Determines if the spot is an invalid spot on the triangle board (not in the triangle).
   *
   * @param row the row of the slot
   * @param col the column of the slot
   * @return whether the slot is not in the triangle part of the board
   */
  @Override
  protected boolean isInvalidSpot(int row, int col) {
    return col > row;
  }

  /**
   * Determines if this is a valid move for a triangle board. A move can now also be diagonal
   * along the topLeft bottomRight diagonal when thinking about the 2D representation of this
   * triangle board.
   *
   * @param fromRow the row of the from slot in the move
   * @param fromCol the column of the from slot in the move
   * @param toRow   the row of the to slot in the move
   * @param toCol   the column of the to slot in the move
   * @return a boolean determining whether this move is valid for a triangle board.
   */
  @Override
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    // checking if there is a horizontal and vertical move and then also checking if there is a
    // diagonal move
    return super.isValidMove(fromRow, fromCol, toRow, toCol)
            || this.isValidDiagonalMove(fromRow, fromCol, toRow, toCol);
  }

  /**
   * Determines if the parameters represent a valid diagonal move for the triangle board.
   *
   * @param fromRow the row of the from slot in the move
   * @param fromCol the column of the from slot in the move
   * @param toRow   the row of the to slot in the move
   * @param toCol   the column of the to slot in the move
   * @return a boolean determining whether this move is a valid diagonal move for a triangle board.
   */
  private boolean isValidDiagonalMove(int fromRow, int fromCol, int toRow, int toCol) {
    // ensuring that the marble conditions are met for a diagonal move again
    if (!(this.board[fromRow][fromCol] == SlotState.Marble
            && this.board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == SlotState.Marble
            && this.board[toRow][toCol] == SlotState.Empty)) {
      return false;
    }
    else {
      // checking for a diagonal move
      return (fromRow + 2 == toRow && fromCol + 2 == toCol)
              || (fromRow - 2 == toRow && fromCol - 2 == toCol);
    }
  }

  /**
   * Determines if this slot has any valid move by checking if it has any valid horizontal or
   * vertical moves in the superclass method and then checking if it has any diagonal moves here.
   *
   * @param row the row of the specified slot whose moves we are looking at
   * @param col the column of the specified slot whose moves we are looking at
   * @return whether this slot has any valid moves.
   */
  @Override
  protected boolean slotHasValidMoves(int row, int col) {
    // NOW CHECKS FOR DIAGONAL MOVES AS WELL
    boolean topLeftDiagonalMove = false;
    boolean bottomRightDiagonalMove = false;

    // if the spot to the top left diagonal is on the board, then check to see if it's valid
    if (!this.isOutOfBounds(row - 2, col - 2)) {
      topLeftDiagonalMove = this.isValidMove(row, col, row - 2, col - 2);
    }
    // if the spot to the bottom right diagonal is on the board, then check to see if it's valid
    if (!this.isOutOfBounds(row + 2, col + 2)) {
      bottomRightDiagonalMove = this.isValidMove(row, col, row + 2, col + 2);
    }

    return  // super checks for horizontal and vertical, and this checks for diagonal
            super.slotHasValidMoves(row, col) || topLeftDiagonalMove || bottomRightDiagonalMove;
  }

  /**
   * Determines the boardSize of the triangle solitaire model, which is just the armThickness.
   *
   * @return the armThickness, which represents the boardSize
   */
  @Override
  public int getBoardSize() {
    return this.armThickness;
  }
}
