//Author: Kerolos Gattas.
//Class' Purpose: Performs factorial operation on the user's input.
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.kerolos.factorials;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kerolos.mathalgorithms.R;

import org.w3c.dom.Text;

import java.math.BigInteger;

public class FactorialsActivity extends Activity {

    EditText factorialsInput;
    Button caculateFactorialsBtn;
    TextView factorialsOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_factorials);

        factorialsInput = (EditText) findViewById(R.id.factorialsInput);
        caculateFactorialsBtn = (Button) findViewById(R.id.calculateFactorialsBtn);
        factorialsOutput = (TextView) findViewById(R.id.factorialsOutput);
        factorialsOutput.setMovementMethod(new ScrollingMovementMethod());

        caculateFactorialsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long startTime = System.currentTimeMillis();

                BigInteger result = calculateFactorial(new BigInteger(factorialsInput.getText().toString()));
                factorialsOutput.setText(String.valueOf(result));

                long stopTime = System.currentTimeMillis();
                long elapsedTime = stopTime - startTime;
                System.out.println("Result: " + result);
                System.out.println("Time1: " + elapsedTime);
            }
        });
    }

    private BigInteger calculateFactorial(BigInteger n){
        BigInteger result = BigInteger.ONE;
        while (!n.equals(BigInteger.ZERO)) {
            result = result.multiply(n);
            n = n.subtract(BigInteger.ONE);
        }
        return result;
    }
}
