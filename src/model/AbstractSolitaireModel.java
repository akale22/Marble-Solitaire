package model;

/**
 * An abstract class for a marble solitaire model with fields and methods that are common to all
 * implementations of a marble solitaire model.
 *
 * <p>
 *   I did not have an abstract class before, but now with many implementations of the model other
 *   than just the English one, I found that there were many common fields, methods, and
 *   properties to each type of model and decided to abstract them into an abstract class. The
 *   default implementations for many methods in the abstract class are for square shaped boards,
 *   such as the English and the European ones, because they are the exact same and this helps
 *   reduce code duplication. For the triangle implementation, there were more methods that had
 *   to be overridden since it was a more different type of model. There were some methods that
 *   did differ for every single implementation and had to be declared abstract, thus
 *   justifying the use of an abstract class here.
 * </p>
 */
public abstract class AbstractSolitaireModel implements MarbleSolitaireModel {

  protected final int armThickness;
  protected final SlotState[][] board;

  /**
   * Constructor for creating a model with a specified arm thickness and the empty spot at the
   * specified row and column.
   *
   * @param armThickness the number of marbles in the top row
   * @param sRow         the row of the empty spot
   * @param sCol         the column of the empty spot
   * @throws IllegalArgumentException if the armThickness is an invalid length (depends on the
   *                                  type of board) or if the specified empty space is an invalid
   *                                  space on the board or is not on the board completely
   */
  public AbstractSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    this.checkArmThickness(armThickness);
    this.armThickness = armThickness;
    if (this.isInvalidSpot(sRow, sCol) || this.isOutOfBounds(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
    this.board = this.generateNewBoard(sRow, sCol);
  }

  /**
   * Checks to see if the specified arm thickness is invalid and violates any necessary conditions
   * for the game to be playable. The two default conditions for square board are that the arm
   * length must be an odd integer and must be greater than or equal to 3.
   *
   * @param armThickness the desired number of marbles in the top row
   * @throws IllegalArgumentException if the armThickness is not an odd integer greater than or
   *                                  equal to 3
   */
  protected void checkArmThickness(int armThickness) throws IllegalArgumentException {
    if (armThickness < 3 || (armThickness % 2 != 1)) {
      throw new IllegalArgumentException("Arm thickness must be a positive odd number greater "
              + "than 3!");
    }
  }

  /**
   * Determines if a slot specified by the row and column represents an invalid spot on the board
   * (in the 2D array but not part of the valid playing area).
   *
   * @param row the row of the slot
   * @param col the column of the slot
   * @return a boolean representing whether the slot specified by the row and column is an
   *         invalid spot, meaning that it is on the board but not a part of the valid playing
   *         area. Returns true if it is not on the board, false otherwise.
   */
  protected abstract boolean isInvalidSpot(int row, int col);

  /**
   * Determines if a slot specified by the row and column is not on the board at all (not
   * contained in the 2D array representing the board at all).
   *
   * @param row represents the row of the specified slot
   * @param col represents the column of the specified slot
   * @return a boolean representing whether the selected slot is not on the board at all.
   *         Returns true if the slot is out of bounds, false otherwise.
   */
  protected boolean isOutOfBounds(int row, int col) {
    // determines whether the row and/or column is less than 0, or greater than the boardSize,
    // which would then return true stating that the slot is out of bounds
    return row < 0 || row > this.getBoardSize() - 1 || col < 0 || col > this.getBoardSize() - 1;
  }

  /**
   * Generates the starting board with the correct SlotState at every position in the 2D array of
   * SlotStates (Invalid SlotStates in the required spots based on the board, and the Empty
   * SlotState at the specified row and column).
   *
   * @param sRow the row of the empty slot
   * @param sCol the column of the empty slot
   * @return a 2D array of SlotStates with the correct SlotState at every position in the 2D
   *         array to represent all the marbles, all the correct invalid spots, and the specified
   *         empty spot
   */
  protected SlotState[][] generateNewBoard(int sRow, int sCol) {
    int boardSize = this.getBoardSize();

    SlotState[][] board = new SlotState[boardSize][boardSize];
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        // if the slot at this row and col , set its SlotState to invalid
        if (this.isInvalidSpot(i, j)) {
          board[i][j] = SlotState.Invalid;
        }
        // if this slot is at the specified empty location, set its SlotState to Empty
        else if (i == sRow && j == sCol) {
          board[sRow][sCol] = SlotState.Empty;
        }
        // a remaining slot, set its SlotState to Marble
        else {
          board[i][j] = SlotState.Marble;
        }
      }
    }
    return board;
  }

  /**
   * This method throws an exception if any of the following are true. There are some other cases
   * which are specific to different implementations of the model.
   *
   * <ul>
   *   <li> The from slot is not on the board. </li>
   *   <li> The to slot is not on the board. </li>
   *   <li> The from slot is an invalid slot. </li>
   *   <li> The to slot is an invalid slot. </li>
   *   <li> The from spot doesn't have a marble. </li>
   *   <li> The move doesn't have a marble in the slot in between. </li>
   *   <li> The to slot isn't empty. </li>
   * </ul>
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    // either the from or to slot are not on the board
    if (this.isOutOfBounds(fromRow, fromCol) || this.isOutOfBounds(toRow, toCol)) {
      throw new IllegalArgumentException("Either the from or to slot is not on the board!");
    }
    // if this is a valid move, then change the SlotStates of the from slot, the to
    // slot, and the slot in between to its appropriate SlotStates
    if (this.isValidMove(fromRow, fromCol, toRow, toCol)) {
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
    }
    // the move from the from slot to the to slot is not valid
    else {
      throw new IllegalArgumentException("This is not a valid move!");
    }
  }

  /**
   * Determines if a move from the position specified by the fromRow and fromCol to the position
   * specified by the toRow and toColumn is a valid move.
   *
   * @param fromRow the row of the from slot in the move
   * @param fromCol the column of the from slot in the move
   * @param toRow   the row of the to slot in the move
   * @param toCol   the column of the to slot in the move
   * @return a boolean representing whether the move specified by the parameters is valid.
   *         Returns true if it is a valid move, false otherwise
   */
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    // Making sure that the from slot has a marble, the slot in between has a marble, and the
    // to slot is empty. This does not guarantee that the to slot is only two slots away
    // but this check comes later in another helper method.
    if (!(this.board[fromRow][fromCol] == SlotState.Marble
            && this.board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == SlotState.Marble
            && this.board[toRow][toCol] == SlotState.Empty)) {
      return false;
    }
    // Checking to see if the move is a valid horizontal or vertical move
    else {
      return this.isValidHorizontalMove(fromRow, fromCol, toRow, toCol)
              || this.isValidVerticalMove(fromRow, fromCol, toRow, toCol);
    }
  }

  /**
   * Determines if a move from the position specified by the fromRow and fromCol to the position
   * specified by the toRow and toColumn is a valid horizontal move. A valid horizontal move has
   * the same fromRow and toRow and the toCol must be 2 slots away from the fromCol in one of the
   * horizontal directions.
   *
   * @param fromRow the row of the from slot in the move
   * @param fromCol the column of the from slot in the move
   * @param toRow   the row of the to slot in the move
   * @param toCol   the column of the to slot in the move
   * @return a boolean representing whether the horizontal move specified by the
   *         parameters is valid. Returns true if it is a valid horizontal move, false
   *         otherwise.
   */
  protected boolean isValidHorizontalMove(int fromRow, int fromCol, int toRow, int toCol) {
    return fromRow == toRow && Math.abs(fromCol - toCol) == 2;
  }

  /**
   * Determines if a move from the position specified by the fromRow and fromCol to the position
   * specified by the toRow and toColumn is a valid vertical move. A valid vertical move has
   * the same fromCol and toCol and the toRow must be 2 slots away from the fromRow in one of the
   * vertical directions.
   *
   * @param fromRow the row of the from slot in the move
   * @param fromCol the column of the from slot in the move
   * @param toRow   the row of the to slot in the move
   * @param toCol   the column of the to slot in the move
   * @return a boolean representing whether the vertical move specified by the
   *         parameters is valid. Returns true if it is a valid vertical move, false
   *         otherwise.
   */
  protected boolean isValidVerticalMove(int fromRow, int fromCol, int toRow, int toCol) {
    return toCol == fromCol && Math.abs(fromRow - toRow) == 2;
  }

  @Override
  public boolean isGameOver() {
    return !this.anyValidMovesOnBoard();
  }

  /**
   * Determines if there is any valid moves left on the board.
   *
   * @return a boolean representing whether there are any valid moves left on the board. Returns
   *         true if there are any valid moves left, false otherwise.
   */
  private boolean anyValidMovesOnBoard() {
    // the acc starts off as false to represent that there are no valid moves to begin with, and
    // we will accumulate this with booleans representing whether every slot has a valid move
    boolean acc = false;
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.board[i][j] == SlotState.Marble) {
          boolean b = this.slotHasValidMoves(i, j);
          // if there is a valid move, then the acc will change to true
          acc = acc || b;
        }
        // if the accumulator is ever changed to true, then we don't need to loop anymore since
        // there is at least one valid move remaining, so we return true
        if (acc) {
          return true;
        }
      }
    }
    // if we go through the entire loop without the acc every changing to true, that means that
    // there are no valid moves for any slot on the entire board, and we return false
    return false;
  }

  /**
   * Determines whether this specific slot, specified by the row and column, has at least one
   * valid move in any direction.
   *
   * @param row the row of the specified slot whose moves we are looking at
   * @param col the column of the specified slot whose moves we are looking at
   * @return a boolean representing whether the slot specified by the row and column has a
   *         valid move in any direction. Returns true if this slot has any valid moves,
   *         false otherwise.
   */
  protected boolean slotHasValidMoves(int row, int col) {
    // initializing the booleans representing moves in every direction to false, if there is
    // a valid move in any direction, the local variable will change to true
    boolean leftMove = false;
    boolean rightMove = false;
    boolean upMove = false;
    boolean downMove = false;
    // if the spot two to the left is on the board, then check to see if moving left is valid
    if (!this.isOutOfBounds(row, col - 2)) {
      leftMove = this.isValidMove(row, col, row, col - 2);
    }
    // if the spot two to the right is on the board, then check to see if moving right is valid
    if (!this.isOutOfBounds(row, col + 2)) {
      rightMove = this.isValidMove(row, col, row, col + 2);
    }
    // if the spot two to the top is on the board, then check to see if moving up is valid
    if (!this.isOutOfBounds(row - 2, col)) {
      upMove = this.isValidMove(row, col, row - 2, col);
    }
    // if the spot two to the bottom is on the board, then check to see if moving down is valid
    if (!this.isOutOfBounds(row + 2, col)) {
      downMove = this.isValidMove(row, col, row + 2, col);
    }
    return leftMove || rightMove || upMove || downMove;
  }

  @Override
  public int getBoardSize() {
    return this.armThickness * 3 - 2;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (this.isOutOfBounds(row, col)) {
      throw new IllegalArgumentException("Row and/or column are beyond the dimensions of the "
              + "board!");
    } else {
      return this.board[row][col];
    }
  }

  @Override
  public int getScore() {
    int count = 0;
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.board[i][j] == SlotState.Marble) {
          count += 1;
        }
      }
    }
    return count;
  }
}
