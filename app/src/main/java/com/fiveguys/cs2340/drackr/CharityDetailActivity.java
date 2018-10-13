package com.fiveguys.cs2340.drackr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CharityDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_detail);
        Charity charity = (Charity) getIntent().getExtras().get("charity");
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
                + charity.getType().getName()
                + "\n"
                + charity.getPhoneNumber()
                + "\n"
                + charity.getUrl().toString());
    }
}
