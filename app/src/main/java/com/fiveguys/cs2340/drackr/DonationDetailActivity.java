package com.fiveguys.cs2340.drackr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

/**
 * Controlls the DonationDetail interface.
 */
public class DonationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_detail);
        Donation donation
                = (Donation) Objects.requireNonNull(getIntent().getExtras()).get("donation");

        TextView text = findViewById(R.id.textView);
        text.setText(donation.getFullDescription());

    }
}
