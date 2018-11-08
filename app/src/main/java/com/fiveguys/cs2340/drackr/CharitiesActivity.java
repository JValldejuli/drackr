package com.fiveguys.cs2340.drackr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Controls the charities list interface.
 */
public class CharitiesActivity extends AppCompatActivity implements ListSelectionDelegate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charities);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_buttons, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mybutton:
                Intent intent
                        = new Intent(CharitiesActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.searchButton:
                DonationSearchCoordinator.shared.setSpecificCharity(null);
                Intent searchIntent
                        = new Intent(CharitiesActivity.this, SearchActivity.class);
                startActivity(searchIntent);
                break;
            case R.id.mapButton:
                DonationSearchCoordinator.shared.setSpecificCharity(null);
                Intent charitiesMapIntent = new Intent(
                        CharitiesActivity.this,
                        CharitiesMapActivity.class
                );
                startActivity(charitiesMapIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void didSelect(Object item) {
        Charity charity = (Charity) item;
        Intent intent = new Intent(
                CharitiesActivity.this,
                CharityDetailActivity.class
        );
        CharityDataProvider.shared.setSelectedCharity(charity);
        startActivity(intent);
    }

}
