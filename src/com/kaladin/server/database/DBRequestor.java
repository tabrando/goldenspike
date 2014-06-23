package com.kaladin.server.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/**
 * Database Requestor file.
 * Instantiates a DBConnector object for DBConnectivity
 * Creates and executes basic SQL queries for updating database properties
 * Currently supports only Postgres.
 * Created by branden on 6/17/14.
 */

//TODO:  This class needs to be refactored to handle multiple database types


public class DBRequestor implements RelationalRequestor
{

    private Map<RelationalProperty, String> dataMap;
    private DBTransactionType transactionType;
    private DBConnector connector;
    private ResultSet results;

    public DBRequestor(Map<RelationalProperty, String> map, DBTransactionType type)
    {
        this.dataMap = map;
        this.transactionType = type;
        this.connector = new DBConnector();
    }

    public ResultSet requestData()
    {
        //attempt to make a connection to the DB
        connector.makeConnection();

        String sql =
                "SELECT " + dataMap.get(RelationalProperty.COLUMN) +
                " FROM " + dataMap.get(RelationalProperty.TABLE) +
                " WHERE " + dataMap.get(RelationalProperty.KEYNAME) +
                " = " + dataMap.get(RelationalProperty.KEYVALUE);
        try
        {
            Statement statement = connector.currentConnection.createStatement();
            return results = statement.executeQuery(sql);
        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
        }
        return null;
    }

    public void submitData()
    {
        if(transactionType == null)
        {
            throw new NullPointerException("TransactionType is Null");
        }
    }

}
