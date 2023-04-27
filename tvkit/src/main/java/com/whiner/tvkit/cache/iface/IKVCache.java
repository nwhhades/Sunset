package com.whiner.tvkit.cache.iface;

import com.tencent.mmkv.MMKV;

public interface IKVCache {

    MMKV getMMKV();

    long getNotExpireTime();

    String getKeySuffix();

    String get(String key, String def);

    void put(String key, String val, long keep_time);

    void put(String key, String val);

}
