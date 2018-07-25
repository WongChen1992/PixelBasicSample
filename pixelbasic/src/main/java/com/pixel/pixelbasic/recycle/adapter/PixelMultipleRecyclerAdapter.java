package com.pixel.pixelbasic.recycle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wongchen on 2017/12/12.
 */

public class PixelMultipleRecyclerAdapter<T> extends PixelBaseRecyclerAdapter<T> {
    /**
     * 反射类型字段名称
     */
    private String typeText;
    private Class clazz;

    private AdapterMap adapterMap = new AdapterMap();

    public PixelMultipleRecyclerAdapter(Context context, List<T> data, String typeText, Class clazz) {
        mContext = context;
        mData = data;
        this.typeText = typeText;
        this.clazz = clazz;
    }

    @Override
    public PixelRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(adapterMap.getLayout(viewType), parent, false);
        PixelRecyclerViewHolder holder = new PixelRecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PixelRecyclerViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        try {
            Field typeField = clazz.getDeclaredField(typeText);
            typeField.setAccessible(true);
            Object value = typeField.get(mData.get(position));
            PixelMultipleRecyclerAdapter.ViewHolderFactory<T> factory = adapterMap.getFactory(value.toString());
            factory.create(holder, position, mData.get(position));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemViewType(int position) {
        try {
//            ParameterizedType parameterizedType = (ParameterizedType)this.getClass().getGenericSuperclass();
//            Class clazz = (Class<T>)parameterizedType.getActualTypeArguments()[0];


//            Type parameterizedType = this.getClass().getGenericSuperclass();
//            Type type = ((ParameterizedType) parameterizedType).getActualTypeArguments()[0];
//            Class clazz = type.getClass();

            Field typeField = clazz.getDeclaredField(typeText);
            typeField.setAccessible(true);
            Object value = typeField.get(mData.get(position));
            return adapterMap.getType(value.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public interface ViewHolderFactory<T> {
        void create(PixelRecyclerViewHolder holder, int position, T data);
    }


    public void putAdapterMap(Integer[] layouts, ViewHolderFactory<T>[] viewHolderFactories, String[] typeNames) {
        if (layouts.length == viewHolderFactories.length && viewHolderFactories.length == typeNames.length) {
            for (int i = 0; i < layouts.length; i++) {
                adapterMap.putMap(layouts[i], viewHolderFactories[i], typeNames[i]);
            }
        } else {
            throw new RuntimeException("布局数量，工厂数量，类型数量不一致");
        }

    }

    private class AdapterMap {
        List<Integer> layouts = new ArrayList<>();
        List<ViewHolderFactory<T>> factories = new ArrayList<>();
        List<String> typeNames = new ArrayList<>();

        private int getLayout(int i) {
            return layouts.get(i);
        }

        private int getType(String typeName) {
            return typeNames.indexOf(typeName);
        }

        private ViewHolderFactory<T> getFactory(String typeName) {
            return factories.get(getType(typeName));
        }

        private void putMap(int layout, ViewHolderFactory<T> factory, String typeName) {
            layouts.add(layout);
            factories.add(factory);
            typeNames.add(typeName);
        }
    }
}
