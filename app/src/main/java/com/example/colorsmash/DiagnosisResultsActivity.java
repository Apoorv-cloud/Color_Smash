package com.example.colorsmash;

import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class DiagnosisResultsActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference mRef;
    private TextView name, result, resultsDetails, cover;
    private Button fullTest, finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis_results);

        name = (TextView) findViewById(R.id.textViewNameDiagnosisResults);
        result = (TextView) findViewById(R.id.textViewResultsDiagnosisResults);
        resultsDetails = (TextView) findViewById(R.id.textViewResultsDetailsDiagnosisResults);
        cover = (TextView) findViewById(R.id.textViewCoverDiagnosisResults);
        fullTest = (Button) findViewById(R.id.buttonFullTestDiagnosisResults);
        finish = (Button) findViewById(R.id.buttonFinishDiagnosisResults);

        fullTest.setOnClickListener(this);
        finish.setOnClickListener(this);

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

                User user = (User) dataSnapshot.getValue(User.class);
                String s = "Hello  ";
                s = s + user.getName();
                name.setText(s);

                result.setText(DiagnosisResult(user.getBadColors()));//NEED FIXXX
                resultsDetails.setText(DiagnosisResultsDetails(user.getBadColors()));

                cover.setText("The color vision test is not a substitute for a complete eye exam." +
                              "and, the test is fast and may be inaccurate in its test results" +
                              "and therefore we recommend doing the complete diagnosis.");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    @Override
    public void onClick(View view) {
        if (view == fullTest) {
            String url = "https://enchroma.com/pages/test";

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } else if (view == finish) {
            this.finish();
            Intent act = new Intent(DiagnosisResultsActivity.this, UserOptionsActivity.class);
            startActivity(act);
        }
    }

    String DiagnosisResult(ArrayList<String> type) {
        String msg = "";

        if (!type.isEmpty()) {

            if (type.contains("PROTAN")) {
                msg = msg + "Protan";
            }
            if (type.contains("DEUTAN")) {
                if (!msg.equals("")) {
                    msg = msg + " OR ";
                }
                msg = msg + "Deutan";
            }
            if (type.contains("TRITAN")) {
                if (!msg.equals("")) {
                    msg = msg + " OR ";
                }
                msg = msg + "Tritan";

            }
            msg=msg+"\n";
        }
        return msg;
    }

    String DiagnosisResultsDetails(ArrayList<String> type) {
        String msg = "";
        if (type != null) {
            if (type.contains("PROTAN")) {
                msg = msg + "You may have Protans color blindness,\n" +
                        " people with Protanomaly, have a type of red-green color blindness in which the red cones are not" +
                        "absent but do not detect enough red and are too sensitive to greens, yellows, and oranges." +
                        "As a result, greens, yellows, oranges, reds, and browns may appear similar, especially in low light." +
                        "Red and black might be hard to tell apart, especially when red text is against a black background.\n\n";
            }
            if (type.contains("DEUTAN")) {
                if (!msg.equals("")) {
                    msg = msg + "\nOR\n";
                }
                msg = msg + "You may have Deutans color blindness,\n" +
                        "people with Deuteranomaly, have a type of red-green color blindness in which green cones are not" +
                        "absent but do not detect enough green and are too sensitive to yellows, oranges, and reds." +
                        "As a result, greens, yellows, oranges, reds, and browns may appear similar, especially in low light. " +
                        " It can also be difficult to tell the difference between blues and purples, or pinks and grays.\n\n";
            }
            if (type.contains("TRITAN")) {
                if (!msg.equals("")) {
                    msg = msg + "\nOR\n";
                }
                msg = msg + "You may have Tritan color blindness,\n" +
                        "causing reduced blue sensitivity and Tritanopia, resulting in no blue sensitivity, " +
                        "can be inherited or acquired; the inherited form is a rare autosomal recessive condition." +
                        "More commonly, tritanomaly is acquired later in life due to age-related or environmental factors. " +
                        "Cataracts, glaucoma and age related macular degeneration could cause someone to test as a Tritan." +
                        "People with tritanomaly have reduced sensitivity in their blue “S” cone cells," +
                        " which can cause confusion between blue versus green and red from purple.\n\n";
            }
        }
        return msg;
    }


}
