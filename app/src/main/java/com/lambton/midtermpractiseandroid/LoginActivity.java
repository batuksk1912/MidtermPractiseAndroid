package com.lambton.midtermpractiseandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button login;
    CheckBox rememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final SharedPreferences preferences = getSharedPreferences("mypref", MODE_PRIVATE);

        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        login = findViewById(R.id.btnLogin);
        rememberMe = findViewById(R.id.checkBoxRemember);

        String user_email = preferences.getString("email", null);
        String user_pwd = preferences.getString("password", null);

        if (user_email != null && user_pwd != null) {
            email.setText(user_email);
            password.setText(user_pwd);
            rememberMe.setChecked(true);
        } else {
            rememberMe.setChecked(false);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(email.getText()) || email.getText().toString().length() == 0) {
                    email.setError("Please enter an e-mail");
                } else {
                    if (email.getText().toString().equals("test@test.com") && password.getText().toString().equals("123")) {
                        Toast.makeText(LoginActivity.this, "User Successfully logged in ", Toast.LENGTH_LONG).show();

                        SharedPreferences.Editor editor = preferences.edit();
                        if (rememberMe.isChecked()) {
                            editor.putString("email", email.getText().toString());
                            editor.putString("password", password.getText().toString());
                        } else {
                            editor.remove("email");
                            editor.remove("password");
                        }
                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "E-mail/password invalid. Please try again!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
