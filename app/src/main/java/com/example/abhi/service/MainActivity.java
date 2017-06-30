package com.example.abhi.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Boolean mBounded;
    MyService mServer;
    TextView text;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                text.setText(mServer.getTime());
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        Intent intent = new Intent(this,MyService.class);
        bindService(intent,mConnection,BIND_AUTO_CREATE);
    }
    ServiceConnection mConnection = new ServiceConnection() {


        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Toast.makeText(MainActivity.this,"Service is disconneted",Toast.LENGTH_LONG).show();
            mBounded = false;
            mServer = null;
        }

        public void onServiceConnected(ComponentName name,IBinder service){
            Toast.makeText(MainActivity.this,"Service is connected",Toast.LENGTH_LONG).show();
            mBounded=true;
            MyService.LocalBinder mLocalBinder = (MyService.LocalBinder)service;
            mServer=mLocalBinder.getServerInstance();

        }




    };
        @Override
        protected void onStop() {
            super.onStop();
            if(mBounded) {
                unbindService(mConnection);
                mBounded = false;
            }
        }


    }


