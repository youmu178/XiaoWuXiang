package com.youzh.xiaowuxiang;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;

import com.chad.library.adapter.base.BaseQuickAdapter;

import butterknife.BindView;

/**
 * Created by youzehong on 16/6/14.
 */
public abstract class BaseRefreshActivity<T> extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnRecyclerViewItemClickListener {

    @Nullable
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Nullable
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private int mPageNum = 1;
    private int mOldPageNum = 1;

    @Override
    protected int onLayoutId() {
        return R.layout.layout_list;
    }

    @Override
    protected void onLayoutCreate() {

    }

    protected abstract void onLoadData(int pageNum);

    protected void initLoad(BaseQuickAdapter<T> adapter) {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mSwipeRefreshLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        adapter.setOnLoadMoreListener(this);
        adapter.openLoadMore(Constant.PAGE_SIZE, true);
        mRecyclerView.setAdapter(adapter);
        loadFirstPage();
    }

    public void loadFirstPage() {
        mOldPageNum = 1;
        mPageNum = 1;
        onLoadData(mPageNum);
    }

    public void loadNextPage() {
        mOldPageNum = mPageNum;
        mPageNum++;
        onLoadData(mPageNum);
    }

    public void restorePageNum() {
        mPageNum = mOldPageNum;
    }

    public void loadFinish() {
        if (mSwipeRefreshLayout != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }, 500);
        }
    }

    @Override
    public void onRefresh() {
        loadFirstPage();
    }

    @Override
    public void onLoadMoreRequested() {
        loadNextPage();
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
