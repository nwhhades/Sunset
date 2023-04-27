package com.whiner.tvkit;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.hjq.toast.Toaster;
import com.whiner.tvkit.toaster.BigBlackToastStyle;

public abstract class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        initToaster();
        init();
    }

    protected void initToaster() {
        Toaster.init(this, new BigBlackToastStyle());
    }

    protected abstract void init();

}
