package com.fiveguys.cs2340.drackr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Responsible for controlling the charity detail interface.
 */
public class CharityDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_detail);
        Charity charity = CharityDataProvider.shared.getSelectedCharity();

        TextView text = findViewById(R.id.textView);
        CharSequence charityFullDescription = charity.getFullDescription();
        text.setText(charityFullDescription);

        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonationSearchCoordinator.shared.setSpecificCharity(
                        CharityDataProvider.shared.getSelectedCharity()
                );
                Intent searchIntent = new Intent(CharityDetailActivity.this, SearchActivity.class);
                startActivity(searchIntent);
            }
        });

        Button donationsButton = findViewById(R.id.donationsButton);
        donationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CharityDetailActivity.this, DonationsActivity.class);
                startActivity(intent);
            }
        });

    }
}
