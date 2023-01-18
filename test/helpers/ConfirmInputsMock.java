package helpers;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * A mock model class that confirms whether the controller is sending the correct inputs to the
 * model. Move is the only method ever called by the controller, so it is the only method that we
 * have to mock in such a way that we have to confirm the inputs.
 */
public class ConfirmInputsMock implements MarbleSolitaireModel {

  private StringBuilder log;

  /**
   * A constructor which instantiate the log.
   *
   * @param log represents the log of inputs passed to this class.
   */
  public ConfirmInputsMock(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    this.log.append("A move was attempted from row: " + fromRow + " and column: " + fromCol + " to "
            + "row: " + toRow + " and column: " + toCol + ".\n");
  }

  @Override
  public boolean isGameOver() {
    log.append("Controller asked if the game is over.\n");
    return false;
  }

  @Override
  public int getBoardSize() {
    log.append("Controller obtained the board size.\n");
    return 0;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    log.append("Controller obtained the slotstate at row: " + row + " and column: " + col + ".\n");
    return null;
  }

  @Override
  public int getScore() {
    log.append("Controller obtained the score from the model.\n");
    return 0;
  }
}
