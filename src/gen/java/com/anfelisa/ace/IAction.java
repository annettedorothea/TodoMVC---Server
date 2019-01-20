package com.anfelisa.ace;

import javax.ws.rs.core.Response;

public interface IAction {

	String getActionName();
	
	HttpMethod getHttpMethod();
	
	IDataContainer getActionData();
	
	void setActionData(IDataContainer data);
	
	ICommand getCommand();
	
    DatabaseHandle getDatabaseHandle();
	
    void setDatabaseHandle(DatabaseHandle databaseHandle);

    Response apply();

}

