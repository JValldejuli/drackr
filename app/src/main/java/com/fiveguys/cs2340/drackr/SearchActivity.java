package com.fiveguys.cs2340.drackr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SearchActivity extends AppCompatActivity {

    Spinner donationTypeSpinner;
    Button searchTypeButton;
    EditText descriptionField;
    Button searchDescriptionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        donationTypeSpinner = (Spinner) findViewById(R.id.donationTypeSpinner);
        searchTypeButton = (Button) findViewById(R.id.searchTypeButton);
        descriptionField = (EditText) findViewById(R.id.descriptionField);
        searchDescriptionButton = (Button) findViewById(R.id.searchDescriptionButton);

        // Setup spinner
        ArrayAdapter<DonationType> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, DonationType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        donationTypeSpinner.setAdapter(adapter);

        searchTypeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonationType donationType = (DonationType) donationTypeSpinner.getSelectedItem();
                DonationSearchCoordinator.searchDonationsByType(donationType);
            }
        });

        searchDescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = descriptionField.getText().toString();
                DonationSearchCoordinator.searchDonationsByDescription(description);
            }
        });

    }

}
