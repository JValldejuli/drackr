package com.fiveguys.cs2340.drackr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailField;
    private EditText passwordField;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                String loginMessage = String.format("Username: %s Password: %s", emailField.getText().toString(), passwordField.getText().toString());
                Log.i("User login", loginMessage);
                break;
            default:
                Log.d("Unknown view clicked.", "Unknown view clicked.");
        }
    }

}
