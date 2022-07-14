package com.github.zipcodewilmington.casino.games.cardgames;

public enum CardRank {
    ACE(1), //in blackjack, ace can be 1 or 11 -GY
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11), //in blackjack, J Q K is value 10 -GY
    QUEEN(12),
    KING(13);
    int value;

    CardRank(int value) {
        this.value = value;
    }

    int getValue() {
        return value;
    }

}
