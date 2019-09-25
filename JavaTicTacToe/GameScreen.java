package cpsc2150.homeworks.hw3;

/**
 *
 * Collin Lloyd
 * ctlloyd
 * cpsc2150
 * hw1
 * GameSceen is the class that holds main and controls the flow of the game
 *
 */

import java.util.*;

/**
 *
 * @invariants
 * Player1 = 'X'
 * Player2 = 'O'
 */
public class GameScreen {

    public static void main(String[] args) {

        final int MAX_SIZE = 100;
        int rows;
        int columns;
        int players;
        int winC;
        char whatType;
        boolean winner = false; //boolean for winner
        boolean tied = false; //boolean if tied
        boolean playAgain = true; //boolean for asking to play again
        Scanner sc = new Scanner(System.in);
        char[] tokens = {'X', 'O', 'Y', 'Z', 'A', 'E', 'J', 'N', 'H'};

        while (playAgain) { //outer while loop shell for asking to play again

            do {
                System.out.println("How many rows shall the game board have?");
                rows = sc.nextInt();
                if (rows < 0 || rows > MAX_SIZE) {
                    System.out.println("Invalid amount of rows, please try again.");
                }
            } while (rows < 0 || rows > MAX_SIZE);

            do {
                System.out.println("How many players will the game have");
                players = sc.nextInt();
                if (players < 2 || players > 10) {
                    System.out.println("Too little or too less for players, please re enter between 2-10.");
                }
            } while (players < 2 || players > 10);

            char[] playersA = new char[players];
            for (int i = 0; i < players; ++i) {
                playersA[i] = tokens[i];
            }

            do {
                System.out.println("How many columns shall the game board have?");
                columns = sc.nextInt();
                if (columns < 0 || columns > MAX_SIZE) {
                    System.out.println("Too little or too much columns, please try again.");
                }
            } while (columns < 0 || columns > MAX_SIZE);

            do {
                System.out.println("How many tokens will it take to win?");
                winC = sc.nextInt();
                if (winC < 0 || winC > columns || winC > rows) {
                    System.out.println("Too little or too much columns, please try again.");
                }
            } while (winC < 0 || winC > columns || winC > rows);

            do {
                System.out.println("Would you like a fast implementation or memory?");
                System.out.println("F for fast or M for memory");
                whatType = sc.next().charAt(0);
                if ((whatType != 'f' && whatType != 'F') && (whatType != 'm' && whatType != 'M')) {
                    System.out.println("Enter en F or M for the type on implementation, try again.");
                }
            } while ((whatType != 'f' && whatType != 'F') && (whatType != 'm' && whatType != 'M'));

            IGameBoard Board;
            if (whatType == 'f' || whatType == 'F') {
                Board = new GameBoardFast(rows, columns, winC);
            } else {
                Board = new GameBoardMem(rows, columns, winC);
            }

            int playerC = 0;
            while ((!winner && !tied) && playerC <= players) { //main while loop for the game
                    System.out.println(Board);
                    System.out.println("Player " + playersA[playerC] + " Please enter your ROW");
                    int row = sc.nextInt();
                    System.out.println("Player " + playersA[playerC] + " Please enter your COLUMN");
                    int column = sc.nextInt();

                    BoardPosition entry = new BoardPosition(row, column, playersA[playerC]); //creating board pos for player

                    //if statements for checking if valid place to put marker.if not, prompts user to try again.
                    //if so, marker is placed and then check calls for winner is called
                    //if noo winner, checks to call if draw, if no draw then flips turn counter and continues
                    if (Board.checkSpace(entry)) {
                        Board.placeMarker(entry);
                        if (Board.checkForWinner(entry)) {
                            winner = true;
                            System.out.println("Player " + playersA[playerC] + " Wins!");
                        } else if (Board.checkForDraw()) {
                            tied = true;
                            System.out.println("Sorry, game has ended in a draw.");
                        }
                    } else {
                        System.out.println("That space is unavailable, please pick again.");
                        System.out.println();
                        playerC--;
                    }

                    if (playerC == players - 1) {
                        playerC = 0;
                    }
                    else {
                        playerC++;
                    }
                }
                //Segment of code to prompt user if wants to play again
                System.out.println("Would you like to play again?, enter Y or y for yes");
                char yes = sc.next().charAt(0);
                if (yes == 'Y' || yes == 'y') { //if yes, resets all variables to be used again
                    winner = false;
                    tied = false;
                } else {
                    System.out.println("Ok, goodbye.");
                    playAgain = false;
                }
        }
    }
}
