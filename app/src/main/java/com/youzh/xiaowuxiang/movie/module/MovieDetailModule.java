package com.youzh.xiaowuxiang.movie.module;

import android.content.Context;

import com.youzh.xiaowuxiang.movie.presenter.MovieDetailContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by youzehong on 16/6/27.
 */
@Module
public class MovieDetailModule {
    private final MovieDetailContract.View mView;
    private final Context mContext;

    public MovieDetailModule(Context context, MovieDetailContract.View mView) {
        this.mView = mView;
        this.mContext = context;
    }

    @Provides
    MovieDetailContract.View provideView() {
        return mView;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }
}
