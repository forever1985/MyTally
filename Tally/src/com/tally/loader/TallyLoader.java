package com.tally.loader;

import java.util.ArrayList;
import java.util.List;

import com.tally.entities.Tallies;
import com.tally.provider.TalliesContentProvider.CostTypesColumns;
import com.tally.provider.TalliesContentProvider.InComeTypesColumns;
import com.tally.provider.TalliesContentProvider.TalliesColumns;
import com.tally.provider.TalliesContentProvider.TallyTypesColumns;
import com.tally.provider.TalliesContentProvider.TallyUserColumns;
import com.tally.provider.TalliesContentProvider.TallyViewColumns;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;

public class TallyLoader extends AsyncTaskLoader<List<Tallies>>{

    private List<Tallies> mTalliesList;
    private String mSelection;
    
    public TallyLoader(Context context,String mSelection) {
        super(context);
        this.mSelection = mSelection;
    }

    @Override
    public List<Tallies> loadInBackground() {
        Cursor cursor = null;
        List<Tallies> list = new ArrayList<Tallies>();
        try {
            cursor = getContext().getContentResolver().query(TallyViewColumns.CONTENT_URI, null, mSelection, null, null);
            while (cursor.moveToNext()) {
                Tallies tally = new Tallies(
                        cursor.getInt(cursor.getColumnIndex(TalliesColumns.TALLIES_ID)), 
                        cursor.getInt(cursor.getColumnIndex(TalliesColumns.TALLY_USER_ID)),
                        cursor.getInt(cursor.getColumnIndex(TalliesColumns.TYPE_ID)),
                        cursor.getInt(cursor.getColumnIndex(TalliesColumns.COST_TYPE_ID)),
                        cursor.getInt(cursor.getColumnIndex(TalliesColumns.INCOME_TYPE_ID)),
                        cursor.getString(cursor.getColumnIndex(TalliesColumns.TALLIES_YEAR)), 
                        cursor.getString(cursor.getColumnIndex(TalliesColumns.TALLIES_MONTH)), 
                        cursor.getString(cursor.getColumnIndex(TalliesColumns.TALLIES_DAY)), 
                        cursor.getString(cursor.getColumnIndex(TalliesColumns.CREATE_TIME)), 
                        cursor.getString(cursor.getColumnIndex(TalliesColumns.EXPENDITURE)), 
                        cursor.getString(cursor.getColumnIndex(TalliesColumns.INCOME)), 
                        cursor.getString(cursor.getColumnIndex(TalliesColumns.REMARKS)),
                        cursor.getString(cursor.getColumnIndex(TallyUserColumns.TALLY_USER_NAME)), 
                        cursor.getString(cursor.getColumnIndex(TallyTypesColumns.TYPE_NAME)), 
                        cursor.getString(cursor.getColumnIndex(CostTypesColumns.COST_TYPE_NAME)), 
                        cursor.getString(cursor.getColumnIndex(InComeTypesColumns.INCOME_TYPE_NAME)));
                list.add(tally);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != cursor) {
                cursor.close();
            }
        }
        return list;
    }

    @Override
    public void onCanceled(List<Tallies> data) {
        // TODO Auto-generated method stub
        super.onCanceled(data);
    }

    @Override
    public void deliverResult(List<Tallies> data) {
        mTalliesList = data;
        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onReset() {
        onStopLoading();
        if (null != mTalliesList) {
            mTalliesList.clear();
            mTalliesList = null;
        }
    }

    @Override
    protected void onStartLoading() {
        if (null != mTalliesList) {
            deliverResult(mTalliesList);
        } else {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

}
