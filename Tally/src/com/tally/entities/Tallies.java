package com.tally.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Tallies implements Parcelable {
    //账目ID
    private int mTalliesId;
    //用户ID
    private int mTallyUsersId;
    //账目类型ID
    private int mTypesId;
    //花费类型ID
    private int mTalliesCostTypesId;
    //收入类型ID
    private int mTalliesIncomeTypesId;
    //账目年
    private String mTalliesYears;
    //账目月
    private String mTalliesMonths;
    //账目日
    private String mTalliesDays;
    //账目创建时间
    private String mTalliesCreateTimes;
    //支出
    private String mTalliesExpenditure;
    //收入
    private String mTalliesIncome;
    //备注
    private String mRemarks;
    
    private String mTallyUser;
    private String mTypes;
    private String mCostTypes;
    private String mIncomeTypes;

    public Tallies(int mTalliesId, int mTallyUsersId, int mTypesId,
            int mTalliesCostTypesId, int mTalliesIncomeTypesId,
            String mTalliesYears, String mTalliesMonths, String mTalliesDays,
            String mTalliesCreateTimes, String mTalliesExpenditure,
            String mTalliesIncome, String mRemarks) {
        super();
        this.mTalliesId = mTalliesId;
        this.mTallyUsersId = mTallyUsersId;
        this.mTypesId = mTypesId;
        this.mTalliesCostTypesId = mTalliesCostTypesId;
        this.mTalliesIncomeTypesId = mTalliesIncomeTypesId;
        this.mTalliesYears = mTalliesYears;
        this.mTalliesMonths = mTalliesMonths;
        this.mTalliesDays = mTalliesDays;
        this.mTalliesCreateTimes = mTalliesCreateTimes;
        this.mTalliesExpenditure = mTalliesExpenditure;
        this.mTalliesIncome = mTalliesIncome;
        this.mRemarks = mRemarks;
    }

    public Tallies(int mTalliesId, String mTalliesYears, String mTalliesMonths,
            String mTalliesDays, String mTalliesCreateTimes,
            String mTalliesExpenditure, String mTalliesIncome, String mRemarks,
            String mTallyUser, String mTypes, String mCostTypes,
            String mIncomeTypes) {
        super();
        this.mTalliesId = mTalliesId;
        this.mTalliesYears = mTalliesYears;
        this.mTalliesMonths = mTalliesMonths;
        this.mTalliesDays = mTalliesDays;
        this.mTalliesCreateTimes = mTalliesCreateTimes;
        this.mTalliesExpenditure = mTalliesExpenditure;
        this.mTalliesIncome = mTalliesIncome;
        this.mRemarks = mRemarks;
        this.mTallyUser = mTallyUser;
        this.mTypes = mTypes;
        this.mCostTypes = mCostTypes;
        this.mIncomeTypes = mIncomeTypes;
    }

    public Tallies(int mTalliesId, int mTallyUsersId, int mTypesId,
            int mTalliesCostTypesId, int mTalliesIncomeTypesId,
            String mTalliesYears, String mTalliesMonths, String mTalliesDays,
            String mTalliesCreateTimes, String mTalliesExpenditure,
            String mTalliesIncome, String mRemarks, String mTallyUser,
            String mTypes, String mCostTypes, String mIncomeTypes) {
        super();
        this.mTalliesId = mTalliesId;
        this.mTallyUsersId = mTallyUsersId;
        this.mTypesId = mTypesId;
        this.mTalliesCostTypesId = mTalliesCostTypesId;
        this.mTalliesIncomeTypesId = mTalliesIncomeTypesId;
        this.mTalliesYears = mTalliesYears;
        this.mTalliesMonths = mTalliesMonths;
        this.mTalliesDays = mTalliesDays;
        this.mTalliesCreateTimes = mTalliesCreateTimes;
        this.mTalliesExpenditure = mTalliesExpenditure;
        this.mTalliesIncome = mTalliesIncome;
        this.mRemarks = mRemarks;
        this.mTallyUser = mTallyUser;
        this.mTypes = mTypes;
        this.mCostTypes = mCostTypes;
        this.mIncomeTypes = mIncomeTypes;
    }

    public int getmTalliesId() {
        return mTalliesId;
    }

    public void setmTalliesId(int mTalliesId) {
        this.mTalliesId = mTalliesId;
    }

    public int getmTallyUsersId() {
        return mTallyUsersId;
    }

    public void setmTallyUsersId(int mTallyUsersId) {
        this.mTallyUsersId = mTallyUsersId;
    }

    public int getmTypesId() {
        return mTypesId;
    }

    public void setmTypesId(int mTypesId) {
        this.mTypesId = mTypesId;
    }

    public int getmTalliesCostTypesId() {
        return mTalliesCostTypesId;
    }

    public void setmTalliesCostTypesId(int mTalliesCostTypesId) {
        this.mTalliesCostTypesId = mTalliesCostTypesId;
    }

    public int getmTalliesIncomeTypesId() {
        return mTalliesIncomeTypesId;
    }

    public void setmTalliesIncomeTypesId(int mTalliesIncomeTypesId) {
        this.mTalliesIncomeTypesId = mTalliesIncomeTypesId;
    }

    public String getmTalliesYears() {
        return mTalliesYears;
    }

    public void setmTalliesYears(String mTalliesYears) {
        this.mTalliesYears = mTalliesYears;
    }

    public String getmTalliesMonths() {
        return mTalliesMonths;
    }

    public void setmTalliesMonths(String mTalliesMonths) {
        this.mTalliesMonths = mTalliesMonths;
    }

    public String getmTalliesDays() {
        return mTalliesDays;
    }

    public void setmTalliesDays(String mTalliesDays) {
        this.mTalliesDays = mTalliesDays;
    }

    public String getmTalliesCreateTimes() {
        return mTalliesCreateTimes;
    }

    public void setmTalliesCreateTimes(String mTalliesCreateTimes) {
        this.mTalliesCreateTimes = mTalliesCreateTimes;
    }

    public String getmTalliesExpenditure() {
        return mTalliesExpenditure;
    }

    public void setmTalliesExpenditure(String mTalliesExpenditure) {
        this.mTalliesExpenditure = mTalliesExpenditure;
    }

    public String getmTalliesIncome() {
        return mTalliesIncome;
    }

    public void setmTalliesIncome(String mTalliesIncome) {
        this.mTalliesIncome = mTalliesIncome;
    }

    public String getmRemarks() {
        return mRemarks;
    }

    public void setmRemarks(String mRemarks) {
        this.mRemarks = mRemarks;
    }

    public String getmTallyUser() {
        return mTallyUser;
    }

    public void setmTallyUser(String mTallyUser) {
        this.mTallyUser = mTallyUser;
    }

    public String getmTypes() {
        return mTypes;
    }

    public void setmTypes(String mTypes) {
        this.mTypes = mTypes;
    }

    public String getmCostTypes() {
        return mCostTypes;
    }

    public void setmCostTypes(String mCostTypes) {
        this.mCostTypes = mCostTypes;
    }

    public String getmIncomeTypes() {
        return mIncomeTypes;
    }

    public void setmIncomeTypes(String mIncomeTypes) {
        this.mIncomeTypes = mIncomeTypes;
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

    private static final Parcelable.Creator<Tallies> CREATOR = new Parcelable.Creator<Tallies>() {

        @Override
        public Tallies createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Tallies[] newArray(int size) {
            // TODO Auto-generated method stub
            return new Tallies[size];
        }
    };
    
}
