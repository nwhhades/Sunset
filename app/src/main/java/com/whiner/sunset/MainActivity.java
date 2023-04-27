package com.whiner.sunset;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.PathUtils;
import com.bumptech.glide.Glide;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.whiner.sunset.databinding.ActivityMainBinding;
import com.whiner.tvkit.ui.base.BaseActivity;

import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding> {


    private static final String TAG = "MainActivity";

    private static final String url = "https://down.wireless-tech.cn/resourceDown/HX-AT-MESH-APK/hx-mesh-release-v0.1.0.apk";

    //private static final String url = "https://gitcode.net/qq_42261027/vod/raw/master/2b675734424e43f102ec3047ded2573f.mp4";


    //private static final String url = "http://file.mounriver.com/upgrade/MounRiver_Studio_Setup_V184.zip";

    @Override
    public void preInit() {
        writeAppBackgroundSrc("http://tse-mm.cn.bing.net/th/id/OIP-C.d0uSI7WjUmxhaR_MXssxRQHaE8?w=280&h=187&c=7&r=0&o=5&pid=1.7");
    }

    @Override
    public boolean enableAppBackground() {
        return true;
    }

    @Override
    public ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        viewBinding.tvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkDownDialog dialog = new OkDownDialog("aadsfasd", "fasdfasd",url, PathUtils.getExternalAppDataPath());
                dialog.showFragment(getSupportFragmentManager());
            }
        });
//        XXPermissions.with(this)
//                .permission(Permission.REQUEST_INSTALL_PACKAGES)
//                .request(new OnPermissionCallback() {
//                    @Override
//                    public void onGranted(@NonNull List<String> permissions, boolean allGranted) {
//                        Log.d(TAG, "onGranted: " + permissions);
//                    }
//                });

        viewBinding.btn2.setSelected(true);


        Glide.with(this)
                .load("http://tse2-mm.cn.bing.net/th/id/OIP-C.d0uSI7WjUmxhaR_MXssxRQHaE8?w=280&h=187&c=7&r=0&o=5&pid=1.7")
                .into(viewBinding.ivRound);

    }

}