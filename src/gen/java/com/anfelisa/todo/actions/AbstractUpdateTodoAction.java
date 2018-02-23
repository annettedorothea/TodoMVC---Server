package com.anfelisa.todo.actions;

import org.skife.jdbi.v2.DBI;
import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.Action;
import com.anfelisa.ace.HttpMethod;
import com.anfelisa.ace.ICommand;
import com.anfelisa.todo.data.TodoData;

import com.anfelisa.todo.commands.UpdateTodoCommand;

public abstract class AbstractUpdateTodoAction extends Action<TodoData> {

	public AbstractUpdateTodoAction(DBI jdbi) {
		super("com.anfelisa.todo.actions.UpdateTodoAction", HttpMethod.PUT, jdbi);
	}

	@Override
	public ICommand getCommand() {
		return new UpdateTodoCommand(this.actionData, databaseHandle);
	}

	protected final void loadDataForGetRequest() {
	}

	public void initActionData(String json) {
		try {
			this.actionData = mapper.readValue(json, TodoData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

}

/*       S.D.G.       */
