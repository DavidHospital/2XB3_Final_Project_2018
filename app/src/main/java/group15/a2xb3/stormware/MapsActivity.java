package group15.a2xb3.stormware;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Main activity class deals with the view and controller parts of the Model View Controller design
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    // GoogleMap object for the view
    private GoogleMap mMap;

    // Current marker, changed by using the search bar
    private Marker mMarker;

    // Current heatmap overlay, changed by selecting a new type
    private TileOverlay mOverlay;

    // Called when the activity is created (app startup)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Parse data into Data module
        NewParser.secondParser("c2017.csv", this);
    }

    // Adds a heatmap when one of the buttons is pressed
    public void addHeatMap(View view) {
        Button b = (Button) view;
        String type = b.getText().toString();

        // Remove previous heatmap
        if (mOverlay != null) {
            mOverlay.remove();
        }

        // Get a list of all events of a given type
        List<DisasterEvent> deList = Data.getList(type);

        // New list for only the location of those events
        List<LatLng> list = new ArrayList<>();
        for (DisasterEvent de : deList) {
            list.add(de.getLocation1());
        }

        // Create a new heatmap from the list of locations
        HeatmapTileProvider mProvider = new HeatmapTileProvider.Builder()
                .data(list)
                .build();

        // Add the heatmap to the GoogleMap object
        mOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));
    }

    /**
     * Implement search method for the search bar.
     */
    public void onMapSearch(View view) {

        // Get a location from the use via search bar
        EditText locationSearch = (EditText) findViewById(R.id.editText);
        String location = locationSearch.getText().toString();
        List<Address> addressList = null;

        // If the inputted location is valid
        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                // Get all locations returned from the search
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }

            // Grab the first location in the address list
            Address address = addressList.get(0);

            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

            // Remove the previous marker if there is one
            if (mMarker != null) {
                mMarker.remove();
            }

            // Add a new marker to the map at the given location and add it to the GoogleMap object
            mMarker = mMap.addMarker(new MarkerOptions().position(latLng).title(address.getAddressLine(0)));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }
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

        // Create the map
        mMap = googleMap;

        // Start it off focused in USA
        String location = "USA";
        Geocoder geocoder = new Geocoder(this);
        try {
            Address address = geocoder.getFromLocationName(location, 1).get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
