package com.tally.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.tally.log.TallyLog;
import com.tally.provider.TalliesContentProvider.CostTypesColumns;
import com.tally.tally.R;
import com.tally.fragment.DayFragment;
import com.tally.fragment.MonthFragment;
import com.tally.fragment.YearFragment;
import com.tally.utils.TallyDateUtils;

public class TallyActivity extends Activity implements OnPageChangeListener{
    
    private TallyViewPager mTallyViewPager;
    
    private TallyViewPagerAdapter mTallyViewPagerAdapter;
    
    private DayFragment mDayFragment;
    
    private MonthFragment mMonthFragment;
    
    private YearFragment mYearFragment;
    
    private TextView mDayTextView;
    private TextView mMonthTextView;
    private TextView mYearTextView;
    
    private static final TabState DEFAULT_TAB = TabState.DAY;
    
    public enum TabState {
        DAY,MONTH,YEAR;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mDayTextView = (TextView) findViewById(R.id.main_day);
        mDayTextView.setText(TallyDateUtils.getCurrentDay());
        mDayTextView.setTag(TabState.DAY);
        mDayTextView.setOnClickListener(mOnClickListener);
        mDayTextView.setActivated(true);
        
        mMonthTextView = (TextView) findViewById(R.id.main_month);
        mMonthTextView.setText(TallyDateUtils.getCurrentMonth());
        mMonthTextView.setTag(TabState.MONTH);
        mMonthTextView.setOnClickListener(mOnClickListener);
        
        mYearTextView = (TextView) findViewById(R.id.main_year);
        mYearTextView.setText(TallyDateUtils.getCurrentYear());
        mYearTextView.setTag(TabState.YEAR);
        mYearTextView.setOnClickListener(mOnClickListener);
        
        mTallyViewPager = (TallyViewPager) findViewById(R.id.pager);
        mTallyViewPagerAdapter = new TallyViewPagerAdapter(getFragmentManager());
        mTallyViewPager.setAdapter(mTallyViewPagerAdapter);
        mTallyViewPager.setOnPageChangeListener(this);
        mTallyViewPager.setCurrentItem(DEFAULT_TAB.ordinal());
        creatFragment();
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(CostTypesColumns.CONTENT_URI, null, null, null, null);
            while (cursor.moveToNext()) {
                TallyLog.i(cursor.getString(cursor.getColumnIndex(CostTypesColumns.COST_TYPE_NAME)));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != cursor) cursor.close();
        }
    }

    @Override
    public void onPageScrollStateChanged(int position) {
    }

    @Override
    public void onPageScrolled(int position, float arg1, int arg2) {
    }

    @Override
    public void onPageSelected(int position) {  
        mTallyViewPager.setCurrentItem(position);
        if (position == TabState.DAY.ordinal()) {
            mDayTextView.setActivated(true);
            mMonthTextView.setActivated(false);
            mYearTextView.setActivated(false);
            return;
        }
        if (position == TabState.MONTH.ordinal()) {
            mDayTextView.setActivated(false);
            mMonthTextView.setActivated(true);
            mYearTextView.setActivated(false);
            return;
        }
        if (position == TabState.YEAR.ordinal()) {
            mDayTextView.setActivated(false);
            mMonthTextView.setActivated(false);
            mYearTextView.setActivated(true);
            return;
        }
        throw new IllegalArgumentException("position: " + position);  
    }
    
    private void creatFragment () {
        mDayFragment = new DayFragment();
        mMonthFragment = new MonthFragment();
        mYearFragment = new YearFragment();
    }
    
    
    public class TallyViewPagerAdapter extends FragmentPagerAdapter {

        public TallyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == TabState.DAY.ordinal()) {
                return mDayFragment;
            }
            if (position == TabState.MONTH.ordinal()) {
                return mMonthFragment;
            }
            if (position == TabState.YEAR.ordinal()) {
                return mYearFragment;
            }
            throw new IllegalArgumentException("position: " + position);  
        }

        @Override
        public int getItemPosition(Object object) {
            if (object == mDayFragment) {
                return TabState.DAY.ordinal();
            }
            if (object == mMonthFragment) {
                return TabState.MONTH.ordinal();
            }
            if (object == mYearFragment) {
                return TabState.YEAR.ordinal();
            }
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return TabState.values().length;
        }
    }
    
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            case R.id.main_day:
                mTallyViewPager.setCurrentItem(((TabState)v.getTag()).DAY.ordinal());
                break;
            case R.id.main_month:
                mTallyViewPager.setCurrentItem(((TabState)v.getTag()).MONTH.ordinal());
                break;
            case R.id.main_year:
                mTallyViewPager.setCurrentItem(((TabState)v.getTag()).YEAR.ordinal());
                break;

            default:
                break;
            }
        }
    };
    
}
