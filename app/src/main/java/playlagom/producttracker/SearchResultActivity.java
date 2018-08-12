package playlagom.producttracker;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;

import playlagom.producttracker.libs.Init;

public class SearchResultActivity  extends FragmentActivity implements
        OnMapReadyCallback,
        Communicator,
        GoogleMap.OnCameraMoveListener {

    private GoogleMap mMap;
    LocationManager locationManager;
    private int minTime = 1000;
    private float minDistance = 1;
    private int counter = 1;

    double latitude = 0;
    double longitude = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_search_result);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapSearchResult);
        mapFragment.getMapAsync(this);

        Toast.makeText(getApplicationContext(),
                "Result displaying through Robot | Deep learning (UNDER CONSTRUCTION)", Toast.LENGTH_LONG).show();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        // check if location provider is enable or not
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    LatLng latLng = new LatLng(latitude, longitude);
                    // Geocoder converts latLng into meaningful address name.
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        // now, we can get country name, locality name, street name,...etc
                        // show location name
                        String str = "phone: " + addressList.get(0).getPhone() + ", ";
                        str += addressList.get(0).getLocality() + ",";
                        str += addressList.get(0).getAddressLine(0) + "";
                        // Init.runMap(SearchResultActivity.this, mMap, latLng, str);

                        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).position(latLng).title(counter++ + "m" ).snippet("Aftabnagar")).showInfoWindow();
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, lastZoomValue));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    LatLng latLng = new LatLng(latitude, longitude);
                    // Geocoder converts latLng into meaningful address name.
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        // now, we can get country name, locality name, street name,...etc
                        // show location name
                        String str = addressList.get(0).getLocality() + ",";
                        str += addressList.get(0).getCountryName();
//                        Init.runMap(SearchResultActivity.this, mMap, latLng, str);
                        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).position(latLng).title(str));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, lastZoomValue));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
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
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.setOnCameraMoveListener(this);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18));


        String title = "Time";
//        String subTitle = "Aftabnagar \nlocation";
        String subTitle = "Aftabnagar";

        //Marker
        MarkerOptions markerOpt = new MarkerOptions();
        markerOpt.position(new LatLng(Double.valueOf(latitude), Double.valueOf(longitude)))
                .title(title)
                .snippet(subTitle);

        //Set Custom InfoWindow Adapter
        CustomInfoWindowAdapterUber adapter = new CustomInfoWindowAdapterUber(SearchResultActivity.this);
        mMap.setInfoWindowAdapter(adapter);

        mMap.addMarker(markerOpt).showInfoWindow();
    }

    private float lastZoomValue = 0;
    @Override
    public void onCameraMove() {
        lastZoomValue = mMap.getCameraPosition().zoom;
    }
}
