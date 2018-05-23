package ru.unturn.iksa.volountersevent;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Iksa on 22.05.2018.
 */

public class News implements Parcelable{
    public int id;
    public String header;
    public String shortNews;
    public String fullNews;
    public String linkImage;
    public String date;

    News(int id, String header, String shortVersion, String fullNews, String linkImage, String date){
        this.id = id;
        this.header = header;
        this.shortNews = shortVersion;
        this.linkImage = linkImage;
        this.fullNews = fullNews;
        this.date = date;
    }

    public int describeContents() {
        return 0;
    }

    // упаковываем объект в Parcel
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(header);
        parcel.writeString(shortNews);
        parcel.writeString(fullNews);
        parcel.writeString(linkImage);
        parcel.writeString(date);
    }

    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        public News[] newArray(int size) {
            return new News[size];
        }
    };

    // конструктор, считывающий данные из Parcel
    private News(Parcel parcel) {
        id = parcel.readInt();
        header = parcel.readString();
        shortNews = parcel.readString();
        fullNews = parcel.readString();
        linkImage = parcel.readString();
        date = parcel.readString();
    }
}
