package com.example.colorsmash;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CheckWheterToBeTestedActivity extends AppCompatActivity implements View.OnClickListener {


    private Button buttonYesTest;
    private Button buttonNoTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_wheter_to_be_tested);

        buttonYesTest = (Button)findViewById(R.id.ButtonYesTest);
        buttonNoTest = (Button)findViewById(R.id.ButtonNoTest);

        buttonNoTest.setOnClickListener(this);
        buttonYesTest.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (view == buttonYesTest) {
            Intent act = new Intent(this, DiagnosisActivity.class);
            startActivity(act);
            this.finish();
            return;

        }

        else if (view == buttonNoTest){
            Intent act = new Intent(this, UserGameActivity.class);
            startActivity(act);
            this.finish();
            return;
        }
    }
}
