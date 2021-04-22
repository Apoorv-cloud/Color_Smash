package com.example.colorsmash;

import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminStatisticsActivity extends AppCompatActivity {


    private TextView textViewAverageAge;
    private TextView textViewMostCommonGender;
    private TextView textViewMostCommonColors;
    private TextView textViewColorblindUsers;
    private ArrayList<User> users;
    private HashMap<String,Integer> colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_statistics);

        users = new ArrayList<User>();
        colors = new HashMap<String,Integer>();
        colors.put("PROTAN",0);
        colors.put("DEUTAN",0);
        colors.put("TRITAN",0);
        textViewAverageAge = (TextView)findViewById(R.id.textViewAverageAge);
        textViewMostCommonGender = (TextView)findViewById(R.id.textViewMostCommonGender);
        textViewMostCommonColors = (TextView)findViewById(R.id.textViewMostCommonColors);
        textViewColorblindUsers = (TextView)findViewById(R.id.textViewColorblindUsers);

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Users");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    User user=ds.getValue(User.class);
                    users.add(user);

                }

                // filter only colorblind users
                List<User> colorblidUsers = new ArrayList<>();
                for(User u: users) {
                    if(u.getBadColors()!=null && u.getBadColors().get(0)!=null) {
                        colorblidUsers.add(u);
                    }
                }

                // print amount of diagnosed color blindness users
                textViewColorblindUsers.setText(textViewColorblindUsers.getText()+" "+ colorblidUsers.size());

                // calc age average
                float age = 0;
                if(colorblidUsers.size()>0) {
                    for (User u : colorblidUsers) {
                        age += Integer.valueOf(u.getAge());
                    }
                    age = age / colorblidUsers.size();
                }

                // print age average
                textViewAverageAge.setText(textViewAverageAge.getText()+" "+ age);


                // calc common gender
                int mCount = 0,fCount = 0;
                String gender = "None";

                if(colorblidUsers.size()>0) {
                    for (User u : colorblidUsers) {
                        if (u.getGender().equals("Male")) {
                            mCount++;
                        } else if (u.getGender().equals("Female")) {
                            fCount++;
                        }
                    }

                    if (mCount > fCount) {
                        gender = "Male";
                    }
                    else{
                        gender = "Female";
                    }
                }

                // print most common gender
                textViewMostCommonGender.setText(textViewMostCommonGender.getText()+" "+ gender);

                // calc common colors
                for (User u : colorblidUsers) {
                    ArrayList<String> badColors = u.getBadColors();
                    if(badColors!=null) {
                        for (String color : badColors) {
                            colors.put(color, colors.get(color) + 1);
                        }
                    }
                }

                int maxColor = Collections.max(colors.values());
                String badColors = "None";

                if (maxColor!=0){
                    badColors="";
                    for (Map.Entry<String, Integer> entry : colors.entrySet()) {
                        if ( entry.getValue() == maxColor){
                            badColors += ", " + entry.getKey();
                        }
                    }
                    badColors = badColors.substring(2);
                }

                // print bad colors
                textViewMostCommonColors.setText(textViewMostCommonColors.getText()+" "+ badColors);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
