package com.ext.portlet.debaterevision.model.impl;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.sql.DataSource;

public class ReferencableDS implements DataSource, Referenceable {

    @Override
    public Connection getConnection() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Connection getConnection(String arg0, String arg1) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setLogWriter(PrintWriter arg0) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setLoginTimeout(int arg0) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Reference getReference() throws NamingException {
        // TODO Auto-generated method stub
        return null;
    }

}
