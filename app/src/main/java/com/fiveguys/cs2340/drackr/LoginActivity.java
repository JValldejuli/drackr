package com.fiveguys.cs2340.drackr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

public class LoginActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;
    private Button loginButton;
    private TextView registerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        UserAuthenticator.initializeWith(preferences);

        setContentView(R.layout.activity_login);

        InputStream inputStream = getResources().openRawResource(R.raw.locationdata);
        CharityDataProvider.setup(inputStream);

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
            @Override
            public void onClick(View v) {
                loginTapped();
            }
        });

        // Register text view
        registerTextView = (TextView) findViewById(R.id.registerTextView);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        refreshLoginEnabled();

    }

    private void loginTapped() {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        Boolean signedIn = UserAuthenticator.signInWith(email, password);
        if (signedIn) {
            Intent intent = new Intent(this, CharitiesActivity.class);
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
