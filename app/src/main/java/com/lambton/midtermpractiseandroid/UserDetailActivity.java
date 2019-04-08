package com.lambton.midtermpractiseandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class UserDetailActivity extends AppCompatActivity {

    TextView userNameJSON;
    TextView userEmail;
    TextView userCompanyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        userNameJSON = findViewById(R.id.userNameJSON);
        userEmail = findViewById(R.id.emailJSON);
        userCompanyName = findViewById(R.id.companyNameJSON);

        if (getIntent().hasExtra("user_name") && getIntent().hasExtra("user_mail") && getIntent().hasExtra("user_company_name")) {
            String u = getIntent().getStringExtra("user_name");
            String m = getIntent().getStringExtra("user_mail");
            String c = getIntent().getStringExtra("user_company_name");
            userNameJSON.setText(u);
            userEmail.setText(m);
            userCompanyName.setText(c);
            Log.d("data", u);
        } else {
            Log.d("this", "aa");
        }
    }
}
