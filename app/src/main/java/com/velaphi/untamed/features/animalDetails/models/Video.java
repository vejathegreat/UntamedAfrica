package com.velaphi.untamed.features.animalDetails.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Video implements Parcelable {

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
    private String caption;
    private String source;
    private String url;
    private String type;

    public Video(String caption, String source, String url, String type) {
        this.caption = caption;
        this.source = source;
        this.url = url;
        this.type = type;
    }


    public Video() {
    }

    protected Video(Parcel in) {
        caption = in.readString();
        source = in.readString();
        url = in.readString();
        type = in.readString();
    }

    public String getCaption() {
        return caption;
    }

    public String getSource() {
        return source;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(caption);
        dest.writeString(source);
        dest.writeString(url);
        dest.writeString(type);
    }
}
