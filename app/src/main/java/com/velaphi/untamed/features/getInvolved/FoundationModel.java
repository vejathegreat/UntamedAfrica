package com.velaphi.untamed.features.getInvolved;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class FoundationModel implements Parcelable {

    public static final Creator<FoundationModel> CREATOR = new Creator<FoundationModel>() {
        @Override
        public FoundationModel createFromParcel(Parcel in) {
            return new FoundationModel(in);
        }

        @Override
        public FoundationModel[] newArray(int size) {
            return new FoundationModel[size];
        }
    };
    private String content;
    private String helpUrl;
    private String image;
    private String mainSite;
    private String name;
    private String tagLine;

    public FoundationModel() {
    }

    public FoundationModel(String content, String helpUrl, String image, String mainSite, String name, String tagLine) {
        this.content = content;
        this.helpUrl = helpUrl;
        this.image = image;
        this.mainSite = mainSite;
        this.name = name;
        this.tagLine = tagLine;
    }

    protected FoundationModel(Parcel in) {
        content = in.readString();
        helpUrl = in.readString();
        image = in.readString();
        mainSite = in.readString();
        name = in.readString();
        tagLine = in.readString();
    }

    public String getContent() {
        return content;
    }

    public String getHelpUrl() {
        return helpUrl;
    }

    public String getImage() {
        return image;
    }

    public String getMainSite() {
        return mainSite;
    }

    public String getName() {
        return name;
    }

    public String getTagLine() {
        return tagLine;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeString(helpUrl);
        dest.writeString(image);
        dest.writeString(mainSite);
        dest.writeString(name);
        dest.writeString(tagLine);
    }
}
