package com.youzh.xiaowuxiang.utils;

/**
 * Created by youzehong on 16/6/16.
 */
public class StringUtils {
    public static String getStr(String href) {
        int last = href.lastIndexOf("/");
        String end = href.substring(last + 1, href.length());
        return end;
    }
}
