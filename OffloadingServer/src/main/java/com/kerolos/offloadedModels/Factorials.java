package com.kerolos.offloadedModels;

import java.math.BigInteger;

public class Factorials {
	
	//Calculate the factorial of a given number
	public BigInteger calculateFactorial(BigInteger n) {
		BigInteger result = BigInteger.ONE;//store the factorial result
		
		//Loop until we reach 0, in each iteration we multiply the number by its previous number (i.e. 4*3)
		while (!n.equals(BigInteger.ZERO)) {
			result = result.multiply(n);
			n = n.subtract(BigInteger.ONE);
		}
		return result;
	}
}
