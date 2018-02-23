package com.anfelisa.todo.actions;

import org.skife.jdbi.v2.DBI;
import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.Action;
import com.anfelisa.ace.HttpMethod;
import com.anfelisa.ace.ICommand;
import com.anfelisa.todo.data.ClearDoneData;

import com.anfelisa.todo.commands.ClearDoneCommand;

public abstract class AbstractClearDoneAction extends Action<ClearDoneData> {

	public AbstractClearDoneAction(DBI jdbi) {
		super("com.anfelisa.todo.actions.ClearDoneAction", HttpMethod.DELETE, jdbi);
	}

	@Override
	public ICommand getCommand() {
		return new ClearDoneCommand(this.actionData, databaseHandle);
	}

	protected final void loadDataForGetRequest() {
	}

	public void initActionData(String json) {
		try {
			this.actionData = mapper.readValue(json, ClearDoneData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

}

/*       S.D.G.       */
