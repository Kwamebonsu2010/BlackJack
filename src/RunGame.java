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

		while (!CPU3.isReady()) {
			while (!CPU2.isReady()) {
				while (!CPU1.isReady()) {
					while (!user.isReady()) {
						System.out.print(userName + ", would you like to hit or submit?");
						System.out.println("  (enter 'hit' to hit and anything else to submit)");
//						Thread.sleep(1000);
						if (input.next().equalsIgnoreCase("hit")) {
							user.Hit(myDeck.giveCard());
//							Thread.sleep(1000);
						} else {
							user.setReady();
//							Thread.sleep(1000);
						}
						System.out.println("\n");
					}
					System.out.println("Mike" + ", would you like to hit or submit?");
//					Thread.sleep(1000);
					if (!CPU1.play()) {
						CPU1.Hit(myDeck.giveCard());
//						Thread.sleep(1000);
					} else {
						System.out.println("I'm good");
						CPU1.setReady();
//						Thread.sleep(1000);
					}

					System.out.println("\n");

				}
				System.out.println("Sarah" + ", would you like to hit or submit?");
//				Thread.sleep(1000);
				if (!CPU2.play()) {
					CPU2.Hit(myDeck.giveCard());
//					Thread.sleep(1000);
				} else {
					System.out.println("I'm good");
					CPU2.setReady();
//					Thread.sleep(1000);
				}

				System.out.println("\n");
			}
			System.out.println("Tyrone" + ", would you like to hit or submit?");
//			Thread.sleep(1000);
			if (!CPU3.play()) {
				CPU3.Hit(myDeck.giveCard());
//				Thread.sleep(1000);
			} else {
				System.out.println("I'm good");
				CPU3.setReady();
//				Thread.sleep(1000);
			}

			System.out.println("\n");
		}

		System.out.println("\n\n");
		user.seeCards();
		System.out.println(userName + " has a score of " + user.calculateSum());
		CPU1.seeCards();
		System.out.println("Mike has a score of " + CPU1.getSum());
		CPU2.seeCards();
		System.out.println("Sarah has a score of " + CPU2.getSum());
		CPU3.seeCards();
		System.out.println("Tyrone has a score of " + CPU3.getSum());

		declareWinner(user, CPU1, CPU2, CPU3);
	}

	// Who is the closest to 21 without going over?
	// In case of a tie, the player with the fewest cards wins
	public static void declareWinner(Player p1, Player p2, Player p3, Player p4) {
		//
		//
		// FIX THIS WHOLE METHOD
		//
		//
		//
		Player highest = p1;
		Player second = p1;
		Player[] players = new Player[4];
		players[0] = p1;
		players[1] = p2;
		players[2] = p3;
		players[3] = p4;

		for (int i = 1; i < players.length; i++) {
			if (players[i].calculateSum() > highest.calculateSum() && players[i].calculateSum() <= 21) {
				highest = players[i];
			} else if (players[i].calculateSum() == highest.calculateSum()) {
				second = players[i];
			}
		}
		if (highest.calculateSum() != second.calculateSum()) {
			System.out.println(highest.getName() + " wins with a score of " + highest.calculateSum());
		} else if (highest.countCards() == second.countCards() && highest.calculateSum() == second.calculateSum()) {
			System.out.println(
					highest.getName() + " and " + second.getName() + " tie with a score of: " + highest.calculateSum());
		} else if (highest.countCards() < second.countCards() && highest.calculateSum() == second.calculateSum()) {
			System.out.println(highest.getName() + " wins with a score of " + highest.calculateSum());
		} else {
			System.out.println(second.getName() + " wins with a score of " + second.calculateSum());
		}

	}
}
