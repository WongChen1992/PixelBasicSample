package com.pixel.pixelbasic.context;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BasicFragment extends Fragment {
    protected View mView;
    protected Context mContext;
    protected Context mAppContext;
    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, mView);

        mContext = getActivity();
        mAppContext = mContext.getApplicationContext();
        init();
        return mView;
    }

    public abstract int getLayoutId();

    public abstract void init();

    public void openActivity(Class<?> pClass, Intent intent) {
        if (intent == null) {
            intent = new Intent();
        }
        intent.setClass(mContext, pClass);
        startActivity(intent);
    }

    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    /**
     * 弹出Toast
     *
     * @param str 提示语
     */
    public void showToast(String str) {
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出Toast
     *
     * @param i R文件的id
     */
    public void showToast(int i) {
        String str = getResources().getString(i);
        showToast(str);
    }
}
