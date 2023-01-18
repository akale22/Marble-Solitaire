import org.junit.Before;
import org.junit.Test;

import marblesolitaire.model.MarbleSolitaireModel;
import marblesolitaire.model.MarbleSolitaireModelState;
import marblesolitaire.model.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A JUnit test class for {@link TriangleSolitaireModel}s.
 */
public class TriangleSolitaireModelTest {
  private MarbleSolitaireModel defaultConstructor;
  // when dimensions = 5:
  // ------pegSlots------
  // 0 | E - - - -
  // 1 | 0 0 - - -
  // 2 | 0 0 0 - -
  // 3 | 0 0 0 0 -
  // 4 | 0 0 0 0 0
  //   +-----------------
  //     0 1 2 3 4
  private MarbleSolitaireModel armLength3;
  private MarbleSolitaireModel armLength7;
  private MarbleSolitaireModel armLength10;
  private MarbleSolitaireModel specifiedEmptySlot;
  private MarbleSolitaireModel specifiedEmptySlotAndArmLength6;
  private MarbleSolitaireModel bigTriangleForMoves;

  @Before
  public void init() {
    defaultConstructor = new TriangleSolitaireModel();
    armLength3 = new TriangleSolitaireModel(3);
    armLength7 = new TriangleSolitaireModel(7);
    armLength10 = new TriangleSolitaireModel(10);
    specifiedEmptySlot = new TriangleSolitaireModel(1, 1);
    specifiedEmptySlotAndArmLength6 = new TriangleSolitaireModel(6, 5, 2);
    bigTriangleForMoves = new TriangleSolitaireModel(10, 6, 3);
  }

  ///////////////////////// Tests for Constructors /////////////////////////////////

  // testing the properties of a model with the default constructor
  @Test
  public void testValidConstructionWithDefaultConstructor() {
    assertEquals(14, this.defaultConstructor.getScore());
    assertEquals(5, this.defaultConstructor.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.defaultConstructor.getSlotAt(0, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.defaultConstructor.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.defaultConstructor.getSlotAt(1, 4));
  }

  // testing the properties of a model with the specified arm length constructor
  @Test
  public void testValidConstructionWithSpecifiedArmLengthConstructor() {
    assertEquals(5, this.armLength3.getScore());
    assertEquals(3, this.armLength3.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.armLength3.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.armLength3.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.armLength3.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.armLength3.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.armLength3.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.armLength3.getSlotAt(0, 2));
  }

  // testing for an exception when a model is made with an arm length of 0
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionWithArmLength0() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(0);
  }

