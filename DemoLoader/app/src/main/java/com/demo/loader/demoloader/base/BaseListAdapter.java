package com.demo.loader.demoloader.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.loader.demoloader.R;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListAdapter<DT> extends RecyclerView.Adapter<BaseListAdapter.DataViewHolder> {
    protected List<DT> mData;

    public BaseListAdapter() {
    }

    public void setData(List<DT> list) {
        if (list == null) {
            return;
        }
        if (mData == null) {
            mData = new ArrayList<>();
        } else {
            mData.clear();
        }

        mData.addAll(list);
        notifyDataSetChanged();
    }

    public void clearData() {
        if (mData != null) {
            mData.clear();
            mData = null;
        }
    }

    public void addData(List<DT> list) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        DataViewHolder holder = new DataViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseListAdapter.DataViewHolder holder, int position) {
        if (mData == null || position >= mData.size()) {
            return;
        }
        userBindViewHolder(holder, position);
    }

    protected abstract void userBindViewHolder(BaseListAdapter.DataViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    protected class DataViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;

        public DataViewHolder(View view) {
            super(view);
            tv = view.findViewById(android.R.id.title);
        }
    }
}
