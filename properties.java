import java.util.*;
import java.io.*;

public class properties {

	public void readInDFA(int numStates, List<String> acceptStates, String alphabet, String[] transTable, List<String> dfaList, List<String> stringsList) {
		int numAccepting = 0;
		int alphabetSize = 0;
		int transCtr = 0;
		//int numStates = 0;
		int[][] transTableNum = new int[numStates][alphabetSize];
		
		for (int i = 0; i < dfaList.size(); i++) {
			//System.out.println(dfaList.get(i));
			// get the number of states
			if (dfaList.get(i).contains("Number")) {
				String temp = dfaList.get(i);
				temp = temp.substring(18);
				//System.out.println("temp: " + temp);
				numStates = Integer.parseInt(temp);
				transTable = new String[numStates];

				//makeDFA.setNumStates(numStates);
			}
			// get the accepting states
			else if (dfaList.get(i).contains("Accepting")) {
				String temp2 = dfaList.get(i);
				// add a space at the end to get the last item
				temp2 += " ";
				numAccepting = temp2.length();
				temp2 = temp2.substring(18,temp2.length());
				String currElemStr = "";//String.valueOf(currElem);
				String[] acceptStatesArr = temp2.split(" ");
				for (int n = 0; n < acceptStatesArr.length; n++) {
					//System.out.println("item split: " + acceptStatesArr[n]);
					acceptStates.add(acceptStatesArr[n]);
				}

				/*temp2 = temp2.replaceAll("\\s+", "");
				for (int j = 0; j < temp2.length(); j++) {
					char currElem = temp2.charAt(j);
					System.out.println("curr item: " + temp2.substring(j,j+1));
					System.out.println("is next: " + temp2.substring(j+1,j+2));
					String test = "hello"+ temp2.substring(j+1,j+2) + "elloh";
					System.out.println(test);

					if (j+2 <= temp2.length() && temp2.substring(j+1,j+2) == " ") {
						System.out.println("in first check, resetting currElemStr");
						currElemStr = "";
					}
					else if (j+2 <= temp2.length() && temp2.substring(j+1,j+2) != " ") {
						currElem = temp2.charAt(j);
						currElemStr += String.valueOf(currElem);
						System.out.println("curr elem: " + currElem);
						System.out.println("currElemStr= " + currElemStr);
						if (currElem != ' ') {
							acceptStates.add(currElemStr);
							System.out.println("added: " + currElemStr);
						}
						//acceptStates.add(currElemStr);
						//System.out.println(currElemStr);
					}




					//char currElem = temp2.charAt(j);
					//String currElemStr = String.valueOf(currElem);
					//acceptStates.add(currElemStr);
					//System.out.println(currElemStr);
				}*/

				/*System.out.println("length: " + temp.length());
				int length = temp.length();
				char[] alphaChar = temp.toCharArray();
				for (i = 0; i < length; i++) {
					if (alphaChar[i] != '\n') { // || alphaChar[i] != ' ') {
						//if (alphaChar[i] == ' ') break;
						System.out.println("temp substr: " + alphaChar[i]);//temp.substring(i,i+1));
						//int temp = Integer.parseInt(temp.substring(counter,counter+1));
						temp = Character.toString(alphaChar[i]);//temp.substring(i,i+1);
						acceptStates.add(temp);
						System.out.println(temp);
					}
				}*/
			}
			// get the alphabet
			else if (dfaList.get(i).contains("Alphabet")) {
				String temp3 = dfaList.get(i);
				temp3 = temp3.substring(10,temp3.length());
				alphabet = temp3;
				alphabetSize = alphabet.length();
				transTableNum = new int[numStates][alphabetSize];
			}
			// read in the transition table
			else {
				// create an array that is the size of the number of states
				// in here, i will create a string for each row in the table of the transition table
				//rows x columns
				String readIn = dfaList.get(i);

				if (i > 2) {
					for (int q = 0; q < alphabetSize; q++) {
						String[] rowStr = readIn.split(" ");
						int rowInt = Integer.parseInt(rowStr[q]);
						transTableNum[i-3][q] = rowInt;
					}

				}				
			}

		}
			/*System.out.println("\n\nTransTable");
			for (int k = 0; k < numStates; k++) {
				for (int l = 0; l < alphabetSize; l++) {
					System.out.print(transTableNum[k][l]);
				}
				System.out.println("");
				
			}*/
		properties(numStates, acceptStates, alphabet, transTableNum, dfaList, stringsList);
	}
	
	/*public void simulator(int numStates, List<String> acceptStates, String alphabet, int[][] transTable, List<String> dfaList, List<String> stringsList) {
		String line = "";
		int lineLength = 0;
		int[] lineToCheck = new int[lineLength];
		int numReturn = 0;
		int currentState = 0;
		int nextState = 0;
		int indexOfElem = 0;

		for (int a = 0; a < stringsList.size(); a++) {
			line = stringsList.get(a);
			// get the length of the line so you know how many transitions to do
			lineLength = line.length();
			// convert the String line to a char array to look at each item
			char[] lineChar = line.toCharArray();
			lineToCheck = new int[lineLength];

			for (int j = 0; j < lineLength; j++) {
				String lineCharStr = String.valueOf(lineChar[j]);
				indexOfElem = alphabet.indexOf(lineCharStr);
				currentState = transTable[currentState][indexOfElem];
			}
			System.out.println("Current " + currentState);
			String finalNum = String.valueOf(currentState);
			//System.out.println("accept states contains " + numReturn + " is: " + acceptStates.contains(String.valueOf(numReturn)));
			if (acceptStates.contains(String.valueOf(finalNum)) == true) {
				System.out.println("accept");
			}
			else {
				System.out.println("reject");
			}
			// reset the current state to zero since the start state is always zero
			currentState = 0;
		}

		

		
	}*/

	/*
		nonempty if and only if some final state is reachable from the start state.
	*/

	public void properties(int numStates, List<String> acceptStates, String alphabet, int[][] transTable, List<String> dfaList, List<String> stringsList) {
		// conver the accepting states into an Integer array so we can add transTable values later on
		//int[] acceptInt = new int[acceptStates.size()];
		List<Integer> acceptInt = new ArrayList<Integer>();
		for (int a = 0; a < acceptStates.size(); a++) {
			String acceptCurr = acceptStates.get(a);
			int acceptIntNum = Integer.valueOf(acceptCurr);
			acceptInt.add(acceptIntNum);
			System.out.println("accept state is: " + acceptInt.get(a));
		}
		// look at each line in the transition table starting at 0 (start state)
		// if we see an accepting state not at the current state or zero, we add it to acceptingInt's array to check later
		for (int i = 0; i < numStates; i++) {
			for (int j = 0; j < alphabet.length(); j++) {
				int returned = transTable[i][j];
				System.out.print(" item: " + returned);
				for (int k = 0; k < acceptInt.size(); k++) {
					if (returned == acceptInt.get(k) && i != returned && i != 0) {
						acceptInt.add(i);
						System.out.println(" accept int is: " + i);
						break;
					}
				}
			}
			System.out.println("");
		}
	}



  public static void main(String[] args) {
	// from looking at my CSCE 146 code, you need to make a class object in order to get around the static/non-static issue
	properties dfa = new properties();
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
	if (0 < args.length) {
	   inFile = new File(args[0]);
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
	        //System.out.println(currLine);
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


	// now that the data has been taken from the file and stored in the DFA list
	// we can begin parsing it
	dfa.readInDFA(numStates, acceptStates, alphabet, transTable, dfaList, stringsList);

	

  }
}
