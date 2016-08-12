package com.tx.ui.core.activities;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tx.core.service.api.commands.ServiceApiCommandsImpl;


/**
 * Created by mykolakoshurenko on 8/12/16.
 */
public class BaseAbstractActivity extends AppCompatActivity {

    private ServiceApiCommandsImpl apiCommands;

   /* public ServiceApiCommandsImpl getApiCommands() {
        return apiCommands;
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       if (null == apiCommands) {
            apiCommands = new ServiceApiCommandsImpl(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        apiCommands.bind();
    }


    @Override
    protected void onStop() {
        super.onStop();
        apiCommands.unbind();
    }
}
