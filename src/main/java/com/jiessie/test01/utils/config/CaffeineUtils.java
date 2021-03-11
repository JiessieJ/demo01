package com.jiessie.test01.utils.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class CaffeineUtils {

    public static Cache<String, String> cache = Caffeine.newBuilder()
            //5秒没有读写自动删除
            .expireAfterAccess(5, TimeUnit.SECONDS)
            //最大容量1024个，超过会自动清理空间
            .maximumSize(1024)
            .removalListener(((key, value, cause) -> {
                System.out.println("sdfasdfasdf");
                //清理通知 key,value ==> 键值对   cause ==> 清理原因
            }))
            .build();
}
