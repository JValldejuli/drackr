package com.fiveguys.cs2340.drackr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Responsible for controlling the register interface.
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText emailField;
    private EditText passwordField;
    private Spinner userAccountTypeSpinner;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        // Name field
        nameField = findViewById(R.id.nameField);
        nameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                refreshRegisterEnabled();
            }
        });

        // Email field
        emailField = findViewById(R.id.emailField);
        emailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                refreshRegisterEnabled();
            }
        });

        // Password field
        passwordField = findViewById(R.id.passwordField);
        passwordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                refreshRegisterEnabled();
            }
        });

        // User type spinner
        userAccountTypeSpinner = findViewById(R.id.userTypeSpinner);
        ArrayAdapter<UserAccountType> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                UserAccountType.values()
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userAccountTypeSpinner.setAdapter(adapter);
        userAccountTypeSpinner.setSelection(0);

        // Register button
        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerTapped();
            }
        });

        // Register text view
        TextView loginTextView = findViewById(R.id.loginTextView);
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        RegisterActivity.this,
                        LoginActivity.class
                );
                startActivity(intent);
            }
        });

        refreshRegisterEnabled();

    }

    private void registerTapped() {
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        UserAccountType userAccountType
                = (UserAccountType) userAccountTypeSpinner.getSelectedItem();
        UserAuthenticator.shared.registerUserAccount(name, email, password, userAccountType);
        Intent intent = new Intent(this, CharitiesActivity.class);
        startActivity(intent);
    }

    private void refreshRegisterEnabled() {
        registerButton.setEnabled(fieldsAreFilled());
    }

    private boolean fieldsAreFilled() {
        boolean nameIsFilled = !nameField.getText().toString().isEmpty();
        boolean emailIsFilled = !emailField.getText().toString().isEmpty();
        boolean passwordIsFilled = !passwordField.getText().toString().isEmpty();
        return nameIsFilled && emailIsFilled && passwordIsFilled;
    }

}
