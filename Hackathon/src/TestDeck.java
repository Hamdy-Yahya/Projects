// package com.hackathon.dekofcards;

public class TestDeck {

	public static void main(String[] args) {
		
		Deck myDeck = new Deck();
		
		myDeck.getRandomCard();
		for (Card card: myDeck.getCards()) {
			card.showCard();
		}
		System.out.print(myDeck);
		Scanner scanner = new 
	}
}