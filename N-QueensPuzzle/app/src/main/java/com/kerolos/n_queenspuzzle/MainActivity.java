package com.kerolos.n_queenspuzzle;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    EditText nQueensInput;
    Button solveNQueensBtn;
    TextView nQueensOutput;

    ArrayList<String> solutions; //store the results for the N-Queens problem
    String results;//store the runtime results and the N-Queens solution

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        results = "";
        nQueensInput = (EditText) findViewById(R.id.nQueensInput);
        solveNQueensBtn = (Button) findViewById(R.id.solvePuzzleBtn);
        nQueensOutput = (TextView) findViewById(R.id.nQueensOutput);
        nQueensOutput.setMovementMethod(new ScrollingMovementMethod());

        solveNQueensBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                results = "";
                //Solve the N-Queens on the phone
                long startTime = System.nanoTime();

                int[] board = new int[Integer.parseInt(nQueensInput.getText().toString())];
                solutions = new ArrayList<String>();
                enumerate(board, 0);

                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                results += "Runtime on   device: " + Long.toString(duration) + "\n";

                //Solve the N-Queens using offloading through the server
                startTime = System.nanoTime();

                sendOffloadingParameters();

                endTime = System.nanoTime();
                duration = (endTime - startTime);
                results += "Offloading Runtime: " + Long.toString(duration) + "\n";
                for (int i = 0; i < solutions.size(); i++) {
                    results += solutions.get(i);
                }

                nQueensOutput.setText(results);
            }
        });
    }

    //Send parameters to the server and receive the result
    private void sendOffloadingParameters() {

        try {
            ProgressDialog progress = new ProgressDialog(MainActivity.this);
            progress.setTitle("Contacting Server");
            progress.setMessage("Please Wait While the code is being offloaded");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();

            //send the parameters using volley
            String url = Resources.SERVER_OFFLOADING + Resources.NQUEENS_OFFLOADING;
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

            Map<String, String> params = new HashMap<>();
            params.put(Resources.NQUEENS_N, nQueensInput.getText().toString());
            CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, this.createRequestSuccessListener(), this.createRequestErrorListener());
            requestQueue.add(jsObjRequest);

            progress.dismiss();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    Resources.DATA_PREP, Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    //Receive the solution to the puzzle
    private Response.Listener<JSONObject> createRequestSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray requestsStr = (JSONArray) response.get(Resources.NQUEENS_RESULTS);
                    //printing out the solution from the server, not displaying it to the user since
                    //we already display it before.
                    System.out.println("Result: ");
                    for (int i = 0; i < requestsStr.length(); i++) {
                        System.out.println(requestsStr.get(i));
                    }


                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),
                            Resources.JSON_DATA_ERROR, Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        };
    }

    //Receive error response from the server
    private Response.ErrorListener createRequestErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        Resources.SERVER_OFFLOADING_ERROR, Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        };
    }

    //The code for the solution for this problem is not my own, the link for this code
    //will be in the project report in the references section
    private void enumerate(int[] q, int k) {
        int n = q.length;
        if (k == n) solutions.add(printQueens(q));
        else {
            for (int i = 0; i < n; i++) {
                q[k] = i;
                if (isConsistent(q, k))
                    enumerate(q, k + 1);
            }
        }
    }

    private boolean isConsistent(int[] q, int n) {
        for (int i = 0; i < n; i++) {
            if (q[i] == q[n]) return false;   // same column
            if ((q[i] - q[n]) == (n - i)) return false;   // same major diagonal
            if ((q[n] - q[i]) == (n - i)) return false;   // same minor diagonal
        }
        return true;
    }

    private String printQueens(int[] q) {
        int n = q.length;
        String solution = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (q[i] == j) solution += "Q ";
                else solution += "* ";
            }
            solution += "\n";
        }
        solution += "\n";
        return solution;
    }
}
