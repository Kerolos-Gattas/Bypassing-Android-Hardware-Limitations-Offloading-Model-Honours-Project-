package com.kerolos.fibonacci;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kerolos.mathalgorithms.R;
import com.kerolos.utilities.CustomRequest;
import com.kerolos.utilities.Resources;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class FibonacciActivity extends Activity {

    EditText fibonacciInput;
    Button caculateFibonacciBtn;
    TextView fibonacciOutput;
    String results;//store the solution for the problem as well as the runtime

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci);

        fibonacciInput = (EditText) findViewById(R.id.fibonacciInput);
        caculateFibonacciBtn = (Button) findViewById(R.id.calculateFibonacciBtn);
        fibonacciOutput = (TextView) findViewById(R.id.fibonacciOutput);

        caculateFibonacciBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //execute the problem locally, direct offloading and generic offloading
                results = "";
                long startTime = System.nanoTime();
                BigInteger fib = nthFibonacci(Integer.parseInt(fibonacciInput.getText().toString()));
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                results += "Runtime on   device:                " + Long.toString(duration) + "\n";

                startTime = System.nanoTime();
                sendOffloadingParameters();
                endTime = System.nanoTime();
                duration = (endTime - startTime);
                results += "Offloading Runtime:                " + Long.toString(duration) + "\n";

                startTime = System.nanoTime();
                sendGenericOffloadingParameters();
                endTime = System.nanoTime();
                duration = (endTime - startTime);
                results += "Generic Offloading Runtime: " + Long.toString(duration) + "\n";

                results += "Result: " + fib;
                fibonacciOutput.setText(results);

            }
        });
    }

    private void sendGenericOffloadingParameters(){
        try {
            ProgressDialog progress = new ProgressDialog(FibonacciActivity.this);
            progress.setTitle("Contacting Server");
            progress.setMessage("Please Wait While code is offloaded");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();

            String url = Resources.SERVER_OFFLOADING + Resources.GENERIC_OFFLOADING_RESULTS;
            RequestQueue requestQueue = Volley.newRequestQueue(FibonacciActivity.this);

            //convert the parameters to JSON then to a string and send it to the server
            JSONObject jsonObject = new JSONObject();
            JSONArray arr = new JSONArray();
            Object n = Integer.parseInt(fibonacciInput.getText().toString());
            arr.put(n);
            jsonObject.put(Resources.METHOD_PARAMETERS, arr);

            Map<String, String> params = new HashMap<>();
            params.put(Resources.METHOD_NAME, "nthFibonacci");
            params.put(Resources.METHOD_PARAMETERS, jsonObject.toString());

            CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, this.createRequestSuccessListener(), this.createRequestErrorListener());
            requestQueue.add(jsObjRequest);

            progress.dismiss();
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(),
                    Resources.DATA_PREP, Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private Response.Listener<JSONObject> createRequestSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray result = (JSONArray) response.get(Resources.OFFLOADED_RESULTS);
                    //printing out the solution from the server, not displaying it to the user since
                    //we already display it before.
                    System.out.print("Generic Result: ");
                    for (int i = 0; i < result.length(); i++) {
                        //results += requestsStr.get(i);
                        System.out.print(result.get(i));
                    }
                    System.out.println();

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),
                            Resources.JSON_DATA_ERROR, Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        };
    }

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

    //send direct offloading parameters
    private void sendOffloadingParameters() {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Contacting Server");
        progress.setMessage("Please Wait While code is offloaded");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        String url = Resources.SERVER_OFFLOADING + Resources.FIBONACCI_OFFLOADING;
        RequestQueue queue = Volley.newRequestQueue(FibonacciActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Result: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        Resources.SERVER_OFFLOADING_ERROR, Toast.LENGTH_LONG).show();
                error.printStackTrace();
                return;
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put(Resources.FIBONACCI_N, fibonacciInput.getText().toString());
                return params;
            }
        };

        queue.add(stringRequest);
        progress.dismiss();
    }

    //Calculate the nth Fibonacci
    private BigInteger nthFibonacci(int n){
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
        return sequence[n-1];
    }
}
