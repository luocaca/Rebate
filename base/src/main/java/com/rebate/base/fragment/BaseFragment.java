package com.rebate.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;

/**
 * 基础 fragment
 * <p>
 * 封装链接 参考
 * https://blog.csdn.net/qq_16318981/article/details/53926906
 */
public abstract class BaseFragment extends Fragment {


    public Activity mActivity;

    public View mView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutId = bindFragmentLayoutId();
        if (layoutId != 0) {
            return inflater.inflate(layoutId, container, false);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    protected abstract int bindFragmentLayoutId();//获取布局xml


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mView = view;
        initViewsAndEvents(view);
    }

    protected abstract void initViewsAndEvents(View view);//初始化控件，如果使用butterknife就不需要写findViewById；


    /**
     * 封装跳转
     */
    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivityForResult(intent, requestCode);
    }

    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


}
