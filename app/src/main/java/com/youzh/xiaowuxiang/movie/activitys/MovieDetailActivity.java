package com.youzh.xiaowuxiang.movie.activitys;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youzh.xiaowuxiang.BaseActivity;
import com.youzh.xiaowuxiang.Constant;
import com.youzh.xiaowuxiang.R;
import com.youzh.xiaowuxiang.movie.component.DaggerMovieDetailComponent;
import com.youzh.xiaowuxiang.movie.entity.MovieDetailEntity;
import com.youzh.xiaowuxiang.movie.module.MovieDetailModule;
import com.youzh.xiaowuxiang.movie.presenter.MovieDetailContract;
import com.youzh.xiaowuxiang.movie.presenter.MovieDetailPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by youzehong on 16/6/16.
 */
public class MovieDetailActivity extends BaseActivity implements MovieDetailContract.View {

    @BindView(R.id.iv_cover)
    ImageView mIvCover;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_author)
    TextView mTvAuthor;
    @BindView(R.id.tv_actor)
    TextView mTvActor;
    @BindView(R.id.tv_introduce)
    TextView mTvIntroduce;
    @BindView(R.id.ll_photo)
    LinearLayout mLayoutPhoto;
    @Inject
    MovieDetailPresenter mPresenter;
    private String mUrl;

    public static void start(Context context, String url) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(Constant.EXTRA_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected int onLayoutId() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void onLayoutCreate() {

        DaggerMovieDetailComponent.builder()
                .movieDetailModule(new MovieDetailModule(mContext, this))
                .build()
                .inject(this);
//        mPresenter = new MovieDetailPresenter(mContext, this);

        mUrl = getIntent().getStringExtra(Constant.EXTRA_URL);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.load(mUrl);
    }

    @Override
    public void onMovieDetailGet(MovieDetailEntity movieDetailEntity) {
        Glide.with(mContext).load(movieDetailEntity.getCover()).into(mIvCover);
        mTvTitle.setText(movieDetailEntity.getTitle());
        mTvAuthor.setText(movieDetailEntity.getAuthor());
        mTvActor.setText(movieDetailEntity.getActor());
        mTvIntroduce.setText(movieDetailEntity.getIntroduce());
        List<String> photoList = movieDetailEntity.getPhotoList();
        if (!photoList.isEmpty()) {
            for (String imgUrl : photoList) {
                ImageView imageView = new ImageView(mContext);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                Glide.with(mContext).load(imgUrl).into(imageView);
                mLayoutPhoto.addView(imageView);
            }
        }
    }
}
