package com.tally.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class TallyUsers implements Parcelable {
    
    //用户ID
    private int mTallyUserId;
    //用户名称
    private String mTallyUserName;
    //用户创建时间
    private String mTallyUserCreateTime;
    

    public int getmTallyUserId() {
        return mTallyUserId;
    }

    public void setmTallyUserId(int mTallyUserId) {
        this.mTallyUserId = mTallyUserId;
    }

    public String getmTallyUserName() {
        return mTallyUserName;
    }

    public void setmTallyUserName(String mTallyUserName) {
        this.mTallyUserName = mTallyUserName;
    }

    public String getmTallyUserCreateTime() {
        return mTallyUserCreateTime;
    }

    public void setmTallyUserCreateTime(String mTallyUserCreateTime) {
        this.mTallyUserCreateTime = mTallyUserCreateTime;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub

    }
    
    private static final Parcelable.Creator<TallyUsers> CREATOR = new Parcelable.Creator<TallyUsers>() {

        @Override
        public TallyUsers createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public TallyUsers[] newArray(int size) {
            // TODO Auto-generated method stub
            return new TallyUsers[size];
        }
        
    };

}
