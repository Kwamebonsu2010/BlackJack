package Players;

import java.util.ArrayList;

import DeckofCards.Card;
import DeckofCards.Deck;

public interface PlayerAbstract {

	public default void seeCards() {
		
	}

	// To start game
	public default void getCards(Card card1, Card card2) {
	}

	public default int getSum() {
		return 0;
	}


	public default void reset() {
		
	}
}
