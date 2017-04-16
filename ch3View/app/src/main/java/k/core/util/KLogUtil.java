package k.core.util;

import android.util.Log;

public class KLogUtil {
    private static final String TAG = "ART";
    public static void D(String log){
        Log.d(TAG, log);
    }
    public static void D(String clsName, String log){
        Log.d(TAG, clsName+"->"+log);
    }
    public static void D(String clsName, String methodName, String log){
        Log.d(TAG, clsName+"->"+methodName+"():" + log);
    }
}
