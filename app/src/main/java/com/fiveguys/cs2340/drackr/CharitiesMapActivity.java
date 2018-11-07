package com.fiveguys.cs2340.drackr;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Controls the map of charities interface.
 */
public class CharitiesMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final float INITIAL_ZOOM_FACTOR = 12.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charities_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Objects.requireNonNull(mapFragment).getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast toast = Toast.makeText(
                        getApplicationContext(),
                        marker.getTitle(),
                        Toast.LENGTH_SHORT
                );
                toast.setGravity(
                        Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,
                        0,
                        0
                );
                toast.show();
                return true;
            }
        });

        List<Charity> charities = CharityDataProvider.getCharities();

        List<MarkerOptions> charitiesMarkers = new ArrayList<>();

        for (Charity charity : charities) {
            MarkerOptions markerOptions = new MarkerOptions();

            MarkerOptions marker = markerOptions.position(
                    charity.getCoordinates()
            ).title(
                    charity.getMapMarkerDescription()
            ).icon(
                    BitmapDescriptorFactory.defaultMarker(
                            BitmapDescriptorFactory.HUE_RED
                    )
            );

            charitiesMarkers.add(marker);
        }

        for (MarkerOptions marker : charitiesMarkers) {
            googleMap.addMarker(marker);
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                charitiesMarkers.get(0).getPosition(),
                INITIAL_ZOOM_FACTOR)
        );

    }

}
