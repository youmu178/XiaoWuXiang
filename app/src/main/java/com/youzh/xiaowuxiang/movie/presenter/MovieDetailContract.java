package com.youzh.xiaowuxiang.movie.presenter;

import com.youzh.xiaowuxiang.BasePresenter;
import com.youzh.xiaowuxiang.BaseView;
import com.youzh.xiaowuxiang.movie.entity.MovieDetailEntity;

/**
 * Created by youzehong on 16/6/20.
 */
public interface MovieDetailContract {

    interface Presenter extends BasePresenter {

        void load(String url);
    }

    interface View extends BaseView {

        void onMovieDetailGet(MovieDetailEntity movieDetailEntity);
    }
}
