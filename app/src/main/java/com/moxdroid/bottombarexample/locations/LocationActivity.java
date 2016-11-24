package com.moxdroid.bottombarexample.locations;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.moxdroid.bottombarexample.R;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class LocationActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener
{
    private static String TAG = LocationActivity.class.getSimpleName();
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    TextView mLatitudeText, mLongitudeText;
    LocationRequest mLocationRequest;
    int REQUEST_CODE_RECOVER_PLAY_SERVICES=200;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        mLatitudeText = (TextView) findViewById(R.id.txtLatitudeText);
        mLongitudeText = (TextView) findViewById(R.id.txtLongitudeText);

        if(checkGooglePlayServices())
        {
            createLocationRequest();
            buildGoogleApiClient();
        }

    }

    protected synchronized void buildGoogleApiClient() {
        if (mGoogleApiClient == null)
        {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    protected void onStart()
    {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop()
    {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle)
    {
        Log.d(TAG, "onConnected: 1");
        if(checkPermission())
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, mLocationRequest, this);
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            Log.d(TAG, "onConnected: 2");
            if (mLastLocation != null)
            {
                Log.d(TAG, "onConnected: 3");
                mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
                mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
            }
        }

    }

    private boolean checkPermission()
    {
        Log.d(TAG, "checkPermission: 1");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            Log.d(TAG, "checkPermission: 2");
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            requestPermissions(new String[]{ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION}, 100);
        }else{
            //startLocationUpdates();
        }
        return true;
    }

    @Override
    public void onConnectionSuspended(int i)
    {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {

    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults){
        switch(permsRequestCode){
            case 100:
                //boolean cameraAccepted = grantResults[0]==PackageManager.PERMISSION_GRANTED;
                Toast.makeText(getApplicationContext(), "Permission  granted", Toast.LENGTH_LONG).show();
                break;
            default:
                super.onRequestPermissionsResult(permsRequestCode, permissions, grantResults);
        }
    }

    private boolean checkGooglePlayServices(){
        int checkGooglePlayServices = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (checkGooglePlayServices != ConnectionResult.SUCCESS) {
		/*
		* Google Play Services is missing or update is required
		*  return code could be
		* SUCCESS,
		* SERVICE_MISSING, SERVICE_VERSION_UPDATE_REQUIRED,
		* SERVICE_DISABLED, SERVICE_INVALID.
		*/
            GooglePlayServicesUtil.getErrorDialog(checkGooglePlayServices,
                    this, REQUEST_CODE_RECOVER_PLAY_SERVICES).show();

            return false;
        }

        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_RECOVER_PLAY_SERVICES) {
            if (resultCode == RESULT_OK) {
                // Make sure the app is not already connected or attempting to connect
                if (!mGoogleApiClient.isConnecting() &&
                        !mGoogleApiClient.isConnected()) {
                    mGoogleApiClient.connect();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Google Play Services must be installed.",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(20000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    @Override
    public void onLocationChanged(Location location)
    {
        mLastLocation = location;
        if (mLastLocation != null)
        {
            Log.d(TAG, "onLocationChanged: ");
            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
        }
    }

    protected void stopLocationUpdates() {
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(
                    mGoogleApiClient, this);
        }
    }

}
