package com.example.colorsmash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private Button buttonLogin;
    private Button buttonAdminLogin;
    private Button buttonStartGame;
    private Button buttonExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonExit = (Button) findViewById(R.id.ButtonExit);
        buttonLogin = (Button) findViewById(R.id.ButtonLogin);
        buttonAdminLogin = (Button) findViewById(R.id.ButtonAdminLogin);
        buttonRegister = (Button) findViewById(R.id.ButtonRegister);
        buttonStartGame = (Button) findViewById(R.id.ButtonStartGame);

        buttonRegister.setOnClickListener(this);
        buttonStartGame.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);
        buttonAdminLogin.setOnClickListener(this);
        buttonExit.setOnClickListener(this);

    }

    //check
    @Override
    public void onClick(View view)
    {
        if(view==buttonStartGame) {
            this.finish();
            Intent act = new Intent (MainActivity.this,StartGameActivity.class);
            startActivity(act);
        }
        else if(view == buttonRegister) {
            Intent act = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(act);
        }

        else if(view == buttonLogin) {
            Intent act = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(act);
        }

        else if (view == buttonAdminLogin) {
            Intent act = new Intent(MainActivity.this, AdminLoginActivity.class);
            startActivity(act);
        }
        else if (view == buttonExit) {
            this.finish();
            System.exit(0);
        }


    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            this.finish();
            System.exit(0);
        }
        return super.onKeyDown(keyCode, event);
    }






}
