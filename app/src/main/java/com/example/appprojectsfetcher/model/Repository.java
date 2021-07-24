package com.example.appprojectsfetcher.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Repository implements Parcelable {
    private long total_count;
    private List<Item> items=new ArrayList<>();

    public Repository(long total_count, List<Item> items) {
        this.total_count = total_count;
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    protected Repository(Parcel in) {
        total_count = in.readLong();
        in.readList(items,Item.class.getClassLoader());
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeLong(total_count);

        parcel.writeList(items);

    }
    public long getTotal_count() {
        return total_count;
    }

    public void setTotal_count(long total_count) {
        this.total_count = total_count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Repository> CREATOR = new Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };


}
