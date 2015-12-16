package org.hcd.hcdproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;

public class MainActivity_signup1 extends AppCompatActivity {


    private Firebase myFirebaseRef;
    private EditText et;
    private TextView name;
    private TextView gender;
    private TextView disabilityGrade;
    private TextView disabilityType;
    private TextView region;
    private TextView age;
    private Button moreInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_signup1);
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://hcd-firebase.firebaseio.com/");
        initView();
    }

    public void onButtonGotoAddInfoClicked(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity_signup2.class);
        intent.putExtra("VOUCHER_ID", et.getText().toString());
        startActivity(intent);
    }

    public void onButtonRegisterClicked(View v) {
        Intent intent = new Intent(MainActivity_signup1.this, Pop.class);
        startActivity(intent);
    }

    public void onButtonEnterClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity_signup1.class);
        intent.putExtra("VOUCHER_IDr", et.getText().toString());
        startActivity(intent);
    }

    private void initView(){
        final String voucher_number = getIntent().getStringExtra("VOUCHER_ID");
        et = (EditText) findViewById(R.id.editText_voucherID);
        age = (TextView) findViewById(R.id.age);
        name = (TextView) findViewById(R.id.name);
        gender = (TextView) findViewById(R.id.gender);
        disabilityGrade = (TextView) findViewById(R.id.disabilityGrade);
        disabilityType = (TextView) findViewById(R.id.disabilityType);
        region = (TextView) findViewById(R.id.region);
        moreInfo = (Button) findViewById(R.id.buttonGotoAddInfo);

        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_signup1.this, MainActivity_signup2.class);
                intent.putExtra("VOUCHER_ID", voucher_number);
                startActivity(intent);
            }
        });

        et.setText(voucher_number);

        myFirebaseRef.child("Profiles").child(voucher_number).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    HashMap hash = (HashMap) dataSnapshot.getValue();
                    age.setText((String) hash.get("Age"));
                    name.setText((CharSequence) hash.get("Name"));
                    if (((String) hash.get("Gender")).matches("F")) {
                        gender.setText("여");
                    } else {
                        gender.setText("남");
                    }
                    disabilityType.setText((String) hash.get("Dis_Type"));
                    disabilityGrade.setText((String) hash.get("Dis_Degree"));
                    region.setText((String) hash.get("Area"));
                } else {
                    // DO NOTHING
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

}
