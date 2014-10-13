package com.tally.fragment;

import java.util.List;

import android.app.Fragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tally.entities.Tallies;
import com.tally.loader.TallyLoader;
import com.tally.provider.TalliesContentProvider.TalliesColumns;
import com.tally.tally.R;
import com.tally.utils.TallyDateUtils;

public class DayFragment extends Fragment implements LoaderCallbacks<List<Tallies>>, OnItemClickListener{

    private static final String QUERY_DAY_SELECTION = TalliesColumns.TALLIES_DAY + " = '" + TallyDateUtils.getCurrentDay() + "'";
    private static final int LOADER_ID = 1000;
    
    private DayAdapter mDayAdapter;
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDayAdapter = new DayAdapter(getActivity());
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day_fragment, container, false);
        ListView listview = (ListView) view.findViewById(R.id.list_day);
        listview.setAdapter(mDayAdapter);
        listview.setOnItemClickListener(this);
        return view;
    }


    @Override
    public Loader<List<Tallies>> onCreateLoader(int id,
            Bundle args) {
        return new TallyLoader(getActivity(), QUERY_DAY_SELECTION);
    }

    @Override
    public void onLoadFinished(Loader<List<Tallies>> arg0,
            List<Tallies> list) {
        mDayAdapter.setData(list);
    }

    @Override
    public void onLoaderReset(Loader<List<Tallies>> arg0) {
        mDayAdapter.setData(null);
    }
    
    private class DayAdapter extends ArrayAdapter<Tallies> {
        
        public DayAdapter(Context context) {
            super(context, R.layout.day_listview_item);
        }
        
        public void setData (List<Tallies> list) {
            clear();
            if (null != list) {
                addAll(list);
            }
            
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder mViewHolder = null;
            Tallies tallies = getItem(position);
            if (null == convertView) {
                mViewHolder = new ViewHolder();
                mViewHolder.mLeftTextView = (TextView) convertView.findViewById(R.id.day_left_text);
                mViewHolder.mRightTextView = (TextView) convertView.findViewById(R.id.day_right_text);
                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (ViewHolder) convertView.getTag();
            }
            
            return convertView;
        }
        
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        
    }
    
    class ViewHolder {
        TextView mLeftTextView;
        TextView mRightTextView;
    }
    
}
