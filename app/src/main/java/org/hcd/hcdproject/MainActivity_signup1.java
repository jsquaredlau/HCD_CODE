package org.hcd.hcdproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity_signup1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_signup1);

    }

    public void onButtonGotoAddInfoClicked(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity_signup2.class);
        startActivity(intent);
    }

    public void onButtonRegisterClicked(View v) {
        Intent intent = new Intent(MainActivity_signup1.this, Pop.class);
        startActivity(intent);
    }

}
