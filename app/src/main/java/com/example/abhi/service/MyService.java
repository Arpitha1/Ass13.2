package com.example.abhi.service;


import android.app.Service;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Binder;
import android.os.IBinder;

import java.util.Date;

public class MyService extends Service {
    IBinder binder = new LocalBinder();


    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class LocalBinder extends Binder {
        public MyService getServerInstance() {
            return MyService.this;
        }
    }


    public String getTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}

