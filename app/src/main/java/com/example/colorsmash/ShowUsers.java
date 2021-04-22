package com.example.colorsmash;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ShowUsers extends AppCompatActivity {

    private DatabaseReference mRef;

    private FirebaseDatabase mDataBase;
    private List<User> users =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);


        mDataBase= FirebaseDatabase.getInstance();
        mRef = mDataBase.getReference("Users");


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                showData(dataSnapshot);
                showUsers();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    //Initiates the table
    public void showUsers(){

        //This part defines the layout to be used for creating new rows
        TableLayout ll = (TableLayout) findViewById(R.id.UsersTable);
        TableRow row= new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        row.setBackgroundColor(Color.parseColor("#F1F1F1"));
        TextView tName = new TextView(this);
        TextView tUsername = new TextView(this);
        TextView tAge = new TextView(this);
        TextView tGender = new TextView(this);

        //This generates the caption row
        tName.setText("Name");
        tName.setPadding(3, 3, 3, 3);



        //test for coloring the table  -> disable the row coloring!!@!@#!!!
        /*
        int id=10;
        tName.setId(id);

        tName.setBackgroundResource(R.color.colorPrimaryDark);
        */

        tUsername.setText("Username");
        tUsername.setPadding(3, 3, 3, 3);
        tAge.setText("Age");
        tAge.setPadding(3, 3, 3, 3);
        tGender.setText("Gender");
        tGender.setPadding(3, 3, 3, 3);
        row.addView(tName);
        row.addView(tUsername);
        row.addView(tAge);
        row.addView(tGender);
        ll.addView(row,0);

        //This loop adds the database content to the table (using hardcoded values here for demonstration)
        for (int i = 0; i < users.size(); i++) {

            row= new TableRow(this);
            lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            tName = new TextView(this);
            tUsername = new TextView(this);
            tAge = new TextView(this);
            tGender = new TextView(this);
            tName.setText(users.get(i).getName());
            tName.setPadding(3, 3, 3, 3);
            tUsername.setText(users.get(i).getUsername());
            tUsername.setPadding(3, 3, 3, 3);
            tAge.setText(Integer.toString(users.get(i).getAge()));
            tAge.setPadding(3, 3, 3, 3);
            tGender.setText(users.get(i).getGender());
            tGender.setPadding(3, 3, 3, 3);

            row.addView(tName);
            row.addView(tUsername);
            row.addView(tAge);
            row.addView(tGender);
            row.setBackgroundColor(Color.parseColor("#FFFFFF"));
            ll.addView(row,i+1);

            //continue of the test coloring
            /*
            TextView tv = (TextView)findViewById(id);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)tv.getLayoutParams();
            params.setMargins(1, 1, 1, 1); //substitute parameters for left, top, right, bottom
            tv.setLayoutParams(params);
            */
        }
    }



    private void showData(DataSnapshot dataSnapshot) {
        List<String> keys=new ArrayList<>();
        users.clear();
        for(DataSnapshot ds:dataSnapshot.getChildren()){
            User user=ds.getValue(User.class);
            users.add(user);

        }
    }
}
