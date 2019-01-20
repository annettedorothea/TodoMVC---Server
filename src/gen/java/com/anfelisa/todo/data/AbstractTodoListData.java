package com.anfelisa.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.AbstractData;
import com.anfelisa.ace.IDataContainer;

@SuppressWarnings("unused")
public abstract class AbstractTodoListData extends AbstractData implements ITodoListData {
	
	static final Logger LOG = LoggerFactory.getLogger(AbstractTodoListData.class);
	
	private java.util.List<com.anfelisa.todo.models.ITodoModel> todoList;
	

	public AbstractTodoListData(
		@JsonProperty("todoList") java.util.List<com.anfelisa.todo.models.ITodoModel> todoList
,		@JsonProperty("uuid") String uuid
	) {
		super(uuid);
		this.todoList = todoList;
	}

	public AbstractTodoListData( String uuid ) {
		super(uuid);
	}

	@JsonProperty
	public java.util.List<com.anfelisa.todo.models.ITodoModel> getTodoList() {
		return this.todoList;
	}
	public void setTodoList(java.util.List<com.anfelisa.todo.models.ITodoModel> todoList) {
		this.todoList = todoList;
	}
	
	
}

/*       S.D.G.       */
