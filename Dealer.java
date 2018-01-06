//Sebrianne Ferguson
//created: January 5th, 2018
//This is a more user-friendly version of the game that I created before. In this game, the user is more in control over their
//hand and can chose whether or not to pick more cards or not, whereas in the last version the computer was in charge of that.

//nothing really changed with this class from the other version of this program other than the package name.

package blackjack_userfriendly;

import java.util.*;

public class Dealer {
	
	HashSet<String> dealerHand = new HashSet<String>();
	//each dealer will have a hand of cards of their own that the players will be against
	
	public Dealer() { //ctor for the dealer 
		
		dealerHand = makeMyHand(); //whenever a new dealer is created, a new hand needs to be created as well
	}
	
	public HashSet<String> deck() { //in blackjack, the dealer is in charge of the deck of cards and deals them out for
		//each round. this method creates a standard 52-card deck for the dealer to deal from. this program kind of assumes that
		//every round the dealer starts with a full deck of cards.
		
		String[] frenchdeck = {"A_heart", "A_diamond", "A_spade", "A_club",
							   "2_heart", "2_diamond", "2_spade", "2_club", 
							   "3_heart", "3_diamond", "3_spade", "3_club",
							   "4_heart", "4_diamond", "4_spade", "4_club",
							   "5_heart", "5_diamond", "5_spade", "5_club",
							   "6_heart", "6_diamond", "6_spade", "6_club",
							   "7_heart", "7_diamond", "7_spade", "7_club",
							   "8_heart", "8_diamond", "8_spade", "8_club",
							   "9_heart", "9_diamond", "9_spade", "9_club",
							   "10_heart", "10_diamond", "10_spade", "10_club",
							   "JACK_heart", "JACK_diamond", "JACK_spade", "JACK_club",
							   "QUEEN_heart", "QUEEN_diamond", "QUEEN_spade", "QUEEN_club",
							   "KING_heart", "KING_diamond", "KING_spade", "KING_club"
							   };
		
		//above is a string array 
		//i decided to create a string array so each card could be stored with its kind and suit. 
		
		HashSet<String> mydeck = new HashSet<String>(); //hashsets do not have duplicates and are stored in arbitrary order
		//which makes this a good fit for my program. if i used a treeset then i would have to include a compareto method.

		for (int i = 0; i < frenchdeck.length; i++) { //add each of the cards to the hashset
			mydeck.add(frenchdeck[i]);
		}
		
		return mydeck;
	}
	
	public void updateDeck() { //after you create the dealers deck use this
		HashSet<String> myDeck = new HashSet<String>(deck());
		
		myDeck.removeAll(this.getHand());
	}
	
	public void playerUpdateDeck(Player p) { //after you create the players deck use this
		HashSet<String> myDeck = new HashSet<String>(deck());
		myDeck.removeAll(p.getHand());
	}
	
	public String deal() {
		
		//**Updated as of January 4th, 2018: trying to fix the following problem:
		//the idea would be that every time the dealer deals a new card, there is a full deck
		//this should be fixed at one point because if the player needs a new card during a round it 
		//will assume that the deck is full
		ArrayList<String> deck = new ArrayList<String>(deck());
		//creates a new 52 card deck
		
		//the update is to create the 2 methods above that will 
		
		Random rand = new Random(); //pick a random card
		
		String card1 = deck.get(rand.nextInt(deck.size())); //deals a card
		//String card2 = deck.get(rand.nextInt(deck.size()));
		
		deck.remove(card1);
		
		return card1;
		
	}
	
	public HashSet<String> makeMyHand(){ //make the dealers hand
		HashSet<String> myHand = new HashSet<String>();
		//deals 2 cards
		myHand.add(deal());
		myHand.add(deal());
		
		return myHand;
	}
	
	public HashSet<String> getHand() { //getter method
		return dealerHand;
	}
	
