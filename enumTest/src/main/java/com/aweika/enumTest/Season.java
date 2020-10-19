package com.aweika.enumTest;

/**
 * @author: Michael
 * @date: 2020/3/3
 * @description:
 */
public enum Season {
    SPRING(222, "bbb"), SUMMER, AUTUMN, WINTER;

    private Integer code;

    private String message;

    Season(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    Season() {
    }
}
