package com.kaladin.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface kaladinServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
