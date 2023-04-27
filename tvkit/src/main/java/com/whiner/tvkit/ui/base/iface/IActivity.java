package com.whiner.tvkit.ui.base.iface;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

public interface IActivity<V extends ViewBinding> {

    /**
     * 获取ViewBinding
     *
     * @return ViewBinding
     */
    V getViewBinding();

    /**
     * 预初始化
     */
    void preInit();

    /**
     * 初始化View
     */
    void initView();

    /**
     * 是否启用APP全局背景
     *
     * @return true false
     */
    boolean enableAppBackground();

    /**
     * 初始化APP背景
     */
    void initAppBackground();

    /**
     * 加载url到ImageView
     *
     * @param url 图片地址
     */
    void loadAppBackground(@NonNull String url);

    /**
     * 读取APP背景地址
     *
     * @return 背景地址
     */
    String readAppBackgroundSrc();

    /**
     * 写入APP背景地址到缓存中
     *
     * @param url 背景地址
     */
    void writeAppBackgroundSrc(@NonNull String url);

    /**
     * 显示LoadingView
     *
     * @param msg 按返回键提示文本
     */
    void showLoadingView(@NonNull String msg);

    /**
     * 隐藏LoadingView
     */
    void hideLoadingView();

}
