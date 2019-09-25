package cpsc2150.homeworks.hw3;

/**
 *
 *Collin Lloyd(ctlloyd)
 *CPSC 2150
 *HomeWork 3
 *
 *
 */

public class GameBoardFast implements IGameBoard {

    private char[][] gameBoard;
    private int rows;
    private int columns;
    private int nToWin;

    /**
     *
     * @param r int for rows
     * @param c int for columns
     * @param n int for number to win
     * @requires
     * this != null
     *  0 < r < MAX_SIZE and 0 < c < MAX_SIZE and 0 < n < (r or c[depends on the smallest])
     * @ensures
     * this == GameBoardFast
     * rowsFor = r;
     * COlumnsFor = c
     * WinC = n
     */
    GameBoardFast(int r, int c, int n) {

        rows = r;
        columns = c;
        nToWin = n;

        gameBoard = new char[MAX_SIZE][MAX_SIZE];

        char empty = ' ';
        for(int i = 0; i < MAX_SIZE; i++) {
            for(int j = 0; j < MAX_SIZE; j++) {
                gameBoard[i][j] = empty;
            }
        }

    }

    @Override
    public boolean checkSpace(BoardPosition pos) {

        //returns true if the position specified in pos is available, false otherwise
        if(pos.getRow() >= rows || pos.getRow() < 0) {
            return false;
        }

        if(pos.getColumn() >= columns|| pos.getColumn() < 0) {
            return false;
        }

        if(gameBoard[pos.getRow()][pos.getColumn()] != ' ') {
            return false;
        }

        return true;
    }

    @Override
    public void placeMarker(BoardPosition marker){

        //places the character in marker on the position specified by marker
        gameBoard[marker.getRow()][marker.getColumn()] = marker.getPlayer();

    }

    @Override
    public boolean checkForWinner(BoardPosition lastPos) {

        //this function will check to see if the lastPos placed resulted in a winner. It so it will return true, otherwise false.
        // Passing in the last position will help limit the possible places to check for a win condition,
        // since you can assume that any win condition that did not include the most recent play made would have been caught earlier.
        if (checkHorizontalWin(lastPos)) {
            return true;
        }
        if (checkVerticalWin(lastPos)) {
            return true;
        }
        if (checkDiagonalWin(lastPos)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean checkForDraw() {

        //this function will check to see if the game has resulted in a tie.
        // A game is tied if there are no free board positions remaining.
        // It will return true if the game is tied, and false otherwise.
        char empty = ' ';
        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < columns; ++j) {
                if(gameBoard[i][j] == empty) {
                    return false;
                }
            }
        }

        return true;

    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();
        String newLine = System.lineSeparator();

        int width[] = new int[rows];
        int length[] = new int[columns];

        for(int i = 0; i < rows; ++i) {
            width[i] = i;
        }
        for(int i = 0; i < columns; ++i) {
            length[i] = i;
        }

        result.append("  ");
        for(int i = 0; i < columns; ++i) {
            result.append(+ length[i] + "|");
        }
        result.append(newLine);
        for(int i = 0; i < rows; ++i) {
            result.append(+ width[i] + "|");
            for(int j = 0; j < columns; ++j) {
                result.append(gameBoard[i][j] + "|");
            }
            result.append(newLine);
        }

        return result.toString();
    }

    /**
     *
     * @param lastPos
     * @return a boolean
     * @requires
     * lastPos = new BoardPostion();
     * lastPos != null
     * @ensures
     * checkHorizontalWin = true iff gameBoard[x][0-7] [contains 5 X or O in a row]
     * returns false otherwise
     *
     */
    private boolean checkHorizontalWin(BoardPosition lastPos) {

        //checks to see if the last marker placed resulted in 5 in a row horizontally.
        // Returns true if it does, otherwise false
        int checkRow = lastPos.getRow(); // to get row to check all spaces in row
        char player = lastPos.getPlayer();
        int i = 0;

        while(i != rows) {

            if(gameBoard[checkRow][i] == player) {
                int vCounter = 1;
                for(int j = 1; j < nToWin; ++j) {
                    if(i + j >= rows) {
                        continue;
                    }
                    //this is were checkFive comes in handy, will ONLY check the 5 spaces in consecutive order
                    //if all five contain the correct char, then it is a win
                    if(gameBoard[checkRow][i + j] == player) {
                        vCounter = vCounter + 1;
                    }
                }
                if (vCounter == nToWin) { //reaches 5 its a win
                    return true;
                }
            }

            ++i;
        }

        return false;
    }

    /**
     *
     * @param lastPos
     * @return a boolean
     * @requires
     * lastPos = new BoardPostion();
     * lastPos != null
     * @ensures
     * checkVerticalWin = true iff gameBoard[0-7][y] [contains 5 X or O in a row]
     * returns false otherwise
     *
     */
    private boolean checkVerticalWin(BoardPosition lastPos){

        //checks to see if the last marker placed resulted in 5 in a row vertically.
        // Returns true if it does, otherwise false
        int checkCol = lastPos.getColumn();
        char player = lastPos.getPlayer();
        int i = 0;

        while(i != columns) { //make sure not to exceed bounds

            if(gameBoard[i][checkCol] == player) { //get column to check all spaces in column
                int vCounter = 1;
                int j;
                for(j = 1; j < nToWin; ++j) {
                    if(i + j >= columns) {
                        continue;
                    }
                    //this is were checkFive comes in handy, will ONLY check the 5 spaces in consecutive order
                    //if all five contain the correct char, then it is a win
                    if(gameBoard[i + j][checkCol] == player) {
                        vCounter = vCounter + 1;
                    }
                }
                if (vCounter == nToWin) {
                    return true;
                }
            }

            ++i;
        }

        return false;
    }

    /**
     *
     * @param lastPos
     * @return a boolean
     * @requires
     * lastPos = marker;
     * lastPos != null
     * @ensures
     * checkVerticalWin = true iff gameBoard[x][x] [contains 5 X or O in a row]
     * returns false otherwise
     *
     */
    private boolean checkDiagonalWin(BoardPosition lastPos){

        //checks to see if the last marker placed resulted in a win diagonally.
        // Returns true if it does, otherwise false
        // Note: there are two diagonals to check

        char player = lastPos.getPlayer();
        int row = lastPos.getRow();
        int column = lastPos.getColumn();
        int winner = 0;

        while((row >= 0 && column < columns) && gameBoard[row][column] == player) {
                winner++;
                --row;
                ++column;
        }
        if(winner == nToWin) {
            return true;
        }
        row = lastPos.getRow() + 1;
        column = lastPos.getColumn() - 1;
        while((row < rows && column >= 0) && gameBoard[row][column] == player) {
            winner++;
            ++row;
            --column;
        }
        if(winner == nToWin) {
            return true;
        }

        winner = 0;
        row = lastPos.getRow();
        column = lastPos.getColumn();

        while((row < rows && column < columns) && gameBoard[row][column] == player) {
            winner++;
            row++;
            column++;
        }
        if(winner == nToWin) {
            return true;
        }

        row = lastPos.getRow() - 1;
        column = lastPos.getColumn() - 1;
        while((row >= 0 && column >= 0) && gameBoard[row][column] == player) {
            winner++;
            row--;
            column--;
        }
        if(winner == nToWin) {
            return true;
        }

        return false;
    }

}
