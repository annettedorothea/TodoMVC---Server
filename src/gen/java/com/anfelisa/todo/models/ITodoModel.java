package com.anfelisa.todo.models;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@SuppressWarnings("unused")
@JsonDeserialize(as=TodoModel.class)
public interface ITodoModel {

	String getId();
	void setId(String id);
	
	String getDescription();
	void setDescription(String description);
	
	Boolean getDone();
	void setDone(Boolean done);
	
	org.joda.time.DateTime getCreatedDateTime();
	void setCreatedDateTime(org.joda.time.DateTime createdDateTime);
	
	org.joda.time.DateTime getUpdatedDateTime();
	void setUpdatedDateTime(org.joda.time.DateTime updatedDateTime);
	
	
	List<String> equalsPrimitiveTypes(ITodoModel other);
	
}

/*       S.D.G.       */
