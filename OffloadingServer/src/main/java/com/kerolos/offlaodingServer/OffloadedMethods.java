package com.kerolos.offlaodingServer;

import java.math.BigInteger;

//This class contains the offloaded methods that the generic engine will call
//The method must be static, its argument and return type must be the type Object
//The parameters also have to be primitive, So for example arrays won't work
public class OffloadedMethods {

	//Calculate the factorial of a given number
	public static Object calculateFactorial(Object obj) {
		BigInteger n = BigInteger.valueOf((long) obj);//convert the parameter to BigInteger
		
		BigInteger result = BigInteger.ONE;//store the factorial result
		
		//Loop until we reach 0, in each iteration we multiply the number by its previous number (i.e. 4*3)
		while (!n.equals(BigInteger.ZERO)) {
			result = result.multiply(n);
			n = n.subtract(BigInteger.ONE);
		}

		return result;
	}

	//Calculate the nth Fibonacci
	public static Object nthFibonacci(Object obj) {
		int n = Integer.parseInt(obj.toString());//Convert the parameter to int
		
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

	//Calculate the nth primal number
	public static Object nthPrime(Object obj) {
		int n = Integer.parseInt(obj.toString());//convert the parameter to int
		
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
	private static boolean isPrime(int number) {
		for (int i = 2; i < number; i++) {
			if (number % i == 0)
				return false;
		}
		return true;
	}

}
