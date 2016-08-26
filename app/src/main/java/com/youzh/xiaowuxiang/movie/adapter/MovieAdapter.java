package com.youzh.xiaowuxiang.movie.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.youzh.xiaowuxiang.movie.entity.MovieEntity;
import com.youzh.xiaowuxiang.R;

import java.util.List;

/**
 * Created by youzehong on 16/6/13.
 */
public class MovieAdapter extends BaseQuickAdapter<MovieEntity> {
    public MovieAdapter(Context context, List<MovieEntity> data) {
        super(context, R.layout.list_movie, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieEntity movieEntity) {
        helper.setText(R.id.tv_title, movieEntity.getmTitle())
                .setText(R.id.tv_content, movieEntity.getmContent());
        Glide.with(mContext).load(movieEntity.getmUrlImg()).crossFade().into((ImageView) helper.getView(R.id.imageView));
    }
}
