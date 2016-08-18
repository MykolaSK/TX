package com.txui.core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.txui.core.fragments.BaseAbstractFragment;
import com.txui.core.presenter.AbstractPresenter;

/**
 * Created by mykolakoshurenko on 8/12/16.
 */
public class BaseAbstractActivity<P extends AbstractPresenter> extends AppCompatActivity {
    protected P mPresenter;
    private AbstractPresenter.PView<BaseAbstractActivity, BaseAbstractFragment> mView;
    private boolean isViewCreated;

    public AbstractPresenter.PView getPView() {
        if (!isViewCreated) throw new RuntimeException("Not yet. Wait until view created");
        if (null != mView) {
            mView = new AbstractPresenter.PView<>(this, null);
        }
        return mView;
    }

    public P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isViewCreated = true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onBindView(getPView());
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onUnbindView(getPView());
        isViewCreated = false;
    }
}
