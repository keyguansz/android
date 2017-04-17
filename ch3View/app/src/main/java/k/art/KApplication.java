package k.art;


import android.app.Application;

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

}
