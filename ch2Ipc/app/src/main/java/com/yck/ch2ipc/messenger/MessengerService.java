package com.yck.ch2ipc.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.yck.ch2ipc.utils.LogUtil;

import static android.R.attr.handle;

/**
 * Created by key on 2016/12/31.
 */

public class MessengerService extends Service {
    private static final String TAG = MessengerService.class.getSimpleName();
    @Nullable
    String n = null;

    public Messenger messenger;
    public class  MyHandler extends  Handler{
        @Override
        public void handleMessage(Message m){
            logE("m="+m.toString());
            if(m.what == 0){
                try {
                    messenger.send(Message.obtain(null, 100, 11, 12));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            super.handleMessage(m);

        }

    }
    private void logE(String log){
        LogUtil.E(TAG,log);
    }


    public MessengerService() {
        messenger = new Messenger(new MyHandler());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
