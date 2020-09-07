package DeckofCards;

public class Card {
	private int value;
	private Suit suit;
	private String name;

	public Card(Suit suit, int value) throws IllegalArgumentException {
		this.suit = suit;
		this.value = value;
		if (value < 1 || value > 13) {
			throw new IllegalArgumentException("Invalid Card Value");
		}
		// Face cards
		if (value == 1) {
			this.name = "Ace";
			this.value = 11;
		} else if (value < 11) {
			this.name = "" + value;
		} else if (value == 11) {
			this.name = "Jack";
			this.value = 10;
		} else if (value == 12) {
			this.name = "Queen";
			this.value = 10;
		} else if (value == 13) {
			this.name = "King";
			this.value = 10;
		}
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public Suit getSuit() {
		return this.suit;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
