package nihonkaeritai.com.himajin.Helpers;

import android.content.Context;
import android.os.Handler;

import nihonkaeritai.com.himajin.Wrappers.GpsWrapper;

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

    public void stopGpsUpdates(){
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
}