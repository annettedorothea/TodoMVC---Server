package com.anfelisa.todo.actions;

import org.skife.jdbi.v2.DBI;
import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.Action;
import com.anfelisa.ace.HttpMethod;
import com.anfelisa.ace.ICommand;
import com.anfelisa.todo.data.TodoToggleData;

import com.anfelisa.todo.commands.ToggleTodoCommand;

public abstract class AbstractToggleTodoAction extends Action<TodoToggleData> {

	public AbstractToggleTodoAction(DBI jdbi) {
		super("com.anfelisa.todo.actions.ToggleTodoAction", HttpMethod.PUT, jdbi);
	}

	@Override
	public ICommand getCommand() {
		return new ToggleTodoCommand(this.actionData, databaseHandle);
	}

	protected final void loadDataForGetRequest() {
	}

	public void initActionData(String json) {
		try {
			this.actionData = mapper.readValue(json, TodoToggleData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

}

/*       S.D.G.       */
