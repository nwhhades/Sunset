package com.whiner.tvkit.install;

import androidx.annotation.NonNull;

public class InstallEvent {

    private String packageName;
    private boolean success;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @NonNull
    @Override
    public String toString() {
        return "InstallEvent{" +
                "packageName='" + packageName + '\'' +
                ", success=" + success +
                '}';
    }

}
