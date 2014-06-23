package com.kaladin.server.database;

/**
 * Created by branden on 6/18/14.
 */
public interface RelationalConnector
{
    public void makeConnection();
    public void closeConnection();

}
