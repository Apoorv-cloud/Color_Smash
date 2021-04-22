package com.example.colorsmash;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowPreviousScoresActivity extends AppCompatActivity {

    //Active User
    private String uID;
    private FirebaseUser user;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_previous_scores);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uID = "";
        if (user != null) {
            uID = user.getUid();
        } else {
            // No user is signed in ?? add Exception ??
        }

        final LinearLayout layout = (LinearLayout) findViewById(R.id.llayout);

        mRef = FirebaseDatabase.getInstance().getReference("Users").child(uID).child("scores");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> scores = (ArrayList<String>)dataSnapshot.getValue();
                if(scores==null){
                    scores = new ArrayList<String>();
                }
                for(int i=0;i<scores.size();i++){
                    TextView score = new TextView(getBaseContext());
                    score.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    score.setText(i+1+" : "+scores.get(i));
                    score.setTextSize(30);
                    score.setTextColor(getResources().getColor(android.R.color.black));
                    score.setPadding(20, 20, 20, 20);
                    score.setTypeface(null, Typeface.BOLD);
                    score.setBackgroundColor(Color.parseColor("#9580D8FF"));
                    layout.addView(score);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Intent act = new Intent(ShowPreviousScoresActivity.this, UserGameActivity.class);
            startActivity(act);
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
