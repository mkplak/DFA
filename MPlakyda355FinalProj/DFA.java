import java.util.*;
import java.io.*;

/*
This is made by Melissa Plakyda for CSCE 355-001 Fall 2016.
This class performs the simulator task as described in the assignments document.
*/

public class DFA {
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
		simulator(numStates, acceptStates, alphabet, transTableNum, dfaList, stringsList);
	}
	
	// The simulator reads in the strings and then based on the DFA description, accepts or rejects.
	public void simulator(int numStates, List<String> acceptStates, String alphabet, int[][] transTable, List<String> dfaList, List<String> stringsList) {
		String line = "";
		int lineLength = 0;
		int[] lineToCheck = new int[lineLength];
		int numReturn = 0;
		int currentState = 0;
		int nextState = 0;
		int indexOfElem = 0;

		// loop through the file of strings and perform the simulator on them.
		for (int a = 0; a < stringsList.size(); a++) {
			line = stringsList.get(a);
			// get the length of the line so you know how many transitions to do
			lineLength = line.length();
			// convert the String line to a char array to look at each item
			char[] lineChar = line.toCharArray();
			lineToCheck = new int[lineLength];
			// go through the line, convert it to a string to get the index of it in the alphabet
			for (int j = 0; j < lineLength; j++) {
				String lineCharStr = String.valueOf(lineChar[j]);
				indexOfElem = alphabet.indexOf(lineCharStr);
				currentState = transTable[currentState][indexOfElem];
			}
			// check to see if the final state is an accepting state, which was read in earlier
			String finalNum = String.valueOf(currentState);
			if (acceptStates.contains(String.valueOf(finalNum)) == true) {
				System.out.println("accept");
			}
			else {
				System.out.println("reject");
			}
			// reset the current state to zero since the start state is always zero
			currentState = 0;
		}
	}

  public static void main(String[] args) {
	// from looking at my CSCE 146 code, you need to make a class object in order to get around the static/non-static issue
	DFA dfa = new DFA();
	MakeDFA newDFA = new MakeDFA();
	int numStates = 0;
	List<String> acceptStates = new ArrayList<String>();
	String alphabet = "";
	String[] transTable = null;
	List<String> dfaList = new ArrayList<String>();
	List<String> stringsList = new ArrayList<String>();


	// http://stackoverflow.com/questions/16802147/java-i-want-to-read-a-file-name-from-command-line-then-use-a-bufferedreader-to
	// read in from the DFA description, and the list of strings on the cmd line
	// we are reading in the DFA as described in the project assignment
	File inFile = null;
	File strings = null;
	if (0 < args.length) {
	   inFile = new File(args[0]);
	   strings = new File(args[1]);
	} else {
	   System.err.println("Invalid arguments count:" + args.length);
	   System.exit(0);
	}

	BufferedReader br = null;

	try {
	    String currLine;
	    br = new BufferedReader(new FileReader(inFile));
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
	    br2 = new BufferedReader(new FileReader(strings));
	    while ((currLine = br2.readLine()) != null) {
		stringsList.add(currLine);
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
	dfa.readInDFA(numStates, acceptStates, alphabet, transTable, dfaList, stringsList);

  }
}
