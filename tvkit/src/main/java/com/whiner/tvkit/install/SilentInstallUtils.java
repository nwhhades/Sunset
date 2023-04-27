package com.whiner.tvkit.install;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageInstaller;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SilentInstallUtils {

    @SuppressLint("UnspecifiedImmutableFlag")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static void silentInstall(@NonNull final File file) {
        try {
            Context context = Utils.getApp();
            PackageInstaller packageInstaller = context.getPackageManager().getPackageInstaller();
            PackageInstaller.SessionParams params = new PackageInstaller.SessionParams(PackageInstaller.SessionParams.MODE_FULL_INSTALL);
            int sessionId = packageInstaller.createSession(params);
            PackageInstaller.Session session = packageInstaller.openSession(sessionId);
            addApkToInstallSession(file, session);
            // Create an install status receiver.
            Intent intent = new Intent();
            PendingIntent pendingIntent;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
            } else {
                pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
            }
            IntentSender statusReceiver = pendingIntent.getIntentSender();
            // Commit the session (this will start the installation workflow).
            session.commit(statusReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private static void addApkToInstallSession(@NonNull final File file, @NonNull final PackageInstaller.Session session) throws IOException {
        // It's recommended to pass the file size to openWrite(). Otherwise installation may fail
        // if the disk is almost full.
        try (OutputStream os = session.openWrite("package", 0, -1); FileInputStream is = new FileInputStream(file)) {
            byte[] buffer = new byte[65536];
            int n;
            while ((n = is.read(buffer)) >= 0) {
                os.write(buffer, 0, n);
            }
        }
    }

}
