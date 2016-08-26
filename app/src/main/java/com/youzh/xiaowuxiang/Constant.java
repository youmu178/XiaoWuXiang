package com.youzh.xiaowuxiang;

import java.util.HashMap;

/**
 * Created by youzehong on 16/6/7.
 */
public class Constant {
    public static final String URL = "http://movtop.cn";
    public static final int PAGE_SIZE = 20;

    public static final String EXTRA_URL = "extra_url";

    public static final String[] CATEGORY = {"电影", "最新电影", "高分电影", "高分喜剧", "高分惊悚", "豆瓣9分", "高清蓝光", "英美剧", "资源合集"};
    public static final String[] CATEGORY_EN = {"movie", "latestmovie", "bestmovie", "gaofenxiji", "gaofenjingsong", "doubanjiufen", "bluray", "ustv", "news"};
    public static final String[] CATEGORY_MOVIE = {"喜剧片", "动作片", "爱情片", "剧情片", "动画片", "科幻片", "恐怖片", "记录片"};
    public static final String[] CATEGORY_MOVIE_EN = {"comedy", "actionfilm", "lovefilm", "storyfilm", "animatedfilm", "science-fictionfilm", "horrorfilm", "documentary"};

    public static HashMap<String, String> getCategoryMovie() {
        HashMap<String, String> strMap = new HashMap<>();
        strMap.put("喜剧片", "comedy");
        strMap.put("动作片", "actionfilm");
        strMap.put("爱情片", "lovefilm");
        strMap.put("剧情片", "storyfilm");
        strMap.put("动画片", "animatedfilm");
        strMap.put("科幻片", "science-fictionfilm");
        strMap.put("恐怖片", "horrorfilm");
        strMap.put("记录片", "documentary");
        return strMap;
    }

    public static HashMap<String, String> getCategory() {
        HashMap<String, String> strMap = new HashMap<>();
        strMap.put("电影", "movie");
        strMap.put("最新电影", "latestmovie");
        strMap.put("高分电影", "bestmovie");
        strMap.put("高分喜剧", "gaofenxiji");
        strMap.put("高分惊悚", "gaofenjingsong");
        strMap.put("豆瓣9分", "doubanjiufen");
        strMap.put("高清蓝光", "bluray");
        strMap.put("英美剧", "ustv");
        strMap.put("资源合集", "news");
        return strMap;
    }
}
