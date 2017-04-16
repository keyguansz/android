package k.art;


import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import k.core.util.KDimenUtil;

/**
 * Created by key on 2017/4/16.
 */

public class KApplication extends Application {

    public KApplication() {
        super();
    }

    public void onCreate() {
        super.onCreate();
        KDimenUtil.main();


    }

    public void onTerminate() {


    }

    public void onConfigurationChanged(Configuration newConfig) {

    }
}
