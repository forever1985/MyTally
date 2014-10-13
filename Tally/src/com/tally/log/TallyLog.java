package com.tally.log;

import android.util.Log;

public class TallyLog {

    private static boolean mIsShowLog = true;
    private static final String TAG = "Tally";
    
    public static void i (String log) {
        if (mIsShowLog) {
            Log.i(TAG, log);
        }
    }
    public static void e (String log) {
        if (mIsShowLog) {
            Log.e(TAG, log);
        }
    }
    
}
