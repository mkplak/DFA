import java.util.*;
import java.io.*;

public class DFA {

	public void readInDFA(int numStates, List<String> acceptStates, String alphabet, String[] transTable, List<String> dfaList) {
		int numAccepting = 0;
		int alphabetSize = 0;
		int transCtr = 0;
		
		for (int i = 0; i < dfaList.size(); i++) {
			System.out.println(dfaList.get(i));
			// get the number of states
			if (dfaList.get(i).contains("Number")) {
				String temp = dfaList.get(i);
				temp = temp.substring(18,19);
				numStates = Integer.parseInt(temp);
				transTable = new String[numStates];

				//makeDFA.setNumStates(numStates);
			}
			// get the accepting states
			else if (dfaList.get(i).contains("Accepting")) {
				String temp2 = dfaList.get(i);
				numAccepting = temp2.length();
				temp2 = temp2.substring(18,temp2.length());
				temp2 = temp2.replaceAll("\\s+", "");
				for (int j = 0; j < temp2.length(); j++) {
					char currElem = temp2.charAt(j);
					String currElemStr = String.valueOf(currElem);
					acceptStates.add(currElemStr);
					System.out.println(currElemStr);
				}
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
			}
			// read in the transition table
			else {
				// create an array that is the size of the number of states
				// in here, i will create a string for each row in the table of the transition table
				String readIn = dfaList.get(i);
				readIn = readIn.replaceAll("\\s+", "");
				transTable[transCtr] = transCtr + " " + readIn;
				transCtr++;				
			}
			for (int k = 0; k < transTable.length; k++) {
				System.out.println(transTable[k]);
			}
		}
		simulator(numStates, acceptStates, alphabet, transTable, dfaList);
	}
	
	public void simulator(int numStates, List<String> acceptStates, String alphabet, String[] transTable, List<String> dfaList) {
		
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


	// http://stackoverflow.com/questions/16802147/java-i-want-to-read-a-file-name-from-command-line-then-use-a-bufferedreader-to
	// read in from a file on the cmd line
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
	dfa.readInDFA(numStates, acceptStates, alphabet, transTable, dfaList);

  }
}
