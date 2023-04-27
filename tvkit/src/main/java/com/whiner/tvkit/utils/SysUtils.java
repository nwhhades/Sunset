package com.whiner.tvkit.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;

import com.blankj.utilcode.util.Utils;

public class SysUtils {

    private static final String sysSharedUserId = "android.uid.system";

    public static boolean isSysApp() {
        try {
            Context context = Utils.getApp();
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return sysSharedUserId.equals(packageInfo.sharedUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getValue(String key) {
        return Settings.Global.getString(Utils.getApp().getContentResolver(), key);
    }

    public static boolean setValue(String key, String value) {
        return Settings.Global.putString(Utils.getApp().getContentResolver(), key, value);
    }

}
