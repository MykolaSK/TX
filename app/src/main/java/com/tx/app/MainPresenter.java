package com.tx.app;

import com.txui.core.fragments.BaseAbstractFragment;
import com.txui.core.presenter.AbstractPresenter;

/**
 * Created by mykolakoshurenko on 8/16/16.
 */
public class MainPresenter extends AbstractPresenter<MainActivity, BaseAbstractFragment> {
    @Override
    public boolean useServiceApi() {
        return false;
    }


    @Override
    public void onBindView(PView view) {
        super.onBindView(view);
    }
}
