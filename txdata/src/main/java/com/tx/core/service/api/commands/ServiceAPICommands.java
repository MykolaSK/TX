package com.tx.core.service.api.commands;

import com.tx.core.entities.BaseEntity;

import rx.Observable;

/**
 * Created by mykolakoshurenko on 8/10/16.
 */
public interface ServiceAPICommands {
    Observable<BaseEntity> doSomething();

    void bind();

    void unbind();
}
