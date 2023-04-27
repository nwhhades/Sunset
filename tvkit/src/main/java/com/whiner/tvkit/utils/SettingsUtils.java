package com.whiner.tvkit.utils;

import android.content.Intent;
import android.provider.Settings;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;

public class SettingsUtils {

    private static final String DEFAULT_SETTING_PACKAGE_NAME = "com.android.settings";

    public static void openSetting() {
        try {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ActivityUtils.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            AppUtils.launchApp(DEFAULT_SETTING_PACKAGE_NAME);
        }
    }

    public static void openNetSetting() {
        try {
            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ActivityUtils.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            openSetting();
        }
    }

}
