package com.txui.core.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.jakewharton.rxbinding.internal.Preconditions;

/**
 * Created by mykolakoshurenko on 8/17/16.
 */
public abstract class AbstractView<T> extends View {
    private T mData;

    private boolean isFinishedInflate;

    public AbstractView(Context context) {
        super(context);
    }

    public AbstractView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributeAndInflate(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        isFinishedInflate = true;
        bindDataAfterInflate(mData);
    }

    protected abstract void bindDataAfterInflate(T data);

    public void bind(T data) {
        Preconditions.checkUiThread();
        Preconditions.checkNotNull(data, "data can't be null");
        mData = data;
        if (isFinishedInflate) bindDataAfterInflate(mData);
    }

    public abstract void initAttributeAndInflate(Context context, AttributeSet attrs);


}
