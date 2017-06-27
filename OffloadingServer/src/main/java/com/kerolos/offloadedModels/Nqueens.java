package com.kerolos.offloadedModels;

import java.util.ArrayList;

//The code for the solution for this problem is not my own, the link for this code
//will be in the project report in the references section
public class Nqueens {

	//store the results for the N-Queens problem
	private ArrayList<String> solutions = new ArrayList<String>();

	public void enumerate(int[] q, int k) {
		int n = q.length;
		//if we reached a solution store it else continue searching for a solution
		if (k == n)
			solutions.add(printQueens(q));
		else {
			for (int i = 0; i < n; i++) {
				q[k] = i;//try a new location for the queen
				
				if (isConsistent(q, k))//check if the new location is valid
					enumerate(q, k + 1);//find a location for the next queen
			}
		}
	}

	//Check the locations for all the queens and test if anyone of them can attack the other
	private boolean isConsistent(int[] q, int n) {
		for (int i = 0; i < n; i++) {
			if (q[i] == q[n])
				return false; // same column
			if ((q[i] - q[n]) == (n - i))
				return false; // same major diagonal
			if ((q[n] - q[i]) == (n - i))
				return false; // same minor diagonal
		}
		return true;
	}

	//Store the solution in the solutions arrayList
	private String printQueens(int[] q) {
		int n = q.length;
		String solution = "";
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (q[i] == j)
					solution += "Q ";
				else
					solution += "* ";
			}
			solution += "\n";
		}
		solution += "\n";
		return solution;
	}
	
	public ArrayList<String> getSolutions() {
		return solutions;
	}
}
