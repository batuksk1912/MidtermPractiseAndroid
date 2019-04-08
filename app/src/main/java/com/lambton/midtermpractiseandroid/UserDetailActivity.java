package com.lambton.midtermpractiseandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class UserDetailActivity extends AppCompatActivity {

    TextView userNameJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        userNameJSON = findViewById(R.id.userNameJSON);

        if (getIntent().hasExtra("user_name")) {
            String u = getIntent().getStringExtra("user_name");
            userNameJSON.setText(u);
            Log.d("data", u);
        } else {
            Log.d("this", "aa");
        }
    }
}
