package com.anfelisa.todo.actions;

import org.skife.jdbi.v2.DBI;
import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.Action;
import com.anfelisa.ace.HttpMethod;
import com.anfelisa.ace.ICommand;
import com.anfelisa.todo.data.ToggleAllData;

import com.anfelisa.todo.commands.ToggleAllCommand;

public abstract class AbstractToggleAllAction extends Action<ToggleAllData> {

	public AbstractToggleAllAction(DBI jdbi, DBI jdbiTimeline) {
		super("com.anfelisa.todo.actions.ToggleAllAction", HttpMethod.PUT, jdbi, jdbiTimeline);
	}

	@Override
	public ICommand getCommand() {
		return new ToggleAllCommand(this.actionData, databaseHandle);
	}

	protected final void loadDataForGetRequest() {
	}

	public void initActionData(String json) {
		try {
			this.actionData = mapper.readValue(json, ToggleAllData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

}

/*       S.D.G.       */
