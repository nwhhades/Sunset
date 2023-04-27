package com.whiner.tvkit.install;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;

public class ApkReceiver extends BroadcastReceiver implements LifecycleEventObserver {

    private static final String TAG = "ApkReceiver";
    private final String apkPackageName;
    private final WeakReference<Activity> weakReference;

    public ApkReceiver(@NonNull String apkPackageName, @NonNull Activity activity) {
        this.apkPackageName = apkPackageName;
        this.weakReference = new WeakReference<>(activity);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String packageName = intent.getData().getSchemeSpecificPart();
            String action = intent.getAction();
            switch (action) {
                case Intent.ACTION_PACKAGE_ADDED:
                    Log.d(TAG, "onReceive: 安装成功 " + packageName);
                    sendInstallEvent(packageName, 1);
                    break;
                case Intent.ACTION_PACKAGE_REMOVED:
                    Log.d(TAG, "onReceive: 卸载成功 " + packageName);
                    sendInstallEvent(packageName, 2);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        Activity activity = weakReference.get();
        if (activity == null) return;
        if (event == Lifecycle.Event.ON_CREATE) {
            register(activity);
        } else if (event == Lifecycle.Event.ON_DESTROY) {
            unregister(activity);
        }
    }

    private void register(Activity activity) {
        Log.d(TAG, "onStateChanged: 开始注册");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addDataScheme("package");
        activity.registerReceiver(this, intentFilter);
    }

    private void unregister(Activity activity) {
        Log.d(TAG, "onStateChanged: 注销注册");
        activity.unregisterReceiver(this);
    }

    private boolean installSuccess = false;

    private void sendInstallEvent(String packageName, int i) {
        if (apkPackageName.equals(packageName) && !installSuccess) {
            if (i == 1) {
                installSuccess = true;
                InstallEvent event = new InstallEvent();
                event.setPackageName(apkPackageName);
                event.setSuccess(true);
                EventBus.getDefault().post(event);
            }
        }
    }

}
