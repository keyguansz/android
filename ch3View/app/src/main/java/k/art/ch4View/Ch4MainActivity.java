package k.art.ch4View;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.TextView;


import java.util.Timer;
import java.util.TimerTask;

import k.art.R;

//http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2012/1117/574.html
public class Ch4MainActivity extends Activity {
    private final String TAG = "MainActivity";

    private TextView mInfoTv;
    private VelocityTracker mVelocityTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ch3main);
        mInfoTv = (TextView) findViewById(R.id.info_tv);
        mVelocityTracker = VelocityTracker.obtain();
        int i = ViewConfiguration.get(getApplicationContext()).getScaledTouchSlop();
        Log.e(TAG, "onCreate: getScaledTouchSlop="+ i );//24
        mInfoTv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mVelocityTracker.addMovement(motionEvent);

                return false;
            }
        });
        TimerTask timerTask =new TimerTask(){
            public void run(){
                mVelocityTracker.computeCurrentVelocity(5000);
                int xV = (int)mVelocityTracker.getXVelocity();
                int yV = (int)mVelocityTracker.getYVelocity();
                Log.e(TAG, "onTouch: xV="+ xV+",yV="+ yV);
            }
      };
        Timer timer =  new Timer();
        timer.schedule(timerTask,200,1000);

    }
}
