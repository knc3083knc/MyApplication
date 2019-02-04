package com.example.windows.myapplication.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DailyAPOD implements Parcelable {

    @SerializedName("copyright") private String copyright;
    @SerializedName("date") private String date;
    @SerializedName("explanation") private String explanation;
    @SerializedName("hdurl") private String hdUrl;
    @SerializedName("media_type") private String mediaType;
    @SerializedName("service_version") private String serviceVersion;
    @SerializedName("title") private String title;
    @SerializedName("url") private String url;

    protected DailyAPOD(Parcel in) {
        copyright = in.readString();
        date = in.readString();
        explanation = in.readString();
        hdUrl = in.readString();
        mediaType = in.readString();
        serviceVersion = in.readString();
        title = in.readString();
        url = in.readString();
    }

    public static final Creator<DailyAPOD> CREATOR = new Creator<DailyAPOD>() {
        @Override
        public DailyAPOD createFromParcel(Parcel in) {
            return new DailyAPOD(in);
        }

        @Override
        public DailyAPOD[] newArray(int size) {
            return new DailyAPOD[size];
        }
    };

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getHdUrl() {
        return hdUrl;
    }

    public void setHdUrl(String hdUrl) {
        this.hdUrl = hdUrl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(copyright);
        dest.writeString(date);
        dest.writeString(explanation);
        dest.writeString(hdUrl);
        dest.writeString(mediaType);
        dest.writeString(serviceVersion);
        dest.writeString(title);
        dest.writeString(url);
    }
}
