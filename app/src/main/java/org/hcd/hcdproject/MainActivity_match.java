package org.hcd.hcdproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity_match extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_match);
    }

    public void onButtonMoreInfoClicked (View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity_match1.class);
        startActivity(intent);
    }

}
