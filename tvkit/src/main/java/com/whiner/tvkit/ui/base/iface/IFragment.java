package com.whiner.tvkit.ui.base.iface;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.viewbinding.ViewBinding;

public interface IFragment<V extends ViewBinding> {

    V getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);

    void showFragment(@NonNull FragmentManager manager);

    void hideFragment();

    void initView();

}
