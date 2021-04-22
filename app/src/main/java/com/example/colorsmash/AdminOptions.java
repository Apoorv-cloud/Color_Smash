package com.example.colorsmash;

import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.NonNull;
import androidx.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AdminOptions extends AppCompatActivity implements View.OnClickListener {

    private Button buttonShowUsers;
    private Button buttonResetLeaderBoard;
    private Button buttonChangeUserColors;
    private Button buttonStatistics;
    private TextView textViewGamesCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_options);

        buttonShowUsers = (Button) findViewById(R.id.ButtonShowUsers);
        buttonResetLeaderBoard = (Button) findViewById(R.id.ButtonResetLeaderBoard);
        buttonChangeUserColors = (Button) findViewById(R.id.ButtonChangeUserColors);
        buttonStatistics = (Button)findViewById(R.id.ButtonStatistics);
        textViewGamesCounter = (TextView)findViewById(R.id.textViewGamesCounter);

        buttonShowUsers.setOnClickListener(this);
        buttonResetLeaderBoard.setOnClickListener(this);
        buttonChangeUserColors.setOnClickListener(this);
        buttonStatistics.setOnClickListener(this);

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Counter");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String counter = (String)dataSnapshot.getValue();
                textViewGamesCounter.setText(textViewGamesCounter.getText()+" "+ counter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v == buttonShowUsers) {
            Intent act = new Intent(AdminOptions.this, ShowUsers.class);
            startActivity(act);
        }
        else if ( v == buttonResetLeaderBoard){
            DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("LeaderBoard");
            mRef.removeValue();
            final DatabaseReference mRef2 = FirebaseDatabase.getInstance().getReference("Users");
            mRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds:dataSnapshot.getChildren()){
                        String key=ds.getKey();
                        mRef2.child(key).child("highscore").setValue("0");
                        mRef2.child(key).child("scores").setValue(new HashMap<String,String>());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            Toast.makeText(this, "Users scores and leader board cleared!", Toast.LENGTH_LONG).show();

        }
        else if (v == buttonChangeUserColors){
            Intent act = new Intent(AdminOptions.this, ChangeUserColorsActivity.class);
            startActivity(act);
        }

        else  if (v == buttonStatistics){
            Intent act = new Intent(AdminOptions.this, AdminStatisticsActivity.class);
            startActivity(act);
        }
    }
}
