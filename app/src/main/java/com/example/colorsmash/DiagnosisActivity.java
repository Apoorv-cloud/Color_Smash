package com.example.colorsmash;

import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class DiagnosisActivity extends AppCompatActivity implements View.OnClickListener {


    private Button submit ;//buttonSubmitDiagnosis
    private Button buttonQuestion1 ,buttonQuestion2,buttonQuestion3,buttonQuestion4,buttonQuestion5,buttonQuestion6;//button Question Diagnosis
    private boolean[] questionButtonFlags = new boolean[6];
    private EditText textQuestion1,textQuestion2,textQuestion3,textQuestion4,textQuestion5,textQuestion6 ;//editTextQuestion1Diagnosis
    private Boolean protan ,deutan , tritan;
    private ArrayList<String> testBedColors;
    private DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);

        Arrays.fill(questionButtonFlags, Boolean.FALSE);

        protan=false;
        deutan=false;
        tritan=false;
        testBedColors=new ArrayList<String>();
        submit=(Button)findViewById(R.id.buttonSubmitDiagnosis);
        buttonQuestion1=(Button)findViewById(R.id.buttonQuestion1Diagnosis);
        buttonQuestion2=(Button)findViewById(R.id.buttonQuestion2Diagnosis);
        buttonQuestion3=(Button)findViewById(R.id.buttonQuestion3Diagnosis);
        buttonQuestion4=(Button)findViewById(R.id.buttonQuestion4Diagnosis);
        buttonQuestion5=(Button)findViewById(R.id.buttonQuestion5Diagnosis);
        buttonQuestion6=(Button)findViewById(R.id.buttonQuestion6Diagnosis);

        submit.setOnClickListener(this);
        buttonQuestion1.setOnClickListener(this);
        buttonQuestion2.setOnClickListener(this);
        buttonQuestion3.setOnClickListener(this);
        buttonQuestion4.setOnClickListener(this);
        buttonQuestion5.setOnClickListener(this);
        buttonQuestion6.setOnClickListener(this);

        textQuestion1=(EditText)findViewById(R.id.editTextQuestion1Diagnosis);
        textQuestion2=(EditText)findViewById(R.id.editTextQuestion2Diagnosis);
        textQuestion3=(EditText)findViewById(R.id.editTextQuestion3Diagnosis);
        textQuestion4=(EditText)findViewById(R.id.editTextQuestion4Diagnosis);
        textQuestion5=(EditText)findViewById(R.id.editTextQuestion5Diagnosis);
        textQuestion6=(EditText)findViewById(R.id.editTextQuestion6Diagnosis);



    }



    @Override
    public void onClick(View view){
        if(view == submit){

            if( !textQuestion1.getText().toString().equals("16") || questionButtonFlags[0]){
              tritan=true;
            }
            if( !textQuestion2.getText().toString().equals("7") || questionButtonFlags[1]){
                protan=true;
            }
            if( !textQuestion3.getText().toString().equals("12") || questionButtonFlags[2]){
                protan=true;
            }
            if( !textQuestion4.getText().toString().equals("8") || questionButtonFlags[3]){
                deutan=true;
            }
            if( !textQuestion5.getText().toString().equals("13") || questionButtonFlags[4]){
                protan=true;
            }
            if( !textQuestion6.getText().toString().equals("9") || questionButtonFlags[5]){
                deutan=true;
            }

            if(tritan){
                testBedColors.add("TRITAN");
                Log.d("TTT","TRITAN!!!!!");
            }
            if(protan){
                testBedColors.add("PROTAN");
                Log.d("TTT", "PROTAN!!!");
            }
            if(deutan){
                testBedColors.add("DEUTAN");
                Log.d("TTT", "DEOTAN!!!!");
            }

            // get current user uID
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uID = "";
            if (user != null) {
                uID = user.getUid();
            } else {
                // No user is signed in ?? add Exception ??
            }

            mRef = FirebaseDatabase.getInstance().getReference("Users").child(uID);
            mRef.child("badColors").setValue(testBedColors);

            this.finish();
            Intent act = new Intent(DiagnosisActivity.this, DiagnosisResultsActivity.class);
            startActivity(act);
            //goto the result activity




        }
        else if(view == buttonQuestion1){
            changeButton((Button) view,0);


        }

        else if(view == buttonQuestion2){
            changeButton((Button) view,1);

        }
        else if(view == buttonQuestion3){
            changeButton((Button) view,2);

        }

        else if(view == buttonQuestion4){
            changeButton((Button) view,3);

        }

        else if(view == buttonQuestion5){
            changeButton((Button) view,4);
        }

        else if(view == buttonQuestion6){
            changeButton((Button) view,5);
        }

    }


    void changeButton(Button button,int index)
    {
        //This function it to change
        if( questionButtonFlags[index]){
            button.setBackgroundColor(getResources().getColor(R.color.color_blue));
            button.setText("Can not see anything");
            questionButtonFlags[index]= false;

        }
        else{
            button.setBackgroundColor(getResources().getColor(R.color.color_red));
            button.setText("I see");
            questionButtonFlags[index]= true;


        }
    }

}
