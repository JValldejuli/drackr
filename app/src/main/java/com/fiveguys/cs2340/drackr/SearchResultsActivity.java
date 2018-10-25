package com.fiveguys.cs2340.drackr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SearchResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
    }

    public void didSelect(Object item) {
        Donation donation = (Donation) item;
        Intent intent = new Intent(SearchResultsActivity.this, DonationDetailActivity.class);
        intent.putExtra("donation", donation);
        startActivity(intent);
    }

}
