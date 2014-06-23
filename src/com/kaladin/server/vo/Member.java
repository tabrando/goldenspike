package com.kaladin.server.vo;

/**
 * Created by branden on 6/17/14.
 */

import com.kaladin.server.database.DBRequestor;
import com.kaladin.server.database.DBTransactionType;
import com.kaladin.server.database.RelationalProperty;
import com.kaladin.server.database.RelationalRequestor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


public class Member
{

    private final String TABLE = "member";
    private final String KEYNAME = "memberID";
    private String column;

    private RelationalRequestor dbRequestor;
    private Map<RelationalProperty, String> dbInfo;
    private ResultSet results;


    public Member()
    {
        dbInfo.put(RelationalProperty.KEYNAME, KEYNAME);
        dbInfo.put(RelationalProperty.TABLE, TABLE);
    }

    private String getFirstName(String targetValue)
    {
        this.column = "first_name";

        //populate the dbInfo map. You'll always have TABLE and KEYNAME pre-populated

        dbInfo.put(RelationalProperty.COLUMN, column);
        dbInfo.put(RelationalProperty.TARGET, targetValue);

        //make the request
        dbRequestor = new DBRequestor(dbInfo, DBTransactionType.QUERY);
        results = dbRequestor.requestData();

        if(results != null)
        {
            try
            {
                return results.getString(column);
            }
            catch (SQLException sqle)
            {
                sqle.printStackTrace();
            }
        }

        return "No Results Found";
    }


}
