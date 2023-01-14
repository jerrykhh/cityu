package poker;

import java.util.ArrayList;
public class Poker {
    // Precondition: The hand of N cards has been sorted by card rank(in ascending order).
    public boolean isDoublePair(String hand) {
		//Block A
	    boolean isDoublePair = false;
		
		if (hand.length() == 10){
			//Block B
	        int i = 0;
	        int matchedPair = 0;
	        int dummyCardCount = 0;
	        boolean validDummy = true; 								  // Turn false if the current detected dummy is not valid
	        int countTest = 0;
	        
		    ArrayList<Character> currentCardRank = new ArrayList<>(); // ArrayList to store the Rank of current 1-2 cards 
		    currentCardRank.add( hand.charAt(1) );                    // Store the rank of the first card 
		    //Block C
		    while (( i < hand.length() / 2 - 1) && (dummyCardCount < 2) && (validDummy == true)) { 
		    	countTest+=1;
				//Block D
				i+=1;
				currentCardRank.add( hand.charAt(i*2 + 1) );          // Get rank of the current card
				
		    	if (currentCardRank.size() == 2) {
		    		//Block E
		    		if (isPairMatched(currentCardRank) == false) {    // Rank not in Pair, Dummy Card Detected
		    			//Block F
		    			currentCardRank.remove(0);                    // The second-last card is dummy
			    		dummyCardCount++;
			    		char dummyCardSuit = hand.charAt((i-1)*2);    // Get the suit of the dummy card
			    		validDummy = isValidDummy(dummyCardSuit);
		    			continue;
		    		}else {											  // Pair matched
		    			//Block G
		    			currentCardRank = new ArrayList<>();          
						matchedPair++;
		    			continue;
		    		}
		    		
		    	}
		    	

			}
			//Block H
		    if (dummyCardCount == 0) {                                // Detect if the last card is dummy
    			//Block I
		    	char dummyCardSuit = hand.charAt(8);
	    		validDummy = isValidDummy(dummyCardSuit);
		    }
			//Block J
			if ((matchedPair == 2) && validDummy) {
				//Block K
				isDoublePair = true;
			}
			
			System.out.println(countTest);

		}		
		//Block L
		return isDoublePair;
	        
    }

    public boolean isPairMatched(ArrayList<Character> pair) {
    	return (pair.get(0) == pair.get(1));
    }
    public boolean isValidDummy(char card) {
    	return ((card != 'C') && (card != 'S'));
    }
}
 
