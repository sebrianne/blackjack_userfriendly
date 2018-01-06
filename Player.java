//Sebrianne Ferguson
//created: January 5th, 2018
//This is a more user-friendly version of the game that I created before. In this game, the user is more in control over their
//hand and can chose whether or not to pick more cards or not, whereas in the last version the computer was in charge of that.


//the main changes were in the player evaluation method, where i added user input to decide whether or not to pick another card or
//to stay.

package blackjack_userfriendly;

import java.util.HashSet; //needed to use hashsets
import java.util.Scanner; //needed for user input

public class Player {
	
	Scanner in = new Scanner(System.in); //for user input
	
	HashSet<String> playerHand = new HashSet<String>(); //creates a hand for the player
	double allowance = 0.00; //set the initial values to 0
	double bet = 0.00;
	
	//invisible ctor, did not see the need to make one for this class. but that might change
	
	public HashSet<String> makePlayerHand(Dealer dealer) {   //kept from the last version, no changes
		playerHand = dealer.makeMyHand(); //make a hand for the player
		return playerHand;
	}
	
	public HashSet<String> getHand() { //classic getter method
		return playerHand;
	}
	
	public void removeFromHand(Dealer dealer) { //this clears the hand for a new game
		HashSet<String> removethese = new HashSet<String>(getHand());
		playerHand.removeAll(removethese);
		dealer.removeFromHand();
	}
	
	public void printHand() {
		for (String c: getHand()) {
			System.out.println(c);
		}
	}
	
	public void addToHand(Dealer dealer) { //if the player wants to add to their deck
		String c = dealer.deal();
		System.out.println("Hit me: " + c);
		playerHand.add(c);
		dealer.playerUpdateDeck(this); //get rid of this from the deck
	}
	
