package com.tx.core.entities;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;


/**
 * Created by mykolakoshurenko on 8/10/16.
 */
@Parcel
public class BaseEntity {
    public String id;

    @ParcelConstructor
    public BaseEntity(String id) {
        this.id = id;
    }

}
