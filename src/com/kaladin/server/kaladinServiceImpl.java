package com.kaladin.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.kaladin.client.kaladinService;

public class kaladinServiceImpl extends RemoteServiceServlet implements kaladinService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}