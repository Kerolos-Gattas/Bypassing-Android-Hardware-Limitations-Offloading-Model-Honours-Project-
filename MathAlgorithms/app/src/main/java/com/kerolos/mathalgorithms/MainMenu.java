//Author: Kerolos Gattas.
//Class' Purpose: Main menu for the application and handling user's choice.
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.kerolos.mathalgorithms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.kerolos.factorials.FactorialsActivity;
import com.kerolos.fibonacci.fibonacciActivity;
import com.kerolos.primals.PrimalsActivity;

public class MainMenu extends Activity {

    private Button factorialsBtn;
    private Button fibonacciBtn;
    private Button primalsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_menu);

        factorialsBtn = (Button) findViewById(R.id.factorialsBtn);
        fibonacciBtn = (Button) findViewById(R.id.fibonacciBtn);
        primalsBtn = (Button) findViewById(R.id.primalsBtn);

        factorialsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent factorialsIntent = new Intent(MainMenu.this, FactorialsActivity.class);
                startActivity(factorialsIntent);
            }
        });

        fibonacciBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent factorialsIntent = new Intent(MainMenu.this, fibonacciActivity.class);
                startActivity(factorialsIntent);
            }
        });

        primalsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent factorialsIntent = new Intent(MainMenu.this, PrimalsActivity.class);
                startActivity(factorialsIntent);
            }
        });
    }
}
