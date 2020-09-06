package DeckofCards;

import java.util.Stack;

public class Deck {
	private int cardsRemaining;
	private Stack<Card> myDeck;
	private static Deck instance = null;

	// Singleton Class
	public static Deck getInstance() {
		if (instance == null) {
			instance = new Deck();
		}
		return instance;
	}

	// Constructor
	public Deck() throws IllegalArgumentException {
		myDeck = new Stack<Card>();
		fillDeck();
//		Shuffle();
		cardsRemaining = myDeck.size();
	}

	public void Shuffle() {
		int size = cardsRemaining;
		// Create array
		Card[] tempDeck = new Card[size];
		// move all cards to array
		for (int i = 0; i < size; i++) {
			tempDeck[i] = myDeck.pop();
		}

		// Randomly swap a set number of times
		for (int i = 0; i < tempDeck.length * 100; i++) {
			int pos1 = (int) (Math.random() * (tempDeck.length));
			int pos2 = (int) (Math.random() * (tempDeck.length));
			// Make sure the 2 card positions are different
			while (pos1 == pos2) {
				pos2 = (int) (Math.random() * (tempDeck.length));
			}
			// Perform the swap
			Card temp;
			temp = tempDeck[pos1];
			tempDeck[pos1] = tempDeck[pos2];
			tempDeck[pos2] = temp;
		}

		// Put the cards back in the stack
		for (int i = 0; i < tempDeck.length; i++) {
			myDeck.push(tempDeck[i]);
		}
	}

	// Give 1 card from the top of the deck
	public Card giveCard() {
		if (cardsRemaining > 0) {
			cardsRemaining--;
			return myDeck.pop();
		} else {
			System.out.println("No more cards!!!");
			return null;
		}
	}

	public void printDeck() {
		Card[] cardArray = new Card[myDeck.size()];
		Stack<Card> simDeck = myDeck;
		for (int i = 0; i < cardArray.length; i++) {
			Card current = simDeck.pop();
			cardArray[i] = current;
			System.out.println(current.getName() + " of " + current.getSuit().getSuitName());
		}
		// put the cards back in simDeck so this can be repeated
		for (int i = 0; i < cardArray.length; i++) {
			simDeck.push(cardArray[cardArray.length - 1 - i]);
		}
	}

	// reset the deck for a new game
	public void reset() {
		myDeck.clear();
		fillDeck();
		cardsRemaining = myDeck.size();
		Shuffle();
	}

	private void fillDeck() {
/*		// Ace
		Card ace = new Card(new Suit("Hearts"), 1);
		ace.setValue(11);
		myDeck.push(ace);
		ace = new Card(new Suit("Diamonds"), 1);
		ace.setValue(11);
		myDeck.push(ace);
		ace = new Card(new Suit("Clubs"), 1);
		ace.setValue(11);
		myDeck.push(ace);
		ace = new Card(new Suit("Spades"), 1);
		ace.setValue(11);
		myDeck.push(ace);
	*/	
		// Fill the deck
		for (int i = 1; i < 14; i++) {
			// Create 1 of each suit
			for (int k = 0; k < 4; k++) {

				Suit current = null;
				if (k == 0) {
					current = new Suit("Hearts");
				} else if (k == 1) {
					current = new Suit("Diamonds");
				} else if (k == 2) {
					current = new Suit("Clubs");
				} else if (k == 3) {
					current = new Suit("Spades");
				} else {
					throw new IllegalArgumentException();
				}
				Card tempCard = new Card(current, i);

				myDeck.push(tempCard);
			}
		}
	}

	public int getCardsRemaining() {
		return cardsRemaining;
	}

	public Stack<Card> getStack() {
		return myDeck;
	}
}
