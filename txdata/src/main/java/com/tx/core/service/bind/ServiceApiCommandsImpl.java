package com.tx.core.service.bind;

import android.content.Context;
import android.os.RemoteException;


import com.tx.core.ServiceAidl;
import com.tx.core.entities.BaseEntity;
import com.tx.core.service.BaseService;
import com.tx.core.service.api.commands.ServiceAPICommands;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;

/**
 * Created by mykolakoshurenko on 8/10/16.
 */
public class ServiceApiCommandsImpl implements ServiceAPICommands {
    private ServiceBindImpl mBind;
    private Context context;

    public ServiceApiCommandsImpl(Context localContext) {
        context = localContext;
        initServiceBinder();
    }

    private void initServiceBinder() {
        mBind = new ServiceBindImpl(context, BaseService.class);
    }

    private void checkBindingAndReconnect() {
        if (!mBind.isBinderAlive()) mBind.reconnect();
    }

    @Override
    public Observable<BaseEntity> doSomething() {
        final PublishSubject<BaseEntity> loadData = PublishSubject.create();
        checkBindingAndReconnect();
        mBind.subscribe(new Subscriber<ServiceAidl>() {
            @Override
            public void onCompleted() {
                loadData.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                loadData.onError(e);
            }

            @Override
            public void onNext(ServiceAidl o) {
                /*try {
                    List<BaseEntity> data = o.test();
                    for (BaseEntity item : data) {
                        loadData.onNext(item);
                    }
                } catch (RemoteException e) {
                    loadData.onError(e);
                }*/
            }
        });
        return loadData.asObservable();
    }

    @Override
    public void bind() {
        mBind.bind();
    }

    @Override
    public void unbind() {
        mBind.unbind();
    }
}
