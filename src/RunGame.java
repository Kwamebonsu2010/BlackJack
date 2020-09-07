import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import DeckofCards.Card;
import DeckofCards.Deck;
import Players.AIPlayer;
import Players.Player;

public class RunGame {

	public static void main(String[] args) throws InterruptedException {
		// Scanner
		Scanner input = new Scanner(System.in);
		// Create Players
		/*
		 * System.out.
		 * println("How many players would you like to play against? (Select a number between 1 and 3"
		 * ); // Make sure between 1 and 3 players selected int num = input.nextInt();
		 * if(num < 1) { num = 1; } else if( num > 3) { num = 3; }
		 */
		// Create user
		System.out.println("What is your name?");
		String userName = input.next();
		Player user = new Player(userName);

		// Create AI players
		AIPlayer CPU1 = new AIPlayer("Mike");
		AIPlayer CPU2 = new AIPlayer("Sarah");
		AIPlayer CPU3 = new AIPlayer("Tyrone");

		// Create Deck
		Deck myDeck = Deck.getInstance();
		myDeck.Shuffle();
		// Deal Cards
		user.getCards(myDeck.giveCard(), myDeck.giveCard());
		CPU1.getCards(myDeck.giveCard(), myDeck.giveCard());
		CPU2.getCards(myDeck.giveCard(), myDeck.giveCard());
		CPU3.getCards(myDeck.giveCard(), myDeck.giveCard());
		// User plays first followed by AI players
		while (!CPU3.isReady()) {
			while (!CPU2.isReady()) {
				while (!CPU1.isReady()) {
					while (!user.isReady()) {
						System.out.print(userName + ", would you like to hit or submit?");
						System.out.println("  (enter 'hit' to hit and anything else to submit)");
						Thread.sleep(1000);
						if (input.next().equalsIgnoreCase("hit")) {
							user.Hit(myDeck.giveCard());
							Thread.sleep(1000);
						} else {
							user.setReady();
							Thread.sleep(1000);
						}
						System.out.println("\n");
					}
					System.out.println("Mike" + ", would you like to hit or submit?");
					Thread.sleep(1000);
					if (!CPU1.play()) {
						CPU1.Hit(myDeck.giveCard());
						Thread.sleep(1000);
					} else {
						System.out.println("I'm good");
						CPU1.setReady();
						Thread.sleep(1000);
					}

					System.out.println("\n");

				}
				System.out.println("Sarah" + ", would you like to hit or submit?");
				Thread.sleep(1000);
				if (!CPU2.play()) {
					CPU2.Hit(myDeck.giveCard());
					Thread.sleep(1000);
				} else {
					System.out.println("I'm good");
					CPU2.setReady();
					Thread.sleep(1000);
				}

				System.out.println("\n");
			}
			System.out.println("Tyrone" + ", would you like to hit or submit?");
			Thread.sleep(1000);
			if (!CPU3.play()) {
				CPU3.Hit(myDeck.giveCard());
				Thread.sleep(1000);
			} else {
				System.out.println("I'm good");
				CPU3.setReady();
				Thread.sleep(1000);
			}

			System.out.println("\n");
		}
		// Output the cards of the players
		System.out.println("\n\n");
		user.seeCards();
		System.out.println(userName + " has a score of " + user.calculateSum());
		CPU1.seeCards();
		System.out.println("Mike has a score of " + CPU1.getSum());
		CPU2.seeCards();
		System.out.println("Sarah has a score of " + CPU2.getSum());
		CPU3.seeCards();
		System.out.println("Tyrone has a score of " + CPU3.getSum() + "\n");
		// Declare the winner
		declareWinner(user, CPU1, CPU2, CPU3);
	}

	// Who is the closest to 21 without going over?
	// In case of a tie, the player with the fewest cards wins
	public static void declareWinner(Player p1, Player p2, Player p3, Player p4) {
		// Used to determine the tense of the word 'win'
		boolean tie = false;
		Player[] players = { p1, p2, p3, p4 };
		players = sort(players);
		// Everyone folds
		if (players[0].calculateSum() > 21) {
			System.out.println("Everybody folded! There is no winner!");
		} else {
			int temp = 0;
			if (players[players.length - 1].calculateSum() <= 21) {
				temp = players.length - 1;
			} else {
				while (players[temp + 1].calculateSum() <= 21 && temp < players.length - 2) {
					temp++;

				}
			}
			// Check for tie
			for (int i = 0; i < players.length; i++) {
				if (i != temp) {
					if (players[i].calculateSum() == players[temp].calculateSum()) {
							System.out.print(players[i].getName() + " and ");
							tie = true;
					}
				}
			}
			if (tie == false) {
				System.out.println(players[temp].getName() + " wins!");
			} else {
				System.out.println(players[temp].getName() + " win!");
			}
		}

	}

	// Bubble Sort Players by their sum attribute
	private static Player[] sort(Player[] players) {
		for (int i = 0; i < players.length; i++) {
			for (int j = 0; j < players.length; j++) {
				if (players[i].calculateSum() < players[j].calculateSum()) {
					Player temp = players[i];
					players[i] = players[j];
					players[j] = temp;
				}
			}
		}
		return players;
	}
}
