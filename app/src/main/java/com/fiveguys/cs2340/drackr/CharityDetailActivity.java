package com.fiveguys.cs2340.drackr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CharityDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_detail);
        Charity charity = CharityDataProvider.selectedCharity;

        TextView text = findViewById(R.id.textView);
        text.setText(charity.getKey()
                + "\n"
                + charity.getName()
                + "\n"
                + charity.getLatitude()
                + "\n"
                + charity.getLongitude()
                + "\n"
                + charity.getStreetAddress()
                + "\n"
                + charity.getCity()
                + "\n"
                + charity.getState()
                + "\n"
                + charity.getZip()
                + "\n"
                + charity.getType().toString()
                + "\n"
                + charity.getPhoneNumber()
                + "\n"
                + charity.getUrl().toString());

        Button donationsButton = (Button) findViewById(R.id.donationsButton);
        donationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CharityDetailActivity.this, DonationsActivity.class);
                startActivity(intent);
            }
        });

    }
}