	public void printHand() { 
		System.out.println("Dealer's Hand:");
		for (String c: getHand()) {
			System.out.println(c);
		}
	}
	
	public void addToHand() { //if after you look at both of the cards you want to be hit
		String c = deal();
		//System.out.println("Hit Dealer: " + c);
		dealerHand.add(c);
		this.updateDeck();
	}
	
	public void removeFromHand() { //clear the hand for the next round
		HashSet<String> removethese = new HashSet<String>(getHand());
		dealerHand.removeAll(removethese);
	}
	
	
	public int calcTotal() { //when you're evaluating your cards
		
		HashSet<String> myHand = new HashSet<String>(); //put the hand in here to evaluate
		//deals 2 cards
		myHand.addAll(getHand());
		
		int total = 0; //set the intial total to 0
		
		for (String card: myHand) { //for every card in the hand evaulate and calculate the total
			
			if ((card == "A_heart") || (card == "A_diamond") || (card == "A_spade") || (card == "A_club")) {
				//ace is different because it can count for 1 or 11 
				//so strategically if you have a small total or you have a 10 then you'd want to use it as an 11
				if (total < 10) {
					total = total + 11;
				}
				//if you have any total above 10 then you'd want to use it as a 1 so you do not bust.
				else {
					total = total + 1;
				}
			}
			
			if ((card == "2_heart") || (card == "2_diamond") || (card == "2_spade") || (card == "2_club")) {
				total = total + 2;
			}
			if ((card == "3_heart") || (card == "3_diamond") || (card == "3_spade") || (card == "3_club")) {
				total = total + 3;
			}
			if ((card == "4_heart") || (card == "4_diamond") || (card == "4_spade") || (card == "4_club")) {
				total = total + 4;
			}
			if ((card == "5_heart") || (card == "5_diamond") || (card == "5_spade") || (card == "5_club")) {
				total = total + 5;
			}
			if ((card == "6_heart") || (card == "6_diamond") || (card == "6_spade") || (card == "6_club")) {
				total = total + 6;
			}
			if ((card == "7_heart") || (card == "7_diamond") || (card == "7_spade") || (card == "7_club")) {
				total = total + 7;
			}
			if ((card == "8_heart") || (card == "8_diamond") || (card == "8_spade") || (card == "8_club")) {
				total = total + 8;
			}
			if ((card == "9_heart") || (card == "9_diamond") || (card == "9_spade") || (card == "9_club")) {
				total = total + 9;
			}
			if ((card == "10_heart") || (card == "10_diamond") || (card == "10_spade") || (card == "10_club")) {
				total = total + 10;
			}
			if ((card == "JACK_heart") || (card == "JACK_diamond") || (card == "JACK_spade") || (card == "JACK_club")) {
				total = total + 10;
			}
			if ((card == "QUEEN_heart") || (card == "QUEEN_diamond") || (card == "QUEEN_spade") || (card == "QUEEN_club")) {
				total = total + 10;
			}
			if ((card == "KING_heart") || (card == "KING_diamond") || (card == "KING_spade") || (card == "KING_club")) {
				total = total + 10;
			}
		}
		
		return total;
			
	}
	
	public Evaluation dealerEvaluate() { //the method for the dealer to evaulate their own hand
		int total = calcTotal(); //first find out what the cards value to
		
		if (total == 21) { //if you got blackjack
			return Evaluation.BLACKJACK;
		}
		
		if (total > 21) { //if you busted, return bust
			return Evaluation.BUST;
		}
		
		while (total < 21) { //if you havent busted and are able to take another card
			if (total >= 17) { //if you have a 16 or over, don't take the chance of adding another card
				break;
			} 
			//however if you're still below 17 take a card 
			addToHand(); //add a card
			total = calcTotal(); //find out if you're still under 21
			
		}
		 
		 return Evaluation.DONE; //the dealer is done with their hand, now time to look at the 
		
	} 
	
	
}

