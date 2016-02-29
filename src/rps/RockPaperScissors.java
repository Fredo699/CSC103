package rps;

import java.lang.Math;
import java.util.Scanner;

/**
 * @author Fred Frey
 * @version 0.01
 * @since 2016-02-02
 */

public class RockPaperScissors 
	{
	/**
	 * Returns string representation of game piece.
	 * 
	 * @param piece an integer representing the game piece
	 * @return the name of the game piece, or "-1" for error.
	 */
	private static String get_piece(int piece)
		{
		if (piece == 0)
			return "rock";
		if (piece == 1)
			return "paper";
		if (piece == 2)
			return "scissors";
		return "-1";
		}
	
	public static void main(String[] args)
		{
		int player_choice, computer_choice,
		player_wins=0, computer_wins=0, tie_games=0;
		
		Scanner input = new Scanner(System.in);
		for(int i = 0; i < 10; i++)
			{
			System.out.println("0 = rock, 1 = paper, 2 = scissors");
			System.out.print("Choose: ");
			player_choice = input.nextInt();
			computer_choice = (int) (Math.random() * 3);
			
			System.out.println("Round " + i);
			System.out.println("Computer choice: " + get_piece(computer_choice));
			System.out.println("Player choice: " + get_piece(player_choice));
			
			/* Check if tie, and restart loop, if so. */
			if (player_choice == computer_choice)
				{
				System.out.println("Result: tie.");
				tie_games += 1;
				continue;
				}
			/*
			 * If the choice is 0, then there are issues comparing the pieces.
			 * These ternary operators make sure that the pieces are in a state 
			 * where we can just compare them, and tell who won.
			 */
			computer_choice = (computer_choice == 0) ? ((player_choice == 1) ? 0 : 3) : computer_choice;
			player_choice = (player_choice == 0) ? ((computer_choice == 1) ? 0 : 3) : player_choice;
			
			/* Check who won, and increment appropriate counter. */
			if (computer_choice > player_choice)
				{
				System.out.println("Result: computer win.");
				computer_wins += 1;
				}
			else
				{
				System.out.println("Result: player win.");
				player_wins += 1;
				}
			
			}
		
		System.out.println("Comp. Wins\tPlayer Wins\tDraws");
		System.out.println(computer_wins + "\t\t" + player_wins + "\t\t" + tie_games);
		input.close();
		}
	}
