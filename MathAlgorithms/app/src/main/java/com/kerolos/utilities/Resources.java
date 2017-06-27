package com.kerolos.utilities;

public class Resources {

    //Server communication paths
    public static final String SERVER_URL = "http://192.168.0.19:8080";
    public static final String SERVER_OFFLOADING = SERVER_URL + "/OffloadingServer/offloading";
    public static final String GENERIC_OFFLOADING_RESULTS = "/getOffloadingResults-p";
    public static final String FACTORIAL_OFFLOADING = "/factorialOffloading-p";
    public static final String FIBONACCI_OFFLOADING = "/fibonacciOffloading-p";
    public static final String NTH_PRIMAL_OFFLOADING = "/primalOffloading-p";

    //Generic offloading Server communication messages
    public final static String METHOD_NAME = "Method_Name";
    public final static String METHOD_PARAMETERS = "Method_Parameters";
    public final static String OFFLOADED_RESULTS = "Generic Offloaded results";

    //Server offloading communication messages
    public final static String FACTORIALS_N = "Factorials-N";
    public final static String FIBONACCI_N = "Fibonacci-N";
    public final static String NTH_PRIMAL = "n_th Primal";

    //Error messages
    public final static String SERVER_OFFLOADING_ERROR = "Server Offloading Error";
    public final static String JSON_DATA_ERROR = "Json Data Error";
    public final static String DATA_PREP = "Error Preparing Data For Server";
}
