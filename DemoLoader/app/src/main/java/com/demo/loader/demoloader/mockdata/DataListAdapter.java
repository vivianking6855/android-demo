package com.demo.loader.demoloader.mockdata;

import com.demo.loader.demoloader.base.BaseListAdapter;

public class DataListAdapter extends BaseListAdapter<MockEntity> {

    @Override
    protected void userBindViewHolder(BaseListAdapter.DataViewHolder holder, int position) {
        holder.tv.setText(mData.get(position).name);
    }
}
