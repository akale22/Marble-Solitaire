import org.junit.Before;
import org.junit.Test;

import model.EnglishSolitaireModel;
import model.MarbleSolitaireModel;
import model.MarbleSolitaireModelState;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for {@link EnglishSolitaireModel}s.
 */
public class EnglishSolitaireModelTest {

  private MarbleSolitaireModel defaultConstructor;
  private MarbleSolitaireModel armLength5;
  private MarbleSolitaireModel armLength7;
  private MarbleSolitaireModel specifiedEmptySlot;
  private MarbleSolitaireModel specifiedEmptySlotAndArmLength5;

  @Before
  public void init() {
    defaultConstructor = new EnglishSolitaireModel();
    armLength5 = new EnglishSolitaireModel(5);
    armLength7 = new EnglishSolitaireModel(7);
    specifiedEmptySlot = new EnglishSolitaireModel(4, 2);
    specifiedEmptySlotAndArmLength5 = new EnglishSolitaireModel(5, 5, 2);
  }

  ///////////////////////// Tests for Constructors /////////////////////////////////

  // testing the properties of a model with the default constructor
  @Test
  public void testValidConstructionWithDefaultConstructor() {
    assertEquals(32, this.defaultConstructor.getScore());
    assertEquals(7, this.defaultConstructor.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.defaultConstructor.getSlotAt(1, 1));
  }

  // testing the properties of a model with the specified arm length constructor
  @Test
  public void testValidConstructionWithSpecifiedArmLengthConstructor() {
    assertEquals(104, this.armLength5.getScore());
    assertEquals(13, this.armLength5.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.armLength5.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.armLength5.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.armLength5.getSlotAt(1, 1));
  }

