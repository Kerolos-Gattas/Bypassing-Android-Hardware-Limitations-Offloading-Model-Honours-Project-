package com.kerolos.n_queenspuzzle;


public class Resources {

    //Server communication paths
    public static final String SERVER_URL = "http://192.168.0.19:8080";
    public static final String SERVER_OFFLOADING = SERVER_URL + "/OffloadingServer/offloading";
    public static final String NQUEENS_OFFLOADING = "/nQueensOffloading-p";

    //Server communication messages
    public final static String NQUEENS_N = "NQueens-N";
    public final static String NQUEENS_RESULTS = "NQueens Results";

    //Error messages
    public final static String SERVER_OFFLOADING_ERROR = "Server Offloading Error";
    public final static String JSON_DATA_ERROR = "Json Data Error";
    public final static String DATA_PREP = "Error Preparing Data For Server";
}
