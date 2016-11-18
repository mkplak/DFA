import java.util.*;
import java.io.*;

public class MakeDFA {

	public int numStates;
	public List<String> acceptStates;
	public String alphabet;
	public String[] transTable;
	public List<String> dfaList;

	public MakeDFA() {
		this.numStates = 0;
		this.acceptStates = new ArrayList<String>();
		this.alphabet = "";
		this.transTable = null;
		this.dfaList = new ArrayList<String>();
	}

	public void setNumStates(int numStates) {
		this.numStates = numStates;
	}

	public void setAcceptStates(List<String> acceptStates) {
		this.acceptStates = acceptStates;
	}

	public void setAlphabet(String alphabet) {
		this.alphabet = alphabet;
	}

	public void setTransTable(String[] transTable) {
		this.transTable = transTable;
	}

	public void setDFAList(List<String> dfaList) {
		this.dfaList = dfaList;
	}

}
