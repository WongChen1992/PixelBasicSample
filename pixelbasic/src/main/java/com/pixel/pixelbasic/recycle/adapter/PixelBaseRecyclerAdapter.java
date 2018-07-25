package com.pixel.pixelbasic.recycle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wongchen on 2017/12/15.
 */

public class PixelBaseRecyclerAdapter<T> extends RecyclerView.Adapter<PixelRecyclerViewHolder> implements View.OnClickListener{
    protected Context mContext;
    protected List<T> mData;
    protected int mItemLayoutId;

    @Override
    public PixelRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mItemLayoutId, parent, false);
        PixelRecyclerViewHolder holder = new PixelRecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PixelRecyclerViewHolder holder, int position) {
        holder.itemView.setOnClickListener(this);
        Map<String, Object> data = new HashMap<>();
        data.put("data", mData.get(position));
        data.put("position", position);
        holder.itemView.setTag(data);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnRecyclerViewItemClickListener != null) {
            mOnRecyclerViewItemClickListener.onItemClick((Integer)((HashMap) v.getTag()).get("position"), (T)((HashMap) v.getTag()).get("data"));
        }
    }

    private PixelRecyclerAdapter.OnRecyclerViewItemClickListener<T> mOnRecyclerViewItemClickListener;

    public void setOnRecyclerViewItemClickListener(PixelRecyclerAdapter.OnRecyclerViewItemClickListener<T> listener) {
        mOnRecyclerViewItemClickListener = listener;
    }

    public interface OnRecyclerViewItemClickListener<T> {
        void onItemClick(int position, T data);
    }

    public void clearAllItem() {
        mData.clear();
        this.notifyDataSetChanged();
    }

    public void addAllItem(List<T> entity) {
        mData.addAll(entity);
        this.notifyItemRangeChanged(mData.size() - entity.size() - 1, entity.size());
    }

    public void addItem(T entity) {
        mData.add(entity);
        notifyItemChanged(mData.size() - 1);
    }

    public void addItem(T entity, int position) {
        mData.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }
}
