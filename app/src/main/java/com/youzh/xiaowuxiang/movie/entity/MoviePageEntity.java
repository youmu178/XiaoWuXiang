package com.youzh.xiaowuxiang.movie.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by youzehong on 16/6/13.
 */
public class MoviePageEntity implements Parcelable {
    // 页码
    private String mPage;
    // 最大页码
    private String mPageMax;

    public String getmPage() {
        return mPage;
    }

    public void setmPage(String mPage) {
        this.mPage = mPage;
    }

    public String getmPageMax() {
        return mPageMax;
    }

    public void setmPageMax(String mPageMax) {
        this.mPageMax = mPageMax;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mPage);
        dest.writeString(this.mPageMax);
    }

    public MoviePageEntity() {
    }

    protected MoviePageEntity(Parcel in) {
        this.mPage = in.readString();
        this.mPageMax = in.readString();
    }

    public static final Parcelable.Creator<MoviePageEntity> CREATOR = new Parcelable.Creator<MoviePageEntity>() {
        @Override
        public MoviePageEntity createFromParcel(Parcel source) {
            return new MoviePageEntity(source);
        }

        @Override
        public MoviePageEntity[] newArray(int size) {
            return new MoviePageEntity[size];
        }
    };
}
