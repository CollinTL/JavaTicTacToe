package cpsc2150.homeworks.hw3;

/**
 *
 *Collin Lloyd(ctlloyd)
 *CPSC 2150
 *Homework 3
 *
 */



/**
 * IGameBoard represents a 2-dimensional gameboard that has characters
 * on it as markers. No space on the board can have multiple
 * players, and there can be a clear winner. Board is NUM_ROWS x
 NUM_COLS in size. Indexing of the gameboard starts in the top left
 corner with 0,0 and goes to NUM_ROWS-1, NUM_COLS-1.
 *
 * Initialization ensures: the Board does not have any markers on it
 * Defines: NUM_ROWS: Z
 * Defines: NUM_COLS: Z
 * Constraints: 0< NUM_ROWS <= MAX_SIZE
 * 0< NUM_COLS <= MAX_SIZE
 */
public interface IGameBoard {
    int MAX_SIZE = 100;

    /**
     *
     * @param pos
     * @return a boolean
     * @reqires
     * this != null
     * pos != null
     * @ensures
     * checkSpace = true iff( 8 > pos.getRow() >= 0 and 8 > pos.getColumn >= 0 and
     * board[r][c] = ' ')
     * checkSpace = false if (pos.getRow() >= 8 or pos.getRow < 0 or Pos.getColumn >= 8 or
     * pos.getColumn < 0 or board[r][c] != ' ';)
     *
     */
    boolean checkSpace(BoardPosition pos);

    /**
     *
     * @parais of type BoardPosition
     * @requires
     * checkSpace = true
     * marker != null
     * @ensures
     * board[marker.r][marker.c] = marker.player;
     *
     */
    void placeMarker(BoardPosition lastPos);

    /**
     *
     * @param lastPos
     * @return a boolean
     * @requires
     * lastPos = marker
     * [all three checksfor must be initialized]
     * @ensures
     * checkForWinner = true if(checkHorizontalWinner = true or checkVerticalWinner =  true or checkDiagonalWinner = true)
     * checkForWinner = false iff (checkHorizontalWinner != true and checkVerticalWinner !=  true and checkDiagonalWinner != true)
     */
    boolean checkForWinner(BoardPosition lastPos);

    /**
     *
     * @return a boolean
     * @requries
     * checkWinner = false;
     * [This way there should be no use in calling this if there is a winner]
     * @esnures
     * checkForDraw = true iff checkWinner = false and (board[0][0] != ' ' and board[0][1] != ' '
     * and board[0][2] != ' ' and ... and board[7][6] != ' ' and board[7][7] != ' ')
     * checkForDraw = false if checkForWinner = true or (board[0][0] = ' ' or board[0][1] = ' '
     * or board[0][2] = ' ' or ... or board[7][6] = ' ' or board[7][7] = ' ')
     *
     */
    boolean checkForDraw();
}
