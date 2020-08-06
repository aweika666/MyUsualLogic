package com.aweika.common.utils;

import java.util.Random;

/**
 * @version 1.0
 * @ClassName: AuthCode
 * @Description: 验证码生成器。
 */

public final class AuthCodeUtil {

    private static final Integer AUTH_CODE_MAX = 10;

    private static final Integer AUTH_CODE_LENGTH = 6;


    /**
     * 生成一个6位的随机验证码，包含当前的时间戳。
     *
     * @return 验证码。
     */
    public static String generateAuthCode() {
        Random rd = new Random();
        StringBuilder code = new StringBuilder("");
        int getNum = 0;
        do {
            getNum = Math.abs(rd.nextInt(AUTH_CODE_MAX)); // 产生数字0-9的随机数
            code.append(getNum);
        } while (code.length() < AUTH_CODE_LENGTH);
        return code.toString();
    }

    public static String code() {
        Random rd = new Random();
        StringBuilder code = new StringBuilder("");
        int getNum = 0;
        do {
            getNum = Math.abs(rd.nextInt(AUTH_CODE_MAX)); // 产生数字0-9的随机数
            code.append(getNum);
        } while (code.length() < 4);
        return code.toString();
    }
}
