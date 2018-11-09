package com.fiveguys.cs2340.drackr;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Responsible for controlling the Donations list interface.
 */
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
            if (UserAuthenticator.shared.getSignedInUserAccount().getType()
                    == UserAccountType.USER) {
                return super.onOptionsItemSelected(item);
            }
            Intent intent = new Intent(DonationsActivity.this, AddDonationActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void didSelect(Object item) {
        Parcelable donation = (Donation) item;
        Intent intent = new Intent(DonationsActivity.this, DonationDetailActivity.class);
        intent.putExtra("donation", donation);
        startActivity(intent);
    }

}
