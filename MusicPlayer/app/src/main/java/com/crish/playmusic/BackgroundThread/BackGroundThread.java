package com.crish.playmusic.BackgroundThread;

import android.os.Handler;
import android.os.HandlerThread;

public class BackGroundThread extends HandlerThread {
    public Handler mHandler;
    private LooperPreparedListener looperPreparedListener;

    public BackGroundThread(String name, LooperPreparedListener looperPreparedListener) {
        super(name);
        this.looperPreparedListener = looperPreparedListener;
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        mHandler = new Handler(getLooper());
        looperPreparedListener.onLooperPrepared();
    }

    public void addTaskToQueue(Runnable task) {
        mHandler.post(task);
    }
}
