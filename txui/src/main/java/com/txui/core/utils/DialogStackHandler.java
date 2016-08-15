package com.txui.core.utils;

import android.content.Context;

import java.util.ArrayDeque;
import java.util.Queue;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by mykolakoshurenko on 8/12/16.
 */
public class DialogStackHandler {

    private DialogModelHandler mCurrentRunnable;
    private ViewContext mViewContext;
    private Queue<DialogModelHandler> mDialogQueue = new ArrayDeque<>();

    private boolean isDisplaying;


    private Subscriber<? super Runnable> mDialogSubscriber;
    private Observable.OnSubscribe<Runnable> mSubscriberDialog = subscriber -> {
        mDialogSubscriber = subscriber;
    };

    private Subscriber<? super Void> mDismissSubscriber;
    private Observable.OnSubscribe<Void> mDismissDialog = subscriber -> {
        mDismissSubscriber = subscriber;
    };

    public void init(ViewContext viewContext) {

        mViewContext = viewContext;

        Observable<Void> mUserClick = Observable.create(mDismissDialog);
        Observable<Runnable> mDialogStream = Observable.create(mSubscriberDialog);

        Observable.zip(mUserClick, mDialogStream, (click, dialogRun) ->
                dialogRun
        ).subscribe(Runnable::run);
    }

    public void dismiss() {
        mDismissSubscriber.onNext(null);

        isDisplaying = false;
        mCurrentRunnable = null;
        if (!mDialogQueue.isEmpty()) {
            mCurrentRunnable = mDialogQueue.remove();
        }
        displayDialog();
    }

    public void onResume() {
        if (!isDisplaying && mViewContext.onAttach()) {
            displayDialog();
        }
    }


    public void onDestroy() {
        isDisplaying = false;
    }
//    public void onDestroy() {
//        if (isDisplaying) {
//            mDialogQueue.add(mCurrentRunnable);
//            mCurrentRunnable = null;
//        }
//    }

    private void displayDialog() {
        if (mViewContext.onAttach() && null != mCurrentRunnable) {
            mCurrentRunnable.getDialogRunnable().run();
            isDisplaying = true;
        }
    }

    public void show(Runnable runnable) {
        DialogModelHandler dialog = new DialogModelHandler(runnable);
        if (null != mCurrentRunnable) {
            mDialogQueue.add(dialog);
            //throw new RuntimeException("You should call dismiss method first");
        } else {
            mCurrentRunnable = dialog;
            displayDialog();
        }
        mDialogSubscriber.onNext(runnable);
    }


    public interface ViewContext {
        Context getViewContext();

        boolean onAttach();
    }

    private static final class DialogModelHandler {
        private final Runnable mRunnable;

        public DialogModelHandler(Runnable runnable) {
            this.mRunnable = runnable;
        }

        public Runnable getDialogRunnable() {
            return mRunnable;
        }
    }

}
