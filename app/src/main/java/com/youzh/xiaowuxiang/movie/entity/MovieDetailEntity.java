package com.youzh.xiaowuxiang.movie.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by youzehong on 16/6/16.
 */
public class MovieDetailEntity implements Parcelable {

    private String cover;
    private String title;
    private String author;
    private String actor;
    private String introduce;
    private List<String> photoList;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public List<String> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<String> photoList) {
        this.photoList = photoList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cover);
        dest.writeString(this.title);
        dest.writeString(this.author);
        dest.writeString(this.actor);
        dest.writeString(this.introduce);
        dest.writeStringList(this.photoList);
    }

    public MovieDetailEntity() {
    }

    protected MovieDetailEntity(Parcel in) {
        this.cover = in.readString();
        this.title = in.readString();
        this.author = in.readString();
        this.actor = in.readString();
        this.introduce = in.readString();
        this.photoList = in.createStringArrayList();
    }

    public static final Parcelable.Creator<MovieDetailEntity> CREATOR = new Parcelable.Creator<MovieDetailEntity>() {
        @Override
        public MovieDetailEntity createFromParcel(Parcel source) {
            return new MovieDetailEntity(source);
        }

        @Override
        public MovieDetailEntity[] newArray(int size) {
            return new MovieDetailEntity[size];
        }
    };
}
