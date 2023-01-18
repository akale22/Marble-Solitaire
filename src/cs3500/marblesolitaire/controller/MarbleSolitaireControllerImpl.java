package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * An implementation of a controller for a Marble Solitaire game which allows the user to play.
 * Throws an IllegalStateException if the controller is unable to successfully read input or
 * transmit output.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;
  private final Readable in;
  private final ArrayList<Integer> numbersForMoves;

  /**
   * A constructor for the implementation of a controller for this game which instantiates all
   * the parameters to the fields of the class.
   *
   * @param model the model that performs the operations for this game
   * @param view  the view which displays the necessary information for this game
   * @param in    what the controller reads from
   * @throws IllegalArgumentException if any of the three parameters are null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable in) throws IllegalArgumentException {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("None of the arguments can be null!");
    }
    this.model = model;
    this.view = view;
    this.in = in;
    this.numbersForMoves = new ArrayList<Integer>();
  }

  /**
   * Throws an exception if reading input or transmission fails, or if there are no more inputs
   * in the readable and the game has not ended or the user has not manually quit the game  by
   * entering q.
   */
  @Override
  public void playGame() throws IllegalStateException {
    Scanner sc = new Scanner(this.in);
    this.displayStartingScreen();

    // if the game hasn't ended yet
    while (!this.model.isGameOver()) {

      // if there's input, get it so that we can see what it is and determine what to do with it
      if (sc.hasNext()) {
        String next = sc.next();

        // if the input is a q, then display the necessary information and then end the method
        if (next.equalsIgnoreCase("q")) {
          this.quitGame();
          sc.close();
          return;
        }

        // checking all other inputs if is not a q, more specifically, looking for a valid number
        else {
          this.checkForValidNumber(next);
        }
      }

      // if the readable is empty and the user hasn't quit or ended the game, throw an exception
      else {
        throw new IllegalStateException("No more inputs!");
      }
    }

    // if the while loop ends meaning the game is over and was not quit manually, then display the
    // necessary information at the end of the game
    this.gameOver();
    sc.close();
  }

  /**
   * A helper method for transmitting a specific message to the view and dealing with the
   * potential IO exception. Helps with readability in situations where nested try catch blocks
   * may have been necessary otherwise (if the try or catch part of a try catch block would have
   * used renderMessage, meaning another nested try catch block would have been necessary to deal
   * with this IO exception).
   *
   * @throws IllegalStateException if transmission to the view fails
   */
  private void catchIOMessageException(String message) {
    try {
      this.view.renderMessage(message + System.lineSeparator());
    }
    catch (IOException e) {
      throw new IllegalStateException("Transmission to the view failed!");
    }
  }

  /**
   * Displays the necessary elements at the beginning of the game: a welcome message, instructions,
   * the board, and the score.
   *
   * @throws IllegalStateException if transmission to the view fails
   */
  private void displayStartingScreen() throws IllegalStateException {
    try {
      this.view.renderMessage(
              System.lineSeparator() + "Welcome to Marble Solitaire!"
              + System.lineSeparator() + "In order to move, enter 4 numbers with a "
              + "space or return between them that represent the following:"
              + System.lineSeparator() + "1. fromRow  2. fromColumn  3. toRow  4. toColumn."
              + System.lineSeparator() + "The numbers should begin at 1 to represent the first " +
              "row/column, 2 to represent the second row/column, and so on."
              + System.lineSeparator() + "Enter q at any moment to quit the game. Have fun!"
              + System.lineSeparator());
      this.showScoreAndBoard();
    }
    catch (IOException e) {
      throw new IllegalStateException("Transmission of the starting screen to the view failed!");
    }
  }

  /**
   * Displays the necessary information once the game is quit.
   *
   * @throws IllegalStateException if transmission to the view fails
   */
  private void quitGame() throws IllegalStateException {
    try {
      this.view.renderMessage("Game quit!" + System.lineSeparator());
      this.view.renderMessage("State of game when quit:" + System.lineSeparator());
      this.showScoreAndBoard();
    }
    catch (IOException e) {
      throw new IllegalStateException("Transmission of the quitting game information to the view " +
              "failed!");
    }
  }

  /**
   * Checks to see if the parameter string is a valid number (integer greater than 0), and if so, it
   * passes it to the numbersForMoves arraylist and sees if a valid move can be performed.
   * Otherwise, informs the user that an invalid input was entered (a negative number or 0, or
   * any other string that can't be parsed into an integer).
   *
   * @param next the string that we are attempting to parse to an integer
   * @throws IllegalStateException if transmission to the view fails
   */
  private void checkForValidNumber(String next) throws IllegalStateException {

    // making sure to catch a NumberFormatException if we try to parse a string to an integer
    // when that's not possible
    try {

      // parse the string to an int, and it is greater than zero, then add num - 1 to
      // the list of numbers since we index at 0 but user input starts at 1, and see if a move can
      // be performed right now
      int num = Integer.parseInt(next);
      if (num > 0) {
        this.numbersForMoves.add(num - 1);
        this.attemptMoveIfPossible();
      }

      // the number is not greater than 0 and thus represents an invalid input
      else {
        this.catchIOMessageException("Invalid Input: " + next + ". Please re-enter that value " +
                "again.");
      }
    }

    // the string cannot be parsed to a number and thus represents an invalid input
    catch (NumberFormatException e) {
      this.catchIOMessageException("Invalid Input: " + next + ". Please re-enter that value " +
              "again.");
    }
  }

  /**
   * Checks to see if the list of numbersForMoves is 4 and if so, attempts to execute a move by
   * passing in the 4 numbers in that list to the move method defined by the model and then
   * displaying the correct new board and score if the move is valid.
   *
   * @throws IllegalStateException if transmission to the view fails
   */
  private void attemptMoveIfPossible() throws IllegalStateException {
    if (this.numbersForMoves.size() >= 4) {
      // removing the 4 numbers for the move from the list and setting them to local variables
      // which can be passed to the move method
      int fromRow = this.numbersForMoves.remove(0);
      int fromCol = this.numbersForMoves.remove(0);
      int toRow = this.numbersForMoves.remove(0);
      int toCol = this.numbersForMoves.remove(0);

      try {
        this.model.move(fromRow, fromCol, toRow, toCol);
        if (!this.model.isGameOver()) {
          this.showScoreAndBoard();
        }
      }
      catch (IllegalArgumentException e) {
        this.catchIOMessageException("Invalid move. Play again.");
      }
    }
  }

  /**
   * Displays the necessary information once the game is over, including a game over message, the
   * final state of the game, and the final score.
   *
   * @throws IllegalStateException if transmission to the view fails
   */
  private void gameOver() throws IllegalStateException {
    try {
      this.view.renderMessage("Game over!" + System.lineSeparator());
      this.showScoreAndBoard();
    }
    catch (IOException e) {
      throw new IllegalStateException("Transmission of the game over information to the view " +
              "failed!");
    }
  }

  /**
   * Displays the board and the current score.
   *
   * @throws IllegalStateException if transmission to the view fails
   */
  private void showScoreAndBoard() throws IllegalStateException {
    try {
      this.view.renderBoard();
      this.view.renderMessage(System.lineSeparator() + "Score: " + this.model.getScore() +
              System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Transmission of the board and score to the view failed!");
    }
  }
}




