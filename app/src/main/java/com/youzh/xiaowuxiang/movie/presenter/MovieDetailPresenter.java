package com.youzh.xiaowuxiang.movie.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.youzh.xiaowuxiang.api.MovieService;
import com.youzh.xiaowuxiang.api.StringResponse;
import com.youzh.xiaowuxiang.movie.entity.MovieDetailEntity;

import javax.inject.Inject;

/**
 * Created by youzehong on 16/6/20.
 */
public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private MovieDetailContract.View mView;
    private Context mContext;

    @Inject
    MovieDetailPresenter(Context context, MovieDetailContract.View view) {
        this.mContext = context;
        this.mView = view;
    }

    @Override
    public void load() {

    }

    @Override
    public void load(String url) {
        if (!TextUtils.isEmpty(url)) {
            new MovieService().getMovieDetail(url, new StringResponse(StringResponse.MOVIE_DETAIL) {
                @Override
                public void onSuccess(MovieDetailEntity movieDetailEntity) {
                    super.onSuccess(movieDetailEntity);
                    mView.onMovieDetailGet(movieDetailEntity);
                }
            });
        }
    }
}
