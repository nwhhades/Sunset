package com.whiner.tvkit.ui.base;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.whiner.tvkit.R;
import com.whiner.tvkit.ui.base.iface.IActivity;
import com.whiner.tvkit.ui.fragment.LoadingFragment;

public abstract class BaseActivity<V extends ViewBinding> extends AppCompatActivity implements IActivity<V> {

    private static final String TAG = "BaseActivity";
    private static final String SP_KEY_APP_BACKGROUND = "SP_KEY_APP_BACKGROUND";
    protected V viewBinding;
    protected AppCompatImageView ivAppBackground;
    protected LoadingFragment loadingFragment;

    @Override
    public void preInit() {
        Log.d(TAG, "preInit: 预加载 " + this);
    }

    @Override
    public boolean enableAppBackground() {
        return false;
    }

    @Override
    public void initAppBackground() {
        if (enableAppBackground() && viewBinding.getRoot() instanceof ViewGroup) {
            ivAppBackground = new AppCompatImageView(this);
            ivAppBackground.setId(R.id.iv_app_background);
            ivAppBackground.setScaleType(ImageView.ScaleType.FIT_XY);
            ivAppBackground.setBackgroundResource(R.color.window_bg);
            ViewGroup viewGroup = (ViewGroup) viewBinding.getRoot();
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            viewGroup.addView(ivAppBackground, 0, layoutParams);
        }
    }

    @Override
    public void loadAppBackground(@NonNull String url) {
        if (ivAppBackground != null && !("".equals(url))) {
            Object srcTag = url.hashCode();
            Object tag = ivAppBackground.getTag();
            Log.d(TAG, "loadAppBackground: " + srcTag + " - " + tag);
            if (srcTag.equals(tag)) {
                Log.d(TAG, "loadAppBackground: 图片源的hashCode相同，跳过重新加载");
            } else {
                Glide.with(this)
                        .load(url)
                        .dontAnimate()
                        .into(ivAppBackground);
                ivAppBackground.setTag(srcTag);
            }
        }
    }

    @Override
    public String readAppBackgroundSrc() {
        return SPUtils.getInstance().getString(SP_KEY_APP_BACKGROUND, "");
    }

    @Override
    public void writeAppBackgroundSrc(@NonNull String url) {
        SPUtils.getInstance().put(SP_KEY_APP_BACKGROUND, url);
    }

    @Override
    public void showLoadingView(@NonNull String msg) {
        if (loadingFragment != null) {
            loadingFragment.hideFragment();
            loadingFragment = null;
        }
        loadingFragment = new LoadingFragment(msg);
        loadingFragment.showFragment(getSupportFragmentManager());
    }

    @Override
    public void hideLoadingView() {
        if (loadingFragment != null) loadingFragment.hideFragment();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        preInit();
        Log.d(TAG, "onCreate: " + this);
        super.onCreate(savedInstanceState);
        viewBinding = getViewBinding();
        initAppBackground();
        setContentView(viewBinding.getRoot());
        initView();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: " + this);
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: " + this);
        super.onResume();
        loadAppBackground(readAppBackgroundSrc());
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: " + this);
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: " + this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: " + this);
        hideLoadingView();
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart: " + this);
        super.onRestart();
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: " + this);
        super.onBackPressed();
    }

    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptWidth(super.getResources(), 3840);
    }

}
