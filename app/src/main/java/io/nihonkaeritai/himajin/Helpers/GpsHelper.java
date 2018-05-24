package io.nihonkaeritai.himajin.Helpers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Handler;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import io.nihonkaeritai.himajin.Wrappers.GpsWrapper;

public class GpsHelper {

    private GpsRunnable mGpsRunnable;
    private Handler mGpsHandler;
    private GpsWrapper mGpsWrapper;
    private long mTimeOutDuration;
    private long mRefreshInterval;

    public GpsHelper(Context context, long refresh, long timeOut, GpsWrapper.LocationReceiver receiver){
        mGpsHandler = new Handler();
        mGpsWrapper = new GpsWrapper(context, receiver);
        mGpsRunnable = new GpsRunnable();
        mRefreshInterval = refresh;
        mTimeOutDuration = timeOut;
    }

    public void stopGpsUpdates() {
        mGpsHandler.removeCallbacks(mGpsRunnable);
    }

    public void resumeGpsUpdates(){
        mGpsHandler.post(mGpsRunnable);
    }

    private class GpsRunnable implements Runnable {
        @Override
        public void run() {
            mGpsWrapper.updateLocation(mTimeOutDuration);
            mGpsHandler.postDelayed(this, mRefreshInterval);
        }
    }

    public static String GetCityName(Context context, Location loc){
        String cityName = "Unknown";
        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(loc.getLatitude(),
                    loc.getLongitude(), 1);
            if (addresses.size() > 0) {
                cityName = addresses.get(0).getLocality();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return cityName;
    }
}