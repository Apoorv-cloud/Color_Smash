package com.example.colorsmash;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class UserOptionsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonStartGame;
    private Button buttonShowLeaderBoard;
    private Button buttonMyDiagnosis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_options);

        buttonStartGame = (Button) findViewById(R.id.ButtonStartGameForRegisteredUser);
        buttonShowLeaderBoard = (Button) findViewById(R.id.ButtonShowLeaderBoard);
        buttonMyDiagnosis = (Button) findViewById(R.id.ButtonMyDiagnosis);

        buttonStartGame.setOnClickListener(this);
        buttonShowLeaderBoard.setOnClickListener(this);
        buttonMyDiagnosis.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == buttonStartGame) {
            Intent act = new Intent(UserOptionsActivity.this, UserGameActivity.class);
            startActivity(act);
            finish();
        }
        else if ( v == buttonShowLeaderBoard){
            Intent act = new Intent(UserOptionsActivity.this, LeaderBoardActivity.class);
            startActivity(act);
            finish();
        }
        else if ( v == buttonMyDiagnosis){
            Intent act = new Intent(UserOptionsActivity.this, MyDiagnosisActivity.class);
            startActivity(act);
            finish();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
