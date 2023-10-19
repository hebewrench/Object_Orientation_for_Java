//Hebe Wrench
//c1922896

import java.util.Scanner;
import java.util.Random;
import java.lang.Math;
//import java.util.Arrays;

public class MagicSquare {
	
	/* Checks if the value entered by the user is valid code for creating 
	the magic square will only be run if the user has entered a reasonable number.*/
	public static int CheckInput(){
		Scanner userOddEntry = new Scanner (System.in);
		System.out.print("Please enter an odd number: "); 

        while (true){
            int x;
            // Setting the user input to x only if the input was an integer
            try {
                x = Integer.parseInt(userOddEntry.next());
            }
            // If they did not enter an integer they are asked to reenter an integer
            catch (NumberFormatException e){
                System.out.println("Please enter an odd number: ");
                continue;
            }
            // Cannot create a square with lengths <0, this checks if it is possible to make the user's square
            if (x <= 0){ // Chastise the user if they don't enter a positive number
                System.out.println("How am I supposed to make a square with negative values???");
                System.out.println("That's super silly and entirely not possible, same with a 0x0 square :|");
                System.out.println("Please enter an odd positive integer: ");
            }
            // Check if x is divisible by 2, if there is a remainder of 1, then it is not divisible by 2 
            // and thus it is odd and can make a magic square
            else if (x % 2 == 1){
                return x;
            }
            // If it is divisible by 2, then it is even and a magic square won't be possible, thus the user
            // is asked to reenter an odd number 
            else {
                System.out.println("That was not an odd number! (Even though it is odd that you tried it)");
                System.out.println("Please enter an odd number: ");
            }
        }
    }

	static class SquareCreator {
	    public int n;
	    public int[][] magicSquare;
	    SquareCreator(int UserOdd) {
	        n = UserOdd; /* Sets n to the value the user inputed, which has already been checked
	         				and is a known odd integer*/ 
	        magicSquare = new int[n][n]; // Initialises a 2D array for the Magic Square
	        
	        // Initialises the first position	        
	        int column = 0;
	        int row = ((n+1)/2-1); //floorMod means the values of the array can wrap around
	        magicSquare[Math.floorMod(column, n)][Math.floorMod(row, n)] = 1;
	              
	        // For loop for every element within the given n
	        for (int num = 2; num <= n * n; num++) {
	        	if (magicSquare[Math.floorMod(column-1, n)][Math.floorMod(row-1, n)] == 0) {
	        		// If the position vector is empty, decrement row and column
	        		column --;
	                row --;
	            } else {
	                // If the position vector is not empty, column is increased, row remains the same
	                column ++;
	            }
	        	magicSquare[Math.floorMod(column, n)][Math.floorMod(row, n)] = num;
	        }
	    }
	    
