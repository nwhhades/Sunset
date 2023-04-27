package com.whiner.tvkit.download.iface;

import java.io.File;

public interface IDownload {

    /**
     * 初始化下载任务
     *
     * @param url  下载地址
     * @param path 下载目录
     */
    void initDownTask(String url, String path);

    /**
     * 开始下载
     */
    void startDownTask();

    /**
     * 停止下载
     */
    void stopDownTask();

    /**
     * 判断是否下载中
     *
     * @return 下载状态
     */
    boolean isDownloading();

    /**
     * 下载启动状态View
     */
    void onStartView();

    /**
     * 下载停止状态View
     */
    void onStopView();

    /**
     * 下载启动
     */
    void onDownStart();

    /**
     * 下载进度
     *
     * @param cur   当前进度
     * @param total 总进度
     */
    void onDownProgress(long cur, long total);

    /**
     * 下载成功
     *
     * @param file 下载的文件
     */
    void onDownSuccess(File file);

    /**
     * 下载失败
     */
    void onDownFail();

    /**
     * 下载停止
     */
    void onDownEnd();

}
