package com.tx.core.entities;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by mykolakoshurenko on 8/10/16.
 */
public class BaseEntity implements Parcelable {

    public String id;

    public BaseEntity() {
    }

    public BaseEntity(String id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
    }

    protected BaseEntity(Parcel in) {
        this.id = in.readString();
    }

    public static final Creator<BaseEntity> CREATOR = new Creator<BaseEntity>() {
        @Override
        public BaseEntity createFromParcel(Parcel source) {
            return new BaseEntity(source);
        }

        @Override
        public BaseEntity[] newArray(int size) {
            return new BaseEntity[size];
        }
    };
}
