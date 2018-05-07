package com.demo.loader.demoloader.applist;

import com.demo.loader.demoloader.base.BaseListAdapter;

public class ApkListAdapter extends BaseListAdapter<ApkEntity> {

    @Override
    protected void userBindViewHolder(BaseListAdapter.DataViewHolder holder, int position) {
        holder.tv.setText(mData.get(position).label);
    }
}
