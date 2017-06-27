package com.kerolos.offloadedModels;

public class PrimalNumbers {

	//Calculate the nth primal number
	public int nthPrimal(int n) {
		//Loop until we find the nth primal number
		int count = 0;
		int number;
		for (number = 2; count < n; number++) {
			if (isPrime(number)) {
				++count;
			}
		}
		return number - 1;
	}

	//Check if the number is a prime number or not
	private boolean isPrime(int number) {
		for (int i = 2; i < number; i++) {
			if (number % i == 0)
				return false;
		}
		return true;
	}
}
