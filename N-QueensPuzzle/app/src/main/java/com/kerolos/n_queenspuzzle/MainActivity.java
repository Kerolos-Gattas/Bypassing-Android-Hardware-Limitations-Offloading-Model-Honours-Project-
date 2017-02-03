//Author: Kerolos Gattas.
//Class' Purpose: Solves the N-Queens Puzzle Problem based on the user's input.
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.kerolos.n_queenspuzzle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    EditText nQueensInput;
    Button solveNQueensBtn;
    TextView nQueensOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        nQueensInput = (EditText) findViewById(R.id.nQueensInput);
        solveNQueensBtn = (Button) findViewById(R.id.solvePuzzleBtn);
        nQueensOutput = (TextView) findViewById(R.id.nQueensOutput);
        nQueensOutput.setMovementMethod(new ScrollingMovementMethod());

        solveNQueensBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
