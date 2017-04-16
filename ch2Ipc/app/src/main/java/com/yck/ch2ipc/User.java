package com.yck.ch2ipc;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Locale;

/**
 * <p>FileName:User.java </p>
 * <p>Description: User 功能说明</p>
 * <p>Copyright (c) 2016 yuanchuangke All rights reserved.</p>
 *
 * @author GuanJian
 * @version 1.0: by GuanJian on 2016/10/1 23:37
 * @email yuanchuangke@foxmail.com
 */
public class User implements Parcelable, Serializable {
    public int userId;
    public String userName;
    public boolean isMale;

    @Override
    public String toString(){
        return String.format(Locale.US,"userId=%d,userName=%s,isMale=%s",
                userId,userName,isMale );
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(userId);
        out.writeString(userName);
        out.writeInt(isMale ? 1 : 0);
    }

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private User(Parcel in) {
        userId = in.readInt();
    }
    public User() {

    }
}
