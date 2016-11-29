import java.util.*;
import java.io.*;

public class closure {

	public void readInDFA(int numStates, List<String> acceptStates, String alphabet, String[] transTable, List<String> dfaList, List<String> stringsList, boolean isConstruct) {
		int numAccepting = 0;
		int alphabetSize = 0;
		int transCtr = 0;
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

		System.out.println("isConstruct: " + isConstruct);
		if (isConstruct == true) {
			construction(numStates, acceptStates, alphabet, transTableNum, dfaList, stringsList);
		}
		else {
			complement(numStates, acceptStates, alphabet, transTableNum, dfaList, stringsList);
		}
	}


	public void complement(int numStates, List<String> acceptStates, String alphabet, int[][] transTable, List<String> dfaList, List<String> constructList) {
		System.out.println("in complement");
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

	public void construction(int numStates, List<String> acceptStates, String alphabet, int[][] transTable, List<String> dfaList, List<String> constructList) {

		System.out.println("in construction");

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

  public static void main(String[] args) {
	// from looking at my CSCE 146 code, you need to make a class object in order to get around the static/non-static issue
	closure dfa = new closure();
	MakeDFA newDFA = new MakeDFA();
	int numStates = 0;
	List<String> acceptStates = new ArrayList<String>();
	String alphabet = "";
	String[] transTable = null;
	List<String> dfaList = new ArrayList<String>();
	List<String> constructList = new ArrayList<String>();
	boolean first = false;
	boolean second = false;

	closure dfa2 = new closure();
	int numStates2 = 0;
	List<String> acceptStates2 = new ArrayList<String>();
	String alphabet2 = "";
	String[] transTable2 = null;
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
	   System.out.println("two args");
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

	BufferedReader br = null;

	try {
	    String currLine;
	    br = new BufferedReader(new FileReader(complement));
		System.out.println("first DFA is: ");
	    while ((currLine = br.readLine()) != null) {
		dfaList.add(currLine);
		System.out.println(currLine);
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
	dfa.readInDFA(numStates, acceptStates, alphabet, transTable, dfaList, constructList, secondArg);


	if (secondArg == true) {

		System.out.println("in second arg stuff");
		BufferedReader br2 = null;

		try {
		    String currLine;
		    br2 = new BufferedReader(new FileReader(construct));
		    System.out.println("second DFA is: ");
		    while ((currLine = br2.readLine()) != null) {
			dfaList2.add(currLine);
			System.out.println(currLine);
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
		dfa2.readInDFA(numStates2, acceptStates2, alphabet2, transTable2, dfaList2, constructList2, secondArg);
	}
  }
}

public class DFAObj {
	int numStatesObj = 0;
	List<String> acceptStatesObj = new ArrayList<String>();
	String alphabetObj = "";
	//String[] transTableObj = null;
	List<String> dfaListObj = new ArrayList<String>();
	List<String> stringsListObj = new ArrayList<String>();
	boolean isConstruct = false;
	int[][] transTableObj = new int[numStatesObj][alphabetObj.length()];

	DFA(int numStates, List<String> acceptStates, String alphabet, List<String> dfaList, List<String> stringsList, int[][] transTable) {
		this.numStatesObj = numStates;
		this.acceptStatesObj = acceptStates;
		this.alphabetObj = alphabet;
		this.dfaListObj = dfaList;
		this.stringsListObj = stringsList;
		this.transTableObj = transTable;
	}
}
