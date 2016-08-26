package com.youzh.xiaowuxiang.movie.component;

import com.youzh.xiaowuxiang.movie.activitys.MovieDetailActivity;
import com.youzh.xiaowuxiang.movie.module.MovieDetailModule;

import dagger.Component;

/**
 * Created by youzehong on 16/6/27.
 */
@Component(modules = MovieDetailModule.class)
public interface MovieDetailComponent {
    void inject(MovieDetailActivity activity);
}
