package com.txui.core.presenter;

import android.support.v7.widget.RecyclerView;

import com.txui.core.activities.BaseAbstractActivity;
import com.txui.core.fragments.AbstractListFragment;

/**
 * Created by mykolakoshurenko on 8/17/16.
 */
public abstract class AbstractListPresenter extends AbstractPresenter<BaseAbstractActivity, AbstractListFragment> {
    @Override
    public boolean useServiceApi() {
        return false;
    }

    public abstract RecyclerView.Adapter getRecyclerAdapter();

    private void initRecyclerAdapter() {

    }


}
