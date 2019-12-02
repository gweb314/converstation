package edu.stanford.converstationv0;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.location.Location;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.TravelMode;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsActivity extends AppCompatActivity implements OnMyLocationButtonClickListener,
        // OnMyLocationClickListener,
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private GoogleMap mMap;
    private String TAG = "so47492459";
    private boolean mLocationPermissionGranted;
    private boolean isLargeLayout = false;
    private Location mUserLocation;
    private String targetLocation;

    private BottomNavigationView navBar;
    private LinearLayout mapContainer;
    SupportMapFragment mapFragment;
    private TextView startNavText;

    private Polyline mPolyline;
    private ActionBar headBar;

    private Map<String, Map<String, Double>> converStationLatLngs;
    private List<Location> converStationLocations;
    private List<Marker> converStationMarkers;
    private Map<String, BitmapDrawable> locationDrawables;
    private Map<String, StepSet> stepSets;
    private FusedLocationProviderClient fusedLocationClient;

    private double OVALLAT = 37.429859;
    private double OVALLNG = -122.169474;
    private double MEMCHULAT = 37.427010;
    private double MEMCHULNG = -122.170719;
    private double DSCHOOLLAT = 37.426339;
    private double DSCHOOLLNG = -122.171968;
    private double ROBLELAT = 37.426191;
    private double ROBLELNG = -122.174807;
    private double TRESULAT = 37.424532;
    private double TRESULNG = -122.170682;
    private double CLOCKLAT = 37.425943;
    private double CLOCKLNG = -122.168703;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        navBar = findViewById(R.id.navigationBar);
        mapContainer = findViewById(R.id.mapContainer);
        startNavText = findViewById(R.id.textView);

        locationDrawables = new HashMap<String, BitmapDrawable>() {{
            put("The Oval", (BitmapDrawable)getResources().getDrawable(R.drawable.stanford_oval));
            put("Garden by MemChu", (BitmapDrawable)getResources().getDrawable(R.drawable.memorial_church_garden));
            put("Roble Arts Gym Courtyard", (BitmapDrawable)getResources().getDrawable(R.drawable.roble_court));
            put("Tresidder Union", (BitmapDrawable)getResources().getDrawable(R.drawable.tresidder_union));
            put("The Clocktower", (BitmapDrawable)getResources().getDrawable(R.drawable.clock_tower));
            put("Stanford D.School", (BitmapDrawable)getResources().getDrawable(R.drawable.stanford_d_school));
        }};

        converStationLatLngs = new HashMap<String, Map<String, Double>>() {{
            put("The Oval", new HashMap<String, Double>(){{put("Latitude", OVALLAT); put("Longitude", OVALLNG);}});
            put("Garden by MemChu", new HashMap<String, Double>(){{put("Latitude", MEMCHULAT); put("Longitude", MEMCHULNG);}});
            put("Roble Arts Gym Courtyard", new HashMap<String, Double>(){{put("Latitude", ROBLELAT); put("Longitude", ROBLELNG);}});
            put("Tresidder Union", new HashMap<String, Double>(){{put("Latitude", TRESULAT); put("Longitude", TRESULNG);}});
            put("The Clocktower", new HashMap<String, Double>(){{put("Latitude", CLOCKLAT); put("Longitude", CLOCKLNG);}});
            put("Stanford D.School", new HashMap<String, Double>(){{put("Latitude", DSCHOOLLAT); put("Longitude", DSCHOOLLNG);}});
        }};

        Context thisContext = this;

        stepSets = new HashMap<String, StepSet>() {{
            put("The Oval", new StepSet(
                    "The Oval",
                    "6 min",
                    "0.3 mile"));
            put("Garden by MemChu", new StepSet(
                    "Garden by MemChu",
                    "2 min",
                    "0.1 mile"));
            put("Roble Arts Gym Courtyard", new StepSet(
                    "Roble Arts Gym Courtyard",
                    "4 min",
                    "0.2 mile"));
            put("Tresidder Union", new StepSet(
                    "Tresidder Union",
                    "4 min",
                    "0.2 mile"));
            put("The Clocktower", new StepSet(
                    "The Clocktower",
                    "3 min",
                    "0.2 mile"));
        }};

        converStationLocations = new ArrayList<Location>();
        converStationMarkers = new ArrayList<Marker>();

        for (String locationName : converStationLatLngs.keySet()) {
            Location currentLocation = new Location(locationName);
            Map<String, Double> locationLatLng = converStationLatLngs.get(locationName);
            currentLocation.setLatitude(locationLatLng.get("Latitude"));
            currentLocation.setLongitude(locationLatLng.get("Longitude"));
            converStationLocations.add(new Location(locationName));
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        headBar = getSupportActionBar();
        headBar.setTitle("ConverStation Map");

        navBar.setVisibility(View.INVISIBLE);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f
        );
        mapContainer.setLayoutParams(param);
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
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        // LatLng sydney = new LatLng(-34, 151);
        // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        for (String locationName : converStationLatLngs.keySet()) {
            Map<String, Double> locationLatLng = converStationLatLngs.get(locationName);
            LatLng converStationLatLng = new LatLng(locationLatLng.get("Latitude"), locationLatLng.get("Longitude"));

            BitmapDrawable bitmapdraw=locationDrawables.get(locationName);
            Bitmap b = bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, 342, 342, false);


            Marker currentMarker = mMap.addMarker(new MarkerOptions().position(converStationLatLng).title(locationName).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
            converStationMarkers.add(currentMarker);
        }

        getLocationPermission();
        mMap.setMyLocationEnabled(true);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMyLocationButtonClickListener(this);
        // mMap.setOnMyLocationClickListener(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            mUserLocation = location;
                            LatLng userLatLng = new LatLng(mUserLocation.getLatitude(), mUserLocation.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(37.4280, -122.17180)));
                            mMap.getUiSettings().setZoomControlsEnabled(true);
                            mMap.setMinZoomPreference(16.0f);
                        } else {
                            Log.d("Debugging Location", "I see problems.");
                        }
                    }
                });
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Log.d("Debugging Location", "This function was entered");
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        headBar.setTitle(marker.getTitle());
        targetLocation = marker.getTitle();

        navBar.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                0.87f
        );
        mapContainer.setLayoutParams(param);

        if (mPolyline != null) {
            //mPolyline.remove();
            // TODO: MAKE LIST DIRECTIONS POP UP HERE.  IT SHOULD REALLY POP UP FROM BOTTOM BAR INTERACTION: "START NAVIGATION"
        }
        for (Marker currentMarker : converStationMarkers) {
            if (currentMarker != marker) {
                currentMarker.setVisible(false);
            }
        }
        marker.setVisible(true);
        LatLng markerLatLng = marker.getPosition();
        double destinationLat = markerLatLng.latitude;
        double destinationLng = markerLatLng.longitude;
        List<LatLng> path = new ArrayList();
        path.add(new LatLng(mUserLocation.getLatitude(), mUserLocation.getLongitude()));
        String userLatLngString = mUserLocation.getLatitude() + "," + mUserLocation.getLongitude();
        String destinationLatLngString = destinationLat + "," + destinationLng;


        //Execute Directions API request
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyB6bo3SyqOIrgdL7WZgx9Bwc3_bk-9tNN4")
                .build();
        DirectionsApiRequest req = DirectionsApi.getDirections(context, userLatLngString, destinationLatLngString).mode(TravelMode.WALKING);
        try {
            DirectionsResult res = req.await();

            //Loop through legs and steps to get encoded polylines of each step
            if (res.routes != null && res.routes.length > 0) {
                DirectionsRoute route = res.routes[0];

                if (route.legs !=null) {
                    for(int i=0; i<route.legs.length; i++) {
                        DirectionsLeg leg = route.legs[i];
                        if (leg.steps != null) {
                            for (int j=0; j<leg.steps.length;j++){
                                DirectionsStep step = leg.steps[j];
                                if (step.steps != null && step.steps.length >0) {
                                    for (int k=0; k<step.steps.length;k++){
                                        DirectionsStep step1 = step.steps[k];
                                        EncodedPolyline points1 = step1.polyline;
                                        if (points1 != null) {
                                            //Decode polyline and add points to list of route coordinates
                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                            }
                                        }
                                    }
                                } else {
                                    EncodedPolyline points = step.polyline;
                                    if (points != null) {
                                        //Decode polyline and add points to list of route coordinates
                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                        for (com.google.maps.model.LatLng coord : coords) {
                                            path.add(new LatLng(coord.lat, coord.lng));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch(Exception ex) {
            Log.e(TAG, ex.getLocalizedMessage());
        }

        path.add(markerLatLng);

        //Draw the polyline
        if (path.size() > 2) {
            PolylineOptions opts = new PolylineOptions().addAll(path).color(this.getResources().getColor(R.color.colorAccent)).width(15);
            mPolyline = mMap.addPolyline(opts);
        }
        return true;
    }

    public void startNavigation(View v) {
        Intent myIntent = new Intent(MapsActivity.this, NavigationSteps.class);
        myIntent.putExtra("StepSet", stepSets.get(targetLocation));
        MapsActivity.this.startActivity(myIntent);
    }

}

