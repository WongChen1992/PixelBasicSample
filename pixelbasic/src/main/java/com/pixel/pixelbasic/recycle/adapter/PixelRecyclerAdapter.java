package com.pixel.pixelbasic.recycle.adapter;

import android.content.Context;

import java.util.List;

/**
 * Created by wongchen on 2017/11/30.
 */

public class PixelRecyclerAdapter<T> extends PixelBaseRecyclerAdapter<T> {

    public PixelRecyclerAdapter(Context context, List<T> data, int itemLayoutId) {
        mContext = context;
        mData = data;
        mItemLayoutId = itemLayoutId;
    }

    @Override
    public void onBindViewHolder(PixelRecyclerViewHolder holder, int position) {
        super.onBindViewHolder(holder,position);
        bindViewHolder.onBindViewHolder(holder, position,  mData.get(position));
    }

    private BindViewHolder<T> bindViewHolder;


    public interface BindViewHolder<T> {
        void onBindViewHolder(PixelRecyclerViewHolder holder, int position, T data);
    }

    public void setBindViewHolder(BindViewHolder<T> bindViewHolder) {
        this.bindViewHolder = bindViewHolder;
    }

}
