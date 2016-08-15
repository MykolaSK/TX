package com.txui.core.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.txui.core.activities.BaseAbstractActivity;
import com.txui.core.presenter.AbstractPresenter;

/**
 * Created by mykolakoshurenko on 8/15/16.
 */
public abstract class BaseAbstractFragment extends Fragment {
    private boolean isViewCreated;
    private AbstractPresenter.PView mView;

    public abstract BaseAbstractFragment getNewInstance(Bundle bundle);

    public AbstractPresenter.PView getPView() {
        if(!isViewCreated) throw new RuntimeException("Not yet. Wait until view created");
        if (null != mView) {
            mView = new AbstractPresenter.PView((BaseAbstractActivity) getActivity(), this);
        }
        return mView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStop() {
        super.onStop();
        isViewCreated = false;
    }

    @Override
    public void onDestroyView() {
        isViewCreated = false;
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
    }
}
