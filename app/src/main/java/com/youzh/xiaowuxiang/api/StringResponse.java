package com.youzh.xiaowuxiang.api;

import android.text.TextUtils;

import com.youzh.xiaowuxiang.movie.entity.MovieDetailEntity;
import com.youzh.xiaowuxiang.movie.entity.MovieEntity;
import com.youzh.xiaowuxiang.utils.StringUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by youzehong on 16/6/13.
 */
public class StringResponse implements Callback<String> {
    public static final int MOVIE = 100;
    public static final int MOVIE_DETAIL = 200;
    private int mChance;

    private List<MovieEntity> mListMovie = new ArrayList<>();

    public StringResponse(int chance) {
        this.mChance = chance;
    }

    public void onSuccess(List<MovieEntity> list) {

    }

    public void onSuccess(MovieDetailEntity movieDetailEntity) {

    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        try {
            if (!TextUtils.isEmpty(response.body())) {
                Document document = Jsoup.parse(response.body());
                switch (mChance) {
                    case MOVIE:
                        parseMovieData(document);
                        break;
                    case MOVIE_DETAIL:
                        parseMovieDetail(document);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getString(List<Node> nodeList, int a) {
        int b = a;
        String s = nodeList.get(a).toString();
        if (s.contains("【简介】")) {
            if (b >= 15) {
                return 15;
            }
            b = ++a;
            getString(nodeList, b);
        }
        return b;
    }

    private void parseMovieDetail(Document document) {
        int a = 11; // 简介的下标
        MovieDetailEntity movieDetailEntity = new MovieDetailEntity();
        List<String> photoList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String cover = document.getElementsByClass("pinbin-image").first().getElementsByTag("img").first().attr("src");
        Timber.i(cover);
        // 封面
        movieDetailEntity.setCover(cover);
        Elements allContent = document.getElementsByClass("pinbin-copy");
        Element element = allContent.get(0);
        List<Node> nodeList = element.childNodes();

        movieDetailEntity.setTitle(nodeList.get(1).toString().replace("<h1>", "").replace("</h1>", ""));
        movieDetailEntity.setAuthor(nodeList.get(3).toString().replace("<p class=\"pinbin-meta\">", "").replace("</p>", ""));

        int b = getString(nodeList, a);
        if (b <= 15) {
            movieDetailEntity.setIntroduce(nodeList.get(b).toString().replace("<p>", "").replace("</p>", ""));
        }

        int index = 0;
        int sbIndex = 0;
        for (Node node : nodeList) {
            Timber.i(node.toString());
            if (node != null) {
                List<Node> nodes = node.childNodes();
                for (Node node1 : nodes) {
                    if (node1 != null) {
                        ++index;
//                        Timber.i(++index + "");
//                        Timber.i(node1.toString());

                        if (node1.toString().contains(".article-download-box")) {
                            movieDetailEntity.setActor(sb.toString());
                            movieDetailEntity.setPhotoList(photoList);
                            onSuccess(movieDetailEntity);
                            return;
                        }
                        Attributes attributes = node1.attributes();
                        for (Attribute attr : attributes) {
//                            Timber.d("key: " +attr.getKey() + " value: " + attr.getValue());
                            String value = attr.getValue();
                            if (!"\n".equals(value.trim()) && !TextUtils.isEmpty(value.trim())) {
//                                Timber.i(++index + "");
//                                Timber.w(value);

                                if (value.contains("导演: ") || value.contains("编剧: ") || value.contains("主演: ")|| value.contains("类型: ")
                                        || value.contains("制片国家/地区: ")|| value.contains("语言: ")|| value.contains("上映时间: ")
                                        || value.contains("片长: ")|| value.contains("又名: ")|| value.contains("IMDb链接: ")
                                        || value.contains("官方网站: ")) {
                                    if (sbIndex > 0) {
                                        sb.append("\n");
                                    }
                                    sb.append(value);
                                    sbIndex ++;
//                                    Timber.e("sbIndex: " + sbIndex);
                                }
                                if (value.contains("http")) {
                                    photoList.add(value);
                                }
                            }
                        }
//                        List<Node> nodeList1 = node1.childNodes();
//                        for (Node node2: nodeList1) {
////                            Timber.e(node2.toString());
//                        }
                    }
                }
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
    }

    private void parseMovieData(Document document) {
        Elements pagesElement = document.getElementsByClass("pages");
        Timber.d(pagesElement.text());
        String pageStart = document.getElementsByClass("current").text();
        String endPageHref = document.getElementsByClass("last").first().attr("href");
        String pageEnd = StringUtils.getStr(endPageHref);
        Timber.d(pageStart + pageEnd);

        Element element = document.getElementById("post-area");
        Elements childrens = element.children();
        for (Element e : childrens) {
            MovieEntity movieEntity = new MovieEntity();
            //获取图片信息
            Elements imgElements = e.getElementsByClass("pinbin-image");
            for (Element img : imgElements) {
                Elements tag = img.getElementsByTag("img");
                for (Element imgSrc : tag) {
                    // 获取到的图片地址
                    String src = imgSrc.attr("src");
                    Timber.i(src);
                    movieEntity.setmUrlImg(src);
                }
            }
            //获取内容信息
            Elements infoElements = e.getElementsByClass("pinbin-copy");
            for (Element info : infoElements) {
                //获取Title
                String title = info.getElementsByClass("front-link").text();
                Timber.i(title);
                movieEntity.setmTitle(title);
                Elements p = info.getElementsByTag("p");
                for (Element pElement : p) {
                    String className = pElement.className();
                    if ("pinbin-date".equals(className)) {
                        //获取日期
                        String date = pElement.getElementsByClass("pinbin-date").text();
                        Timber.i(date);
                        movieEntity.setmDate(date);
                    } else if ("pinbin-link".equals(className)) {
                        //获取点击跳转到的连接
                        String detailUrl = pElement.getElementsByTag("a").first().attr("href");
                        Timber.i(detailUrl);
                        movieEntity.setmUrlDetail(detailUrl);
                    } else {
                        //获取内容
                        String content = pElement.text();
                        Timber.i(content);
                        movieEntity.setmContent(content);
                    }
                }
            }
            mListMovie.add(movieEntity);
        }
        onSuccess(mListMovie);
    }
}
