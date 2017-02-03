//Author: Kerolos Gattas.
//Class' Purpose: Finds the nth fibonacci number based on the user's input.
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.kerolos.fibonacci;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kerolos.mathalgorithms.R;

public class fibonacciActivity extends AppCompatActivity {

    EditText fibonacciInput;
    Button caculateFibonacciBtn;
    TextView fibonacciOutput;

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
                long startTime = System.currentTimeMillis();

                int fib = nthFibonacci(Integer.parseInt(fibonacciInput.getText().toString()));
                fibonacciOutput.setText(String.valueOf(fib));

                long stopTime = System.currentTimeMillis();
                long elapsedTime = stopTime - startTime;
                System.out.println("Result: " + fib);
                System.out.println("Time1: " + elapsedTime);
            }
        });
    }

    private int nthFibonacci(int n){

        int a = 1;
        int b = 1;
        int[] sequence = new int[n];

        for (int i = 0; i < n; i++) {
            sequence[i] = a;

            int temp = a;
            a = b;
            b = temp + b;
        }

        return sequence[n-1];
    }
}
