package ru.unturn.iksa.volountersevent;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Iksa on 11.03.2018.
 */

public class Event implements Parcelable {
    public int id;
    public String name;
    public String owner;
    public String startOfEvent;
    public String description;
    public String location;

    Event(int id, String eventName, String ownerOfEvent, String location, String startOfEvent, String description){
        this.id = id;
        this.name = eventName;
        this.owner = ownerOfEvent;
        this.location = location;
        this.description = description;
        this.startOfEvent = startOfEvent;
    }

    public int describeContents() {
        return 0;
    }

    // упаковываем объект в Parcel
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(owner);
        parcel.writeString(startOfEvent);
        parcel.writeString(description);
        parcel.writeString(location);
    }

    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    // конструктор, считывающий данные из Parcel
    private Event(Parcel parcel) {
        id = parcel.readInt();
        name = parcel.readString();
        owner = parcel.readString();
        startOfEvent = parcel.readString();
        description = parcel.readString();
        location = parcel.readString();
    }
}
