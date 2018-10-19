package com.fiveguys.cs2340.drackr;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class DonationsActivity extends AppCompatActivity implements ListSelectionDelegate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.donations_buttons, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.addDonationButton) {
            Intent intent = new Intent(DonationsActivity.this, AddDonationActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void didSelect(Object item) {
        Donation donation = (Donation) item;
        Intent intent = new Intent(DonationsActivity.this, DonationDetailActivity.class);
        intent.putExtra("donation", donation);
        startActivity(intent);
    }

}
