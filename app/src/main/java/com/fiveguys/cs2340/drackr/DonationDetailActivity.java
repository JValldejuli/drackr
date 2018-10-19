package com.fiveguys.cs2340.drackr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DonationDetailActivity extends AppCompatActivity {

    private Donation donation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_detail);
        donation = (Donation) getIntent().getExtras().get("donation");

        TextView text = findViewById(R.id.textView);
        text.setText(donation.getDate()
                + "\n"
                + donation.getZipCode()
                + "\n"
                + donation.getDescription()
                + "\n"
                + donation.getShortDescription()
                + "\n"
                + "$" + donation.getAmount()
                + "\n"
                + donation.getType());

    }
}
