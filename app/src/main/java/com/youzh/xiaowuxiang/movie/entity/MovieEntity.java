package com.youzh.xiaowuxiang.movie.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by youzehong on 16/6/13.
 */
public class MovieEntity implements Parcelable {

    // 图片地址
    private String mUrlImg;
    // 标题
    private String mTitle;
    // 日期
    private String mDate;
    // 详情地址
    private String mUrlDetail;
    // 内容
    private String mContent;

    public String getmUrlImg() {
        return mUrlImg;
    }

    public void setmUrlImg(String mUrlImg) {
        this.mUrlImg = mUrlImg;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmUrlDetail() {
        return mUrlDetail;
    }

    public void setmUrlDetail(String mUrlDetail) {
        this.mUrlDetail = mUrlDetail;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mUrlImg);
        dest.writeString(this.mTitle);
        dest.writeString(this.mDate);
        dest.writeString(this.mUrlDetail);
        dest.writeString(this.mContent);
    }

    public MovieEntity() {
    }

    protected MovieEntity(Parcel in) {
        this.mUrlImg = in.readString();
        this.mTitle = in.readString();
        this.mDate = in.readString();
        this.mUrlDetail = in.readString();
        this.mContent = in.readString();
    }

    public static final Parcelable.Creator<MovieEntity> CREATOR = new Parcelable.Creator<MovieEntity>() {
        @Override
        public MovieEntity createFromParcel(Parcel source) {
            return new MovieEntity(source);
        }

        @Override
        public MovieEntity[] newArray(int size) {
            return new MovieEntity[size];
        }
    };
}
