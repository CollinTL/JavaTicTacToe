package cpsc2150.homeworks.hw3;

/**
 *
 * Collin Lloyd
 * ctlloyd
 * cpsc2150
 * hw1
 * BoardPostion is for holding the player who put coordinates on the board
 * this is a indirect form of UI
 *
 */

/**
 *
 * @invariants
 * row >= 0 and column >= 0
 * row <=8 and column <= 8
 *
 */
public class BoardPosition {

    private int row = 0;
    private int column = 0;
    private char player;

    /**
     *
     * @param x <= 8 and x >= 0
     * @param y <= 8 and x >= 0
     * @param p is a char
     * @requires
     * 0 <= x >= 8
     * 0 <= y >= 8
     * p = (X or O)
     * @ensures
     * row = x
     * column = y
     * player = p
     */
    BoardPosition(int x, int y, char p) {

        row = x;
        column = y;
        player = p;

    }

    /**
     *
     * @return and int
     * @requires
     * this != null
     * @ensures
     * getRow = row
     *
     */
    public int getRow() {
       return row;
    }

    /**
     *
     * @return and int
     * @requires
     * this != null
     * @ensures
     * getColumn = column
     *
     */
    public int getColumn() {
        return column;
    }

    /**
     *
     * @return a char
     * @requires
     * this != null
     * @ensures
     * getPlayer = player
     *
     */
    public char getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object other) {

        if(other == this) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {

        return String.format("Player" + player + "at position" + row + "," + column);

    }


}
