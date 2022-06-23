package tictactoe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 * TO DO: 
 * implement winner determination
 * if user tries to insert their X or O in a cell that is already taken, make them try again
 * 
 */
public class game {

	public static boolean winner = false;
	public static int turn = 0; //keeps track of X and O turns
	public static char[][] board = {{' ', '|', ' ', '|', ' '}, 
								    {'-', '+', '-', '+', '-'},
									{' ', '|', ' ', '|', ' '},
									{'-', '+', '-', '+', '-'},
									{' ', '|', ' ', '|', ' '}};;
	public static char win = ' ';
	//arrays to keep track of the cell numbers each player has used
	public static ArrayList<Integer> playerX = new ArrayList<Integer>();
	public static ArrayList<Integer> playerO = new ArrayList<Integer>();
	public static void main(String[] args) {
		//loop that will continue as long as no player has lost
		System.out.println("Cells are numbered 1-9.");
		while (winner == false && turn < 9) {
			printBoard();
			play();
		}
		if (turn == 9) {
			printBoard();
			System.out.println("No player wins.");
		}
		else if (winner = true) {
			System.out.println("Player "+win+" wins the game!");
			printBoard();
			}
	}
		
	/*
	 * Players take turns playing and altering the board.
	 * At the end of each turn, the turn variable is incremented and board is checked for winner
	 */
	public static void play () {
		char temp;
		if (turn % 2 == 0) { //player X's turn
			temp = 'X';
		}
		else { //player O's turn
			temp = 'O';
		}
		
		int pos = 10;
		@SuppressWarnings("resource")
		Scanner player = new Scanner (System.in);
		while (pos > 9 | pos < 0 | playerX.contains(pos) | playerO.contains(pos)){
			System.out.printf("Enter a valid cell number to place "+temp+" (1-9): ");
			pos = player.nextInt();
		}
		//based on pos input, alter the board with X/O
		switch (pos) {
		case 1:
			board[0][0] = temp;
			break;
		case 2:
			board[0][2] = temp;
			break;
		case 3:
			board[0][4] = temp;
			break;
		case 4:
			board[2][0] = temp;
			break;
		case 5: 
			board[2][2] = temp;
			break;
		case 6:
			board[2][4] = temp;
			break;
		case 7:
			board[4][0] = temp;
			break;
		case 8:
			board [4][2] = temp;
			break;
		case 9:
			board [4][4] = temp;
			break;
		}
		
		//add to player arrays and check if the player's turn caused a win
		if (temp == 'X') {
			playerX.add(pos);
			win = determineWinner(temp, playerX);
		}
		else {
			playerO.add(pos);
			win = determineWinner(temp, playerO);
		}
		turn++;
	}
	
	public static char determineWinner(char player, ArrayList<Integer> playerList) {
		//check if the list contains the winning combinations of cell numbers
		List<Integer> down1 = Arrays.asList(1,4,7);
		List<Integer> down2 = Arrays.asList(2,5,8);
		List<Integer> down3 = Arrays.asList(3,6,9);
		List<Integer> across1 = Arrays.asList(1,2,3);
		List<Integer> across4 = Arrays.asList(4,5,6);
		List<Integer> across7 = Arrays.asList(7,8,9);
		List<Integer> diag1 = Arrays.asList(1,5,9);
		List<Integer> diag3 = Arrays.asList(3,5,7);
		
		for (int e : playerList) {
			switch (e) {
			case 1:
				if (playerList.containsAll(down1) | playerList.containsAll(across1) | playerList.containsAll(diag1)) {
					winner = true; return player;
				}
				continue;
			case 2:
				if (playerList.containsAll(down2)) {
					winner = true; return player;
				}
				continue;
			case 3:
				if (playerList.containsAll(down3) | playerList.containsAll(diag3)) {
					winner = true; return player;
				}
				continue;
			case 4:
				if (playerList.containsAll(across4)) {
					winner = true; return player;
				}
				continue;
			case 5: 
				if (playerList.containsAll(diag1) | playerList.containsAll(diag3)) {
					winner = true; return player;
				}
				continue;
			case 6: 
				if (playerList.containsAll(across4) | playerList.containsAll(down3)) {
					winner = true; return player;
				}
				continue;
			case 7:
				if (playerList.containsAll(across7)) {
					winner = true; return player;
				}
				continue;
			case 8: 
				if (playerList.containsAll(across7)) {
					winner = true; return player;
				}
				continue;
			case 9: 
				if (playerList.containsAll(diag1) | playerList.containsAll(down3) | playerList.containsAll(across4)) {
					winner = true; return player;
				}
				continue;
			}
		}
		winner = false; return ' ';
	}
	
	
	public static void printBoard() {
		for (char[] row : board) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
}




