package com.example.colorsmash;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

public class GameInstructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_instructions);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Intent act = new Intent(GameInstructionsActivity.this, UserGameActivity.class);
            startActivity(act);
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
