package com.fiveguys.cs2340.drackr;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Controls the interface to view the donation search results.
 */
public class SearchResultsActivity extends AppCompatActivity implements ListSelectionDelegate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
    }

    @Override
    public void didSelect(Object item) {
        Parcelable donation = (Donation) item;
        Intent intent = new Intent(SearchResultsActivity.this, DonationDetailActivity.class);
        intent.putExtra("donation", donation);
        startActivity(intent);
    }

}
