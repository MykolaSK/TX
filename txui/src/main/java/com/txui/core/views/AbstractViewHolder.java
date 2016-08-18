package com.txui.core.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mykolakoshurenko on 8/17/16.
 */
public abstract class AbstractViewHolder<V extends View> extends RecyclerView.ViewHolder {

    public AbstractViewHolder(V itemView) {
        super(itemView);
    }

}
