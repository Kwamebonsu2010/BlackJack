package Players;

import java.util.ArrayList;

import DeckofCards.Card;
import DeckofCards.Deck;

public class Player {
	private ArrayList<Card> cards;
	private String name;
	private boolean fold;
	private boolean ready;

	public Player(String name) {
		this.name = name;
		this.fold = false;
		this.ready = false;
		cards = new ArrayList<Card>();
	}

	public String getName() {
		return name;
	}

	public int countCards() {
		return cards.size();
	}

	public ArrayList<Card> seeCards() {
		ArrayList<Card> myCards = new ArrayList<Card>();
		if (cards.size() > 0) {
			for (int i = 0; i < cards.size(); i++) {
				System.out.println(cards.get(i).getName() + " of " + cards.get(i).getSuit().getSuitName());
				myCards.add(cards.get(i));
			}
		}
		System.out.println();
		return myCards;
	}

	// To start game
	public void getCards(Card card1, Card card2) {
		cards.add(card1);
		cards.add(card2);
		getSum();
	}

	public int getSum() {
		int sum = 0;
		System.out.println("Your cards are: ");
		for (int i = 0; i < cards.size(); i++) {
			System.out.println(cards.get(i).getName() + " of " + cards.get(i).getSuit().getSuitName());
			sum += cards.get(i).getValue();
		}
		// check for aces
		if (sum > 21) {
			for (int i = 0; i < cards.size(); i++) {
				if (cards.get(i).getName().equals("Ace")) {
					cards.get(i).setValue(1);
					sum -= 10;
					break;
				}
			}
			getSum();
		}
		evaluate(sum);

		return sum;
	}

	public void evaluate(int sum) {
		if (sum > 21) {
			fold = true;
			ready = true;
			System.out.print("FOLD! ");
		}
	}

	public int calculateSum() {
		int sum = 0;
		for (int i = 0; i < cards.size(); i++) {
			sum += cards.get(i).getValue();
		}
		return sum;
	}

	public void Hit(Card card) {
		System.out.println("Hit me!");
		if (!fold) {
			cards.add(card);
			System.out.println("Your drew a " + card.getName() + " of " + card.getSuit().getSuitName() + "\n");
			getSum();
		}
	}

	public void askKwame() {
		Deck myDeck = Deck.getInstance();
		int maxWanted = 21 - getSum();
		int counter = 0;
		for (int i = 0; i < myDeck.getCardsRemaining(); i++) {
			if (myDeck.getStack().get(i).getValue() <= maxWanted) {
				counter++;
			}
		}
	}

	public void reset() {
		cards.clear();
	}

	public void setReady() {
		this.ready = true;
	}

	public boolean isReady() {
		return ready;
	}
}
