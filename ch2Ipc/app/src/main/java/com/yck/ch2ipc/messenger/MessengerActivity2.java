package com.yck.ch2ipc.messenger;



import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.yck.ch2ipc.R;
import com.yck.ch2ipc.utils.LogUtil;
import com.yck.ch2ipc.utils.MyConstants;

public class MessengerActivity2 extends Activity {

    private static final String TAG = "MessengerActivity2";
    private Messenger mGetReplyMessenger = new Messenger(new MyHandler());


    private ServiceConnection mC2 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            messenger = new Messenger(iBinder);
           Message m =  Message.obtain(null,1,2,3);
            m.replyTo = mGetReplyMessenger;
            try {
                messenger.send(m);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    private Messenger messenger;
    public class  MyHandler extends  Handler{
        @Override
        public void handleMessage(Message m){
            logE("m="+m.toString());

            super.handleMessage(m);

        }

    }
    private void logE(String log){
        LogUtil.E(TAG,log);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, mC2, BIND_AUTO_CREATE);
        findViewById(R.id.send_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    messenger.send(Message.obtain(null,0,8,9));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    
    @Override
    protected void onDestroy() {
        unbindService(mC2);
        super.onDestroy();
    }
}
