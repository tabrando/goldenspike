package com.kaladin.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("kaladinService")
public interface kaladinService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    /**
     * Utility/Convenience class.
     * Use kaladinService.App.getInstance() to access static instance of kaladinServiceAsync
     */
    public static class App {
        private static kaladinServiceAsync ourInstance = GWT.create(kaladinService.class);

        public static synchronized kaladinServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
