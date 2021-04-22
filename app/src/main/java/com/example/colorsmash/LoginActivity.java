package com.example.colorsmash;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button singinButton;
    public EditText emailEditText;
    public EditText passwordEditText;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        singinButton = (Button) findViewById(R.id.singinButton);
        emailEditText = (EditText) findViewById(R.id.emailLogin);
        passwordEditText = (EditText) findViewById(R.id.passwordLogin);
        progressDialog = new ProgressDialog(this);

        firebaseAuth=FirebaseAuth.getInstance();
        //checking if the user is connected
        /*
        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext() , StartGameActivity.class));
        }
        */
        singinButton.setOnClickListener(this);

    }

    public final boolean isValidPassword(CharSequence target) {
        if(TextUtils.isEmpty(target)){
            Toast.makeText(this,"Please enter password" , Toast.LENGTH_LONG).show();
            return false;
        }
        if(target.length() < 8){
            Toast.makeText(this,"The password is too short" , Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }

    public final boolean isValidEmail(CharSequence target) {
        if(TextUtils.isEmpty(target)){
            Toast.makeText(this,"Please enter email", Toast.LENGTH_LONG).show();
            return false;
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()) {
            Toast.makeText(this,"The Email is not valid", Toast.LENGTH_LONG).show();
            return false;
        };

        return true; //end
    }

    public int test(int num){
        return num;
    }

    public boolean isEmailValid(String email) {
        return email.contains("@");
    }

    public boolean isPasswordValid(String password) {
        return password.length() > 4;
    }


    private void userLogin( String email ,String password ){


        //Checking if email and password are valid
        if(!isValidEmail(email)){
            isValidPassword(password);
            return ;
        };

        if(!isValidPassword(password)) {
            isValidEmail(email);
            return ;
        }



        progressDialog.setMessage("Checking User Please Wait ...");
        progressDialog.show();
        // Check for a valid email address.

        Task<AuthResult> firebaseTask =  firebaseAuth.signInWithEmailAndPassword(email , password);

        firebaseTask.addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
            public void onComplete(Task<AuthResult> task){
                progressDialog.dismiss();
                //GO TO MAIN ACTIVITY
                if(task.isSuccessful()){

                    finish();
                    startActivity(new Intent(getApplicationContext() , UserOptionsActivity.class));

                }
                else{
                    Toast.makeText(LoginActivity.this,"Could not Login...please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view){
        if(view == singinButton){
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            userLogin(email ,password);
        }

    }

}

