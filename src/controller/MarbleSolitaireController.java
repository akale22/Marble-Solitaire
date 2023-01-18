package controller;

/**
 * This interface represents a controller for a game of Marble Solitaire which facilitates
 * successful gameplay.
 */
public interface MarbleSolitaireController {

  /**
   * Plays the game of MarbleSolitaire by taking user input, reading it, performing operations on
   * that input using the mode, and displaying output to the user.
   *
   * @throws IllegalStateException if the controller is unable to read input or transmit output
   */
  void playGame() throws IllegalStateException;
}
