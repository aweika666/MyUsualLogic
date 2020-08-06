package com.aweika.common.utils;

import java.util.UUID;

/**
 * Created by sadyppz on 2017/7/19.
 */
public class UUidUtil {
    public static String get32Uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
