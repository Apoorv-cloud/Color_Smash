package com.example.colorsmash;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import android.design.widget.FloatingActionButton;

import com.google.android.material.snackbar.Snackbar;
//import android.support.design.widget.Snackbar;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyDiagnosisActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView TVname ;
    private TextView TVresults ;
    private TextView TVfilteredColors;
    private String name ;
    private Button FullTest ;
    private Button buttonResetMyColorBlindness;
    private DatabaseReference mRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diagnosis);

        TVname=(TextView)findViewById(R.id.textViewNameMyDiagnosis);
        TVresults=(TextView)findViewById(R.id.textViewTestResultsMyDiagnosis);
        TVfilteredColors=(TextView)findViewById(R.id.textViewFilteredColors);
        FullTest=(Button) findViewById(R.id.textViewFullTestMyDiagnosis);
        buttonResetMyColorBlindness = (Button)findViewById(R.id.ButtonResetMyColorblindness);
        buttonResetMyColorBlindness.setOnClickListener(this);
        FullTest.setOnClickListener(this);

        // get current user uID
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uID = "";
        if (user != null) {
            uID = user.getUid();
        } else {
            // No user is signed in ?? add Exception ??
        }

        mRef = FirebaseDatabase.getInstance().getReference("Users").child(uID);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User user = (User)dataSnapshot.getValue(User.class);
                name=  "Welcome : " ;
                name=name+user.getName();
                TVname.setText(name);

                TVresults.setText(DiagnosisResults(user.getBadColors()));

                TVfilteredColors.setText(filteredColors(user.getBadColors()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    @Override
    public void onClick(View view){
        if(view == FullTest){
            String url = "https://enchroma.com/pages/test";

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
        else if(view == buttonResetMyColorBlindness){
            mRef.child("badColors").removeValue();
        }



    }
    String DiagnosisResults(ArrayList<String> type){
        String msg ="";
        if (type != null) {
            if(type.contains("PROTAN")){
                msg=msg+"You may have Protans color blindness,\n" +
                        " people with Protanomaly, have a type of red-green color blindness in which the red cones are not" +
                        "absent but do not detect enough red and are too sensitive to greens, yellows, and oranges." +
                        "As a result, greens, yellows, oranges, reds, and browns may appear similar, especially in low light." +
                        "Red and black might be hard to tell apart, especially when red text is against a black background.\n\n";
            }
            if(type.contains("DEUTAN")){
                if(!msg.equals("")){msg=msg+"\nOR\n";}
                msg=msg+"You may have Deutans color blindness,\n" +
                        "people with Deuteranomaly, have a type of red-green color blindness in which green cones are not" +
                        "absent but do not detect enough green and are too sensitive to yellows, oranges, and reds." +
                        "As a result, greens, yellows, oranges, reds, and browns may appear similar, especially in low light. " +
                        " It can also be difficult to tell the difference between blues and purples, or pinks and grays.\n\n";
            }
            if(type.contains("TRITAN")){
                if(!msg.equals("")){msg=msg+"\nOR\n";}
                msg=msg+"You may have Tritan color blindness,\n" +
                        "causing reduced blue sensitivity and Tritanopia, resulting in no blue sensitivity, " +
                        "can be inherited or acquired; the inherited form is a rare autosomal recessive condition." +
                        "More commonly, tritanomaly is acquired later in life due to age-related or environmental factors. " +
                        "Cataracts, glaucoma and age related macular degeneration could cause someone to test as a Tritan." +
                        "People with tritanomaly have reduced sensitivity in their blue “S” cone cells," +
                        " which can cause confusion between blue versus green and red from purple.\n\n";
            }
        }

        else{
            msg="\nNo color blindness / not tested\n";
        }

        return msg;
    }

    String filteredColors(ArrayList<String> type){
        String msg ="";
        if (type != null) {
            msg += "We filtered you this colors from the game:\n";
            if(type.contains("PROTAN") || type.contains("DEUTAN") ){
                msg += "GREEN PINK PURPLE ";
            }
            if(type.contains("TRITAN")){
                if(!msg.contains("BLUE")){
                    msg += "BLUE ";
                }
                if(!msg.contains("PURPLE")){
                    msg += "PURPLE ";
                }
            }
        }


        return msg;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            this.finish();
            Intent act = new Intent(MyDiagnosisActivity.this, UserOptionsActivity.class);
            startActivity(act);
        }
        return super.onKeyDown(keyCode, event);
    }

}
