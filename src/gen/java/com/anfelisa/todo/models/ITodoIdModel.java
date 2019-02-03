package com.anfelisa.todo.models;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@SuppressWarnings("unused")
@JsonDeserialize(as=TodoIdModel.class)
public interface ITodoIdModel {

	String getId();
	void setId(String id);
	
	
	List<String> equalsPrimitiveTypes(ITodoIdModel other);
	
}

/*       S.D.G.       */
