package org.wigs;

import java.util.*;

class Card {
    private final String suit;
    private final String rank;
    private final int value;

    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

class Deck {
    private final List<Card> cards = new ArrayList<>();
    private static final String[] SUITS = {"Hearts", "Diamonds", "Clubs", "Spades"};
    private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    public Deck() {
        for (int i = 0; i < RANKS.length; i++) {
            for (String suit : SUITS) {
                cards.add(new Card(suit, RANKS[i], i + 2));
            }
        }
        Collections.shuffle(cards);
    }

    public List<Card> getCards() {
        return cards;
    }
}

class Player {
    private final Queue<Card> hand = new LinkedList<>();

    public Player(List<Card> cards) {
        hand.addAll(cards);
    }

    public boolean hasCards() {
        return !hand.isEmpty();
    }

    public Card playCard() {
        return hand.poll();
    }

    public void collectCards(List<Card> wonCards) {
        hand.addAll(wonCards);
    }

    public int getCardCount() {
        return hand.size();
    }
}

public class WarCardGame {
    public static void main(String[] args) {
        Deck deck = new Deck();
        List<Card> cards = deck.getCards();
        Player player1 = new Player(cards.subList(0, 26));
        Player player2 = new Player(cards.subList(26, 52));

        int round = 1;
        while (player1.hasCards() && player2.hasCards()) {
            System.out.println("Round " + round++);
            List<Card> pot = new ArrayList<>();
            Card card1 = player1.playCard();
            Card card2 = player2.playCard();

            pot.add(card1);
            pot.add(card2);

            System.out.println("Player 1 plays: " + card1);
            System.out.println("Player 2 plays: " + card2);

            if (card1.getValue() > card2.getValue()) {
                player1.collectCards(pot);
                System.out.println("Player 1 wins this round.");
            } else if (card1.getValue() < card2.getValue()) {
                player2.collectCards(pot);
                System.out.println("Player 2 wins this round.");
            } else {
                System.out.println("War!");
                war(player1, player2, pot);
            }

            System.out.println("Player 1 has " + player1.getCardCount() + " cards.");
            System.out.println("Player 2 has " + player2.getCardCount() + " cards.");
            System.out.println("--------------------------------");
        }

        System.out.println(player1.hasCards() ? "Player 1 wins the game!" : "Player 2 wins the game!");
    }

    private static void war(Player player1, Player player2, List<Card> pot) {
        List<Card> warCards1 = new ArrayList<>();
        List<Card> warCards2 = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            if (player1.hasCards()) warCards1.add(player1.playCard());
            if (player2.hasCards()) warCards2.add(player2.playCard());
        }

        Card warCard1 = player1.hasCards() ? player1.playCard() : null;
        Card warCard2 = player2.hasCards() ? player2.playCard() : null;

        if (warCard1 != null) pot.addAll(warCards1);
        if (warCard2 != null) pot.addAll(warCards2);
        if (warCard1 != null) pot.add(warCard1);
        if (warCard2 != null) pot.add(warCard2);

        System.out.println("War cards placed, final battle card:");
        System.out.println("Player 1 plays: " + warCard1);
        System.out.println("Player 2 plays: " + warCard2);

        if (warCard1 != null && warCard2 != null) {
            if (warCard1.getValue() > warCard2.getValue()) {
                player1.collectCards(pot);
                System.out.println("Player 1 wins the war!");
            } else if (warCard1.getValue() < warCard2.getValue()) {
                player2.collectCards(pot);
                System.out.println("Player 2 wins the war!");
            } else {
                System.out.println("Another war!");
                war(player1, player2, pot);
            }
        } else {
            if (warCard1 != null) player1.collectCards(pot);
            else if (warCard2 != null) player2.collectCards(pot);
        }
    }
}