  // testing for an exception when a model is made with an even arm length
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionWithEvenArmLength() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(4);
  }

  // testing for an exception when a model is made with an arm length less than 3
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionWithArmLength1() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(1);
  }

  // testing the properties of a model with the specified empty spot constructor
  @Test
  public void testValidConstructionWithSpecifiedEmptySlotConstructor() {
    assertEquals(32, this.specifiedEmptySlot.getScore());
    assertEquals(7, this.specifiedEmptySlot.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.specifiedEmptySlot.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.specifiedEmptySlot.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.specifiedEmptySlot.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.specifiedEmptySlot.getSlotAt(1, 1));
  }

  // testing for an exception where the empty slot is in the topLeft invalid corner
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionWithInvalidEmptySlot() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(0, 0);
  }

  // testing for an exception where the empty slot is in the topRight invalid corner
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionWithInvalidEmptySlot2() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(0, 5);
  }

  // testing for an exception where the empty slot is in the bottomLeft invalid corner
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionWithInvalidEmptySlot3() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(5, 0);
  }

  // testing for an exception where the empty slot is in the bottomRight invalid corner
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionWithInvalidEmptySlot4() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(5, 5);
  }

  // testing for an exception where the empty slot is off the board to the top
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionWithOutOfBoundsEmptySlot() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(-100, 3);
  }

  // testing for an exception where the empty slot is off the board to the bottom
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionWithOutOfBoundsEmptySlot2() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(100, 3);
  }

  // testing for an exception where the empty slot is off the board to the left
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionWithOutOfBoundsEmptySlot3() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(3, -100);
  }

  // testing for an exception where the empty slot is off the board to the right
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionWithOutOfBoundsEmptySlot4() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 100);
  }

  // testing the properties of a model with the specified empty spot and arm length constructor
  // using public methods
  @Test
  public void testValidConstructionWithSpecifiedArmLengthAndEmptySpotConstructor() {
    assertEquals(104, this.specifiedEmptySlotAndArmLength5.getScore());
    assertEquals(13, this.specifiedEmptySlotAndArmLength5.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.specifiedEmptySlotAndArmLength5.getSlotAt(5, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.specifiedEmptySlotAndArmLength5.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.specifiedEmptySlotAndArmLength5.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.specifiedEmptySlotAndArmLength5.getSlotAt(1, 1));
  }

  // testing for an exception when the arm thickness is less than 3 with a different constructor
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionComplexConstructor() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(1, 3, 3);
  }

  // testing for an exception when the arm thickness is not even with a different constructor
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionComplexConstructor2() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(10, 3, 3);
  }

  // testing for an exception when the empty slot is off the board with a different constructor
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionComplexConstructor3() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(5, 25, 30);
  }

  // testing for another exception when the empty slot is off the board with a different constructor
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionComplexConstructor4() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(7, -3, 0);
  }

  // testing for an exception when the empty slot is in an invalid spot with a different constructor
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionComplexConstructor5() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 0, 0);
  }

  // testing for an exception when the empty slot is in another invalid spot with a different
  // constructor
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionComplexConstructor6() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 5, 5);
  }

  // testing for an exception when the constructor has a negative arm length
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionComplexConstructor7() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(-20, 5, 5);
  }



  ///////////////////////// Tests for Methods /////////////////////////////////

  // testing a valid move to the left by checking conditions before and after
  @Test
  public void testLeftMove() {
    // testing the preconditions of a valid move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(3, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(3, 3));
    assertEquals(32, defaultConstructor.getScore());

    // performing a valid left move
    this.defaultConstructor.move(3, 5, 3, 3);

    // testing the SlotStates of every slot involved in the move after and the score to make sure
    // it changed
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(3, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(3, 3));
    assertEquals(31, defaultConstructor.getScore());
  }

  // testing a valid move to the right by checking conditions before and after
  @Test
  public void testRightMove() {
    // testing the preconditions of a valid move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(3, 3));
    assertEquals(32, defaultConstructor.getScore());

    // performing a valid right move
    this.defaultConstructor.move(3, 1, 3, 3);

    // testing the SlotStates of every slot involved in the move after and the score to make sure
    // it changed
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(3, 3));
    assertEquals(31, defaultConstructor.getScore());
  }

  // testing a valid move up by checking conditions before and after
  @Test
  public void testUpMove() {
    // testing the preconditions of a valid move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(3, 3));
    assertEquals(32, defaultConstructor.getScore());

    // performing a valid up move
    this.defaultConstructor.move(5, 3, 3, 3);

    // testing the SlotStates of every slot involved in the move after and the score to make sure
    // it changed
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(3, 3));
    assertEquals(31, defaultConstructor.getScore());
  }

  // testing a valid down move by checking conditions before and after
  @Test
  public void testDownMove() {
    // testing the preconditions of a valid move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(1, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(3, 3));
    assertEquals(32, defaultConstructor.getScore());

    // performing a valid down move
    this.defaultConstructor.move(1, 3, 3, 3);

    // testing the SlotStates of every slot involved in the move after and the score to make sure
    // it changed
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(1, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(3, 3));
    assertEquals(31, defaultConstructor.getScore());
  }

  // testing the conditions before and after of multiple consecutive moves
  @Test
  public void testMultipleMoves() {
    // testing the preconditions of a valid move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(3, 3));
    assertEquals(32, defaultConstructor.getScore());

    // performing a valid right move
    this.defaultConstructor.move(3, 1, 3, 3);

    // testing the SlotStates of every slot involved in the move after and the score to make sure
    // it changed
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(3, 3));
    assertEquals(31, defaultConstructor.getScore());

    // testing the preconditions of another valid move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(3, 2));

    // performing a valid down move
    this.defaultConstructor.move(1, 2, 3, 2);

    // testing the SlotStates of every slot involved in the move after and the score to make sure
    // it changed
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(3, 2));
    assertEquals(30, defaultConstructor.getScore());
  }

  // testing a move where the from slot is out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove() {
    this.defaultConstructor.move(-2, 2, 0, 2);
  }

  // testing a move where the to slot is out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove2() {
    this.defaultConstructor.move(0, 2, -2, 2);
  }

  // testing a move where the from slot is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove3() {
    this.defaultConstructor.move(1, 1, 1, 3);
  }

  // testing a move where the to slot is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove4() {
    this.defaultConstructor.move(3, 1, 1, 1);
  }


  // testing a move where the from slot doesn't have a marble
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove5() {
    this.defaultConstructor.move(3, 3, 3, 5);
  }

  // testing a move where the to slot isn't empty
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove6() {
    this.defaultConstructor.move(3, 1, 5, 1);
  }

  // testing a move where the slot in between doesn't have a marble
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove7() {
    this.defaultConstructor.move(3, 2, 3, 4);
  }

  // testing a move that isn't orthogonal
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove8() {
    this.defaultConstructor.move(2, 2, 3, 3);
  }

  // testing a move that is horizontal but not exactly 2 columns away
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove9() {
    this.defaultConstructor.move(3, 0, 3, 3);
  }

  // testing a move that is vertical but not exactly 2 columns away
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove10() {
    this.defaultConstructor.move(0, 3, 3, 3);
  }


  // playing out a game using the move method repeatedly only with valid moves, and checking to
  // see if the game is over after every move. isGameOver should return false until the game
  // has actually ended and there are no more valid moves
  @Test
  public void testIsGameOver() {
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(3, 1, 3, 3);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(1, 2, 3, 2);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(1, 4, 1, 2);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(0, 2, 2, 2);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(0, 4, 0, 2);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(3, 2, 1, 2);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(0, 2, 2,2);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(3, 3, 1, 3);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(2, 1, 2,3);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(2, 3, 0, 3);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(2, 5, 2, 3);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(4, 5, 2, 5);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(2, 6, 2, 4);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(4, 6, 2, 6);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(2, 3, 2, 5);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(2, 6, 2, 4);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(3, 4, 1, 4);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(5, 4, 3, 4);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(4, 2, 4, 4);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(4, 4, 2, 4);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(2, 4, 0, 4);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(0, 4, 0, 2);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(6, 3, 4, 3);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(6, 2, 4, 2);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(4, 2, 4, 4);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(4, 0, 4, 2);
    assertFalse(this.defaultConstructor.isGameOver());
    this.defaultConstructor.move(2, 0, 4, 0);

    // THERE ARE NO LONGER ANY VALID MOVES, THUS, THE GAME IS OVER
    assertTrue(this.defaultConstructor.isGameOver());
  }

  // testing the method getBoardSize on different sizes of armLengths
  @Test
  public void testGetBoardSize() {
    assertEquals(7, this.defaultConstructor.getBoardSize());
    assertEquals(7, this.specifiedEmptySlot.getBoardSize());
    assertEquals(13, this.armLength5.getBoardSize());
    assertEquals(13, this.specifiedEmptySlotAndArmLength5.getBoardSize());
    assertEquals(19, this.armLength7.getBoardSize());
  }


  // testing different SlotStates of the board constructed by the default constructor
  @Test
  public void testGetSlotAtWithDefaultConstructor() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultConstructor.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.defaultConstructor.getSlotAt(5, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.defaultConstructor.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.defaultConstructor.getSlotAt(0, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.defaultConstructor.getSlotAt(6, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.defaultConstructor.getSlotAt(5, 6));
  }

  // testing different SlotStates of the board constructed by the constructor which
  // specifies arm length
  @Test
  public void testGetSlotAtWithSpecifiedArmLengthConstructor() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.armLength5.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.armLength5.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.armLength5.getSlotAt(12, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.armLength5.getSlotAt(7, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.armLength5.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.armLength5.getSlotAt(12, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.armLength5.getSlotAt(11, 9));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.armLength5.getSlotAt(3, 10));
  }

  // testing different SlotStates of the board constructed by the constructor which
  // specifies the empty slot
  @Test
  public void testGetSlotAtWithSpecifiedEmptySlotConstructor() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.specifiedEmptySlot.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.specifiedEmptySlot.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.specifiedEmptySlot.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.specifiedEmptySlot.getSlotAt(5, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.specifiedEmptySlot.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.specifiedEmptySlot.getSlotAt(0, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.specifiedEmptySlot.getSlotAt(6, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.specifiedEmptySlot.getSlotAt(5, 6));
  }

  // testing different SlotStates of the board constructed by the constructor which
  // specifies arm length and the empty slot
  @Test
  public void testGetSlotAtWithSpecifiedEmptySlotAndArmLengthConstructor() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.specifiedEmptySlotAndArmLength5.getSlotAt(5, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.specifiedEmptySlotAndArmLength5.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.specifiedEmptySlotAndArmLength5.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.specifiedEmptySlotAndArmLength5.getSlotAt(12, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.specifiedEmptySlotAndArmLength5.getSlotAt(7, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.specifiedEmptySlotAndArmLength5.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.specifiedEmptySlotAndArmLength5.getSlotAt(12, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.specifiedEmptySlotAndArmLength5.getSlotAt(11, 9));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.specifiedEmptySlotAndArmLength5.getSlotAt(3, 10));
  }

  // testing for an exception when the specified slot is out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void testGetScoreAtOutOfBoundsSpot() {
    this.defaultConstructor.getSlotAt(0, 10);
  }

  // testing for another exception when the specified slot is out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void testGetScoreAtOutOfBoundsSpot2() {
    this.defaultConstructor.getSlotAt(-1, 4);
  }

  // testing for another exception when the specified slot is out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void testGetScoreAtOutOfBoundsSpot3() {
    this.defaultConstructor.getSlotAt(2, -5);
  }

  // testing for another exception when the specified slot is out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void testGetScoreAtOutOfBoundsSpot4() {
    this.defaultConstructor.getSlotAt(8, 3);
  }

  // testing the number of marbles on different sized boards
  @Test
  public void testGetScore() {
    assertEquals(32, this.defaultConstructor.getScore());
    assertEquals(104, this.armLength5.getScore());
    assertEquals(216, this.armLength7.getScore());
    assertEquals(32, this.specifiedEmptySlot.getScore());
    assertEquals(104, this.specifiedEmptySlotAndArmLength5.getScore());
  }
}
