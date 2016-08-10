package com.tx.core.service.bind;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;

import com.tx.core.ServiceAidl;

import rx.Subscriber;
import rx.Subscription;
import rx.subjects.BehaviorSubject;

/**
 * Created by mykolakoshurenko on 8/10/16.
 */
public class ServiceBindImpl {
    private BehaviorSubject<ServiceAidl> connectSbj = BehaviorSubject.create();
    private IBinder mServiceBinder;


    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mServiceBinder = service;
            connectSbj.onNext(ServiceAidl.Stub.asInterface(service));
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            connectSbj.onCompleted();
        }
    };

    private Intent mServiceIntent;
    private Context mContext;

    public ServiceBindImpl(Context context, Class serviceClazz) {
        mContext = context;
        mServiceIntent = new Intent(context, serviceClazz);
    }

    public void reconnect() {
        connectSbj = BehaviorSubject.create();
        bind();
    }

    public boolean isBinderAlive() {
        return null != mServiceBinder && mServiceBinder.isBinderAlive() && mServiceBinder.pingBinder();
    }

    public void bind() {
        mContext.bindService(mServiceIntent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public void unbind() {
        mContext.unbindService(mConnection);
    }


    public Subscription subscribe(Subscriber subscriber) {
        return connectSbj.subscribe(subscriber);
    }
}
