package com.whiner.tvkit.ui.fragment;

import android.app.Dialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hjq.toast.Toaster;
import com.whiner.tvkit.databinding.FragmentLoadingDialogBinding;
import com.whiner.tvkit.ui.base.BaseDialogFragment;

public class LoadingFragment extends BaseDialogFragment<FragmentLoadingDialogBinding> {

    private static final String TAG = "LoadingFragment";

    private final String msg;

    public LoadingFragment(String msg) {
        this.msg = msg;
    }

    @Override
    public FragmentLoadingDialogBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentLoadingDialogBinding.inflate(inflater, container, false);
    }

    @Override
    public void initView() {
        setCancelable(false);
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setOnKeyListener((dialog1, keyCode, event) -> {
                if (KeyEvent.ACTION_DOWN == event.getAction()) {
                    if (KeyEvent.KEYCODE_BACK == keyCode) {
                        Log.d(TAG, "initView: 按了一次返回键");
                        Toaster.showShort(msg);
                        return true;
                    }
                }
                return false;
            });
        }
    }

}
