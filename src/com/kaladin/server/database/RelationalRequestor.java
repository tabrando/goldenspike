package com.kaladin.server.database;

import java.sql.ResultSet;

/**
 * Created by branden on 6/18/14.
 */
public interface RelationalRequestor
{
    ResultSet requestData();
    void submitData();
}