	public int calcTotal() { //method to calculate to total of the cards, basically the same as the method for the dealer.
		//no changes here
		HashSet<String> myHand = new HashSet<String>();
		//deals 2 cards
		myHand.addAll(getHand());
		int total = 0;
		
		for (String card: myHand) {
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
	
public int choose() { //added this because it was easier to create a method to ask to be hit or stay and call it when needed
	Scanner input = new Scanner(System.in);
	
	System.out.println("Press 1 for HIT ME, Press 2 for STAY: ");
	int choice = input.nextInt();
	
	return choice;
}
	
public Evaluation playerEvaluate(Dealer dealer) { //pass in the dealer object to compare to it
		
		int total = calcTotal(); //first find out what the cards value to -- this is the calctotal method for the player
		dealer.dealerEvaluate();
		int dealerTotal = dealer.calcTotal(); //this finds the dealers total
		
		System.out.println("Your score is: " + total);
		
		if (total == 21) { //if you got blackjack, you win! (this is possible before choosing any cards if you have a face card and 10)
			System.out.println("BLACKJACK");
			this.adjustAllowanceForBlackjack(); //pay the player double
			System.out.println("The dealer's score is: " + dealerTotal); //show the dealers hand for comparison
			dealer.printHand();
			return Evaluation.BLACKJACK;
		}
		
		boolean status = true; //for controlling the loop
		
		while ((total < 21) && (status == true)) { //if you havent busted and are able to take a card 
			                                       //there is a chance you could beat the dealer.
			
			int choice = choose(); //ask the user if they would like to take another card.
			while (status == true) { //while the user still wants to take cards
				
				if (choice == 2) {
					status = false; //will break out of this while loop and the while total < 21 while loop
				}
				
				else if (choice == 1) { //if the user would like another card
					addToHand(dealer); //add a card
					break; //have to have this or else it will go into an infinite loop of giving them cards
				}
				
				else { //if the entry is not a 1 or a 2
					System.out.println("INVALID");
					choice = choose(); //have them enter it again
				}
			}

			
			total = calcTotal(); 
			System.out.println("Your score is: " + total); //print the total
			
			
		}
		
		//once you're finished taking any cards, compare your cards to the dealer
		//you could have blackjack now or you could have busted
		
		if (total == 21) { //if you got blackjack, you win!
			System.out.println("BLACKJACK");
			this.adjustAllowanceForBlackjack(); //pay the player double
			System.out.println("The dealer's score is: " + dealerTotal); //show the dealers hand for comparison
			dealer.printHand();
			return Evaluation.BLACKJACK;
		}
		
		if (total > 21) { //if you busted, return bust and you lose
			System.out.println("BUST");
			this.adjustAllowanceForLowerOrBust(); //take the players money
			System.out.println("The dealer's score is: " + dealerTotal);
			dealer.printHand();
			return Evaluation.BUST;
		}
		
		System.out.println("The dealer's score is: " + dealerTotal);
		dealer.printHand();
		
	 	 if (dealer.dealerEvaluate() == Evaluation.BUST) { //added this 01/05/2018
	 		 System.out.println("DEALER BUSTED"); //if the dealer busts, considered the same as Evaluation.HIGHER
			 this.adjustAllowanceForHigher();
			 return Evaluation.HIGHER;
	 	 }
		 
		 if ((total < dealerTotal) && (dealer.dealerEvaluate() != Evaluation.BUST)) { //you lose
			 System.out.println("LOWER");
			 this.adjustAllowanceForLowerOrBust();
			 return Evaluation.LOWER;
		 }
		 if (total > dealerTotal) { //you win
			 System.out.println("HIGHER");
			 this.adjustAllowanceForHigher();
			 return Evaluation.HIGHER;
		 }
		 if (total == dealerTotal) { //you tie
			 System.out.println("SPLIT");
			 return Evaluation.SPLIT;
		 }
		 
		 return Evaluation.DONE;
		
	}

public double putUpBet() { //for each round the user will be able to choose how much they want to bet
	System.out.println("How much would you like to bet?");
	this.bet = in.nextDouble(); //in case they want to bet cents
	return this.bet;
}

public double adjustAllowanceForLowerOrBust() { //taking money from the player if they got lower or bust
	this.allowance = this.allowance - this.bet;
	return this.allowance;
}

public double adjustAllowanceForHigher() { //paying them the amount they put up if they got higher than the dealer
	this.allowance = this.allowance + this.bet;
	return this.allowance;
}

public double adjustAllowanceForBlackjack() { //paying them double if they got blackjack
	this.allowance = this.allowance + 2*(this.bet);
	return this.allowance;
}

public void printMoney() { //printing the amt of money they have 
	System.out.println("You have $" + this.allowance);
	
}

public static void main(String[] args) {
	Scanner in = new Scanner(System.in); //for input
	Player p = new Player(); //create a player object
	Dealer dealer = new Dealer(); //create a dealer object
	p.allowance = 100.00; //set the initial allowance for the user to 100
	p.printMoney(); //print how much money the user has
	
	boolean play = true; //for managing the while loop
	
	while(play == true && (p.allowance > 0.00) ) { //while the user still wants to play and they have money left
		p.makePlayerHand(dealer); //make the players hand
		dealer.makeMyHand(); //make the dealers hand
		
		dealer.updateDeck(); //remove those cards from the deck accordingly
		dealer.playerUpdateDeck(p); 
		
		double bet = p.putUpBet(); //player puts up their bet
		while ((bet <= 5.00) || (bet > p.allowance)) { //makes sure that the players actually bets a valid amt of money
			//and usually in blackjack the lowest bet is $5
			System.out.println("NOT A VALID BET.");
			bet = p.putUpBet();
		}
		
		System.out.println("");
		p.printHand(); //print the cards the player has
		p.playerEvaluate(dealer); //evaluate the deck and will determine whether or not to get more cards or not
		//it will also determine how much money to give or take
		p.printMoney(); //print the amount of money the user has
		System.out.println("");
		System.out.println("Would you like to play another round? (1 for YES, 2 for NO)"); //ask to play again
		int choose = in.nextInt(); //input
		if (choose == 2) { //break out of the loop if the user chooses no
			play = false;
		}
		dealer.removeFromHand(); //if not update the dealers hands so there is nothing
		
	}
	
	if (p.allowance <= 0.00) {
		System.out.println("You ran out of money!");
	}
	
	System.out.println("***END OF GAME***");
	
	
}

	
}
