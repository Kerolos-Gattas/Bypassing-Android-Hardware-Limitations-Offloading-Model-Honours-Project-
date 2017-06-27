package com.kerolos.offlaodingServer;

import java.lang.reflect.*;
import java.math.BigInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.kerolos.offloadedModels.Factorials;
import com.kerolos.offloadedModels.Fibonacci;
import com.kerolos.offloadedModels.Nqueens;
import com.kerolos.offloadedModels.PrimalNumbers;
import com.kerolos.resources.CommunicationMessages;

//This class is responsible for all the offloading communications for the generic and non generic ones
@Path("/offloading")
public class OffloadingCommunication {

	//The generic offloading method
	@POST
	@Path("getOffloadingResults-p")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public JSONObject getOffloadingResults(@FormParam(CommunicationMessages.METHOD_NAME) String methodName,
			@FormParam(CommunicationMessages.METHOD_PARAMETERS) String parameterStr) {
		
		JSONObject error = null;

		try {
			
			//We use reflection to find the correct offloaded method and invoke it using the parameters
			OffloadedMethods offloadedMethods = new OffloadedMethods();
			Class cls = offloadedMethods.getClass();
			Method[] methods = cls.getMethods();
			//Loop through all the methods and find the correct one
			int methodIndex = 0;
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].getName().equals(methodName)) {
					methodIndex = i;
					break;
				}
			}
			
			//Using the parser we retrieve the arguments for the offloaded method 
			JSONParser parser = new JSONParser();
			JSONObject parameters = (JSONObject) parser.parse(parameterStr);
			JSONArray parametersArr = (JSONArray) parameters.get(CommunicationMessages.METHOD_PARAMETERS);



			//To invoke the offloaded method with several parameters we have to use an array
			Object parameter[] = new Object[parametersArr.size()];
			for (int i = 0; i < parameter.length; i++) {
				parameter[i] = parametersArr.get(i);//get the parameters from the Json array
			}

			//invoke the offloaded method
			Object obj = methods[methodIndex].invoke(null, parameter);

			//Send the result to the client
			JSONObject offloadedObj = new JSONObject();
			JSONArray offloadedList = new JSONArray();
			offloadedList.add(obj);
			offloadedObj.put(CommunicationMessages.OFFLOADED_RESULTS, offloadedList);
			return offloadedObj;
			
		} catch (IllegalAccessException  | IllegalArgumentException | InvocationTargetException | ParseException e) {
			e.printStackTrace();
			return error;
		}
	}

	//This method does the non generic offloading for the N-Queens puzzle
	@POST
	@Path("nQueensOffloading-p")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public JSONObject nQueensOffloading(@FormParam(CommunicationMessages.NQUEENS_N) String n) {

		JSONObject error = null;

		try {
			//Call the n-queens method to solve the puzzle
			Nqueens nQueens = new Nqueens();
			int[] q = new int[Integer.valueOf(n)];
			nQueens.enumerate(q, 0);

			//Send the solution to the client
			if (!nQueens.getSolutions().isEmpty()) {
				JSONObject offloadedObj = new JSONObject();
				JSONArray offloadedList = new JSONArray();

				for (int i = 0; i < nQueens.getSolutions().size(); i++) {
					offloadedList.add(nQueens.getSolutions().get(i));
				}
				offloadedObj.put(CommunicationMessages.NQUEENS_RESULTS, offloadedList);
				return offloadedObj;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return error;
		}
		return error;
	}

	//This method does the non generic offloading for the factorial
	@POST
	@Path("factorialOffloading-p")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response factorialOffloading(@FormParam(CommunicationMessages.FACTORIALS_N) String n) {

		try {
			//call the factorials method to solve the problem
			Factorials factorials = new Factorials();
			BigInteger result = factorials.calculateFactorial(new BigInteger(n));
			//send the result to the server
			return Response.ok(result.toString()).build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok(e.getLocalizedMessage()).build();
		}
	}

	//This method does the non generic offloading for the Fibonacci
	@POST
	@Path("fibonacciOffloading-p")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response fibonacciOffloading(@FormParam(CommunicationMessages.FIBONACCI_N) String n) {

		try {
			//call the Fibonacci method to solve the problem
			Fibonacci fibonacci = new Fibonacci();
			BigInteger result = fibonacci.nthFibonacci(Integer.valueOf(n));
			//send the result to the server
			return Response.ok(String.valueOf(result)).build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok(e.getLocalizedMessage()).build();
		}
	}

	//This method does the non generic offloading for the nth prime number
	@POST
	@Path("primalOffloading-p")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response primalOffloading(@FormParam(CommunicationMessages.NTH_PRIMAL) String n) {

		try {
			//call the nthPrimal method to solve the problem
			PrimalNumbers nthPrimal = new PrimalNumbers();
			int result = nthPrimal.nthPrimal(Integer.valueOf(n));
			//send the result to the server
			return Response.ok(String.valueOf(result)).build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok(e.getLocalizedMessage()).build();
		}
	}
}
