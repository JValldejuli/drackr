package com.fiveguys.cs2340.drackr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;

/**
 * Controls the add donation interface.
 */
public class AddDonationActivity extends AppCompatActivity {

    private EditText dateField;
    private EditText zipCodeField;
    private EditText descriptionField;
    private EditText amountField;
    private Spinner donationTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        dateField = findViewById(R.id.dateField);
        zipCodeField = findViewById(R.id.zipCodeField);
        descriptionField = findViewById(R.id.descriptionField);
        amountField = findViewById(R.id.amountField);
        donationTypeSpinner = findViewById(R.id.donationTypeSpinner);
        Button doneButton = findViewById(R.id.doneButton);

        // Setup spinner
        ArrayAdapter<DonationType> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                DonationType.values()
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        donationTypeSpinner.setAdapter(adapter);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doneTapped();
            }
        });

    }

    private void doneTapped() {

        String dateString = dateField.getText().toString();
        if (dateString.isEmpty()) {
            return;
        }
        Date date = new Date(dateString);

        String zipCode = zipCodeField.getText().toString();
        if (zipCode.isEmpty()) {
            return;
        }

        String description = descriptionField.getText().toString();
        if (description.isEmpty()) {
            return;
        }

        String amountString = amountField.getText().toString();
        if (amountString.isEmpty()) {
            return;
        }
        double amount = Double.parseDouble(amountString);

        DonationType donationType = (DonationType) donationTypeSpinner.getSelectedItem();

        Donation newDonation = new Donation(date, zipCode, description, amount, donationType);
        CharityDataProvider.shared.addDonationToSelectedCharity(newDonation);
        CharityDataProvider.shared.save();

        finish();

    }

}