  // testing for an exception when a model is made with a
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionWithArmLengthNeg() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(-10);
  }

  // testing the properties of a model with the specified empty spot constructor
  @Test
  public void testValidConstructionWithSpecifiedEmptySlotConstructor() {
    assertEquals(14, specifiedEmptySlot.getScore());
    assertEquals(5, this.specifiedEmptySlot.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.specifiedEmptySlot.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.specifiedEmptySlot.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.specifiedEmptySlot.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.specifiedEmptySlot.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.specifiedEmptySlot.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.specifiedEmptySlot.getSlotAt(0, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.specifiedEmptySlot.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.specifiedEmptySlot.getSlotAt(1, 4));
  }

  // testing for an exception where the empty slot is in an invalid slot
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionWithInvalidEmptySlot() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(0, 2);
  }

  // testing for an exception where the empty slot is off the board to the top
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionWithOutOfBoundsEmptySlot() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(-100, 3);
  }

  // testing for an exception where the empty slot is off the board to the bottom
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionWithOutOfBoundsEmptySlot2() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(100, 3);
  }

  // testing for an exception where the empty slot is off the board to the left
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionWithOutOfBoundsEmptySlot3() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(3, -100);
  }

  // testing for an exception where the empty slot is off the board to the right
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionWithOutOfBoundsEmptySlot4() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(3, 100);
  }

  // testing the properties of a model with the specified empty spot and arm length constructor
  // using public methods
  @Test
  public void testValidConstructionWithSpecifiedArmLengthAndEmptySpotConstructor() {
    assertEquals(20, this.specifiedEmptySlotAndArmLength6.getScore());
    assertEquals(6, this.specifiedEmptySlotAndArmLength6.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.specifiedEmptySlotAndArmLength6.getSlotAt(5, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.specifiedEmptySlotAndArmLength6.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.specifiedEmptySlotAndArmLength6.getSlotAt(5, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.specifiedEmptySlotAndArmLength6.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.specifiedEmptySlotAndArmLength6.getSlotAt(3, 4));
  }

  // testing for an exception when the arm thickness is 0
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionComplexConstructor() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(0, 0, 0);
  }

  // testing for an exception when the arm thickness is negative
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionComplexConstructor2() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(-10, 0, 0);
  }

  // testing for an exception when the empty slot is off the board with a different constructor
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionComplexConstructor3() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(6, 25, 30);
  }

  // testing for another exception when the empty slot is off the board with a different constructor
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionComplexConstructor4() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(7, -3, 0);
  }

  // testing for an exception when the empty slot is in an invalid spot with a different constructor
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionComplexConstructor5() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(3, 1, 2);
  }

  ///////////////////////// Tests for Methods /////////////////////////////////

  // testing a valid move to the left by checking conditions before and after
  @Test
  public void testLeftMove() {
    // testing the preconditions of a valid move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(6, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(6, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(6, 3));
    assertEquals(54, bigTriangleForMoves.getScore());

    // performing a valid left move
    this.bigTriangleForMoves.move(6, 5, 6, 3);

    // testing the SlotStates of every slot involved in the move after and the score to make sure
    // it changed
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(6, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(6, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(6, 3));
    assertEquals(53, bigTriangleForMoves.getScore());
  }

  // testing a valid move to the right by checking conditions before and after
  @Test
  public void testRightMove() {
    // testing the preconditions of a valid move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(6, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(6, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(6, 3));
    assertEquals(54, bigTriangleForMoves.getScore());

    // performing a valid right move
    this.bigTriangleForMoves.move(6, 1, 6, 3);

    // testing the SlotStates of every slot involved in the move after and the score to make sure
    // it changed
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(6, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(6, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(6, 3));
    assertEquals(53, bigTriangleForMoves.getScore());
  }

  // testing a valid move up left diagonal by checking conditions before and after
  @Test
  public void testUpLeftDiagonalMove() {
    // testing the preconditions of a valid move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(8, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(7, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(6, 3));
    assertEquals(54, bigTriangleForMoves.getScore());

    // performing a valid up left diagonal move
    this.bigTriangleForMoves.move(8, 5, 6, 3);

    // testing the SlotStates of every slot involved in the move after and the score to make sure
    // it changed
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(8, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(7, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(6, 3));
    assertEquals(53, bigTriangleForMoves.getScore());
  }

  // testing a valid move up right diagonal by checking conditions before and after
  @Test
  public void testUpRightDiagonalMove() {
    // testing the preconditions of a valid move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(8, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(7, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(6, 3));
    assertEquals(54, bigTriangleForMoves.getScore());

    // performing a valid up right diagonal move
    this.bigTriangleForMoves.move(8, 3, 6, 3);

    // testing the SlotStates of every slot involved in the move after and the score to make sure
    // it changed
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(8, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(7, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(6, 3));
    assertEquals(53, bigTriangleForMoves.getScore());
  }

  // testing a valid move down right diagonal by checking conditions before and after
  @Test
  public void testDownRightDiagonalMove() {
    // testing the preconditions of a valid move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(5, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(6, 3));
    assertEquals(54, bigTriangleForMoves.getScore());

    // performing a valid down right diagonal move
    this.bigTriangleForMoves.move(4, 1, 6, 3);

    // testing the SlotStates of every slot involved in the move after and the score to make sure
    // it changed
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(5, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(6, 3));
    assertEquals(53, bigTriangleForMoves.getScore());
  }

  // testing a valid move down left diagonal by checking conditions before and after
  @Test
  public void testDownLeftDiagonalMove() {
    // testing the preconditions of a valid move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(6, 3));
    assertEquals(54, bigTriangleForMoves.getScore());

    // performing a valid down left diagonal move
    this.bigTriangleForMoves.move(4, 3, 6, 3);

    // testing the SlotStates of every slot involved in the move after and the score to make sure
    // it changed
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(6, 3));
    assertEquals(53, bigTriangleForMoves.getScore());
  }


  // testing the conditions before and after of multiple consecutive moves
  @Test
  public void testMultipleMoves() {
    // testing the preconditions of a valid move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(6, 3));
    assertEquals(54, bigTriangleForMoves.getScore());

    // performing a valid down left move
    this.bigTriangleForMoves.move(4, 3, 6, 3);

    // testing the SlotStates of every slot involved in the move after and the score to make sure
    // it changed
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(6, 3));
    assertEquals(53, bigTriangleForMoves.getScore());

    // testing the preconditions of another valid move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(5, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(5, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(5, 3));

    // performing a valid down move
    this.bigTriangleForMoves.move(5, 1, 5, 3);

    // testing the SlotStates of every slot involved in the move after and the score to make sure
    // it changed
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(5, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.bigTriangleForMoves.getSlotAt(5, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.bigTriangleForMoves.getSlotAt(5, 3));
    assertEquals(52, bigTriangleForMoves.getScore());
  }

  // testing a move where the from slot is out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove() {
    this.defaultConstructor.move(1, -1, 1, 2);
  }

  // testing a move where the to slot is out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove2() {
    this.defaultConstructor.move(1, 1, -1, 1);
  }

  // testing a move where the from slot is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove3() {
    this.defaultConstructor.move(1, 3, 1, 1);
  }

  // testing a move where the to slot is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove4() {
    this.defaultConstructor.move(1, 1, 1, 3);
  }

  // testing a move where the from slot doesn't have a marble
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove5() {
    this.defaultConstructor.move(0, 0, 2, 0);
  }

  // testing a move where the to slot isn't empty
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove6() {
    this.defaultConstructor.move(1 , 1,3, 1);
  }

  // testing a move where the slot in between doesn't have a marble
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove7() {
    this.bigTriangleForMoves.move(6, 2, 6, 4);
  }

  // testing a move that is horizontal but not exactly 2 columns away
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove9() {
    this.bigTriangleForMoves.move(6, 0, 6, 3);
  }

  // testing a move that is vertical (representing diagonal movement) but not exactly 2 columns away
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove10() {
    this.bigTriangleForMoves.move(3, 3, 6, 3);
  }

  // testing for a diagonal move that isn't exactly diagonal (2 spots away diagonally)
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove11() {
    this.bigTriangleForMoves.move(4, 0, 6, 3);
  }

  // testing for a diagonal move that by incrementing row and decrementing row by 2, which isn't
  // allowed
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove12() {
    this.bigTriangleForMoves.move(8, 1, 6, 3);
  }

  // testing for a diagonal move that by incrementing row and decrementing row by 2, which isn't
  // allowed
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove13() {
    this.bigTriangleForMoves.move(4, 5, 6, 3);
  }

  // playing out a game using the move method repeatedly only with valid moves, and checking to
  // see if the game is over after every move. isGameOver should return false until the game
  // has actually ended and there are no more valid moves
  @Test
  public void testIsGameOver() {
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(2, 0, 0, 0);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(2, 2, 2, 0);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(0, 0, 2, 2);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(3, 0, 1, 0);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(3, 3, 1, 1);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(4, 1, 2, 1);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(4, 3, 4, 1);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(2, 1, 4, 3);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(4, 0, 4, 2);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(4, 3, 4, 1);


    // THERE ARE NO LONGER ANY VALID MOVES, THUS, THE GAME IS OVER
    assertTrue(this.defaultConstructor.isGameOver());
  }

  // testing the method getBoardSize on different sizes of armLengths
  @Test
  public void testGetBoardSize() {
    assertEquals(5, this.defaultConstructor.getBoardSize());
    assertEquals(5, this.specifiedEmptySlot.getBoardSize());
    assertEquals(3, this.armLength3.getBoardSize());
    assertEquals(7, this.armLength7.getBoardSize());
    assertEquals(10, this.armLength10.getBoardSize());
    assertEquals(6, this.specifiedEmptySlotAndArmLength6.getBoardSize());
  }


  // testing different SlotStates of the board constructed by the default constructor
  @Test
  public void testGetSlotAtWithDefaultConstructor() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.defaultConstructor.getSlotAt(0, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.defaultConstructor.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.defaultConstructor.getSlotAt(3, 4));
  }

  // testing different SlotStates of the board constructed by the constructor which
  // specifies arm length
  @Test
  public void testGetSlotAtWithSpecifiedArmLengthConstructor() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.armLength7.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.armLength7.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.armLength7.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.armLength7.getSlotAt(6, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.armLength7.getSlotAt(1, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.armLength7.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.armLength7.getSlotAt(0, 6));
  }

  // testing different SlotStates of the board constructed by the constructor which
  // specifies the empty slot
  @Test
  public void testGetSlotAtWithSpecifiedEmptySlotConstructor() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.specifiedEmptySlot.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.specifiedEmptySlot.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.defaultConstructor.getSlotAt(0, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.defaultConstructor.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.defaultConstructor.getSlotAt(3, 4));
  }

  // testing different SlotStates of the board constructed by the constructor which
  // specifies arm length and the empty slot
  @Test
  public void testGetSlotAtWithSpecifiedEmptySlotAndArmLengthConstructor() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.specifiedEmptySlotAndArmLength6.getSlotAt(5, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.specifiedEmptySlotAndArmLength6.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.armLength7.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.armLength7.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.armLength7.getSlotAt(5, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.armLength7.getSlotAt(1, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.armLength7.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.armLength7.getSlotAt(0, 2));
  }

  // testing for an exception when the specified slot is out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void testGetScoreAtOutOfBoundsSpot() {
    this.defaultConstructor.getSlotAt(2, 10);
  }

  // testing for another exception when the specified slot is out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void testGetScoreAtOutOfBoundsSpot2() {
    this.defaultConstructor.getSlotAt(-10, 2);
  }

  // testing for another exception when the specified slot is out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void testGetScoreAtOutOfBoundsSpot3() {
    this.defaultConstructor.getSlotAt(2, 10);
  }

  // testing for another exception when the specified slot is out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void testGetScoreAtOutOfBoundsSpot4() {
    this.defaultConstructor.getSlotAt(2, -10);
  }


  // testing the number of marbles on different sized boards
  @Test
  public void testGetScore() {
    assertEquals(14, this.defaultConstructor.getScore());
    assertEquals(14, this.specifiedEmptySlot.getScore());
    assertEquals(5, this.armLength3.getScore());
    assertEquals(27, this.armLength7.getScore());
    assertEquals(54, this.armLength10.getScore());
    assertEquals(20, this.specifiedEmptySlotAndArmLength6.getScore());
  }

}