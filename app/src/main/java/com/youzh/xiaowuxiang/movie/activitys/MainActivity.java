package com.youzh.xiaowuxiang.movie.activitys;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaomi.market.sdk.XiaomiUpdateAgent;
import com.youzh.xiaowuxiang.Constant;
import com.youzh.xiaowuxiang.R;
import com.youzh.xiaowuxiang.api.MovieService;
import com.youzh.xiaowuxiang.api.StringResponse;
import com.youzh.xiaowuxiang.movie.adapter.MovieAdapter;
import com.youzh.xiaowuxiang.movie.entity.MovieEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
//    @BindView(R.id.fab)
//    FloatingActionButton mFab;
    private List<MovieEntity> mListMovie = new ArrayList<>();
    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        XiaomiUpdateAgent.update(this);
//        mFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        initViews();
    }

    private void initViews() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mMovieAdapter = new MovieAdapter(MainActivity.this, mListMovie);
        mMovieAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mMovieAdapter.setOnLoadMoreListener(this);
        mMovieAdapter.openLoadMore(Constant.PAGE_SIZE, true);
        mMovieAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                MovieEntity item = mMovieAdapter.getItem(i);
                MovieDetailActivity.start(MainActivity.this, item.getmUrlDetail());
                Timber.i(item.getmUrlDetail());
            }
        });
        mRecyclerView.setAdapter(mMovieAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        new MovieService().getMovieList("1", new StringResponse(StringResponse.MOVIE) {
            @Override
            public void onSuccess(List<MovieEntity> list) {
                super.onSuccess(list);
                mMovieAdapter.setNewData(list);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        new MovieService().getMovieList("2", new StringResponse(StringResponse.MOVIE) {
            @Override
            public void onSuccess(List<MovieEntity> list) {
                super.onSuccess(list);
                mMovieAdapter.notifyDataChangedAfterLoadMore(list, true);
            }
        });
    }
}
