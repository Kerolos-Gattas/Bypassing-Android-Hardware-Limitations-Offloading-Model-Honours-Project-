package com.kerolos.offloadedModels;

import java.math.BigInteger;

public class Fibonacci {

	//Calculate the nth Fibonacci
	public BigInteger nthFibonacci(int n) {
		//Initialize the first two Fibonacci numbers
		BigInteger a = new BigInteger("1");
		BigInteger b = new BigInteger("1");
		
		//Store each calculated Fibonacci in this array
		BigInteger[] sequence = new BigInteger[n];

		//In each iteration calculate the next Fibonacci until we reach the desired nth Fibonacci
		for (int i = 0; i < n; i++) {
			sequence[i] = a;
			a = b;
			b = b.add(sequence[i]);
		}
		return sequence[n - 1];
	}
}
