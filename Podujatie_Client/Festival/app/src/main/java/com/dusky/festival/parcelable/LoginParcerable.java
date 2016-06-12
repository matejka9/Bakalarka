package com.dusky.festival.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dusky on 5/24/16.
 */
public class LoginParcerable implements Parcelable {
    private String email;
    private Long id;

    /* everything below here is for implementing Parcelable */

    // 99.9% of the time you can just ignore this
    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(email);
        out.writeLong(id);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<LoginParcerable> CREATOR = new Parcelable.Creator<LoginParcerable>() {
        public LoginParcerable createFromParcel(Parcel in) {
            return new LoginParcerable(in);
        }

        public LoginParcerable[] newArray(int size) {
            return new LoginParcerable[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private LoginParcerable(Parcel in) {
        email = in.readString();
        id = in.readLong();
    }

    public LoginParcerable(String email, Long id){
        this.email = email;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}