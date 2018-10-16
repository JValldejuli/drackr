package com.fiveguys.cs2340.drackr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.fiveguys.cs2340.drackr.dummy.DummyContent;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DonationsActivity extends AppCompatActivity implements CharityFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_buttons, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            Intent intent = new Intent(DonationsActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Intent intent = new Intent(DonationsActivity.this, CharityDetailActivity.class);
        intent.putExtra("charity", item.charity);
        startActivity(intent);
    }

}
