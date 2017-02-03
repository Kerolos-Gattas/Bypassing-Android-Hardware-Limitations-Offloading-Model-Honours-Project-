//Author: Kerolos Gattas.
//Class' Purpose: Finds the nth primal number based on the user's input.
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.kerolos.primals;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kerolos.mathalgorithms.R;

import java.math.BigInteger;

public class PrimalsActivity extends AppCompatActivity {

    EditText primalsInput;
    Button caculatePrimalsBtn;
    TextView primalsOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primals);

        primalsInput = (EditText) findViewById(R.id.primalsInput);
        caculatePrimalsBtn = (Button) findViewById(R.id.calculatePrimalsBtn);
        primalsOutput = (TextView) findViewById(R.id.primalsOutput);

        caculatePrimalsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long startTime = System.currentTimeMillis();

                int number = nthPrimal(Integer.parseInt(primalsInput.getText().toString()));
                primalsOutput.setText(String.valueOf(number));

                long stopTime = System.currentTimeMillis();
                long elapsedTime = stopTime - startTime;
                System.out.println("Result: " + number);
                System.out.println("Time1: " + elapsedTime);
            }
        });
    }

    private int nthPrimal(int n){
        int count = 0;
        int number;
        for(number = 2; count < n; number++) {
            if (isPrime(number)) {
                ++count;
            }
        }
        return number -1;
    }

    private boolean isPrime(int number) {
        for(int i = 2; i < number; i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }
}
