package bresiu.mapapplication;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.google.android.gms.location.LocationListener;

public class MapsActivity extends FragmentActivity implements LocationListener,
        android.location.LocationListener {

    private TextView mPointsCount;
    private int mPointsCounter = 0;

    private Array array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        initViews();
        initVarables();
    }

    private void initVarables() {
        array = new Array();
    }

    private void initViews() {
        mPointsCount = (TextView) findViewById(R.id.points_value);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initLocationListener();
    }

    private void initLocationListener() {
        LocationManager mLocationManager = (LocationManager) getSystemService(Context
                .LOCATION_SERVICE);
        Lo.g(mLocationManager.getAllProviders().toString());
        if (mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) != null) {
            onLocationChanged(mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onLocationChanged(Location location) {
        mPointsCounter++;
        mPointsCount.setText(mPointsCounter + "");

        array.insertPoint(new Point(mPointsCounter, location.getSpeed(),
                location.getLongitude(), location.getLatitude(), location.getTime()));
        Lo.g("New Location:" +
                "\ncount: " + mPointsCounter +
                "\nspeed: " + location.getSpeed() +
                "\nlong: " + location.getLongitude() +
                "\nlat: " + location.getLatitude());
        if (mPointsCounter == 50) {
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(200);

            array.saveLogs(array.returnArray());
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
}
