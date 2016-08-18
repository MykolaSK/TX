package com.txui.core.fragments;

import android.os.Bundle;

import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;
import com.txui.core.presenter.AbstractListPresenter;

/**
 * Created by mykolakoshurenko on 8/17/16.
 */
public abstract class AbstractListFragment extends BaseAbstractFragment<AbstractListPresenter> {

    @Override
    public abstract BaseAbstractFragment getNewInstance(Bundle bundle);

    public abstract RxRecyclerView getRecycleView();




}
