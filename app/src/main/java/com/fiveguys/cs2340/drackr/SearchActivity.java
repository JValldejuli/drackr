package com.fiveguys.cs2340.drackr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Controls the interface to search for a donation.
 */
public class SearchActivity extends AppCompatActivity {

    private Spinner donationTypeSpinner;
    private EditText descriptionField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        donationTypeSpinner = findViewById(R.id.typeSpinner);
        Button searchTypeButton = findViewById(R.id.searchTypeButton);
        descriptionField = findViewById(R.id.descriptionField);
        Button searchDescriptionButton = findViewById(R.id.searchDescriptionButton);

        // Setup spinner
        ArrayAdapter<DonationType> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                DonationType.values()
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        donationTypeSpinner.setAdapter(adapter);

        searchTypeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonationType donationType = (DonationType) donationTypeSpinner.getSelectedItem();
                DonationSearchCoordinator.searchDonationsByType(donationType);
                Intent searchResultsIntent = new Intent(
                        SearchActivity.this,
                        SearchResultsActivity.class
                );
                startActivity(searchResultsIntent);
            }
        });

        searchDescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = descriptionField.getText().toString();
                DonationSearchCoordinator.searchDonationsByDescription(description);
                Intent searchResultsIntent = new Intent(
                        SearchActivity.this,
                        SearchResultsActivity.class
                );
                startActivity(searchResultsIntent);
            }
        });

    }

}
