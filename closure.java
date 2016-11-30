import java.util.*;
import java.io.*;

public class closure {

	public void readInDFA(int numStates, List<String> acceptStates, String alphabet, int[][] transTable, List<String> dfaList, List<String> stringsList, boolean isConstruct, int numStates2, List<String> acceptStates2, String alphabet2, int[][] transTable2, List<String> dfaList2, List<String> stringsList2) {
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
			}
			// get the accepting states
			else if (dfaList.get(i).contains("Accepting")) {
				//System.out.println("in accepting");
				String temp2 = dfaList.get(i);
				// add a space at the end to get the last item
				temp2 += " ";
				numAccepting = temp2.length();
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

		if (isConstruct == false) {
			complement(numStates, acceptStates, alphabet, transTableNum, dfaList, stringsList);
		}

		else if (isConstruct == true) {
			// stores second DFA
			int numAccepting2 = 0;
			int alphabetSize2 = 0;
			int transCtr2 = 0;
			int[][] transTableNum2 = new int[numStates2][alphabetSize2];
		
			for (int i = 0; i < dfaList2.size(); i++) {
				// get the number of states
				if (dfaList2.get(i).contains("Number")) {
					String temp = dfaList2.get(i);
					temp = temp.substring(18);
					numStates2 = Integer.parseInt(temp);
				}
				// get the accepting states
				else if (dfaList2.get(i).contains("Accepting")) {
					String temp2 = dfaList2.get(i);
					// add a space at the end to get the last item
					temp2 += " ";
					numAccepting2 = temp2.length();
					temp2 = temp2.substring(18,temp2.length());
					String currElemStr = "";
					String[] acceptStatesArr = temp2.split(" ");
					for (int n = 0; n < acceptStatesArr.length; n++) {
						acceptStates2.add(acceptStatesArr[n]);
					}
				}
				// get the alphabet
				else if (dfaList2.get(i).contains("Alphabet")) {
					String temp3 = dfaList2.get(i);
					//System.out.println(temp3);
					temp3 = temp3.substring(10,temp3.length());
					alphabet2 = temp3;
					alphabetSize2 = alphabet2.length();
					transTableNum2 = new int[numStates2][alphabetSize2];
				}
				// read in the transition table
				else {
					// create an array that is the size of the number of states
					// in here, i will create a string for each row in the table of the transition table
					//rows x columns
					String readIn = dfaList2.get(i);

					if (i > 2) {
						for (int q = 0; q < alphabetSize2; q++) {
							String[] rowStr = readIn.split(" ");
							int rowInt = Integer.parseInt(rowStr[q]);
							transTableNum2[i-3][q] = rowInt;
						}

					}				
				}

			}
			// do the construction of the two DFA's before the end of the if
		construction(numStates, acceptStates, alphabet, transTableNum, dfaList, stringsList, numStates2, acceptStates2, alphabet2, transTableNum2, dfaList2, stringsList2);

	} // end of if second item
}


	public void complement(int numStates, List<String> acceptStates, String alphabet, int[][] transTable, List<String> dfaList, List<String> constructList) {
		List<String> newAccept = new ArrayList<String>();
		for (int i = 0; i < numStates; i++) {
			if (!acceptStates.contains(Integer.toString(i))) {
				newAccept.add(Integer.toString(i));
			}
		}
		System.out.println("Number of states: " + numStates);
		System.out.print("Accepting states: ");
		for (int j = 0; j < newAccept.size(); j++) {
			System.out.print(newAccept.get(j) + " ");
		}
		System.out.println("");
		System.out.println("Alphabet: " + alphabet);
		for (int k = 0; k < numStates; k++) {
			for (int l = 0; l < alphabet.length(); l++) {
				System.out.print(transTable[k][l] + " ");
			}
			System.out.println("");
		}
	}

