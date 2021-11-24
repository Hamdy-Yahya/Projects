package com.codingdojo.hackathon;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

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

 
    
    public int getCardRank(int randomNumber, String player) {
    	cards.get(randomNumber).showCard(player);
    	int rank = cards.get(randomNumber).getRank();
    	return rank;
    	
    }
    public String getCardSuit(int randomNumber) {
    	String suit = cards.get(randomNumber).getSuit();
    	cards.remove(randomNumber);
    	return suit;
    }
    
    public int convertSuit(String suit) {
    	if (suit.equals("Spades")) {
    		return 4;
    	} else if (suit.equals("Hearts")) {
    		return 3;
    	} else if (suit.equals("Diamonds")) {
    		return 2;
    	} else if (suit.equals("Clubs")) {
    		return 1;
    	} else {
    		return 0;    		
    	}
    }
    
    
    
    
    
    
    public void playGame() {
    	
    	System.out.println("You have $100.");
    	int money = 100;
    	int numOfCards = 52;
    	
    	while (money > 0 ) {
    		
    		Scanner scanner = new Scanner(System.in);
    		System.out.println("Are you ready to play? (y/n)");
    		String userInput = scanner.next();
    		
    		if ( userInput.equals("y")) {
    			
    			System.out.println("How much money do you want to bet?");
    			int betAmount = Integer.parseInt(scanner.next());
    			
    			while (betAmount > money) {
    				System.out.println("You don't have enough money, enter a new value");
        			betAmount = Integer.parseInt(scanner.next());
    			}
    			
    			
    			Random rand = new Random();
    			int randomNumber = rand.nextInt(numOfCards);
    			
    			int playerRank = this.getCardRank(randomNumber, "You");
    			String playerSuit =this.getCardSuit(randomNumber);
    			
    			
    			int randomNumber2 = rand.nextInt(numOfCards - 1);
    			int computerRank = this.getCardRank(randomNumber2, "Computer");
    			String computerSuit =this.getCardSuit(randomNumber2);
    			
    			
    			//spade > hearts > diamonds > clubs 
    			
    			if ( playerRank > computerRank) {
    				System.out.println("You win");
    				money += betAmount;
    				numOfCards--;
    				System.out.println("You now have $" + money);
    			} else if (playerRank < computerRank) {
    				System.out.println("You lose");
    				money -= betAmount;
    				System.out.println("You now have $" + money);
 					System.out.println("Do you want to play again? (y/n)");
					userInput = scanner.next();
					if ( userInput.equals("y")) {
						playGame();
					} else if (userInput.equals("n")) {
						System.out.println("Game Over. Have a good day");
					} else {
						System.out.println("Invalid Input");
					}
					
					
					
    			} else if ( playerRank == computerRank ) {
    				int playerSuitValue = this.convertSuit(playerSuit);
    				int computerSuitValue = this.convertSuit(computerSuit);
    				if (playerSuitValue > computerSuitValue) {
    					System.out.println("You win");
    				} else if (playerSuitValue < computerSuitValue) {
    					System.out.println("You lose :(");
    					System.out.println("Do you want to play again? (y/n)");
    					userInput = scanner.next();
    					if ( userInput.equals("y")) {
    						playGame();
    					} else if (userInput.equals("n")) {
    						System.out.println("Game Over. Have a good day");
    					} else {
    						System.out.println("Invalid Input");
    					}
    				} else {
    					System.out.println("Draw!");
    				}
    			}
    			
    			
    			
    		} else if ( userInput.equals("n")) {
    			System.out.println("You have $" + money);
    			System.out.println("Game Over. Have a good day");
    			money = 0;
    		} else {
    			System.out.println("Invalid Input");
    		}
    		
    	}
    	 

    }
    
    
	public ArrayList<Card> getCards() {
		
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
    
}