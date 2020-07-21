/* 
 * Copyright (c) 2020, Annette Pohl, Koblenz, Germany
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.

 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 * generated with de.acegen 0.9.7
 *
 */




package com.anfelisa.todo.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.models.ITodoModel;

import de.acegen.CustomAppConfiguration;
import de.acegen.E2E;
import de.acegen.IDaoProvider;
import de.acegen.PersistenceConnection;
import de.acegen.PersistenceHandle;
import de.acegen.ViewProvider;

public class GetTodoAction extends AbstractGetTodoAction {

	static final Logger LOG = LoggerFactory.getLogger(GetTodoAction.class);

	public GetTodoAction(PersistenceConnection persistenceConnection, CustomAppConfiguration appConfiguration, IDaoProvider daoProvider, 
			ViewProvider viewProvider, E2E e2e) {
		super(persistenceConnection, appConfiguration, daoProvider, viewProvider, e2e);
	}


	@Override
	protected void loadDataForGetRequest(PersistenceHandle readonlyHandle) {
		ITodoModel todo = daoProvider.getTodoDao().selectById(readonlyHandle, this.actionData.getId());
		this.actionData.setDescription(todo.getDescription());
		this.actionData.setDone(todo.getDone());
		this.actionData.setCreatedDateTime(todo.getCreatedDateTime());
		this.actionData.setUpdatedDateTime(todo.getUpdatedDateTime());
	}
	
	public void initActionData() {
		// init not replayable data here
	}

}




/******* S.D.G. *******/