	public void construction(int numStates, List<String> acceptStates, String alphabet, int[][] transTable, List<String> dfaList, List<String> constructList, int numStates2, List<String> acceptStates2, String alphabet2, int[][] transTable2, List<String> dfaList2, List<String> stringsList2) {
		int numStatesF = numStates * numStates2;
		List<String> acceptStatesF = new ArrayList<String>();
		int[][] transTableF = new int[numStatesF][alphabet.length()];
		int cntr = 0;
		int[][] transTable3 = new int[numStatesF][alphabet.length()];
		for (int a = 0; a < acceptStates.size(); a++) {
			for (int b = 0; b < acceptStates2.size(); b++) {
				String newAccept = acceptStates.get(a) + "" + acceptStates2.get(b);
				int newDigit = ((Integer.valueOf(acceptStates.get(a))) * numStates2) + (Integer.valueOf(acceptStates2.get(b)));
				acceptStatesF.add(Integer.toString(newDigit));
			}
		}

		for (int i = 0; i < numStates; i++) {
			for (int j = 0; j < numStates2; j++) {
				for (int k = 0; k < alphabet.length(); k++) {
					transTableF[cntr][k] = Integer.valueOf((Integer.toString(transTable[i][k]) + "" + Integer.toString(transTable2[j][k])));
					// do the transition to the actual states
					// when looking through the table it seems to do the transition you take the first digit, multiply it
					// by the numStates, then add the second digit
					int firstDig = ((transTable[i][k] * numStates2) + transTable2[j][k]);
					transTable3[cntr][k] = firstDig;
				} 
			//System.out.println("cntr = " + cntr);
			cntr += 1;
			}
		}	
		// print out the new DFA description
		System.out.println("Number of states: " + numStatesF);
		System.out.print("Accepting states: ");
		for (int n = 0; n < acceptStatesF.size(); n++) {
			System.out.print(acceptStatesF.get(n) + " ");
		}
		System.out.println("\nAlphabet: " + alphabet);
		for (int l = 0; l < cntr; l++) {
			for (int m = 0; m < alphabet.length(); m++) {
				System.out.print(transTable3[l][m] + " ");
			}
			System.out.println("");
		}	
	}

  public static void main(String[] args) {
	// from looking at my CSCE 146 code, you need to make a class object in order to get around the static/non-static issue
	closure dfa = new closure();
	MakeDFA newDFA = new MakeDFA();
	int numStates = 0;
	List<String> acceptStates = new ArrayList<String>();
	String alphabet = "";
	int[][] transTable = null;
	List<String> dfaList = new ArrayList<String>();
	List<String> constructList = new ArrayList<String>();
	boolean first = false;
	boolean second = false;

	boolean isSecondItem = false;

	closure dfa2 = new closure();
	int numStates2 = 0;
	List<String> acceptStates2 = new ArrayList<String>();
	String alphabet2 = "";
	int[][] transTable2 = null;
	List<String> dfaList2 = new ArrayList<String>();
	List<String> constructList2 = new ArrayList<String>();


	// http://stackoverflow.com/questions/16802147/java-i-want-to-read-a-file-name-from-command-line-then-use-a-bufferedreader-to
	// read in from the DFA description, and the list of strings on the cmd line
	// we are reading in the DFA as described in the project assignment
	File complement = null;
	File construct = null;
	boolean secondArg = false;
	if (args.length == 1) {
	   complement = new File(args[0]);
	   first = true;
	}
	else if (args.length == 2) {
	   complement = new File(args[0]);
	   first = true;
	   construct = new File(args[1]);
	   secondArg = true;
	   second = true;
	} 
	else {
	   System.err.println("Invalid arguments count:" + args.length);
	   System.exit(0);
	}
	if (secondArg == false) {

		BufferedReader br = null;

		try {
		    String currLine;
		    br = new BufferedReader(new FileReader(complement));
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
		dfa.readInDFA(numStates, acceptStates, alphabet, transTable, dfaList, constructList, secondArg, numStates2, acceptStates2, alphabet2, transTable2, dfaList2, constructList2);
	}

	else if (secondArg == true) {

		BufferedReader br = null;

		try {
		    String currLine;
		    br = new BufferedReader(new FileReader(complement));
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
		    br2 = new BufferedReader(new FileReader(construct));
		    while ((currLine = br2.readLine()) != null) {
			dfaList2.add(currLine);
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
		dfa2.readInDFA(numStates, acceptStates, alphabet, transTable, dfaList, constructList, secondArg, numStates2, acceptStates2, alphabet2, transTable2, dfaList2, constructList2);
	}
  }
}
