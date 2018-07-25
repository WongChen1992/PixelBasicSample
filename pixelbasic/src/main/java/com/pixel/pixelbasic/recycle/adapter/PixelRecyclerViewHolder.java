package com.pixel.pixelbasic.recycle.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by wongchen on 2017/12/3.
 */

public class PixelRecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> views = new SparseArray<>();

    public PixelRecyclerViewHolder(View itemView) {
        super(itemView);
    }

    public  <T extends View> T getView(int id) {
        View view = views.get(id);
        if (view == null) {
            view = this.itemView.findViewById(id);
            if (view != null) {
                views.put(view.getId(), view);
            }
        }
        return (T) view;
    }

    public void setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
    }

    public void setText(int viewId, int resId) {
        TextView tv = getView(viewId);
        tv.setText(resId);
    }

    public void setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
    }

    public void setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
    }

    public void setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
    }

    public void setImage(int viewId, int resId){
        ImageView view = getView(viewId);
        view.setImageResource(resId);
    }

//    public void setImage(int viewId, String url){
//        SimpleDraweeView view = getView(viewId);
//        view.setImageURI(Uri.parse(url));
//    }

    public void setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
    }

    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
    }

    public void setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
    }
}
