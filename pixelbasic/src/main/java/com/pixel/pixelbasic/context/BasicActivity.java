package com.pixel.pixelbasic.context;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BasicActivity extends AppCompatActivity {
    private Unbinder mUnbinder;
    protected Context mAppContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        init();
    }

    public abstract int getLayoutId();

    public abstract void init();


    public void openActivity(Class<?> pClass, Intent intent) {
        if (intent == null) {
            intent = new Intent();
        }
        intent.setClass(this, pClass);
        startActivity(intent);
    }

    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    /**
     * 弹出Toast
     *
     * @param str 提示语
     */
    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
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
