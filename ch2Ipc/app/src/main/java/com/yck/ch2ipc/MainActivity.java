package com.yck.ch2ipc;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.yck.ch2ipc.messenger.MessengerActivity;
import com.yck.ch2ipc.messenger.MessengerActivity2;
import com.yck.ch2ipc.utils.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private String path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserManager.sUserId = 2;
        LogUtil.E(TAG," UserManager.sUserId ="+UserManager.sUserId );
        findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
        findViewById(R.id.write_btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                persistToFile();
            }
        });
        findViewById(R.id.read_btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                readFromFile();
            }
        });
        findViewById(R.id.send_btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(MainActivity.this, MessengerActivity2.class));
            }
        });


        path = getFilesDir().getAbsolutePath()+"/user.o";


    }

    @Override
    protected void onResume() {


        super.onResume();
    }

    private void persistToFile() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                File file = new File(path);
                User user = new User();
                user.userId = 10;
                user.userName = "key";
                user.isMale = true;
                if(file.exists()){
                    LogUtil.E(TAG, "file.mkdirs()");
                    file.mkdirs();
                }
                ObjectOutputStream oos = null;
                try {
                     oos = new ObjectOutputStream( new FileOutputStream(file));
                    oos.writeObject(user);
                } catch (IOException e) {
                    LogUtil.E(TAG, "e="+e);
                    e.printStackTrace();
                }finally {
                    if(oos != null){
                        try {
                            oos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
        }).start();
    }
    private void readFromFile() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                File file = new File(path);
                User user;
                if(file.exists()){
                    LogUtil.E(TAG, "file.mkdirs()");
                    file.mkdirs();
                }
                ObjectInputStream ois = null;
                try {
                    ois = new ObjectInputStream( new FileInputStream(file));
                    user = (User)ois.readObject();
                    LogUtil.E(TAG, "user="+user+",user="+user.toString());
                } catch (ClassNotFoundException e) {
                    LogUtil.E(TAG, "e="+e);
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(ois != null){
                        try {
                            ois.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
        }).start();
    }
}
