package cpsc2150.homeworks.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *Collin Lloyd
 *CPSC 2150
 *HomeWork 3
 *
 */ 
public class GameBoardMem implements IGameBoard{

    private int rows;
    private int columns;
    private int nToWin;
    Map<Character, List<BoardPosition>> myMap;

    GameBoardMem(int r, int c, int n) {

        rows = r;
        columns = c;
        nToWin = n;

        myMap = new HashMap<>();

    }

    @Override
    public boolean checkForDraw() {

        System.out.println("Error, not implemented yet.");
        return false;
    }
    @Override
    public boolean checkForWinner(BoardPosition lastPos) {
        System.out.println("Error, not implemented yet.");
        return false;
    }

    @Override
    public boolean checkSpace(BoardPosition pos) {

        System.out.println("Error, not implemented yet.");
        //returns true if the position specified in pos is available, false otherwise
        if(pos.getRow() >= rows || pos.getRow() < 0) {
            return false;
        }

        if(pos.getColumn() >= columns|| pos.getColumn() < 0) {
            return false;
        }

        return true;
    }

    @Override
    public void placeMarker(BoardPosition lastPos) {
        System.out.println("Error, not implemented yet.");
        myMap.get(lastPos.getPlayer()).add(lastPos);
    }
}
