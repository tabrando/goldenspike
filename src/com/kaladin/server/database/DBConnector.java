package com.kaladin.server.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Creates a connection with a database.  Takes configuration settings from a properties file stored in resources/database.properties
 * Currently created by the VO's when needed.  Will convert to factory creation methodology.
 * Created by branden on 6/17/14.
 */

//TODO: This class should be instantiated by a factory - possibly abstract as there are a limited number of DBTypes that we will support.
public class DBConnector implements RelationalConnector
{
    public enum DBFields { DBTYPE, DBNAME, URL, USERNAME, PASSWORD }
    public Connection currentConnection = null;
    public Properties connectionProperties = null;

    public DBConnector()
    {
        gatherProperties();
    }

    private void gatherProperties()
    {
        try
        {
            FileInputStream fileInput = new FileInputStream(new File("resources/database.properties"));
            connectionProperties = new Properties();
            connectionProperties.load(fileInput);
            fileInput.close();
        }
        catch (FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }

    }

    private void connect() throws SQLException
    {
        this.currentConnection = DriverManager.getConnection(connectionProperties.getProperty(DBFields.URL.name()),
                connectionProperties.getProperty(DBFields.USERNAME.name()),
                connectionProperties.getProperty(DBFields.PASSWORD.name()));
    }

    private void disconnect() throws SQLException

    {
        currentConnection.close();
    }

    public void makeConnection()
    {
        try
        {
            connect();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
    }

    public void closeConnection()
    {
        try
        {
            currentConnection.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
    }


}