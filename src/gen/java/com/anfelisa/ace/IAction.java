package com.anfelisa.ace;

import javax.ws.rs.core.Response;

public interface IAction {

	String getActionName();
	
	HttpMethod getHttpMethod();
	
	IDataContainer getActionData();
	
	ICommand getCommand();
	
    DatabaseHandle getDatabaseHandle();
	
    void setDatabaseHandle(DatabaseHandle databaseHandle);

    Response apply();
	
	void initActionData(String json);
	
}

