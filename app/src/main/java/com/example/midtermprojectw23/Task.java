package com.example.midtermprojectw23;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Task implements Parcelable {

    String task;
    String date;
    boolean isUrgent;

    public Task(String task, String date, boolean isUrgent) {
        this.task = task;
        this.date = date;
        this.isUrgent = isUrgent;
    }

    protected Task(Parcel in) {
        task = in.readString();
        date = in.readString();
        isUrgent = in.readByte() != 0;
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(task);
        parcel.writeString(date);
        parcel.writeByte((byte) (isUrgent ? 1 : 0));
    }
}
