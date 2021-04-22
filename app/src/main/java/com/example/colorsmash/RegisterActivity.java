package com.example.colorsmash;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    private DatabaseReference mRef;

    private FirebaseDatabase mDataBase;

    private int index;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);


        buttonRegister = (Button) findViewById(R.id.ButtonRegister);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);

        mDataBase=FirebaseDatabase.getInstance();
        mRef = mDataBase.getReference("Users");
    }

    private void registerUser()
    {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //in case email field is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }
        if (!isEmailValid(email)) {
            //bad email
            Toast.makeText(this, "Please enter valid email", Toast.LENGTH_LONG).show();
            return;
        }
        if(!isPasswordValid(password)){
            //bad password
            Toast.makeText(this, "Please enter at least 8 characters password", Toast.LENGTH_LONG).show();
            return;
        }


        //incase both validations went well
        //show progress bar
        progressDialog.setMessage("Registering User please wait...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful())
                {
                    //user is succesfully registered and logged in
                    //we will start the profile activity here
                    //for now we will display a TOAST only,
                    Toast.makeText(RegisterActivity.this,"Registered Succesfully", Toast.LENGTH_SHORT).show();
                    createUserAccount();
                    Intent act = new Intent(RegisterActivity.this, PersonalInfo.class);
                    startActivity(act);

                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"Bad email input, Please use valid mail\nOr email is already taken", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onClick(View view)
    {
        if(view == buttonRegister)
            registerUser();

        if(view == textViewSignin) {
            Intent act = new Intent(this, LoginActivity.class);
            startActivity(act);
        }
    }


    public void createUserAccount(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uID = "";
        if (user != null) {
            uID = user.getUid();
        } else {
            // No user is signed in
        }
        String email = editTextEmail.getText().toString().trim();
        mRef.child(uID).child("username").setValue(email);
        mRef.child(uID).child("gender").setValue("none");
        mRef.child(uID).child("age").setValue(999);
        mRef.child(uID).child("name").setValue("none");
        mRef.child(uID).child("scores").setValue(new HashMap<String,String>());
        mRef.child(uID).child("highscore").setValue("0");
        mRef.child(uID).child("colorblind").setValue("false");
        mRef.child(uID).child("badColors").setValue(new HashMap<String,String>());

    }

    public boolean isEmailValid(String email) {
        return email.contains("@") && email.contains(".");
    }

    public boolean isPasswordValid(String password) {
        return password.length() > 7;
    }

}
