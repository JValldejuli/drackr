package com.fiveguys.cs2340.drackr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
                Intent intent = new Intent(CharitiesActivity.this, LoginActivity.class);
                startActivity(intent);
            case R.id.searchButton:
//                Intent searchIntent = new Intent(CharitiesActivity.this, LoginActivity.class);
//                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void didSelect(Object item) {
        Charity charity = (Charity) item;
        Intent intent = new Intent(CharitiesActivity.this, CharityDetailActivity.class);
        CharityDataProvider.selectedCharity = charity;
        startActivity(intent);
    }

}
