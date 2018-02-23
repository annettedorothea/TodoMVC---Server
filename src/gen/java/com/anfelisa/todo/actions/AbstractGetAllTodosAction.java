package com.anfelisa.todo.actions;

import org.skife.jdbi.v2.DBI;
import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.Action;
import com.anfelisa.ace.HttpMethod;
import com.anfelisa.ace.ICommand;
import com.anfelisa.todo.data.TodoListData;


public abstract class AbstractGetAllTodosAction extends Action<TodoListData> {

	public AbstractGetAllTodosAction(DBI jdbi) {
		super("com.anfelisa.todo.actions.GetAllTodosAction", HttpMethod.GET, jdbi);
	}

	@Override
	public ICommand getCommand() {
		return null;
	}


	public void initActionData(String json) {
		try {
			this.actionData = mapper.readValue(json, TodoListData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

}

/*       S.D.G.       */
