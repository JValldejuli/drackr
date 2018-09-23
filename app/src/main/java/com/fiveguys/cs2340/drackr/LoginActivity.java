package com.fiveguys.cs2340.drackr;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        // Email field
        emailField = (EditText) findViewById(R.id.emailField);
        emailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                refreshLoginEnabled();
            }
        });

        // Password field
        passwordField = (EditText) findViewById(R.id.passwordField);
        passwordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                refreshLoginEnabled();
            }
        });
        passwordField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!loginButton.isEnabled()) {
                    return;
                }
                loginButton.performClick();
            }
        });

        // Login button
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loginTapped();
            }
        });

        refreshLoginEnabled();

    }

    private void loginTapped() {
        boolean emailIsValid = emailField.getText().toString().equals("user");
        boolean passwordIsValid = passwordField.getText().toString().equals("pass");
        if (emailIsValid && passwordIsValid) {
            Intent intent = new Intent(this, DonationsActivity.class);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Invalid credentials.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }
    }

    private void refreshLoginEnabled() {
        loginButton.setEnabled(fieldsAreFilled());
    }

    private boolean fieldsAreFilled() {
        boolean emailIsFilled = !emailField.getText().toString().isEmpty();
        boolean passwordIsFilled = !passwordField.getText().toString().isEmpty();
        return emailIsFilled && passwordIsFilled;
    }

}
