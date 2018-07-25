package com.pixel.pixelbasic.context;

import com.pixel.pixelbasic.recycle.adapter.PixelRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 带有RecyclerView分页加载功能的基础Activity
 *
 * @param <T> 适配器的数据类型
 */

public abstract class BasicPageContext<T> {
    private int mPage = 0;//网络请求的页码
    private int mCount = 10;//网络请求的数据数量
    private int mTotal = Integer.MAX_VALUE;//接口下的数据总数，默认为int最大值

    private List<T> mData = new ArrayList<>();//适配器数据
    protected PixelRecyclerAdapter<T> mPixelRecyclerAdapter;//Recycler的适配器

    private NoMoreCallBack mNoMoreCallBack;//没有更多数据的回调接口

    protected void setPage(int page) {
        mPage = page;
    }

    protected void setCount(int count) {
        mCount = count;
    }

    protected void setTotal(int total) {
        mTotal = total;
    }

    protected void loadMore() {
        if (mTotal <= mPage * mCount) {
            if (mNoMoreCallBack != null)
                mNoMoreCallBack.noMore();
            return;
        }

        load(mPage, mCount);
        mPage++;

    }

    protected void clean() {
        mPage = 0;
        mData.clear();
        mPixelRecyclerAdapter.notifyDataSetChanged();
    }

    protected void update() {
        clean();
        loadMore();
    }

    protected void uiUpdate(List<T> data) {
        mData.addAll(data);
        mPixelRecyclerAdapter.notifyDataSetChanged();
    }

    public abstract void load(int page, int count);

    public interface NoMoreCallBack {
        void noMore();
    }

    protected void setNoMoreCallBack(NoMoreCallBack noMoreCallBack) {
        mNoMoreCallBack = noMoreCallBack;
    }
}
