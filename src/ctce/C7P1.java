package ctce;

import java.util.Queue;

public class C7P1 {
    abstract class Deck {
        Queue<Card> cards;
        int cardCount;

        abstract void shuffle();
    }

    class Card {
        Suit suit;
        int number;
    }

    enum Suit {
        DIAMOND,
        CLUB,
        SPADES,
        HEART
    }
}
