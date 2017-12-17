package com.example.ricardo.tickit2.data.network.utils;

import com.qiniu.android.common.FixedZone;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;

public class QiniuImageLoader {

    private static UploadManager instance;

    private QiniuImageLoader() {

    }

    //获取UploadManager
    public static UploadManager getInstance() {
        if (instance == null) {
            synchronized (QiniuImageLoader.class) {
                if (instance == null) {
                    instance = new UploadManager(new Configuration.Builder()
                            .zone(FixedZone.zone0) // 华东
                            .build());
                }
            }
        }
        return instance;
    }
}