	    // Displays the square to the user
	    public String printSquare() {
	        String StringSquare = ""; // Creates an empty string 
	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) { 
	            	// Puts each value of the Magic Square into the string, in order, and with spacing
	                StringSquare = StringSquare + magicSquare[i][j] + "  ";
	            }
	            StringSquare = StringSquare + "\n";
	            // After running through all the items in each column, a new line is printed
	        }
	        return StringSquare;
	    }
	    
	    public static int[][] RandomDirection(int x, int y,int [][] MagicSquare, int n) {
	    	// Generates a random number 0-3, these will act as the indicator of what direction the shuffle is in
	    	Random r = new Random();
	    	int randomDirectionInt = r.nextInt(4);
	    	int temp;
	    	
	    	int[][] ShuffleSquare = MagicSquare.clone(); //clones the original square to create a shuffled version
	    	//uses a very similar method to that in bubble sort, to swap the items for the shuffle
	    	if (randomDirectionInt == 0) { // 0 is considered an downwards swap
	    		temp = ShuffleSquare[Math.floorMod(x+1, n)][Math.floorMod(y, n)];
	    		ShuffleSquare[Math.floorMod(x+1, n)][Math.floorMod(y, n)] = ShuffleSquare[Math.floorMod(x, n)][Math.floorMod(y,n)];
	    		ShuffleSquare[Math.floorMod(x, n)][Math.floorMod(y, n)] = temp;
	    	}
	    	else if (randomDirectionInt == 1 ) {// 1 is considered a upwards swap
	    		temp = ShuffleSquare[Math.floorMod(x-1, n)][Math.floorMod(y, n)];	    	
	    		ShuffleSquare[Math.floorMod(x-1, n)][Math.floorMod(y, n)] = ShuffleSquare[Math.floorMod(x, n)][Math.floorMod(y,n)];
	    		ShuffleSquare[Math.floorMod(x, n)][Math.floorMod(y, n)] = temp;
	         }
	        else if (randomDirectionInt == 2 ) {// 2 is considered a leftward swap
	        	temp = ShuffleSquare[Math.floorMod(x, n)][Math.floorMod(y-1, n)];	        	
	        	ShuffleSquare[Math.floorMod(x, n)][Math.floorMod(y-1, n)] = ShuffleSquare[Math.floorMod(x, n)][Math.floorMod(y,n)];
	        	ShuffleSquare[Math.floorMod(x, n)][Math.floorMod(y, n)] = temp;	    	
	         }
	        else  {// 3 is considered a rightward swap
	        	temp = ShuffleSquare[Math.floorMod(x, n)][Math.floorMod(y+1, n)];
	        	ShuffleSquare[Math.floorMod(x, n)][Math.floorMod(y+1, n)] = ShuffleSquare[Math.floorMod(x, n)][Math.floorMod(y,n)];
	        	ShuffleSquare[Math.floorMod(x, n)][Math.floorMod(y, n)] = temp;
	         }
	       return ShuffleSquare;//a 2d array, very useful for the gameplay
	    }

	    public int [][] returnShuffleSquare() {
	        Random r = new Random();
	        for (int i = 0; i < n*n; i++) {//repeats the loop n*n times
	        	int xCoord = r.nextInt(n);//uses random xs and ys each time so as to make it a 
	        	int yCoord = r.nextInt(n);//completely random shuffle
	        	magicSquare = RandomDirection(xCoord, yCoord, magicSquare, n);
	            }
	        return magicSquare;
	    }
	}
	
    public static void main (String[] args){
        int y = CheckInput(); //if the input is valid then the square is created
        SquareCreator x =  new SquareCreator (y);//creates the square
        /*commented out for 1b, but displays a complete magic square
        System.out.println(("The Magic Square of "+y+"x"+y+ ": "));
        System.out.println(x.printSquare());
        */
        System.out.println(("A Shuffled Magic Square of "+y+"x"+y+ ": "));
        int [][] shuffled = x.returnShuffleSquare();//shuffles the square and stores it
        //printSquare algorithm 
        String ShuffleSquare = ""; // Creates an empty string 
        for (int l = 0; l <y; l++) {
            for (int k = 0; k < y; k++) { 
            	// Puts each value of the Magic Square into the string, in order, and with spacing
                ShuffleSquare = ShuffleSquare + shuffled[l][k] + "  ";
            }
            ShuffleSquare = ShuffleSquare + "\n";}
        System.out.println(ShuffleSquare);//prints the shuffled square
		System.out.println("Try to rearrange the square to make it a correct Magic Square");
		System.out.println("Hint: the sum of each row or column should be: "+y*(y*y+1)/2+ "\n");
		System.out.println("Please enter the move you want to make in the form: ");
		System.out.println("i j direction" + "\n");
		System.out.println("where i is the column, j is the row, and the direction is either U, D, L, or R");
		System.out.println("(for Up, Down, Left, or Right, respectively)" +"\n");
		
		int SwapCount = 0; //to keep track of how many swaps the user makes
		boolean Magical = false; //to give an escape from the while loop
		
		while (Magical==false) {
			Scanner scan = new Scanner (System.in);
			System.out.println("Please enter the move you want to make: ");
					
			String userInput = scan.nextLine(); //arrays to retrieve the user's move choice
			String userInputArray[] = userInput.split(" ");
				
			int [] coordinateArray = new int [2];
			coordinateArray[0] = Integer.parseInt(userInputArray[0]);
			coordinateArray[1] = Integer.parseInt(userInputArray[1]);
			
			int i = coordinateArray[0];
			int j = coordinateArray[1];
			
			if (userInputArray[2].charAt(0) == 'U' | userInputArray[2].charAt(0) == 'u') {
				int temp = shuffled[Math.floorMod(i-1, y)][Math.floorMod(j, y)];
				shuffled[Math.floorMod(i-1, y)][Math.floorMod(j, y)] = shuffled[Math.floorMod(i, y)][Math.floorMod(j, y)];
				shuffled[Math.floorMod(i, y)][Math.floorMod(j, y)] = temp;
			}
			else if (userInputArray[2].charAt(0) == 'D' | userInputArray[2].charAt(0) == 'd') {
				int temp = shuffled[Math.floorMod(i+1, y)][Math.floorMod(j, y)];
				shuffled[Math.floorMod(i+1, y)][Math.floorMod(j, y)] = shuffled[Math.floorMod(i, y)][Math.floorMod(j, y)];
				shuffled[Math.floorMod(i, y)][Math.floorMod(j, y)] = temp;
			}
			else if (userInputArray[2].charAt(0) == 'L' | userInputArray[2].charAt(0) == 'l') {
				int temp = shuffled[Math.floorMod(i, y)][Math.floorMod(j-1, y)];
				shuffled[Math.floorMod(i, y)][Math.floorMod(j-1, y)] = shuffled[Math.floorMod(i, y)][Math.floorMod(j, y)];
				shuffled[Math.floorMod(i, y)][Math.floorMod(j, y)] = temp;
			}
			else if (userInputArray[2].charAt(0) == 'R' | userInputArray[2].charAt(0) == 'r') {
				int temp = shuffled[Math.floorMod(i, y)][Math.floorMod(j+1, y)];
				shuffled[Math.floorMod(i, y)][Math.floorMod(j+1, y)] = shuffled[Math.floorMod(i, y)][Math.floorMod(j, y)];
				shuffled[Math.floorMod(i, y)][Math.floorMod(j, y)] = temp;
			}
			
			ShuffleSquare = ""; // prints the new square, after the move made by the user 
	        for (int l = 0; l <y; l++) {
	            for (int k = 0; k < y; k++) { 
	            	// Puts each value of the Magic Square into the string, in order, and with spacing
	                ShuffleSquare = ShuffleSquare + shuffled[l][k] + "  ";
	            }
	            ShuffleSquare = ShuffleSquare + "\n";}
	        System.out.println(ShuffleSquare);
	        SwapCount++;
	        System.out.println("Swaps Made: " + SwapCount); 
	        
	        int columnTotal = 0; //initialisation of variables to test if it is a magic square
	        int rowTotal = 0;
	        int diagTotal1 = 0;
	        int trueCount = 0; //counts how many true values have been returned
	        
	        for (int a=0; a<y; a++) {
	        	for (int b=0; b<y; b++) {
	        		columnTotal = columnTotal + shuffled[a][b]; //totals in a column
	        		rowTotal = rowTotal + shuffled[b][a];
	        	}
	        	if ((columnTotal == y*(y*y+1)/2)) { // checks if the column sums to the right total
	        		trueCount ++; //if it does, increments the trueCount
	        	}
	        	if ((rowTotal == y*(y*y+1)/2)) {// checks if the row sums to the right total
	        		trueCount++;//if it does, increments the trueCount
	        	}
	        	//diagonal top left to bottom right
	        	for (i=0; i<y; i++) {
	        		diagTotal1 = diagTotal1 + shuffled[i][i];
	        	}
	        	if (diagTotal1 == y*(y*y+1)/2 ) {// checks if a diagonal sums to the right total
	        		trueCount ++; // don't need to check both diagonals because if all the other values add 
	        		//to the right amount, the last one won't need to be checked
	        	}
	        	columnTotal = 0;//resets the column total so other columns can be checked
		        rowTotal = 0;
		        //diagTotal1 = 0;
	        }
	        if (trueCount != ((2*y)+1)) {//if the trueCount is not double +1 of the length of the array
	        	trueCount = 0; //loop continues
		       }
	        else { //else, you finally escape
	        	System.out.println("Congratulations! You have completed the Magic Square!");
		        System.out.println("You made " +SwapCount+" swaps!");
		        Magical = true;
		       }
		}
    }
}