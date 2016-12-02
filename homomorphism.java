import java.util.*;
import java.io.*;

/*
This is made by Melissa Plakyda for CSCE 355-001 Fall 2016.
This class performs the homomorphism task as described in the assignments document.
*/

public class homomorphism {
	// readInDFA takes in all the information read in in the main and then parses through the data to get the information required
	// from the DFA description file and store it appropriately.
	// At the end, I pass that information into the simulator method. (I had issues with global variables, that's why I did it
	// this messy way of passing things around. I know it's not the best practice.)
	public void readInDFA(int numStates, List<String> acceptStates, String alphabet, String[] transTable, List<String> dfaList, List<String> stringsList) {
		int numAccepting = 0;
		int alphabetSize = 0;
		int transCtr = 0;
		int[][] transTableNum = new int[numStates][alphabetSize];
		
		for (int i = 0; i < dfaList.size(); i++) {
			// get the number of states
			if (dfaList.get(i).contains("Number")) {
				String temp = dfaList.get(i);
				temp = temp.substring(18);
				numStates = Integer.parseInt(temp);
				transTable = new String[numStates];
			}
			// get the accepting states
			else if (dfaList.get(i).contains("Accepting")) {
				String temp2 = dfaList.get(i);
				// add a space at the end to get the last item
				temp2 += " ";
				numAccepting = temp2.length();
				// go from the start of the accepting states, parse through and get the states w/o spaces
				temp2 = temp2.substring(18,temp2.length());
				String currElemStr = "";
				String[] acceptStatesArr = temp2.split(" ");
				for (int n = 0; n < acceptStatesArr.length; n++) {
					acceptStates.add(acceptStatesArr[n]);
				}
			}
			// get the alphabet
			else if (dfaList.get(i).contains("Alphabet")) {
				String temp3 = dfaList.get(i);
				temp3 = temp3.substring(10,temp3.length());
				alphabet = temp3;
				alphabetSize = alphabet.length();
				// set the size of the transition table now that we know the size of our alphabet
				transTableNum = new int[numStates][alphabetSize];
			}
			// read in the transition table
			else {
				// create an array that is the size of the number of states
				// in here, i will create a string for each row in the table of the transition table
				//rows x columns
				String readIn = dfaList.get(i);

				// This checks to see that it's actually the beginning of the transition table.
				if (i > 2) {
					for (int q = 0; q < alphabetSize; q++) {
						String[] rowStr = readIn.split(" ");
						int rowInt = Integer.parseInt(rowStr[q]);
						transTableNum[i-3][q] = rowInt;
					}

				}				
			}

		}
		isHomo(numStates, acceptStates, alphabet, transTableNum, dfaList, stringsList);
	}
	
	// The isHomo takes the description of the DFA along with the homomorphism list passed in and converts the new transition table.
	public void isHomo(int numStates, List<String> acceptStates, String alphabet, int[][] transTable, List<String> dfaList, List<String> homoList) {
		int[] inputAlpha = new int[0];
		String outputAlpha = "";
		List<String> hValues = new ArrayList<String>();
		int[][] homoTransTable = new int[0][0];
		int currentState = 0;
		int indexOfElem = 0;
		String alphaNew = "";
		
		// Loop through the homomorphism list that was read in and parse the data.
		for (int i = 0; i < homoList.size(); i++) {
			// Store the input alphabet.
			if (homoList.get(i).contains("Input")) {
				String temp = homoList.get(i);
				alphaNew = temp.substring(16, temp.length());
				inputAlpha = new int[alphaNew.length()];
				// Store the input alphabet as integer values, these will be the columns later on.
				for (int j = 0; j < inputAlpha.length; j++) {
					inputAlpha[j] = j;
				}
			}
			// Store the output alphabet.
			else if (homoList.get(i).contains("Output")) {
				String temp2 = homoList.get(i);
				outputAlpha = temp2.substring(17, temp2.length());
			}
			// Otherwise, the remaining values are the homomorphism values.
			else {
				if (i > 1) {
					hValues.add(homoList.get(i));
				}
			}
		}
		// Reset the homomorphism table values to reflect the sizes read in.
		homoTransTable = new int[numStates][inputAlpha.length];

		// Loop through the number of states and set the current state to where you are looking. (rows)
		for (int n = 0; n < numStates; n++) {
			currentState = n;
			// Then, loop through the input alphabet and calculate the transitons. (columns)
			for (int o = 0; o < inputAlpha.length; o++) {
				currentState = n;
				String hValStr = hValues.get(o);
				char[] hContents = hValStr.toCharArray();
				// Loop through the contents of the homomorphism value you are looking at and find the state it ends on.
				for (int p = 0; p < hContents.length; p++) {
					String lineCharStr = String.valueOf(hContents[p]);
					indexOfElem = alphabet.indexOf(lineCharStr);
					currentState = transTable[currentState][indexOfElem];
				}
				// Store whatever state the end of the value is, based on what was read in. This will go in the table.
				homoTransTable[n][o] = currentState;
			}
		}

		// Print out all of the data required for std out.
		System.out.println("Number of states: " + numStates);
		System.out.print("Accepting states: ");
		for (int k = 0; k < acceptStates.size(); k++) {
			System.out.print(acceptStates.get(k) + " ");
		}
		System.out.println("");
		System.out.println("Alphabet: " + alphaNew);
		for (int q = 0; q < numStates; q++) {
			for (int r = 0; r < inputAlpha.length; r++) {
				System.out.print(homoTransTable[q][r] + " ");
			}
			System.out.println("");
		}
	}

  public static void main(String[] args) {
	// from looking at my CSCE 146 code, you need to make a class object in order to get around the static/non-static issue
	homomorphism dfa = new homomorphism();
	int numStates = 0;
	List<String> acceptStates = new ArrayList<String>();
	String alphabet = "";
	String[] transTable = null;
	List<String> dfaList = new ArrayList<String>();
	List<String> homoList = new ArrayList<String>();


	// http://stackoverflow.com/questions/16802147/java-i-want-to-read-a-file-name-from-command-line-then-use-a-bufferedreader-to
	// read in from the DFA description, and the list of strings on the cmd line
	// we are reading in the DFA as described in the project assignment
	File description = null;
	File homo = null;
	boolean secondArg = false;
	if (args.length == 1) {
	   description = new File(args[0]);
	}
	else if (args.length == 2) {
	   description = new File(args[0]);
	   homo = new File(args[1]);
	   secondArg = true;
	} 
	else {
	   System.err.println("Invalid arguments count:" + args.length);
	   System.exit(0);
	}

	BufferedReader br = null;

	try {
	    String currLine;
	    br = new BufferedReader(new FileReader(description));
	    while ((currLine = br.readLine()) != null) {
		dfaList.add(currLine);
	    }
	} 

	catch (IOException e) {
	    e.printStackTrace();
	} 

	finally {
	    try {
	        if (br != null)br.close();
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
  	}

	BufferedReader br2 = null;

	try {
	    String currLine;
	    br2 = new BufferedReader(new FileReader(homo));
	    while ((currLine = br2.readLine()) != null) {
		homoList.add(currLine);
	    }
	} 

	catch (IOException e) {
	    e.printStackTrace();
	} 

	finally {
	    try {
	        if (br2 != null)br2.close();
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
  	}

	// now that the data has been taken from the file and stored in the DFA list
	// we can begin parsing it
	dfa.readInDFA(numStates, acceptStates, alphabet, transTable, dfaList, homoList);

  }
}
