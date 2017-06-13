package id.sch.smktelkom_mlg.privateassignment.xirpl303.cinemaxxiv.service;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import id.sch.smktelkom_mlg.privateassignment.xirpl303.cinemaxxiv.NowPlayingFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl303.cinemaxxiv.adapter.NowPlaying;

/**
 * Created by Andini Novidayanti A on 13/05/2017.
 */

public class VolleySingleton {
    private static volatile VolleySingleton mInstance;
    private static Context mCtx;
    private RequestQueue mRequestQueue;

    private VolleySingleton(Context context) {
        if (mInstance != null) {
            throw new RuntimeException(
                    "Use getInstance() method to get the single instance of this class");
        }
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static VolleySingleton getInstance(Context context) {
        if (mInstance == null) {
            synchronized (VolleySingleton.class) {
                if (mInstance == null) mInstance = new VolleySingleton(context);
            }
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}

