// package com.hackathon.dekofcards;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Deck {
	
	private ArrayList<Card> cards;
	
    public Deck() {
        this.cards = new ArrayList<Card>();

        // Populate the cards list -- loop to 52
        for (String name : new String[] {"Hearts", "Clubs", "Diamonds", "Spades"}) {
            for (Integer rank = 1; rank <= 13; rank++) {
                this.cards.add(new Card(name, rank));
            }
        }
    }
    
    public void getRandomCard() {
    	Random rand = new Random();
    	int randomNumber = rand.nextInt(52) + 1;
    	cards.get(randomNumber).showCard();
    	cards.remove(randomNumber);
    	
    }
    
    publi void playGame() {
    	Scanner scanner = new Scanner(System.in);
    	System.out
    	String userInput = scanner.next();
    	
    }

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
    
}