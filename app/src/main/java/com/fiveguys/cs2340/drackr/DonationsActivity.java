package com.fiveguys.cs2340.drackr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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



//        Button logoutButton = (Button) findViewById(R.id.logoutButton);
//        logoutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(DonationsActivity.this, LoginActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Intent intent = new Intent(DonationsActivity.this, CharityDetailActivity.class);
        intent.putExtra("charity", item.charity);
        startActivity(intent);
    }

}
