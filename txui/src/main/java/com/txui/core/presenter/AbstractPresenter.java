package com.txui.core.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.tx.core.service.api.commands.ServiceApiCommandsImpl;
import com.txui.core.activities.BaseAbstractActivity;
import com.txui.core.fragments.BaseAbstractFragment;
import com.txui.core.utils.DialogStackHandler;

/**
 * Created by mykolakoshurenko on 8/15/16.
 */
public abstract class AbstractPresenter<A extends BaseAbstractActivity, F extends BaseAbstractFragment> {

    private ServiceApiCommandsImpl mApiCommands;
    private DialogStackHandler mDialogHandler;
    private PView<A, F> mView;
    private volatile boolean isViewBind;


    public AbstractPresenter() {
        mDialogHandler = new DialogStackHandler();
    }

    public void onBindView(PView<A, F> view) {
        isViewBind = true;
        if (useServiceApi()) {
            initServiceApi(view.getActivityContext());
            mApiCommands.bind();
        }
        mView = view;
        mDialogHandler.init(new DialogStackHandler.ViewContext() {
            @Override
            public Context getViewContext() {
                return mView.getActivityContext();
            }

            @Override
            public boolean onAttach() {
                return isViewBind;
            }
        });
    }

    public void onUnbindView(PView view) {
        isViewBind = false;
        if (useServiceApi()) mApiCommands.unbind();
        mView = view;
    }

    public DialogStackHandler getDialogHandler() {
        return mDialogHandler;
    }

    public ServiceApiCommandsImpl getApiCommands() {
        return mApiCommands;
    }

    public abstract boolean useServiceApi();

    protected void initServiceApi(Context context) {
        if (null == mApiCommands) {
            mApiCommands = new ServiceApiCommandsImpl(context);
        }
    }

    protected PView<A, F> getView() {
        return mView;
    }

    public static final class PView<A extends BaseAbstractActivity, F extends BaseAbstractFragment> {
        private final A mActivity;
        private final F mFragment;

        public PView(@NonNull A activity, F fragment) {
            this.mActivity = activity;
            this.mFragment = fragment;
        }

        public Context getActivityContext() {
            return mActivity;
        }

        public final A getActivity() {
            return mActivity;
        }

        public final F getFragment() {
            return mFragment;
        }
    }


}
