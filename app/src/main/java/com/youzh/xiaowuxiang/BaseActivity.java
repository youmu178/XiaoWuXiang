package com.youzh.xiaowuxiang;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by youzehong on 16/6/14.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;

    protected abstract int onLayoutId();
    protected abstract void onLayoutCreate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(onLayoutId());
        mContext = this;
        ButterKnife.bind(this);
        onLayoutCreate();
    }
}
